package alg.paradigm.ofnlimit.math.nested.greedy;

class GiveChangeApp {

    static int[] denominations = {100,50,20,10,5,2,1};

    public static void main(String[] args) {
        int i = minCntGiveChange(7);
        System.out.println(i);
    }

    static int minCntGiveChange(int change) {
        int minCnt = 0;
        //根据面值大小，从大到小取出来看下做选择
        int remain = change;
        for (int i = 0; i < change; i++) {
            int maxVal = getMaxVal(remain);
            if (maxVal > 0) {
                minCnt++;
                remain -= maxVal;
            } else {
                break;
            }
        }

        return minCnt;
    }

    static int getMaxVal(int remain) {
        if (remain<=0) {
            return 0;
        }
        for (int i = 0; i < denominations.length; i++) {
            int denomination = denominations[i];
            if (denomination <= remain) {
                return denomination;
            }
        }
        return 1;
    }

}
