package pl.polsl.covid19.model;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reads data from csv covid file and converts it into collection of {@link CovidData}.
 *
 * @author Krzysztof Franków
 * @version 1.0
 */
class CsvFileReader {

  /**
   * Reads CsvFile of given name and returns collection of {@link CovidData} records.
   * Uses instance of {@link CSVReader} from external opencsv library.
   *
   * @param filename name of file containing covid data
   * @return collection of {@link CovidData}
   */
  List<CovidData> readCsvFile(final String filename) {
    var records = new ArrayList<CovidData>();
    var fileInputStream = getClass().getClassLoader().getResourceAsStream(filename);
    if (fileInputStream == null) {
      throw new AppException("File " + filename + " not found");
    }
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(fileInputStream))) {
      while (true) {
        var rawValue = csvReader.readNext();
        if (rawValue == null) {
          break;
        }
        var values = Arrays.asList(rawValue);
        if (!values.get(0).equals("#")) {
          records.add(initializeCovidDataFromValues(values));
        }
      }
    } catch (IOException e) {
      throw new AppException("Unable to read source data file", e);
    }
    return records;
  }

  /**
   * Initializes {@link CovidData} record from csv file line represented as String array.
   *
   * @param values string array representing one line in csv file
   * @return {@link CovidData} record of covid data
   */
  private CovidData initializeCovidDataFromValues(List<String> values) {
    var covidDataRecord = new CovidData();
    covidDataRecord.setCountry(values.get(1));
    covidDataRecord.setTotalDeaths(getNumberFromStringValue(values.get(3)));
    covidDataRecord.setActiveCases(getNumberFromStringValue(values.get(6)));
    covidDataRecord.setTotalTests(getNumberFromStringValue(values.get(10)));
    return covidDataRecord;
  }

  /**
   * Removes commas from given string and converts it into a number or 0 in case of conversion error.
   *
   * @param value number as string value
   * @return converted number as integer
   */
  private int getNumberFromStringValue(final String value) {
    try {
      return Integer.parseInt(value.replace(",", ""));
    } catch (NumberFormatException e) {
      return 0;
    }
  }
}
