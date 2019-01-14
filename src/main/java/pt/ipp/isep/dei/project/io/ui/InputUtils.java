package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;

import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
class InputUtils {

    public void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }

    EnergyGrid getInputGridByList(House house) {
        EnergyGridSettingsController controller = new EnergyGridSettingsController();
        EnergyGrid result = new EnergyGrid();
        if (house == null) {
            System.out.println("The selected house is NOT a valid one\n" + "Returning to main menu\n");
            return null;
        }
        UtilsUI utilsUI = new UtilsUI();
        if (house.getEGList().getEnergyGridList().isEmpty()) {
            System.out.print("Invalid Grid List - List Is Empty\n" + "Returning to main menu\n");
            return null;
        }
        boolean activeInput = false;
        InputUtils inputUtils = new InputUtils();
        System.out.println("Please select one of the existing grids on the selected house: ");
        while (!activeInput) {
            System.out.println(controller.printGridList(house));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getEGList().getEnergyGridList().size()) {
                result = house.getEGList().getEnergyGridList().get(aux);
                activeInput = true;
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        }
        return result;
    }

    /**
     * Method to read the user input as an Int
     * If its not an int it will print an invalid option message
     * If its a double it will convert it to an int
     *
     * @return value read from the user
     */
    int readInputNumberAsInt() {
        UtilsUI utils = new UtilsUI();
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            System.out.println(utils.invalidOption);
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
