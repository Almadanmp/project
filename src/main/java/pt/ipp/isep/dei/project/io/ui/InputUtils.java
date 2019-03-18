package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
public class InputUtils {

    private static final String SELECT_ROOMS = "You have chosen the following room: ";
    private static final String SELECT_DEVICES = "Please select one of the existing devices in the selected room: ";

    static void returnToMenu(Scanner scanner) {
        String pressEnter = "\nPress ENTER to return.";
        System.out.println(pressEnter);
        scanner.nextLine();
    }

    static GeographicArea getGeographicAreaByList(GeographicAreaList geographicAreaList) {
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

    static RoomDTO getHouseRoomDTOByList(House house) {
        Mapper mapper = new Mapper();
        while (true) {
            System.out.println("Please select one of the existing rooms in the house: ");
            System.out.println(house.buildRoomListString());
            int aux = getInputAsInt();
            if (aux >= 0 && aux < house.roomListSize()) {
                Room result = house.getRoomByIndex(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
                return mapper.roomToDTO(result);
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    static Room getHouseRoomByList(House house) {
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

    static Room getGridRoomByList(EnergyGrid grid) {
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

    static Device getGridDevicesByList(EnergyGrid grid) {
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

    static FixedTimeProgram getSelectedProgramFromDevice(Programmable device) {
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

    static Device getInputRoomDTODevicesByList(RoomDTO room, House house) {
        RoomConfigurationController controller = new RoomConfigurationController();
        Mapper mapper = new Mapper();
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(controller.buildDeviceListString(mapper.updateHouseRoom(room, house)));
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

    static Device getInputRoomDevicesByList(Room room) {
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

    static EnergyGrid getInputGridByList(House house) {
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

    static TypeSensor getInputSensorTypeByList(TypeSensorList typeSensorList) {
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

    static Sensor getInputSensorByList(SensorList sensorList) {
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


    static DeviceType getInputDeviceTypeByList(House house) {
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

    static boolean yesOrNo(String question) {
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
    static String getInputStringAlphabetCharOnly() {
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
    static int getInputAsInt() {
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
    static Double getInputAsDouble() {
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
    static Double getInputAsDoublePositive() {
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
    static Double getInputAsDoubleZeroOrPositive() {
        double input = -1.0;
        while (input <= 0) {
            input = getInputAsDouble();
        }
        return input;
    }

    /**
     * Gets input of a filepath. For now, this file is either a .csv file or a .json file, since those are the only kind
     * of files the program is ready to manipulate.
     *
     * @return returns a filepath.
     */
    String getInputJSONPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the location of the file you want to import:");
        String result = scanner.next();
        while (!(result.endsWith(".json")) || !new File(result).exists()) {
            System.out.println("Please enter a valid json path");
            result = scanner.next();
        }
        return result;
    }

    /**
     * Method to get the path to the file from user input, only works if the file is a .csv file and it actually exists.
     *
     * @author Andre (US20)
     */
    String getInputCSVPath() {
        Scanner scanner = new Scanner(System.in);
        UtilsUI.printMessage("Please insert the location of the CSV file");
        String csvFileLocation = scanner.next();
        while (!csvFileLocation.endsWith(".csv") || !new File(csvFileLocation).exists()) {
            UtilsUI.printMessage("Please enter a valid  CSV path");
            csvFileLocation = scanner.next();

        }
        return csvFileLocation;
    }
}