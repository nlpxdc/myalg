package alg.paradigm.ofnlimit.math.nested.dp;

import java.util.ArrayList;
import java.util.List;

class GiveChangeApp {

    static int[] denominations = {4,3,1};

    public static void main(String[] args) {
        System.out.println("aa");
    }

    static List<Integer> detailGiveChange(int change) {
        ArrayList<Integer> retAry = new ArrayList<>();
        //根据面值大小，从大到小取出来看下做选择
        int remain = change;
        for (int i = 0; i < change; i++) {
            int maxVal = getMaxVal(remain);
            if (maxVal > 0) {
                retAry.add(maxVal);
                remain -= maxVal;
            } else {
                break;
            }
        }

        return retAry;
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
