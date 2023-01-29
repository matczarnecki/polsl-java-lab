package pl.polsl.covid19.view;

import java.util.List;
import javax.swing.JFrame;
import pl.polsl.covid19.model.CovidData;

/**
 * View class that handles console outputs.
 *
 * @author Krzysztof Franków
 * @version 1.1
 */
public class AppView {

  /**
   * Prints application menu to the console.
   */
  public void printApplicationMenu() {
    System.out.println("# ====================================================== #");
    System.out.println("Choose the task number and press Enter...");
    System.out.println("1. Print country with the highest number of deaths.");
    System.out.println("2. Sort countries by highest number of active cases.");
    System.out.println("3. Print number of tests per country.");
    System.out.println("4. Calculate Pearson's correlation.");
    System.out.println("# ====================================================== #");
  }

  /**
   * Prints output with a country with the highest number of deaths to the console.
   *
   * @param result {@link CovidData} result that will be printed
   */
  public void printCountryWithHighestNumberOfDeaths(final CovidData result) {
    System.out.println("Country with highest number of deaths is: " + result.getCountry());
  }

  /**
   * Prints output with list of countries ordered by active covid cases to the console.
   *
   * @param results collection of {@link CovidData} containing records sorted by number of active cases
   */
  public void printCountriesOrderedByActiveCases(final List<CovidData> results) {
    System.out.println("Countries by highest number of deaths:");
    System.out.printf("%-30.30s  %-30.30s%n", "Country", "Number of active cases");
    System.out.println("-----------------------------------------------------------");
    for (var result : results) {
      System.out.printf("%-30.30s  %-30.30s%n", result.getCountry(), result.getActiveCases());
    }
  }

  /**
   * Prints number of tests done in each country.
   *
   * @param results collection of {@link CovidData} containing information about
   *                countries and tests done in each of them
   */
  public void printNumberOfTestsPerCountry(final List<CovidData> results) {
    System.out.println("Number of tests per country:");
    System.out.printf("%-30.30s  %-30.30s%n", "Country", "Number of tests");
    System.out.println("-----------------------------------------------------------");
    results.forEach(result -> System.out.printf("%-30.30s  %-30.30s%n", result.getCountry(), result.getTotalTests()));
  }

  /**
   * Prints information about the feature is yet to be implemented.
   */
  public void printFeatureYetToBeImplementedMessage() {
    System.out.println("This feature is yet to be implemented");
  }

  /**
   * Prints a message about not being able to find requested task.
   */
  public void printTaskNotFoundMessage() {
    System.out.println("Task not found with given number");
  }

  public void testSwing() {
    JFrame frame = new JFrame();
    frame.setVisible(true);
  }
}
