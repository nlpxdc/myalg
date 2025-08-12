package hash;

class MyHashApp {
    public static void main(String[] args) {
        System.out.println("aa");

        String str = "hello hash";
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
            hash = (hash*31+byteData) % mod;
        }
        return hash;
    }
//    3 异或hash
    static int xorHash(byte[] data) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash ^= byteData;
            hash = (hash << 4) ^ (hash >>> 2);
        }
        hash &= 0xFFFF;
        return hash;
    }
//    4 旋转hash&滚动hash
    static int rollingHash(byte[] data, int mod) {
        int hash = 0;
        for (int i = 0; i < data.length; i++) {
            byte byteData = data[i];
            hash = (hash*257+byteData) % mod;
        }
        return hash;
    }
//    5 组合hash FNV-1a
    static int fnv1aHash(byte[] data) {
        return 0;
    }
    //hash冲突解决的算法？

    //校验 CRC

    //一致性 ketama一致性hash环



}
