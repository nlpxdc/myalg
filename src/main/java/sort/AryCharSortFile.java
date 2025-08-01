package sort;

//频次 统计 排序 也是一种分治 用的计数排序
//借助额外辅助数组aux 空间复杂度O(C)
//假设范围为英文小写字母
//这是一维的排序，只有一位，没有多位，是二维，多位排序的基础和前置，二维多位基于这个
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

}
