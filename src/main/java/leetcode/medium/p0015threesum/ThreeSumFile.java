package leetcode.medium.p0015threesum;

import java.util.*;

class ThreeSumApp {
    public static void main(String[] args) {
        System.out.println("aa");
        int[] nums = {-100,-70,-60,110,120,130,160};
        ThreeSumApp threeSumApp = new ThreeSumApp();
        List<List<Integer>> lists = threeSumApp.threeSum(nums);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> listList = new ArrayList<>();

        HashSet<Long> set = new HashSet<>();

        int zeroCnt = 0;
        HashMap<Integer, Integer> positiveMap = new HashMap<>();
        HashMap<Integer, Integer> negativeMap = new HashMap<>();

        for (int num : nums) {
            if (num == 0) {
                if (zeroCnt < 3) {
                    zeroCnt++;
                }
            } else if (num > 0) {
                Integer positiveNumCnt = positiveMap.getOrDefault(num, 0);
                if (positiveNumCnt < 2) {
                    positiveMap.put(num, positiveNumCnt+1);
                }
            } else if (num < 0) {
                Integer negativeNumCnt = negativeMap.getOrDefault(num, 0);
                if (negativeNumCnt < 2) {
                    negativeMap.put(num, negativeNumCnt+1);
                }
            }
        }

        //1. 000
        if (zeroCnt ==3) {
            listList.add(Arrays.asList(0,0,0));
        }

        //2. 2-1-1
        for (Integer positiveNum : positiveMap.keySet()) {
//            if (positiveNum % 2 == 0)
            if ((positiveNum & 1) == 0) {
//                int shouldNegativeNum = -positiveNum /2;
                int shouldNegativeNum = (-positiveNum) >>1;
                Integer shouldNegativeNumCnt = negativeMap.get(shouldNegativeNum);
                if (shouldNegativeNumCnt != null && shouldNegativeNumCnt == 2) {
                    listList.add(Arrays.asList(positiveNum, shouldNegativeNum, shouldNegativeNum));
                }
            }
        }

        //3. -211
        for (Integer negativeNum : negativeMap.keySet()) {
            if ((negativeNum & 1) == 0) {
//                 int shouldPositiveNum = -negativeNum /2;
                int shouldPositiveNum = (-negativeNum) >>1;
                Integer shouldPositiveNumCnt = positiveMap.get(shouldPositiveNum);
                if (shouldPositiveNumCnt != null && shouldPositiveNumCnt == 2) {
                    listList.add(Arrays.asList(negativeNum, shouldPositiveNum, shouldPositiveNum));
                }
            }
        }

        //4. -101
        if (zeroCnt >=1 ) {
            for (Integer negativeNum : negativeMap.keySet()) {
                if (positiveMap.containsKey(-negativeNum)) {
                    listList.add(Arrays.asList(0, negativeNum, -negativeNum));
                }
            }
        }

        //5. -1-3 4
        for (Integer positiveNum : positiveMap.keySet()) {
            for (Integer negativeNum : negativeMap.keySet()) {
                int delta = -positiveNum-negativeNum;
                long key = key(negativeNum, delta);
                if (delta != negativeNum && negativeMap.containsKey(delta) && !set.contains(key)) {
                    listList.add(Arrays.asList(positiveNum, negativeNum, delta));
                    set.add(key);
                }
            }
        }

        //6. -413
        for (Integer negativeNum : negativeMap.keySet()) {
            for (Integer positiveNum : positiveMap.keySet()) {
                int delta = -negativeNum-positiveNum;
                long key = key(positiveNum, delta);
                if (delta != positiveNum && positiveMap.containsKey(delta) && !set.contains(key)) {
                    listList.add(Arrays.asList(negativeNum, positiveNum, delta));
                    set.add(key);
                }
            }
        }

        return listList;
    }

    private long key(int x, int y) {
        if (x > y) {int t = x; x = y; y = t;}
//        return ((long) x << 32) | ((long) y) ;
        return ((long) x & 0xFFFFFFFFL) << 32 | (y & 0xFFFFFFFFL);
    }

//    private long key(int x, int y, int z) {
//        if (x > y) { int t = x; x = y; y = t;}
//        if (x > z) { int t = x; x = z; z = t;}
//        if (y > z) { int t = y; y = z; z = t;}
//        return (((long) x) << 42) | (((long) y) << 21) | (((long) z)) ;
//    }

}
