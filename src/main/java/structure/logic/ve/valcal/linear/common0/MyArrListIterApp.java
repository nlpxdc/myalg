package structure.logic.ve.valcal.linear.common0;

public class MyArrListIterApp {
    public static void main(String[] args) {
        MyArrList myArrList = new MyArrList();
        myArrList.addEl(new MyArrNode(5));
        myArrList.addEl(new MyArrNode(3));
        myArrList.addEl(new MyArrNode(28));

        myArrList.traverse();
    }
}
