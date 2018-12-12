package Sprint0.UI;

import Sprint0.Controller.US01Controller;
import Sprint0.Model.TypeAreaList;

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

    public void run(TypeAreaList list) {
        this.active = true;

        getInput();
        updateModel(list);
        displayState();

    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the new Geographic Area Type: ");
        this.mTypeArea = scanner.nextLine();
    }

    private void updateModel(TypeAreaList list) {
        US01Controller ctrl = new US01Controller(list);
        this.mTypeAreaList = ctrl.CreateAndAddTypeAreaToList(mTypeArea);
    }

    private void displayState() {
        this.active = true;
        if (mTypeAreaList) {
            System.out.println("Success, you have inserted a new Type of Geographic Area.");
        } else {
            System.out.println("Unsuccess, you have inserted an invalid or repeated Type of Geographic Area.");
        }
    }

}
