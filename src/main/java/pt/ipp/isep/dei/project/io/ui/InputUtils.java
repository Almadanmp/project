package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.Device.Device;
import pt.ipp.isep.dei.project.model.Device.Program;
import pt.ipp.isep.dei.project.model.Device.ProgramList;

import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
class InputUtils {

    void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }

    GeographicArea getGeographicAreaByList(GeographicAreaList geographicAreaList) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (geographicAreaList.getGeographicAreaList().isEmpty()) {
            System.out.println("Invalid Geographic Area list - List is empty\n");
            return null;
        }
        System.out.println("Please select one of the existing geographic areas: ");
        System.out.println(geographicAreaList.buildGaWholeListString(geographicAreaList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < geographicAreaList.getGeographicAreaList().size()) {
            GeographicArea result = geographicAreaList.getGeographicAreaList().get(aux);
            String stringRequestGA = "You have chosen the following Geographic Area: ";
            System.out.println(stringRequestGA);
            System.out.println(result.buildGeographicAreaString());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
    }

    Room getHouseRoomByList(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (house.getRoomList().isEmpty()) {
            System.out.println("Invalid room list - List is empty\n");
            return null;
        }
        System.out.println("Please select one of the existing rooms in the house: ");
        System.out.println(house.buildRoomListString());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < house.getRoomList().size()) {
            Room result = house.getRoomList().get(aux);
            String stringRequestRoom = "You have chosen the following Room: ";
            System.out.println(stringRequestRoom);
            System.out.println(result.buildRoomString());
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
            System.out.println("Invalid room list - List is empty\n");
            return null;
        }
        System.out.println("Please select one of the existing rooms in the house: ");
        System.out.println(grid.buildRoomListString());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < grid.getRoomList().size()) {
            Room result = grid.getRoomList().get(aux);
            String stringRequestRoom = "You have chosen the following Room: ";
            System.out.println(stringRequestRoom);
            System.out.println(result.buildRoomString());
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
            System.out.println("Invalid device list - List is empty\n");
            return null;
        }
        System.out.println("Please select one of the existing devices in the selected room: ");
        System.out.println(grid.buildDeviceListString());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < grid.getDeviceList().size()) {
            Device result = grid.getDeviceList().get(aux);
            String stringRequestDevice = "You have chosen the following device: ";
            System.out.println(stringRequestDevice);
            System.out.println(result.buildDeviceString());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
    }

    Program getSelectedProgramFromDevice(Device device) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        ProgramList programList = (ProgramList)device.getAttributeValue("programList");
        if (programList.getProgramList().isEmpty()) {
            System.out.println("Invalid Program list - List is empty\n");
            return null;
        }
        System.out.println("Please select one of the existing Programs in the selected Program List to alter: ");
        System.out.println(programList.buildProgramListString());
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < programList.getProgramList().size()) {
            Program result =programList.getProgramList().get(aux);
            String stringRequestProgram = "You have chosen the following Program: ";
            System.out.println(stringRequestProgram);
            System.out.println(result.buildProgramString());
            return result;
        } else {
            System.out.println(utils.invalidOption);
            return null;
        }
    }

    Device getInputDeviceByList(Room room) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (room.getDeviceList().isEmpty()) {
            System.out.println("Invalid Device List - List Is Empty\n");
            return null;
        }
        System.out.println("Please select one of the existing Devices in the selected Room: ");
            System.out.println(room.buildDeviceListString());
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < room.getDeviceList().size()) {
                Device result = room.getDeviceList().get(aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildDeviceString());
                return result;
            } else {
                System.out.println(utils.invalidOption);
                return null;
            }

        }


    EnergyGrid getInputGridByList(House house) {
        EnergyGridSettingsController controller = new EnergyGridSettingsController();
        EnergyGrid result = new EnergyGrid();
        UtilsUI utilsUI = new UtilsUI();
        boolean activeInput = false;
        System.out.println("Please select one of the existing grids on the selected house: ");
        while (!activeInput) {
            System.out.println(controller.buildGridListString(house));
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
        while (!("y".equalsIgnoreCase(answer)) && !("n".equalsIgnoreCase(answer))) {
            System.out.println(utils.invalidOption);
            System.out.println(question);
            answer = scanner.nextLine();
        }
        if ("y".equalsIgnoreCase(answer)) {
            return true;
        } else return !"n".equalsIgnoreCase(answer);
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

    /**
     * Method to read a double value from a user.
     * Will validate input is a double. if it isn't it will print an error message.
     * @return value read from user
     */
    Double getInputAsDouble(){
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        return scanner.nextDouble();
    }


}
