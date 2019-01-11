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
    private String mStringChosenSensor = "You have chosen the following Sensor:";
    private String mSensorName;

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();

    }

    void run(House house, GeographicArea ga) {
        this.mGeoArea = ga;
        this.mSensorList = ga.getSensorList();
        this.mHouse = house;
        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");

        if (mHouse == null) {
            System.out.println("Unable to select a house. Returning to main menu.");
            return;
        }
        getInputRoom();
        if (mRoom == null) {
            System.out.println("Unable to select a room. Returning to main menu.");
            return;
        }
        getInputSensor();
        System.out.print("Sensor " + mSensor.getName() + " was successefully added to " + this.mRoomName);
    }

    /* *****************************************************************************
     ************************** Room Input Segment ********************************
     ******************************************************************************/

    private void getInputRoom() {
        System.out.println(
                "We need to know which one is your room.\n" + "Would you like to:\n" +
                        "1) Type the name of your Room;\n" +
                        "2) Choose it from a list;\n" +
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
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputRoomName() {
        Scanner mScanner = new Scanner(System.in);
        System.out.println("Please type the name of the Room you want to access.");
        this.mRoomName = mScanner.nextLine();
        return (!(this.mRoomName.equals("exit")));
    }

    private boolean getRoomByName() {
        List<Integer> listOfIndexesRoom = mRoomConfigurationController.matchRoomIndexByString(mRoomName, mHouse);
        while (listOfIndexesRoom.isEmpty()) {
            System.out.println("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesRoom = mRoomConfigurationController.matchRoomIndexByString(mRoomName, mHouse);
        }
        if (listOfIndexesRoom.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(mRoomConfigurationController.printRoomElementsByIndex(listOfIndexesRoom, mHouse));
            int aux = UtilsUI.readInputNumberAsInt();
            if (listOfIndexesRoom.contains(aux)) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                mHouse.getRoomList().getRoomList().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            this.mRoom = mHouse.getRoomList().getRoomList().get(listOfIndexesRoom.get(0));
            this.mRoomName = mRoom.getRoomName();
            System.out.println("You have chosen the following Room:");
            this.mHouse.getRoomList().getRoomList().get(0);
            System.out.println(mRoomConfigurationController.printRoom(mRoom));
        }
        return true;
    }

    private void getInputRoomByList() {
        if (mHouse.getRoomList().getRoomList().size() == 0) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    /* * ****************************************************************************
     ************************** Sensor Input Segment ******************************
     ******************************************************************************/

    Sensor getInputSensor() {

        System.out.println(
                "We need to know which sensor you want to work with.\n" + "Would you like to:\n" + "1) Type the Sensor name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = UtilsUI.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputSensorName();
                if (!getSensorByName(mSensorList)) {
                    return mSensor;
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
        if (listOfIndexesSensors.size() > 1) {
            System.out.println("There are multiple Sensors with that name. Please choose the right one.");
            System.out.println(ctrl.printSensorElementsByIndex(listOfIndexesSensors, mSensorList));
            int aux = UtilsUI.readInputNumberAsInt();
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
        return true;
    }

    private boolean getInputSensorName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type the name of the Sensor located in your houses' Geographical Area.");
        this.mSensorName = scanner.nextLine();
        return (!("exit".equals(mSensorName)));
    }

    private void getInputSensorByList(SensorList mSensorList) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        if (mGeoArea.getSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select a Sensor from the Houses' Geographical Area from the list: ");
        while (!activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = UtilsUI.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = true;
                System.out.println(mStringChosenSensor);
                System.out.println((mSensor.printSensor()));
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }
}




