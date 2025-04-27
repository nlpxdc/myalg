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

    void pop();

    void peek();

    void traverse();
}

class MyAryStack implements MyStack<AryNode> {


    @Override
    public void push(AryNode el) {

    }

    @Override
    public void pop() {

    }

    @Override
    public void peek() {

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
    public void pop() {

    }

    @Override
    public void peek() {

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
    public void pop() {

    }

    @Override
    public void peek() {

    }

    @Override
    public void traverse() {

    }
}
