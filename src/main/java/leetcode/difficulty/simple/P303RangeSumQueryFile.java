package leetcode.difficulty.simple;

class P303RangeSumQueryApp {
    public static void main(String[] args) {

    }
}

class NumArray {

    int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
