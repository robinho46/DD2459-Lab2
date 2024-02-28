

public class Sorting {
    /**
     * @requires arr != null
     * @ensures
     * @return
     */
    public void bubbleSort(int[] arr){
        if (arr == null){
            throw new IllegalArgumentException("Array must not be null");
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
    }
    public void printArray(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
    }
}