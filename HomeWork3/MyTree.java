package HomeWork3;

import java.util.Stack;

public class MyTree {
    private Node root;

    public boolean add(int value){
        if(root!=null) {
            boolean result=addNode(root, value);
            root=rebalance(root);
            root.color=Color.BLACK;
            return result;
        }
        else {
            root=new Node();
            root.color=Color.BLACK;
            root.value=value;
            return true;
        }
    }

    private boolean addNode(Node node, int value){
        if (node.value==value) {
            return false;
        }
        else {
            if(node.value>value) {
                if(node.leftChild!=null){
                    boolean result=addNode(node.leftChild,value);
                    node.leftChild=rebalance(node.leftChild);
                    return result;
                }
                else {
                    node.leftChild=new Node();
                    node.leftChild.color=Color.RED;
                    node.leftChild.value=value;
                    return true;
                }
            }
            else {
                if(node.rightChild!=null){
                    boolean result=addNode(node.rightChild,value);
                    node.rightChild=rebalance(node.rightChild);
                    return result;
                }
                else {
                    node.rightChild=new Node();
                    node.rightChild.color=Color.RED;
                    node.rightChild.value=value;
                    return true;
                }

            }
        }
    }


    //ребалансировка
    private Node rebalance(Node node){
        Node result=node;
        boolean needRebalance;
        do{
            needRebalance=false;
            if(result.rightChild!=null && result.rightChild.color==Color.RED && (result.leftChild==null  || result.leftChild.color==Color.BLACK))
            {
                needRebalance=true;
                result=rightSwap(result);
            }
            if(result.leftChild!=null && result.leftChild.color==Color.RED && (result.leftChild.leftChild!=null && result.leftChild.leftChild.color==Color.RED))
            {
                needRebalance=true;
                result=leftSwap(result);
            }
            if(result.leftChild!=null && result.leftChild.color==Color.RED && (result.rightChild!=null && result.rightChild.color==Color.RED))
            {
                needRebalance=true;
                colorSwap(result);
            }
        }
        while(needRebalance);
        return result;
    }

    //правостороний поворот
    private Node rightSwap(Node node){
        Node rightChild=node.rightChild;
        Node betweenChild=rightChild.leftChild;
        rightChild.leftChild=node;
        node.rightChild=betweenChild;
        rightChild.color=node.color;
        node.color=Color.RED;
        return rightChild;
    }
    //левостороний поворот
    private Node leftSwap(Node node){
        Node leftChild=node.leftChild;
        Node betweenChild=leftChild.rightChild;
        leftChild.rightChild=node;
        node.leftChild=betweenChild;
        leftChild.color=node.color;
        node.color=Color.RED;
        return leftChild;
    }
    //смена цвета
    private void colorSwap(Node node){
        node.rightChild.color=Color.BLACK;
        node.leftChild.color=Color.BLACK;
        node.color=Color.RED;
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";


    public void printTree() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    if(temp.getColor()==Color.RED) {System.out.print(ANSI_RED + temp.getValue() + ANSI_RESET);}
                    else if (temp.getColor()==Color.BLACK) {System.out.print(ANSI_BLACK + temp.getValue() + ANSI_RESET);}
                    else {System.out.print(temp.getValue());}
                    localStack.push(temp.getLeftChild());
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }
        System.out.println(separator);
    }

    private class Node{
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        public int getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }
        public Color getColor() {
            return color;
        }
    }

    private enum Color{
        RED, BLACK
    }
}
