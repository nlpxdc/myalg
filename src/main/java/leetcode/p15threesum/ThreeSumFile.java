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
        Arrays.sort(nums);
        HashSet<String> set = new HashSet<>();
        List<List<Integer>> listList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (i != j && i != k && j != k) {
                        if (nums[i]+nums[j]+nums[k] == 0) {
                            String str = String.format("%d,%d,%d", nums[i], nums[j], nums[k]);
                            if (!set.contains(str)) {
                                listList.add(Arrays.asList(nums[i],nums[j],nums[k]));
                                set.add(str);
                            }
                        }
                    }
                }
            }
        }
        return listList;
    }

}
