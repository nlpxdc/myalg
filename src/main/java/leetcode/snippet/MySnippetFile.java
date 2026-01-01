package leetcode.snippet;

public class MySnippetFile {
    public static void main(String[] args) {
        System.out.println("hello cjf");
        System.out.println("hello cjf");


    }

    public static void swap(int[] ary, int idxLeft, int idxRight) {
        if (idxLeft == idxRight) return;
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    public static long key2unorderedS2b(int x, int y) {
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        return ((long) x << 32) | y;
    }

    public static int[] unpack2(long key) {
        int first = (int) (key >>> 32);
        int second = (int) (key & 0xFFFF_FFFFL);
        return new int[]{first, second};
    }

    public static int[] unpack3(long key) {
        int z = (int) (key & 0x1F_FFFFL);
        int y = (int) ((key >>> 21) & 0x1F_FFFFL);
        int x = (int) (key >>> 42);
        return new int[]{x, y, z};
    }

}
