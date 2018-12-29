package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US620Controller;
import pt.ipp.isep.dei.project.controller.US623Controller;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
 *
 * US623UI: As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 */
public class US623UI {
    private Scanner mScanner;
    private House mHouse;
    private GeographicArea mGeoArea;
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
    private static final String INVALID_OPTION = "Please enter a valid option";


    public US623UI() {
        this.mScanner = new Scanner(System.in);
        this.controller623 = new US623Controller();
        this.controller620 = new US620Controller();
    }

    public void run(GeographicAreaList newGeoListUi) {

        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        String option;

        getInputGeographicArea(newGeoListUi);
        getInputHouse();
        if (mHouse == null) {
            System.out.println("Unable to select a house. Returning to main menu.");
            return;
        }
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
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");

        while (!activeInput) {
            controller623.printGAList(newGeoListUi);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    private void getInputHouse() {
        if (mGeoArea.getHouseList().getHouseList().size() == 0) {
            System.out.print("Invalid House List - List Is Empty");
            return;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            controller623.printHouseList(mGeoArea);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    private int readInputNumberAsInt() {

        while (!mScanner.hasNextDouble()) {
            System.out.println(INVALID_OPTION);
            mScanner.next();
        }
        Double option = mScanner.nextDouble();
        return option.intValue();
    }

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
        this.mResult620 = controller620.getTotalRainfallOnGivenDayHouseArea(mGeoArea, mStartDate);
    }

    private void updateModelUS623() {
        this.mStartDate = controller623.createDate(dataYear1, dataMonth1, dataDay1);
        this.mEndDate = controller623.createDate(dataYear2, dataMonth2, dataDay2);
        this.mResult623 = controller623.getAVGDailyRainfallOnGivenPeriod(mHouse, mStartDate, mEndDate);
    }

    private void displayState620() {
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " that is located on " + mGeoArea.getName() + " on the date " +
                mStartDate + " is " + mResult620 + "%.");
    }

    private void displayState623() {
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " between " + mStartDate + " and " +
                mEndDate + " is " + mResult623 + "%.");
    }

    private void printOptionMessage() {
        System.out.println("\nDo you wish to know the average rainfall on:");
        System.out.println("1) A specific day.");
        System.out.println("2) A day interval.");
        System.out.println("0) (Return to main menu)");
    }
}

