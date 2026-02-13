package leetcode.difficulty.simple.p0035searchinsert;

class SearchInsertApp {
    public static void main(String[] args) {
        System.out.println("aa");
        SearchInsertApp app = new SearchInsertApp();
        int[] ary = {1,3,5,6};
        int i = app.searchInsert(ary, 2);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        if (target < nums[0]) {
            return 0;
        } else if (nums[nums.length-1] < target) {
            return nums.length;
        } else if (target == nums[0]) {
            return 0;
        } else if (target == nums[nums.length-1]) {
            return nums.length-1;
        } else {
            int leftIdx = 0, rightIdx=nums.length-1;
            for (
                    ; leftIdx < nums.length && rightIdx >= 0 && leftIdx<rightIdx;
            ) {
                //获取中间位置
                int midIdx = (leftIdx+rightIdx)/2;
                int midtarget = nums[midIdx];
                if (target < midtarget) {
                    if (rightIdx != midIdx) {
                        rightIdx = midIdx;
                        continue;
                    } else {
                        break;
                    }
                } else if (midtarget < target) {
                    if (leftIdx != midIdx) {
                        leftIdx = midIdx;
                        continue;
                    } else {
                        break;
                    }
                } else if (target == nums[midIdx]) {
                    leftIdx = rightIdx = midIdx;
                    break;
                } else {
                    //noway
                }
            }
            return rightIdx;
        }
    }
    
}
