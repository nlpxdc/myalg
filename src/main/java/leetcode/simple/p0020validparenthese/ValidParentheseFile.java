package leetcode.simple.p0020validparenthese;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ValidParentheseFile {
    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
//        char[] charArray = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
//        List<Character> leftCharList = Arrays.asList('(', '{', '[');

        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            //1. 获取栈顶元素
            Character peekChar = stack.peek();
            if (peekChar == null) {
//                if (!leftCharList.contains(currChar)) return false;
                if (currChar != '(' && currChar !='{' && currChar != '[') return false;
                stack.push(currChar);
            } else {
                boolean beMatch = beMatch(currChar, peekChar);
                if (beMatch) {
                    stack.pop();
                } else {
                    stack.push(currChar);
                }
            }
        }

        return stack.isEmpty();

    }

    private boolean beMatch(char currChar, char peekChar) {
        if (currChar == ')' && peekChar == '(') {
            return true;
        } else if (currChar == '}' && peekChar == '{') {
            return true;
        }  else if (currChar == ']' && peekChar == '[') {
            return true;
        }  else {
            return false;
        }
    }
}


