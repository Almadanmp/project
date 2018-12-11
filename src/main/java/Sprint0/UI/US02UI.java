package Sprint0.UI;

import Sprint0.Controller.US02Controller;
import Sprint0.Model.TypeArea;
import java.util.List;

/**
 * User Story 2
 * <p>
 * As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
 * <p>
 * Class responsible for presenting the list.
 */

public class US02UI {

    private List<TypeArea> typeList;
    private boolean active;
    private US02Controller controller;


    public US02UI() {
        active = false;
    }

    public void run() {
        this.active = true;
        while (this.active) {
            updateModel();
            displayState();
        }
    }

    private void updateModel() {
        US02Controller controller = new US02Controller();
        this.typeList = controller.getTypeAreaList();
    }

    private void displayState() {
        if(typeList == null) {
            System.out.print("Type List hasn't been created/initialized.");
        }
        else {
            if (typeList.isEmpty()) {
                System.out.print("No previously defined area types.");
            } else {
                System.out.print("Previously defined area types:");
                for (int i = 0; i < typeList.size(); i++) {
                    System.out.println(typeList.get(i));
                }
            }
        }
        active = false;
    }
}