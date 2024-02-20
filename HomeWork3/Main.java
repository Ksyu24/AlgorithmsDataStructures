package HomeWork3;

import HomeWork1.HeapSort;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyTree tree=new MyTree();

//        tree.add(8);
//        tree.add(1);
//        tree.add(3);
//        tree.add(4);
//        tree.add(5);
//        tree.add(6);
//        tree.add(7);
        Random rnd=new Random();
        int count=7;
        for(int i=0; i<count; i++) { tree.add(rnd.nextInt(100));}
        tree.printTree();

    }
}
