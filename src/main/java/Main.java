import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        HelpFunctions generator = new HelpFunctions();
        generator.randomGenerator();
        generator.pairwiseGenerator();
        //System.out.print("hello world");
        List<int[]> tester = generator.readFromFile("./src/testFiles/pairWiseTest.txt");
        /*for (int i = 0; i < tester.size(); i++) {
            for (int j = 0; j < tester.get(i).length; j++) {
                System.out.print(tester.get(i)[j] + " ");
            }
            System.out.println(); // Print a newline after each array
        }*/
        //pairwiseGenerator();
    }
}