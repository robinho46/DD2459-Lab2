import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelpFunctions {
    public static void randomGenerator() {
        final int MAX_SIZE = 100;
        final int MIN_SIZE = -100;
        Random rand = new Random();
        try {
            FileWriter fw = new FileWriter("./src/testFiles/randomTest.txt");
            for (int j = 0; j < 4; j++) {
                int N = rand.nextInt(1, 20);
                //fw.write(N + " ");
                int[] arr = new int[N];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rand.nextInt(MIN_SIZE, MAX_SIZE);
                    fw.write(arr[i] + " ");
                }
                int target = rand.nextInt(MIN_SIZE, MAX_SIZE);
                fw.write("\n"); // Add a newline after writing all elements of an array
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void pairwiseGenerator() {
        int[] arr = new int[20];
        try {
            FileWriter fw = new FileWriter("./src/testFiles/pairWiseTest.txt");

            // 0-wise already done with default value 0
            for (int r : arr) {
                fw.write(r + " ");
            }
            fw.write("\n");

            // 1-wise
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 1;
                for (int i : arr) {
                    fw.write(i + " ");

                }
                fw.write("\n");
                arr[j] = 0;
            }

            //fw.write("\n===========================================================\n\n");
            // 2-wise


            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1;
                for (int k = i + 1; k < arr.length; k++) {
                    arr[k] = 2;
                    for (int p : arr) {
                        fw.write(p + " ");
                    }
                    fw.write("\n");
                    arr[k] = 0;
                }
                //fw.write("\n");
                arr[i] = 0;

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<int[]> readFromFile(String path) {
        List<int[]> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numberStrings = line.trim().split("\\s+");
                int[] numbers = new int[numberStrings.length];
                for (int i = 0; i < numberStrings.length; i++) {
                    try {
                        numbers[i] = Integer.parseInt(numberStrings[i]);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing integer on line: " + (lines.size() + 1) + ", Value: '" + numberStrings[i] + "'. Skipping this value.");
                        // Optionally, you might want to set a default value or skip this line
                        // For now, let's simply continue to the next iteration
                        continue;
                    }
                }
                lines.add(numbers);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file.");
            e.printStackTrace();
        }
        return lines;
    }


}
