package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseMonitoringController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class HouseMonitoringUI {
    private House mHouse;
    private GeographicArea mGeoArea;
    private String geoName;
    private double mResult620;
    private double mResult623;
    private int dataYear1;
    private int dataMonth1;
    private int dataDay1;
    private int dataYear2;
    private int dataMonth2;
    private int dataDay2;
    private Date mStartDate;
    private HouseMonitoringController houseMonitoringcontroller;
    private List<Integer> listOfIndexesGeographicAreas;
    private List<Integer> listOfIndexesHouses;
    private List<Integer> listOfIndexesRoom;
    private List<Integer> listOfIndexesSensor;
    private double mCurrentHouseAreaTemperature;
    private String mHouseName;
    private String mNameRoom;
    private Sensor mSensor;
    private Room mRoom;
    private double mMaxTemperature;
    private double mCurrentTemperature;
    private String mNameSensor;

    public HouseMonitoringUI() {
        this.houseMonitoringcontroller = new HouseMonitoringController();
    }

    public void run(GeographicAreaList newGeoListUi, RoomList roomList) {
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Monitoring\n");
        System.out.println("--------------\n");
        getInputGeographicArea(newGeoListUi);
        if (mGeoArea == null) {
            System.out.println("Unable to select a Geographic Area. Returning to main menu.");
            return;
        }
        getInputHouse(mGeoArea);
        if (mHouse == null) {
            System.out.println("Unable to select a house. Returning to main menu.");
            return;
        }
        while (!activeInput) {
            printOptionMessage();
            option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputRoom();
                    getInpuSensor();
                    getInputStartDate();
                    updateModel610(roomList);
                    displayState610();
                    activeInput = true;
                    break;

                case 2:
                    getInputRoom();
                    getInpuSensor();
                    updateModel605(roomList);
                    displayState605();
                    activeInput = true;
                    break;
                case 3:
                    updateModel600();
                    displayState600();
                    activeInput = true;
                    break;
                case 4:
                    getInputStartDate();
                    updateModelUS620();
                    displayState620();
                    activeInput = true;
                    break;
                case 5:
                    getInputStartDate();
                    getInputEndDate();
                    updateAndDispleyUS623();
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    private void getInputRoom() {
        System.out.println(
                "We need to know which one is your room.\n" + "Would you like to:\n" + "1) Type the name of your Room;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = UtilsUI.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputRoomName();
                if (!getRoomByName()) {
                    System.out.println("Unable to select a Room. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputRoomByList();
                break;
            case 0:
                return;
            default:
                System.out.println(UtilsUI.INVALID_OPTION);
                break;
        }
    }

    private boolean getInputRoomName() {
        System.out.println("Please type the name of the Room you want to access.");
        Scanner scan = new Scanner(System.in);
        this.mNameRoom = scan.nextLine();
        return (!(this.mNameRoom.equals("exit")));
    }

    private boolean getRoomByName() {
        this.listOfIndexesRoom = houseMonitoringcontroller.matchRoomIndexByString(mNameRoom, mHouse);

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
            int aux = UtilsUI.readInputNumberAsInt();
            if (listOfIndexesRoom.contains(aux)) {
                mHouse.getmRoomList().getListOfRooms().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(houseMonitoringcontroller.printRoom(mRoom));
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Room:");
            mHouse.getmRoomList().getListOfRooms().get(0);
            System.out.println(houseMonitoringcontroller.printRoom(mRoom));
        }
        return true;
    }


    private void getInputRoomByList() {
        if (mHouse.getmRoomList().getListOfRooms().size() == 0) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            houseMonitoringcontroller.printRoomList(mHouse);
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getmRoomList().getListOfRooms().size()) {
                this.mRoom = mHouse.getmRoomList().getListOfRooms().get(aux);
                activeInput = true;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }


    private void getInpuSensor() {
        System.out.println(
                "We need to know which Sensor you wish to acess.\n" + "Would you like to:\n" + "1) Type the name of your Sensor;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = UtilsUI.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputSensorName();
                if (!getSensorByName()) {
                    System.out.println("Unable to select a Sensor. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputSensorByList();
                break;
            case 0:
                return;
            default:
                System.out.println(UtilsUI.INVALID_OPTION);
                break;
        }
    }

    private boolean getInputSensorName() {
        System.out.println("Please type the name of the Sensor you want to access.");
        Scanner scan = new Scanner(System.in);
        this.mNameSensor = scan.nextLine();
        return (!(this.mNameSensor.equals("exit")));
    }

    private boolean getSensorByName() {
        this.listOfIndexesSensor = houseMonitoringcontroller.matchSensorIndexByString(mNameSensor, mRoom);

        while (listOfIndexesSensor.isEmpty()) {
            System.out.println("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesSensor = houseMonitoringcontroller.matchSensorIndexByString(mNameSensor, mRoom);
        }
        if (listOfIndexesSensor.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(houseMonitoringcontroller.printSensorElementsByIndex(listOfIndexesSensor, mRoom));
            int aux = UtilsUI.readInputNumberAsInt();
            if (listOfIndexesSensor.contains(aux)) {
                mSensor=mRoom.getmRoomSensorList().getSensorList().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(houseMonitoringcontroller.printSensor(mSensor));
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Sensor:");
            mSensor=mRoom.getmRoomSensorList().getSensorList().get(0);
            System.out.println(houseMonitoringcontroller.printSensor(mSensor));
        }
        return true;
    }


    private void getInputSensorByList() {
        if (mHouse.getmRoomList().getListOfRooms().size() == 0) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            houseMonitoringcontroller.printSensorList(mRoom);
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < mRoom.getmRoomSensorList().getSensorList().size()) {
                this.mSensor = mRoom.getmRoomSensorList().getSensorList().get(aux);
                activeInput = true;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }
    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know where your house is located\n" + "Would you like to:\n" +
                        "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        boolean activeInput = false;
        while (!activeInput) {
            int option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputGeographicAreaName();
                    if (!getGeographicAreaByName(newGeoListUi)) {
                        System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                        return;
                    }
                    activeInput = true;
                    break;
                case 2:
                    getInputGeographicAreaByList(newGeoListUi);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    private boolean getInputGeographicAreaName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        this.geoName = scan.nextLine();
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
            int aux = UtilsUI.readInputNumberAsInt();
            if (listOfIndexesGeographicAreas.contains(aux)) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println(ctrl.printGA(mGeoArea));
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
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
            houseMonitoringcontroller.printGAList(newGeoListUi);
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
                //TODO fazer um print bonito
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println((mGeoArea.printGeographicArea()));
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    private void getInputHouse(GeographicArea mGeoArea) {
        System.out.println(
                "We need to know which one is your house.\n" + "Would you like to:\n" + "1) Type the name of your House;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        boolean activeInput = false;

        while (!activeInput) {
            int option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputHouseName();
                    if (!getHouseByName(mGeoArea)) {
                        System.out.println("Unable to select a House. Returning to main menu.");
                        return;
                    }
                    activeInput = true;
                    break;
                case 2:
                    getInputHousebyList(mGeoArea);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    private boolean getInputHouseName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type the name of the House you want to access.");
        this.mHouseName = scan.nextLine();
        return (!(mHouseName.equals("exit")));
    }

    private boolean getHouseByName(GeographicArea mGeoArea) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        listOfIndexesHouses = ctrl.matchHouseIndexByString(mHouseName, mGeoArea);

        while (listOfIndexesHouses.isEmpty()) {
            System.out.println("There is no House Area with that name. Please insert the name of a House" +
                    " that exists or  Type 'exit' to cancel and create a new House on the Main Menu.");
            if (!getInputHouseName()) {
                return false;
            }
            listOfIndexesHouses = ctrl.matchHouseIndexByString(mHouseName, mGeoArea);
        }
        if (listOfIndexesHouses.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(ctrl.printHouseElementsByIndex(listOfIndexesHouses, mGeoArea));
            int aux = UtilsUI.readInputNumberAsInt();
            if (listOfIndexesHouses.contains(aux)) {
                this.mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                System.out.println("You have chosen the following House:");
                System.out.println(ctrl.printHouse(mHouse));
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following House:");
            this.mHouse = mGeoArea.getHouseList().getHouseList().get(0);
            System.out.println(ctrl.printHouse(mHouse));
        }
        return true;
    }


    private void getInputHousebyList(GeographicArea mGeoArea) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        if (mGeoArea.getHouseList().getHouseList().isEmpty()) {
            System.out.print("Invalid House List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            houseMonitoringcontroller.printHouseList(mGeoArea);
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                System.out.println("You have chosen the following House:");
                System.out.println(ctrl.printHouse(mHouse));
                activeInput = true;
            } else {
                System.out.println(UtilsUI.INVALID_OPTION);
            }
        }
    }

    private void getInputStartDate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the year:");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid year. Try again");
        }
        this.dataYear1 = scan.nextInt();

        scan.nextLine();
        out.println("\nEnter the Month:\t");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid month. Try again");
        }
        this.dataMonth1 = scan.nextInt() - 1;

        scan.nextLine();
        out.println("\nEnter the Day:\t");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Not a valid day. Try again");
        }
        this.dataDay1 = scan.nextInt();
        System.out.println("You entered the date successfully!");
        scan.nextLine();
    }

    private void getInputEndDate() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the year:");
        while (!scan.hasNextInt()) {
            scan.next();
            out.println("Not a valid year. Try again");
        }
        this.dataYear2 = scan.nextInt();
        scan.nextLine();
        out.println("\nEnter the Month:\t");
        while (!scan.hasNextInt()) {
            scan.next();
            out.println("Not a valid month. Try again");
        }
        this.dataMonth2 = scan.nextInt() - 1;
        scan.nextLine();
        out.println("\nEnter the Day:\t");
        while (!scan.hasNextInt()) {
            scan.next();
            out.println("Not a valid day. Try again");
        }
        this.dataDay2 = scan.nextInt();
        out.println("You entered the date successfully!");
        scan.nextLine();
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
        mCurrentHouseAreaTemperature = houseMonitoringcontroller.getCurrentTemperatureInTheHouseArea(mHouse, mGeoArea);
    }

    public void displayState600() {
        System.out.println("The current temperature in the house area is: " + mCurrentHouseAreaTemperature + "°C.");
    }

    /**
     * US605 As a Regular User, I want to get the current temperature in a room, in order to check
     * if it meets my personal comfort requirements.
     */

    private void updateModel605(RoomList list) {
        out.print("The room is " + this.mRoom.getRoomName() + " and the Temperature Sensor is " + this.mSensor.getName() + "\n");
        Date mDate = new Date();
        this.mCurrentTemperature = houseMonitoringcontroller.getCurrentRoomTemperature(mDate, list);
    }

    private void displayState605() {
        out.println("The Current Temperature in the room " + this.mRoom.getRoomName() +
                " is " + this.mCurrentTemperature + "°C.");
    }


    /**
     * US610
     */
    private void updateModel610(RoomList list) {
        HouseMonitoringController ctrl = new HouseMonitoringController();
        Date mDate = ctrl.createDate(this.dataYear1, this.dataMonth1, this.dataDay1);
        out.print("The room is " + this.mRoom.getRoomName() + " the Temperature Sensor is " + this.mSensor.getName() +
                " and the date is " + mDate + "\n");
        this.mMaxTemperature = ctrl.getMaxTemperatureInARoomOnAGivenDay(mDate, list);
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
    private void updateModelUS620() {
        this.mStartDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        this.mResult620 = houseMonitoringcontroller.getTotalRainfallOnGivenDayHouseArea(mGeoArea, mStartDate);
    }

    private void displayState620() {
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " that is located on " + mGeoArea.getName() + " on the date " +
                mStartDate + " is " + mResult620 + "%.");
    }

    /**
     * US623: As a Regular User, I want to get the average daily rainfall in the house area for a
     * given period (days), as it is needed to assess the garden’s watering needs.
     */
    private void updateAndDispleyUS623() {
        Date initialDate = houseMonitoringcontroller.createDate(dataYear1, dataMonth1, dataDay1);
        Date endDate = houseMonitoringcontroller.createDate(dataYear2, dataMonth2, dataDay2);
        this.mResult623 = houseMonitoringcontroller.getAVGDailyRainfallOnGivenPeriod(mHouse, initialDate, endDate);
        System.out.print("The Average Rainfall on " + mHouse.getHouseDesignation() + " between " + initialDate + " and " + endDate +
                " is " + mResult623 + "%.");
    }
}
