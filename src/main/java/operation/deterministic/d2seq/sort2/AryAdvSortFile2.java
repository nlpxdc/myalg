package operation.deterministic.d2seq.sort2;

import java.util.Arrays;

class AryAdvSortApp {
    public static void main(String[] args) {
        int[] ary = {8, 4, 5, 3, 5};
        funcA(ary);
        System.out.println(Arrays.toString(ary));
    }

    public static void funcA(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            funcB(ary, ary.length-i);
            funcD(ary, 0, ary.length-1-i);
        }
    }

    public static void funcB(int[] ary, int size) {
        for (int i = funcF(ary.length-1); i >=0; i--) {
            funcE(ary, i, size);
        }
    }

    static void funcE(int[] ary, int tIdx, int size) {
        int cIdx = tIdx;
        for (int i = 0; i <= (funcH(size) - funcG(tIdx)); i++) {
            int lIdx = funcJ(cIdx);
            int rIdx = funcK(cIdx);
            boolean hl = lIdx < size;
            boolean hr = rIdx < size;
            if (!hr && !hl) {
                return;
            } else if (hr && !hl) {
                throw new RuntimeException("根据定义，不可能");
            } else if (!hr && hl) {
                int lv = funcL(ary, lIdx);
                int cv = funcL(ary, cIdx);
                if (cv < lv) {
                    funcD(ary, cIdx, lIdx);
                    cIdx = lIdx;
                    continue;
                }
            } else if (hr && hl) {
                int rv = funcL(ary, rIdx);
                int lv = funcL(ary, lIdx);
                int cv = funcL(ary, cIdx);
                if (rv > lv) {
                    if (cv < rv) {
                        funcD(ary, cIdx, rIdx);
                        cIdx = rIdx;
                        continue;
                    }
                } else {
                    if (cv < lv) {
                        funcD(ary, cIdx, lIdx);
                        cIdx = lIdx;
                        continue;
                    }
                }
            }
        }
    }

    static int funcL(int[] ary, int tIdx) {
        return ary[tIdx];
    }

    static void funcD(int[] ary, int il, int ir) {
        int t = ary[il];
        ary[il] = ary[ir];
        ary[ir] = t;
    }

    static int funcF(int tIdx) {
        return (tIdx-1)/2;
    }

    static int funcJ(int tIdx) {
        return 2*tIdx+1;
    }

    static int funcK(int tIdx) {
        return 2*tIdx+2;
    }

    static int funcH(int s) {
        return  ((int) (Math.log(s) / Math.log(2)));
    }

    static int funcG(int tIdx) {
        return  ((int) (Math.log(tIdx+1) / Math.log(2)));
    }

}

