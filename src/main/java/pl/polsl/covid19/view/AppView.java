package pl.polsl.covid19.view;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
  private JPanel resultsPanel;

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
    var initialLabel = new JLabel("No results to show");
    initialLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

    var newResultPanel = new JPanel();
    newResultPanel.setBounds(400, 100, 700, 700);
    newResultPanel.add(initialLabel);

//    resultsPanel.setBackground(Color.red);
    return newResultPanel;
  }

  public void showCountryWithHighestNumberOfDeaths(final CovidData result) {
    var resultTitleLabel = new JLabel("Country with the highest number of deaths is:");
    resultTitleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

    var resultLabel = new JLabel(result.getCountry());
    resultLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

    resultsPanel.removeAll();
    resultsPanel.add(resultTitleLabel);
    resultsPanel.add(resultLabel);
    resultsPanel.updateUI();
  }

  public void showCountriesOrderedByActiveCases(final List<CovidData> results) {
    Object[][] resultsArray = getObjectsFromListActiveCases(results);
    String[] columnNames = {"Country name", "Active cases"};
    var resultTable = new JTable(resultsArray, columnNames);
    var scroll = new JScrollPane(resultTable);
    resultsPanel.removeAll();
    resultsPanel.add(scroll);
    resultsPanel.updateUI();
  }

  public void showNumberOfTestsPerContry(final List<CovidData> results) {
    Object[][] resultsArray = getObjectsFromListTotalCases(results);
    String[] columnNames = {"Country name", "Total tests"};
    var resultTable = new JTable(resultsArray, columnNames);
    var scroll = new JScrollPane(resultTable);
    resultsPanel.removeAll();
    resultsPanel.add(scroll);
    resultsPanel.updateUI();
  }

  public void showFeatureYetToBeImplementedMessage() {
    var infoPanel = new JLabel("This feature is yet to be implemented");
    infoPanel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

    resultsPanel.removeAll();
    resultsPanel.add(infoPanel);
    resultsPanel.updateUI();
  }

  private Object[][] getObjectsFromListActiveCases(List<CovidData> listData) {
    Object[][] resultArray = new String[listData.size()][4];
    for (int i = 0; i < listData.size(); i++) {
      resultArray[i][0] = listData.get(i).getCountry();
      resultArray[i][1] = listData.get(i).getActiveCases().toString();
    }
    return resultArray;
  }

  private Object[][] getObjectsFromListTotalCases(List<CovidData> listData) {
    Object[][] resultArray = new String[listData.size()][4];
    for (int i = 0; i < listData.size(); i++) {
      resultArray[i][0] = listData.get(i).getCountry();
      resultArray[i][1] = listData.get(i).getTotalTests().toString();
    }
    return resultArray;
  }
}
