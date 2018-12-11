package Sprint0.UI;

import Sprint0.Controller.US03Controller;
import Sprint0.Model.GeographicArea;
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
    public boolean newGeoAreaAdded;


    private boolean active;

    public US03UI() {
        active = false;
    }


    public void run() {
        this.active = true;
        while (this.active) {
            getInputNameNewArea();
            getInputTypeOfArea();
            getLocalGeoArea();

            displayState();
        }
    }

    private void getInputNameNewArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the Geographic Area you Want to Add: ");
        this.nameOfGeoArea = scanner.next();
    }

    private void getInputTypeOfArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the Geographic Area you Want to Add: ");
        this.typeArea = scanner.next();
    }

    private void getLocalGeoArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Insert Latitude for the New Geografic Area: ");
        this.geoAreaLat = scanner.nextDouble();
        System.out.print("Please Insert Longitude for the New Geografic Area: ");
        this.geoAreaLong = scanner.nextDouble();
    }

    private void updateModel() {
        US03Controller controller = new US03Controller();
        TypeArea typ1 = controller.createType(typeArea);
        GeographicArea newGeo = new GeographicArea(nameOfGeoArea, typ1, new Local(geoAreaLat,geoAreaLong));
        this.newGeoAreaAdded = controller.addNewGeoArea(newGeo);
    }

    private void displayState() {
        if (newGeoAreaAdded) {
            System.out.print("The Geographic Area has been successfully added.");
        } else
            System.out.print("The Geographic Area hasn't been added to the list. There is already an area with the characteristics inputed");
        active = false;
    }

}