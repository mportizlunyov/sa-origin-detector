// package dev.experiments.texttest.d;

/**
 * This is a test class that exists to begin experimenting with letters.
 */
public class TextTest {
  // Fields
  // // Everything related to text input
  protected String inputtedString;
  protected String[] splitString;
  protected char[] charArray;
  protected int[] unicodePointerArray;
  // // Everything related to ranges of Unicode (inclusive)
  protected int[] basicLatinCapitalUnicodeRange = new int[]{65,90};
  protected int[] basicLatinLowerCsUnicodeRange = new int[]{97,122};


  /**
   * Private helper method to check if input is valid.
   * 
   * Can be overridden, and can be potentially expented to a seperate class if needed.
   */
  private boolean isValidInput(String input) { //TODO: See JavaDoc
    return input != null && !input.isBlank();
  }

  private void setFields(String input) {
    if (isValidInput(input)) {
      this.inputtedString = input;
      this.splitString = input.split("");
    }
  }

  private void convertSplitStringToChar() {
    this.charArray = new char[this.splitString.length];
    for (int i = 0 ; i < this.splitString.length ; ++i) {
      this.charArray[i] = this.splitString[i].charAt(0);
    }
  }
  private void convertCharArraytoUnicodePointers() {
    this.unicodePointerArray = new int[this.splitString.length];
    for (int i = 0 ; i < this.charArray.length ; ++i) {
      this.unicodePointerArray[i] = (int) this.charArray[i];
    }
  }

  public static void main(String[] args) {
    TextTest inst = new TextTest();
    inst.setFields("HEllo!");
    inst.convertSplitStringToChar();
    inst.convertCharArraytoUnicodePointers();
    for (int unicode : inst.unicodePointerArray) {
      System.out.println(unicode);
    }
  }
}
