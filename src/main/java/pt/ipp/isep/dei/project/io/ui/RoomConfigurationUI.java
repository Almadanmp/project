package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.program.FixedTimeProgram;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


class RoomConfigurationUI {
    private RoomConfigurationController controller;
    private String requestProgramName = "Please, type the new FixedTimeProgram name:";

    RoomConfigurationUI() {
        this.controller = new RoomConfigurationController();
    }

    void run(House house, TypeSensorList typeSensorList) {
        if (house.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printRoomConfigMenu();
            option = InputHelperUI.getInputAsInt();
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
                    runUS253(house, typeSensorList);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    /**
     * US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.
     * Prints device List in that room.
     */
    private void runUS201(House house) {
        if (house.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        Room room = InputHelperUI.getHouseRoomByList(house);
        if (room.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        printRoomDeviceList(room);
    }

    private void printRoomDeviceList(Room room) {
        System.out.println("Available Devices in Room " + room.getName());
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        System.out.println(controller.buildDeviceListString(room));
    }

    /**
     * US210 As an Administrator, I want to addWithoutPersisting a new device to a room from the list of available device types, so that I can configure it.
     *
     * @param house
     */

    private void runUS210(House house) {
        if (house.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        Room room = InputHelperUI.getHouseRoomByList(house);
        DeviceType deviceType = InputHelperUI.getInputDeviceTypeByList(house);
        createDevice(room, deviceType);
    }


    private void createDevice(Room room, DeviceType deviceType) {
        Scanner scanner = new Scanner(System.in);
        // get device name
        System.out.print("Please, type the name of the device: ");
        String deviceName = scanner.nextLine();

        //get Device specs
        Device device = controller.createDevice(deviceType);
        controller.setDeviceName(deviceName, device);
        List<String> deviceAttributes = controller.getAttributeNames(device);
        for (int i = 0; i < deviceAttributes.size(); i++) {
            System.out.println("Please insert value for: " + deviceAttributes.get(i));
            Double value = InputHelperUI.getInputAsDoublePositive();
            controller.setAttributeValue(device, deviceAttributes.get(i), value);
        }
        System.out.println("Please insert nominal power: ");
        controller.setNominalPowerDevice(device, InputHelperUI.getInputAsDoubleZeroOrPositive());

        createProgram(device);
        if (controller.addDevice(room, device)) {
            System.out.println("You have successfully created a " + controller.getType(device) + " with the name " + deviceName + ". \n");
        } else {
            System.out.println("Device already exists in the room. Please, try again.\n");
        }
    }

    private void createProgram(Device device) {
        Scanner scanner = new Scanner(System.in);
        if (device instanceof Programmable) {
            System.out.println("This device is programmable.");
            FixedTimeProgram program = new FixedTimeProgram("ProgramName", 23, 23);
            ProgramList programList = controller.getProgramList((Programmable) device);
            System.out.println(requestProgramName);
            String programName = scanner.nextLine();
            List<String> programAttributesNames = controller.getProgramAttributeNames(program);
            loopToSetAttributeValuesProgram(program, programAttributesNames);
            controller.setProgramName(program, programName);
            loopToBuildFinalStringProgram(program, programAttributesNames);
            String message = "Would you like to addWithoutPersisting another FixedTimeProgram? (y/n)";
            controller.addProgramToProgramList(programList, program);
            loopForCreatingProgram(message, programList);
            controller.configureDeviceProgramList(device, programList);
        }
    }

    // USER STORY 215 - As an Administrator, I want to edit the configuration of an existing device,so that I can reconfigure it.. - CARINA ALAS
    //* runs US215, As an Administrator, I want to edit the configuration of an existing device.

    private void runUS215(House house) {
        Room room = InputHelperUI.getHouseRoomByList(house);
        if (room.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        Device device = InputHelperUI.getInputRoomDevicesByList(room);
        getInputDeviceCharacteristicsUS215(device, room, house);
    }

    //* gets the input of the new device name, room, attributes and nominal power. If the device is programmable,
    // it shows the list of programs, and allows for the user to choose one or more to edit.
    private void getInputDeviceCharacteristicsUS215(Device device, Room room, House house) {
        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the device: ");
        String deviceName = scanner.nextLine();
        controller.setDeviceName(deviceName, device);
        //get room
        controller.removeDevice(room, device);
        Room room1;
        room1 = InputHelperUI.getHouseRoomByList(house);
        controller.addDevice(room1, device);
        List<String> attributeNames = controller.getAttributeNames(device);
        for (int i = 0; i < attributeNames.size(); i++) {
            System.out.println("Please insert the value for: " + attributeNames.get(i)
                    + " (" + controller.getAttributeUnit(device, i) + ")");
            Double value = InputHelperUI.getInputAsDoublePositive();
            controller.setAttributeValue(device, attributeNames.get(i), value);
        }
        System.out.println("Please insert the value for: Nominal Power (kW)");
        controller.setNominalPowerDevice(device, InputHelperUI.getInputAsDoubleZeroOrPositive());
        if (device instanceof Programmable) {
            System.out.println("This device is programmable.");
            ProgramList programList = controller.getProgramList((Programmable) device);
            if (programList.isEmpty()) {
                System.out.println(UtilsUI.INVALID_PROGRAM_LIST);
                return;
            }
            FixedTimeProgram program = InputHelperUI.getSelectedProgramFromDevice((Programmable) device);
            configureAProgrammableDevice(program, programList, (Programmable) device);
            controller.configureDeviceProgramList(device, programList);
        }
        displayDeviceUS215(device, room1, deviceName);
    }

    //configures the programs in the device's program list, if the device is indeed programmable.
    private void configureAProgrammableDevice(FixedTimeProgram program, ProgramList programList, Programmable device) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(requestProgramName);
        String programName = scanner.nextLine();
        List<String> programAttributeNames = controller.getProgramAttributeNames(program);
        loopToSetAttributeValuesProgram(program, programAttributeNames);
        controller.setProgramName(program, programName);
        loopToBuildFinalStringProgram(program, programAttributeNames);
        loopForProgramList(programList, device);
    }

    // US215 As an Administrator, I want to edit the configuration of an existing device, so that I can reconfigure it - CARINA ALAS
    // displays final string to the user.
    private void displayDeviceUS215(Device device, Room room, String deviceName) {
        List<String> attributeNames = controller.getAttributeNames(device);
        System.out.println("\nYou have successfully changed the device name to " + deviceName + "." +
                "\nThe room is " + room.getName() + "\n");
        for (int i = 0; i < attributeNames.size(); i++) {
            System.out.println("You have changed the : " + attributeNames.get(i) + " to: "
                    + controller.getAttributeValue(device, i) + " "
                    + controller.getAttributeUnit(device, i) + ".\n");
        }
    }

    // enters a loop if the device's program list has more than one program, and allows for the configuration of other
    // programs in the list (us215).
    private void loopForProgramList(ProgramList programList, Programmable device) {
        String message = "Would you like to edit another FixedTimeProgram? (y/n)";
        if (programList.size() > 1) {
            System.out.println(message);
            loopForEditingProgram(message, device);
        }
    }

    // enters a loop if the user chooses to edit another program of the existing programs in the list. (us215)
    private void loopForEditingProgram(String message, Programmable device) {
        FixedTimeProgram program;
        while (InputHelperUI.yesOrNo(message)) {
            program = InputHelperUI.getSelectedProgramFromDevice(device);
            loopForPrograms(program);
        }
    }

    // enters a loop if the user chooses to addWithoutPersisting another program. (us210)
    private void loopForCreatingProgram(String message, ProgramList programList) {
        while (InputHelperUI.yesOrNo(message)) {
            loopForCreatingPrograms(programList);
        }
    }

    //loop that sets all the attributes of the chosen program and then displays it.
    private void loopForPrograms(FixedTimeProgram program) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(requestProgramName);
        String programName = scanner.nextLine();
        List<String> programAttributeNames = controller.getProgramAttributeNames(program);
        loopToSetAttributeValuesProgram(program, programAttributeNames);
        controller.setProgramName(program, programName);
        loopToBuildFinalStringProgram(program, programAttributeNames);
    }

    //loop that sets all the attributes of the chosen program to configure it.
    private void loopToSetAttributeValuesProgram(FixedTimeProgram program2, List<String> programAttributeNames) {
        for (int i = 0; i < programAttributeNames.size(); i++) {
            System.out.println("Please insert the value for: " + programAttributeNames.get(i)
                    + " (" + controller.getProgramAttributeUnit(program2, i) + ")");
            Double value = InputHelperUI.getInputAsDoublePositive();
            controller.setProgramAttributeValue(program2, i, value);
        }
    }

    // loop that display all the edited attributes of the program.
    private void loopToBuildFinalStringProgram(FixedTimeProgram program, List<String> programAttributeNames) {
        for (int i = 0; i < programAttributeNames.size(); i++) {
            System.out.println("You have changed the : " + programAttributeNames.get(i) + " to: "
                    + controller.getProgramAttributeValue(program, i) + " "
                    + controller.getProgramAttributeUnit(program, i));
        }
    }

    //loop that creates new programs and configures them. (us210)
    private void loopForCreatingPrograms(ProgramList programList) {
        Scanner scanner = new Scanner(System.in);
        FixedTimeProgram program2 = new FixedTimeProgram();
        List<String> programAttributeNames = controller.getProgramAttributeNames(program2);
        System.out.println(requestProgramName);
        String programName = scanner.nextLine();
        loopToSetAttributeValuesProgram(program2, programAttributeNames);
        program2.setProgramName(programName);
        loopToBuildFinalStringProgram(program2, programAttributeNames);
        controller.addProgramToProgramList(programList, program2);

    }


    /*US222 As a Power User, I want to deactivate a device, so that it is no longer used.
     Nevertheless, it should be possible to access its configuration and activity log.*/

    private void runUS222(House house) {
        Room room = InputHelperUI.getHouseRoomByList(house);
        if (room.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        Device device = InputHelperUI.getInputRoomDevicesByList(room);
        updateStateUS222(device);
    }

    private void updateStateUS222(Device device) {
        if (controller.deactivateDevice(device)) {
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
        Room room = InputHelperUI.getHouseRoomByList(house);
        getRoomNominalPower(room);
    }

    private void getRoomNominalPower(Room room) {
        double roomNominalPower = controller.getRoomNominalPower(room);
        System.out.println("This room has a total nominal power of " + roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/
    private void runUS250(House house) {
        Room room = InputHelperUI.getHouseRoomByList(house);
        displaySensorListUS250(room);
    }

    private void displaySensorListUS250(Room room) {
        if (room.isSensorListEmpty()) {
            System.out.println(UtilsUI.INVALID_SENSOR_LIST);
            return;
        }
        SensorList sensorList = room.getSensorList();
        System.out.println(controller.buildSensorListString(sensorList));
    }


    /**
     * runs US253, As an Administrator, I want to addWithoutPersisting a new sensor to a room from the list of available
     * sensor types, in order to configure it.
     * <p>
     * //  * @param typeSensorList is
     */
    private void runUS253(House house, TypeSensorList typeSensorList) {
        if (typeSensorList.isEmpty()) {
            System.out.println(UtilsUI.INVALID_TYPE_SENSOR_LIST);
            return;
        }
        Room room = InputHelperUI.getHouseRoomByList(house);
        TypeSensor typeSensor = InputHelperUI.getInputSensorTypeByList(typeSensorList);
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
        SensorSettingsController sensorSettingsController = new SensorSettingsController();
        Date mDate = sensorSettingsController.createDate(dateYear, dateMonth, dateDay);
        Sensor mSensor = sensorSettingsController.createRoomSensor(sensorName, typeSensor, mDate);
        if (controller.addSensorToRoom(mSensor, room)) {
            System.out.println("\nSensor successfully added to the Room " + room.getName());
        } else System.out.println("\nSensor already exists in the room.");
    }


    /*US220 - As an Administrator, I want to removeGeographicArea a device from a room, so that it is no longer used.
    Its activity log is also removed.
    MARIA MEIRELES*/
    private void runUS220(House house) {
        Room room = InputHelperUI.getHouseRoomByList(house);
        if (room.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        Device device = InputHelperUI.getInputRoomDevicesByList(room);
        removeDeviceUS220(device, room);
    }

    private void removeDeviceUS220(Device device, Room room) {
        if (device == null || room == null) {
            System.out.println("There are no devices in this room.");
            return;
        }
        controller.removeDevice(room, device);
        System.out.println("The device " + device.getName() + " on room " + room.getName() + " has ceased to be.");
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