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
    // Run super's constructor
    super(input);
    // Run URLAnalyzer's own filtering
    super.input = this.filterURL(input);
    if (!this.matchesDomainLength()) {
      throw new BadURLFormatException("URL Length Invalid!");
    }
    this.removePeriods();
  }

  /**
   * Private helper method toremove the periods
   */
  private void removePeriods() {
    super.input = super.input.replace(".", "");
  }
  /**
   * Private helper method to filter the URL.
   * 
   * @return The modified string, with 'https://', 'http://', and periods removed.
   */
  private String filterURL(String input) {
    String moddedInput = input;
    if (moddedInput.startsWith("http://")) {
      moddedInput = input.substring(6,input.length()-1);
    } else if (moddedInput.startsWith("https://")) {
      moddedInput = input.substring(7,input.length()-1);
    }

    return moddedInput;
  }

  /**
   * Private helper method to confirm if the input matches the proper domain length.
   * 
   * Specifically, the requirements are:
   *  - less than 253 characters in total (without period)
   *  - each label (section between .) less than 63 characters
   * 
   * https://www.nic.ad.jp/timeline/en/20th/appendix1.html
   * 
   * @return True, if the input matches the requirements to be a legitimate domain name;
   * False otherwise.
   */
  private boolean matchesDomainLength() {
    // Check that total length is greater than 255
    if (super.input.length() > 255) { return false; }
    // Iterate through split strings, checking their length and beginnings/endings
    for (String label : super.input.split(".")) {
      if (label.length() > 63
          || label.startsWith("-")
          || label.endsWith("-")
      ) {
        return false;
      }
    }

    // Return true if none of the previous conditions are met.
    return true;
  }
}