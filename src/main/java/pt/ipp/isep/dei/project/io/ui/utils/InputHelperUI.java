package pt.ipp.isep.dei.project.io.ui.utils;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
public class InputHelperUI {

    private static final String SELECT_ROOMS = "You have chosen the following room: ";
    private static final String SELECT_DEVICES = "Please select one of the existing devices in the selected room: ";

    /**
     * Method used to introduce a pause, usually after information is displayed to the user. Prompts user to press
     * ENTER to continue.
     *
     * @param scanner is a generic stdin scanner.
     */
    public static void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }

    /**
     * Method to select a particular geographic area from the list of geographic areas available to the program.
     *
     * @param geographicAreaList is the list of geographic areas available to the program.
     * @return is the selected geographic area.
     */
    public static GeographicArea getGeographicAreaByList(GeographicAreaList geographicAreaList) {
        while (true) {
            System.out.println("Please select one of the existing geographic areas: ");
            System.out.println(geographicAreaList.buildString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < geographicAreaList.size()) {
                GeographicArea result = geographicAreaList.get(aux);
                System.out.println("You have chosen the following geographic area: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that returns a particular Room from a list of the program's house available rooms, according to the user's
     * choice by index.
     *
     * @param house is the program's house.
     * @return is the chosen room.
     */
    public static RoomDTO getHouseRoomDTOByList(House house) {
        while (true) {
            System.out.println("Please select one of the existing rooms in the house: ");
            System.out.println(house.buildRoomListString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < house.roomListSize()) {
                Room result = house.getRoomByIndex(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
                return RoomMapper.objectToDTO(result);
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of the house's available rooms, then prompts him to choose one by index.
     *
     * @param house is the program's house.
     * @return is the chosen room.
     */
    public static Room getHouseRoomByList(House house) {
        while (true) {
            System.out.println("Please select one of the existing rooms: ");
            System.out.println(house.buildRoomListString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < house.roomListSize()) {
                Room result = house.getRoomByIndex(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of the rooms connected to a particular grid, then prompts him to choose one by
     * index.
     *
     * @param grid is the grid we want to choose rooms from.
     * @return is the chosen room.
     */
    public static Room getGridRoomByList(EnergyGrid grid) {
        while (true) {
            System.out.println("Please select one of the existing rooms in the house: ");
            System.out.println(grid.buildRoomListString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < grid.roomListSize()) {
                Room result = grid.getRoom(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of the devices connected to all the rooms in a particular grid, then prompts him
     * to choose one by index.
     *
     * @param grid is the grid we want to choose a device from.
     * @return is the chosen device.
     */
    public static Device getGridDevicesByList(EnergyGrid grid) {
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(grid.buildDeviceListString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < grid.getNumberOfDevices()) {
                Device result = grid.getDeviceByIndex(aux);
                System.out.println("You have chosen the following device: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user the list of programs available in a given programmable device, then asks the user
     * to choose one by index.
     *
     * @param device is the programmable device we want to choose a program from.
     * @return is the chosen program.
     */
    public static FixedTimeProgram getSelectedProgramFromDevice(Programmable device) {
        while (true) {
            ProgramList deviceProgramList = device.getProgramList();
            System.out.println("Please select one of the existing program in the selected program List: ");
            System.out.println(deviceProgramList.buildString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < deviceProgramList.size()) {
                FixedTimeProgram result = (FixedTimeProgram) deviceProgramList.get(aux);
                System.out.println("You have chosen the following program: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of all the devices included in a given room DTO, and then prompts the user
     * to choose one by index.
     *
     * @param room  is the room DTO we want to get the list from.
     * @param house is the program's house.
     * @return is the selected Device.
     */
    public static Device getInputRoomDTODevicesByList(RoomDTO room, House house) {
        RoomConfigurationController controller = new RoomConfigurationController();
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(controller.buildDeviceListString(RoomMapper.updateHouseRoom(room, house)));
            int aux = getInputAsInt();
            if (aux >= 0 && aux < controller.getDeviceListSize(room, house)) {
                Device result = controller.getDeviceByIndex(room, house, aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of all the devices included in a given room, and then prompts the user
     * to choose one by index.
     *
     * @param room is the room DTO we want to get the list from.
     * @return is the selected Device.
     */
    public static Device getInputRoomDevicesByList(Room room) {
        RoomConfigurationController controller = new RoomConfigurationController();
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(controller.buildDeviceListString(room));
            int aux = getInputAsInt();
            if (aux >= 0 && aux < room.getDeviceListSize()) {
                Device result = room.getDeviceByIndex(aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of all the grids that exist in the program's house, and then prompts him
     * to choose based on index.
     *
     * @param house is the program's house.
     * @return is the chosen energy grid.
     */
    public static EnergyGrid getInputGridByList(House house) {
        EnergyGridSettingsController controller = new EnergyGridSettingsController();
        while (true) {
            System.out.println("Please select one of the existing grids on the selected house: ");
            System.out.println(controller.buildGridListString(house));
            int aux = getInputAsInt();
            if (aux >= 0 && aux < house.energyGridListSize()) {
                EnergyGrid result = house.getEnergyGridByIndex(aux);
                System.out.println("You have chosen the following grid:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a list of all the available types of sensor the program has, then prompts him to choose
     * one based on index.
     *
     * @param typeSensorList is the list of available types of sensor.
     * @return is the chosen type of sensor.
     */
    public static TypeSensor getInputSensorTypeByList(TypeSensorList typeSensorList) {
        while (true) {
            System.out.println("Please select a type of sensor from the list:");
            System.out.println(typeSensorList.buildString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < typeSensorList.size()) {
                TypeSensor result = typeSensorList.get(aux);
                System.out.println("You have chosen the following sensor type:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user a given sensor list, then prompts him to choose one of the sensors by index.
     *
     * @param sensorList is the sensor list we want to choose a sensor from.
     * @return is the chosen sensor.
     */
    public static Sensor getInputSensorByList(SensorList sensorList) {
        while (true) {
            System.out.println("Please select a sensor from the list:");
            System.out.println(sensorList.toString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < sensorList.size()) {
                Sensor result = sensorList.get(aux);
                System.out.println("You have chosen the following sensor:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that shows the user the list of available device types in the program, then prompts him to choose one by
     * index.
     *
     * @param house is the program's house.
     * @return is the chosen device type.
     */
    public static DeviceType getInputDeviceTypeByList(House house) {
        List<DeviceType> deviceTypeList = house.getDeviceTypeList();
        while (true) {
            System.out.println("Please select one of the device types: ");
            System.out.println(house.buildDeviceTypeString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < house.deviceTypeListSize()) {
                DeviceType result = deviceTypeList.get(aux);
                System.out.println("You have chosen the following device type:");
                System.out.println(result.getDeviceType() + "\n");
                return result;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    /**
     * Method that asks the user a question, then loops getting input from the user until he replies with yes or no.
     *
     * @param question is the question we want to ask the user.
     * @return true if user answers yes, false if user answers no.
     */
    public static boolean yesOrNo(String question) {
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        while (!("y".equalsIgnoreCase(answer)) && !("n".equalsIgnoreCase(answer))) {
            System.out.println(UtilsUI.INVALID_OPTION);
            System.out.println(question);
            answer = scanner.nextLine();
        }
        if ("y".equalsIgnoreCase(answer)) {
            return true;
        } else return !"n".equalsIgnoreCase(answer);
    }


    /**
     * Method that asks user for string input
     * If user character input isn't alphabetic, the user is asked to type again.
     *
     * @return String with user input
     */
    public static String getInputStringAlphabetCharOnly() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid option. Please enter alphabetic characters only.");
            scan.next();
        }
        return scan.next();
    }

    /**
     * Method to read the user input as an Int
     * If its not an int it will print an invalid option message
     * If its a double it will convert it to an int
     *
     * @return value read from the user
     */
    public static int getInputAsInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextDouble()) {
            System.out.println(UtilsUI.INVALID_OPTION);
            scan.next();
        }
        Double option = scan.nextDouble();
        return option.intValue();
    }

    /**
     * Method to read a double value from a user.
     * Will validate input is a double. if it isn't it will print an error message.
     *
     * @return value read from user
     */
    public static Double getInputAsDouble() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println(UtilsUI.INVALID_NUMBER);
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Method to read a double value from a user.
     * Will validate if input is a double and of positive value. if it isn't it will print an error message.
     *
     * @return value read from user
     */
    public static Double getInputAsDoublePositive() {
        double input = -1.0;
        while (input < 0) {
            input = getInputAsDouble();
        }
        return input;
    }

    /**
     * Method to read a double value from a user.
     * Will validate if input is a double and zero or positive value. if it isn't it will print an error message.
     *
     * @return value read from user
     */
    public static Double getInputAsDoubleZeroOrPositive() {
        double input = -1.0;
        while (input <= 0) {
            input = getInputAsDouble();
        }
        return input;
    }


    /**
     * Gets input of a filepath for an XML or JSON file.
     *
     * @return returns a filepath.
     */
    private String getInputPath(String filePath) {
        String result = filePath;
        Scanner scanner = new Scanner(System.in);
        while (!new File(result).exists()) {
            System.out.println("Please enter a valid path");
            result = scanner.next();
        }
        return result;
    }


    /**
     * Reads either a .json ou a .xml path
     *
     * @param input - input of user
     */
    public String getInputPathJsonOrXML(String input) {
        String filePath = "";
        if ((input.endsWith(".json") || (input.endsWith(".xml")))) {
            filePath = getInputPath(input);
        }
        return filePath;

    }


    /**
     * This method will ask for a file location from the user and return it in case the file is
     * a .csv, .xml or .json.
     *
     * @return the file path as a String
     **/
    public String getInputFileLocation() {
        Scanner scanner = new Scanner(System.in);
        UtilsUI.printMessage("Please insert the location of the file you want to import: ");
        String result = scanner.next();
        while (!pathIsValid(result) || !new File(result).exists()) {
            System.out.println("Please enter a valid path.");
            result = scanner.next();
        }
        return result;
    }

    /**
     * This method receives a string of a path and checks if the path is valid
     * (i.e. is either a .xml, .csv, .json file)
     *
     * @return true if the path is valid, false otherwise
     **/
    private boolean pathIsValid(String path) {
        return (path.endsWith(".xml") || path.endsWith(".csv") || path.endsWith(".json"));
    }
}