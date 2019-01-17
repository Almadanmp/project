package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController mController;
    private EnergyGrid mEnergyGrid;

    EnergyGridSettingsUI() {
        this.mController = new EnergyGridSettingsController();
    }

    void run(House house) {
        InputUtils inputs = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (utilsUI.houseIsNull(house) ) {
            System.out.println("Invalid House - This house doesn't meet the necessary requirements, please configure" +
                    " your house first through the main menu.");
            return;
        }
        if (utilsUI.houseEGListIsNull(house)){
            System.out.println("The selected House has not a valid Energy Grid associated\nReturning to Main Menu");
            return;
        }
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy grid settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printEnergyGridMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1: //US130
                    getInputUS130();
                    updateHouse(house);
                    activeInput = false;
                    break;
                case 2: //US135
                    mEnergyGrid = inputs.getInputGridByList(house);
                    getInputAndCreatePowerSource();
                    updateGridAndDisplayState();
                    activeInput = false;
                    break;
                case 3: //US145
                    mEnergyGrid = inputs.getInputGridByList(house);
                    displayRoomList(mEnergyGrid);
                    activeInput = false;
                    break;
                case 4: //US147
                    Room room = inputs.getHouseRoomByList(house);
                    EnergyGrid energyGrid = getInputGrid(house, room);
                    updateGridUS147(energyGrid, room);
                    activeInput = false;
                    break;
                case 5: //US149
                    energyGrid = inputs.getInputGridByList(house);
                    room = getInputEnergyGridRoom(energyGrid);
                    updateGridUS149(energyGrid, room);
                    activeInput = false;
                    break;
                case 6: //US160
                    energyGrid = inputs.getInputGridByList(house);
                    displayUS160(energyGrid);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utilsUI.invalidOption);
                    break;
            }
        }
    }


    // USER STORY 130 UI -  As an Administrator, I want to create a house grid, so that I can define the rooms that are
    // attached to it and the contracted maximum power for that grid - DANIEL OLIVEIRA .

    private void getInputUS130() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Set the maximum potency of this energy grid: ");
        double maxPower = scanner.nextDouble();
        mController.createEnergyGrid(name, maxPower);
    }

    private void updateHouse(House house) {
            if (mController.addEnergyGridToHouse(house)) {
                System.out.println("The energy grid was successfully created and added to the house.");
            }
    }

    /* USER STORY 135 UI - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */
    //FIXME parece-me que esta US não faz uma validação no caso da EnergyGrid não ter uma lista de PowerSource

    private void getInputAndCreatePowerSource() {
        if (mEnergyGrid != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type the designation of the power source you want to add: ");
            String name = scanner.next();
            System.out.println("Type the maximum power output of the power source you want to add: ");
            double maxPowerOutput = scanner.nextDouble();
            System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
            double maxEnergyStorage = scanner.nextDouble();
            mController.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
        }
    }

    private void updateGridAndDisplayState() {
        if (mEnergyGrid != null) {
            if (mController.addPowerSourceToGrid(mEnergyGrid)) {
                System.out.println("The power source was added with success!");
            } else {
                System.out.println("The power source was NOT added to the energy grid!");
            }
        }
    }

    // USER STORY 145 -  an Administrator, I want to have a list of existing rooms attached to a house grid, so that I
    // can attach/detach rooms from it - JOAO CACHADA.

    private void displayRoomList(EnergyGrid energyGrid) {
        if (mEnergyGrid != null) {
            System.out.println(mController.buildRoomsString(energyGrid.getListOfRooms()));
        }
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO

    private EnergyGrid getInputGrid(House house, Room room) {
        if(room != null) {
        InputUtils inputUtils = new InputUtils();
        return inputUtils.getInputGridByList(house);
        }
        else {
            return null;
        }
    }

    private void updateGridUS147(EnergyGrid grid, Room room) {
        if (room == null || grid == null) {
            return;
        }
        if (mController.addRoomToGrid(grid, room)) {
            System.out.println("Room successfully added to the grid!");
        } else {
            System.out.println("It wasn't possible to add the room. Please try again.");
        }
    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.

    private Room getInputEnergyGridRoom(EnergyGrid energyGrid){
        if(energyGrid != null) {
            InputUtils inputUtils = new InputUtils();
            return inputUtils.getGridRoomByList(energyGrid);
        }
        else{
            return null;
        }
    }
    private void updateGridUS149(EnergyGrid grid, Room room) {
        if (grid != null && room != null) {
            if (mController.removeRoomFromGrid(grid, room)) {
                System.out.println("Room successfully removed from grid!");
            } else {
                System.out.println("It wasn't possible to remove the room. Please try again.");
            }
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/

    private void displayUS160(EnergyGrid energyGrid) {
        if(energyGrid != null) {
            System.out.println(mController.buildListOfDevicesOrderedByTypeString(energyGrid));
        }
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
