package leetcode.difficulty.medium;

class P1109CorpFlightBookFile {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] retAry = new int[n];

        //遍历循环每条预定记录 first last seat
        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            int firstIdx = booking[0]-1;
            int lastIdx = booking[1]-1;
            int seat = booking[2];

            for (int j = firstIdx; j <= lastIdx; j++) {
                retAry[j] += seat;
            }

        }

        return retAry;
    }
}

class P1109CorpFlightBookFile2 {
    public static void main(String[] args) {
        P1109CorpFlightBookFile2 app = new P1109CorpFlightBookFile2();
        app.corpFlightBookings(new int[][]{{1,2,10},{2,2,15}}, 2);
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] retAry = new int[n];

        int[] diff = new int[n+2];
        //diff[i] = retAry[i] - retAry[i-1]; diff[0] = retAry[0]
        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            int firstNo = booking[0];
            int lastNo = booking[1];
            int seat = booking[2];

            diff[firstNo] += seat;
            diff[lastNo+1] -= seat;
        }

        retAry[0] = diff[1];
        for (int idx = 1; idx < n; idx++) {
            int no = idx+1;
            retAry[idx] += retAry[idx-1] + diff[no];
        }

        return retAry;
    }
}
