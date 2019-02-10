package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.PowerSource;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController mController;

    EnergyGridSettingsUI() {
        this.mController = new EnergyGridSettingsController();
    }

    void run(House house) {
        UtilsUI utilsUI = new UtilsUI();
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
                    System.out.println(utilsUI.invalidOption);
                    break;
            }
        }
    }


    // USER STORY 130 UI -  As an Administrator, I want to create a house grid, so that I can define the rooms that are
    // attached to it and the contracted maximum power for that grid - DANIEL OLIVEIRA .
    private void runUS130(House house) {
        EnergyGrid energyGrid = getInputUS130();
        updateHouse(house, energyGrid);
    }

    private EnergyGrid getInputUS130() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum contracted power for this energy grid.");
        double power = getInputMaxPower();
        return mController.createEnergyGrid(name, power);
    }


    private void updateHouse(House house, EnergyGrid energyGrid) {
        if(mController.addEnergyGridToHouse(house, energyGrid)){
            System.out.println("The energy grid was successfully created and added to the house.");
        }
        else {
            System.out.println("The energy grid wasn't added to the house. There is already an energy grid with " +
                    "that name.");
        }
    }

    /* USER STORY 135 UI - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */
    private void runUS135(House house) {
        InputUtils inputs = new InputUtils();
        UtilsUI check = new UtilsUI();
        if (check.houseGridListIsValid(house)) {
            EnergyGrid energyGrid = inputs.getInputGridByList(house);
            PowerSource powerSource = getInputAndCreatePowerSource();
            updateGridAndDisplayState(energyGrid, powerSource);
        }
        else {
            System.out.println(check.invalidGridList);
        }
    }

    private PowerSource getInputAndCreatePowerSource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Now let's set the maximum power output of this power source.");
        double maxPowerOutput = getInputMaxPower();
        System.out.println("Now let's set the maximum energy storage of this power source (it should be 0 in case the " +
                "power source can't storage any energy).");
        double maxEnergyStorage = getInputMaxPower();
        return mController.createPowerSource(name, maxPowerOutput, maxEnergyStorage);

    }

    private void updateGridAndDisplayState(EnergyGrid energyGrid, PowerSource powerSource) {
        if (mController.addPowerSourceToGrid(energyGrid, powerSource)) {
            System.out.println("The power source was successfully added to the energy grid.");
        } else {
            System.out.println("The power source wasn't added to the energy grid. The energy grid already has a power source " +
                    "with that name.");
        }
    }

    //US130 and US135 SHARED METHODS

    private double getInputMaxPower(){
        Scanner scan = new Scanner(System.in);
        double power = -1;
        while (power < 0) {
            power = getInputDouble(scan);
            scan.nextLine();
        }
        return power;
    }

    private double getInputDouble(Scanner scan){
        System.out.println("Please enter a valid number: ");
        while (!scan.hasNextDouble()) {
            scan.next();
            System.out.println("Not a valid number. Try again.");
        }
        return scan.nextDouble();
    }

    // USER STORY 145 -  an Administrator, I want to have a list of existing rooms attached to a house grid, so that I
    // can attach/detach rooms from it - JOAO CACHADA.
    private void runUS145(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if(!utilsUI.houseGridListIsValid(house)){
            System.out.println(utilsUI.invalidGridList);
            return;
        }
        InputUtils inputs = new InputUtils();
        EnergyGrid energyGrid = inputs.getInputGridByList(house);
        displayRoomList(energyGrid);

    }

    private void displayRoomList(EnergyGrid energyGrid) {
        System.out.println(mController.buildRoomsString(energyGrid.getListOfRooms()));
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO
    private void runUS147(House house) {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (!utilsUI.houseRoomListIsValid(house)) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        if (!utilsUI.houseGridListIsValid(house)) {
            System.out.println(utilsUI.invalidGridList);
            return;
        }
        Room room = inputUtils.getHouseRoomByList(house);
        EnergyGrid energyGrid = inputUtils.getInputGridByList(house);
        updateGridUS147(energyGrid, room);
    }

    private void updateGridUS147(EnergyGrid grid, Room room) {
        if (mController.addRoomToGrid(grid, room)) {
            System.out.println("Room successfully added to the grid!");
        } else {
            System.out.println("It wasn't possible to add the room. Please try again.");
        }
    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.
    private void runUS149(House house) {
        UtilsUI utilsUI = new UtilsUI();
        if(!utilsUI.houseGridListIsValid(house)){
            System.out.println(utilsUI.invalidGridList);
            return;
        }
        InputUtils inputs = new InputUtils();
        EnergyGrid energyGrid = inputs.getInputGridByList(house);
        if(!utilsUI.gridRoomListIsValid(energyGrid)){
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        Room room = inputs.getGridRoomByList(energyGrid);
        updateGridUS149(energyGrid, room);
    }

    private void updateGridUS149(EnergyGrid grid, Room room) {
        if (mController.removeRoomFromGrid(grid, room)) {
            System.out.println("Room successfully removed from grid!");
        } else {
            System.out.println("It wasn't possible to remove the room. Please try again.");
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/
    private void runUS160(House house) {
        InputUtils inputs = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.houseGridListIsValid(house)) {
            System.out.println(utilsUI.invalidGridList);
            return;
        }
        EnergyGrid energyGrid = inputs.getInputGridByList(house);
        if (!utilsUI.gridRoomListIsValid(energyGrid)) {
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        if (!utilsUI.gridDeviceListIsValid(energyGrid)) {
            System.out.println(utilsUI.invalidDeviceList);
            return;
        }
        displayUS160(energyGrid, house);
    }

    private void displayUS160(EnergyGrid energyGrid, House house) {
        if (energyGrid != null) {
            System.out.println(mController.buildListOfDevicesOrderedByTypeString(energyGrid, house));

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
