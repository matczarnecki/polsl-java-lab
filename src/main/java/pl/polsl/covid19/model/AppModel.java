package pl.polsl.covid19.model;

import static java.util.Comparator.comparing;
import java.util.List;

/**
 * Model class used for retrieving and manage covid related data.
 *
 * @author Krzysztof Franków
 * @version 1.2
 */
public class AppModel {

  /**
   * Filename of csv file with covid data.
   */
  private final String filename;

  /**
   * Instance of CsvFileLoader {@link CsvFileReader}.
   */
  private final CsvFileReader csvFileReader = new CsvFileReader();

  /**
   * AppModel class constructor.
   *
   * @param dataFilename filename of the file with covid data
   */
  public AppModel(String dataFilename) {
    this.filename = dataFilename;
  }

  /**
   * Finds a country with the highest number of covid deaths.
   *
   * @return {@link CovidData} covid data record of country with the highest number of covid deaths
   */
  public CovidData findCovidRecordWithHighestNumberOfDeaths() {
    var covidData = csvFileReader.readCsvFile(filename);
    return covidData
        .stream()
        .max(comparing(CovidData::getTotalDeaths))
        .orElseThrow(() -> new AppException("Country not found with highest number of deaths."));
  }

  /**
   * Finds and sorts covid data by the highest number of active cases.
   *
   * @return collection of {@link CovidData} that contains covid data records
   * ordered by the highest number of active cases
   */
  public List<CovidData> getCovidDataOrderedByActiveCases() {
    var covidData = csvFileReader.readCsvFile(filename);
    return covidData
        .stream()
        .sorted(comparing(CovidData::getActiveCases).reversed())
        .toList();
  }

  /**
   * Finds all covid data records.
   *
   * @return collection of {@link CovidData} that contains all covid data records
   */
  public List<CovidData> getCovidData() {
    return csvFileReader.readCsvFile(filename);
  }
}
