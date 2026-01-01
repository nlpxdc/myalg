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

}
