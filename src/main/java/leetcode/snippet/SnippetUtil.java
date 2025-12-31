package leetcode.snippet;

public class SnippetUtil {

    public static void swap(int[] ary, int idxLeft, int idxRight) {
        if (idxLeft == idxRight) return;
        swap0(ary,idxLeft, idxRight);
    }

    public static void swap0(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

    public static void swap1(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] + ary[idxRight];
//        ary[idxRight] = ary[idxLeft] - ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] - ary[idxRight];

        ary[idxLeft] += ary[idxRight];
        ary[idxRight] = ary[idxLeft] - ary[idxRight];
        ary[idxLeft] -= ary[idxRight];
    }

    public static void swap2(int[] ary, int idxLeft, int idxRight) {
//        ary[idxLeft] = ary[idxLeft] * ary[idxRight];
//        ary[idxRight] = ary[idxLeft] / ary[idxRight];
//        ary[idxLeft] = ary[idxLeft] / ary[idxRight];

        ary[idxLeft] *= ary[idxRight];
        ary[idxRight] = ary[idxLeft] / ary[idxRight];
        ary[idxLeft] /= ary[idxRight];
    }

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

}
