package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US02Controller;
import pt.ipp.isep.dei.project.model.TypeAreaList;



/**
 * User Story 2
 * <p>
 * As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
 * <p>
 * Class responsible for presenting the list.
 */

public class US02UI {

    private boolean active;

    public US02UI() {
        active = false;
    }

    public void run(TypeAreaList list) {
        this.active = true;
        while (this.active) {
            updateModel(list);
            displayState();
        }
    }

    private void updateModel(TypeAreaList list) {
        US02Controller ctrl = new US02Controller(list);
        System.out.println(ctrl.getTypeAreaList());
    }

    private void displayState() {
        this.active = true;
        System.out.println("\nList finished.");
        active = false;
    }
}