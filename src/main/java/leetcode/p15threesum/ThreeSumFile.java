package leetcode.p15threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class ThreeSumApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        HashSet<Long> set = new HashSet<>();
        List<List<Integer>> listList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (i != j && i != k && j != k) {
                        if (nums[i]+nums[j]+nums[k] == 0) {
                            long key = key(nums[i], nums[j], nums[k]);
                            if (!set.contains(key)) {
                                listList.add(Arrays.asList(nums[i],nums[j],nums[k]));
                                set.add(key);
                            }
                        }
                    }
                }
            }
        }
        return listList;
    }

    private static long key(int x, int y, int z) {
        if (x > y) { int t = x; x = y; y = t;}
        if (x > z) { int t = x; x = z; z = t;}
        if (y > z) { int t = y; y = z; z = t;}
        return (((long) x) << 42) | (((long) y) << 21) | (((long) z)) ;
    }

}
