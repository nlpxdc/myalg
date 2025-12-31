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

}
