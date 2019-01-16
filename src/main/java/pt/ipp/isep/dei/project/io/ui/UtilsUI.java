package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option";
    String invalidHouse = "The selected House is not a valid one.\n\" + \"Returning to main menu\n";
    String invalidGridList = "Invalid Grid List - List is empty.\n" + "Returning to main menu\n";
    String invalidGAList = "Invalid Geographic Area List - List is empty\n" + "Returning to main menu\n";
    String invalidGATypeList = "Invalid list of Geographic Area types. List is empty\n" + "Returning to main menu\n";

    boolean validateHouse(House house) {
        if (house == null) {
            return true;
        }
        return false;
    }

    boolean validateHouseEGList(House house) {
        if (house.getEGList() == null) {
            return true;
        }
        return false;
    }

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        if (geographicAreaList == null || geographicAreaList.getGeographicAreaList().isEmpty()) {
            return false;
        }
        return true;
    }

    boolean typeAreaIsValid(TypeAreaList list) {
        if (list == null || list.getTypeAreaList().isEmpty()) {
            return false;
        }
        return true;
    }
}
