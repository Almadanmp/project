package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US101Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;
import PT.IPP.ISEP.DEI.Project.Model.RoomList;

import java.util.Scanner;

/**
 * As an Administrator, I want to configure the location of the house.
 **/

public class US101UI {
    private String mHouseDesignation;
    private String mHouseAddress;
    private String mHouseZipCode;
    private double mHouseLat;
    private double mHouseLong;
    private boolean houseAddedResult;
    private boolean active;
    private GeographicArea mGeoArea;
    private String mNameGeoArea;


    public US101UI() {
        active = false;
    }

    public void run(HouseList newHouseListUi, GeographicAreaList newGeoAreaList) {
        this.active = true;
        while (this.active) {
            getInputHouse();
            displayGeoList(newGeoAreaList);
            getGeographicArea();
            displayGeoArea(newGeoAreaList);
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

    private boolean displayGeoList(GeographicAreaList geoAreaList) {
        US101Controller ctrl = new US101Controller(geoAreaList);
        if(ctrl.getGeographicAreaList().getGeographicAreaList().isEmpty()) {
            System.out.println(ctrl.printGeographicAreaListNames());
            return false;
        }
        else {
            System.out.println(ctrl.printGeographicAreaListNames());
            return true;
        }
    }

    private void getGeographicArea() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the GeographicArea of your home: ");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        this.mNameGeoArea = scanner.next();
    }

    private void displayGeoArea(GeographicAreaList list) {
        US101Controller ctrl = new US101Controller(list);
        if (ctrl.validateGeoArea(mNameGeoArea)) {
            System.out.println("Success, you have inserted a valid Geographic Area.");
        } else {
            System.out.println("Unsuccess, you have inserted a non-existing Geographic Area.");
        }
    }

    /**
     * Update the new house configurations
     */
    public void updateHouse() {
        System.out.print("The house named " + mHouseDesignation + " you want to create is in the Geo Area " + mNameGeoArea + " in the address " + mHouseAddress + " with the zipcode " + mHouseZipCode +
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
        GeographicArea geoArea = ctrl.matchGeoArea(mNameGeoArea);
        ctrl.setHouseListToGeoArea(newHouseListUi, geoArea);
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


