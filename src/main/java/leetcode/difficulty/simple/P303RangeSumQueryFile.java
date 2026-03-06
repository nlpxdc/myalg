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

class NumArray2 {

    int[] prefixSums;

    public NumArray2(int[] nums) {
        prefixSums = new int[nums.length];
        prefixSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] += prefixSums[i-1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left==0) return prefixSums[right];
        return prefixSums[right] - prefixSums[left - 1];
    }
}
