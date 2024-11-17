package app.mkpp.engine;

/**
 * This class analyzes an input in the form of a URL,
 * and attempts to derive the origin of all of its letters or digits.
 * 
 * @author Mikhail Ortiz-Lunyov (mportizlunyov)
 * @version 0.0.1
 */
public class URLAnalyzer extends Analyzer {

  /**
   * Creates a new URLAnalyzer object,
   * filtering out null and attempting to cast to String,
   * if it is not already that type.
   * 
   * It also attempts to check that the input is in the form of a domain name,
   * following the standard conventions along with it.
   * 
   * @param input The input for the Analyzer constructor.
   * @throws EmptyInputException In the event of null or empty input 
   */
  public URLAnalyzer(String input) {
    super(input);
    // TODO: New Exception?
    if (!this.matchesDomainLength()) {
      throw new IllegalArgumentException("NOT REAL URL!");
    }
  }

  /**
   * Private helper method to confirm if the input matches the proper domain length.
   * 
   * Specifically, the requirements are:
   *  - less than 253 characters in total (without period)
   *  - each 
   * 
   * @return True, if the input matches the requirements to be a legitimate domain name;
   * False otherwise.
   */
  private boolean matchesDomainLength() {
    return super.input.length() < 255;
  }
}