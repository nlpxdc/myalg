package structure.logic.linear.variant.mystack2;

class ArrStackApp {
    public static void main(String[] args) {
        AryStack aryStack = new AryStack();
        aryStack.push(new Node(1));
        aryStack.push(new Node(3));
        aryStack.push(new Node(5));
        aryStack.push(new Node(7));
        aryStack.traverse();

        Node pop = aryStack.pop();
        Node pop1 = aryStack.pop();

        Node top = aryStack.peek();

        aryStack.traverse();
    }
}

class Node {
    int val;
    Node(int val) {
        this.val = val;
    }
}

class AryStack {
    Node[] nodeAry;
    int size;

    AryStack() {
        nodeAry = new Node[1000];
        size = 0;
    }

    void push(Node node) {
        nodeAry[size++] = node;
    }

    Node pop() {
        Node ret = nodeAry[--size];
        return  ret;
    }

    Node peek() {
        Node ret = nodeAry[size-1];
        return ret;
    }

    void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodeAry[i].val+",");
        }
        System.out.println();
    }
}


