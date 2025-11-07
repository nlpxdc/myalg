package storage.hash;

class MyHashFile {
    public static void main(String[] args) {
        System.out.println("aa");

        Object[] objects = new Object[100000];

        Object o0 = new Object();
        int i0 = o0.hashCode() % 100000;

        Object o1 = new Object();
        int i1 = o1.hashCode() % 100000;

        Object o2 = new Object();
        int i2 = o2.hashCode() % 100000;

        Object o3 = new Object();
        int i3 = o3.hashCode() % 100000;

//        myHashCode()
        objects[i0] = o0;
        objects[i1] = o1;
        objects[i2] = o2;
        objects[i3] = o3;


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
