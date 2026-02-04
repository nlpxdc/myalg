package operation.deterministic.op2seq.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//频次 统计 排序 也是一种分治 用的计数排序
//借助额外辅助数组aux 空间复杂度O(C)
//假设范围为英文小写字母
//这是一维的排序，只有一位，没有多位，是二维，多位排序的基础和前置，二维多位可能基于这个，不一定一定要基于这个
//字符比较，相当于数字比较，对应于ascii码的数字，基本类型
//还有十六进制数等，不转成十进制，编译器转？最终二进制，指令比较。另一种思路，数字转成字符串，按照基数比较，选择这个好
//如果字符串代表一个n进制数，那么排序可以按照转成十进制数后的比较交换排序，然后再转回字符串，但这样性能差吧
class AryCharSortApp {
    public static void main(String[] args) {
//        char[] ary = {'d','f','k','e','v','j','e','p','j'};
//        countingSortV2(ary);
        String[] hex17 = {
                "3b",       // 3b₁₇
                "a07",      // a07₁₇
                "1g",       // 1g₁₇  ← 与下一条相同
                "d",        // d₁₇
                "f5c2",     // f5c2₁₇
                "9e",       // 9e₁₇
                "0",        // 0₁₇
                "1g",       // 1g₁₇  ← 重复值
                "10a"       // 10a₁₇
        };
        radixSortLsd(hex17);
        List<Integer> dec10List = Arrays.stream(hex17).map(t -> Integer.parseInt(t, 17)).collect(Collectors.toList());
        System.out.println(Arrays.toString(hex17));
        System.out.println(dec10List);
    }

    //1b 计数排序 counting sort 含偏移量
    static void countingSortV2(char[] ary) {
        int offset = 'a';

        //建桶，26个英文小写字母
        int[] cntBuckets = new int[26];

        for (int idx = 0; idx < ary.length; idx++) {
            char c = ary[idx];
            int cIdx = c;
            cntBuckets[cIdx-offset]++;
        }

        int globalIdx = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int cCnt = cntBuckets[c-offset];
            for (int i = 0; i < cCnt; i++) {
                ary[globalIdx++] = c;
            }
        }
    }


    //数字化作字符串处理比较，是十六进制的一种思路
    //尤其是比如十七进制这种原生不支持的进制数
    //基数排序 按位处理 倒着来，最小位开始 radix sort LSD
    //空间复杂度O(C+N)
    //这里用String充当十七进制数的表示，因为int不能直接支持，所以用字符串替代
    //所以这里的输入本质上还是理解成一个数字，等价int
    static void radixSortLsd(String[] ary) {
        if (ary == null || ary.length <=1) {
            return;
        }
        //确定最大位数
        int maxDigits = ary[0].length();
        for (int i = 1; i < ary.length; i++) {
            if (ary[i].length() > maxDigits) {
                maxDigits = ary[i].length();
            }
        }

        //3. 创建一个临时数组，用于存储排序后的结果
        //辅助数组 O(n)的来源
        String[] tmpAry = new String[ary.length];

        //按位循环迭代，从低位开始
        for (int i = 1; i <= maxDigits; i++) {
            //2. 创建17个桶用来计数
            //计数数组
            int[] buckets = new int[17];
            //3. 计数
            for (int j = 0; j < ary.length; j++) {
                String s = ary[j];
                //这里就在按位操作比较了
                char c = 0;
                if (s.length() < i) {
                    //说明前面已经没数了，直接char c按照0算
                    c = '0';
                } else {
                    c = s.charAt(s.length() - i);
                }
                //根据char计算桶的索引位置idx
                int bucketIdxByChar = getBucketIdxByChar(c);
                buckets[bucketIdxByChar]++;
            }

            //4. 每一位数的右端位置（），计算idx的位置区间
            //这里直接就变成起始索引位置了，实际是结束索引位置，所以后续可以倒着来方便，lsd结束索引位置，倒着来
            //msd 起始索引位置？正着来
            for (int j = 1; j < 17; j++) {
                buckets[j] = buckets[j-1] + buckets[j];
            }

            //根据计算的位置区间确定元素，倒着来
            for (int j = ary.length-1; j >= 0; j--) {
                //找到哪个桶的
                String s = ary[j];
                char c = 0;
                if (s.length() < i) {
                    //说明前面已经没数了，直接char c按照0算
                    c = '0';
                } else {
                    c = s.charAt(s.length() - i);
                }
                int bucketIdxByChar = getBucketIdxByChar(c);
                //确定这个桶的下标位置
                int idx = --buckets[bucketIdxByChar];
                tmpAry[idx] = s;
            }

            //此轮位数排序好，赋值给临时辅助数组，然后进入下一次循环
            //找到两个数组要赋值的起始位置，然后给定复制长度
            System.arraycopy(tmpAry, 0, ary, 0, ary.length);
        }
    }

    //假设只是纯数字0-9，以及abcdefg的十七进制
    static int getBucketIdxByChar(char c) {
        int idx = -1;
        switch(c){
            case '0': idx = 0; break;
            case '1': idx = 1; break;
            case '2': idx = 2; break;
            case '3': idx = 3; break;
            case '4': idx = 4; break;
            case '5': idx = 5; break;
            case '6': idx = 6; break;
            case '7': idx = 7; break;
            case '8': idx = 8; break;
            case '9': idx = 9; break;
            case 'a': idx = 10; break;
            case 'b': idx = 11; break;
            case 'c': idx = 12; break;
            case 'd': idx = 13; break;
            case 'e': idx = 14; break;
            case 'f': idx = 15; break;
            case 'g': idx = 16; break;
            default :
                throw new RuntimeException("不是十七进制数");
        }
        return idx;
    }

    //老的方法
//    static void radixSortLsd(int[] ary) {
//        //1. 找到数组中的最大值，以确定最大位数
//        int max = MyUtil.max(ary);
//        int maxDigits = MyUtil.digits(max);
//
//        //3. 创建一个临时数组，用于存储排序后的结果
//        //辅助数组 O(n)的来源
//        int[] tmpAry = new int[ary.length];
//
//        for (int i = 1, exp = 1; i <= maxDigits; i++, exp *= 10) {
//            //2. 创建10个桶用来计数
//            //计数数组
//            int[] buckets = new int[10];
//            //3. 计数
//            for (int j = 0; j < ary.length; j++) {
//                int val = ary[j];
//                //余数 这里就在按位操作比较了 其实没必要
//                int remainder = (val / exp) % 10;
//                buckets[remainder]++;
//            }
//
//            //4. 每一位数的右端位置（）
//            for (int j = 1; j <= 9; j++) {
//                buckets[j] = buckets[j-1] + buckets[j];
//            }
//
//            //根据位置确定元素
//            for (int j = ary.length-1; j >= 0; j--) {
//                //找到哪个桶的
//                int val = ary[j];
//                int remainder = (val/exp)%10;
//                //确定这个桶的下标位置
//                int idx = --buckets[remainder];
//                tmpAry[idx] = val;
//            }
//
//            System.arraycopy(tmpAry, 0, ary, 0, ary.length);
//        }
//    }

}
