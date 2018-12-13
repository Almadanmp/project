package Sprint0.UI;

import Sprint0.Controller.US03Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;

import java.util.Scanner;

/**
 * * User Story 03
 * As a System Administrator I want to Create a new Geographic Area.
 * This class is responsible for handling user input.
 */
public class US03UI {
    private String nameOfGeoArea;
    private String typeArea;
    private double geoAreaLat;
    private double geoAreaLong;
    private boolean newGeoAreaAdded;
    private boolean active;

    public US03UI() {
        active = false;
    }

    public void run(GeographicAreaList newGeoListUi) {
        this.active = true;
        while (this.active) {
            getInputNameNewArea();
            getInputTypeOfArea();
            getLocalGeoArea();
            updateGeoArea();
            updateModel(newGeoListUi);
            displayState();
        }
    }

    private void getInputNameNewArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the Geographic Area you Want to Add: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name for a Geographic Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.nameOfGeoArea = scanner.next();
    }

    private void getInputTypeOfArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the Type of the Geographic Area you Want to Add: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name a Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.typeArea = scanner.next();
    }

    private void getLocalGeoArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Insert Latitude for the New Geographic Area: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a Valid Input for Latitude. Please insert numbers");
            scanner.next();
        }
        this.geoAreaLat = scanner.nextDouble();
        System.out.print("Please Insert Longitude for the New Geographic Area: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("That's not a Valid Input for Longitude. Please insert numbers");
            scanner.next();
        }
        this.geoAreaLong = scanner.nextDouble();
    }

    private void updateGeoArea() {
        System.out.print("The Geographic Area you want to create is " + nameOfGeoArea + " with the type " + typeArea + " and "
                + " its localization is on " + geoAreaLat + " latitude " + geoAreaLong + " longitude\n");
    }

    private void updateModel(GeographicAreaList newGeoListUi) {
        US03Controller controller = new US03Controller();
        this.newGeoAreaAdded = controller.addNewGeoArea(new GeographicArea(nameOfGeoArea, new TypeArea(typeArea), new Local(geoAreaLat, geoAreaLong)), newGeoListUi);
    }

    private void displayState() {
        if (newGeoAreaAdded) {
            System.out.print("The Geographic Area has been successfully added.");
        } else
            System.out.print("The Geographic Area hasn't been added to the list. There is already an area with the characteristics inputed");
        active = false;
    }
}