import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MembershipTest {

    public static List<int[]> pairwiseVec;
    public static int keyExist;
    public static int keyMissing;
    public static int keyExistDefault;
    public static String RESULT_FILE_PATH = "pairwiseTestResult.txt";;
    int passedTests;


    @BeforeAll
    static void setupAll() throws IOException {
        HelpFunctions.pairwiseGenerator();
        pairwiseVec = HelpFunctions.readFromFile("./src/testFiles/pairWiseTest.txt");
        keyExist = 1;
        keyMissing = 100;
        keyExistDefault = 0;
        Files.writeString(Paths.get(RESULT_FILE_PATH), "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    @BeforeEach
    void setup() {
        // This method runs before each test
        // Ideal for setting up test-specific conditions if necessary
    }

    @Test
    void membershipPositive() {
        Search s = new Search();
        for(int i = 0; i < pairwiseVec.size(); i++){
            int keyToCheck = (i == 0) ? keyExistDefault : keyExist;
            try{
                Assertions.assertTrue(s.membership(pairwiseVec.get(i), keyToCheck));
                passedTests++;
            }catch(AssertionError e){
                try{
                    Files.writeString(Paths.get(RESULT_FILE_PATH), "Test case: " + i + "failed with key not found: " + keyToCheck + "\n");
                    return;
                }catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Key found in: ").append(passedTests).append(" Test cases" + "\n");
        sb.append("Default key = ").append(keyExistDefault).append("\n");
        sb.append("Key = ").append(keyExist).append("\n");
        try{
            Files.writeString(Paths.get(RESULT_FILE_PATH), sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @Test
    void membershipNegative() {
        Search s = new Search();
        for(int i = 0; i < pairwiseVec.size(); i++){
            try{
                Assertions.assertFalse(s.membership(pairwiseVec.get(i), keyMissing));
                passedTests++;
            }catch(AssertionError e){
                try{
                    Files.writeString(Paths.get(RESULT_FILE_PATH), "Test case: " + i + " failed with key found: " + keyMissing + "\n");
                    return;
                }catch(IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Key missing in: ").append(passedTests).append(" Test cases " + "\n");
        sb.append("Key = ").append(keyMissing).append("\n").append("-----" + "\n");
        try{
            Files.writeString(Paths.get(RESULT_FILE_PATH), sb.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        //ta bort pairwiseTest.txt
        File file = new File("./src/testFiles/pairWiseTest.txt");
        file.delete();
    }

    @AfterAll
    static void tearDownAll() {

    }
}
