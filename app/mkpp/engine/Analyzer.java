// Package decalration
package app.mkpp.engine;

/**
 * This class analyzes an input,
 * and attempts to derive the origin of all of its letters or digits.
 * 
 * @author Mikhail Ortiz-Lunyov (mportizlunyov)
 * @version 0.0.1
 */
public class Analyzer {
  // Set fields
  // // Unicode ranges of major alphabetic scripts (inclusive)
  // // // Numerical Digits
  /**
   * Field containing Unicode ranges for numerical digits
   */
  protected int[] numericalDigitRange = new int[]{30,39};
  // // // Latin
  /**s
   * Field containing Unicode ranges for basic, Capital Latin.
   */
  protected int[] basicLatinCapitalUnicodeRange = new int[]{65,90};
  /**
   * Field containing Unicode ranges for basic, Lowercase Latin.
   */
  protected int[] basicLatinLowrCseUnicodeRange = new int[]{97,122};
  /**
   * Field containing Unicode ranges for extended Latin
   */
  protected int[] extendedLatinUnicodeRange = new int[]{192,7935};
  // // // Phonetic
  // TODO
  // // // Greek/Coptic
  /**
   * Field containing Unicode ranges for basic Greek/Coptic
   */
  protected int[] basicGreekUnicodeRange = new int[]{880,1023};
  // // // Cyrillic
  /**
   * Field containing Unicode ranges for basic Cyrillic
   */
  protected int[] basicCyrillicUnicodeRange = new int[]{1024,1279};
  // // // Chinese (Simplified)
  // TODO
  // // // Hindi
  // TODO
  // // Fields related analyzing
  /**
   * Field containing the raw, string version of the input from constructor.
   */
  protected String input;
  /**
   * Field containing the Unicode pointers of each character/digit of this.input.
   * Set via this.convertStringtoUnicodeList().
   */
  protected int[] unicodeArray;

  // Begin Methods
  // // Constructor
  /**
   * Creates a new Analyzer object,
   * filtering out null and attempting to cast to String,
   * if it is not already that type.
   * 
   * @param input The input for the Analyzer constructor.
   * @throws EmptyInputException In the event of null or empty input 
   */
  public Analyzer(Object input) throws EmptyInputException {
    // TODO: Define if gratiously, or with exception
    switch (input) {
      case null:
        throw new EmptyInputException("input for Analyzer object CANNOT be null!");
      default:
        this.input = input.toString();
        if (this.input.length() == 0) {
          throw new EmptyInputException("Resulting input CANNOT be empty!");
        }
    }
    convertStringToUnicodeList();
  }

  // // Private Helpers
  // // // Private helper method for this.unicodeArray field.
  /**
   * Private helper method made to fill the this.unicodeArray field.
   */
  private void convertStringToUnicodeList() {
    // Initialise variables
    int index = 0;
    this.unicodeArray = new int[this.input.length()];
    // Iterate through split String, converting it to char and then int
    for (String stringChar : this.input.split("")) {
      this.unicodeArray[index] = (int)stringChar.charAt(0);
      ++index;
    }
  }

  /**
   * Private helper method to confirm if a value sis within an script/alphabet range.
   * 
   * @return True, if the int is within a value of a Unicode range; false otherwise.
   * @throws IllegalArgumentException If the unicodeRange parameter
   * does not match the proper format (internal developer exception).
   */
  private boolean isWithinRange(int potUnicode, int[] unicodeRange)
      throws IllegalArgumentException {
    // Check formatting of unicodeRange
    if (unicodeRange.length != 2
        || unicodeRange[0] > unicodeRange[1]
        || unicodeRange[1] < unicodeRange[0]
    ) {
      throw new IllegalArgumentException("unicodeRange parameter NOT properly formatted!");
    }
    // Check potUnicode in relation to filtered unicodeRange
    if (potUnicode >= unicodeRange[0] && potUnicode <= unicodeRange[1]) {
      return true;
    } else {
      return false;
    }
  }

  // // Getter Methods
  /**
   * Getter method to get the input from constructor.
   */
  protected String getInput() { return this.input; }

  /**
   * Getter method to retrieve raw Unicode.
   */
  protected int[] getUnicode() { return this.unicodeArray; }
}
