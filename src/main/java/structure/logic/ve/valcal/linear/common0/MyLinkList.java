package structure.logic.ve.valcal.linear.common0;

class MyLinkList {
    MyLinkNode head;
    int size;

    public MyLinkList() {
        head = null;
        size = 0;
    }

    public MyLinkNode getLastNode() {
        MyLinkNode last = head;
        for (int i = 0; i < size && last.next != null; i++, last = last.next) {

        }
        return last;
    }

    public void addEl(MyLinkNode el) {
        //在链表末尾节点指向此节点
        MyLinkNode lastNode = getLastNode();
        if (lastNode == null) {
            head = el;
        } else {
            lastNode.next = el;
        }
        size++;
    }

    public void traverse() {
        MyLinkNode currentNode = head;
        for (int i = 0; i < size && currentNode != null; i++, currentNode = currentNode.next) {
            System.out.print(currentNode.val+",");
        }
    }
}
