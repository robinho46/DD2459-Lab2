import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelpFunctions {

    private static final String RANDOM_TEST_FILE = "src/main/resources/randomTest.txt";
    private static final String PAIRWISE_TEST_FILE = "src/main/resources/pairWiseTest.txt";

    /**
     * Generates random arrays with elements between -100 and 100.
     * Saves test cases in randomTest.txt.
     */
    public static void randomGenerator() {
        final int MAX_SIZE = 100;
        final int MIN_SIZE = -100;
        Random rand = new Random();

        try (FileWriter fw = new FileWriter(RANDOM_TEST_FILE)) {
            for (int j = 0; j < 10; j++) {
                int N = rand.nextInt(1, 20);
                int[] arr = new int[N];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rand.nextInt(MIN_SIZE, MAX_SIZE);
                    fw.write(arr[i] + " ");
                }
                fw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates pairwise arrays where specific patterns are introduced (0-wise, 1-wise, and 2-wise variations).
     * Saves test cases in pairWiseTest.txt.
     */
    public static void pairwiseGenerator() {
        int[] arr = new int[20];

        try (FileWriter fw = new FileWriter(PAIRWISE_TEST_FILE)) {
            // 0-wise: All zeros
            for (int r : arr) {
                fw.write(r + " ");
            }
            fw.write("\n");

            // 1-wise: One '1' at a time
            for (int j = 0; j < arr.length; j++) {
                arr[j] = 1;
                for (int i : arr) {
                    fw.write(i + " ");
                }
                fw.write("\n");
                arr[j] = 0; // Reset to 0
            }

            // 2-wise: Two positions altered
            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1;
                for (int k = i + 1; k < arr.length; k++) {
                    arr[k] = 2;
                    for (int p : arr) {
                        fw.write(p + " ");
                    }
                    fw.write("\n");
                    arr[k] = 0; // Reset position
                }
                arr[i] = 0; // Reset position
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads test cases from a file and returns them as a list of integer arrays.
     * @param path Path to the file.
     * @return List of integer arrays representing the test cases.
     */
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