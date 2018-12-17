package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US03Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import java.util.Scanner;

/**
 * * User Story 03
 * As a System Administrator I want to Create a new Geographic Area.
 * <p>
 * This class is responsible for handling user input.
 * It calls the respective US03 controller.
 */
public class US03UI {
    private String nameOfGeoArea;
    private String typeArea;
    private double geoAreaLat;
    private double geoAreaLong;
    private boolean areaAddedResult;
    private boolean active;
    private Scanner scanner;

    public US03UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * US 03 view - run method.
     * Reads necessary user inputs .
     * Calls controller to create the GA and add it to the list (received as a parameter).
     * Displays result information to the user.
     *
     * @param newGeoListUi the list where new GA shall be added to
     */
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
        this.nameOfGeoArea = readInputString("name");
    }

    private void getInputTypeOfArea() {
        this.typeArea = readInputString("Type Area");
    }

    private void getLocalGeoArea() {
        this.geoAreaLat = readInputNumber("Latitude");
        this.geoAreaLong = readInputNumber("Longitude");
    }

    private void updateGeoArea() {
        System.out.print("The Geographic Area you want to create is " + nameOfGeoArea + " with the type " + typeArea +
                " and its localization is on " + geoAreaLat + " latitude " + geoAreaLong + " longitude.\n");
    }

    private void updateModel(GeographicAreaList newGeoListUi) {
        US03Controller controller = new US03Controller();
        this.areaAddedResult = controller.addNewGeoAreaToList(newGeoListUi, nameOfGeoArea, typeArea, geoAreaLat, geoAreaLong);
    }

    private void displayState() {
        if (areaAddedResult) {
            System.out.print("The Geographic Area has been successfully added.");
        } else
            System.out.print("The Geographic Area hasn't been added to the list. " +
                    "There is already an area with those input values.");
        active = false;
    }

    private String createInputMsg(String inputType) {
        return "Please Insert " + inputType + " for the New Geographic Area: ";
    }

    private String createInvalidStringMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Alphabetic Characters";
    }

    private String createInvalidNumberMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Numbers";
    }

    private String readInputString(String inputType) {
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println(createInvalidStringMsg(inputType));
            scanner.next();
        }
        return scanner.next();
    }

    private double readInputNumber(String inputType) {
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNextDouble()) {
            System.out.println(createInvalidNumberMsg(inputType));
            scanner.next();
        }
        return scanner.nextDouble();
    }
}