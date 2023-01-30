
package pl.polsl.covid19.controller;

import java.awt.event.ActionListener;
import pl.polsl.covid19.model.AppException;
import pl.polsl.covid19.model.AppModel;
import pl.polsl.covid19.view.AppView;

/**
 * Class will be used to read user input.
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public class AppController {

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
  private final AppView appView;

  /**
   * Class constructor that initializes view with action listeners.
   */
  public AppController() {
    appView = new AppView(countryWithHighestNumberOfDeathsActionListener(),
        dataOrderedByActiveCasesActionListener(),
        numberOfTestsByCountryActionListener(),
        pearsonsCoefficientActionListener());
  }

  /**
   * Defines an action listener of finding country with the highest number of deaths.
   *
   * @return action listener of finding country with highest number of deaths
   */
  private ActionListener countryWithHighestNumberOfDeathsActionListener() {
    return e -> {
      try {
        appView.showCountryWithHighestNumberOfDeaths(
            appModel.findCovidRecordWithHighestNumberOfDeaths());
      } catch (AppException ex) {
        appView.handlePrintErrorPopup(ex.getMessage());
      }
    };
  }

  /**
   * Defines an action listener of finding covid data ordered by active cases.
   *
   * @return action of finding covid data ordered by active cases
   */
  private ActionListener dataOrderedByActiveCasesActionListener() {
    return e -> {
      try {
        appView.showCountriesOrderedByActiveCases(
            appModel.getCovidDataOrderedByActiveCases());
      } catch (AppException ex) {
        appView.handlePrintErrorPopup(ex.getMessage());
      }
    };
  }

  /**
   * Defines an action listener of finding number of tests by country.
   *
   * @return action listener of finding number of tests by country
   */
  private ActionListener numberOfTestsByCountryActionListener() {
    return e -> {
      try {
        appView.showNumberOfTestsPerContry(appModel.getCovidData());
      } catch (AppException ex) {
        appView.handlePrintErrorPopup(ex.getMessage());
      }
    };
  }

  /**
   * Defines an action listener of calculating Pearson's coefficient.
   *
   * @return action listener of calculating Pearson's coefficient
   */
  private ActionListener pearsonsCoefficientActionListener() {
    return e -> {
      try {
        appView.showFeatureYetToBeImplementedMessage();
      } catch (AppException ex) {
        appView.handlePrintErrorPopup(ex.getMessage());
      }
    };
  }
}
