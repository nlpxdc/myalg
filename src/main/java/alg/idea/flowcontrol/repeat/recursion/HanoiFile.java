package alg.idea.flowcontrol.repeat.recursion;

import java.util.*;

class HanoiApp {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(list);
//        System.out.println("aa");
        move(3);
        System.out.println("---");
        System.out.println("---");
        move2(3);
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

    private static void move2(int n) {
        Deque<Move> moveStack = new LinkedList<>();
        moveStack.push(new Move(n, 'A', 'B', 'C'));

        for ( ; !moveStack.isEmpty() ; ) {
            Move move = moveStack.pop();
            if (move.n == 1) {
                System.out.println(String.format("moving, %s -> %s", move.nSrc, move.nDst));
            } else {
                moveStack.push(new Move(move.n-1, move.nAux, move.nSrc ,move.nDst));
                moveStack.push(new Move(1, move.nSrc, move.nAux , move.nDst));
                moveStack.push(new Move(move.n-1, move.nSrc, move.nDst ,move.nAux));
            }
        }
    }

}

class Move {
    int n;
    char nSrc;
    char nAux;
    char nDst;

    Move(int n, char nSrc, char nAux, char nDst) {
        this.n = n;
        this.nSrc = nSrc;
        this.nAux = nAux;
        this.nDst = nDst;
    }

}
