package Sprint0.UI;

import Sprint0.Controller.US07Controller;
import Sprint0.Model.GeographicAreaList;

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
            displayGeoList();
            getMotherGeographicArea();
            displayStateMother(newGeoListUi);
            getDaughterGeographicArea();
            displayStateDaughter(newGeoListUi);
            updateGeoArea();
            displayState();
        }
    }

    private void displayGeoList(){
        US07Controller ctrl = new US07Controller(mGeoList);
        System.out.println(ctrl.getGeographicAreaList());
    }

    private void getMotherGeographicArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please type the area to add the new geographic area to: ");
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
        System.out.print("Type the name of the area you want to check if its CONTAINED IN another area: ");
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

    private void updateGeoArea() {
        US07Controller ctrl = new US07Controller(mGeoList);
        System.out.println(ctrl.getGeographicAreaList());
    }


    private void displayState() {
        System.out.print("The Geographic Area " + mNameGeographicAreaDaughter + " is contained in " + mNameGeographicAreaMother + "\n");
        active = false;
    }
}

