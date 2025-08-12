package hash;

import java.nio.charset.StandardCharsets;

class MyHashApp {
    public static void main(String[] args) {
        System.out.println("aa");

        String str = "hello hashab";
        int i1 = addHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i2 = multiHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i3 = xorHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i4 = rollingHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i5 = fnv1aHash(str.getBytes(StandardCharsets.UTF_8), 8);
        int i = str.hashCode();
    }

    //教学 计算下标，索引位置
//    1 加法hash
    static int addHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash + byteData) % mod;
        }
        return hash;
    }
//    2 乘法hash
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
