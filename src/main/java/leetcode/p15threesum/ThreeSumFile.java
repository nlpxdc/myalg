package leetcode.p15threesum;

import java.util.*;

class ThreeSumApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int[] nums = {-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> lists = threeSum(nums);
    }

    public static List<List<Integer>> threeSum(int[] nums) {


        List<List<Integer>> listList = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int delta = -nums[i];
            int[] ints = twoSum(nums, delta);
            if (ints != null && i != ints[0] && i != ints[1]) {
//                long key = key(nums[i], nums[ints[0]], nums[ints[1]]);
                int x = nums[i], y = nums[ints[0]], z = nums[ints[1]];
                if (x > y) { int t = x; x = y; y = t;}
                if (x > z) { int t = x; x = z; z = t;}
                if (y > z) { int t = y; y = z; z = t;}
                String str = String.format("%d,%d,%d", x, y, z);
                if (!set.contains(str)) {
                    listList.add(Arrays.asList(nums[i], nums[ints[0]], nums[ints[1]]));
                    set.add(str);
                }
            }
        }
        return listList;
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

//    public static List<List<Integer>> threeSum(int[] nums) {
//
//        HashSet<Long> set = new HashSet<>();
//        List<List<Integer>> listList = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                for (int k = j+1; k < nums.length; k++) {
//                    if (i != j && i != k && j != k) {
//                        if (nums[i]+nums[j]+nums[k] == 0) {
//                            long key = key(nums[i], nums[j], nums[k]);
//                            if (!set.contains(key)) {
//                                listList.add(Arrays.asList(nums[i],nums[j],nums[k]));
//                                set.add(key);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return listList;
//    }

    private static long key(int x, int y, int z) {
        if (x > y) { int t = x; x = y; y = t;}
        if (x > z) { int t = x; x = z; z = t;}
        if (y > z) { int t = y; y = z; z = t;}
        return (((long) x) << 42) | (((long) y) << 21) | (((long) z)) ;
    }

}
