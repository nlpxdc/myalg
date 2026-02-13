package leetcode.difficulty.simple.p0014longprefix;

class LongPrefixFile {
    public static void main(String[] args) {

    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; ; i++) {
            Character sameChar = getSameStrAryAtIdx(strs, i);
            if (sameChar != null) {
                strBuilder.append(sameChar);
            } else {
                return strBuilder.toString();
            }
        }
    }

    private static Character getSameStrAryAtIdx(String[] strs, int idx) {
        if (strs.length==0) {
            return null;
        } else if (strs.length == 1) {
            if (idx < strs[0].length()) {
                return strs[0].charAt(idx);
            } else {
                return null;
            }
        } else {
            for (int i = 1; i < strs.length; i++) {
                String preStr = strs[i-1];
                String postStr = strs[i];
                char preChar;
                if (idx < preStr.length()) {
                    preChar = preStr.charAt(idx);
                } else {
                    return null;
                }
                char postChar;
                if (idx < postStr.length()) {
                    postChar = postStr.charAt(idx);
                } else {
                    return null;
                }
                if (preChar != postChar) {
                    return null;
                }
            }
            return strs[0].charAt(idx);
        }
    }

    private static boolean beSameStrAryAtIdx(String[] strs, int idx) {
        for (int i = 0; i < strs.length-1; i++) {
            String preStr = strs[i];
            String postStr = strs[i + 1];
            char preChar;
            if (idx < preStr.length()) {
                preChar = preStr.charAt(idx);
            } else {
                return false;
            }
            char postChar;
            if (idx < postStr.length()) {
                postChar = postStr.charAt(idx);
            } else {
                return false;
            }
            if (preChar != postChar) {
                return false;
            }
        }
        return true;
    }


}
