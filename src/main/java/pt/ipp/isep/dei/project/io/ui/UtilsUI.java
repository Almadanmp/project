package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.House;


/**
 * Utility class that aggregates common methods used by the UI classes.
 */
class UtilsUI {

    String invalidOption = "Please enter a valid option";

    boolean validateHouse(House house){
        if (house == null) {
            System.out.println("The selected house is not a valid one\n" + "Returning to main menu\n");
            return true;
        }return false;
    }

    boolean validateHouseEGList(House house){
        if (house.getEGList() == null){
            System.out.println("Invalid grid list - List is empty\n" + "Returning to main menu\n");
            return true;
        }return false;
    }
}
