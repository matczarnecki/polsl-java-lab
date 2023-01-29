package pl.polsl.covid19.model;

/**
 * Enumerated type representing all possible operations in application
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public enum Task {

  /**
   * Represents a task of finding a record with the highest number of deaths
   */
  FIND_RECORD_HIGHEST_NUMBER_OF_DEATHS,

  /**
   * Represents a task of printing all countries ordered by active covid 19 cases
   */
  GET_DATA_ORDERED_BY_ACTIVE_CASES,

  /**
   * Represents a task of returning all countries
   */
  GET_DATA,

  /**
   * Represents a task of calculating Pearsons Coefficient
   */
  GET_PEARSONS_COEFFICIENT,

  /**
   * Represents a lack of task to perform
   */
  DO_NOTHING;

  /**
   * Converts given integer into Task.
   *
   * @param number task number
   * @return task
   */
  public static Task fromInt(final int number) {
    return switch (number) {
      case 1 -> FIND_RECORD_HIGHEST_NUMBER_OF_DEATHS;
      case 2 -> GET_DATA_ORDERED_BY_ACTIVE_CASES;
      case 3 -> GET_DATA;
      case 4 -> GET_PEARSONS_COEFFICIENT;
      default -> DO_NOTHING;
    };
  }

}
