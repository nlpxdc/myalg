package sort;

class MiscApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    //基数排序，入参整型数组？ 不合适
    //数字化作字符串处理比较，其实不合适

    //能做，但也不合适，稍好些
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

//    //不合适 直接不用写了 因为按位处理，前面要补全，等价补全，补全以后等长按位处理会正确，否则就不对了
//    //补全后，进制0-9可以排，当作一个整体比较，不然不能比，这样弄就不合适了，没必要
//    static void radixSortMsd(int[] ary) {
//
//    }

}
