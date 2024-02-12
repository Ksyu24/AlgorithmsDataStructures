package HomeWork1;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr=new int[15];
        Random rnd=new Random();

        for (int i=0; i<arr.length; i++ ){
            arr[i]=rnd.nextInt(100);
        }
        System.out.print(Arrays.toString(arr));
        System.out.println();
        HeapSort.sort(arr);
        System.out.print(Arrays.toString(arr));

    }
}