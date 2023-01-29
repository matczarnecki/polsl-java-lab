package pl.polsl.covid19.model;

/**
 * Represents a single record of csv file data.
 *
 * @author Krzysztof Franków
 * @version 1.1
 */
public class CovidData {

  /**
   * Country name.
   */
  private String country;

  /**
   * Total number of covid19 related deaths.
   */
  private Integer totalDeaths;

  /**
   * Total number of covid19 active cases.
   */
  private Integer activeCases;

  /**
   * Total number of all performed tests.
   */
  private Integer totalTests;


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Integer getTotalDeaths() {
    return totalDeaths;
  }

  public void setTotalDeaths(Integer totalDeaths) {
    this.totalDeaths = totalDeaths;
  }

  public Integer getActiveCases() {
    return activeCases;
  }

  public void setActiveCases(Integer activeCases) {
    this.activeCases = activeCases;
  }

  public Integer getTotalTests() {
    return totalTests;
  }

  public void setTotalTests(Integer totalTests) {
    this.totalTests = totalTests;
  }
}
