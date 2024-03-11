import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class RandomTest {
    public static List<int[]> randomList;

    public static String RESULT_FILE_PATH = "./src/testFiles/randomTestResult.txt";
    public static String TEST_FILE_PATH = "./src/testFiles/randomTest.txt";
    public static int passedTests;
    public static int keyExist;
    public static int keyMissing;
    public static int keyExistDefault;


    @BeforeAll
    static void setupAll() throws IOException {
        HelpFunctions.randomGenerator();
        randomList = HelpFunctions.readFromFile(TEST_FILE_PATH);
        keyMissing = 1000;
        keyExistDefault = 0;
        Files.writeString(Paths.get(RESULT_FILE_PATH), "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @BeforeEach
    void setup() {
        // This method runs before each test
        // Ideal for setting up test-specific conditions if necessary
    }

    @Test
    void randomIsPositive() throws IOException {
        Search s = new Search();
        int passedTests = 0; // Initialize passedTests for this test

        for (int i = 0; i < randomList.size(); i++) {
            int[] testCase = randomList.get(i);
            int keyToCheck = testCase[0]; // Directly use the first element as the key for this test case

            boolean result;
            try {
                result = s.membership(testCase, keyToCheck);
                Assertions.assertTrue(result);
                passedTests++;
            } catch (AssertionError e) {
                // Log failure without stopping execution
                String message = "Test case: " + i + " failed with key not found: " + keyToCheck + "\n";
                Files.writeString(Paths.get(RESULT_FILE_PATH), message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
            try {
                // Ensure the directory exists
                Files.createDirectories(Paths.get(RESULT_FILE_PATH).getParent());

                // Attempt to write to the file
                //Files.writeString(Paths.get(RESULT_FILE_PATH), "Your content here", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace(); // Log or print any exceptions
            }
        }

        // Build and write the summary
        StringBuilder sb = new StringBuilder();
        sb.append("Key found in: ").append(passedTests).append(" Test cases\n");
        sb.append("Default key = ").append("First element in each array").append("\n"); // This line might need adjustment based on actual test logic relevance

        Files.writeString(Paths.get(RESULT_FILE_PATH), sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Test
    void randomIsNegative() throws IOException {
        Search s = new Search();
        int passedTests = 0; // Reset passedTests for this test

        // Ensure the directory exists for the result file
        Files.createDirectories(Paths.get(RESULT_FILE_PATH).getParent());

        for (int i = 0; i < randomList.size(); i++) {
            int[] testCase = randomList.get(i);
            int keyToCheck = keyMissing; // Use the keyMissing for this negative test

            boolean result;
            try {
                result = s.membership(testCase, keyToCheck);
                Assertions.assertFalse(result);
                passedTests++;
            } catch (AssertionError e) {
                // Log failure without stopping execution
                String message = "Test case: " + i + " failed with key found: " + keyToCheck + "\n";
                Files.writeString(Paths.get(RESULT_FILE_PATH), message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                // Do not return; continue with the next test case
            }
        }

        // Build and write the summary
        StringBuilder sb = new StringBuilder();
        sb.append("Key missing in: ").append(passedTests).append(" Test cases\n");
        sb.append("Key = ").append(keyMissing).append("\n-----\n");

        Files.writeString(Paths.get(RESULT_FILE_PATH), sb.toString(), StandardOpenOption.APPEND);
    }

    @AfterEach
    void tearDown() {
        //ta bort pairwiseTest.txt
        //File file = new File(TEST_FILE_PATH);
        //file.delete();
    }
}