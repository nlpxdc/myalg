package leetcode.difficulty.simple.p0001twosum;

import java.util.HashMap;

class TwoSumApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int delta = target - nums[i];
            if (map.get(delta) != null) {
                return new int[]{map.get(delta), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

//    public static int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if (i != j) {
//                    if (nums[i] + nums[j] == target) {
//                        return new int[]{i,j};
//                    }
//                }
//            }
//        }
//        return null;
//    }

//    public static int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//                if (i != j) {
//                    if (nums[i] + nums[j] == target) {
//                        return new int[]{i,j};
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
