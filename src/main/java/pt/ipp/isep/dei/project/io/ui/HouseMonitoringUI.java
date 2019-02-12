package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.model.GeographicArea;
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
                    runUS610(programHouse);
                    activeInput = true;
                    break;
                case 2:
                    runUS605(programHouse);
                    activeInput = true;
                    break;
                case 3:
                    runUS600(programHouse);
                    activeInput = true;
                    break;
                case 4:
                    runUS620(programHouse);
                    activeInput = true;
                    break;
                case 5:
                    runUS623(programHouse);
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

    private Date getInputStartDate() {
        InputUtils inputUtils = new InputUtils();
        Scanner scan = new Scanner(System.in);

        this.dataYear1 = inputUtils.getInputDateAsInt(scan, "year");
        scan.nextLine();

        this.dataMonth1 = inputUtils.getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();

        this.dataDay1 = inputUtils.getInputDateAsInt(scan, "day");

        System.out.println("You entered the date successfully!");
        scan.nextLine();
        HouseMonitoringController ctrl = new HouseMonitoringController();
        return ctrl.createDate(this.dataYear1, this.dataMonth1, this.dataDay1);
    }


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
        if (mRoom.getSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty, or there are no Temperature Sensors in the " +
                    "selected Room.\n");
            return true;
        }
        System.out.println("Please select one of the existing Sensors on the selected Room: ");
        System.out.println(houseMonitoringcontroller.buildRoomSensorListString(mRoom));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mRoom.getSensorList().getSensorList().size()) {
            this.mSensor = mRoom.getSensorList().getSensorList().get(aux);
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }


    /**
     * US600
     * As a Regular User, I want to get the current temperature in the house area. If, in the
     * first element with temperature sensors of the hierarchy of geographical areas that
     * includes the house, there is more than one temperature sensor, the nearest one
     * should be used.
     */
    private void runUS600(House house) {
        UtilsUI utilsUI = new UtilsUI();
        GeographicArea motherArea = house.getMotherArea();
        if(!utilsUI.geographicAreaSensorListIsValid(motherArea)){
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        updateModel600(house, motherArea);
        displayState600();
    }

    private void updateModel600(House house, GeographicArea geographicArea) {
        mCurrentHouseAreaTemperature = houseMonitoringcontroller.getCurrentTemperatureInTheHouseArea(house, geographicArea);
    }

    private void displayState600() {
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature + "°C.");
    }

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */
    private void runUS605(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if(!utilsUI.houseRoomListIsValid(house)){
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        InputUtils inputUtils = new InputUtils();
        Room room = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomSensorListIsValid(room)) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        double currentTemp = updateModel605(room);
        displayState605(room, currentTemp);

    }

    private double updateModel605(Room room) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        out.print("You selected the room " + ctrl.getRoomName(room));
        return houseMonitoringcontroller.getCurrentRoomTemperature(mRoom);
    }

    private void displayState605(Room room, double temperature) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        out.println("The Current Temperature in the room " + ctrl.getRoomName(room) +
                " is " + temperature + "°C.");
    }

    /**
     * US610 - Get Max Temperature in a room in a specific day - CARINA ALAS
     */
    private void runUS610(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if (!(utilsUI.houseRoomListIsValid(house))) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        InputUtils inputUtils = new InputUtils();
        Room room = inputUtils.getHouseRoomByList(house);
        if (!(utilsUI.roomSensorListIsValid(room))) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        Date date = getInputStartDate();
        double maxTemp = updateModel610(room, date);
        displayState610(room, date, maxTemp);
    }

    private double updateModel610(Room room, Date date) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        System.out.print("You selected the room " + room.getRoomName() + " and the date " + date + "\n");
        return ctrl.getMaxTemperatureInARoomOnAGivenDay(room, date);
    }

    private void displayState610(Room room,  Date date, double maxTemperature) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if(maxTemperature < -1000){
            System.out.println("The room you selected has no temperature readings.");
            return;
        }
        out.println("The maximum temperature in the room " + ctrl.getRoomName(room) +
                " on the day " + date +
                " was " + maxTemperature + "°C.");
    }


    /**
     * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
     */
    private void runUS620(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.houseMotherAreaIsValid(house)) {
            System.out.println(utils.invalidMotherArea);
            return;
        }
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }
        getInputStartDateWithValidSensorList();
        updateAndDisplayModelUS620(house);
    }

    private void getInputStartDateWithValidSensorList() {
        getInputStartDate();
    }

    private void updateAndDisplayModelUS620(House house) {
        Date mStartDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        double result620 = houseMonitoringcontroller.getTotalRainfallOnGivenDay(house, mStartDate);
        printResultMessageUS620(house, mStartDate, result620);
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
    private void runUS623(House house) {
        UtilsUI utils = new UtilsUI();
        if (!utils.geographicAreaSensorListIsValid(house.getMotherArea())) {
            System.out.println(utils.invalidSensorList);
            return;
        }
        getInputStartDate();
        getInputEndDate();
        updateAndDisplayUS623(house);
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


    private void updateAndDisplayUS623(House house) {
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
