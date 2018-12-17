package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US05Controller;
import PT.IPP.ISEP.DEI.Project.Model.SensorList;

import java.util.Scanner;

/**
 * * User Story 05
 * * As a system administrator, I wish to specify the type of reading that a sensor is capable
 * * of registering.
 * This class is responsible for handling user input.
 */

public class US05UI {

    private String mTypeSensor;
    private String mNameSensor;
    private boolean mActive;
    private boolean mTypeAdded;

    public US05UI() {
        mActive = false;
    }

    public void run(SensorList list) {
        this.mActive = true;
        while (this.mActive) {
            getInputSensor();
            getInput();
            updateModel(list);
            displayState();
        }
    }

    private void getInputSensor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the sensor to add the type to: ");
        this.mNameSensor = scanner.next();
    }

    private void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the type of sensor you want to assign to the sensor: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name of Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mTypeSensor = scanner.next();
    }

    private void updateModel(SensorList list) {
        US05Controller controller = new US05Controller(list);
        this.mTypeAdded = controller.setTypeSensor(mNameSensor, mTypeSensor);
    }

    private void displayState() {
        if (mTypeAdded) {
            System.out.print("The type has been successfully assigned.");
        } else System.out.print("The type of sensor wasn't added. There's no sensor with that name.");
        mActive = false;
    }
}
