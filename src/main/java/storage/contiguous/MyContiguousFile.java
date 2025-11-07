package storage.contiguous;

class MyContiguousFile {
    public static void main(String[] args) {
        System.out.println("aa");

        //new delete malloc free

        boolean[] booleans = new boolean[100000];
        byte[] bytes = new byte[100000];
        short[] shorts = new short[100000];
        int[] ints = new int[100000];
        long[] longs = new long[100000];
        char[] chars = new char[100000];

        Object[] objects = new Object[100000];
        Boolean[] booleans1 = new Boolean[100000];
        Byte[] bytes1 = new Byte[100000];
        Short[] shorts1 = new Short[100000];
        Integer[] integers = new Integer[100000];
        Long[] longs1 = new Long[100000];
        Character[] characters = new Character[100000];

        String[] strings = new String[100000];

        Void[] voids = new Void[100000];

        MyObj[] myObjs = new MyObj[100000];

        //头插O(n)，尾插O(1)

    }
}

class MyObj {
    int age;
    Long[] longs;
    String[][] strAryAry;
}
