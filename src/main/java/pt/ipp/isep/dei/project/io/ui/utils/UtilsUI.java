package pt.ipp.isep.dei.project.io.ui.utils;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

/**
 * Utility class that aggregates common methods used by the UI classes.
 */
public class UtilsUI {

    public static final String INVALID_OPTION = "Please enter a valid option.";
    static final String INVALID_NUMBER = "Please enter a valid number.";
    private static final String RETURNING_TO_MAIN_MENU = "-- Returning to main menu -- \n";
    public static final String INVALID_ROOM_LIST = "Invalid Room List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_GRID_LIST = "Invalid Grid List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_DEVICE_LIST = "Invalid Device List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public  static final String INVALID_GA_LIST = "Invalid Geographic Area List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public  static final String INVALID_GA_TYPE_LIST = "Invalid Geographic Area Type List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_SENSOR_LIST = "Invalid Sensor List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_TYPE_SENSOR_LIST = "Invalid Type Sensor List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_MOTHER_AREA = "The selected House does not have a Geographical Area defined.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_PROGRAM_LIST = "Invalid FixedTimeProgram List - The selected Device does not have any Programs defined.\n" + RETURNING_TO_MAIN_MENU;


    public  boolean roomDTOSensorListIsValid(RoomDTO roomDTO, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getSensorList() != null && !room.isSensorListEmpty();
    }

    public   boolean roomDTODeviceListIsValid(RoomDTO roomDTO, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getDeviceList() != null && !room.isDeviceListEmpty();
    }

    /**
     * Methods for display String results in pretty boxes in console for User.
     * Use method printBox instead of System.out.println for this effect.
     * Use a simple coma ',' in between lines to separate lines instead of '\n'.
     * Old example: System.out.println("Line 1 blablabla \n Line 2 blablabla");
     * New example: printBox("Line 1 blablabla" , "Line 2 blablabla");
     *
     * @param strings for being printed
     * @author Nuno
     */
    public  static void printBox(String... strings) {
        int maxBoxWidth = getStringMaxLength(strings);
        String line = " " + fill('-', maxBoxWidth + 2) + " ";
        System.out.println(line);
        for (String str : strings) {
            System.out.printf("| %s |%n", padString(str, maxBoxWidth));
        }
        System.out.println(line);
    }

    /**
     * Auxiliary method for printBox for getting String length
     *
     * @param strings to know the length of
     * @return string length
     * @author Nuno
     */
    private static int getStringMaxLength(String... strings) {
        int length = Integer.MIN_VALUE;
        for (String str : strings) {
            length = Math.max(str.length(), length);
        }
        return length;
    }

    /**
     * Auxiliary method for printBox for padding spaces for printBox method
     *
     * @param str string
     * @param length for padding
     * @return padded string
     * @author Nuno
     */
    private static String padString(String str, int length) {
        StringBuilder string = new StringBuilder(str);
        return string.append(fill(' ', length - str.length())).toString();
    }

    /**
     * Auxiliary printBox method for filling string
     *
     * @param ch for filling/drawing box
     * @param length for filling
     * @return filled string with char
     * @author Nuno
     */
    private static String fill(char ch, int length) {
        StringBuilder sBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sBuilder.append(ch);
        }
        return sBuilder.toString();
    }
    // End of methods for printBox

    /**
     *
     * @param string is the message we want to print.
     * @return string is the message we want to print.
     * @author André Rua
     */
    public static String printMessage(String string) {
        System.out.println(string);
        return string;
    }
}
