package leetcode.snippet;

public class SnippetUtil {

    //时间O(1) 空间O(1)
    public static void swap(int[] ary, int idxLeft, int idxRight) {
        if (idxLeft == idxRight) return;
        swap0(ary,idxLeft, idxRight);
    }

    //时间O(1) 空间O(1)
    public static void swap0(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    //时间O(1) 空间O(0)
    public static void swap1(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] + ary[idxRight];
//        ary[idxRight] = ary[idxLeft] - ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] - ary[idxRight];

        ary[idxLeft] += ary[idxRight];
        ary[idxRight] = ary[idxLeft] - ary[idxRight];
        ary[idxLeft] -= ary[idxRight];
    }

    //时间O(1) 空间O(0)
    public static void swap2(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] * ary[idxRight];
//        ary[idxRight] = ary[idxLeft] / ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] / ary[idxRight];

        ary[idxLeft] *= ary[idxRight];
        ary[idxRight] = ary[idxLeft] / ary[idxRight];
        ary[idxLeft] /= ary[idxRight];
    }

    //时间O(1) 空间O(0)
    public static void swap3(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxRight] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] ^ ary[idxRight];

//        ary[idxLeft] ^= ary[idxRight];
//        ary[idxRight] = ary[idxLeft] ^ ary[idxRight];
//        ary[idxLeft] ^= ary[idxRight];

        ary[idxLeft] ^= ary[idxRight];
        ary[idxRight] ^= ary[idxLeft];
        ary[idxLeft] ^= ary[idxRight];
    }

    public static int key1(int x) {
        return x;
    }

    public static int unpack1(int key) {
        return key;
    }

    public static long key2ordered(int x, int y) {
        return ((long) x << 32) | y;
    }

    public static long key2unordered(int x, int y) {
        return key2unorderedS2b(x, y);
    }

    //时间O(1) 空间O(1)
    public static long key2unorderedS2b(int x, int y) {
        if (x > y) {int t = x; x = y; y = t;}
        return ((long) x << 32) | y;
    }

    public static long key2unorderedB2s(int x, int y) {
        if (x < y) {int t = x; x = y; y = t;}
        return ((long) x << 32) | y;
    }

    public static int[] unpack2ordered(long key) {
        int first = (int) (key >>> 32);
        int second = (int) (key & 0xFFFF_FFFFL);
        return new int[]{first, second};
    }

    //时间O(1) 空间O(0)
    public static int[] unpack2unordered(long key) {
        int first = (int) (key >>> 32);
        int second = (int) (key & 0xFFFF_FFFFL);
        return first > second ? new int[] {second, first} : new int[]{first, second};
    }

    public static long key3(int x, int y, int z) {
        if (x > y) {int t=x;x=y;y=t;}
        if (x > z) {int t=x;x=z;z=t;}
        if (y > z) {int t=y;y=z;z=t;}
        return ((long) x << 42) | ((long) y << 21) | z;
    }

}
