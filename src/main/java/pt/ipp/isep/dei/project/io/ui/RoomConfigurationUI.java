package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private GeographicArea mGeoArea;
    private RoomConfigurationController mRoomConfigurationController;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private Room mRoom;
    private Sensor mSensor;
    private String mRoomName;
    private SensorList mSensorList;
    private String mSensorName;
    private String mStringRequestRoom = "You have chosen the following Room:";

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house) {
        if (house == null) {
            System.out.println("Please create a House before you continue.");
            return;
        }
        this.mHouse = house;
        RoomList roomList = mHouse.getRoomList();
        if (roomList == null|| roomList.getRoomList().isEmpty()) {
            System.out.println("There are no available rooms in the house. Please add a room to continue.");
            return;
        }
        this.mGeoArea = house.getMotherArea();
        this.mSensorList = mGeoArea.getSensorList();
        UtilsUI utils = new UtilsUI();
        boolean activeInput = true;
        int option;

        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printHouseConfigMenu();
            option = utils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    activeInput = false;
                    break;
                case 2:
                    activeInput = false;
                    break;
                case 3:
                    activeInput = false;
                    break;
                case 4:
                    activeInput = false;
                    break;
                case 5:
                    activeInput = false;
                    break;
                case 6:
                    if (mGeoArea.getSensorList().getSensorList().isEmpty() || mGeoArea.getSensorList() == null) {
                        System.out.println("There's no available sensors in the Geographical Area");
                        return;
                    }
                    getInputRoom();
                    getInputSensor();
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }


    //  SHARED METHODS

    private void getInputRoom() {
        UtilsUI utils = new UtilsUI();
        System.out.println(
                "We need to know which one is your room.\n" + "Would you like to:\n" +
                        "1) Type the name of your Room;\n" +
                        "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = utils.readInputNumberAsInt();
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
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputRoomName() {
        Scanner mScanner = new Scanner(System.in);
        System.out.println("Please type the name of the Room you want to access.");
        this.mRoomName = mScanner.nextLine();
        return (!("exit".equals(this.mRoomName)));
    }

    private boolean getRoomByName() {
        UtilsUI utils = new UtilsUI();
        List<Integer> listOfIndexesRoom = mRoomConfigurationController.matchRoomIndexByString(mRoomName, mHouse);
        while (listOfIndexesRoom.isEmpty()) {
            System.out.print("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesRoom = mRoomConfigurationController.matchRoomIndexByString(mRoomName, mHouse);
        }
        if (listOfIndexesRoom.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(mRoomConfigurationController.printRoomElementsByIndex(listOfIndexesRoom, mHouse));
            int aux = utils.readInputNumberAsInt();
            if (listOfIndexesRoom.contains(aux)) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                mHouse.getRoomList().getRoomList().get(aux);
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            this.mRoom = mHouse.getRoomList().getRoomList().get(listOfIndexesRoom.get(0));
            this.mRoomName = mRoom.getRoomName();
            System.out.println(mStringRequestRoom);
            this.mHouse.getRoomList().getRoomList().get(0);
            System.out.println(mRoomConfigurationController.printRoom(mRoom));
        }
        return true;
    }

    private void getInputRoomByList() {
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = utils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private Sensor getInputSensor() {
        UtilsUI utils = new UtilsUI();
        System.out.println(
                "We need to know which sensor you want to add the room.\n" + "Would you like to:\n" +
                        "1) Type the Sensor name;\n" +
                        "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = utils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputSensorName();
                if (!getSensorByName(mSensorList)) {
                    return this.mSensor;
                }
                break;
            case 2:
                getInputSensorByList(mSensorList);
                return this.mSensor;

            case 0:
                break;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }

        return this.mSensor;

    }

    private boolean getSensorByName(SensorList mSensorList) {
        UtilsUI utils = new UtilsUI();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        List<Integer> listOfIndexesSensors = ctrl.matchSensorIndexByString(mSensorName, mSensorList);
        while (listOfIndexesSensors.isEmpty()) {
            System.out.println("There is no Sensor with that name. Please insert the name of a Geographic Area" +
                    " that exists or  Type 'exit' to cancel and create a new Geographic Area on the Main Menu.");
            if (!getInputSensorName()) {
                System.out.println("Unable to select a Sensor. Returning to main menu.");
                return false;
            }
            listOfIndexesSensors = ctrl.matchSensorIndexByString(mSensorName, mSensorList);
        }
        String mStringChosenSensor = "You have chosen the following Sensor:";
        if (listOfIndexesSensors.size() > 1) {
            System.out.println("There are multiple Sensors with that name. Please choose the right one.");
            System.out.println(ctrl.printSensorElementsByIndex(listOfIndexesSensors, mSensorList));
            int aux = utils.readInputNumberAsInt();
            if (listOfIndexesSensors.contains(aux)) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                System.out.println(mStringChosenSensor);
                System.out.println(ctrl.printSensor(mSensor));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println(mStringChosenSensor);
            this.mSensor = mSensorList.getSensorList().get(listOfIndexesSensors.get(0));
            this.mSensorName = mSensor.getName();
            System.out.println(ctrl.printSensor(mSensor));
        }
        System.out.print("Sensor " + mSensor.getName() + " was successfully added to " + this.mRoomName);
        return true;
    }

    private boolean getInputSensorName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type the name of the Sensor located in your houses' Geographical Area.");
        this.mSensorName = scanner.nextLine();
        return (!("exit".equals(mSensorName)));
    }

    private void getInputSensorByList(SensorList mSensorList) {
        UtilsUI utils = new UtilsUI();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        if (mGeoArea.getSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select a Sensor from the Houses' Geographical Area from the list: ");
        while (!activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = utils.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = true;
                System.out.println("You have chosen the following Sensor:");
                System.out.println(mRoomConfigurationController.printSensor(mSensor));
                System.out.print("Sensor " + mSensor.getName() + " was successfully added to " + this.mRoomName);
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printHouseConfigMenu() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) Get the total nominal power of a room (US230)");
        System.out.println("5) Get a list of all sensors in a room (US250)");
        System.out.println("6) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("0) (Return to main menu)\n");
    }
}




