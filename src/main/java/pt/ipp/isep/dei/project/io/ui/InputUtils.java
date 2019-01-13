package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.GeographicArea;

import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
class InputUtils {

    private GeographicArea mGeographicArea;
    private String mGeographicAreaName;
    private String mStringChosenGeographicArea = "You have chosen the following Geographic Area:";
    private String mReturnString = "0) Return;";

    String invalidOption = "Please enter a valid option";

    /**
     * Method to read the user input as an Int
     * If its not an int it will print an invalid option message
     * If its a double it will convert it to an int
     *
     * @return value read from the user
     */
    int readInputNumberAsInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            System.out.println(invalidOption);
            scan.next();
        }
        Double option = scan.nextDouble();
        return option.intValue();
    }

    /**
     * Method to get a date as an int.
     *
     * @param scan     the scanner to read input
     * @param dataType the type of date to read (year, month or day)
     * @return value read from the user
     */
    int getInputDateAsInt(Scanner scan, String dataType) {
        System.out.println("Enter the " + dataType + ":");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid " + dataType + ". Try again");
        }
        return scan.nextInt();
    }
}
