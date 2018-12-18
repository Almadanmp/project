package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US101Controller;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;

import java.util.Scanner;

/**
 * As an Administrator, I want to configure the location of the house.
 **/

public class US101UI {
    private String mHouseAddress;
    private String mHouseZipCode;
    private double mHouseLat;
    private double mHouseLong;
    private boolean houseAddedResult;
    private boolean active;
    private Scanner scanner;

    public US101UI() {
        this.scanner = new Scanner(System.in);
    }

    public void run(HouseList newHouseListUi) {
        this.active = true;
        while (this.active) {
            getInputAddress();
            getInputZipCode();
            getHouseLat();
            getHouseLong();
            updateHouse();
            updateModel(newHouseListUi);
            displayState();
        }
    }

    public void getInputAddress() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the adress of the house: ");
        this.mHouseAddress = scanner.next();
    }

    public void getInputZipCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.next();
    }

    public void getHouseLat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLat = scanner.nextDouble();
    }

    public void getHouseLong() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLong = scanner.nextDouble();
    }

    public void updateHouse() {
        System.out.print("The house you want to create is " + mHouseAddress + " with the zipcode " + mHouseZipCode +
                " and its localization is on " + mHouseLat + " latitude " + mHouseLong + " longitude.\n");
    }

    public void updateModel(HouseList newHouseListUi) {
        US101Controller ctrl = new US101Controller();
        this.houseAddedResult = ctrl.addHouseToHouseList(newHouseListUi, mHouseAddress, mHouseZipCode, mHouseLat, mHouseLong);
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


