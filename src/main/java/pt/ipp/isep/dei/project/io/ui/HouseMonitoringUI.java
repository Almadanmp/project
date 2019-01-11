package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.Sensor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class HouseMonitoringUI {
    private HouseMonitoringController houseMonitoringcontroller;
    private House mHouse;
    private GeographicArea mGeoArea;
    private double mResult620;
    private int dataYear1;
    private int dataMonth1;
    private int dataDay1;
    private int dataYear2;
    private int dataMonth2;
    private int dataDay2;
    private Date mStartDate;
    private double mCurrentHouseAreaTemperature;
    private String mNameRoom;
    private Sensor mSensor;
    private Room mRoom;
    private double mMaxTemperature;
    private double mCurrentTemperature;
    private String mNameSensor;

    public HouseMonitoringUI() {
        this.houseMonitoringcontroller = new HouseMonitoringController();
    }

    void run(House programHouse) {
        this.mHouse = programHouse;
        this.mGeoArea = programHouse.getMotherArea();
        UtilsUI utils = new UtilsUI();
        if (programHouse == null || programHouse.getMotherArea() == null || programHouse.getRoomList() == null) {
            System.out.println("Invalid House - This house doesn't meet the necessary requirements, please configure" +
                    " your house first through the main menu");
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = utils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    if (!getInputRoom(programHouse) || !getInputSensor()) {
                        return;
                    }
                    getInputStartDate();
                    updateModel610(programHouse);
                    displayState610();
                    activeInput = true;
                    break;

                case 2:
                    if (!getInputRoom(programHouse)) {
                        return;
                    }
                    if (!getInputSensor()) {
                        return;
                    }
                    updateModel605();
                    displayState605();
                    activeInput = true;
                    break;
                case 3:
                    updateModel600(programHouse);
                    displayState600();
                    activeInput = true;
                    break;
                case 4:
                    getInputStartDate();
                    updateAndDisplayModelUS620();
                    activeInput = true;
                    break;
                case 5:
                    getInputStartDate();
                    getInputEndDate();
                    updateAndDisplayUS623(programHouse);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }

    private boolean getInputRoom(House house) {
        UtilsUI utils = new UtilsUI();
        System.out.println(
                "We need to know which one is your room.\n" + "Would you like to:\n" + "1) Type the name of your Room;\n" +
                        "2) Choose it from a list;\n" + "0) Return;");
        int option = utils.readInputNumberAsInt();
        boolean activeInput = false;
        while (!activeInput) {
            switch (option) {
                case 1:
                    getInputRoomName();
                    if (!getRoomByName(house)) {
                        System.out.println("Unable to select a Room. Returning to main menu.");
                        return false;
                    }
                    activeInput = true;
                    break;
                case 2:
                    getInputRoomByList(house);
                    activeInput = true;
                    break;
                case 0:
                    return false;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
        return true;
    }

    private boolean getInputRoomName() {
        System.out.println("Please type the name of the Room you want to access.");
        Scanner scan = new Scanner(System.in);
        this.mNameRoom = scan.nextLine();
        return (!("exit".equals(this.mNameRoom)));
    }

    private boolean getRoomByName(House house) {
        UtilsUI utils = new UtilsUI();
        List<Integer> listOfIndexesRoom = houseMonitoringcontroller.matchRoomIndexByString(mNameRoom, mHouse);

        while (listOfIndexesRoom.isEmpty()) {
            System.out.println("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesRoom = houseMonitoringcontroller.matchRoomIndexByString(mNameRoom, mHouse);
        }
        if (listOfIndexesRoom.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(houseMonitoringcontroller.printRoomElementsByIndex(listOfIndexesRoom, mHouse));
            int aux = utils.readInputNumberAsInt();
            if (listOfIndexesRoom.contains(aux)) {
                house.getRoomList().getRoomList().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(houseMonitoringcontroller.printRoom(mRoom));
            } else {
                System.out.println(utils.invalidOption);
            }
        } else {
            System.out.println("You have chosen the following Room:");
            house.getRoomList().getRoomList().get(0);
            System.out.println(houseMonitoringcontroller.printRoom(mRoom));
        }
        return true;
    }


    private void getInputRoomByList(House house) {
        UtilsUI utils = new UtilsUI();
        if (house.getRoomList().getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            System.out.println(houseMonitoringcontroller.printHouseRoomList(house));
            int aux = utils.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getRoomList().getRoomList().size()) {
                this.mRoom = house.getRoomList().getRoomList().get(aux);
                activeInput = true;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    private boolean getInputSensor() {
        UtilsUI utils = new UtilsUI();
        System.out.println(
                "We need to know which Sensor you wish to access.\n" + "Would you like to:\n" +
                        "1) Type the name of your Sensor;\n" + "2) Choose it from a list;\n" + "0) Return;");
        boolean activeInput = false;
        while (!activeInput) {
            int option = utils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputSensorName();
                    if (!getSensorByName()) {
                        System.out.println("Unable to select a Sensor. Returning to main menu.");
                        return false;
                    }
                    activeInput = true;
                    break;
                case 2:
                    if (!getInputSensorByList()) {
                        System.out.println("Unable to select a Sensor. Returning to main menu.");
                        return false;
                    }
                    activeInput = true;
                    break;
                case 0:
                    return false;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
        return true;
    }

    private boolean getInputSensorName() {
        System.out.println("Please type the name of the Sensor you want to access.");
        Scanner scan = new Scanner(System.in);
        this.mNameSensor = scan.nextLine();
        return (!("exit".equals(this.mNameSensor)));
    }

    private boolean getSensorByName() {
        UtilsUI utils = new UtilsUI();
        List<Integer> listOfIndexesSensor = houseMonitoringcontroller.matchSensorIndexByString(mNameSensor, mRoom);

        while (listOfIndexesSensor.isEmpty()) {
            System.out.println("There is no Sensor with that name. Please insert the name of a Sensor" +
                    " that exists or  Type 'exit' to cancel and create a new Sensor on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesSensor = houseMonitoringcontroller.matchSensorIndexByString(mNameSensor, mRoom);
        }
        if (listOfIndexesSensor.size() > 1) {
            System.out.println("There are multiple Sensors with that name. Please choose the right one.");
            System.out.println(houseMonitoringcontroller.printSensorElementsByIndex(listOfIndexesSensor, mRoom));
            int aux = utils.readInputNumberAsInt();
            if (listOfIndexesSensor.contains(aux)) {
                mSensor = mRoom.getmRoomSensorList().getSensorList().get(aux);
                System.out.println("You have chosen the following Sensor:");
                System.out.println(houseMonitoringcontroller.printSensor(mSensor));
            } else {
                System.out.println(utils.invalidOption);
            }
        } else {
            System.out.println("You have chosen the following Sensor:");
            mSensor = mRoom.getmRoomSensorList().getSensorList().get(0);
            System.out.println(houseMonitoringcontroller.printSensor(mSensor));
        }
        return true;
    }

    private boolean getInputSensorByList() {
        UtilsUI utils = new UtilsUI();
        if (mRoom.getmRoomSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty, or there are no Temperature Sensors in the " +
                    "selected Room.\n");
            return false;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing Sensors on the selected Room: ");

        while (!activeInput) {
            houseMonitoringcontroller.printRoomSensorList(mRoom);
            int aux = utils.readInputNumberAsInt();
            if (aux >= 0 && aux < mRoom.getmRoomSensorList().getSensorList().size()) {
                this.mSensor = mRoom.getmRoomSensorList().getSensorList().get(aux);
                return true;
            } else {
                System.out.println(utils.invalidOption);
                return false;
            }
        }
        return true;
    }

    private void getInputStartDate() {
        Scanner scan = new Scanner(System.in);

        this.dataYear1 = UtilsUI.getInputDateAsInt(scan, "year");
        scan.nextLine();

        this.dataMonth1 = UtilsUI.getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();

        this.dataDay1 = UtilsUI.getInputDateAsInt(scan, "day");

        System.out.println("You entered the date successfully!");
        scan.nextLine();
    }

    private void getInputEndDate() {
        Scanner scan = new Scanner(System.in);

        this.dataYear2 = UtilsUI.getInputDateAsInt(scan, "year");
        scan.nextLine();

        this.dataMonth2 = UtilsUI.getInputDateAsInt(scan, "month") - 1;
        scan.nextLine();

        this.dataDay2 = UtilsUI.getInputDateAsInt(scan, "day");

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
    public void updateModel600(House house) {
        mCurrentHouseAreaTemperature = houseMonitoringcontroller.getCurrentTemperatureInTheHouseArea(house, house.getMotherArea());
    }

    public void displayState600() {
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
     * US610
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

    /**
     * US620UI: As a Regular User, I want to get the total rainfall in the house area for a given day.
     */
    private void updateAndDisplayModelUS620() {
        this.mStartDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        this.mResult620 = houseMonitoringcontroller.getTotalRainfallOnGivenDay(mHouse, mStartDate);
        printResultMessageUS620(mHouse, mStartDate, mResult620);
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
