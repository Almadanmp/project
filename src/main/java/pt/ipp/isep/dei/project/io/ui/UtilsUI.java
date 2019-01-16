package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option";

    boolean validateHouse(House house) {
        if (house == null) {
            System.out.println("The selected House is not a valid one\n" + "Returning to main menu\n");
            return true;
        }
        return false;
    }

    boolean validateHouseEGList(House house) {
        if (house.getEGList() == null) {
            System.out.println("Invalid Grid List - List is empty\n" + "Returning to main menu\n");
            return true;
        }
        return false;
    }

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        if (geographicAreaList == null || geographicAreaList.getGeographicAreaList().isEmpty()) {
            System.out.println("Invalid Geographic Area List - List is empty");
            return false;
        }
        return true;
    }

    boolean typeAreaIsValid(TypeAreaList list) {
        if (list == null || list.getTypeAreaList().isEmpty()) {
            System.out.println("Invalid list of Geographic Area types. List is empty");
            return false;
        }
        return true;
    }
}
