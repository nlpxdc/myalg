package operation.retobj.generate.space;

import java.nio.ByteBuffer;

//伪散列 空间函数 位置确定，映射值确定
//对地址处理，显式地址，可预期，生成
//也可对值处理，签名验签 摘要
//散列三把刀 乘法（可位移，用素数质数） 移位（换块，高低位，非位移） 终混（混合，混淆）
//还有三把刀 取模（快速定位） 移位（打破连续） 位运算（提速） 异或（雪崩 扩散）
//取模 -> 乘法 移动 异或 (非线性 扰动) 扰动 取桶
//common的 不带时间的输入input 就一个种子seed，讲空间均匀分布，不讲周期不讲时间
//“移位 + 异或 + 乘法” 这条组合拳在两者里都出现，原因是它既廉价又能快速扩散比特。
//hash 用它加速雪崩并降低碰撞
//评价指标和调参方向完全不同，只能算“共用砖块”，不是“共用建筑图纸”。
//hash 用长度编码、分块并行、树形合并、尾部填充——PRNG 没有“任意长度”问题，也用不到。
//PRNG 用本原多项式、Lagged Fibonacci 的借位链、WELL 的巨型状态矩阵——这些在 hash 里根本不会出现；
//但“不重复”背后的理论工具、评价指标、调参目标完全不同
//hash 玩的是碰撞概率 + 雪崩速度。
//经常用在map中，天然适配
//还有完美hash
//测哈希：SMhasher 最全面，Hashcat 看纯速度。
//        测随机：TestU01 / PractRand / dieharder 三件套，统计学报告直接出。
class MyHashApp {
    public static void main(String[] args) {
//        System.out.println("aa");
//        int num = 0xFF;
//        int num2 = 0xff;
//        int num3 = 0xFf;
//        int num4 = 0xfF;
//        int num5 = 0Xff;
//        int num6 = 0Xf;
//        int num7 = 077;
//        int num8 = 076;
//        int num9 = 0b1;
//        int num10 = 0B0;
//        int num11 = 0B10;

//        String str = "hello hashab";
//        int i1 = crc32(str.getBytes(StandardCharsets.UTF_8));
//        byte[] bytes = addHash(str.getBytes(StandardCharsets.UTF_8), 8);
//        int i2 = multiHash(str.getBytes(StandardCharsets.UTF_8), 8);
//        int i3 = xorAndBitMoveHash(str.getBytes(StandardCharsets.UTF_8), 8);
////        int i4 = rollingHash(str.getBytes(StandardCharsets.UTF_8), 8);
//        int i5 = fnv1aHash(str.getBytes(StandardCharsets.UTF_8), 8);
//        int i = str.hashCode();

//        ZipHashMap<String, Object> zipHashMap = new ZipHashMap<>();
//        zipHashMap.put("cjf", 3);
//        zipHashMap.put("davidc", 5L);
//        zipHashMap.put("nlpx", 4);
//        zipHashMap.put("nlpxdc", 8);
//        zipHashMap.put("davidchen", 9);
//        zipHashMap.put("chenjiefei", 10);
//
//        Object cjf = zipHashMap.get("cjf");
//        Object davidc = zipHashMap.get("davidc");
//        Object xxx = zipHashMap.get("xxx");

        ProbeHashMap<String, Object> probeHashMap = new ProbeHashMap<>();
        probeHashMap.put("cjf", 3);
        probeHashMap.put("davidc", 5L);
        probeHashMap.put("nlpx", 4);
        probeHashMap.put("nlpxdc", 8);
        probeHashMap.put("davidchen", 9);
        probeHashMap.put("chenjiefei", 10);

        Object cjf = probeHashMap.get("cjf");
        Object davidc = probeHashMap.get("davidc");
        Object xxx = probeHashMap.get("xxx");
        Object davidchen = probeHashMap.get("davidchen");
    }

    //目的为了校验检错，要求快，成本低，够准，判断准确率大，纠错码 成本高，慢，因为冗余多，但是链路物理链路真的慢又容易出错，要用纠错码，更多容易划算，比如卫星通讯？深空通讯？
    //最快，不要求均匀散列，不抗碰撞恶意
    //校验一致性，文件 以太网
    // CRC32 反转按照lsb 最终异或安全要求避免全0 查表 优化手段 static静态初始化表
    //反转多项式 0xEDB88320是为了 LSB-First
    //只为检验，不为散开，所以堆散列要求不高，所以不能算正统的hash算法，要求的是快
    //就是求和，所以是加法求和而已
    static final int POLY = 0x04C11DB7; //对应的prime质数？
    static int crc32(byte[] data) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (byteData & 0xFF) << 24;
            for (int j = 0; j < 8; j++) {
                if ((hash & 0x80000000) != 0) {
                    hash = (hash << 1) ^ POLY;
                } else {
                    hash <<= 1;
                }
            }
        }
        return hash;
    }

    //普通散列hash，目的够散列，最原始最初始的目的
    //教学 计算下标，索引位置
    //返回其实可以是定长的byte[], 比如md5的32位或16位，相当于16进制数，用字符串表示，大数
    //32位=32*4=128bit位=16字节，16位=8字节，int 4字节 2个字 long 8字节 4个字
    //target的hash空间int不够大 long还行 md5再翻倍，但是还是不安全，所以还是要256bit，区块链？ sha2-256 sha2-512？
    //sha3？
    //mod是让int的4字节空间，变小变成可用下标索引桶位置，圈定更小范围，那这里就要解决hash冲突了
    //关键是原空间4字节空间的打散程度
    //这个是必须够散列才行，主要是给hash表用的，要够散列和均匀，区域数后要平均

    static byte[] intToByteAry(int key) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(key);
        byte[] array = byteBuffer.array();
        return array;
    }

