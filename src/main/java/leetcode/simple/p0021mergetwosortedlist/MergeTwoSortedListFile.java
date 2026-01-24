package leetcode.simple.p0021mergetwosortedlist;

import java.util.Arrays;
import java.util.List;

class MergeTwoSortedListApp {
    public static void main(String[] args) {
        MergeTwoSortedListApp app = new MergeTwoSortedListApp();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        ListNode minNode = dummyNode;


        for (ListNode upNode = list1, downNode = list2; upNode != null || downNode != null; minNode = minNode.next) {
//            //拿出list1的最小头节点
//            ListNode upNode = list1;
//            //拿出list2的最小头节点
//            ListNode downNode = list2;
            //比较两个列表当前最小的头节点，取出小的那个
            if (upNode != null && downNode != null) {
                int upVal = upNode.val;
                int downVal = downNode.val;
                if (upVal <= downVal) {
                    int minVal = upVal;
                    ListNode toAddNode = new ListNode(minVal);
                    minNode.next = toAddNode;
                    upNode = upNode.next;
                } else {
                    int minVal = downVal;
                    ListNode toAddNode = new ListNode(minVal);
                    minNode.next = toAddNode;
                    downNode = downNode.next;
                }
            } else if (upNode != null) {
                int upVal = upNode.val;
                int minVal = upVal;
                ListNode toAddNode = new ListNode(minVal);
                minNode.next = toAddNode;
                upNode = upNode.next;
            } else if (downNode != null) {
                int downVal = downNode.val;
                int minVal = downVal;
                ListNode toAddNode = new ListNode(minVal);
                minNode.next = toAddNode;
                downNode = downNode.next;
            } else {
                //结束
                return dummyNode.next;
            }
        }

        return dummyNode.next;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

