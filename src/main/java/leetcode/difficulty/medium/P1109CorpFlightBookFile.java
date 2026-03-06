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
