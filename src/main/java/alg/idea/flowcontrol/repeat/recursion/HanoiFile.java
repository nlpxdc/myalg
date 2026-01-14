package alg.idea.flowcontrol.repeat.recursion;

class HanoiApp {
    public static void main(String[] args) {
//        System.out.println("aa");
        move(3);
    }

    private static void move(int n) {
        move(n, 'A', 'B', 'C');
    }

    private static void move(int n, char nSrc, char nAux, char nDst) {
//        System.out.println(String.format("begin move n%d , src:%s aux:%s dst:%s", n, nSrc, nAux, nDst));
        if (n == 1) {
            System.out.println(String.format("moving disk%d , %s -> %s", n, nSrc, nDst));
//            System.out.println(String.format("end move n%d , src:%s aux:%s dst:%s", n, nSrc, nAux, nDst));
            return;
        }
        move(n-1, nSrc, nDst ,nAux);
        System.out.println(String.format("moving disk%d , %s -> %s", n, nSrc, nDst));
        move(n-1, nAux, nSrc ,nDst);
//        System.out.println(String.format("end move n%d , src:%s aux:%s dst:%s", n, nSrc, nAux, nDst));
    }

}
