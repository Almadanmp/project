package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.*;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private GeographicArea mGeoArea;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Sensor mSensor;
    private Device mDevice;
    private DeviceType mDeviceType;
    private double mNominalPower;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mCapacity;
    private String mDeviceName;
    private String mRoomName;
    private SensorList mSensorList;
    private String mSensorName;
    private String mStringRequestRoom = "You have chosen the following Room:";
    private String mStringRequestDevice = "You have chosen the following Device:";
    private String mStringChosenSensor = "You have chosen the following Sensor:";
    private DeviceSpecs mDeviceSpecs;
    private TypeSensor mTypeSensor;
    private int mDataYear;
    private int mDataMonth;
    private int mDataDay;

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house, List<DeviceType> deviceTypeList, List<TypeSensor> typeSensorList) {
        if (house == null) {
            System.out.println("Please create a House before you continue.");
            return;
        }
        this.mHouse = house;
        List<Room> roomList = mHouse.getRoomList();
        if (roomList == null || roomList.isEmpty()) {
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
                    if(getInputRoomByList()){
                        return;
                    }
                    printRoomDeviceList(mRoom);
                    activeInput = false;
                    break;
                case 2: //US210
                    getInputDeviceTypeByList(deviceTypeList);
                    createDevice(mDeviceType);
                    displayDeviceUS210();
                    activeInput = false;
                    break;
                case 3: //215
                    if(getInputRoomByList()){
                        return;
                    }
                    if(getInputDeviceByList()){
                        return;
                    }
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
                    if(getInputRoomByList()){
                        return;
                    }
                    if(getInputSensorFromRoomByList()){
                        return;
                    }
                    activeInput = false;
                    break;
                case 6: //US253
                    if (typeSensorList.isEmpty()) {
                        System.out.println("There's no defined types of sensor available yet. Please define one first.");
                        return;
                    }
                    if(getInputRoomByList()){
                        return;
                    }
                    if(getInputTypeFromTypeListByList(typeSensorList)){
                        return;
                    }
                    getInput253();
                    updateAndDisplay253();
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

    private boolean getInputRoomByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select one of the existing Rooms in the House: ");
        System.out.println(mRoomConfigurationController.printRoomList(mHouse));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mHouse.getRoomList().size()) {
            this.mRoom = mHouse.getRoomList().get(aux);
            this.mRoomName = mRoom.getRoomName();
            System.out.println(mStringRequestRoom);
            System.out.println(mRoomConfigurationController.printRoom(mRoom));
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    private boolean getInputDeviceByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mRoom.getDeviceList().isEmpty()) {
            System.out.println("Invalid Device List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        System.out.println(mRoomConfigurationController.printDeviceList(mRoom));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mRoom.getDeviceList().size()) {
            this.mDevice = mRoom.getDeviceList().get(aux);
            this.mDeviceName = mDevice.getName();
            System.out.println(mStringRequestDevice);
            System.out.println(mRoomConfigurationController.printDevice(mDevice));
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    /**US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * @param room comes from getInputRoomByList
     * Prints Device List in that room.
     */
    private void printRoomDeviceList(Room room) {
        System.out.println("Available Devices in Room " + mRoomName);
        System.out.println(mRoomConfigurationController.printDeviceList(room));
    }

     /* USER STORY 210 - As an Administrator, I want to add a new device to a room from the list of available
    device types, so that I can configure it. - MARIA MEIRELES */

    private boolean getInputDeviceTypeByList(List<DeviceType> deviceTypeList) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        boolean activeInput = false;
        while (!activeInput) {
            System.out.println("Please select one of the Device Types: ");
            System.out.println(mRoomConfigurationController.printDeviceTypeList(deviceTypeList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < DeviceType.values().length) {
                this.mDeviceType = DeviceType.values()[aux];
                return true;
            } else {
                System.out.println(utils.invalidOption);
                return false;
            }

        }
        return true;
    }

    private void createDevice(DeviceType deviceType) {
        Device device = new Device();
        Scanner scanner = new Scanner(System.in);
        String onlyNumbers = "Please,try again. Only numbers this time:";
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();

        // get device name
        System.out.print("Please, type the new name of the Device: ");
       mDeviceName = scanner.nextLine();

        //get room
        if (mHouse.getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }

        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House to which you want to add your Device: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = true;
                mRoom.addDevice(device);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
        //get nominal power
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();
        switch (deviceType) {
            case WATER_HEATER:
                mDeviceSpecs = new WaterHeater();
                System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                this.mVolumeOfWater = scanner.nextDouble();

                System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                this.mHotWaterTemperature = scanner.nextDouble();

                System.out.print("Please, type the Minimum Temperature of the water in the Water Heater: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                this.mColdWaterTemperature = scanner.nextDouble();

                System.out.println(
                        "Do you wish to alter the performance ration?\n" +
                                "1) Yes;\n" +
                                "2) No;\n");
                int option = inputUtils.readInputNumberAsInt();

                switch (option) {
                    case 1:
                        System.out.print("Please, type the Performance Ration of the Water Heater: ");
                        while (!scanner.hasNextDouble()) {
                            System.out.println(onlyNumbers);
                            scanner.next();
                        }
                        mPerformanceRatio = scanner.nextDouble();
                        break;
                    case 2:
                        this.mPerformanceRatio = 0.9;
                        break;
                }
                mDevice = new Device(mDeviceName, mNominalPower, mDeviceSpecs);
                break;
            case FRIDGE:
                mDeviceSpecs = new Fridge();
                System.out.print("Please, type the Freezer Capacity in L for the Fridge:");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                mFreezerCapacity = scanner.nextDouble();
                mDevice = new Device(mDeviceName, mNominalPower, mDeviceSpecs);
                break;
            case WASHING_MACHINE:
                mDeviceSpecs = new WashingMachine();
                System.out.print("Please, type the Capacity in Kg for the Washing Machine: ");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                mCapacity = scanner.nextDouble();
                mDevice = new Device(mDeviceName, mNominalPower, mDeviceSpecs);
                break;
            case DISHWASHER:
                mDeviceSpecs = new Dishwasher();
                System.out.print("Please, type the Capacity in Kg for the Dishwasher:");
                while (!scanner.hasNextDouble()) {
                    System.out.println(onlyNumbers);
                    scanner.next();
                }
                mCapacity = scanner.nextDouble();
                mDevice = new Device(mDeviceName, mNominalPower, mDeviceSpecs);
                break;
        }
    }


    private void displayDeviceUS210() {
        System.out.println("You have successfully created a " + mDeviceType.printDeviceType(mDeviceType) + " with the name " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + ".");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, the Min Temperature is " + mColdWaterTemperature + " ºC.");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is " + mCapacity + " Kg.");
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.println("The freezer Capacity is  " + mFreezerCapacity + " L and the Refrigerator Capacity is " + mRefrigeratorCapacity + " L.");
        }
    }

    /* USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,
    so that I can reconfigure it.. - CARINA ALAS */

    private void getInputDeviceCharacteristicsUS215() {

        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the Device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoom.removeDevice(mDevice);
        InputUtils inputUtils = new InputUtils();
        getInputRoomByList();
        mRoom.addDevice(mDevice);
        mDevice.setmParentRoom(mRoom);
        //get nominal power
        String onlyNumbers = "Please,try again. Only numbers this time:";
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();

        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mVolumeOfWater = scanner.nextDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mHotWaterTemperature = scanner.nextDouble();

            System.out.print("Please, type the Minimum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mColdWaterTemperature = scanner.nextDouble();

            System.out.println(
                    "Do you wish to alter the performance ratio?\n" +
                            "1) Yes;\n" +
                            "2) No;\n");
            int option = inputUtils.readInputNumberAsInt();

            switch (option) {
                case 1:
                    System.out.print("Please, type the Performance Ration of the Water Heater: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println(onlyNumbers);
                        scanner.next();
                    }
                    this.mPerformanceRatio = scanner.nextDouble();
                    mRoomConfigurationController.setPerformanceRatio(mPerformanceRatio, mDevice);
                    break;
                case 2:
                    this.mPerformanceRatio = 0.9;
                    break;
            }

        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            System.out.print("Please, type the new Capacity in Kg for the Washing Machine: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.print("Please, type the new Capacity in Kg for the Dishwasher:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }

        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.print("Please, type the new Freezer Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mFreezerCapacity = scanner.nextDouble();

            System.out.print("Please, type the new Refrigerator Capacity in L for the Fridge:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mRefrigeratorCapacity = scanner.nextDouble();
        }

    }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void updateDeviceUS215() {
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            mRoomConfigurationController.setVolumeWater(mVolumeOfWater, mDevice);
            mRoomConfigurationController.setHotWaterTemp(mHotWaterTemperature, mDevice);
            mRoomConfigurationController.setColdWaterTemp(mColdWaterTemperature, mDevice);
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            mRoomConfigurationController.setCapacity(mCapacity, mDevice);
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            mRoomConfigurationController.setCapacity(mCapacity, mDevice);
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            mRoomConfigurationController.setFreezerCapacity(mFreezerCapacity, mDevice);
            mRoomConfigurationController.setRefrigeratorCapacity(mRefrigeratorCapacity, mDevice);
        }
    }

    /* US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it. - CARINA ALAS*/

    private void displayDeviceUS215() {
        System.out.println("\nYou have successfully changed the Device name to " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, the Min Temperature is " + mColdWaterTemperature + " ºC.");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is " + mCapacity + " Kg.");
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.println("The freezer Capacity is  " + mFreezerCapacity + " L and the Refrigerator Capacity is " + mRefrigeratorCapacity + " L.");
        }
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA*/

    private void getRoomNominalPower() {
        double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
        System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/

    private boolean getInputSensorFromRoomByList() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        mSensorList = mRoom.getmRoomSensorList();
        if (mSensorList.getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select a Sensor from the Chosen Room in order to Configure it: ");
        System.out.println(ctrl.printSensorList(mSensorList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
            this.mSensor = mSensorList.getSensorList().get(aux);
            this.mSensorName = mSensor.getName();
            System.out.println(mStringChosenSensor);
            System.out.println(mRoomConfigurationController.printSensor(mSensor));
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private boolean getInputTypeFromTypeListByList(List<TypeSensor> typeSensorList) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        System.out.println("Please select a Type of Sensor from the list: ");
        System.out.println(ctrl.printTypeList(typeSensorList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < typeSensorList.size()) {
            this.mTypeSensor = typeSensorList.get(aux);
            String typeSensorName = mTypeSensor.getName();
            System.out.println("You have chosen the following Type: " + typeSensorName);
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }
    private void getInput253() {
        Scanner input = new Scanner(System.in);
        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        this.mSensorName = input.nextLine();
        System.out.println("You entered sensor " + mSensorName);
        // Date Getter
        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid year. Try again");
        }
        this.mDataYear = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Month:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid month. Try again");
        }
        this.mDataMonth = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Day:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid day. Try again");
        }
        this.mDataDay = input.nextInt();
        System.out.println("You entered the date successfully!");
    }

    private void updateAndDisplay253() {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        SensorSettingsController mController = new SensorSettingsController();
        Date mDate = mController.createDate(this.mDataYear, this.mDataMonth, this.mDataDay);
        this.mSensor = mController.createRoomSensor(mSensorName, mTypeSensor, mDate);
        if (ctrl.addSensorToRoom(mRoom,mSensor)) {
            System.out.println("\nSensor successfully added to the Room " + mRoom.getRoomName());
        }
        else System.out.println("\nSensor already exists in the room.");
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




