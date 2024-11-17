package app.mkpp.engine;

/**
 * Custom exception that extends IllegalArgumentException.
 * Created for better clarity when using URLAnalyzer.java.
 * 
 * @author Mikhail Ortiz-Lunyov
 * @extends IllegalArgumentException
 */
public class BadURLFormatException extends IllegalArgumentException {
  /**
   * Default constructor with no custom comment.
   */
  BadURLFormatException() { super(); }

  /**
   * Overridden constructor with custom error message.
   */
  BadURLFormatException(String errMessage) { super(errMessage); }
}
