package on;

//无额外计算，临时存储需求，不借助外部记录来判断
class FibonacciApp {
    public static void main(String[] args) {

    }

    //O(2n) dfs递归
    int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n >= 2) return fibo(n-1) + fibo(n-2);
        return -1;
    }

    //O(n) dp 自顶向下
    int fibo3(int n) {
        //加结果缓存
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n >= 2) return fibo(n-1) + fibo(n-2);
        return -1;
    }

    //O(n) dp 自底向上 全结果缓存
    long fibo4(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < n; i++) {
            dp[n] = dp[n-1]+dp[n-2];
        }
        return dp[n];
    }

    //O(n) dp 自底向上
    long fibo5(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n >= 2) {
            long prev2Tab = 0, prev1Tab = 1;
            for (int i = 2; i < n; i++) {
                long t = prev1Tab;
                prev1Tab += prev2Tab;
                prev2Tab = t;
            }
            return prev2Tab + prev1Tab;
        }

        return -1;
    }

//    //无贪心，不借助贪心，不借助辅助结果存储
//    int fibo4() {
//        return -1;
//    }

    //O(1) 数学公式
    int fibo2(int n) {
        //通项公式 Binet formula
        return -1;
    }



}


