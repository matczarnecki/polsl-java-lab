package pl.polsl.covid19.view;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pl.polsl.covid19.model.CovidData;

/**
 * View class that handles console outputs.
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public class AppView {
  private final JPanel titlePanel;
  private final JPanel menuPanel;
  private final JPanel resultsPanel;

  private final ActionListener countryWithHighestNumberOfDeathsActionListener;
  private final ActionListener dataOrderedByActiveCasesActionListener;
  private final ActionListener numberOfTestsByCountryActionListener;
  private final ActionListener pearsonsCoefficientActionListener;

  public AppView(ActionListener countryWithHighestNumberOfDeathsActionListener,
                 ActionListener dataOrderedByActiveCasesActionListener,
                 ActionListener numberOfTestsByCountryActionListener,
                 ActionListener pearsonsCoefficientActionListener) {
    this.countryWithHighestNumberOfDeathsActionListener = countryWithHighestNumberOfDeathsActionListener;
    this.dataOrderedByActiveCasesActionListener = dataOrderedByActiveCasesActionListener;
    this.numberOfTestsByCountryActionListener = numberOfTestsByCountryActionListener;
    this.pearsonsCoefficientActionListener = pearsonsCoefficientActionListener;

    titlePanel = initializeTitlePanel();
    menuPanel = initializeMenuPanel();
    resultsPanel = initializeResultsPanel();

    initializeMainAppFrame();
  }

  /**
   * Prints application menu to the console.
   */
  public void printApplicationMenu() {

  }

  private void initializeMainAppFrame() {
    var newFrame = new JFrame();
    newFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    newFrame.setLayout(null);
    newFrame.setSize(1200, 800);
    newFrame.setResizable(false);

    newFrame.add(titlePanel);
    newFrame.add(menuPanel);
    newFrame.add(resultsPanel);
    newFrame.setVisible(true);
  }

  private JPanel initializeTitlePanel() {
    var titleLabel = new JLabel("Covid-19 Data Analyzer");
    titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));

    var newTitlePanel = new JPanel();
    newTitlePanel.setBounds(0, 0, 1200, 100);
    newTitlePanel.add(titleLabel);

    //    titlePanel.setBackground(Color.cyan);
    return newTitlePanel;
  }

  private JPanel initializeMenuPanel() {
    var menuTopLabel = new JLabel("Choose a task:");
    menuTopLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

    var printHighestNoOfCasesButton = new JButton("1. Print a country with the highest number of deaths");
    printHighestNoOfCasesButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    printHighestNoOfCasesButton.setBounds(0, 100, 500, 100);
    printHighestNoOfCasesButton.addActionListener(countryWithHighestNumberOfDeathsActionListener);

    var sortByNumberOfActiveButton = new JButton("2. Sort countries by number of active cases");
    sortByNumberOfActiveButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    sortByNumberOfActiveButton.setBounds(0, 200, 500, 100);
    sortByNumberOfActiveButton.addActionListener(dataOrderedByActiveCasesActionListener);

    var printTestsPerCountryButton = new JButton("3. Print number of tests per country");
    printTestsPerCountryButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    printTestsPerCountryButton.setBounds(0, 300, 500, 100);
    printTestsPerCountryButton.addActionListener(numberOfTestsByCountryActionListener);

    var calculatePearsonsCoefficientButton = new JButton("4. Calculate Pearson's coefficient");
    calculatePearsonsCoefficientButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
    calculatePearsonsCoefficientButton.setBounds(0, 400, 500, 100);
    calculatePearsonsCoefficientButton.addActionListener(pearsonsCoefficientActionListener);

    var newMenuPanel = new JPanel();
    newMenuPanel.setBounds(0, 100, 500, 700);
    newMenuPanel.add(menuTopLabel);
    newMenuPanel.add(printHighestNoOfCasesButton);
    newMenuPanel.add(sortByNumberOfActiveButton);
    newMenuPanel.add(printTestsPerCountryButton);
    newMenuPanel.add(calculatePearsonsCoefficientButton);

    //    menuPanel.setBackground(Color.blue);
    return newMenuPanel;
  }

  private JPanel initializeResultsPanel() {
    var newResultPanel = new JPanel();
    newResultPanel.setBounds(400, 100, 700, 700);


//    resultsPanel.setBackground(Color.red);
    return newResultPanel;
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
}
