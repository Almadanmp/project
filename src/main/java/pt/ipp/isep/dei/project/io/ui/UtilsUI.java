package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option";
    String returningToMainMenu = "Returning to main menu\n";
    String invalidHouse = "The selected House is not a valid one.\n" + returningToMainMenu; //Serão usados
    String invalidGridList = "Invalid Grid List - List is empty.\n" + returningToMainMenu; //Serão usados
    String invalidGAList = "Invalid Geographic Area List - List is empty\n" + returningToMainMenu;
    String invalidGATypeList = "Invalid list of Geographic Area types. List is empty\n" + returningToMainMenu;

    boolean houseIsNull(House house) {
        return house == null;
    }

    boolean houseEGListIsNull(House house) {
        return house.getEGList() == null;
    }

    boolean gridIsNull(EnergyGrid energyGrid) {return energyGrid == null;}

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.getGeographicAreaList().isEmpty();
    }

    boolean houseRoomListInvalid(House house) {
        return house.getRoomList() != null && !house.getRoomList().isEmpty();
    }

    boolean houseDeviceListInvalid(House house) {
        return house.getDeviceList() != null && !house.getRoomList().isEmpty();
    }

    boolean typeAreaIsValid(TypeAreaList list) {
        return list != null && !list.getTypeAreaList().isEmpty();
    }
}
