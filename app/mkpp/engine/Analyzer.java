// Package declaration
package app.mkpp.engine;

// Import packages
import java.util.ArrayList;

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
  // // Source: https://en.wikipedia.org/wiki/List_of_Unicode_characters
  // // // Numerical Digits
  /**
   * Field containing Unicode ranges for numerical digits
   */
  protected int[] numericalDigitRange = new int[]{48,57};
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
  protected int[] extendedLatinUnicodeRange = new int[]{161,591};
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
  // // Fields related analyzing
    // Automatically run Analysis as soom as it is created.
  protected boolean analysisRun = false;
  // // // Related to user input
  /**
   * Field containing the raw, string version of the input from constructor.
   */
  protected String input;
  /**
   * Field containing the Unicode pointers of each character/digit of this.input.
   * Set via this.convertStringtoUnicodeList().
   */
  protected int[] unicodeArray;
  // // // Related to result
  /**
   * Field containing the judged unicode codes, in the form of enum
   */
  protected ArrayList<Alphabet> alphabetEnumArray = new ArrayList<Alphabet>();
  /**
   * Field containing number of instances of different scripts
   */
  private int[] numInstances = new int[]{0,0,0,0,0};
  /**
   * Field containing number of scripts NOT listed in the enum or numInstances
   */
  private int otherInstances = 0;

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
    switch (input) {
      case null:
        throw new EmptyInputException("input for Analyzer object CANNOT be null!");
      default:
        this.input = input.toString();
        if (this.input.length() == 0) {
          throw new EmptyInputException("Resulting input CANNOT be empty!");
        }
    }
  }

  /**
   * Method to start the actual analysis.
   */
  public void beginAnalysis() {
    // Set the field as true for this method being run.
    this.analysisRun = true;
    // Run conversion and judgements
    this.convertStringToUnicodeList();
    this.judgeUnicodeChar();
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
   * Private helper method to confirm if a value sits within an script/alphabet range.
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

  /**
   * Private helper method to judge and save the origin of the unicode.
   */
  private void judgeUnicodeChar() throws IllegalStateException {
    // Throw exception in the event that the beginAnalysis() method has not been run.
    if (!this.analysisRun) { throw new IllegalStateException("Analysis has NOT been called."); }
    // Iterate through this.unicodeArray
    for (int unicode : this.unicodeArray) {
      // Check if unicode is within the various ranges
      if (this.isWithinRange(unicode, this.numericalDigitRange)) {
        ++this.numInstances[0];
        this.alphabetEnumArray.add(Alphabet.DIGIT);
      } else if (this.isWithinRange(unicode, this.basicLatinCapitalUnicodeRange)
                 || this.isWithinRange(unicode, this.basicLatinLowrCseUnicodeRange)
      ) {
        ++this.numInstances[1];
        this.alphabetEnumArray.add(Alphabet.LATINBASIC);
      } else if (this.isWithinRange(unicode, this.extendedLatinUnicodeRange)) {
        ++this.numInstances[2];
        this.alphabetEnumArray.add(Alphabet.LATINEXTENDED);
      } else if (this.isWithinRange(unicode, this.basicGreekUnicodeRange)) {
        ++this.numInstances[3];
        this.alphabetEnumArray.add(Alphabet.GREEK);
      } else if (this.isWithinRange(unicode, this.basicCyrillicUnicodeRange)) {
        ++this.numInstances[4];
        this.alphabetEnumArray.add(Alphabet.CYRILLIC);
      } else {
        ++this.otherInstances;
        this.alphabetEnumArray.add(Alphabet.OTHER);
      }
    }
  }

  // // Getter Methods
  /**
   * Getter method to retrieve the input from constructor.
   */
  public String getInput() { return this.input; }

  /**
   * Getter method to retrieve raw Unicode.
   */
  public int[] getUnicode() { return this.unicodeArray; }

  /**
   * Getter method to retrieve statistics on the origins of letters in the input.
   * 
   * @param choice Enum option selected.
   * @return Decimal ranging from 0.0 to 1.0 representing the percentage of different origins.
   */
  public double getStatistics(Alphabet choice) {
    // Initilise variables
    double total = (double)this.input.length();

    // Set the case switches
    switch (choice) {
      case DIGIT: return ((double)this.numInstances[0])/total;
      case LATINBASIC: return ((double)this.numInstances[1])/total;
      case LATINEXTENDED: return ((double)this.numInstances[2])/total;
      case GREEK: return ((double)this.numInstances[3])/total;
      case CYRILLIC: return ((double)this.numInstances[4])/total;
      default: return ((double)this.otherInstances)/total;
    }
  }

  /**
   * Getter method to state whether or not all characters originate from the same alphabet.
   * 
   * @return True, if all characters are from the same alphabet; False otherwise.
   */
  public boolean originsAllSame() {
    // Initialise variables
    Alphabet benchmark = this.alphabetEnumArray.get(0);
    // Iterate through this.alphabetEnumArray(), returning false at the first difference.
    for (Alphabet check : this.alphabetEnumArray) {
      if (check.equals(benchmark)) { return false; }
    }
    // After entire iteration, return True
    return true;
  }
}
