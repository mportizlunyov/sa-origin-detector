package app.mkpp.engine;

public class TempTester {
  public static void main(String[] args) {
    Analyzer an = null;
    try {
      // Greek, Latin, Latin, Latin, LatinEx
      an = new Analyzer("ϿaaaḀ");
    }
    catch (EmptyInputException e) {
      System.out.println(e.getMessage());
    }
    System.out.println(an.getStatistics(Alphabet.LATINBASIC)); // 0.6
    System.out.println(an.getStatistics(Alphabet.LATINEXTENDED
    )); // 0.2
    System.out.println(an.getStatistics(Alphabet.GREEK)); // 0.2
    System.out.println(an.getStatistics(Alphabet.CYRILLIC
    )); // 0.0
    System.out.println(an.getStatistics(Alphabet.OTHER
    )); // 0.0
    
  }
}
