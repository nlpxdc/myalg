package leetcode.simple.p0013roman2int;

import java.util.HashMap;
import java.util.Map;

class Roman2IntApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int i = romanToInt("MCMXCIV");
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
        for (int i = charArray.length-1, preNum=0; i >=0; i--) {
            char c = charArray[i];
            Integer cNum = map.get(c);
            if (cNum < preNum) {
                ret -= cNum;
            } else {
                ret += cNum;
            }
            preNum=cNum;
        }

//        for (int i = charArray.length-1; i >= 0; i--) {
//            char c = charArray[i];
//            if (c == 'V' || c =='X') {
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'I') {
//                    ret -= 2;
//                }
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'I') {
//                    ret -= 2;
//                }
//            } else if (c == 'L' || c == 'C') {
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'X') {
//                    ret -= 20;
//                }
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'X') {
//                    ret -= 20;
//                }
//            } else if (c == 'D' || c == 'M') {
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'C') {
//                    ret -= 200;
//                }
//                i--;
//                if (i <0) {break;}
//                if (charArray[i] == 'C') {
//                    ret -= 200;
//                }
//            }
//        }

//        for (int i = 0,j = 1; i < charArray.length-1 && j < charArray.length; i++,j++) {
//            char iChar = charArray[i];
//            char jChar = charArray[j];
//            if (iChar == 'I' && (jChar == 'V' || jChar == 'X')) {
////                ret -= 1;
////                ret -= 1;
//                ret -= 2;
//                i++;j++;
//            } else if (iChar == 'X' && (jChar == 'L' || jChar == 'C')) {
//                ret -= 20;
//                i++;j++;
//            } else if (iChar == 'C' && (jChar == 'D') || jChar == 'M') {
//                ret -= 200;
//            }
//        }

        return ret;
    }

}
