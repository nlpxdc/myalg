package alg.repeat.recurrence.dfs.example;

import java.util.HashSet;

class NoRepeatCharLongestSubstringApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxCnt = 1;
        for (int startIdx = 0; startIdx < s.length(); startIdx++) {
            for (int endIdx = startIdx; endIdx < s.length(); endIdx++) {
                boolean hasSame = hasSameChar(s, startIdx, endIdx);
                if (!hasSame) {
                    int cnt = endIdx - startIdx + 1;
                    if (cnt > maxCnt) {
                        maxCnt = cnt;
                    }
                }
            }
        }
        return maxCnt;
    }

    boolean hasSameChar(String s, int startIdx, int endIdx) {
        HashSet<Character> set = new HashSet<>();
        for (int idx = startIdx; idx <= endIdx; idx++) {
            char c = s.charAt(idx);
            if (set.contains(c)) {
                return true;
            } else {
                set.add(c);
            }
        }
        return false;
    }

}

class NoRepeatCharLongestSubstringApp2 {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int maxCnt = 1;
        HashSet<Character> set = new HashSet<>();
        for (int leftIdx = 0, rightIdx = 0;
             leftIdx < s.length() && rightIdx < s.length() && leftIdx <= rightIdx;
             rightIdx++) {
            char rightChar = s.charAt(rightIdx);
            while (set.contains(rightChar)) {
                set.remove(s.charAt(leftIdx));
                leftIdx++;
            }
            set.add(rightChar);
            maxCnt = Math.max(maxCnt, rightIdx-leftIdx+1);
        }

        return maxCnt;
    }

    boolean hasSameChar(String s, int startIdx, int endIdx) {
        HashSet<Character> set = new HashSet<>();
        for (int idx = startIdx; idx <= endIdx; idx++) {
            char c = s.charAt(idx);
            if (set.contains(c)) {
                return true;
            } else {
                set.add(c);
            }
        }
        return false;
    }

}
