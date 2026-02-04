package operation.deterministic.op2seq.sort;

public class MyUtil {
    // 假设输入是自然数
    public static int digits(int n) {
        if (n == 0) {
            return 1;
        }
        int cnt = 0;
        for (int i = 0, d = n; i < Integer.MAX_VALUE && d>0; i++, d /= 10) {
            cnt++;
        }
        return cnt;
    }

    public static int max(int[] ary) {
        int max = ary[0];
        for (int i = 1; i < ary.length; i++) {
            if (ary[i] > max) {
                max = ary[i];
            }
        }
        return max;
    }
}
