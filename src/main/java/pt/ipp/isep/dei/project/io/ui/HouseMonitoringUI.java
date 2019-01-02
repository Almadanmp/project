package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.*;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class HouseMonitoringUI {
    private Scanner mScanner;
    private House mHouse;
    private GeographicArea mGeoArea;
    private String geoName;
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
    private HouseMonitoringController controller623;
    private US620Controller controller620;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private List<Integer> listOfIndexesGeographicAreas;
    private double mCurrentHouseAreaTemperature;
    private String mNameRoom;
    private double mMaxTemperature;
    private double mCurrentTemperature;
    private String mNameSensor;


    public HouseMonitoringUI() {
        this.mScanner = new Scanner(System.in);
        this.controller623 = new HouseMonitoringController();
        this.controller620 = new US620Controller();
    }

    public void run(GeographicAreaList newGeoListUi, RoomList roomList) {

        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        String option;
        System.out.println("--------------\n");
        System.out.println("House Monitoring\n");
        System.out.println("--------------\n");
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
                    if (!getInputRoom(roomList)) {
                        return;
                    }
                    if (!getInputSensorName(roomList)) {
                        return;
                    }
                    getInputStartDate();
                    updateModel610(roomList);
                    displayState610();
                    break;

                case "2":
                    if (!getInputRoom(roomList)) {
                        return;
                    }
                    if (!getInputSensorName(roomList)) {
                        return;
                    }
                    updateModel605(roomList);
                    displayState605();
                    return;
                case "3":
                    updateModel600();
                    displayState600();
                    activeInput = true;
                    break;
                case "4":
                    getInputStartDate();
                    updateModelUS620();
                    displayState620();
                    activeInput = true;
                    break;
                case "5":
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


    private boolean getInputRoom(RoomList list) {
        US610Controller ctrl = new US610Controller(list);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Room you want to get the Maximum Temperature from: ");

        this.mNameRoom = scanner.next();
        if (ctrl.doesListContainRoomByName(this.mNameRoom)) {
            System.out.println("You chose the Room " + this.mNameRoom);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }

    private boolean getInputSensorName(RoomList list) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please insert the name of the Sensor you want to get the Maximum Temperature from: ");
        this.mNameSensor = scanner.next();
        US610Controller ctrl = new US610Controller(list);
        if (ctrl.doesSensorListInARoomContainASensorByName(this.mNameSensor)) {
            System.out.println("You chose the Sensor " + this.mNameSensor);
        } else {
            System.out.println("This sensor does not exist in the list of sensors.");
            return false;
        }
        return true;
    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know where your house is located\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        String option = mScanner.nextLine();
        switch (option) {
            case "1":
                getInputGeographicAreaName();
                if (!getGeographicAreaByName(newGeoListUi)) {
                    System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                    return;
                }
                break;
            case "2":
                getInputGeographicAreaByList(newGeoListUi);
                break;
            case "0":
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        this.geoName = mScanner.nextLine();
        return (!(geoName.equals("exit")));
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(geoName, newGeoListUi);

        while (listOfIndexesGeographicAreas.isEmpty()) {
            System.out.println("There is no Geographic Area with that name. Please insert the name of a Geographic Area" +
                    " that exists or  Type 'exit' to cancel and create a new Geographic Area on the Main Menu.");
            if (!getInputGeographicAreaName()) {
                return false;
            }
            listOfIndexesGeographicAreas = ctrl.matchGeographicAreaIndexByString(geoName, newGeoListUi);
        }

        if (listOfIndexesGeographicAreas.size() > 1) {
            System.out.println("There are multiple Geographic Areas with that name. Please choose the right one.");
            System.out.println(ctrl.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas, newGeoListUi));
            int aux = readInputNumberAsInt();
            if (listOfIndexesGeographicAreas.contains(aux)) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println(ctrl.printGA(mGeoArea));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Geographic Area:");
            mGeoArea = newGeoListUi.getGeographicAreaList().get(listOfIndexesGeographicAreas.get(0));
            System.out.println(ctrl.printGA(mGeoArea));
        }
        return true;
    }

    private void getInputGeographicAreaByList(GeographicAreaList newGeoListUi) {
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
            System.out.print("Invalid House List - List Is Empty\n/**/");
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


    private void printOptionMessage() {
        System.out.println("House Monitoring Options:\n");
        System.out.println("1) Get Max Temperature in a room in a specific day");
        System.out.println("2) Get Current Temperature in a room");
        System.out.println("3) Get Current Temperature in a House Area.");
        System.out.println("4) Get The Average Rainfall on a specific day in a House Area .");
        System.out.println("5) Get The Average Rainfall on a day interval in a House Area.");
        System.out.println("0) (Return to main menu)");
    }

    /**
     * US600
     * As a Regular User, I want to get the current temperature in the house area. If, in the
     * first element with temperature sensors of the hierarchy of geographical areas that
     * includes the house, there is more than one temperature sensor, the nearest one
     * should be used.
     */
    public void updateModel600() {
        US600Controller ctrl = new US600Controller(mHouse);
        mCurrentHouseAreaTemperature = ctrl.getCurrentTemperatureInTheHouseArea(mHouse, mGeoArea);
    }

    public void displayState600() {
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature + "°C.");
    }

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */

    private void updateModel605(RoomList list) {
        US605Controller ctrl = new US605Controller(list);
        out.print("The room is " + this.mNameRoom + " and the Temperature Sensor is " + this.mNameSensor + "\n");
        Date mDate = new Date();
        this.mCurrentTemperature = ctrl.getCurrentRoomTemperature(mDate);
    }

    private void displayState605(){
        out.println("The Current Temperature in the room " + this.mNameRoom  +
                " is " + this.mCurrentTemperature+ "°C.");
    }
    /**
     * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
     */
    private void updateModelUS620() {
        this.mStartDate = controller623.createDate(dataYear1, dataMonth1, dataDay1);
        this.mResult620 = controller620.getTotalRainfallOnGivenDayHouseArea(mGeoArea, mStartDate);
    }

    private void displayState620() {
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " that is located on " + mGeoArea.getName() + " on the date " +
                mStartDate + " is " + mResult620 + "%.");
    }

    /**
     * US610
     */
    private void updateModel610(RoomList list) {
        US610Controller ctrl = new US610Controller(list);
        out.print("The room is " + this.mNameRoom + " the Temperature Sensor is " + this.mNameSensor +
                " and the date is " + this.dataDay1 +"-"+ this.dataMonth1 +"-"+ this.dataYear1 + "\n");
        Date mDate = ctrl.createDate(this.dataYear1, this.dataMonth1, this.dataDay1);
        this.mMaxTemperature = ctrl.getMaxTemperatureInARoomOnAGivenDay(mDate);
    }

    private void displayState610(){
        out.println("The Maximum Temperature in the room " + this.mNameRoom  +
                " on the day " + this.dataDay1 + "-" + this.dataMonth1 + "-" + this.dataYear1 +
                " was " + this.mMaxTemperature + "°C.");
    }

    /**
     * US623: As a Regular User, I want to get the average daily rainfall in the house area for a
     * given period (days), as it is needed to assess the garden’s watering needs.
     */
    private void updateModelUS623() {
        this.mStartDate = controller623.createDate(dataYear1, dataMonth1, dataDay1);
        this.mEndDate = controller623.createDate(dataYear2, dataMonth2, dataDay2);
        this.mResult623 = controller623.getAVGDailyRainfallOnGivenPeriod(mHouse, mStartDate, mEndDate);
    }

    private void displayState623() {
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " between " + mStartDate + " and " +
                mEndDate + " is " + mResult623 + "%.");
    }
}



