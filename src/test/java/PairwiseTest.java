import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PairwiseTest {

    private static List<int[]> pairwiseVec;
    private static final String RESULT_FILE_PATH = "./src/test/results/pairwiseTestResult.txt";
    private static final String TEST_FILE_PATH = "./src/main/resources/pairWiseTest.txt";
    private static int keyExist;
    private static int keyMissing;
    private static int keyExistDefault;

    @BeforeAll
    static void setupAll() throws IOException {
        HelpFunctions.pairwiseGenerator();
        pairwiseVec = HelpFunctions.readFromFile(TEST_FILE_PATH);
        keyExist = 1;
        keyMissing = 100;
        keyExistDefault = 0;

        Files.writeString(Paths.get(RESULT_FILE_PATH), "Pairwise Test Results\n", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    void membershipPositive() throws IOException {
        Search s = new Search();
        int firstFailureIndex = -1;

        for (int i = 0; i < pairwiseVec.size(); i++) {
            int keyToCheck = (i == 0) ? keyExistDefault : keyExist;

            try {
                Assertions.assertTrue(s.membership(pairwiseVec.get(i), keyToCheck));
            } catch (AssertionError e) {
                firstFailureIndex = i;
                Files.writeString(
                        Paths.get(RESULT_FILE_PATH),
                        "Test case: " + i + " failed with key not found: " + keyToCheck + "\n",
                        StandardOpenOption.APPEND
                );
                break;
            }
        }

        String result = "Minimum test cases to detect failure: " + (firstFailureIndex == -1 ? "None" : firstFailureIndex) + "\n";
        Files.writeString(Paths.get(RESULT_FILE_PATH), result, StandardOpenOption.APPEND);
    }

    @Test
    void membershipNegative() throws IOException {
        Search s = new Search();
        int firstFailureIndex = -1;

        for (int i = 0; i < pairwiseVec.size(); i++) {
            try {
                Assertions.assertFalse(s.membership(pairwiseVec.get(i), keyMissing));
            } catch (AssertionError e) {
                firstFailureIndex = i;
                Files.writeString(
                        Paths.get(RESULT_FILE_PATH),
                        "Test case: " + i + " failed with key found: " + keyMissing + "\n",
                        StandardOpenOption.APPEND
                );
                break;
            }
        }

        String result = "Minimum test cases to detect failure: " + (firstFailureIndex == -1 ? "None" : firstFailureIndex) + "\n";
        Files.writeString(Paths.get(RESULT_FILE_PATH), result, StandardOpenOption.APPEND);
    }

    @AfterEach
    void tearDown() throws IOException {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
