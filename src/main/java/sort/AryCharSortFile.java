package sort;

//频次 统计 排序 也是一种分治 用的计数排序
//借助额外辅助数组aux 空间复杂度O(C)
//假设范围为英文小写字母
//这是一维的排序，只有一位，没有多位，是二维，多位排序的基础和前置，二维多位可能基于这个，不一定一定要基于这个
//字符比较，相当于数字比较，对应于ascii码的数字，基本类型
//还有十六进制数等，不转成十进制，编译器转？最终二进制，指令比较。另一种思路，数字转成字符串，按照基数比较
class AryCharSortApp {
    public static void main(String[] args) {
        char[] ary = {'d','f','k','e','v','j','e','p','j'};
        countingSortV2(ary);
        System.out.println(ary);
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

    //基数排序，入参整型数组？ 不合适
    //数字化作字符串处理比较，其实不合适

    //能做，但也不合适，稍好些，还是比较交换排序算法好
    //基数排序 排整型 按位处理 倒着来，最小位开始 radix sort LSD 整数排序
    //空间复杂度O(C+N)
    static void radixSortLsd(int[] ary) {
        //1. 找到数组中的最大值，以确定最大位数
        int max = MyUtil.max(ary);
        int maxDigits = MyUtil.digits(max);

        //3. 创建一个临时数组，用于存储排序后的结果
        //辅助数组 O(n)的来源
        int[] tmpAry = new int[ary.length];

        for (int i = 1, exp = 1; i <= maxDigits; i++, exp *= 10) {
            //2. 创建10个桶用来计数
            //计数数组
            int[] buckets = new int[10];
            //3. 计数
            for (int j = 0; j < ary.length; j++) {
                int val = ary[j];
                //余数 这里就在按位操作比较了 其实没必要
                int remainder = (val / exp) % 10;
                buckets[remainder]++;
            }

            //4. 每一位数的右端位置（）
            for (int j = 1; j <= 9; j++) {
                buckets[j] = buckets[j-1] + buckets[j];
            }

            //根据位置确定元素
            for (int j = ary.length-1; j >= 0; j--) {
                //找到哪个桶的
                int val = ary[j];
                int remainder = (val/exp)%10;
                //确定这个桶的下标位置
                int idx = --buckets[remainder];
                tmpAry[idx] = val;
            }

            System.arraycopy(tmpAry, 0, ary, 0, ary.length);
        }
    }

}
