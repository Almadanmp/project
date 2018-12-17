package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US07Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;

import java.util.Scanner;

public class US07UI {

    private GeographicAreaList mGeoList;
    private String mNameGeographicAreaDaughter;
    private String mNameGeographicAreaMother;
    private boolean active;


    public US07UI() {
        active = false;
    }

    public void run(GeographicAreaList newGeoListUi) {

        this.active = true;
        this.mGeoList = newGeoListUi;
        while (this.active) {
            if(!displayGeoList()) {
                return;
            }
            else {
                getMotherGeographicArea();
                displayStateMother(newGeoListUi);
                getDaughterGeographicArea();
                displayStateDaughter(newGeoListUi);
                updateState();
                displayState();
            }
        }
    }

    private boolean displayGeoList() {
        US07Controller ctrl = new US07Controller(mGeoList);
        if(ctrl.getGeographicAreaList().getGeographicAreaList().isEmpty()) {
            System.out.println(ctrl.printGeographicAreaListNames());
            return false;
        }
        else {
            System.out.println(ctrl.printGeographicAreaListNames());
            return true;
        }
    }

    private void getMotherGeographicArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the GeographicArea that you want to say contains another: ");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        this.mNameGeographicAreaMother = scanner.next();
    }

    private void displayStateMother(GeographicAreaList list) {
        US07Controller ctrl = new US07Controller(list);
        if (ctrl.validateGeoArea(mNameGeographicAreaMother)) {
            System.out.println("Success, you have inserted a valid Geographic Area.");
        } else {
            System.out.println("Unsuccess, you have inserted a non-existing Geographic Area.");
        }
    }

    private void getDaughterGeographicArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the GeographicArea that you want to say is contained in another: ");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        this.mNameGeographicAreaDaughter = scanner.next();
    }

    private void displayStateDaughter(GeographicAreaList list) {
        US07Controller ctrl = new US07Controller(list);
        if (ctrl.validateGeoArea(mNameGeographicAreaDaughter)) {
            System.out.println("Success, you have inserted a valid Geographic Area.");
        } else {
            System.out.println("Unsuccess, you have inserted a non-existing Geographic Area.");
        }
    }

    private void updateState() {
        US07Controller ctrl = new US07Controller(mGeoList);
        GeographicArea daughterArea = ctrl.matchGeoArea(mNameGeographicAreaDaughter);
        GeographicArea motherArea = ctrl.matchGeoArea(mNameGeographicAreaMother);
        ctrl.setMotherArea(daughterArea, motherArea);
    }

    private void displayState() {
        System.out.print("The Geographic Area " + mNameGeographicAreaDaughter + " is contained in " + mNameGeographicAreaMother + "\n");
        active = false;
    }
}

