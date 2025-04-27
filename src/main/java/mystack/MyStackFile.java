package mystack;

import common2.AryNode;
import common2.DLinkNode;
import common2.LinkNode;
import common2.Node;

class MyStackApp {
    public static void main(String[] args) {
        System.out.println("aaa");
    }
}



interface MyStack<T extends Node> {
    void push(T el);

    T pop();

    T peek();

    void traverse();
}

class MyAryStack implements MyStack<AryNode> {


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
