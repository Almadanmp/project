package Sprint0.UI;

import Sprint0.Controller.US02Controller;
import Sprint0.Model.TypeArea;
import Sprint0.Model.TypeAreaList;

import java.util.Scanner;

import java.util.List;


/**
 * User Story 2
 * <p>
 * As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
 * <p>
 * Class responsible for presenting the list.
 */

public class US02UI {

    private boolean active;
    private boolean mTypeAreaList;
    private String mTypeArea;


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
        System.out.println("Area Types List:");
        System.out.println(ctrl.getTypeAreaList());
    }

    private void displayState() {
        Scanner s=new Scanner(System.in);
        this.active = true;
        System.out.println("List finished.");
        s.nextLine();
        active = false;
    }
}