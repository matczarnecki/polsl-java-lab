
package pl.polsl.covid19.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import pl.polsl.covid19.model.AppException;
import pl.polsl.covid19.model.Task;
import pl.polsl.covid19.view.AppView;
import pl.polsl.covid19.model.AppModel;

/**
 * Class will be used to read user input.
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public class AppController {

  /**
   * Contains information about correct number of console arguments.
   */
  private static final int NUMBER_OF_CONSOLE_ARGS = 1;

  /**
   * Name of file with covid data.
   */
  private static final String DATA_FILENAME = "CovidLive.csv";

  /**
   * Instance of app model used for business application logic.
   */
  private final AppModel appModel = new AppModel(DATA_FILENAME);

  /**
   * Instance of app view used for handling view operations.
   */
  private final AppView appView = new AppView();

  /**
   * Handles input provided by user and selects task to perform.
   *
   * @param args command line arguments
   */
  public void handleUserInput(String... args) {
    try {
      var taskNumber = args.length == NUMBER_OF_CONSOLE_ARGS ? Integer.parseInt(args[0]) : handleConsoleInput();
      switch (Task.fromInt(taskNumber)) {
        case FIND_RECORD_HIGHEST_NUMBER_OF_DEATHS -> appView.printCountryWithHighestNumberOfDeaths(
            appModel.findCovidRecordWithHighestNumberOfDeaths());
        case GET_DATA_ORDERED_BY_ACTIVE_CASES -> appView.printCountriesOrderedByActiveCases(
            appModel.getCovidDataOrderedByActiveCases());
        case GET_DATA -> appView.printNumberOfTestsPerCountry(appModel.getCovidData());
        case GET_PEARSONS_COEFFICIENT -> appView.printFeatureYetToBeImplementedMessage();
        default -> appView.printTaskNotFoundMessage();
      }
    } catch (AppException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Receives user console input and converts it into {@link Task} task enumerated type.
   *
   * @return {@link Task} task enumerated type
   */
  private int handleConsoleInput() {
    try (Scanner scanner = new Scanner(System.in)) {
      appView.printApplicationMenu();
      return scanner.nextInt();
    } catch (InputMismatchException e) {
      return 0;
    }
  }
}
