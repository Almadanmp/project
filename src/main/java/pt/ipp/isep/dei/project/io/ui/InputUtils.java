package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.Device;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

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

    Room getHouseRoomByList(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (house.getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return null;
        }
        System.out.println("Please select one of the existing Rooms in the House: ");
        System.out.println(house.printRoomList());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < house.getRoomList().size()) {
            Room result = house.getRoomList().get(aux);
            String stringRequestRoom = "You have chosen the following Room:";
            System.out.println(stringRequestRoom);
            System.out.println(result.printRoom());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
    }

    Room getGridRoomByList(EnergyGrid grid) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (grid.getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return null;
        }
        System.out.println("Please select one of the existing Rooms in the House: ");
        System.out.println(grid.printRoomList());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < grid.getRoomList().size()) {
            Room result = grid.getRoomList().get(aux);
            String stringRequestRoom = "You have chosen the following Room:";
            System.out.println(stringRequestRoom);
            System.out.println(result.printRoom());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
    }

    Device getGridDevicesByList(EnergyGrid grid) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (grid.getDeviceList().isEmpty()) {
            System.out.println("Invalid Device List - List Is Empty\n");
            return null;
        }
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        System.out.println(grid.printDeviceList());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < grid.getDeviceList().size()) {
            Device result = grid.getDeviceList().get(aux);
            String stringRequestDevice = "You have chosen the following Device:";
            System.out.println(stringRequestDevice);
            System.out.println(result.printDevice());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
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
        System.out.println("Please select one of the existing grids on the selected house: ");
        while (!activeInput) {
            System.out.println(controller.printGridList(house));
            int aux = this.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getEGList().getEnergyGridList().size()) {
                result = house.getEGList().getEnergyGridList().get(aux);
                activeInput = true;
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        }
        System.out.println("You have successfully selected a grid.\n");
        return result;
    }

    boolean yesOrNo(String answer, String question){
        UtilsUI utils = new UtilsUI();
        Scanner scanner = new Scanner(System.in);
        while (!(answer.equalsIgnoreCase("y")) && !(answer.equalsIgnoreCase("n"))) {
            System.out.println(utils.invalidOption);
            System.out.println(question);
            answer = scanner.nextLine();
        }
        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else return !answer.equalsIgnoreCase("n");
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
