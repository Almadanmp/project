package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US01Controller;
import pt.ipp.isep.dei.project.model.TypeAreaList;

import java.util.Scanner;

/**
 * User Story 01
 * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
 */

public class US01UI {
    private boolean active;
    private boolean mTypeAreaList;
    private String mTypeArea;


    public US01UI() {
        active = false;
    }

    public void runUS01UI(TypeAreaList list) {
        this.active = true;

            getInput();
            updateModel(list);
            displayState();

    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the new Geographic Area Type: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name a Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mTypeArea = scanner.next();
    }

    private void updateModel(TypeAreaList list) {
        US01Controller ctrl = new US01Controller(list);
        this.mTypeAreaList = ctrl.createAndAddTypeAreaToList(mTypeArea);
    }

    private void displayState() {
        if (mTypeAreaList) {
            System.out.println("Success, you have inserted a new Type of Geographic Area.");
        } else {
            System.out.println("Unsuccess, you have inserted an invalid or repeated Type of Geographic Area.");
            this.active = false;
        }
    }

}
