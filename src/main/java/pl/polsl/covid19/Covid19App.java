package pl.polsl.covid19;

import pl.polsl.covid19.controller.AppController;


/**
 * Main application class that handles application run and initializes app controller.
 *
 * @author Krzysztof Franków
 * @version 1.1
 */
public class Covid19App {

  /**
   * Main function for the covid19 application.
   *
   * @param args a list of console arguments. Accepts one number in range of 1 to 4 indicating a number of task that
   *             should be performed. In case of passing incorrect number or unsupported number of arguments,
   *             the app will present simple console menu and will allow user to select a task.
   *             Application uses the opencsv library that need to be included into dependencies while running.
   *             Library is included in jar file containing jar-with-dependencies in name.
   *             <p>
   *             Example console command for running application task 1 in command line:
   *             java -jar pl.polsl.Covid19-1.0-SNAPSHOT-jar-with-dependencies.jar 1
   */
  public static void main(String... args) {
    new AppController();
  }
}
