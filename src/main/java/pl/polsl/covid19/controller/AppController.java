
package pl.polsl.covid19.controller;

import java.awt.event.ActionListener;
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

  public AppController() {
    appView = new AppView(countryWithHighestNumberOfDeathsActionListener(),
        dataOrderedByActiveCasesActionListener(),
        numberOfTestsByCountryActionListener(),
        pearsonsCoefficientActionListener());
  }

  private ActionListener countryWithHighestNumberOfDeathsActionListener() {
    return e -> appView.showCountryWithHighestNumberOfDeaths(
        appModel.findCovidRecordWithHighestNumberOfDeaths());
  }

  private ActionListener dataOrderedByActiveCasesActionListener() {
    return e -> appView.printCountriesOrderedByActiveCases(
        appModel.getCovidDataOrderedByActiveCases());
  }

  private ActionListener numberOfTestsByCountryActionListener() {
    return e -> appView.printNumberOfTestsPerCountry(appModel.getCovidData());
  }

  private ActionListener pearsonsCoefficientActionListener() {
    return e -> appView.printFeatureYetToBeImplementedMessage();
  }
}
