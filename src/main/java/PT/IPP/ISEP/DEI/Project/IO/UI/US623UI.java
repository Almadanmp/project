package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US620Controller;
import PT.IPP.ISEP.DEI.Project.Controller.US623Controller;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.HouseList;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * US623UI: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 */
public class US623UI {
    private Scanner mScanner;

    private House mHouse;
    //private String mGeoAreaName;
    private GeographicArea mGeoArea;
    //private GeographicAreaList mGeoAreaList;

    private double mResult620;
    private double mResult623;
    private Date mStartDate;
    private Date mEndDate;
    private int dataYear1;
    private int dataMonth1;
    private int dataDay1;
    private int dataYear2;
    private int dataMonth2;
    private int dataDay2;
    private US623Controller controller623;
    private US620Controller controller620;


    public US623UI() {
        this.mScanner = new Scanner(System.in);
        this.controller623 = new US623Controller();
        this.controller620 = new US620Controller();
    }

    public void run(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        String option;

        getInputGeographicArea(newGeoListUi);
        getInputHouse();
        while (!activeInput) {
            printOptionMessage();
            option = mScanner.next();
            switch (option) {
                case "1":
                    getInputStartDate();
                    updateModelUS620();
                    displayState620();
                    activeInput = true;
                    break;
                case "2":
                    getInputStartDate();
                    getInputEndDate();
                    updateModelUS623();
                    displayState623();
                    activeInput = true;
                    break;
                case "0":
                    return;

                default:
                    System.out.println("Please enter a valid option");
                    break;
            }
        }
    }

    private void printOptionMessage() {
        System.out.println("\nDo you wish to know the average rainfall on:");
        System.out.println("1) A specific day.");
        System.out.println("2) A day interval.");
        System.out.println("0) (Return to main menu)");
    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        Double option;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");


        while (!activeInput) {
            printGaList(newGeoListUi);

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

    private void getInputHouse() {
        boolean activeInput = false;
        Double option;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            printHouseList(mGeoArea);

            while (!mScanner.hasNextDouble()) {
                System.out.println("Please enter a valid option");
                mScanner.next();
            }

            option = mScanner.nextDouble();
            int aux = option.intValue();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                activeInput = true;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }

    private void printGaList(GeographicAreaList newGeoListUi) {
        System.out.println("---------------");

        for (int i = 0; i < newGeoListUi.getGeographicAreaList().size(); i++) {
            GeographicArea aux = newGeoListUi.getGeographicAreaList().get(i);
            System.out.print(i + ") Name: " + aux.getName() + " | ");
            System.out.print("Type: " + aux.getTypeArea().getTypeOfGeographicArea() + " | ");
            System.out.print("Latitude: " + aux.getLocal().getLatitude() + " | ");
            System.out.print("Longitude: " + aux.getLocal().getLongitude());
            System.out.println();
        }
        System.out.println("---------------");
    }

    private void printHouseList(GeographicArea geoArea) {
        System.out.println("---------------");

        for (int i = 0; i < geoArea.getHouseList().getHouseList().size(); i++) {
            House aux = geoArea.getHouseList().getHouseList().get(i);
            System.out.print(i + ") Designation: " + aux.getHouseDesignation() + " | ");
            System.out.print("Address: " + aux.getmAddress() + " | ");
            System.out.print("ZipCode: " + aux.getmZipCode());
            System.out.println();
        }
        System.out.println("---------------");
    }

 /*   private void getInputHouse(GeographicAreaList newGeoListUi) {
        System.out.println("Please Select One Of The Existing Houses on " + mGeoAreaName + "\n" + newHouseListUi.printHouseList());
        String house = mScanner.nextLine();
        mHouse = newHouseListUi.getHouseByDesignation(house);
    }
*/

    private void getInputStartDate() {
        System.out.println("Enter the year:");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            System.out.println("Not a valid year. Try again");
        }
        this.dataYear1 = mScanner.nextInt();

        mScanner.nextLine();
        out.println("\nEnter the Month:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            System.out.println("Not a valid month. Try again");
        }
        this.dataMonth1 = mScanner.nextInt();

        mScanner.nextLine();
        out.println("\nEnter the Day:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            System.out.println("Not a valid day. Try again");
        }
        this.dataDay1 = mScanner.nextInt();
        System.out.println("You entered the date successfully!");
        mScanner.nextLine();
    }

    private void getInputEndDate() {
        System.out.println("Enter the year:");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid year. Try again");
        }
        this.dataYear2 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Month:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid month. Try again");
        }
        this.dataMonth2 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Day:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid day. Try again");
        }
        this.dataDay2 = mScanner.nextInt();
        out.println("You entered the date successfully!");
        mScanner.nextLine();
    }

    private void updateModelUS620() {
        this.mStartDate = controller623.createDate(dataYear1, dataMonth1, dataDay1);
        this.mResult620 = controller620.getTotalRainfallOnGivenDayHouseArea(mHouse, mStartDate);
    }

    private void updateModelUS623() {
        this.mStartDate = controller623.createDate(dataYear1, dataMonth1, dataDay1);
        this.mEndDate = controller623.createDate(dataYear2, dataMonth2, dataDay2);
        this.mResult623 = controller623.getAVGDailyRainfallOnGivenPeriod(mHouse, mStartDate, mEndDate);
    }

    private void displayState623() {
        System.out.print("The Average Temperature is " + mResult623 + "%.");
    }

    private void displayState620() {
        System.out.print("The Average Temperature on " + mHouse.getHouseDesignation() + " that is located on " + mGeoArea.getName() + " on the date " +
                mStartDate + " is " + mResult620 + "%.");
    }
}

