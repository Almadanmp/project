package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyConsumptionController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.device.log.LogList;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


class EnergyConsumptionUI {
    private EnergyConsumptionController controller;
    private Scanner returnToConsole = new Scanner(System.in);
    private static final String INSERT_START_DATE = "PLEASE INSERT THE START OF THE INTERVAL:";
    private static final String INSERT_END_DATE = "PLEASE INSERT THE END OF THE INTERVAL:";

    EnergyConsumptionUI() {
        this.controller = new EnergyConsumptionController();
    }

    void run(House programHouse) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy Consumption Monitoring\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printOptionMessage();
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS172(programHouse);
                    activeInput = false;
                    break;
                case 2:
                    runUS705(programHouse);
                    activeInput = false;
                    break;
                case 3:
                    runUS720(programHouse);
                    activeInput = false;
                    break;
                case 4:
                    runUS721(programHouse);
                    activeInput = false;
                    break;
                case 5:
                    runUS722(programHouse);
                    activeInput = false;
                    break;
                case 6:
                    runUS730(programHouse);
                    activeInput = false;
                    break;
                case 7:
                    runUS752(programHouse);
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

    // USER STORY 172 - As a Power User [or Administrator], I want to know the total nominal power
    // connected to a grid, i.e. the sum of the nominal power of all devices in all rooms
    // in the grid.

