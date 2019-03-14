package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.dto.Mapper;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;

/**
 * Utility class that aggregates common methods used by the UI classes.
 */
public class UtilsUI {

    String invalidOption = "Please enter a valid option.";
    String invalidNumber = "Please enter a valid number.";
    static final String RETURNING_TO_MAIN_MENU = "-- Returning to main menu -- \n";
    String invalidRoomList = "Invalid Room List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    String invalidGridList = "Invalid Grid List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    String invalidDeviceList = "Invalid Device List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    String invalidGAList = "Invalid Geographic Area List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    String invalidGATypeList = "Invalid Geographic Area Type List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_SENSOR_LIST = "Invalid Sensor List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    String invalidTypeSensorList = "Invalid Type Sensor List - List is empty.\n" + RETURNING_TO_MAIN_MENU;
    public static final String INVALID_MOTHER_AREA = "The selected House does not have a Geographical Area defined.\n" + RETURNING_TO_MAIN_MENU;
    String invalidProgramList = "Invalid FixedTimeProgram List - The selected Device does not have any Programs defined.\n" + RETURNING_TO_MAIN_MENU;

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.isEmpty();
    }

    boolean houseRoomListIsValid(House house) {
        return !house.isRoomListEmpty();
    }

    boolean roomDTOSensorListIsValid(RoomDTO roomDTO, House house) {
        Mapper mapper = new Mapper();
        Room room = mapper.updateHouseRoom(roomDTO, house);
        return room.getSensorList() != null && !room.isSensorListEmpty();
    }

    boolean roomSensorListIsValid(Room room) {
        return room.getSensorList() != null && !room.isSensorListEmpty();
    }

    boolean houseGridListIsValid(House house) {
        return house.getGridList() != null && !house.isEnergyGridListEmpty();
    }

    boolean gridDeviceListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getDeviceList() != null && !energyGrid.isDeviceListEmpty();
    }

    boolean gridRoomListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getRoomList() != null && !energyGrid.isRoomListEmpty();
    }

    boolean typeAreaListIsValid(TypeAreaList list) {
        return list != null && !list.isEmpty();
    }

    boolean programListIsValid(ProgramList programList) {
        return programList != null && !programList.isEmpty();
    }

    boolean roomDTODeviceListIsValid(RoomDTO roomDTO, House house) {
        Mapper mapper = new Mapper();
        Room room = mapper.updateHouseRoom(roomDTO, house);
        return room.getDeviceList() != null && !room.isDeviceListEmpty();
    }

    boolean roomDeviceListIsValid(Room room) {
        return room.getDeviceList() != null && !room.isDeviceListEmpty();
    }

    boolean deviceLogListIsValid(Device device) {
        return (!device.isLogListEmpty());
    }

    boolean typeSensorListIsValid(TypeSensorList typeSensorList) {
        return typeSensorList != null && !typeSensorList.isEmpty();
    }

    public boolean geographicAreaSensorListIsValid(GeographicArea geographicArea) {
        return geographicArea.getSensorList() != null && !geographicArea.isSensorListEmpty();
    }

    /**
     * Methods for display String results in pretty boxes in console for User.
     * Use method printBox instead of System.out.println for this effect.
     * Use a simple coma ',' in between lines to separate lines instead of '\n'.
     * Old example: System.out.println("Line 1 blablabla \n Line 2 blablabla");
     * New example: printBox("Line 1 blablabla" , "Line 2 blablabla");
     *
     * @param strings for being printed
     */

    public static void printBox(String... strings) {
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
     * @param str
     * @param length
     * @return
     */

    private static String padString(String str, int length) {
        StringBuilder sBuilder = new StringBuilder(str);
        return sBuilder.append(fill(' ', length - str.length())).toString();
    }

    /**
     * Auxiliary printBox method for filling string
     *
     * @param ch
     * @param length
     * @return
     */

    private static String fill(char ch, int length) {
        StringBuilder sBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sBuilder.append(ch);
        }
        return sBuilder.toString();
    }
    // End of methods for printBox

    public static String printMessage(String string) {
        System.out.println(string);
        return string;
    }
}
