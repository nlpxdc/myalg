package structure.storage.indexno.stochastic.hash;

import com.google.common.hash.Hashing;
import structure.storage.common.TListAdt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

//一块区域，散列摆放，记录个数 meta元信息， 中间隔开可null
//数学知识 散列技巧 概率论（随机过程）数论？ 数学证明  >数理统计 （实际验证）
//O(1) 参考SimRef的O(logn)
//和contiguous冲突，不合作 和动态simref合作组合
//一一映射，不讲区间
//在连续中，玩离散，解决冲突，散列冲突
//假设前提散列不冲突 完美hash算法 （用一个common的来模拟即可）
//测哈希：SMhasher 最全面，Hashcat 看纯速度。
//        测随机：TestU01 / PractRand / dieharder 三件套，统计学报告直接出。
//带解冲突的，必须要用概率来分析 时空复杂度
class MyHashAppV2 {
    public static void main(String[] args) {
        System.out.println("aa");

//        Object[] objects = new Object[100000];
//
//        Object o0 = new Object();
//        int i0 = o0.hashCode() % 100000;
//
//        Object o1 = new Object();
//        int i1 = o1.hashCode() % 100000;
//
//        Object o2 = new Object();
//        int i2 = o2.hashCode() % 100000;
//
//        Object o3 = new Object();
//        int i3 = o3.hashCode() % 100000;
//
////        myHashCode()
//        objects[i0] = o0;
//        objects[i1] = o1;
//        objects[i2] = o2;
//        objects[i3] = o3;

        String[] stringAry = new String[100000];

        String abc = new String("abc");
        String def = new String("def");
        String ghi = new String("ghi");

        stringAry[myHashCode("abc")%100000] = abc;
        stringAry[myHashCode("def")%100000] = def;
        stringAry[myHashCode("ghi")%100000] = ghi;

    }

    static int myHashCode(String str) {
        int h = 0;
        char[] charArray = str.toCharArray();
        if (charArray.length > 0) {
            for (int i = 0; i < charArray.length; i++) {
                h = 31 * h + charArray[i];
            }
        }
        return h;
    }

//    static int myHashCode(int[] val) {
//        int h = 0;
//        for (int i = 0; i < val.length; i++) {
//            h = 31 * h + val[i];
//        }
//        return h;
//    }

}

//class MyNode {
//
//}

class Node {
    int no;
    Integer val;

    Node(int no, Integer val) {
        this.no = no;
        this.val = val;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Node node = (Node) o;
//        return no == node.no && val.equals(node.val);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(no, val);
//    }
}

class MyHashListV2 implements TListAdt<Integer> {

    int size;
//    final int[] ary;
    final List<List<Node>> ary;
    static final int max = 100000;

    public MyHashListV2() {
        size = 0;
//        ary = new int[max];
        ary = new ArrayList<>(max);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Integer loadAtNo(int no) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        List<Node> nodeList = ary.get(noIdx);
        //这里可以树来降低搜索的时间复杂度，bst，avl或红黑树（工程实现）
        //O(n) 可能多维度独立元素？ O(m+n) O(m*N) O(logmN)
        for (Node node : nodeList) {
            if (node.no == no) {
                return node.val;
            }
        }
        return null;
    }

    @Override
    public int[] search(Integer val) {
        return new int[0];
    }

    @Override
    public int searchFirst(Integer val) {
        //O(n)
        for (int i = 0; i < size; i++) {
            int noIdx = MyHashUtil.noIdx(i, max);
            List<Node> nodeList = ary.get(noIdx);
            for (Node node : nodeList) {
                if (node.val.equals(val)) {
                    return node.no;
                }
            }
        }
        return -1;
    }

    @Override
    public int searchLast(Integer val) {
        //O(n)
        for (int i = size-1; i >= 0; i--) {
            int noIdx = MyHashUtil.noIdx(i, max);
            List<Node> nodeList = ary.get(noIdx);
            Collections.reverse(nodeList);
            for (Node node : nodeList) {
                if (node.val.equals(val)) {
                    return node.no;
                }
            }
        }
        return -1;
    }

    @Override
    public void add(Integer val) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(size, max);
        List<Node> nodes = ary.get(noIdx);
        nodes.add(new Node(size, val));
        size++;
    }

    @Override
    public void delAtNo(int no) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        List<Node> nodeList = ary.get(no);
        Node toDelNode = null;
        for (Node node : nodeList) {
            if (node.no == no) {
                toDelNode = node;
            }
        }
        if (toDelNode == null) {
            throw new RuntimeException();
        } else {
            nodeList.remove(toDelNode);
            size--;
        }
    }

    @Override
    public void updateAtNo(int no, Integer val) {
        //O(1)
        int noIdx = MyHashUtil.noIdx(no, max);
        List<Node> nodeList = ary.get(noIdx);
        for (Node node : nodeList) {
            if (node.no == no) {
                node.val = val;
            }
        }
        throw new RuntimeException();
    }
}

class MyHashUtilV2 {
    //内部可以自己写，自定义，也可以用任何三方的，jdk的等等，自由度很大，不限制
    //这里假设默认hash不会冲突去写
    //如果考虑有冲突，需要考虑解决冲突，开发地址法，或者链表法，这里就先不搞复杂了，重点关注散列地址本身这个策略
    static int hash(int no) {
        return Hashing.murmur3_32_fixed().hashInt(no).asInt();
    }

    static int noIdx(int no, int mod) {
        int noHash = hash(no);
        return noHash % mod;
    }

}


