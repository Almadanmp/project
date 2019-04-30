package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class EnergyGridSettingsUI {
    private final EnergyGridSettingsController controller;
    private final List<String> menuOptions;

    EnergyGridSettingsUI() {
        this.controller = new EnergyGridSettingsController();
        menuOptions = new ArrayList<>();
        menuOptions.add("Create an energy grid. (US130)");
        menuOptions.add("Add a power source to a house grid. (US135)");
        menuOptions.add("List all existing rooms attached to a house grid. (US145)");
        menuOptions.add("Attach a room to a house grid. (US147)");
        menuOptions.add("Detach a room from a house grid. (US149)");
        menuOptions.add("Display all available devices on an energy grid (US160)");
        menuOptions.add("(Return to main menu)");
    }

    void run(House house, EnergyGridService energyGridService, RoomService roomService) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy grid settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("Energy Grid Settings", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1: //US130
                    runUS130(house, energyGridService);
                    activeInput = false;
                    break;
                case 2: //US135
                    runUS135(energyGridService);
                    activeInput = false;
                    break;
                case 3: //US145
                    runUS145(energyGridService, roomService);
                    activeInput = false;
                    break;
                case 4: //US147
                    runUS147(energyGridService, roomService);
                    activeInput = false;
                    break;
                case 5: //US149
                    runUS149(energyGridService);
                    activeInput = false;
                    break;
                case 6: //US160
                    runUS160(energyGridService);
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
    private void runUS130(House house, EnergyGridService energyGridService) {

        EnergyGrid energyGrid = getInputUS130(house, energyGridService);
        updateHouse(energyGrid, energyGridService);
    }

    private EnergyGrid getInputUS130(House house, EnergyGridService energyGridService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Insert the maximum contracted power for this energy grid.");
        double power = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createEnergyGrid(name, power, house.getId(), energyGridService);
    }

    private void updateHouse(EnergyGrid energyGrid, EnergyGridService energyGridService) {
        EnergyGrid energyGridAux = controller.addEnergyGridToHouse(energyGrid, energyGridService);
        if (energyGridAux != null) {
            System.out.println("The energy grid was successfully created and added to the house.");
        }
    }

    /* USER STORY 135 UI - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */
    private void runUS135(EnergyGridService energyGridService) {
        if (!energyGridService.getAllGrids().isEmpty()) {
            EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridService);
            PowerSource powerSource = getInputAndCreatePowerSource(energyGridService);
            updateGridAndDisplayState(energyGrid, powerSource);
        } else {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
        }
    }

    private PowerSource getInputAndCreatePowerSource(EnergyGridService energyGridService) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum power output of this power source.");
        double maxPowerOutput = InputHelperUI.getInputAsDoubleZeroOrPositive();
        System.out.println("Now let's set the maximum energy storage of this power source (it should be 0 in case the " +
                "power source can't storage any energy).");
        double maxEnergyStorage = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createPowerSource(name, maxPowerOutput, maxEnergyStorage, energyGridService);
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
    private void runUS145(EnergyGridService energyGridService, RoomService roomService) {
        if (energyGridService.getAllGrids().isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridService);
        List<Room> roomsOnGrid = roomService.getAllByEnergyGridName(energyGrid.getName());
        displayRoomList(roomsOnGrid, roomService);

    }

    private void displayRoomList(List<Room> roomsOnGrid, RoomService roomService) {
        System.out.println(controller.buildRoomsString(roomService, roomsOnGrid));
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO
    private void runUS147(EnergyGridService energyGridService, RoomService roomService) {
        if (roomService.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        if (energyGridService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        List<Room> houseRooms = roomService.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomService, houseRooms);
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridService);
        updateGridUS147(energyGrid, room, roomService, energyGridService);
    }

    private void updateGridUS147(EnergyGrid grid, RoomDTO room, RoomService roomService, EnergyGridService energyGridService) {
        if (controller.addRoomToGrid(grid, room, roomService)) {
            controller.updateEnergyGrid(grid, energyGridService);
            System.out.println("Room successfully added to the grid!");
        } else {
            System.out.println("It wasn't possible to add the room. Please try again.");
        }
    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.
    private void runUS149(EnergyGridService energyGridService) {
        if (energyGridService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridService);
        if (energyGrid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        Room room = InputHelperUI.getGridRoomByList(energyGrid);
        updateGridUS149(energyGrid, room, energyGridService);
    }

    private void updateGridUS149(EnergyGrid grid, Room room, EnergyGridService energyGridService) {
        if (controller.removeRoomFromGrid(grid, room)) {
            controller.updateEnergyGrid(grid, energyGridService);
            System.out.println("Room successfully removed from grid!");
        } else {
            System.out.println("It wasn't possible to removeSensor the room. Please try again.");
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator), I want to get a list of all devices in a grid, grouped by
    device type. It must include device location
    DANIEL OLIVEIRA*/
    private void runUS160(EnergyGridService energyGridService) {
        if (energyGridService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridService);
        if (energyGrid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        if (energyGrid.isDeviceListEmpty()) {
            System.out.println(UtilsUI.INVALID_DEVICE_LIST);
            return;
        }
        displayUS160(energyGrid);
    }

    private void displayUS160(EnergyGrid energyGrid) {
        System.out.println("\nList of device(s) by type:\n" + controller.buildListOfDevicesOrderedByTypeString(energyGrid));
    }
}
