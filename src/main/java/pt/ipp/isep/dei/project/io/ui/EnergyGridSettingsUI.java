package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController mController;
    private EnergyGrid mEnergyGrid;
    private Room mRoom;
    private House mHouse;
    private String noGrid = "You don't have a energy grid in your house. Please add a energy grid to continue.";
    private Double mEGridNPower;

    EnergyGridSettingsUI() {
        this.mController = new EnergyGridSettingsController();
    }

    void run(House house) {
        InputUtils inputs = new InputUtils();
        if (house == null) {
            System.out.println("Invalid House - You need to create a house to continue.");
            return;
        }
        this.mHouse = house;
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy Grid Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            UtilsUI utilsUI = new UtilsUI();
            printEnergyGridMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputUS130();
                    updateHouse(mHouse);
                    activeInput = false;
                    break;
                case 2:
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    getInputAndCreatePowerSource();
                    updateGridAndDisplayState();
                    activeInput = false;
                    break;
                case 3:
                    printGridValidation();
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    displayRoomList(mEnergyGrid);
                    activeInput = false;
                    break;
                case 4:
                    printUS147Validation();
                    if(getInputRoomByList()){
                        return;
                    }
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    updateGridUS147(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 5:
                    printGridValidation();
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    if(getInputRoomByListInEG()){
                        return;
                    }
                    updateGridUS149(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 6:
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    displayUS160(mEnergyGrid);
                    activeInput = false;
                    break;
                case 7:
                    if (this.mHouse.getEGList() == null) {
                        System.out.println(noGrid);
                        return;
                    } else if (this.mHouse.getEGList().getEnergyGridList().isEmpty()) {
                        System.out.println(noGrid);
                        return;
                    }
                    mEnergyGrid = inputs.getInputGridByList(mHouse);
                    updateUS172(mEnergyGrid);
                    displayUS172();
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

    // Get Input Room By List

    private boolean getInputRoomByList() {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (this.mHouse.getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select one of the existing rooms on the selected House: ");
        System.out.println(mController.printHouseRoomList(this.mHouse));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux < this.mHouse.getRoomList().size() && aux >= 0) {
            this.mRoom = this.mHouse.getRoomList().get(aux);
            return false;
        } else {
            System.out.println(utilsUI.invalidOption);
            return true;
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
        if (house != null) {
            if (mController.addEnergyGridToHouse(house)) {
                System.out.println("The energy grid was successfully created and added to the house.");
            }
        } else {
            System.out.println("The energy grid was NOT successfully created and added to the selected house.");
        }
    }

    /* USER STORY 135 UI - As an Administrator, I want to add a power source to an energy grid, so that the produced
    energy may be used by all devices on that grid - DANIEL OLIVEIRA.
     */

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
            System.out.println(mController.printRooms(energyGrid.getListOfRooms()));
        } else {
            System.out.println("The energy grid you've selected has no rooms attached to it.");
        }
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid. MIGUEL ORTIGAO

    private void updateGridUS147(EnergyGrid grid, Room room) {
        if (mController.addRoomToGrid(grid, room)) {
            System.out.println("Room successfully added to the grid!");
        } else {
            System.out.println("It wasn't possible to add the room. Please try again.");
        }
    }

    // USER STORY 149 -  an Administrator, I want to detach a room from a house grid, so that the room’s power  and
    // energy  consumption  is  not  included  in  that  grid.  The  room’s characteristics are not changed.

    private void updateGridUS149(EnergyGrid grid, Room room) {
        if (mController.removeRoomFromGrid(grid, room)) {
            System.out.println("Room successfully removed from grid!");
        } else {
            System.out.println("It wasn't possible to remove the room. Please try again.");
        }
    }

    private boolean getInputRoomByListInEG() {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (mEnergyGrid.getListOfRooms().getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return true;
        }
        System.out.println("Please select one of the existing rooms on the selected House: ");
        System.out.println(mController.printGridRooms(mEnergyGrid));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < mEnergyGrid.getListOfRooms().getRoomList().size()) {
            this.mRoom = mEnergyGrid.getListOfRooms().getRoomList().get(aux);
            return false;
        } else {
            System.out.println(utilsUI.invalidOption);
            return true;
        }
    }

    /*USER STORY 160 - As a Power User (or Administrator),
    I want to get a list of all devices in a grid, grouped by device type.
    It must include device location
    DANIEL OLIVEIRA*/

    private void displayUS160(EnergyGrid energyGrid) {
        if (mHouse == null) {
            return;
        }
        mController.printListOfDevicesByType(energyGrid);
    }

    // USER STORY 172 - As a Power User [or Administrator], I want to know the total nominal power
    //connected to a grid, i.e. the sum of the nominal power of all devices in all rooms
    //in the grid.  - ANDRE RUA.

    private void updateUS172(EnergyGrid grid) {
        if (mEnergyGrid != null) {
            this.mEGridNPower = mController.getTotalPowerFromGrid(grid);
        }
    }

    private void displayUS172() {
        if (mEnergyGrid != null) {
            System.out.println(" The sum of the Nominal Power of all the devices connected to this Energy Grid is " + mEGridNPower + " kW.");
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
        System.out.println("7) Display total nominal power of one of the Energy Grids. (US172)");
        System.out.println("0) (Return to main menu)\n");
    }

    void printGridValidation() {
        if (this.mHouse.getEGList() == null) {
            System.out.println(noGrid);
            return;
        } else if ((this.mHouse.getEGList().getEnergyGridList().isEmpty())) {
            System.out.println("Your energy grid doesn't have any rooms. Please add a room to continue.");
            return;
        }
    }

    /**
     * Print Different Types of Messages.
     * If The Energy Grid Is Null or If the RoomList is Empty or If the Energy Grid List is Empty.
     * If everything is OK, the case in the run() method continues.
     */
    void printUS147Validation() {
        if (this.mHouse.getEGList() == null) {
            System.out.println(noGrid);
            return;
        } else if ((this.mHouse.getRoomList().isEmpty())) {
            System.out.println("Your house doesn't have any rooms. Please create a room to continue.");
            return;
        } else if (this.mHouse.getEGList().getEnergyGridList().isEmpty()) {
            System.out.println("You don't have a energy grid in your house. Please add a energy grid to continue.");
            return;
        }
    }
}
