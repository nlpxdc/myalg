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
        int i1 = addHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i2 = multiHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i3 = xorHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i4 = rollingHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i5 = fnv1aHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i = str.hashCode();
    }

    //教学 计算下标，索引位置
    //返回其实可以是定长的byte[], 比如md5的32位或16位，相当于16进制数，用字符串表示，大数
    //32位=32*4=128bit位=16字节，16位=8字节，int 4字节 2个字 long 8字节 4个字
    //target的hash空间int不够大 long还行 md5再翻倍，但是还是不安全，所以还是要256bit，区块链？ sha2-256 sha2-512？
    //sha3？

    static byte[] intToByteAry(int key) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.putInt(key);
        byte[] array = byteBuffer.array();
        return array;
    }

//    1 加法hash
    //就是用上所有数据，这个不能算
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
    //这个算
    static int multiHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash*31+byteData) % mod; //prime
        }
        return hash;
    }
//    3 异或hash
    static int xorHash(byte[] data, int mod) {
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
//    4 旋转hash&滚动hash
    static int rollingHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash*257+byteData) % mod; //prime
        }
        return hash;
    }
//    5 组合hash FNV-1a
    static int fnv1aHash(byte[] data, int mod) {
        int hash = 0x811C9DC5; //offset
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash ^= byteData & 0xFF;
            hash *= 0x01000193; //prime
        }
        hash = hash % mod;
        return hash;
    }
    //hash冲突解决的算法？

    //校验 CRC

    //一致性 ketama一致性hash环



}
