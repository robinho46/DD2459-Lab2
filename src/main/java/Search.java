public class Search {

    public int BinarySearch(int[] A, int key) {
        int l = 0;
        int r = A.length-1;
        while(l <= r) {
            int x = (l + r) / 2;
            if(A[x] < key) {
                l = x + 1;
            } else if(A[x] > key) {
                r = x - 1;
            } else {
                return x;
            }
        }
        return -1; // Key not found
    }

    public boolean membership(int[] vec, int key) {
        Sorting sort = new Sorting();
        sort.bubbleSort(vec);
        int result = BinarySearch(vec, key);
        return result != -1;
    }
}