//    1 加法hash
    //就是用上所有数据，这个不能算 不是打散手段
    //就是求和检验而已，相当于crc，但是求和是怎么都不能少的，是基础
    static byte[] addHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash + byteData) % mod;
        }
//        return hash;
        return intToByteAry(hash);
    }
//    2 乘法hash
    //这个算 扩散 高低位 不同于位移，乘法是质数
    static int multiHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash*31+byteData) % mod; //prime
        }
        return hash;
    }
//    3 异或hash和位移（2的倍数） 异或位移一般同时出现
    //非线性 原有混合 位移扩散（但和乘以质数扩散不一样），然后两者结合导致雪崩 50%bit改变
    //这个其实接近fnv-1a了，这里先异或再位移了。
    // 也可以先位移再异或，这样较差？
    static int xorAndBitMoveHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash ^= byteData; //异或
            hash = (hash << 4) ^ (hash >>> 2); //位移
        }
        hash &= 0xFFFF;
        hash = hash % mod;
        return hash;
    }
////    4 旋转hash&滚动hash
//    //可以不要，同乘法，只是质数不同
//    static int rollingHash(byte[] data, int mod) {
//        int hash = 0;
//        for (int i = 0; i < data.length; i++) {
//            byte byteData = data[i];
//            hash = (hash*257+byteData) % mod; //prime
//        }
//        return hash;
//    }
//    5 组合hash FNV-1a
    //这个其实是异或加乘法了
    static int fnv1aHash(byte[] data, int mod) {
        int hash = 0x811C9DC5; //offset
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash ^= byteData & 0xFF;
            hash *= 0x01000193; //prime
            //这里还可以加个位移
        }
        hash = hash % mod;
        return hash;
    }


    //hash冲突解决的算法？



    //一致性 ketama一致性hash环



}

class Node<K,V> {
    K k;
    V v;
    Node<K,V> next;

    Node(K k, V v) {
        this.k = k;
        this.v = v;
    }
}

//解决散列冲突 拉链法 拉树法，没拉图法
//装填因子很重要，目标的装填因子，loadFactor，就是你预估需要插入的总数/要设计的大小 或实际数量/设计大小，约等于0.75 0.6浪费 0.8拉链长度大，性能退化O(n)
//泊松 Poisson分布 λ P(8) 涉及到拉链长度，即冲突概率
//随即涉及到扩容 arraylist 翻倍
//不所容，真要缩容，弄一个更小的进行putAll 复制克隆
class ZipHashMap<K,V> {
    Node<K,V>[] buckets = new Node[8];

    public void put(K k, V v) {
        //0x7fffffff 最高位清零
        int idx = (k.hashCode() & 0x7fffffff) % buckets.length;
        Node<K,V> node = new Node<>(k, v);
        node.next = buckets[idx];
        buckets[idx] = node;
    }

    //删除方法
    // 链表桶
    //示例代码 真删节点
//    for (Node<K,V> prev = null, e = first; e != null; ) {
//        if (key.equals(e.key)) {
//            if (prev == null) first = e.next;
//            else prev.next = e.next;
//            e.value = null; // help GC
//            size--; //维护元数据
//            break;
//        }
//    }

    //更新忽略，先查找，再更新原节点的内容，结构不变，结构不变就还好

    public V get(K k) {
        int idx = (k.hashCode() & 0x7fffffff) % buckets.length;
        for (Node<K,V> current = buckets[idx]; current != null; current = current.next) {
            if (current.k.equals(k)) {
                return current.v;
            }
        }
        return null;
    }

}

//解决散列冲突 开放地址法
//也有装填因子 只是计算不太一样 扩容也存在
//线性探测 0.7 二次探测 0.85 双重hash 0.85 步长？
class ProbeHashMap<K,V> {
    //对其，也可以一个数组？好像不合适，就是kv两个，两个数组，做对应，怎么解决并发写入？因为有2步，但是结构必须是这个
    //应该可以的也用entry，一个数组即可，看看
    Object[] keys = new Object[4];
    Object[] vals = new Object[4];

    public void put(K k, V v) {
        int idx = (k.hashCode() & 0x7fffffff) % keys.length;
        for (int i = 0; i < keys.length; i++) {
            if (keys[idx] != null) {
                idx = (idx+1) % keys.length;
            }
        }
        if (keys[idx] == null) {
            keys[idx] = k;
            vals[idx] = v;
        } else {
//            throw new RuntimeException("放满了");
            System.out.println(String.format("放满了, %s->%s", k, v));
            return;
        }
    }

    //删除方法
    //更新方法，和节点有点不同，这是一一对应的

    public V get(K k) {
        int idx = (k.hashCode() & 0x7fffffff) % keys.length;
        for (int i = 0; i < keys.length && keys[idx] != null; i++) {
            if (keys[idx] == k) {
                return (V) vals[idx];
            } else {
                idx = (idx+1) % keys.length;
            }
        }
        return null;
    }

}
