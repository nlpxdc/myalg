package search;

import java.util.Arrays;
import java.util.Collections;

//查找第一个或者最后一个，查找多个的话就是字符串匹配，正则表达式，状态机了，有一整套完善的
//普通场景，主键，值都不同，索引失效不！
class MySearchApp {
    public static void main(String[] args) {
        System.out.println("aa");
        String str = "abc";
        char[] charArray = str.toCharArray();
        int i = str.indexOf('b');
        int i1 = Arrays.binarySearch(charArray, 'b');
        int i2 = Collections.binarySearch(null, null);
    }
}
