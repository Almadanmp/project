package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.programs.Program;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


class RoomConfigurationUI {
    private RoomConfigurationController mRoomConfigurationController;
    private String requestProgramName = "Please, type the new Program name:";

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house, TypeSensorList typeSensorList) {
        UtilsUI utils = new UtilsUI();
        if (!utils.houseRoomListIsValid(house)) {
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
            option = inputUtils.getInputAsInt();
            switch (option) {
                case 1: //US201
                    runUS201(house);
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
                case 4: //US220
                    runUS220(house);
                    activeInput = false;
                    break;
                case 5: //US222
                    runUS222(house);
                    activeInput = false;
                    break;
                case 6: //US230
                    runUS230(house);
                    activeInput = false;
                    break;
                case 7: //US250
                    runUS250(house);
                    activeInput = false;
                    break;
                case 8: //US253
                    runUS253(typeSensorList, house);
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
     * US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * <p>
     * Prints device List in that room.
     */
    private void runUS201(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.houseRoomListIsValid(house)) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        Room room = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomDeviceListIsValid(room)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        printRoomDeviceList(room);
    }

    private void printRoomDeviceList(Room room) {
        System.out.println("Available Devices in Room " + room.getRoomName());
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        System.out.println(mRoomConfigurationController.buildDeviceListString(room));
    }



    private void runUS210(House house) {
        InputUtils inputUtils = new InputUtils();
        Room room = inputUtils.getHouseRoomByList(house);
        DeviceType deviceType;
        deviceType = inputUtils.getInputDeviceTypeByList(house);
        createDevice(room, deviceType);
    }


    private void createDevice(Room room, DeviceType deviceType) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Scanner scanner = new Scanner(System.in);
        // get device name
        System.out.print("Please, type the name of the device: ");
        String deviceName = scanner.nextLine();
        InputUtils inputUtils = new InputUtils();
        //get DeviceTemporary specs
        DeviceTemporary device = ctrl.createDevice(deviceType);
        device.setName(deviceName);
        List<String> deviceAttributes = ctrl.getAttributeNames(device);
        for (int i = 0; i < deviceAttributes.size(); i++) {
            System.out.println("Please insert value for: " + deviceAttributes.get(i));
            Double value = inputUtils.getInputAsDouble();
            ctrl.setAttributeValue(device, deviceAttributes.get(i), value);
        }
        createProgram(device);
        if (ctrl.addDevice(room, device)) {
            System.out.println("You have successfully created a " + ctrl.getType(device) + " with the name " + deviceName + ". \n");
        } else {
            System.out.println("Device already exists in the room. Please, try again.\n");
        }
    }

    private void createProgram(DeviceTemporary device) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils();
        if (device.isProgrammable()) {
            {
                System.out.println("This device is programmable.");
                Program program = new Program("jjs", 23, 23);
                ProgramList programList = ((ProgramList) ctrl.getAttributeValueWashingMachine(device));
                System.out.println(requestProgramName);
                String programName = scanner.nextLine();
                for (int i = 0; i < program.getAttributeNames().size(); i++) {
                    System.out.println("Please insert the value for: " + program.getAttributeNames().get(i)
                            + " (" + program.getAttributeUnit(program.getAttributeNames().get(i)) + ")");
                    Double value = inputUtils.getInputAsDouble();
                    program.setAttributeValue(program.getAttributeNames().get(i), value);
                }
                program.setProgramName(programName);
                for (int i = 0; i < program.getAttributeNames().size(); i++) {
                    System.out.println("You have added the : " + program.getAttributeNames().get(i) + " to: "
                            + program.getAttributeValue(program.getAttributeNames().get(i)) + " "
                            + program.getAttributeUnit(program.getAttributeNames().get(i)) + ".");
                }
                String message = "Would you like to add another Program? (y/n)";
                String messageOutput = "You have added the : ";
                loopForProgram(message, device, messageOutput);
                ctrl.configureOneWashingMachineProgram(device, programList);
                ctrl.addProgramToProgramList(programList, program);
            }
        }

    }

    // USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,so that I can reconfigure it.. - CARINA ALAS

    //* runs US215, As an Administrator, I want to edit the configuration of an existing device.

    private void runUS215(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        Room room = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomDeviceListIsValid(room)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        DeviceTemporary device = inputUtils.getInputRoomDevicesByList(room);
        getInputDeviceCharacteristicsUS215(device, room, house);
    }


    private void getInputDeviceCharacteristicsUS215(DeviceTemporary device, Room room, House house) {
        Scanner scanner = new Scanner(System.in);
        if (device == null || room == null) {
            System.out.println("There are no devices in this room.");
            return;
        }

        // get device name
        System.out.print("Please, type the new name of the device: ");
        String deviceName = scanner.nextLine();

        //get room
        mRoomConfigurationController.removeDevice(room, device);
        InputUtils inputUtils = new InputUtils();
        room = inputUtils.getHouseRoomByList(house);
        mRoomConfigurationController.addDevice(room, device);
        device.getAttributeNames();
        for (int i = 0; i < device.getAttributeNames().size(); i++) {
            System.out.println("Please insert the value for: " + device.getAttributeNames().get(i)
                    + " (" + device.getAttributeUnit(device.getAttributeNames().get(i)) + ")");
            Double value = inputUtils.getInputAsDouble();
            device.setAttributeValue(device.getAttributeNames().get(i), value);
        }
        if (device.isProgrammable()) {
            System.out.println("This device is programmable.");
            Program program;
            program = inputUtils.getSelectedProgramFromDevice(device);
            ProgramList programList = ((ProgramList) mRoomConfigurationController.getAttributeValueWashingMachine(device));
            if (program == null || programList == null) {
                System.out.println("There are no programs to edit.");
                return;
            }
            updateAProgrammableDevice(program, programList, device);
            mRoomConfigurationController.configureOneWashingMachineProgram(device, programList);
        }
        displayDeviceUS215(device, room, deviceName);
    }

    private void updateAProgrammableDevice(Program program, ProgramList programList, DeviceTemporary device) {
        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils();
        System.out.println(requestProgramName);
        String programName = scanner.nextLine();
        for (int i = 0; i < program.getAttributeNames().size(); i++) {
            System.out.println("Please insert the value for: " + program.getAttributeNames().get(i)
                    + " (" + program.getAttributeUnit(program.getAttributeNames().get(i)) + ")");
            Double value = inputUtils.getInputAsDouble();
            program.setAttributeValue(program.getAttributeNames().get(i), value);
        }
        program.setProgramName(programName);
        for (int i = 0; i < program.getAttributeNames().size(); i++) {
            System.out.println("You have changed the : " + program.getAttributeNames().get(i) + " to: "
                    + program.getAttributeValue(program.getAttributeNames().get(i)) + " "
                    + program.getAttributeUnit(program.getAttributeNames().get(i)) + ".");
        }
        loopForProgramList(programList, device);

    }

    private void loopForProgramList(ProgramList programList, DeviceTemporary device) {
        String message = "Would you like to edit another Program? (y/n)";
        String messageOutput = "You have changed the : ";
        if (programList.getProgramList().size() > 1) {
            System.out.println(message);
            loopForProgram(message, device, messageOutput);
        }
    }

    private void loopForProgram(String message, DeviceTemporary device, String messageOutput) {
        InputUtils inputUtils = new InputUtils();
        Program program1;
        Scanner scanner = new Scanner(System.in);
        while (inputUtils.yesOrNo(scanner.nextLine(), message)) {
            program1 = inputUtils.getSelectedProgramFromDevice(device);
            System.out.println(requestProgramName);
            String programName = scanner.nextLine();
            for (int i = 0; i < program1.getAttributeNames().size(); i++) {
                System.out.println("Please insert the value for: " + program1.getAttributeNames().get(i)
                        + " (" + program1.getAttributeUnit(program1.getAttributeNames().get(i)) + ")");
                Double value = inputUtils.getInputAsDouble();
                program1.setAttributeValue(program1.getAttributeNames().get(i), value);
            }
            program1.setProgramName(programName);
            for (int i = 0; i < program1.getAttributeNames().size(); i++) {
                System.out.println(messageOutput + program1.getAttributeNames().get(i) + " to: "
                        + program1.getAttributeValue(program1.getAttributeNames().get(i)) + " "
                        + program1.getAttributeUnit(program1.getAttributeNames().get(i)) + ".");
            }
        }
    }

    // US215 As an Administrator, I want to edit the configuration of an existing device, so that I can reconfigure it. - CARINA ALAS
    private void displayDeviceUS215(DeviceTemporary device, Room room, String deviceName) {
        if (device == null || room == null) {
            return;
        }
        if (mRoomConfigurationController.addDevice(room, device)) {
            for (int i = 0; i < device.getAttributeNames().size(); i++) {
                System.out.println("You have changed the : " + device.getAttributeNames().get(i) + " to: "
                        + device.getAttributeValue(device.getAttributeNames().get(i)) + " "
                        + device.getAttributeUnit(device.getAttributeNames().get(i)) + ".");
            }
            System.out.println("\nYou have successfully changed the device name to " + deviceName + "." +
                    "\nThe room is " + room.getRoomName() + "\n");

        } else {
            mRoomConfigurationController.addDevice(room, device);
            System.out.println("Device already exists in the room. Please, try again.\n");
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
        DeviceTemporary device = inputUtils.getInputRoomDevicesByList(room);
        updateStateUS222(device);
    }

    private void updateStateUS222(DeviceTemporary device) {
        if (this.mRoomConfigurationController.deactivateDevice(device)) {
            System.out.println("Device successfully deactivated!");
        } else {
            System.out.println("It wasn't possible to deactivate the device. The device is already deactivated.");
        }
    }

    /**
     * USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
     * nominal power of a room, i.e. the sum of the nominal power of all devices in the
     * room.
     **/
    private void runUS230(House house) {
        InputUtils inputUtils = new InputUtils();
        Room room = inputUtils.getHouseRoomByList(house);
        getRoomNominalPower(room);
    }

    private void getRoomNominalPower(Room room) {
        double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(room);
        System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/
    private void runUS250(House house) {
        InputUtils inputUtils = new InputUtils();
        Room room = inputUtils.getHouseRoomByList(house);
        displaySensorListUS250(room);
    }

    private void displaySensorListUS250(Room room) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.roomSensorListIsValid(room)) {
            System.out.println(utilsUI.invalidSensorList);
            return;
        }
        SensorList sensorList = room.getSensorList();
        System.out.println(mRoomConfigurationController.buildSensorListString(sensorList));
    }


    /**
     * runs US253, As an Administrator, I want to add a new sensor to a room from the list of available
     * sensor types, in order to configure it.
     *
     * @param typeSensorList is
     */
    private void runUS253(TypeSensorList typeSensorList, House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.typeSensorListIsValid(typeSensorList)) {
            System.out.println(utilsUI.invalidTypeSensorList);
            return;
        }
        Room room = inputUtils.getHouseRoomByList(house);
        TypeSensor typeSensor = inputUtils.getInputSensorTypeByList(typeSensorList);
        getInput253(room, typeSensor);
    }

    private void getInput253(Room room, TypeSensor typeSensor) {
        Scanner input = new Scanner(System.in);
        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        String sensorName = input.nextLine();
        System.out.println("You entered sensor " + sensorName);
        // Date Getter
        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid year. Try again");
        }
        int dateYear = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Month:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid month. Try again");
        }
        int dateMonth = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Day:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid day. Try again");
        }
        int dateDay = input.nextInt();
        System.out.println("You entered the date successfully!");
        updateAndDisplay253(typeSensor, room, dateYear, dateMonth, dateDay, sensorName);

    }

    private void updateAndDisplay253(TypeSensor typeSensor, Room room, int dateYear, int dateMonth, int dateDay, String sensorName) {
        RoomConfigurationController ctrl = new RoomConfigurationController();
        SensorSettingsController mController = new SensorSettingsController();
        Date mDate = mController.createDate(dateYear, dateMonth, dateDay);
        Sensor mSensor = mController.createRoomSensor(sensorName, typeSensor, mDate);
        if (ctrl.addSensorToRoom(room, mSensor)) {
            System.out.println("\nSensor successfully added to the Room " + room.getRoomName());
        } else System.out.println("\nSensor already exists in the room.");
    }



    /*US220 - As an Administrator, I want to remove a device from a room, so that it is no longer used.
    Its activity log is also removed.
    MARIA MEIRELES*/
    private void runUS220(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        Room room = inputUtils.getHouseRoomByList(house);
        if (!utilsUI.roomDeviceListIsValid(room)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        DeviceTemporary device = inputUtils.getInputRoomDevicesByList(room);
        removeDeviceUS220(device, room);
    }

    private void removeDeviceUS220(DeviceTemporary device, Room room) {
        if (device == null || room == null) {
            System.out.println("There are no devices in this room.");
            return;
        }
        RoomConfigurationController ctrl = new RoomConfigurationController();
        ctrl.removeDevice(room, device);
        System.out.println("The device " + device.getName() + " on room " + room.getRoomName() + " has ceased to be.");
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printRoomConfigMenu() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) Delete an existing device (US220)");
        System.out.println("5) I want do deactivate a device (US222)");
        System.out.println("6) Get the total nominal power of a room (US230)");
        System.out.println("7) Get a list of all sensors in a room (US250)");
        System.out.println("8) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("0) (Return to main menu)\n");
    }
}

