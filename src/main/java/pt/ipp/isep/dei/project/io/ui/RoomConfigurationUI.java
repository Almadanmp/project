package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Program;
import pt.ipp.isep.dei.project.model.device.ProgramList;
import pt.ipp.isep.dei.project.model.device.devicetypes.*;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Device mDevice;
    private DeviceType mDeviceType;
    private double mNominalPower;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mPerformanceRatio;
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mCapacity;
    private double mLuminousFlux;
    private String mDeviceName;
    private ProgramList mProgramList = new ProgramList();
    private String mProgramName;
    private double mDuration;
    private double mEnergyConsumption;
    private String mSensorName;
    private TypeSensor mTypeSensor;
    private int mDataYear;
    private int mDataMonth;
    private int mDataDay;
    private String requestProgramName = "Please, type the new Program name:";
    private String requestProgramEnergyConsumption = "Please, type the new Program Energy Consumption:";
    private String requestProgramDuration = "Please, type the new Program duration:";

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house, List<DeviceType> deviceTypeList, List<TypeSensor> typeSensorList) {
        UtilsUI utilsUI = new UtilsUI();
        if (utilsUI.houseIsNull(house)) {
            System.out.println(utilsUI.invalidHouse);
            return;
        }
        this.mHouse = house;
        List<Room> roomList = mHouse.getRoomList();
        if (roomList == null || roomList.isEmpty()) {
            System.out.println("There are no available rooms in the house. Please add a room to continue.");
            return;
        }
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
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    printRoomDeviceList();
                    activeInput = false;
                    break;
                case 2: //US210
                    runUS210(deviceTypeList);
                    activeInput = false;
                    break;
                case 3: //US215
                    runUS215();
                    activeInput = false;
                    break;
                case 4: //US230
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    getRoomNominalPower();
                    activeInput = false;
                    break;
                case 5: //US250
                    this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
                    displaySensorListUS250();
                    activeInput = false;
                    break;
                case 6: //US253
                    runUS253(typeSensorList);
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

    /**
     * runs US210, created to avoid having several lines in case 2.
     * @param deviceTypeList
     */
    private void runUS210(List<DeviceType> deviceTypeList) {
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        if (getInputDeviceTypeByList(deviceTypeList)) {
            return;
        }
        createDevice();
        displayDeviceUS210();
    }

    /**
     *runs US215, created to avoid having several lines in case 3.
     */
    private void runUS215() {
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        this.mDevice = inputUtils.getInputDeviceByList(this.mRoom);
        getInputDeviceCharacteristicsUS215();
        updateDeviceUS215();
        displayDeviceUS215();
    }

    /**
     * runs US253, created to avoid having several lines in case 6.
     * @param typeSensorList
     */
    private void runUS253( List<TypeSensor> typeSensorList){
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        if (getInputTypeFromTypeListByList(typeSensorList)) {
            return;
        }
        getInput253();
        updateAndDisplay253();
    }
    /**
     * US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * <p>
     * Prints device List in that room.
     */
    private void printRoomDeviceList() {
        System.out.println("Available Devices in Room " + mRoom.getRoomName());
        System.out.println(mRoomConfigurationController.buildDeviceListString(mRoom));
    }

     /* USER STORY 210 - As an Administrator, I want to add a new device to a room from the list of available
    device types, so that I can configure it. - MARIA MEIRELES */

    /**
     * @param deviceTypeList is a list of device types
     * @return prints a list of available device types by index if the list of device types is not empty
     */
    private boolean getInputDeviceTypeByList(List<DeviceType> deviceTypeList) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (deviceTypeList.isEmpty()) {
            System.out.println("device types list is empty.");
            return true;
        }
        System.out.println("Please select one of the device Types: ");
        System.out.println(mRoomConfigurationController.buildDeviceTypeListString(deviceTypeList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < DeviceType.values().length) {
            this.mDeviceType = DeviceType.values()[aux];
            return false;
        } else {
            System.out.println(utils.invalidOption);
            return true;
        }
    }

    /**
     * Asks for input from the user in order to construct a new device with its parameters
     * (name, nominal power and device specs (according to the selected device Type)
     */

    private void createDevice() {
        this.mDevice = new Device();
        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the device: ");
        mDeviceName = scanner.nextLine();

        //get nominal power
        System.out.print("Please, type the new Nominal Power: ");
        InputUtils inputUtils = new InputUtils();

        this.mNominalPower = inputUtils.getInputAsDouble();

        //device Type
        if (this.mDeviceType == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");

            this.mVolumeOfWater = inputUtils.getInputAsDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");

            this.mHotWaterTemperature = inputUtils.getInputAsDouble();
            System.out.print("Please, type the Performance Ration of the Water Heater: ");

            this.mPerformanceRatio = inputUtils.getInputAsDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new WaterHeater(mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio));
        }

        if (this.mDeviceType == DeviceType.FRIDGE) {
            System.out.print("Please, type the Freezer Capacity in L for the Fridge:");

            this.mFreezerCapacity = inputUtils.getInputAsDouble();
            System.out.print("Please, type the Refrigerator Capacity in L for the Fridge:");

            this.mRefrigeratorCapacity = inputUtils.getInputAsDouble();
            System.out.print("Please, type the new Annual Energy Consumption in kWh:");

            this.mAnnualEnergyConsumption = inputUtils.getInputAsDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new Fridge(mFreezerCapacity, mRefrigeratorCapacity, mAnnualEnergyConsumption));
        }

        if (this.mDeviceType == DeviceType.WASHING_MACHINE) {
            createAWashingMachineOrADishWasher();
            mDevice = new Device(mDeviceName, mNominalPower, new WashingMachine(mCapacity, mProgramList));
        }

        if (this.mDeviceType == DeviceType.DISHWASHER) {
            createAWashingMachineOrADishWasher();
            mDevice = new Device(mDeviceName, mNominalPower, new Dishwasher(mCapacity, mProgramList));
        }

        if (this.mDeviceType == DeviceType.LAMP) {
            System.out.print("Please, type the new Luminous Flux in lm for the Lamp:");

            this.mLuminousFlux = inputUtils.getInputAsDouble();
            mDevice = new Device(mDeviceName, mNominalPower, new Lamp(mLuminousFlux));
        }
    }

    private void createAWashingMachineOrADishWasher() {
        InputUtils inputUtils = new InputUtils();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, type the Capacity in Kg for the Washing Machine: ");
        this.mCapacity = inputUtils.getInputAsDouble();
        Program program;
        System.out.println(requestProgramName);
        this.mProgramName = scanner.nextLine();
        System.out.println(requestProgramDuration);
        this.mDuration = inputUtils.getInputAsDouble();
        System.out.println(requestProgramEnergyConsumption);
        this.mEnergyConsumption = inputUtils.getInputAsDouble();
        program = new Program(mProgramName, mDuration, mEnergyConsumption);
        mProgramList.addProgram(program);
        loopForProgramsCreation();
    }

    private void loopForProgramsCreation() {
        InputUtils inputUtils = new InputUtils();
        Program program1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add a new Program? (y/n)");
        while (inputUtils.yesOrNo(scanner.nextLine(), "Would you like to add a new Program? (y/n)")) {
            System.out.println(requestProgramName);
            this.mProgramName = scanner.nextLine();
            System.out.println(requestProgramDuration);
            this.mDuration = inputUtils.getInputAsDouble();
            System.out.println(requestProgramEnergyConsumption);
            this.mEnergyConsumption = inputUtils.getInputAsDouble();
            program1 = new Program(mProgramName, mDuration, mEnergyConsumption);
            mProgramList.addProgram(program1);
        }
    }

    /**
     * Displays a string with the new created device and its parameters.
     * Adds the new created device to the selected room
     */

    private void displayDeviceUS210() {
        if (mRoom.addDevice(mDevice)) {
            System.out.println("You have successfully created a " + mDeviceType.buildDeviceTypeString(mDeviceType) + " with the name " + mDeviceName + ". \n"
                    + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + ".");
            if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
                System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                        mHotWaterTemperature + " ºC, and the Performance Ratio is: "
                        + mPerformanceRatio + ".");
            }
            if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
                System.out.println("The Capacity is " + mCapacity + " Kg.");
            }
            if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
                System.out.println("The Freezer Capacity is  " + mFreezerCapacity + " L, the Refrigerator Capacity is " + mRefrigeratorCapacity +
                        " L and the " + mAnnualEnergyConsumption + " kWh.");
            }
            if (mDevice.getDeviceType() == DeviceType.LAMP) {
                System.out.println("The Luminous Flux is " + mLuminousFlux + " lm.");
            }
            mDevice.setmParentRoom(mRoom);
        } else {
            System.out.println("device already exists in the room. Please, try again.\n");
        }
    }

    /* USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,
    so that I can reconfigure it.. - CARINA ALAS */

    private void getInputDeviceCharacteristicsUS215() {
        Scanner scanner = new Scanner(System.in);

        if (mDevice == null || mRoom == null) {
            System.out.println("There are no devices in this room.");
            return;
        }

        // get device name
        System.out.print("Please, type the new name of the device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoomConfigurationController.removeDeviceFromRoom(mRoom, mDevice);
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        mDevice.setmParentRoom(mRoom);

        mRoomConfigurationController.addDeviceToRoom(mRoom, mDevice);
        //get nominal power
        System.out.print("Please, type the new Nominal Power: ");

        this.mNominalPower = inputUtils.getInputAsDouble();

        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");

            this.mVolumeOfWater = inputUtils.getInputAsDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");

            this.mHotWaterTemperature = inputUtils.getInputAsDouble();
            System.out.println("Please type the new Performance Ratio");
            this.mPerformanceRatio = inputUtils.getInputAsDouble();

        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            System.out.print("Please, type the new Capacity in Kg for the Washing Machine: ");

            this.mCapacity = inputUtils.getInputAsDouble();


            Program program;
            program = inputUtils.getSelectedProgramFromDevice(mDevice);
            mProgramList = ((ProgramList) mRoomConfigurationController.getAttributeValueWashingMachine(mDevice));
            if (program == null || mProgramList == null) {
                System.out.println("There are no programs to edit.");
                return;
            }
            updateDishWasherOrWashingMachine(program);
            mRoomConfigurationController.configureOneWashingMachineProgram(mDevice, mProgramList);


        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.print("Please, type the new Capacity in Kg for the Dishwasher:");

            this.mCapacity = inputUtils.getInputAsDouble();
            Program program;
            program = inputUtils.getSelectedProgramFromDevice(mDevice);
            mProgramList = ((ProgramList) mRoomConfigurationController.getAttributeValueWashingMachine(mDevice));
            if (program == null || mProgramList == null) {
                System.out.println("There are no programs to edit.");
                return;
            }
            updateDishWasherOrWashingMachine(program);
            mRoomConfigurationController.configureOneDishWasherProgram(mDevice, mProgramList);

        }

        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.print("Please, type the new Freezer Capacity in L for the Fridge:");

            this.mFreezerCapacity = inputUtils.getInputAsDouble();

            System.out.print("Please, type the new Refrigerator Capacity in L for the Fridge:");

            this.mRefrigeratorCapacity = inputUtils.getInputAsDouble();

            System.out.print("Please, type the new Annual Energy Consumption in kWh:");

            this.mAnnualEnergyConsumption = inputUtils.getInputAsDouble();
        }
        if (mDevice.getDeviceType() == DeviceType.LAMP) {
            System.out.print("Please, type the new Luminous Flux in lm for the Lamp:");

            this.mLuminousFlux = inputUtils.getInputAsDouble();
        }

    }

    private void updateDishWasherOrWashingMachine(Program program) {
        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils();
        mProgramList.removeProgram(program);
        System.out.println(requestProgramName);
        this.mProgramName = scanner.nextLine();
        System.out.println(requestProgramDuration);
        this.mDuration = inputUtils.getInputAsDouble();
        System.out.println(requestProgramEnergyConsumption);
        this.mEnergyConsumption = inputUtils.getInputAsDouble();
        program = new Program(mProgramName, mDuration, mEnergyConsumption);
        mProgramList.addProgram(program);
        loopForPrograms();

    }

    private void loopForPrograms() {
        InputUtils inputUtils = new InputUtils();
        Program program1;
        Scanner scanner = new Scanner(System.in);
        if (mProgramList.getProgramList().size() > 1) {
            System.out.println("Would you like to edit another Program? (y/n)");
            while (inputUtils.yesOrNo(scanner.nextLine(), "Would you like to edit another Program? (y/n)")) {
                program1 = inputUtils.getSelectedProgramFromDevice(mDevice);
                mProgramList.removeProgram(program1);
                System.out.println(requestProgramName);
                this.mProgramName = scanner.nextLine();
                System.out.println(requestProgramDuration);
                this.mDuration = inputUtils.getInputAsDouble();
                System.out.println(requestProgramEnergyConsumption);
                this.mEnergyConsumption = inputUtils.getInputAsDouble();
                program1 = new Program(mProgramName, mDuration, mEnergyConsumption);
                mProgramList.addProgram(program1);
            }
        }
    }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void updateDeviceUS215() {
        if (mDevice == null || mRoom == null) {
            return;
        }
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);

        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {

            mRoomConfigurationController.configureOneHeater(mDevice, mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio);
            System.out.println("device Configured.\n");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            mRoomConfigurationController.configureOneWashingMachineCapacity(mDevice, mCapacity);
            mRoomConfigurationController.configureOneWashingMachineProgram(mDevice, mProgramList);
            System.out.println("device Configured.\n");

        }
        String deviceReconfigurated = "Device reconfigured.\n";
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            mRoomConfigurationController.configureOneDishWasherProgram(mDevice, mProgramList);
            mRoomConfigurationController.configureOneDishWasherCapacity(mDevice, mCapacity);
            System.out.println(deviceReconfigurated);

        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            mRoomConfigurationController.configureOneFridge(mDevice, mFreezerCapacity, mRefrigeratorCapacity);
            System.out.println(deviceReconfigurated);

        }
        if (mDevice.getDeviceType() == DeviceType.LAMP) {
            mRoomConfigurationController.configureOneLamp(mDevice, mLuminousFlux);
            System.out.println(deviceReconfigurated);

        }
    }

    /* US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it. - CARINA ALAS*/

    private void displayDeviceUS215() {
        if (mDevice == null || mRoom == null) {
            return;
        }
        System.out.println("\nYou have successfully changed the device name to " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, and the Performance Ratio is: "
                    + mPerformanceRatio + ".");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is " + mCapacity + " Kg." + "\nThe following programs were reconfigured: "
                    + "\n" + mProgramList.buildProgramListStringForEach());
        }
        if (mDevice.getDeviceType() == DeviceType.FRIDGE) {
            System.out.println("The freezer Capacity is  " + mFreezerCapacity + " L, the Refrigerator Capacity is " + mRefrigeratorCapacity +
                    " L and the " + mAnnualEnergyConsumption + " kWh.");
        }
        if (mDevice.getDeviceType() == DeviceType.LAMP) {
            System.out.println("The Luminous Flux is " + mLuminousFlux + " lm.");
        }
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA*/

    private void getRoomNominalPower() {
        if (this.mRoom != null) {
            double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
            System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                    "from the sum of the nominal power of all devices in the room.");
        }
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/

    private void displaySensorListUS250() {
        SensorList sensorList = mRoom.getmRoomSensorList();
        System.out.println(mRoomConfigurationController.buildSensorListString(sensorList));
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private boolean getInputTypeFromTypeListByList(List<TypeSensor> typeSensorList) {
        if (typeSensorList.isEmpty()) {
            System.out.println("There's no defined types of sensor available yet. Please define one first.");
            return true;
        }
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        System.out.println("Please select a Type of Sensor from the list: ");
        System.out.println(ctrl.buildTypeListString(typeSensorList));
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
        Sensor mSensor = mController.createRoomSensor(mSensorName, mTypeSensor, mDate);
        if (ctrl.addSensorToRoom(mRoom, mSensor)) {
            System.out.println("\nSensor successfully added to the Room " + mRoom.getRoomName());
        } else System.out.println("\nSensor already exists in the room.");
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




