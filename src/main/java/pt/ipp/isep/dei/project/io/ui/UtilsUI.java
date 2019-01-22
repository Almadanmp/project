package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.ProgramList;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option.";
    String returningToMainMenu = "-- Returning to main menu -- \n";
    String invalidHouse = "The selected House is not a valid one.\n" + returningToMainMenu;
    String invalidRoomList = "Invalid Room List - List is empty.\n" + returningToMainMenu;
    String invalidGridList = "Invalid Grid List - List is empty.\n" + returningToMainMenu;
    String invalidDeviceList = "Invalid Device List - List is empty.\n" + returningToMainMenu;
    String invalidGAList = "Invalid Geographic Area List - List is empty.\n" + returningToMainMenu;
    String invalidGATypeList = "Invalid Geographic Area Type List - List is empty.\n" + returningToMainMenu;
    String invalidProgramList = "Invalid Program List - List is empty.\n" + returningToMainMenu;

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.getGeographicAreaList().isEmpty();
    }

    boolean houseRoomListIsValid(House house) {
        return house.getRoomList() != null && !house.getRoomList().isEmpty();
    }

    boolean houseDeviceListInvalid(House house) {
        return house.getDeviceList() != null && !house.getRoomList().isEmpty();
    }

    boolean houseGridListIsValid(House house) {
        return house.getEGList() != null && !house.getEGList().getEnergyGridList().isEmpty(); //TODO missing encapsulation
    }

    boolean gridDeviceListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getDeviceList() != null && !energyGrid.getDeviceList().isEmpty();
    }

    boolean gridRoomListIsValid(EnergyGrid energyGrid) {
        return energyGrid.getListOfRooms() != null && !energyGrid.getListOfRooms().getList().isEmpty(); //TODO missing encapsulation
    }

    boolean typeAreaListIsValid(TypeAreaList list) {
        return list != null && !list.getTypeAreaList().isEmpty();
    }

    boolean programListIsValid(ProgramList programList) {
        return programList != null && !programList.getProgramList().isEmpty();
    }

    boolean roomDeviceListIsValid(Room room) {
        return room.getDeviceList() != null && !room.getDeviceList().isEmpty();
    }
}
