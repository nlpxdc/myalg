package alg.repeat.recurrence.bfs.example;

class SlidingWindowMaxApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        final int retLen = nums.length-k+1;
        int[] ret = new int[retLen];

        for (int i = 0; i < retLen; i++) {
            //取出区间内的最大值
            int iMax = windowMax(nums, i, k);
            ret[i] = iMax;
        }

        return ret;
    }

    int windowMax(int[] nums, int beginIdx, int k) {
        int endIdx = beginIdx + k - 1;
        int max = nums[beginIdx];
        for (int i = beginIdx+1; i <= endIdx; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}
