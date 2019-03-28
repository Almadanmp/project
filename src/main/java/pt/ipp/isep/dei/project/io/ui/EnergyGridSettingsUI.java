package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController controller;

    EnergyGridSettingsUI() {
        this.controller = new EnergyGridSettingsController();
    }

    void run(House house) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy grid settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printEnergyGridMenu();
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1: //US130
                    runUS130(house);
                    activeInput = false;
                    break;
                case 2: //US135
                    runUS135(house);
                    activeInput = false;
                    break;
                case 3: //US145
                    runUS145(house);
                    activeInput = false;
                    break;
                case 4: //US147
                    runUS147(house);
                    activeInput = false;
                    break;
                case 5: //US149
                    runUS149(house);
                    activeInput = false;
                    break;
                case 6: //US160
                    runUS160(house);
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


    // USER STORY 130 UI -  As an Administrator, I want to create a house grid, so that I can define the rooms that are
    // attached to it and the contracted maximum power for that grid - DANIEL OLIVEIRA .
    private void runUS130(House house) {
        EnergyGrid energyGrid = getInputUS130(house);
        updateHouse(house, energyGrid);
    }

    private EnergyGrid getInputUS130(House programHouse) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum contracted power for this energy grid.");
        double power = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createEnergyGrid(programHouse, name, power);
    }


    private void updateHouse(House house, EnergyGrid energyGrid) {
        if (controller.addEnergyGridToHouse(house, energyGrid)) {
            System.out.println("The energy grid was successfully created and added to the house.");
        } else {
            System.out.println("The energy grid wasn't added to the house. There is already an energy grid with " +
                    "that name.");
        }
    }

    /* USER STORY 135 UI - As an Administrator, I want to addWithoutPersisting a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */
    private void runUS135(House house) {
        if (!house.isEnergyGridListEmpty()) {
            EnergyGrid energyGrid = InputHelperUI.getInputGridByList(house);
            PowerSource powerSource = getInputAndCreatePowerSource(energyGrid);
            updateGridAndDisplayState(energyGrid, powerSource);
        } else {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
        }
    }

    private PowerSource getInputAndCreatePowerSource(EnergyGrid energyGrid) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to addWithoutPersisting: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum power output of this power source.");
        double maxPowerOutput = InputHelperUI.getInputAsDoubleZeroOrPositive();
        System.out.println("Now let's set the maximum energy storage of this power source (it should be 0 in case the " +
                "power source can't storage any energy).");
        double maxEnergyStorage = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createPowerSource(energyGrid, name, maxPowerOutput, maxEnergyStorage);
    }

    private void updateGridAndDisplayState(EnergyGrid energyGrid, PowerSource powerSource) {
        if (controller.addPowerSourceToGrid(energyGrid, powerSource)) {
            System.out.println("The power source was successfully added to the energy grid.");
        } else {
            System.out.println("The power source wasn't added to the energy grid. The energy grid already has a power source " +
                    "with that name.");
        }
    }


    // USER STORY 145 -  an Administrator, I want to have a list of existing rooms attached to a house grid, so that I
    // can attach/detach rooms from it - JOAO CACHADA.
    private void runUS145(House house) {
        if (house.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(house);
        displayRoomList(energyGrid);

    }

    private void displayRoomList(EnergyGrid energyGrid) {
        System.out.println(controller.buildRoomsString(energyGrid.getRoomList()));
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO
    private void runUS147(House house) {
        if (house.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        if (house.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(house);
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(house);
        updateGridUS147(energyGrid, room, house);
    }

    private void updateGridUS147(EnergyGrid grid, RoomDTO room, House house) {
        if (controller.addRoomToGrid(grid, room, house)) {
            System.out.println("Room successfully added to the grid!");
        } else {
            System.out.println("It wasn't possible to addWithoutPersisting the room. Please try again.");
        }
    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.
    private void runUS149(House house) {
        if (house.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(house);
        if (energyGrid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        Room room = InputHelperUI.getGridRoomByList(energyGrid);
        updateGridUS149(energyGrid, room);
    }

    private void updateGridUS149(EnergyGrid grid, Room room) {
        if (controller.removeRoomFromGrid(grid, room)) {
            System.out.println("Room successfully removed from grid!");
        } else {
            System.out.println("It wasn't possible to removeGeographicArea the room. Please try again.");
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/
    private void runUS160(House house) {
        if (house.isEnergyGridListEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(house);
        if (energyGrid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        if (energyGrid.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        displayUS160(energyGrid, house);
    }

    private void displayUS160(EnergyGrid energyGrid, House house) {
        System.out.println("\nList of device(s) by type:\n" + controller.buildListOfDevicesOrderedByTypeString(energyGrid, house));
    }


    // UI SPECIFIC METHODS - Not Used on User Stories.

    private void printEnergyGridMenu() {
        System.out.println("Energy Grid Settings Options:\n");
        System.out.println("1) Create a energy grid. (US130)");
        System.out.println("2) Add a power source to a house grid. (US135)");
        System.out.println("3) List of existing rooms attached to a house grid. (US145)");
        System.out.println("4) Attach a room to a house grid. (US147)");
        System.out.println("5) Detach a room from a house grid. (US149)");
        System.out.println("6) Display all available devices on an energy grid (US160)");
        System.out.println("0) (Return to main menu)\n");
    }
}