    private void runUS172(House house) {
        if (house.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid grid = InputHelperUI.getInputGridByList(house);
        if (grid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        double nominalPower = updateUS172(grid);
        displayUS172(nominalPower);
    }

    private double updateUS172(EnergyGrid grid) {
        EnergyConsumptionController mController = new EnergyConsumptionController();
        return mController.getTotalPowerFromGrid(grid);
    }

    private void displayUS172(Double nomPower) {
        System.out.println(" The sum of the Nominal Power of all the devices connected to this Energy Grid is " + nomPower + " kW.\n");
        InputHelperUI.returnToMenu(returnToConsole);
    }

    // US705 - As a Power User, I want to know the total nominal power of a subset of rooms
    // and/or devices of my choosing connected to a grid.

    private void runUS705(House programHouse) {
        if (programHouse.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid grid = InputHelperUI.getInputGridByList(programHouse);
        RoomList selectedRooms = new RoomList();
        DeviceList selectedDevices = new DeviceList();
        while (true) {
            printSelection(selectedDevices, selectedRooms);
            System.out.println("\nWhat would you like to select? \n\n 1) Select / Deselect a Room (and all its devices); " +
                    "\n 2) Select / Deselect a device; \n 3) Get the Total Nominal Power of the currently selected subset; " +
                    "\n 4) Return to main menu;\n ");
            int option;
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    allRoomDevicesSelection(grid, selectedRooms, selectedDevices);
                    break;
                case 2:
                    devicesSelection(grid, selectedRooms, selectedDevices);
                    break;
                case 3:
                    printSelection(selectedDevices, selectedRooms);
                    printSelectionNominalPower(selectedDevices);
                    InputHelperUI.returnToMenu(returnToConsole);
                    break;
                case 4:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }

    private void allRoomDevicesSelection(EnergyGrid grid, RoomList selectedRooms, DeviceList selectedDevices) {
        boolean active = true;
        while (active) {
            printSelection(selectedDevices, selectedRooms);
            selectRooms(grid, selectedRooms, selectedDevices);
            active = continuePrompt();
        }
    }

    private void devicesSelection(EnergyGrid grid, RoomList selectedRooms, DeviceList selectedDevices) {
        boolean active = true;
        while (active) {
            printSelection(selectedDevices, selectedRooms);
            selectDevices(grid, selectedDevices);
            active = continuePrompt();
        }
    }

    /**
     * Prints currently selected devices and rooms.
     *
     * @param selectedDevices the list of currently selected devices.
     * @param selectedRooms   the list of currently selected rooms.
     */

    private void printSelection(DeviceList selectedDevices, RoomList selectedRooms) {
        if (selectedDevices.isEmpty() && selectedRooms.isEmpty()) {
            System.out.println("You haven't selected any rooms or devices yet.");
        } else
            System.out.println("\nYou have already selected the following rooms:\n" + "\n" + selectedRooms.buildString()
                    + "\n" + "You have already selected the following devices:\n\n" + selectedDevices.buildString() + ".");
    }

    /**
     * Allows user to select multiple rooms, or deselect them if already selected.
     *
     * @param grid            is the grid we want to select rooms from.
     * @param selectedRooms   is the rooms already selected.
     * @param selectedDevices is the devices already selected, including devices contained in the rooms already selected.
     */

    private void selectRooms(EnergyGrid grid, RoomList selectedRooms, DeviceList selectedDevices) {
        Room r1 = InputHelperUI.getGridRoomByList(grid);
        if (selectedRooms.contains(r1)) {
            String duplicateRoom = "That room is already selected. Would you like to removeGeographicArea it from the list? (Y/N)\n";
            System.out.println(duplicateRoom);
            if (InputHelperUI.yesOrNo(duplicateRoom)) {
                controller.removeRoomFromList(r1, selectedRooms);
                controller.removeRoomDevicesFromDeviceList(r1, selectedDevices);
                System.out.println("The room and its devices have been deselected.");
            }
            return;
        }
        controller.addRoomToList(r1, selectedRooms);
        controller.addRoomDevicesToDeviceList(r1, selectedDevices);
        System.out.println("You have selected the room: " + r1.buildString());
    }

    /**
     * Allows user to select multiple devices, or deselect them if already selected.
     *
     * @param grid            is the grid we want to select devices from.
     * @param selectedDevices is the devices already selected, including devices contained in the rooms already selected.
     */

    private void selectDevices(EnergyGrid grid, DeviceList selectedDevices) {
        Device d1 = InputHelperUI.getGridDevicesByList(grid);
        if (selectedDevices.containsDevice(d1)) {
            String duplicateDevice = "That device is already on the list. Would you like to deselect the device? (Y/N)\n";
            System.out.println(duplicateDevice);
            if (InputHelperUI.yesOrNo(duplicateDevice)) {
                controller.removeDeviceFromList(d1, selectedDevices);
                System.out.println("The device has been deselected.");
            }
            return;
        }
        if (d1 != null) {
            controller.addDeviceToList(d1, selectedDevices);
        }
    }

    /**
     * Asks user if he wants to select more rooms or devices.
     *
     * @return true if yes, false if no.
     */

    private boolean continuePrompt() {
        String prompt = "Would you like to continue choosing? (y/n)";
        System.out.println(prompt);
        return InputHelperUI.yesOrNo(prompt);
    }

    /**
     * Prints the value of the totalNominalPower of selected subset.
     *
     * @param selectedDevices selected subset.
     */

    private void printSelectionNominalPower(DeviceList selectedDevices) {
        double totalPower = controller.getSelectionNominalPower(selectedDevices);
        System.out.println("The total nominal power for the selected subset is " + totalPower + " kW/h.");
    }

    /* US720 As a Power User [or Administrator], I want to know the total metered energy consumption of a device in a
     * given time interval, i.e. the sum of the energy consumption of the device in the interval.
     * Only metering periods fully contained in the interval will be included.
     * One cannot know the exact energy consumption of devices not connected to an energy meter.
     */

    /**
     * This run makes the validation of the Room Device  List and the Device  Log List.
     * Then it calls the controller to get the total metered energy consumption for the given time interval.
     *
     * @param house - Is the parameter which is used to get all the parameters needed for this User Story (720)
     */

    private void runUS720(House house) {
        UtilsUI utilsUI = new UtilsUI();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(house);
        if (!utilsUI.roomDTODeviceListIsValid(room, house)) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        Device device = InputHelperUI.getInputRoomDTODevicesByList(room, house);
        if (device.isLogListEmpty()) {
            System.out.println("This device has no energy consumption logs.");
            return;
        }
        System.out.println("Insert the Date in which you want your consumption data gathering to begin: ");
        Date initialTime = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Insert the Date in which you want your consumption data gathering to stop: ");
        Date finalTime = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Device : " + device.getName() + "\n" + "Between " + initialTime + " and " + finalTime +
                "\n" + "");
        controller.getDeviceConsumptionInInterval(device, initialTime, finalTime);
    }

    /* US721As a Power User [or Administrator], I want to know the total metered energy consumption of a room in a
       given time interval, i.e. the sum of the energy consumption of all energy-metered devices in the room in
       the interval.
     */

    private void runUS721(House programHouse) {
        if (programHouse.isRoomListEmpty()) {
            System.out.print(UtilsUI.INVALID_ROOM_LIST);
        }
        Room room = InputHelperUI.getHouseRoomByList(programHouse);
        System.out.println("Please insert the date at which you want to start the interval.");
        Date initialDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please insert the date at which you want to end the interval.");
        Date finalDate = DateUtils.getInputYearMonthDayHourMin();
        double result = controller.getRoomConsumptionInInterval(room, initialDate, finalDate);
        System.out.println("The total energy consumption of the selected room in the selected interval is: " + result);
    }

    /*US722 As a Power User [or Administrator], I want to know the total metered energy consumption of a grid in a
    given time interval, i.e. the sum of the energy consumption of all energy-metered rooms in the grid in the
    interval.*/

    private void runUS722(House programHouse) {
        EnergyGridList gridList = controller.getHouseGridList(programHouse);
        if (gridList.isEmpty()) {
            System.out.println("Your house has no Grids.\nReturning to main menu.");
            return;
        }
        EnergyGrid eGrid = InputHelperUI.getInputGridByList(programHouse);
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println("Please insert the date at which you want to start the interval.");
        Date initialDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println("Please insert the date at which you want to end the interval.");
        Date finalDate = DateUtils.getInputYearMonthDayHourMin();
        double result = controller.getGridConsumptionInInterval(eGrid, initialDate, finalDate);
        System.out.println("\n" + "Between " + initialDate + " and " + finalDate +
                "\n" + "The total energy consumption of the " + eGrid.getName() + " in the selected interval is: " + df.format(result) + " kW");
    }

    /* US730
     *  As a Power User [or Administrator], I want to have the data series necessary to design an energy
     *  consumption chart of the metered energy consumption of a device/room/grid in a given time interval.
     */

    private void runUS730(House programHouse) {
        this.printUS730Menu();
        int option = InputHelperUI.getInputAsInt();
        switch (option) {
            case 1:
                setGridData(programHouse);
                break;
            case 2:
                setRoomData(programHouse);
                break;
            case 3:
                setDeviceData(programHouse);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;

        }
    }

    private void setGridData(House programHouse) {
        EnergyGrid grid = InputHelperUI.getInputGridByList(programHouse);
        System.out.println(INSERT_START_DATE);
        Date startDate = DateUtils.getInputYearMonthDayHourMin();
        System.out.println(INSERT_END_DATE);
        Date endDate = DateUtils.getInputYearMonthDayHourMin();
        LogList gridLogs = controller.getGridLogsInInterval(grid, startDate, endDate);
        System.out.println(controller.buildLogListString(gridLogs));

    }

    private void setRoomData(House programHouse) {
        RoomDTO case2Room = InputHelperUI.getHouseRoomDTOByList(programHouse);
        Date startDate = requestStartDate();
        Date endDate = requestEndDate();
        LogList roomLogs = controller.getRoomLogsInInterval(case2Room, startDate, endDate, programHouse);
        System.out.println(controller.buildLogListString(roomLogs));
    }

    private void setDeviceData(House programHouse) {
        RoomDTO case3Room = InputHelperUI.getHouseRoomDTOByList(programHouse);
        Device device = InputHelperUI.getInputRoomDTODevicesByList(case3Room, programHouse);
        Date startDate = requestStartDate();
        Date endDate = requestEndDate();
        LogList deviceLogs = controller.getDeviceLogsInInterval(device, startDate, endDate);
        System.out.println(controller.buildLogListString(deviceLogs));
    }

    private Date requestStartDate() {
        System.out.println(INSERT_START_DATE);
        return DateUtils.getInputYearMonthDayHourMin();
    }

    private Date requestEndDate() {
        System.out.println(INSERT_END_DATE);
        return DateUtils.getInputYearMonthDayHourMin();
    }

    /*
     * US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */

    private void runUS752(House house) {
        List<Device> waterHeaters = controller.getWaterHeaterDeviceList(house).getList();
        if (waterHeaters.isEmpty()) {
            System.out.println("Your house has no Electric Water Heaters. Returning to Main Menu.");
            return;
        }
        System.out.println("You currently have " + waterHeaters.size() + " electric water heaters in your house:\n");
        for (Device d : waterHeaters) {
            System.out.println("Water Heater name: " + controller.getWHName(d) + ".\n");
            System.out.println("Please insert the cold water temperature for Water Heater " + controller.getWHName(d) + ":");
            double coldWaterTemperature = InputHelperUI.getInputAsDouble();
            System.out.println("Please insert the volume of water to heat for Water Heater " + d.getName() + ":");
            double volumeWaterToHeat = InputHelperUI.getInputAsDouble();
            boolean configResult = controller.configureWH(d, coldWaterTemperature, volumeWaterToHeat);
            if (!configResult) {
                System.out.println("Error: unable to set parameters. Returning to Main Menu.");
                return;
            }
            System.out.println("Options registered for water heater: " + controller.getWHName(d) + ".\n----------------" +
                    "-----------------------------\n");
        }
        double result = controller.getDailyWaterHeaterConsumption(house);
        System.out.println("The estimated total energy used in heating water in a day is: " + result + " kW.");
    }

    private void printOptionMessage() {
        System.out.println("Energy Consumption Management Options:\n");
        System.out.println("1) Display total nominal power of one of the Energy Grids. (US172)");
        System.out.println("2) Get total nominal power of a subset of rooms and/or devices connected to a grid." +
                " (US705)");
        System.out.println("3) Display total Metered Energy Consumption of a Device in a given time interval. (US720)");
        System.out.println("4) Display total Metered Energy Consumption of a Room in a given time interval. (US721)");
        System.out.println("5) Display total Metered Energy Consumption of a Grid in a given time interval. (US722)");
        System.out.println("6) Show data series necessary to design an energy consumption chart of the metered energy consumption of a device/room/grid in a given time interval. (US730)");
        System.out.println("7) Estimate the total energy used in heating water in a day. (US752)");
        System.out.println("0) (Return to main menu)\n");
    }

    private void printUS730Menu() {
        System.out.println("Choose the type of data to access:");
        System.out.println("1) Grid data.");
        System.out.println("2) Room data.");
        System.out.println("3) Device data.");
    }
}
