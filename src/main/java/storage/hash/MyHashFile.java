package storage.hash;

class MyHashFile {
    public static void main(String[] args) {
        System.out.println("aa");

//        Object[] objects = new Object[100000];
//
//        Object o0 = new Object();
//        int i0 = o0.hashCode() % 100000;
//
//        Object o1 = new Object();
//        int i1 = o1.hashCode() % 100000;
//
//        Object o2 = new Object();
//        int i2 = o2.hashCode() % 100000;
//
//        Object o3 = new Object();
//        int i3 = o3.hashCode() % 100000;
//
////        myHashCode()
//        objects[i0] = o0;
//        objects[i1] = o1;
//        objects[i2] = o2;
//        objects[i3] = o3;

        String[] stringAry = new String[100000];

        String abc = new String("abc");
        String def = new String("def");
        String ghi = new String("ghi");

        stringAry[myHashCode("abc")%100000] = abc;
        stringAry[myHashCode("def")%100000] = def;
        stringAry[myHashCode("ghi")%100000] = ghi;

    }

    static int myHashCode(String str) {
        int h = 0;
        char[] charArray = str.toCharArray();
        if (charArray.length > 0) {
            for (int i = 0; i < charArray.length; i++) {
                h = 31 * h + charArray[i];
            }
        }
        return h;
    }

//    static int myHashCode(int[] val) {
//        int h = 0;
//        for (int i = 0; i < val.length; i++) {
//            h = 31 * h + val[i];
//        }
//        return h;
//    }

}

//class MyNode {
//
//}
