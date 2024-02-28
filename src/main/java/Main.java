import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static boolean membership(int[] arr, int key){
        Sorting sort = new Sorting();
        Search search = new Search();
        sort.bubbleSort(arr);

        return search.BinarySearch(arr, key) > -1;
    }
    public static void randomGenerator(){
        final int MAX_SIZE = 100;
        final int MIN_SIZE = -100;
        Random rand = new Random();
        try {
            FileWriter fw = new FileWriter("./src/test/randomTest.txt");
            for (int j = 0; j < 4; j++){
                int N = rand.nextInt(1, 20);
                //fw.write(N + " ");
                int[] arr = new int[N];
                for (int i = 0; i < arr.length; i++){
                    arr[i] = rand.nextInt(MIN_SIZE, MAX_SIZE);
                    fw.write(arr[i] + " ");
                }
                int target = rand.nextInt(MIN_SIZE, MAX_SIZE);
                fw.write("\n"); // Add a newline after writing all elements of an array
            }
            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void pairwiseGenerator() {
        int[] arr = new int[20];
        try {
            FileWriter fw = new FileWriter("./src/test/pairWiseTest.txt");

            // 0-wise already done with default value 0
            for(int r : arr){
                fw.write(r + " ");
            }
            fw.write("\n===========================================================\n\n");

            // 1-wise
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 1;
                for (int i : arr){
                    fw.write(i + " ");

                }
                fw.write("\n");
                arr[j] = 0;
            }

            fw.write("\n===========================================================\n\n");
            // 2-wise


            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1;
                for (int k = i + 1; k < arr.length; k++) {
                    arr[k] = 2;
                    for (int p : arr){
                        fw.write(p + " ");
                    }
                    fw.write("\n");
                    arr[k] = 0;
                }
                fw.write("\n");
                arr[i] = 0;

            }
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int[] arr = {9, 3, 5, 1, 6, 10, 2, 2};
        boolean temp = membership(arr, 5);
        System.out.println(temp);
        randomGenerator();
        //pairwiseGenerator();
        pairwiseGenerator();
    }
}