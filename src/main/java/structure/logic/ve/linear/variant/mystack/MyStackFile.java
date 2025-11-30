package structure.logic.ve.linear.variant.mystack;

import structure.common2.AryNode;
import structure.common2.DLinkNode;
import structure.common2.LinkNode;
import structure.common2.Node;

//也能用于多线程，不只是队列，栈顶一把锁即可，队列队头队尾需要两把锁提高并发度，也可以一把锁
class MyStackApp {
    public static void main(String[] args) {
        System.out.println("aaa");
        MyStackApp myStackApp = new MyStackApp();
        MyStack<AryNode> myStack = null;
        myStackApp.<AryNode>fund3(myStack);
        myStackApp.fund3(myStack);
        AryNode aryNode = myStackApp.func4(myStack);
        MyStack<Node> myStack2 = null;
        Node aryNode2 = myStackApp.<Node>func4(myStack2);
    }

    private void func0 (MyStack<?> myStack) {

    }

    private void func1(MyStack<? extends LinkNode> myStack) {

    }

    private void func2(MyStack<? super DLinkNode> myStack) {

    }

    private <T extends Node> void fund3(MyStack<? extends T> myStack) {

    }

    private <T> T func4(MyStack<? super T> myStack) {
        return null;
    }

    private <T extends AryNode> T func5(MyStack<? extends T> myStack) {
        return null;
    }
}



interface MyStack<T extends Node> {
    void push(T el);

    T pop();

    T peek();

    void traverse();
}

class MyAryStack implements MyStack<AryNode> {

    AryNode[] aryNodes = new AryNode[10000];
    int size = 0;


    @Override
    public void push(AryNode el) {

    }

    @Override
    public AryNode pop() {
        return null;
    }

    @Override
    public AryNode peek() {
        return null;
    }

    @Override
    public void traverse() {

    }
}

class MyLinkStack implements MyStack<LinkNode> {


    @Override
    public void push(LinkNode el) {

    }

    @Override
    public LinkNode pop() {
        return null;
    }

    @Override
    public LinkNode peek() {
        return null;
    }

    @Override
    public void traverse() {

    }
}

class MyDLinkStack implements MyStack<DLinkNode> {


    @Override
    public void push(DLinkNode el) {

    }

    @Override
    public DLinkNode pop() {
        return null;
    }

    @Override
    public DLinkNode peek() {
        return null;
    }

    @Override
    public void traverse() {

    }
}

