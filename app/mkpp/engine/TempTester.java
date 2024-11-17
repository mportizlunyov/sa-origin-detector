package app.mkpp.engine;

public class TempTester {
  public static void main(String[] args) {
    URLAnalyzer an = null;
    try {
      // Greek, Latin, Latin, Latin, LatinEx
      an = new URLAnalyzer("ϿaaaΤ");
      an.beginAnalysis();
    }
    catch (EmptyInputException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(an.getStatistics(Alphabet.LATINBASIC)); // 0.6
    System.out.println(an.getStatistics(Alphabet.LATINEXTENDED
    )); // 0.0
    System.out.println(an.getStatistics(Alphabet.GREEK)); // 0.4
    System.out.println(an.getStatistics(Alphabet.CYRILLIC
    )); // 0.0
    System.out.println(an.getStatistics(Alphabet.OTHER
    )); // 0.0
  }
}
