package pl.polsl.covid19.model;

/**
 * Exception thrown in case of application error occurred.
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public class AppException extends RuntimeException {

  /**
   * Exception constructor with message.
   *
   * @param message exception message
   */
  public AppException(String message) {
    super(message);
  }

  /**
   * Exception constructor with message and cause.
   *
   * @param message exception message
   * @param cause   the cause of the exception
   */
  public AppException(String message, Throwable cause) {
    super(message, cause);
  }
}
