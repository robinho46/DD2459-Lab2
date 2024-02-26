package src.main;
public class Main {
    public static int combine(int[] arr, int key){
        Sorting sort = new Sorting();
        Search search = new Search();

        sort.bubbleSort(arr);

        return search.BinarySearch(arr, key);
    }
    public static void main(String[] args){
        int[] arr = {9, 3, 5, 1, 6, 10, 2, 2};
        int x = combine(arr, 5);
    }
}