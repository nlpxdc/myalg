package leetcode.p01twosum;

class TwoSumApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (i != j) {
                    if (nums[i] + nums[j] == target) {
                        return new int[]{i,j};
                    }
                }
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
