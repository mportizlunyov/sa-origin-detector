package app.mkpp.engine;

public class TempTester {
  public static void main(String[] args) {
    try {
      Analyzer an = new Analyzer(new int[8]);
    }
    catch (EmptyInputException e) {
      System.out.println(e.getMessage());
    }
    // System.out.println("GOOD!");
  }
}
