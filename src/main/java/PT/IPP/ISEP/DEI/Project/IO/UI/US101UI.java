package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US101Controller;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;

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


    public US101UI() {
        active = false;
    }

    public void run(HouseList newHouseListUi) {
        this.active = true;
        while (this.active) {
            getInputHouse();
            updateHouse();
            updateModel(newHouseListUi);
            displayState();
        }
    }

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

    public void updateHouse() {
        System.out.print("The house named " + mHouseDesignation + " you want to create is " + mHouseAddress + " with the zipcode " + mHouseZipCode +
                " and its localization is on " + mHouseLat + " latitude " + mHouseLong + " longitude.\n");
    }

    public void updateModel(HouseList newHouseListUi) {
        US101Controller ctrl = new US101Controller();
        this.houseAddedResult = ctrl.addHouseToHouseList(newHouseListUi, mHouseDesignation, mHouseAddress, mHouseZipCode, mHouseLat, mHouseLong);
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


