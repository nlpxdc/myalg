package leetcode.difficulty.simple.p0027rmel;

class RmElFile {
    public static void main(String[] args) {
        System.out.println("aa");
        RmElFile rmElFile = new RmElFile();
//        int[] ary = {};
//        rmElFile.removeElement()
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;
        for (int leftIdx = 0, rightIdx = nums.length-1; leftIdx <= rightIdx; leftIdx++ ) {
            //取出当前左值，进行比较基准比较
            int leftVal = nums[leftIdx];
            if (leftVal == val) {
                //如果值相等，那么抛弃不需要，需要进行覆盖，取出一个非基准值放入
                //尝试从右边循环找出一个非基准值数字
                for ( ; leftIdx < rightIdx; rightIdx-- ) {
                    int rightVal = nums[rightIdx];
                    if (rightVal == val) {
                        //如果右值等于基准值，那么移动右指针，跳过继续寻找
                        continue;
                    } else {
                        count++;
                        //如果右值不等于基准值，放入左边
                        nums[leftIdx] = rightVal;
                        rightIdx--;
                        //移动右指针，并跳出内部循环
                        break;
                    }
                }
            } else {
                //如果不相等，那么移动指针后，保留val，继续
                count++;
                continue;
            }
        }
        return count;
    }

}
