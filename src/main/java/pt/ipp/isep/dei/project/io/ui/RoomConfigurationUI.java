package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;
import pt.ipp.isep.dei.project.model.device.devicetypes.*;*/

class RoomConfigurationUI {

    private House mHouse;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Device mDevice;
    private String mDeviceName;
    private double mNominalPower;


    private ProgramList mProgramList = new ProgramList();
    private String mProgramName;
    private double mDuration;
    private double mEnergyConsumption;
    private String mSensorName;
    private int mDataYear;
    private int mDataMonth;
    private int mDataDay;

    private String requestProgramName = "Please, type the new Program name:";
    private String requestProgramEnergyConsumption = "Please, type the new Program Energy Consumption:";
    private String requestProgramDuration = "Please, type the new Program duration:";

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house, List<TypeSensor> typeSensorList) {
        UtilsUI utils = new UtilsUI();
        this.mHouse = house;
        if (!utils.houseRoomListIsValid(this.mHouse)) {
            System.out.println(utils.invalidRoomList);
            return;
        }
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
                    runUS201();
                    activeInput = false;
                    break;
                case 2: //US210
                    runUS210(house);
                    activeInput = false;
                    break;
                case 3: //US215
                    runUS215(house);
                    activeInput = false;
                    break;
                case 4: //US222
                    runUS222(house);
                    activeInput = false;
                    break;
                case 5: //US230
                    runUS230();
                    activeInput = false;
                    break;
                case 6: //US250
                    runUS250();
                    activeInput = false;
                    break;
                case 7: //US253
                    runUS253(typeSensorList);
                    activeInput = false;
                    break;
                case 8: //US220
                    runUS220();
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


    private void runUS210(House house) {
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(house);
        String deviceType;
        try {
            deviceType = inputUtils.getInputDeviceTypeByList(house);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n Program will shut down.");
            return;
        }
        createDevice(deviceType, house);
    }


    private void createDevice(String deviceType, House house) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Scanner scanner = new Scanner(System.in);
        // get device name
        System.out.print("Please, type the name of the device: ");
        String deviceName = scanner.nextLine();
        //get nominal power
        System.out.print("Please, type the Nominal Power: ");
        InputUtils inputUtils = new InputUtils();
        double nominalPower = inputUtils.getInputAsDouble();
        //get Device specs
        String devicePath = ctrl.getDeviceTypePathToClassId(house, deviceType);
        Device device = ctrl.createDevice(deviceName, nominalPower, devicePath);
        ctrl.getAttributeName(device);
        for (int i = 0; i < ctrl.getAttributeName(device).size(); i++) {
            System.out.println("Please insert value for: " + ctrl.getAttributeName(device).get(i));
            double value = inputUtils.getInputAsDouble();
            ctrl.setAttributeValue(device, ctrl.getAttributeName(device).get(i), value);
        }

        //todo create a way to use the same logic as used above
        if (device.isProgrammable()) {
            System.out.println("This device is programmable.");
            //TODO ask user if he wants to add a program, if not, fisnish
            ProgramList pList = device.getProgramList();
            System.out.println("Please insert program name: ");
            String programName = scanner.nextLine();
            System.out.println("Please insert program duration: ");
            double duration = inputUtils.getInputAsDouble();
            System.out.println("Please insert program duration: ");
            double energyConsumption = inputUtils.getInputAsDouble();
            //TODO ask user if he wishes to add another program
            //todo move the program creation to controller to avoid model access on UI

            Program newProgram = new Program(programName, duration, energyConsumption);
            pList.addProgram(newProgram);

        }

        if (ctrl.addDevice(this.mRoom, device)) {
            System.out.println("You have successfully created a " + ctrl.getType(device) + " with the name " + deviceName + ". \n");

        } else {
            System.out.println("Device already exists in the room. Please, try again.\n");
        }
    }


    /**
     * runs US253, As an Administrator, I want to add a new sensor to a room from the list of available
     * sensor types, in order to configure it.
     *
     * @param typeSensorList is
     */
    private void runUS253(List<TypeSensor> typeSensorList) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.typeSensorListIsValid(typeSensorList)) {
            System.out.println(utilsUI.invalidTypeSensorList);
            return;
        }
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        TypeSensor typeSensor = inputUtils.getInputSensorTypeByList(typeSensorList);
        getInput253();
        updateAndDisplay253(typeSensor);
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

    private void updateAndDisplay253(TypeSensor typeSensor) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        SensorSettingsController mController = new SensorSettingsController();
        Date mDate = mController.createDate(this.mDataYear, this.mDataMonth, this.mDataDay);
        Sensor mSensor = mController.createRoomSensor(mSensorName, typeSensor, mDate);
        if (ctrl.addSensorToRoom(mRoom, mSensor)) {
            System.out.println("\nSensor successfully added to the Room " + mRoom.getRoomName());
        } else System.out.println("\nSensor already exists in the room.");
    }

    /**
     * US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * <p>
     * Prints device List in that room.
     */
    private void runUS201() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.houseRoomListIsValid(this.mHouse)) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        if (!utilsUI.roomDeviceListIsValid(this.mRoom)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        printRoomDeviceList();
    }

    private void printRoomDeviceList() {
        System.out.println("Available Devices in Room " + mRoom.getRoomName());
        System.out.println(mRoomConfigurationController.buildDeviceListString(mRoom));
    }


    // USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,so that I can reconfigure it.. - CARINA ALAS


    //* runs US215, As an Administrator, I want to edit the configuration of an existing device.

    private void runUS215(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        this.mRoom = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomDeviceListIsValid(this.mRoom)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        Device device = inputUtils.getInputRoomDevicesByList(this.mRoom);
        getInputDeviceCharacteristicsUS215(device);
       // updateDeviceUS215();
    }


    private void getInputDeviceCharacteristicsUS215( Device device) {
        Scanner scanner = new Scanner(System.in);

        if (device == null || mRoom == null) {
            System.out.println("There are no devices in this room.");
            return;
        }

        // get device name
        System.out.print("Please, type the new name of the device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoomConfigurationController.removeDevice(mRoom, device);
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);

        //get nominal power
        System.out.print("Please, type the new Nominal Power: ");

        this.mNominalPower = inputUtils.getInputAsDouble();

        device.getAttributeNames();
        for (int i = 0; i < device.getAttributeNames().size(); i++) {
            System.out.println("Please insert the value for: " + device.getAttributeNames().get(i));
            Double value = inputUtils.getInputAsDouble();
            device.setAttributeValue(device.getAttributeNames().get(i), value);
            if (device.isProgrammable()) {
                System.out.println("This device is programmable.");
                Program program;
                program = inputUtils.getSelectedProgramFromDevice(device);
                mProgramList = ((ProgramList) mRoomConfigurationController.getAttributeValueWashingMachine(device));
                if (program == null || mProgramList == null) {
                    System.out.println("There are no programs to edit.");
                    return;
                }
                updateDishWasherOrWashingMachine(program);
                mRoomConfigurationController.configureOneWashingMachineProgram(device, mProgramList);
            }
        }
        displayDeviceUS215(device);

     /*   if (mDevice.getType().equals("WaterHeater")) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");

            this.mVolumeOfWater = inputUtils.getInputAsDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");

            this.mHotWaterTemperature = inputUtils.getInputAsDouble();
            System.out.println("Please type the new Performance Ratio");
            this.mPerformanceRatio = inputUtils.getInputAsDouble();

        }
        if (mDevice.getType().equals("WashingMachine")) {
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
        if (mDevice.getType().equals("Dishwasher")) {
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

        if (mDevice.getType().equals("Fridge")) {
            System.out.print("Please, type the new Freezer Capacity in L for the Fridge:");

            this.mFreezerCapacity = inputUtils.getInputAsDouble();

            System.out.print("Please, type the new Refrigerator Capacity in L for the Fridge:");

            this.mRefrigeratorCapacity = inputUtils.getInputAsDouble();

            System.out.print("Please, type the new Annual Energy Consumption in kWh:");

            this.mAnnualEnergyConsumption = inputUtils.getInputAsDouble();
        }
        if (mDevice.getType().equals("Lamp")) {
            System.out.print("Please, type the new Luminous Flux in lm for the Lamp:");

            this.mLuminousFlux = inputUtils.getInputAsDouble();
        }
*/
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


    // US215 As an Administrator, I want to edit the configuration of an existing device, so that I can reconfigure it.
  /*  private void updateDeviceUS215() {
        if (mDevice == null || mRoom == null) {
            return;
        }
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);

        if (mDevice.getType().equals("WaterHeater")) {

            mRoomConfigurationController.configureOneHeater(mDevice, mVolumeOfWater, mHotWaterTemperature, mPerformanceRatio);
            System.out.println("device Configured.\n");
        }
        if (mDevice.getType().equals("WashingMachine")) {
            mRoomConfigurationController.configureOneWashingMachineCapacity(mDevice, mCapacity);
            mRoomConfigurationController.configureOneWashingMachineProgram(mDevice, mProgramList);
            System.out.println("device Configured.\n");

        }
        String deviceReconfigured = "Device reconfigured.\n";
        if (mDevice.getType().equals("Dishwasher")) {
            mRoomConfigurationController.configureOneDishWasherProgram(mDevice, mProgramList);
            mRoomConfigurationController.configureOneDishWasherCapacity(mDevice, mCapacity);
            System.out.println(deviceReconfigured);

        }
        if (mDevice.getType().equals("Fridge")) {
            mRoomConfigurationController.configureOneFridge(mDevice, mFreezerCapacity, mRefrigeratorCapacity);
            System.out.println(deviceReconfigured);

        }
        if (mDevice.getType().equals("Lamp")) {
            mRoomConfigurationController.configureOneLamp(mDevice, mLuminousFlux);
            System.out.println(deviceReconfigured);

        }
    }*/

    // US215 As an Administrator, I want to edit the configuration of an existing device, so that I can reconfigure it. - CARINA ALAS

    private void displayDeviceUS215(Device device) {
        if (device == null || mRoom == null) {
            return;
        }
        if (mRoom.addDevice(device)) {
            for (int i = 0; i < device.getAttributeNames().size(); i++) {
                System.out.println("You have changed the : " + device.getAttributeNames().get(i) + " to: "
                        + device.getAttributeValue(device.getAttributeNames().get(i)) + " "
                        + device.getAttributeUnit(device.getAttributeNames().get(i)) + ".");
            }
            System.out.println("\nYou have successfully changed the device name to " + mDeviceName + ". \n"
                    + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");

        } else {
            mRoom.addDevice(device);
            System.out.println("device already exists in the room. Please, try again.\n");
        }
    }

    /*US222 As a Power User, I want to deactivate a device, so that it is no longer used.
     Nevertheless, it should be possible to access its configuration and activity log.*/

    private void runUS222(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        Room room = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomDeviceListIsValid(room)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        Device device = inputUtils.getInputRoomDevicesByList(room);
        updateStateUS222(device);
    }

    private void updateStateUS222(Device device) {
        if (this.mRoomConfigurationController.deactivateDevice(device)) {
            System.out.println("Device successfully deactivated!");
        } else {
            System.out.println("It wasn't possible to deactivate the device.");
        }
    }

    /**
     * USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
     * nominal power of a room, i.e. the sum of the nominal power of all devices in the
     * room.
     **/
    private void runUS230() {
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        getRoomNominalPower();
    }

    private void getRoomNominalPower() {
        if (this.mRoom != null) {
            double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
            System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                    "from the sum of the nominal power of all devices in the room.");
        }
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/
    private void runUS250() {
        InputUtils inputUtils = new InputUtils();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        displaySensorListUS250();
    }

    private void displaySensorListUS250() {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.roomSensorListIsValid(mRoom)) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        SensorList sensorList = mRoom.getSensorList();
        System.out.println(mRoomConfigurationController.buildSensorListString(sensorList));
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    /* private boolean getInputTypeFromTypeListByList(List<TypeSensor> typeSensorList) {
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
*/

    private void runUS220() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        this.mRoom = inputUtils.getHouseRoomByList(this.mHouse);
        if (!utilsUI.roomDeviceListIsValid(this.mRoom)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        this.mDevice = inputUtils.getInputRoomDevicesByList(this.mRoom);
        removeDeviceUS220();
    }

    private void removeDeviceUS220() {
        if (mDevice == null || mRoom == null) {
            System.out.println("There are no devices in this room.");
            return;
        }
        RoomConfigurationController ctrl = new RoomConfigurationController();
        ctrl.removeDevice(mRoom, mDevice);
        System.out.println("The device " + mDevice.getName() + " on room " + mRoom.getRoomName() + " has ceased to be.");
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printRoomConfigMenu() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) I want do deactivate a device (US222)");
        System.out.println("5) Get the total nominal power of a room (US230)");
        System.out.println("6) Get a list of all sensors in a room (US250)");
        System.out.println("7) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("8) Delete an existing device (US220)");
        System.out.println("0) (Return to main menu)\n");
    }
}




