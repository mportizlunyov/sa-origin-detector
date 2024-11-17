package app.mkpp.engine;

/**
 * Custom exception that extends IllegalArgumentException.
 * Created for better clarity.
 * 
 * @author Mikhail Ortiz-Lunyov
 * @extends IllegalArgumentException
 */
public class EmptyInputException extends IllegalArgumentException {
  /**
   * Default constructor with no custom comment.
   */
  EmptyInputException() { super(); }

  /**
   * Overridden constructor with custom error message.
   */
  EmptyInputException(String errMessage) { super(errMessage); }
}
