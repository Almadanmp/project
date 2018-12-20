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
    private boolean active;
    private Scanner mScanner;
    private House mHouse;
    private String mGeoAreaName;
    private GeographicArea mGeoArea;
    private double mResult;
    private Date mStartDate;
    private Date mEndDate;
    private int dataYear1;
    private int dataMonth1;
    private int dataDay1;
    private int dataYear2;
    private int dataMonth2;
    private int dataDay2;
    private US623Controller controller;
    private US620Controller controller620;


    public US623UI() {
        this.mScanner = new Scanner(System.in);
        this.controller = new US623Controller();
        this.controller620 = new US620Controller();
    }

    public void run(GeographicAreaList newGeoListUi, HouseList newHouseListUi) {
        this.active = true;
        boolean activeInput = false;
        getInputGeographicArea(newGeoListUi);
        getInputHouse(newHouseListUi);
        while (!activeInput) {
            System.out.println("\n Do you wish to know the average rainfall on a specific day or day interval?\n");
            System.out.println("1 for Option1 / 2 for Option2 / 0 to exit:\t");
            if ("1".equals(mScanner.nextLine())) {
                getInputSingularDate();
                updateModelUS620();
                displayState620();
                activeInput = true;
            }
            if ("2".equals(mScanner.nextLine())) {
                getInputStartDate();
                getInputEndDate();
                updateModelUS623(mGeoAreaName, mHouse);
                displayState();
                activeInput = true;
            }
            if ("0".equals(mScanner.nextLine())) {
                activeInput = true;
                return;
            }
            else System.out.println("Please enter a valid option");
        }
    }
    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println("Please Select One Of The Existing Geographic Areas: " + newGeoListUi.printGeoAreaList());
        mGeoAreaName = mScanner.nextLine();
    }

    private void getInputHouse(HouseList newHouseListUi) {
        System.out.println("Please Select One Of The Existing Houses on " + mGeoAreaName + "\n" + newHouseListUi.printHouseList());
        String house = mScanner.nextLine();
        mHouse = newHouseListUi.getHouseByDesignation(house);
    }
    private void getInputSingularDate() {
        System.out.println("Enter the year:");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid year. Try again");
        }
        this.dataYear1 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Month:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid month. Try again");
        }
        this.dataMonth1 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Day:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid day. Try again");
        }
        this.dataDay1 = mScanner.nextInt();
        out.println("You entered the date successfully!");
        mScanner.nextLine();
    }

    private void getInputStartDate() {
        System.out.println("Enter the year:");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid year. Try again");
        }
        this.dataYear1 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Month:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid month. Try again");
        }
        this.dataMonth1 = mScanner.nextInt();
        mScanner.nextLine();
        out.println("\nEnter the Day:\t");
        while (!mScanner.hasNextInt()) {
            mScanner.next();
            out.println("Not a valid day. Try again");
        }
        this.dataDay1 = mScanner.nextInt();
        out.println("You entered the date successfully!");
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
        this.mStartDate = controller.createDate(dataDay1, dataMonth1, dataYear1);
        this.mResult = controller620.getTotalRainfallOnGivenDayHouseArea(mHouse,mStartDate);
    }

    private void updateModelUS623(String mGeoArea, House mHouse) {
        this.mGeoArea = controller.getGeographicArea(mGeoArea);
        this.mStartDate = controller.createDate(dataDay1, dataMonth1, dataYear1);
        this.mEndDate = controller.createDate(dataDay2, dataMonth2, dataYear2);
        this.mResult = controller.getAVGDailyRainfallOnGivenPeriod(mHouse, mStartDate, mEndDate);
    }

    private void displayState() {
        System.out.print("The Average Temperature on " + mHouse + "that is located on " + mGeoAreaName + "between the dates" +
                mStartDate + "and " + mEndDate + "is " + mResult + "%.");
        active = false;
    }

    private void displayState620() {
        System.out.print("The Average Temperature on " + mHouse + "that is located on " + mGeoAreaName + "on the date" +
                mStartDate + "is " + mResult + "%.");
        active = false;
    }
}

