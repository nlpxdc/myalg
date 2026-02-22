package alg.repeat.recurrence.bfs.example;

import java.util.ArrayList;
import java.util.List;

class GiveChangeApp {

//    static int[] denominations = {100,50,20,10,5,2,1};
    static int[] denominations = {4,3,1};

    public static void main(String[] args) {
        int change = 6;
        int i = minCntGiveChange(change);
        System.out.println(i);
        List<Integer> list = detailGiveChange(change);
//        System.out.println(IntStream.of(ints).boxed().collect(Collectors.toList()));
        System.out.println(list);
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
