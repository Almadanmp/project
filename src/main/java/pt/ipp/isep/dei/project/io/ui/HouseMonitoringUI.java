package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.Sensor;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;


public class HouseMonitoringUI {
    private HouseMonitoringController houseMonitoringcontroller;
    private House mHouse;
    private int dataYear1;
    private int dataMonth1;
    private int dataDay1;
    private int dataYear2;
    private int dataMonth2;
    private int dataDay2;
    private double mCurrentHouseAreaTemperature;
    private Sensor mSensor;
    private Room mRoom;
    private double mMaxTemperature;
    private double mCurrentTemperature;

    public HouseMonitoringUI() {
        this.houseMonitoringcontroller = new HouseMonitoringController();
    }

    void run(House programHouse) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        this.mHouse = programHouse;
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    if (getInputRoomByList()) {
                        return;
                    }
                    if (getInputSensorByList()) {
                        return;
                    }
                    getInputStartDate();
                    updateModel610(programHouse);
                    displayState610();
                    activeInput = true;
                    break;

                case 2:
                    if (getInputRoomByList()) {
                        return;
                    }
                    if (getInputSensorByList()) {
                        return;
                    }
                    updateModel605();
                    displayState605();
                    activeInput = true;
                    break;
                case 3:
                    updateModel600(programHouse);
                    displayState600(programHouse);
                    activeInput = true;
                    break;
                case 4:
                    getInputStartDateWithValidSensorList(programHouse);
                    updateAndDisplayModelUS620(programHouse);
                    activeInput = true;
                    break;
                case 5:
                    getInputStartDateWithValidSensorList(programHouse);
                    getInputEndDate623(programHouse);
                    updateAndDisplayUS623(programHouse);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utilsUI.invalidOption);
                    break;
            }
        }
    }

    //SHARED METHODS

    private boolean getInputRoomByList() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (this.mHouse.getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select one of the existing rooms on the selected House: ");
        System.out.println(houseMonitoringcontroller.buildHouseRoomListString(this.mHouse));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < this.mHouse.getRoomList().size()) {
            this.mRoom = this.mHouse.getRoomList().get(aux);
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    private boolean getInputSensorByList() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (mRoom.getmRoomSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty, or there are no Temperature Sensors in the " +
                    "selected Room.\n");
            return true;
        }
        System.out.println("Please select one of the existing Sensors on the selected Room: ");
        System.out.println(houseMonitoringcontroller.buildRoomSensorListString(mRoom));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mRoom.getmRoomSensorList().getSensorList().size()) {
            this.mSensor = mRoom.getmRoomSensorList().getSensorList().get(aux);
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    private void getInputStartDate() {
        InputUtils inputUtils = new InputUtils();
        Scanner scan = new Scanner(System.in);

        this.dataYear1 = inputUtils.getInputDateAsInt(scan, "year");
        scan.nextLine();

        this.dataMonth1 = inputUtils.getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();

        this.dataDay1 = inputUtils.getInputDateAsInt(scan, "day");

        System.out.println("You entered the date successfully!");
        scan.nextLine();
    }

    private void getInputEndDate623(House house){
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null) {
            return;
        }
        getInputEndDate();
    }


    private void getInputEndDate() {
        InputUtils inputUtils = new InputUtils();
        Scanner scan = new Scanner(System.in);

        this.dataYear2 = inputUtils.getInputDateAsInt(scan, "year");
        scan.nextLine();

        this.dataMonth2 = inputUtils.getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();

        this.dataDay2 = inputUtils.getInputDateAsInt(scan, "day");

        System.out.println("You entered the date successfully!");
        scan.nextLine();
    }

    /**
     * US600
     * As a Regular User, I want to get the current temperature in the house area. If, in the
     * first element with temperature sensors of the hierarchy of geographical areas that
     * includes the house, there is more than one temperature sensor, the nearest one
     * should be used.
     */
    private void updateModel600(House house) {
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null) {
            System.out.println("The Geographic Area in which this House is inserted doesn't have a valid Sensor List.");
            return;
        }
        mCurrentHouseAreaTemperature = houseMonitoringcontroller.getCurrentTemperatureInTheHouseArea(house, house.getMotherArea());
    }

    private void displayState600(House house) {
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null) {
            return;
        }
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature + "°C.");
    }

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */

    private void updateModel605() {
        out.print("The room is " + this.mRoom.getRoomName() + " and the Temperature Sensor is " +
                this.mSensor.getName() + "\n");
        this.mCurrentTemperature = houseMonitoringcontroller.getCurrentRoomTemperature(mRoom);
    }

    private void displayState605() {
        out.println("The Current Temperature in the room " + this.mRoom.getRoomName() +
                " is " + this.mCurrentTemperature + "°C.");
    }

    /**
     * US610 - CARINA ALAS
     */
    private void updateModel610(House house) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date mDate = ctrl.createDate(this.dataYear1, this.dataMonth1, this.dataDay1);
        out.print("The room is " + this.mRoom.getRoomName() + " the Temperature Sensor is " + this.mSensor.getName() +
                " and the date is " + mDate + "\n");
        this.mMaxTemperature = ctrl.getMaxTemperatureInARoomOnAGivenDay(mDate, house, this.mRoom);
    }

    private void displayState610() {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date mDate = ctrl.createDate(this.dataYear1, this.dataMonth1, this.dataDay1);
        out.println("The Maximum Temperature in the room " + this.mRoom.getRoomName() +
                " on the day " + mDate +
                " was " + this.mMaxTemperature + "°C.");
    }

    private void getInputStartDateWithValidSensorList(House house){
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null){
            System.out.println("The Geographic Area in which this House is inserted doesn't have a valid Sensor List.");
            return;
        }
        getInputStartDate();
    }

    /**
     * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
     */
    private void updateAndDisplayModelUS620(House house) {
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null){
            return;
        }
        Date mStartDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        double result620 = houseMonitoringcontroller.getTotalRainfallOnGivenDay(mHouse, mStartDate);
        printResultMessageUS620(mHouse, mStartDate, result620);
    }

    private void printResultMessageUS620(House houseGiven, Date givenDate, double result) {
        if (Double.isNaN(result)) {
            System.out.println("Warning: average value not calculated - no readings available.");
        } else {
            System.out.println(houseMonitoringcontroller.getHouseInfoForOutputMessage(houseGiven) + " on " + givenDate
                    + " is " + result + "%.");
        }
    }

    /**
     * US623: As a Regular User, I want to get the average daily rainfall in the house area for a
     * given period (days), as it is needed to assess the garden’s watering needs.
     */
    private void updateAndDisplayUS623(House house) {
        if (house.getMotherArea().getSensorList().getSensorList().isEmpty() || house.getMotherArea().getSensorList() == null){
            return;
        }
        Date initialDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        Date endDate = houseMonitoringcontroller.createDate(dataYear2, dataMonth2, dataDay2);
        double result623 = houseMonitoringcontroller.getAVGDailyRainfallOnGivenPeriod(house, initialDate, endDate);
        printResultMessageUS623(house, initialDate, endDate, result623);
    }

    private void printResultMessageUS623(House house, Date initialDate, Date endDate, double result623) {
        if (Double.isNaN(result623)) {
            System.out.println("Warning: average value not calculated - no readings available.");
        } else {
            System.out.println(houseMonitoringcontroller.getHouseInfoForOutputMessage(house) + " between " + initialDate
                    + " and " + endDate + " is " + result623 + "%.");
        }
    }

    private void printOptionMessage() {
        System.out.println("House Monitoring Options:\n");
        System.out.println("1) Get Max Temperature in a room in a specific day (US610).");
        System.out.println("2) Get Current Temperature in a room. (US605).");
        System.out.println("3) Get Current Temperature in a House Area. (US600)");
        System.out.println("4) Get The Average Rainfall on a specific day in a House Area. (US620)");
        System.out.println("5) Get The Average Rainfall on a day interval in a House Area. (US623)");
        System.out.println("0) (Return to main menu)\n");
    }
}
