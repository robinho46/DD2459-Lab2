package src.main;

import javax.lang.model.element.Name;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
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
                fw.write(N + " ");
                int[] arr = new int[N];
                for (int i = 0; i < arr.length; i++){
                    arr[i] = rand.nextInt(MIN_SIZE, MAX_SIZE);
                    fw.write(arr[i] + " ");
                }
                int target = rand.nextInt(MIN_SIZE, MAX_SIZE);
                fw.write(" " + target + "\n"); // Add a newline after writing all elements of an array
            }
            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void pairwiseGenerator(){
        // Positive, Negative, Mixed, Zero (arr and key)
        int[] positiveValues = new int[20];
        int[] negativeValues = new int[20];
        int[] mixedValues = new int[20];
        int[] zeros = new int[20];
        int key = 0;
        int MAX_SIZE = 20;
        int MIN_SIZE = -20;

        for (int i = 0; i < MAX_SIZE; i++){
            positiveValues[i] = i;
            for (int j = 0; j < 4; j++){

            }
        }
        for (int i = 0; i < 20; i++){
            negativeValues[i] = -i;
        }

        Random rand = new Random();
        for (int i = 0; i < 20; i++){
            mixedValues[i] = rand.nextInt(-10, 10);
        }
        for (int i = 0; i < 20; i++){
            zeros[i] = 0;
        }
        /*for (int i : zeros){
            System.out.print(i + " ");
        }*/
    }
    public static void main(String[] args){
        int[] arr = {9, 3, 5, 1, 6, 10, 2, 2};
        boolean temp = membership(arr, 5);
        System.out.println(temp);
        //randomGenerator();
        //pairwiseGenerator();
        pairwiseGenerator();
    }
}