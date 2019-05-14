package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllerCLI.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

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

    void run(House house, EnergyGridRepository energyGridRepository, RoomRepository roomRepository, EnergyGridRoomService energyGridRoomService) {
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
                    runUS130(house, energyGridRepository);
                    activeInput = false;
                    break;
                case 2: //US135
                    runUS135(energyGridRepository);
                    activeInput = false;
                    break;
                case 3: //US145
                    runUS145(energyGridRepository, roomRepository, energyGridRoomService);
                    activeInput = false;
                    break;
                case 4: //US147
                    runUS147(energyGridRepository, roomRepository);
                    activeInput = false;
                    break;
                case 5: //US149
                    runUS149(energyGridRepository);
                    activeInput = false;
                    break;
                case 6: //US160
                    runUS160(energyGridRepository);
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
    private void runUS130(House house, EnergyGridRepository energyGridRepository) {

        EnergyGrid energyGrid = getInputUS130(house, energyGridRepository);
        updateHouse(energyGrid, energyGridRepository);
    }

    private EnergyGrid getInputUS130(House house, EnergyGridRepository energyGridRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Insert the maximum contracted power for this energy grid.");
        double power = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createEnergyGrid(name, power, house.getId(), energyGridRepository);
    }

    private void updateHouse(EnergyGrid energyGrid, EnergyGridRepository energyGridRepository) {
        try {
            controller.addEnergyGridToHouse(energyGrid, energyGridRepository);
            System.out.println("The energy grid was successfully created and added to the house.");
        } catch (RuntimeException ok) {
            System.out.println("Something went wrong. Please try again.");
        }
    }

    /* USER STORY 135 UI - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */
    private void runUS135(EnergyGridRepository energyGridRepository) {
        if (!energyGridRepository.getAllGrids().isEmpty()) {
            EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridRepository);
            PowerSource powerSource = getInputAndCreatePowerSource(energyGridRepository);
            updateGridAndDisplayState(energyGrid, powerSource);
        } else {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
        }
    }

    private PowerSource getInputAndCreatePowerSource(EnergyGridRepository energyGridRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum power output of this power source.");
        double maxPowerOutput = InputHelperUI.getInputAsDoubleZeroOrPositive();
        System.out.println("Now let's set the maximum energy storage of this power source (it should be 0 in case the " +
                "power source can't storage any energy).");
        double maxEnergyStorage = InputHelperUI.getInputAsDoubleZeroOrPositive();
        return controller.createPowerSource(name, maxPowerOutput, maxEnergyStorage, energyGridRepository);
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
    private void runUS145(EnergyGridRepository energyGridRepository, RoomRepository roomRepository, EnergyGridRoomService energyGridRoomService) {
        if (energyGridRepository.getAllGrids().isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridRepository);
        List<Room> roomsOnGrid = energyGridRoomService.getAllByEnergyGridName(energyGrid.getName());
        displayRoomList(roomsOnGrid, roomRepository);

    }

    private void displayRoomList(List<Room> roomsOnGrid, RoomRepository roomRepository) {
        System.out.println(controller.buildRoomsString(roomRepository, roomsOnGrid));
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO
    private void runUS147(EnergyGridRepository energyGridRepository, RoomRepository roomRepository) {
        if (roomRepository.isEmptyRooms()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        if (energyGridRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        RoomDTO room = InputHelperUI.getHouseRoomDTOByList(roomRepository, houseRooms);
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridRepository);
        updateGridUS147(energyGrid, room, roomRepository, energyGridRepository);
    }

    private void updateGridUS147(EnergyGrid grid, RoomDTO room, RoomRepository roomRepository, EnergyGridRepository energyGridRepository) {
        try {
            if (controller.addRoomDTOToGrid(grid, room, roomRepository)) {
                try {
                    controller.addEnergyGridToHouse(grid, energyGridRepository);
                    System.out.println("Room successfully added to the grid!");
                }
                catch (RuntimeException ok){
                    System.out.println("Something went wrong with the Energy Grid. Please try again.");
                }
            } else {
                System.out.println("It wasn't possible to add the room. Please try again.");
            }
        }
        catch (RuntimeException ok){
            System.out.println("The room you are trying to access doesn't exist in the database. Please try again.");
        }

    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.
    private void runUS149(EnergyGridRepository energyGridRepository) {
        if (energyGridRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridRepository);
        if (energyGrid.isRoomListEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
            return;
        }
        Room room = InputHelperUI.getGridRoomByList(energyGrid);
        updateGridUS149(energyGrid, room, energyGridRepository);
    }

    private void updateGridUS149(EnergyGrid grid, Room room, EnergyGridRepository energyGridRepository) {
        if (controller.removeRoomFromGrid(grid, room)) {
            try {
                controller.addEnergyGridToHouse(grid, energyGridRepository);
                System.out.println("Room successfully removed from grid!");
            }
            catch (RuntimeException ok){
                System.out.println("Something is wrong with the room's Energy Grid. Please try again.");
            }
        } else {
            System.out.println("It wasn't possible to remove the room. Please try again.");
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator), I want to get a list of all devices in a grid, grouped by
    device type. It must include device location
    DANIEL OLIVEIRA*/
    private void runUS160(EnergyGridRepository energyGridRepository) {
        if (energyGridRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GRID_LIST);
            return;
        }
        EnergyGrid energyGrid = InputHelperUI.getInputGridByList(energyGridRepository);
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
