package leetcode.difficulty.simple.p0028idxoffirststr;

class IdxOfFirstStrFile {
    public static void main(String[] args) {

    }

    public int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            boolean match = match(haystack, i, needle);
            if (match) {
                return i;
            }
        }
        return -1;
    }

    private static boolean match(String haystack, int startIdx, String needle) {
        if (startIdx+needle.length() > haystack.length()) {
            return false;
        }
        for (int i = 0, leftIdx=startIdx, rightIdx=startIdx+needle.length()-1; i < needle.length() && leftIdx <= rightIdx; i++, leftIdx++, rightIdx--) {
            //获取左边第一个字符比较
            char leftChar1 = haystack.charAt(leftIdx);
            char leftChar2 = needle.charAt(i);
            if (leftChar1 != leftChar2) return false;
            //获取右边第一个字符比较
            char rightChar1 = haystack.charAt(rightIdx);
            char rightChar2 = needle.charAt(needle.length() - i - 1);
            if (rightChar1 != rightChar2) return false;
        }
        return true;
    }
}
