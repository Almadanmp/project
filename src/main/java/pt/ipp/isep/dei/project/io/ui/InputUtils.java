package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
        while (true) {
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
            }
        }
    }

    Room getHouseRoomByList(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
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
            }
        }
    }

    Room getGridRoomByList(EnergyGrid grid) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
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
            }
        }
    }

    Device getGridDevicesByList(EnergyGrid grid) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
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
            }
        }
    }

    Program getSelectedProgramFromDevice(Device device) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            ProgramList deviceProgramList = device.getProgramList(); //getAttributeValue("programList");
            System.out.println("Please select one of the existing Programs in the selected Program List to alter: ");
            System.out.println(deviceProgramList.buildProgramListString());
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < deviceProgramList.getProgramList().size()) {
                Program result = deviceProgramList.getProgramList().get(aux);
                String stringRequestProgram = "You have chosen the following Program: ";
                System.out.println(stringRequestProgram);
                System.out.println(result.buildProgramString());
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    Device getInputRoomDevicesByList(Room room) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            System.out.println(room.buildDeviceListString());
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < room.getDeviceList().size()) {
                Device result = room.getDeviceList().get(aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildDeviceString());
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    EnergyGrid getInputGridByList(House house) {
        EnergyGridSettingsController controller = new EnergyGridSettingsController();
        UtilsUI utilsUI = new UtilsUI();
        while (true) {
            System.out.println("Please select one of the existing grids on the selected house: ");
            System.out.println(controller.buildGridListString(house));
            int aux = this.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getEGListObject().getEnergyGridList().size()) {
                return house.getEGListObject().getEnergyGridList().get(aux);
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        }
    }

    TypeSensor getInputSensorTypeByList(List<TypeSensor> typeSensorList) {
        while (true) {
            UtilsUI utils = new UtilsUI();
            InputUtils inputUtils = new InputUtils();
            RoomConfigurationController ctrl = new RoomConfigurationController();
            System.out.println("Please select a Type of Sensor from the list: ");
            System.out.println(ctrl.buildTypeListString(typeSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < typeSensorList.size()) {
                System.out.println("You have chosen the following Type: " + typeSensorList.get(aux).getName());
                return typeSensorList.get(aux);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    DeviceType getInputDeviceTypeByList(House house) throws IOException {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        List<DeviceType> deviceTypeList = house.getmDeviceTypeList();
        while (true) {
            System.out.println("Please select one of the device Types: ");

            System.out.println(house.getDeviceTypes());
            System.out.println(house.buildTypeListString(deviceTypeList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getDeviceTypes().size()) {

                return deviceTypeList.get(aux);

            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }


    Sensor getInputRoomSensorByList(Room room) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        HouseMonitoringController controller = new HouseMonitoringController();
        while (true) {
            System.out.println("Please select one of the existing Sensors on the selected Room: ");
            System.out.println(controller.buildRoomSensorListString(room));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < room.getSensorList().getSensorList().size()) {
                return room.getSensorList().getSensorList().get(aux);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    boolean yesOrNo(String answer, String question) {
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
     *
     * @return value read from user
     */
    Double getInputAsDouble() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public Date getInputDate() {
        Scanner scan = new Scanner(System.in);
        int year = getInputDateAsInt(scan, "year");
        scan.nextLine();
        int month = getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();
        int day = getInputDateAsInt(scan, "day");
        scan.nextLine();
        int hour = getInputDateAsInt(scan, "hour");
        scan.nextLine();
        int minute = getInputDateAsInt(scan, "minute");
        scan.nextLine();
        return new GregorianCalendar(year, month, day, hour, minute).getTime();
    }

}