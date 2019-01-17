package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyConsumptionController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.List;
import java.util.Scanner;


class EnergyConsumptionUI {
    private EnergyConsumptionController controller;

    EnergyConsumptionUI() {
        this.controller = new EnergyConsumptionController();
    }

    private Scanner returnToConsole = new Scanner(System.in);

    void run(House programHouse) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (utils.houseIsNull(programHouse)) {
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy Consumption Monitoring\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    if (!checkHouseGrid(programHouse)) {
                        EnergyGrid mEnergyGrid = inputUtils.getInputGridByList(programHouse);
                        double nominalPower = updateUS172(mEnergyGrid);
                        displayUS172(nominalPower);
                    }
                    activeInput = false;
                    break;
                case 2:
                    runUS705(programHouse);
                    activeInput = true;
                    break;
                case 3:
                    runUS752(programHouse);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }

    // US705 - As a Power User, I want to know the total nominal power of a subset of rooms
    // and/or devices of my choosing connected to a grid.

    private void runUS705(House programHouse) {
        UtilsUI utils = new UtilsUI();
        boolean active;
        InputUtils inputs = new InputUtils();
        EnergyGrid grid = inputs.getInputGridByList(programHouse);
        RoomList selectedRooms = new RoomList();
        DeviceList selectedDevices = new DeviceList();
        while (true) {
            printSelection(selectedDevices, selectedRooms);
            System.out.println("\nWhat would you like to select? \n\n 1) Select / Deselect a Room (and all its devices); \n 2) Select / Deselect a device; \n 3) Get the Total Nominal Power of the currently selected subset; \n 4) Return to main menu;\n ");
            int option;
            option = inputs.readInputNumberAsInt();
            switch (option) {
                case 1:
                    active = true;
                    while (active) {
                        printSelection(selectedDevices, selectedRooms);
                        selectRooms(grid, selectedRooms, selectedDevices);
                        active = continuePrompt();
                    }
                    break;
                case 2:
                    active = true;
                    while (active) {
                        printSelection(selectedDevices, selectedRooms);
                        selectDevices(grid, selectedDevices);
                        active = continuePrompt();
                    }
                    break;
                case 3:
                    printSelection(selectedDevices, selectedRooms);
                    printSelectionNominalPower(grid, selectedDevices);
                    inputs.returnToMenu(returnToConsole);
                    break;
                case 4:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }

    /**
     * Prints currently selected devices and rooms.
     *
     * @param selectedDevices the list of currently selected devices.
     * @param selectedRooms   the list of currently selected rooms.
     */

    private void printSelection(DeviceList selectedDevices, RoomList selectedRooms) {
        if (selectedDevices.getDeviceList().isEmpty() && selectedRooms.getRoomList().isEmpty()) {
            System.out.println("You haven't selected any rooms or devices yet.");
        } else
            System.out.println("\nYou have already selected the following rooms:\n" + "\n" + selectedRooms.buildRoomsString() + "\n" + "You have already selected the following devices:\n\n" + selectedDevices.buildDevicesString() + ".");
    }

    /**
     * Allows user to select multiple rooms, or deselect them if already selected.
     *
     * @param grid            is the grid we want to select rooms from.
     * @param selectedRooms   is the rooms already selected.
     * @param selectedDevices is the devices already selected, including devices contained in the rooms already selected.
     */

    private void selectRooms(EnergyGrid grid, RoomList selectedRooms, DeviceList selectedDevices) {
        InputUtils inputs = new InputUtils();
        Room r1 = inputs.getGridRoomByList(grid);
        Scanner scanner = new Scanner(System.in);
        if (selectedRooms.contains(r1)) {
            String duplicateRoom = "That room is already selected. Would you like to remove it from the list? (Y/N)\n";
            System.out.println(duplicateRoom);
            if (inputs.yesOrNo(scanner.nextLine(), duplicateRoom)) {
                controller.removeRoomFromList(r1, selectedRooms);
                controller.removeRoomDevicesFromDeviceList(r1, selectedDevices);
                System.out.println("The room and its devices have been deselected.");
            }
            return;
        }
        controller.addRoomToList(r1, selectedRooms);
        controller.addRoomDevicesToDeviceList(r1, selectedDevices);
        System.out.println("You have selected the room: " + r1.buildRoomString());
    }

    /**
     * Allows user to select multiple devices, or deselect them if already selected.
     *
     * @param grid            is the grid we want to select devices from.
     * @param selectedDevices is the devices already selected, including devices contained in the rooms already selected.
     */

    private void selectDevices(EnergyGrid grid, DeviceList selectedDevices) {
        InputUtils inputs = new InputUtils();
        Device d1 = inputs.getGridDevicesByList(grid);
        Scanner scanner = new Scanner(System.in);
        if (selectedDevices.contains(d1)) {
            String duplicateDevice = "That device is already on the list. Would you like to deselect the device? (Y/N)\n";
            System.out.println(duplicateDevice);
            if (inputs.yesOrNo(scanner.nextLine(), duplicateDevice)) {
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
        Scanner scanner = new Scanner(System.in);
        InputUtils inputs = new InputUtils();
        return inputs.yesOrNo(scanner.nextLine(), prompt);
    }

    /**
     * Prints the value of the totalNominalPower of selected subset.
     *
     * @param grid            grid we want to select a subset from.
     * @param selectedDevices selected subset.
     */

    private void printSelectionNominalPower(EnergyGrid grid, DeviceList selectedDevices) {
        double totalPower = controller.getSelectionNominalPower(grid, selectedDevices);
        System.out.println("The total nominal power for the selected subset is " + totalPower + " kW/h.");
    }


    /*
     * US752
     * As a Regular User [or Power User], I want to estimate the total energy used in heating water in a given day,
     * given the cold-water temperature and the volume of water produced in each water heater.
     */

    private void runUS752(House house) {
        InputUtils inputUtils = new InputUtils();
        List<Device> waterHeaters = controller.getWaterHeaterDeviceList(house);
        if (waterHeaters.isEmpty()) {
            System.out.println("Your house has no Water Heaters. Returning to Main Menu.");
            return;
        }
        System.out.println("You currently have " + waterHeaters.size() + " water heaters in your house:\n");

        for (Device d : waterHeaters) {
            System.out.println("Water Heater name: " + d.getName() + ".\n");
            System.out.println("Please insert the cold water temperature for Water Heater: " + d.getName() + ":");
            double coldWaterTemperature = inputUtils.getInputAsDouble();
            System.out.println("Please insert the volume of water to heat for Water Heater: " + d.getName() + ":");
            double volumeWaterToHeat = inputUtils.getInputAsDouble();
            boolean configResult = controller.configureOneHeater(d, coldWaterTemperature, volumeWaterToHeat);
            if (!configResult){
                System.out.println("Error: unable to set parameters. Returning to Main Menu.");
                return;
            }

            System.out.println("device " + d.getName() + " options registered.\n");
        }
        double result = controller.getDailyHouseConsumptionWaterHeater(house);
        System.out.println("The estimate total energy used in heating water in a day is: " + result + " kW.");
    }

    // USER STORY 172 - As a Power User [or Administrator], I want to know the total nominal power
    //connected to a grid, i.e. the sum of the nominal power of all devices in all rooms
    //in the grid.

    private double updateUS172(EnergyGrid grid) {
        EnergyConsumptionController mController = new EnergyConsumptionController();
        if (grid != null) {
            return mController.getTotalPowerFromGrid(grid);
        }
        return 0;
    }

    private void displayUS172(Double nomPower) {
        InputUtils inputs = new InputUtils();
        System.out.println(" The sum of the Nominal Power of all the devices connected to this Energy Grid is " + nomPower + " kW.\n");
        inputs.returnToMenu(returnToConsole);

    }

    private void printOptionMessage() {
        System.out.println("Energy Consumption Management Options:\n");
        System.out.println("1) Display total nominal power of one of the Energy Grids. (US172)");
        System.out.println("2) Get total nominal power of a subset of rooms and/or devices connected to a grid." +
                " (US705)");
        System.out.println("3) Estimate the total energy used in heating water in a day. (US752)");
        System.out.println("0) (Return to main menu)\n");
    }

    private boolean checkHouseGrid(House house) {
        if (house.getEGList() == null) {
            InputUtils inputs = new InputUtils();
            System.out.println("You don't have a energy grid in your house. Please add a energy grid to continue.");
            inputs.returnToMenu(returnToConsole);
            return true;
        }
        return false;
    }


}
