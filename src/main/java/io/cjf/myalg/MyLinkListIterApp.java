package io.cjf.myalg;

public class MyLinkListIterApp {
    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.addEl(new MyLinkNode(7));
        myLinkList.addEl(new MyLinkNode(4));
        myLinkList.addEl(new MyLinkNode(18));

        myLinkList.traverse();
    }
}
