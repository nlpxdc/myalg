package leetcode.snippet;

public class SnippetUtil {

    public static void swap(int[] ary, int idxLeft, int idxRight) {
        swap0(ary,idxLeft, idxRight);
    }

    public static void swap0(int[] ary, int idxLeft, int idxRight) {
        int t = ary[idxLeft];
        ary[idxLeft] = ary[idxRight];
        ary[idxRight] = t;
    }

}
