/**
 * Testing class to experiment with custom exceptions
 */
public class ExceptionTest {
  private void exceptionUser(String input) throws EmptyInputException {
    if (input == null || input.isBlank()) {
      throw new EmptyInputException();
    }
  }

  public static void main(String[] args) {
    ExceptionTest tester = new ExceptionTest();
    tester.exceptionUser(null);
  }

}

/**
 * Custom Exception that extends IllegalArgumentException.
 * Created for better clarity.
 * 
 * @extends IllegalArgumentException
 */
class EmptyInputException extends IllegalArgumentException {
  /**
   * Default field with no custom comment.
   */
  EmptyInputException() { super(); }

  /**
   * Overridden field with custom error message.
   */
  EmptyInputException(String errMessage) { super(errMessage); }
}
