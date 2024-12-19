import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTest {
    public static List<int[]> randomList;
    private static final String RESULT_FILE_PATH = "./src/test/results/randomTestResult.txt";
    private static final String TEST_FILE_PATH = "./src/main/resources/randomTest.txt";
    private static final int TOTAL_RUNS = 10;
    private static int keyMissing;
    private static int keyExistDefault;

    @BeforeAll
    static void setupAll() throws IOException {
        HelpFunctions.randomGenerator();
        randomList = HelpFunctions.readFromFile(TEST_FILE_PATH);
        keyMissing = 1000;
        keyExistDefault = 0;

        Files.writeString(Paths.get(RESULT_FILE_PATH), "Random Test Results\n", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    void randomIsPositive() throws IOException {
        log("Starting Positive Test\n");

        Search s = new Search();
        int passedTests = 0;

        for (int i = 0; i < randomList.size(); i++) {
            int[] testCase = randomList.get(i);
            int keyToCheck = testCase[0];

            try {
                boolean result = s.membership(testCase, keyToCheck);
                Assertions.assertTrue(result);
                passedTests++;
            } catch (AssertionError e) {
                log("Test case " + i + " failed with key not found: " + keyToCheck + "\n");
            }
        }

        log("Positive Test Summary: Key found in " + passedTests + " test cases\n\n");
    }

    @Test
    void randomIsNegative() throws IOException {
        log("Starting Negative Test\n");

        Search s = new Search();
        int passedTests = 0;

        for (int i = 0; i < randomList.size(); i++) {
            int[] testCase = randomList.get(i);

            try {
                boolean result = s.membership(testCase, keyMissing);
                Assertions.assertFalse(result);
                passedTests++;
            } catch (AssertionError e) {
                log("Test case " + i + " failed with key found: " + keyMissing + "\n");
            }
        }

        log("Negative Test Summary: Key missing in " + passedTests + " test cases\n\n");
    }

    @Test
    void randomIsPositiveMultipleRuns() throws IOException {
        log("Starting Positive Test with Multiple Runs\n");

        Search s = new Search();
        int totalFailures = 0;
        int failedRuns = 0;

        for (int run = 0; run < TOTAL_RUNS; run++) {
            int firstFailureIndex = -1;
            int passedTests = 0;

            for (int i = 0; i < randomList.size(); i++) {
                int[] testCase = randomList.get(i);
                int keyToCheck = testCase[0];

                try {
                    boolean result = s.membership(testCase, keyToCheck);
                    Assertions.assertTrue(result);
                    passedTests++;
                } catch (AssertionError e) {
                    if (firstFailureIndex == -1) {
                        firstFailureIndex = i;
                        failedRuns++;
                    }
                    log("Run " + run + ": Test case " + i + " failed with key not found: " + keyToCheck + "\n");
                }
            }

            if (firstFailureIndex != -1) {
                totalFailures += firstFailureIndex;
            }

            log("Run " + run + " Summary: Passed tests = " + passedTests + ", First failure at index = " + firstFailureIndex + "\n");
        }

        double averageFailureIndex = (failedRuns > 0) ? (double) totalFailures / failedRuns : -1;
        log("Average index of first failure across " + TOTAL_RUNS + " runs: " + averageFailureIndex + "\n\n");
    }

    private void log(String message) throws IOException {
        Files.writeString(Paths.get(RESULT_FILE_PATH), message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
