package pl.polsl.covid19.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for AppModel {@link AppModel}.
 *
 * @author Krzysztof Franków
 * @version 1.0
 */
class AppModelTest {

  /**
   * Name of file with covid data.
   */
  private static final String DATA_FILENAME = "CovidLive.csv";

  /**
   * Incorrect filename.
   */
  private static final String INCORRECT_FILENAME = "INCORRECT_FILENAME";

  /**
   * Instance of appModel with correct filename.
   */
  private AppModel appModel;

  /**
   * Instance of appModel with incorrect filename.
   */
  private static final AppModel incorrectFilenameAppModel = new AppModel(INCORRECT_FILENAME);

  /**
   * AppModelTest setup.
   */
  @BeforeEach
  public void setup() {
    appModel = new AppModel(DATA_FILENAME);
  }

  /**
   * Test that verifies if app model with correct filename when finding record with the highest number of
   * deaths returns correct record.
   */
  @Test
  void givenCorrectFileWhenFindingRecordWithHighestNumberOfDeathsThenReturnCorrectRecord() {
    var foundRecord = appModel.findCovidRecordWithHighestNumberOfDeaths();
    assertEquals("USA", foundRecord.getCountry());
    assertEquals(1084282, foundRecord.getTotalDeaths());
    assertEquals(2120510, foundRecord.getActiveCases());
    assertEquals(1118158870, foundRecord.getTotalTests());
  }

  /**
   * Test that verifies if app model with correct filename when finding covid data ordered by active cases returns
   * correctly ordered data.
   */
  @Test
  void givenCorrectFileWhenFindingDataOrderedByActiveCasesThenReturnOrderedData() {
    var sortedRecords = appModel.getCovidDataOrderedByActiveCases();
    var firstRecord = sortedRecords.get(0);
    assertEquals("USA", firstRecord.getCountry());
    assertEquals(1084282, firstRecord.getTotalDeaths());
    assertEquals(2120510, firstRecord.getActiveCases());
    assertEquals(1118158870, firstRecord.getTotalTests());

    var secondRecord = sortedRecords.get(1);
    assertEquals("Germany", secondRecord.getCountry());
    assertEquals(149948, secondRecord.getTotalDeaths());
    assertEquals(847225, secondRecord.getActiveCases());
    assertEquals(122332384, secondRecord.getTotalTests());

    var middleRecord = sortedRecords.get(115);
    assertEquals("Fiji", middleRecord.getCountry());
    assertEquals(878, middleRecord.getTotalDeaths());
    assertEquals(1062, middleRecord.getActiveCases());
    assertEquals(662620, middleRecord.getTotalTests());

    var nextToLastRecord = sortedRecords.get(228);
    assertEquals("Western Sahara", nextToLastRecord.getCountry());
    assertEquals(1, nextToLastRecord.getTotalDeaths());
    assertEquals(0, nextToLastRecord.getActiveCases());
    assertEquals(0, nextToLastRecord.getTotalTests());

    var lastRecord = sortedRecords.get(229);
    assertEquals("MS Zaandam", lastRecord.getCountry());
    assertEquals(2, lastRecord.getTotalDeaths());
    assertEquals(0, lastRecord.getActiveCases());
    assertEquals(0, lastRecord.getTotalTests());
  }

  /**
   * Test that verifies if app model with correct filename when getting all data returns correct list of data.
   */
  @Test
  void givenCorrectFileWhenFindingDataThenReturnData() {
    var data = appModel.getCovidData();
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
   * Parameterized test that verifies if all methods return AppException if incorrect filename was used.
   *
   * @param callable methods that will be tested
   */
  @ParameterizedTest
  @MethodSource("provideMethodsForThrowAppException")
  void givenIncorrectFileNameWhenGettingDataThenThrowAppException(ThrowingSupplier<Object> callable) {
    Throwable exception = assertThrows(AppException.class, callable::get);
    assertEquals("File INCORRECT_FILENAME not found", exception.getMessage());
  }

  /**
   * A source of methods to be tested in parameterized test.
   *
   * @return methods to be tested in parameterized test
   */
  private static Stream<ThrowingSupplier<Object>> provideMethodsForThrowAppException() {
    return Stream.of(incorrectFilenameAppModel::findCovidRecordWithHighestNumberOfDeaths,
        incorrectFilenameAppModel::getCovidDataOrderedByActiveCases,
        incorrectFilenameAppModel::getCovidData);
  }

}
