package app.mkpp.frontend.terminal;

// Import packages
// // Import system packages
import java.util.Scanner;
// // Import backend engine
import app.mkpp.engine.*;

/**
 * An example terminal front-end that implements the engine
 * 
 * @author Mikhail Ortiz-Lunyov
 */
public class TerminalFrontend {
  public static void main(String[] args) {
    // Define initial variables
    boolean prompt = true;
    Analyzer textAnalyzer = null;
    URLAnalyzer urlAnalyzer = null;
    Scanner scanner = new Scanner(System.in);

    //
    while (prompt) {
      System.out.println("Enter a string of text: ");
      System.out.print(" : ");
      String input = scanner.nextLine();

      try {
        textAnalyzer = new Analyzer(input);
        textAnalyzer.beginAnalysis();
        scanner.close();
        prompt = false;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Please give different input.\n");
      }
    }

    // Return results
    System.out.println("All letters from the same alphabet : " + textAnalyzer.originsAllSame());
    System.out.println("Percent digits : " + 100*textAnalyzer.getStatistics(Alphabet.DIGIT));
    System.out.println("Percent basic Latin : " + 100*textAnalyzer.getStatistics(Alphabet.LATINBASIC));
    System.out.println("Percent extended Latin : " + 100*textAnalyzer.getStatistics(Alphabet.LATINEXTENDED));
    System.out.println("Percent greek : " + 100*textAnalyzer.getStatistics(Alphabet.GREEK));
    System.out.println("Percent cyrillic : " + 100*textAnalyzer.getStatistics(Alphabet.CYRILLIC));
    System.out.println("Percent OTHER : " + 100*textAnalyzer.getStatistics(Alphabet.OTHER));
  }
}
