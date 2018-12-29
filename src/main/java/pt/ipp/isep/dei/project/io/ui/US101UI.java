package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US101Controller;
import pt.ipp.isep.dei.project.controller.US623Controller;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.HouseList;

import java.util.Scanner;

/**
 * As an Administrator, I want to configure the location of the house.
 **/

public class US101UI {
    private Scanner mScanner;
    private String mHouseDesignation;
    private String mHouseAddress;
    private String mHouseZipCode;
    private double mHouseLat;
    private double mHouseLong;
    private boolean houseAddedResult;
    private boolean active;
    private GeographicArea mGeoArea;
    private String mNameGeoArea;
    private US623Controller controller623;


    public US101UI() {
        active = false;
        this.mScanner = new Scanner(System.in);
    }

    public void run(HouseList newHouseListUi, GeographicAreaList newGeoAreaList) {
        this.active = true;
        while (this.active) {
            getInputHouse();
            getInputGeographicArea(newGeoAreaList);
            updateHouse();
            updateModel(newHouseListUi, newGeoAreaList);
            displayState();
        }
    }

    /**
     * Get the house definitions by the admin: designation, address, zipcode, local (latitude + longitude)
     */
    public void getInputHouse() {

        //gethousedesignation
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the name of the house: ");
        this.mHouseDesignation = scanner.nextLine();


        //gethouseaddress
        System.out.print("Please, type the address of the house: ");
        this.mHouseAddress = scanner.nextLine();


        //getzipcode
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.nextLine();


        //getlatitude
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLat = scanner.nextDouble();


        //getlongitude
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLong = scanner.nextDouble();

    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        this.controller623 = new US623Controller();
        boolean activeInput = false;
        Double option;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");


        while (!activeInput) {
            controller623.printGAList(newGeoListUi);

            while (!mScanner.hasNextDouble()) {
                System.out.println("Please enter a valid option");
                mScanner.next();
            }

            option = mScanner.nextDouble();
            int aux = option.intValue();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }


    /**
     * Update the new house configurations
     */
    public void updateHouse() {
        System.out.print("The house named " + mHouseDesignation + " you want to create is in the Geo Area " + mGeoArea.getName() + " in the address " + mHouseAddress + " with the zipcode " + mHouseZipCode +
                " and its localization is on " + mHouseLat + " latitude " + mHouseLong + " longitude.\n");
    }

    /**
     * Add the new House to the House list
     *
     * @param newHouseListUi
     */
    public void updateModel(HouseList newHouseListUi, GeographicAreaList geoList) {
        US101Controller ctrl = new US101Controller(geoList);
        this.houseAddedResult = ctrl.addHouseToHouseList(newHouseListUi, mHouseDesignation, mHouseAddress, mHouseZipCode, mHouseLat, mHouseLong);
        ctrl.setHouseListToGeoArea(newHouseListUi, mGeoArea);
    }


    public void displayState() {
        if (houseAddedResult) {
            System.out.print("The House has been successfully configured.");
        } else
            System.out.print("The House hasn't been configured. " +
                    "There is already an house with those input values.");
        active = false;
    }
}


