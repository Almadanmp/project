package PT.IPP.ISEP.DEI.Project.IO.UI;

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


    public US623UI() {
        this.mScanner = new Scanner(System.in);
        this.controller = new US623Controller();
    }

    public void run(GeographicAreaList newGeoListUi, HouseList newHouseListUi) {
        this.active = true;
        getInputGeographicArea(newGeoListUi);
        getInputHouse(newHouseListUi);
        getInputStartDate();
        getInputEndDate();
        updateModelUS620();
        updateModelUS623(mGeoAreaName, mHouse);
        displayState();

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
}

