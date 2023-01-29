package pl.polsl.covid19.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link CsvFileReader}
 *
 * @author Krzysztof Franków
 * @version 1.0
 */
class CsvFileReaderTest {

  /**
   * Name of file with covid data.
   */
  private static final String DATA_FILENAME = "CovidLive.csv";

  /**
   * Incorrect filename.
   */
  private static final String INCORRECT_FILENAME = "INCORRECT_FILENAME";

  /**
   * Instance of tested csvFileReader.
   */
  private CsvFileReader csvFileReader;

  /**
   * CsvFileReaderTest setup.
   */
  @BeforeEach
  public void setup() {
    csvFileReader = new CsvFileReader();
  }

  /**
   * Test that verifies if csvFileReader reads data correctly when correct filename is provided.
   */
  @Test
  void givenCorrectFilenameWhenReadingCsvThenReturnListOfCovidData() {
    var data = csvFileReader.readCsvFile(DATA_FILENAME);
    var firstRecord = data.get(0);
    assertEquals("USA", firstRecord.getCountry());
    assertEquals(1084282, firstRecord.getTotalDeaths());
    assertEquals(2120510, firstRecord.getActiveCases());
    assertEquals(1118158870, firstRecord.getTotalTests());

    var secondRecord = data.get(1);
    assertEquals("India", secondRecord.getCountry());
    assertEquals(528629, secondRecord.getTotalDeaths());
    assertEquals(39583, secondRecord.getActiveCases());
    assertEquals(894416853, secondRecord.getTotalTests());

    var middleRecord = data.get(115);
    assertEquals("El Salvador", middleRecord.getCountry());
    assertEquals(4229, middleRecord.getTotalDeaths());
    assertEquals(18146, middleRecord.getActiveCases());
    assertEquals(2610114, middleRecord.getTotalTests());

    var nextToLastRecord = data.get(228);
    assertEquals("Western Sahara", nextToLastRecord.getCountry());
    assertEquals(1, nextToLastRecord.getTotalDeaths());
    assertEquals(0, nextToLastRecord.getActiveCases());
    assertEquals(0, nextToLastRecord.getTotalTests());

    var lastRecord = data.get(229);
    assertEquals("MS Zaandam", lastRecord.getCountry());
    assertEquals(2, lastRecord.getTotalDeaths());
    assertEquals(0, lastRecord.getActiveCases());
    assertEquals(0, lastRecord.getTotalTests());
  }

  /**
   * Test that verifies if csvFileReader throws correct exception when incorrect filename provided.
   */
  @Test
  void givenIncorrectFilenameWhenReadingCsvThenThrowAppException() {
    assertThrows(AppException.class, () -> csvFileReader.readCsvFile(INCORRECT_FILENAME));
  }

}
