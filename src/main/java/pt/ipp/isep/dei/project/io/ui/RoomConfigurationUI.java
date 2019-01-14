package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private GeographicArea mGeoArea;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Sensor mSensor;
    private Device mDevice;
    private double mNominalPower;
    private String mDeviceName;
    private String mRoomName;
    private SensorList mSensorList;
    private String mSensorName;
    private String mStringRequestRoom = "You have chosen the following Room:";
    private String mStringRequestDevice = "You have chosen the following Device:";
    private String mStringChosenSensor = "You have chosen the following Sensor:";

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
        if (roomList == null || roomList.getRoomList().isEmpty()) {
            System.out.println("There are no available rooms in the house. Please add a room to continue.");
            return;
        }
        this.mGeoArea = house.getMotherArea();
        this.mSensorList = mGeoArea.getSensorList();
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;

        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printRoomConfigMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1: //US201
                    getInputRoomByList();
                    getRoomDeviceList(mRoom);
                    activeInput = false;
                    break;
                case 2: //US210
                    activeInput = false;
                    break;
                case 3: //215
                    getInputRoomByList();
                    getInputDeviceByList();
                    getInputDeviceCharacteristicsUS215();
                    updateDeviceUS215();
                    displayDeviceUS215();
                    activeInput = false;
                    break;
                case 4: //US230
                    getInputRoomByList();
                    getRoomNominalPower();
                    activeInput = false;
                    break;
                case 5: //US250
                    getInputRoomByList();
                    getInputSensorByListRoom();
                    activeInput = false;
                    break;
                case 6: //US253
                    if (mGeoArea.getSensorList().getSensorList().isEmpty() || mGeoArea.getSensorList() == null) {
                        System.out.println("There's no available sensors in the Geographical Area");
                        return;
                    }
                    getInputRoomByList();
                    getInputSensor();
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }


    //  SHARED METHODS

    private void getInputRoomByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing Rooms in the House: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = true;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    private void getInputDeviceByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mRoom.getDeviceList().isEmpty()) {
            System.out.println("Invalid Device List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printDeviceList(mRoom));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mRoom.getDeviceList().size()) {
                this.mDevice = mRoom.getDeviceList().get(aux);
                this.mDeviceName = mDevice.getName();
                System.out.println(mStringRequestDevice);
                System.out.println(mRoomConfigurationController.printDevice(mDevice));
                activeInput = true;
            } else {
                System.out.println(utils.invalidOption);
            }

        }
    }
    /*US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.*/

    private void getRoomDeviceList(Room room) {
        System.out.println("Available Devices in Room");
        System.out.println(mRoomConfigurationController.printDeviceList(room));
    }

    /*US215 As an Administrator, I want to edit the configuration of an existing device, so that I
can reconfigure it.*/

    private void getInputDeviceCharacteristicsUS215() {

        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the Device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoom.removeDevice(mDevice);
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House to which you want to add your Device: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = true;
                mRoom.addDevice(mDevice);
                mDevice.setmParentRoom(mRoom);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
        //get nominal power
        String onlyNumbers = "Please,try again. Only numbers this time:";
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();

    }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void updateDeviceUS215() {
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);
    }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void displayDeviceUS215() {
        System.out.println("You have successfully changed the Device name to " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    private void getRoomNominalPower() {
        double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
        System.out.println("This room has a total nominal power of "+ roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/

    private void getInputSensorByListRoom() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        mSensorList = mRoom.getmRoomSensorList();
        if (mSensorList.getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select a Sensor from the Chosen Room in order to Configure it: ");
        while (!activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = true;
                System.out.println(mStringChosenSensor);
                System.out.println(mRoomConfigurationController.printSensor(mSensor));
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private Sensor getInputSensor() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        System.out.println(
                "We need to know which sensor you want to add the room.\n" + "Would you like to:\n" +
                        "1) Type the Sensor name;\n" +
                        "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = inputUtils.readInputNumberAsInt();
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
                System.out.println(utils.invalidOption);
                break;
        }

        return this.mSensor;

    }

    private boolean getSensorByName(SensorList mSensorList) {
        InputUtils inputUtils = new InputUtils();
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
        String mStringChosenSensor = this.mStringChosenSensor;
        if (listOfIndexesSensors.size() > 1) {
            System.out.println("There are multiple Sensors with that name. Please choose the right one.");
            System.out.println(ctrl.printSensorElementsByIndex(listOfIndexesSensors, mSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (listOfIndexesSensors.contains(aux)) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                System.out.println(mStringChosenSensor);
                System.out.println(ctrl.printSensor(mSensor));
            } else {
                System.out.println(utils.invalidOption);
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
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        if (mGeoArea.getSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select a Sensor from the Houses' Geographical Area from the list: ");
        while (!activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = true;
                System.out.println(mStringChosenSensor);
                System.out.println(mRoomConfigurationController.printSensor(mSensor));
                System.out.print("Sensor " + mSensor.getName() + " was successfully added to " + this.mRoomName);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printRoomConfigMenu() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) Get the total nominal power of a room (US230)");
        System.out.println("5) Get a list of all sensors in a room (US250)");
        System.out.println("6) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("0) (Return to main menu)\n");
    }
}




