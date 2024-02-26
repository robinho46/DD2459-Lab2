package src.main;

import java.util.ArrayList;

public class Search {

    public int BinarySearch(ArrayList<Integer> A, int key) {
        int l = 0;
        int r = A.size() ;
        while(l <= r) {
            int x = (l + r) / 2;
            if(A.get(x) < key) {
                l = x + 1;
            } else if(A.get(x) > key) {
                r = x - 1;
            } else {
                return x;
            }
        }
        return -1; // Key not found
    }

}
