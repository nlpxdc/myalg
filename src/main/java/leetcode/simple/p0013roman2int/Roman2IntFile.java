package leetcode.simple.p0013roman2int;

import java.util.HashMap;
import java.util.Map;

class Roman2IntApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int i = romanToInt("MMCCCXCIX");
    }

    public static int romanToInt(String s) {
        //预处理
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        //真实逻辑
        int ret = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            Integer cNum = map.get(c);
            ret += cNum;
        }

        if (charArray.length <=1) {
            return ret;
        }

        for (int i = 0,j = 1; i < charArray.length-1 && j < charArray.length; i++,j++) {
            char iChar = charArray[i];
            char jChar = charArray[j];
            if (iChar == 'I' && (jChar == 'V' || jChar == 'X')) {
//                ret -= 1;
//                ret -= 1;
                ret -= 2;
                i++;j++;
            } else if (iChar == 'X' && (jChar == 'L' || jChar == 'C')) {
                ret -= 20;
                i++;j++;
            } else if (iChar == 'C' && (jChar == 'D') || jChar == 'M') {
                ret -= 200;
            }
        }

        return ret;
    }

}
