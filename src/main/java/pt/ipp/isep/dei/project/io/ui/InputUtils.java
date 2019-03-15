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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class that aggregates common INPUT methods used by the UI classes.
 */
public class InputUtils {

    private static final String SELECT_ROOMS = "You have chosen the following room: ";
    private static final String SELECT_DEVICES = "Please select one of the existing devices in the selected room: ";

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
            System.out.println(geographicAreaList.buildString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < geographicAreaList.size()) {
                GeographicArea result = geographicAreaList.get(aux);
                System.out.println("You have chosen the following geographic area: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    RoomDTO getHouseRoomDTOByList(House house) {
        Mapper mapper = new Mapper();
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            System.out.println("Please select one of the existing rooms in the house: ");
            System.out.println(house.buildRoomListString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < house.roomListSize()) {
                Room result = house.getRoomByIndex(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
                return mapper.roomToDTO(result);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    Room getHouseRoomByList(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            System.out.println("Please select one of the existing rooms: ");
            System.out.println(house.buildRoomListString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < house.roomListSize()) {
                Room result = house.getRoomByIndex(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
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
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < grid.roomListSize()) {
                Room result = grid.getRoom(aux);
                System.out.println(SELECT_ROOMS);
                System.out.println(result.buildString() + "\n");
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
            System.out.println(SELECT_DEVICES);
            System.out.println(grid.buildDeviceListString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < grid.getNumberOfDevices()) {
                Device result = grid.getDeviceByIndex(aux);
                System.out.println("You have chosen the following device: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    FixedTimeProgram getSelectedProgramFromDevice(Programmable device) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            ProgramList deviceProgramList = device.getProgramList();
            System.out.println("Please select one of the existing program in the selected program List: ");
            System.out.println(deviceProgramList.buildString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < deviceProgramList.size()) {
                FixedTimeProgram result = (FixedTimeProgram) deviceProgramList.get(aux);
                System.out.println("You have chosen the following program: ");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    Device getInputRoomDTODevicesByList(RoomDTO room, House house) {
        RoomConfigurationController controller = new RoomConfigurationController();
        InputUtils inputUtils = new InputUtils();
        Mapper mapper = new Mapper();
        UtilsUI utils = new UtilsUI();
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(controller.buildDeviceListString(mapper.updateHouseRoom(room, house)));
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < controller.getDeviceListSize(room, house)) {
                Device result = controller.getDeviceByIndex(room, house, aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    Device getInputRoomDevicesByList(Room room) {
        RoomConfigurationController controller = new RoomConfigurationController();
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        while (true) {
            System.out.println(SELECT_DEVICES);
            System.out.println(controller.buildDeviceListString(room));
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < room.getDeviceListSize()) {
                Device result = room.getDeviceByIndex(aux);
                System.out.println("You have chosen the following device:");
                System.out.println(result.buildString() + "\n");
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
            int aux = this.getInputAsInt();
            if (aux >= 0 && aux < house.energyGridListSize()) {
                EnergyGrid result = house.getEnergyGridByIndex(aux);
                System.out.println("You have chosen the following grid:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        }
    }

    TypeSensor getInputSensorTypeByList(TypeSensorList typeSensorList) {
        while (true) {
            UtilsUI utils = new UtilsUI();
            InputUtils inputUtils = new InputUtils();
            System.out.println("Please select a type of sensor from the list:");
            System.out.println(typeSensorList.buildString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < typeSensorList.size()) {
                TypeSensor result = typeSensorList.get(aux);
                System.out.println("You have chosen the following sensor type:");
                System.out.println(result.buildString() + "\n");
                return result;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }


    DeviceType getInputDeviceTypeByList(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        List<DeviceType> deviceTypeList = house.getDeviceTypeList();
        while (true) {
            System.out.println("Please select one of the device types: ");
            System.out.println(house.buildDeviceTypeString());
            int aux = inputUtils.getInputAsInt();
            if (aux >= 0 && aux < house.deviceTypeListSize()) {
                DeviceType result = deviceTypeList.get(aux);
                System.out.println("You have chosen the following device type:");
                System.out.println(result.getDeviceType() + "\n");
                return result;

            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    boolean yesOrNo(String question) {
        String answer = "";
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
     * Method that asks user for string input
     * If user character input isn't alphabetic, the user is asked to type again.
     *
     * @return String with user input
     */
    String getInputStringAlphabetCharOnly() {
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
    int getInputAsInt() {
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
     * Method to read a double value from a user.
     * Will validate input is a double. if it isn't it will print an error message.
     *
     * @return value read from user
     */
    Double getInputAsDouble() {
        UtilsUI utils = new UtilsUI();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println(utils.invalidNumber);
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
    Double getInputAsDoublePositive() {
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
    Double getInputAsDoubleZeroOrPositive() {
        double input = -1.0;
        while (input <= 0) {
            input = getInputAsDouble();
        }
        return input;
    }

    /**
     * Method will read a group of values from user and return a date (year, month, day, hour and
     * minute). It will only accept valid numbers.
     *
     * @return date introduced by user
     */
    /**
     * Method will read a group of values from user and return a date (year, month, day, hour and
     * minute). It will only accept valid numbers.
     *
     * @return date introduced by user
     */
    Date getInputYearMonthDay() {
        Scanner scan = new Scanner(System.in);
        int year = getInputYear();
        boolean isLeapyear = new GregorianCalendar().isLeapYear(year);
        int month = getInputMonth();
        int day = getInputDay(isLeapyear, month);
        Date date = new GregorianCalendar(year, month, day).getTime();

        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateResultFormatted = formatter.format(date);

        System.out.println(("You have chosen the following date:\n") + dateResultFormatted + "\n");
        scan.nextLine();
        return date;
    }

    /**
     * Method will read a group of values from user and return a date (year, month, day, hour and
     * minute). It will only accept valid numbers.
     *
     * @return date introduced by user
     */
    Date getInputYearMonthDayHourMin() {
        int year = getInputYear();
        boolean isLeapYear = new GregorianCalendar().isLeapYear(year);
        int month = getInputMonth();
        int day = getInputDay(isLeapYear, month);
        int hour = getInputHour();
        int minute = getInputMinute();
        Date date = new GregorianCalendar(year, month, day, hour, minute).getTime();
        System.out.println(("You have chosen the following date:\n") + date.toString() + "\n");
        return date;
    }

    /**
     * Method will ask the user to introduce a year and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * values from the gregorian calendar
     *
     * @return int of the year introduced by user
     */
    private int getInputYear() {
        Scanner scan = new Scanner(System.in);
        int year = -1;
        while (year <= 1581) { //Gregorian Calendar was introduced in 1582, so was the concept of leap year
            year = getInputDateParameter(scan, "year");
            scan.nextLine();
        }
        return year;
    }

    /**
     * Method will ask the user for a month and will return an int of that value subtracted by one
     *
     * @return int of the month introduced by user
     */
    private int getInputMonth() {
        Scanner scan = new Scanner(System.in);
        int month = -1;
        while (month <= -1 || month > 11) { // -1 e 11 porque depois se subtrai um valor
            month = getInputDateParameter(scan, "month") - 1;
            scan.nextLine();
        }
        return month;
    }

    /**
     * Method will ask the user to introduce a day and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values
     *
     * @return int of the day introduced by user
     */
    private int getInputDay(boolean isLeapyear, int month) {
        if (month == 1) {
            return getInputFebruaryDay(isLeapyear);
        } else {
            return getInputNotFebruaryDay(month);
        }
    }

    /**
     * Method will receive a boolean and ask the user for a day and will return an int
     * that corresponds to the number introduced by user. The boolean is true in case of leap
     * year and the user can only introduce a number from 1 to 29. Otherwise, it can only a number
     * from 0 to 28.
     *
     * @return int of the day introduced by user
     */
    private int getInputFebruaryDay(boolean isLeapyear) {
        Scanner scan = new Scanner(System.in);
        int day = -1;
        if (isLeapyear) {
            while (day < 1 || day > 29) {
                day = getInputDateParameter(scan, "day");
                scan.nextLine();
            }
            return day;
        }
        while (day < 1 || day > 28) {
            day = getInputDateParameter(scan, "day");
            scan.nextLine();
        }
        return day;
    }

    /**
     * Method will receive an int of a month and ask the user for a day. It will return an int
     * that corresponds to the number introduced by user. The user can only introduce valid days
     * for that month (January - 0 to 31, April - 0 to 30).
     *
     * @return int of the day introduced by user
     */
    private int getInputNotFebruaryDay(int month) {
        Scanner scan = new Scanner(System.in);
        int day = -1;
        if (isJanuaryMarchMay(month) || isJulyAugust(month) || isOctoberDecember(month)) {
            while (day < 1 || day > 31) {
                day = getInputDateParameter(scan, "day");
                scan.nextLine();
            }
            return day;
        }
        while (day < 1 || day > 30) {
            day = getInputDateParameter(scan, "day");
            scan.nextLine();
        }
        return day;
    }

    //The next three methods were created because of a code smell

    /**
     * Method that checks if the month given is January, March or May, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    private boolean isJanuaryMarchMay(int month) {
        return month == 0 || month == 2 || month == 4;
    }

    /**
     * Method that checks if the month given is July or August, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    private boolean isJulyAugust(int month) {
        return month == 6 || month == 7;
    }

    /**
     * Method that checks if the month given is October or December, returning
     * true in case it is, false in case it is not.
     *
     * @param month month to test
     */
    private boolean isOctoberDecember(int month) {
        return month == 9 || month == 11;
    }

    /**
     * Method will ask the user for an hour and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values (0 to 23).
     *
     * @return int of the hour introduced by user
     */
    private int getInputHour() {
        Scanner scan = new Scanner(System.in);
        int hour = -1;
        while (hour < 0 || hour > 23) {
            hour = getInputDateParameter(scan, "hour");
            scan.nextLine();
        }
        return hour;
    }

    /**
     * Method will ask the user to introduce a minute and will return an int
     * that corresponds to the number introduced by user. User can only introduce
     * valid values (0 to 59).
     *
     * @return int of the minute introduced by user
     */
    private int getInputMinute() {
        Scanner scan = new Scanner(System.in);
        int minute = -1;
        while (minute < 0 || minute > 59) {
            minute = getInputDateParameter(scan, "minute");
            scan.nextLine();
        }
        return minute;
    }

    /**
     * Method to get a date as an int.
     *
     * @param scan     the scanner to read input
     * @param dataType the type of date to read (year, month or day)
     * @return value read from the user
     */
    private int getInputDateParameter(Scanner scan, String dataType) {
        System.out.println("Enter a " + dataType + ":");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid " + dataType + ". Try again");
        }
        return scan.nextInt();
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
    String startAndPromptPath() {
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