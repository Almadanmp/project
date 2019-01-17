package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option";
    String invalidHouse = "The selected House is not a valid one.\n\" + \"Returning to main menu\n"; //Serão usados
    String invalidGridList = "Invalid Grid List - List is empty.\n" + "Returning to main menu\n"; //Serão usados
    String invalidGAList = "Invalid Geographic Area List - List is empty\n" + "Returning to main menu\n";
    String invalidGATypeList = "Invalid list of Geographic Area types. List is empty\n" + "Returning to main menu\n";

    boolean houseIsNull(House house) {
        return house == null;
    }

    boolean houseEGListIsNull(House house) {
        return house.getEGList() == null;
    }

    boolean geographicAreaListIsValid(GeographicAreaList geographicAreaList) {
        return geographicAreaList != null && !geographicAreaList.getGeographicAreaList().isEmpty();
    }

    boolean typeAreaIsValid(TypeAreaList list) {
        return list != null && !list.getTypeAreaList().isEmpty();
    }
}
