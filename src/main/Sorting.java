import java.io.*;

public class Sorting {
    /**
     * @requires arr != null
     *
     * @return
     */
    public static int[] bubbleSort(int[] arr){
        if (arr == null){
            assert false : "Array must not be null";
        }
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - 1 - i; j++){
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args){
        int[] arr = {9, 3, 5, 1, 6, 10, 2, 2};
        for(int i : arr){
            System.out.print(i + ", ");
        }
        bubbleSort(arr);
        System.out.println("Sorted with bubbleSort");
        for(int i : arr){
            System.out.print(i + ", ");
        }
    }
}
