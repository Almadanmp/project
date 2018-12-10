package Sprint0.UI;

import Sprint0.Controller.US05Controller;

import java.util.Scanner;

/**
 * * User Story 05
 * * As a system administrator, I wish to specify the type of reading that a sensor is capable
 * * of registering.
 * This class is responsible for handling user input.
 */

public class US05UI {

    private String typeSensor;
    private String nameSensor;
    private boolean active;
    private boolean typeAdded;

    public US05UI() {
        active = false;
    }

    public void run() {
        this.active = true;
        while (this.active) {
            getInputSensor();
            getInput();
            updateModel();
            displayState();
        }
    }

    private void getInputSensor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the sensor to add the type to: ");
        this.nameSensor = scanner.next();
    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the type of sensor you want to assign to the sensor: ");
        this.typeSensor = scanner.next();
    }

    private void updateModel() {
        US05Controller controller = new US05Controller();
        this.typeAdded = controller.setTypeSensor(nameSensor, typeSensor);
    }

    private void displayState() {
        if (typeAdded) {
            System.out.print("The type has been successfully assigned.");
        } else System.out.print("The type of sensor wasn't added. There's no sensor with that name.");
        active = false;
    }
}
