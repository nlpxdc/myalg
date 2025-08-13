package hash;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

class MyHashApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int num = 0xFF;
        int num2 = 0xff;
        int num3 = 0xFf;
        int num4 = 0xfF;
        int num5 = 0Xff;
        int num6 = 0Xf;
        int num7 = 077;
        int num8 = 076;
        int num9 = 0b1;
        int num10 = 0B0;
        int num11 = 0B10;

        String str = "hello hashab";
        int i1 = crc32(str.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = addHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i2 = multiHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i3 = xorAndBitMoveHash(str.getBytes(StandardCharsets.UTF_8), 8);
//        int i4 = rollingHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i5 = fnv1aHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i = str.hashCode();
    }

    //目的为了校验检错，要求快，成本低，够准，判断准确率大，纠错码 成本高，慢，因为冗余多，但是链路物理链路真的慢又容易出错，要用纠错码，更多容易划算，比如卫星通讯？深空通讯？
    //最快，不要求均匀散列，不抗碰撞恶意
    //校验一致性，文件 以太网
    // CRC32 反转按照lsb 最终异或安全要求避免全0 查表 优化手段 static静态初始化表
    //反转多项式 0xEDB88320是为了 LSB-First
    static final int POLY = 0x04C11DB7;
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

    static byte[] intToByteAry(int key) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(key);
        byte[] array = byteBuffer.array();
        return array;
    }

//    1 加法hash
    //就是用上所有数据，这个不能算 不是打散手段
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
    //这个其实接近fnv-1a了，这里先异或再位移了。也可以先位移再异或，这样较差？
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
