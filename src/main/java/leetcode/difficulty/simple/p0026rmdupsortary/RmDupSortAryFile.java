package leetcode.difficulty.simple.p0026rmdupsortary;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class RmDupSortAryFile {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        //初始化记录非重复数量
        int count = 0;

        //上一个值，用来比较
        int preVal=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //获取当前值
            int currVal = nums[i];

            if (preVal == currVal) {
                continue;
            } else if (preVal < currVal) {
//                if (count != i) {
//                    nums[count] = currVal;
//                }
//                count++;
                nums[count++] = currVal;
                preVal = currVal;
            }
        }
        //返回这个非重复数量
        return count;
    }


    public int countNonDuplicates(int[] nums) {
        //初始化记录非重复数量
        int count = 0;
        //初始化一个临时数组，用来存放非重复数字
        int[] tmpNums = new int[nums.length];

        //上一个值，用来比较
        int preVal=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //获取当前值
            int currVal = nums[i];
            //比较上一个值和当前值，如果相等那么丢弃，不记录，不自增，如果大于则count自增加1
            if (preVal < currVal) {
                tmpNums[count] = currVal;
                count++;
            } else {

            }
        }
        //返回这个非重复数量
        return count;
    }

}

//class ValNode {
//    final int val;
//    final int startIdx;
//    int sameCnt;
//    int endIdx;
//
//    ValNode(int val, int startIdx) {
//        this.val = val;
//        this.startIdx = startIdx;
//        this.sameCnt = 1;
//        this.endIdx = startIdx;
//    }
//}
