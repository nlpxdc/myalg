package alg.repeat.recurrence.bfs.example;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

class SlidingWindowMaxApp {
    public static void main(String[] args) {
        System.out.println("aa");
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        final int retLen = nums.length-k+1;
        int[] ret = new int[retLen];

        for (int i = 0; i < retLen; i++) {
            //取出区间内的最大值
            int iMax = windowMax(nums, i, k);
            ret[i] = iMax;
        }

        return ret;
    }

    int windowMax(int[] nums, int beginIdx, int k) {
        int endIdx = beginIdx + k - 1;
        int max = nums[beginIdx];
        for (int i = beginIdx+1; i <= endIdx; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}

class SlidingWindowMaxApp2 {
    public static void main(String[] args) {
        SlidingWindowMaxApp2 app = new SlidingWindowMaxApp2();
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints1 = app.maxSlidingWindow(ints, 3);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        final int retLen = nums.length-k+1;
        int[] ret = new int[retLen];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        //初始化priorityQueue
        for (int i = 0; i < k; i++) {
            priorityQueue.add(nums[i]);
        }

        for (int i = 0; i < retLen; i++) {
            int max = priorityQueue.peek();
            ret[i] = max;
            int leftVal = nums[i];
            priorityQueue.remove(leftVal);
            if (i < retLen-1) {
                int rightVal = nums[k+i];
                priorityQueue.add(rightVal);
            }

        }

        return ret;
    }

    int windowMax(int[] nums, int beginIdx, int k) {
        int endIdx = beginIdx + k - 1;
        int max = nums[beginIdx];
        for (int i = beginIdx+1; i <= endIdx; i++) {
            max = Math.max(max, nums[i]);
        }
        return max;
    }

}

class SlidingWindowMaxApp3 {
    public static void main(String[] args) {
        SlidingWindowMaxApp3 app = new SlidingWindowMaxApp3();
        int[] ints = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints1 = app.maxSlidingWindow(ints, 3);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        final int retLen = nums.length-k+1;
        int[] ret = new int[retLen];

        ArrayDeque<Node> deque = new ArrayDeque<>();
        for (int idx = 0; idx < nums.length; idx++) {
            int val = nums[idx];
            while (!deque.isEmpty()) {
                Node dequeLastNode = deque.peekLast();
                if (dequeLastNode.val <= val) {
                    deque.pollLast();
                    continue;
                } else {
                    break;
                }
            }

            Node node = new Node(idx, val);
            deque.offerLast(node);

            int windowLeftIdx = idx-(k-1);
            Node dequeFirstNode = deque.peekFirst();
            if (dequeFirstNode.idx < windowLeftIdx) {
                deque.pollFirst();
            }

            if (idx>=(k-1)) {
                ret[idx-(k-1)] = deque.peekFirst().val;
            }
        }

        return ret;
    }

}

class Node {
    int idx;
    int val;

    Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }

}
