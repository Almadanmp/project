package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.List;
import java.util.Scanner;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController mController;
    private EnergyGrid mEnergyGrid;
    private String mGridName;
    private Room mRoom;
    private String mRoomName;
    private House mHouse;
    private String mStringRequest = "Would you like to:\n";
    private String mStringRequestChoseFromList = "2) Choose it from a list:\n";
    private String returnString = "return";
    private String noGrid = "You don't have a energy grid in your house. Please add a energy grid to continue.";
    private Double mEGridNPower;

    EnergyGridSettingsUI() {
        this.mController = new EnergyGridSettingsController();
    }

    void run(House house) {
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
                    getInputEnergyGrid(mHouse);
                    getInputAndCreatePowerSource();
                    updateGridAndDisplayState();
                    activeInput = false;
                    break;
                case 3:
                    printGridValidation();
                    getInputEnergyGrid(mHouse);
                    displayRoomList(mEnergyGrid);
                    activeInput = false;
                    break;
                case 4:
                    printUS147Validation();
                    getInputRoom();
                    getInputEnergyGrid(mHouse);
                    updateGridUS147(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 5:
                    printGridValidation();
                    getInputEnergyGrid(mHouse);
                    getInputRoomUS149();
                    updateGridUS149(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 6:
                    getInputGridByList(mHouse);
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
                    getInputEnergyGrid(mHouse);
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

    //SHARED METHODS THROUGH UIS

    private void getInputEnergyGrid(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        System.out.println(
                "We need to know which one is your energy grid.\n" + mStringRequest + "1) Type the name of your grid;\n" + mStringRequestChoseFromList +
                        "0) Return;");

        int option = inputUtils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputGridName();
                if (!getGridByName(house)) {
                    System.out.println("Unable to select a Grid. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputGridByList(house);
                break;
            case 0:
                break;
            default:
                System.out.println(utilsUI.invalidOption);
                break;
        }
    }

    private boolean getGridByName(House house) {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        List<Integer> listOfIndexesGrids = mController.matchGridIndexByString(mGridName, house);
        while (listOfIndexesGrids.isEmpty()) {
            System.out.println("There is no EnergyGrid with that name. Please insert the name of a Grid" +
                    " that exists or  Type 'exit' to cancel and create a new Grid on the Main Menu.");
            if (!getInputGridName()) {
                return false;
            }
            listOfIndexesGrids = mController.matchGridIndexByString(mGridName, house);
        }
        if (listOfIndexesGrids.size() > 1) {
            System.out.println("There are multiple Energy Grids with that name. Please choose the right one.");
            System.out.println(mController.printEnergyGridByIndex(listOfIndexesGrids, house.getEGList()));
            int aux = inputUtils.readInputNumberAsInt();
            if (listOfIndexesGrids.contains(aux)) {
                mEnergyGrid = house.getEGList().getEnergyGridList().get(aux);
                System.out.println("You have chosen the following grid:");
                System.out.println(mController.printEnergyGrid(mEnergyGrid));
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        } else {
            System.out.println("You have chosen the following grid:");
            mEnergyGrid = house.getEGList().getEnergyGridList().get(0);
            System.out.println(mController.printEnergyGrid(mEnergyGrid));
        }
        return true;
    }

    private boolean getInputGridName() {
        if (!this.mHouse.getEGList().getEnergyGridList().isEmpty()) {
            Scanner mScanner = new Scanner(System.in);
            System.out.println("Please type the name of the Grid you want to access.");
            this.mGridName = mScanner.nextLine();
            return (!("exit".equals(mGridName)));
        } else {
            return false;
        }
    }

    private void getInputGridByList(House house) {
        if (house == null) {
            System.out.println("The selected house is NOT a valid one\n" + "Returning to main menu\n");
            return;
        }
        UtilsUI utilsUI = new UtilsUI();
        if (house.getEGList().getEnergyGridList().isEmpty()) {
            System.out.print("Invalid Grid List - List Is Empty\n" + "Returning to main menu\n");
            return;
        }
        boolean activeInput = false;
        InputUtils inputUtils = new InputUtils();
        System.out.println("Please select one of the existing grids on the selected house: ");
        while (!activeInput) {
            System.out.println(mController.printGridList(house));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < house.getEGList().getEnergyGridList().size()) {
                mEnergyGrid = house.getEGList().getEnergyGridList().get(aux);
                activeInput = true;
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        }
    }

    // Get Input Room
    // Methods to get Rooms By Name / by List

    private void getInputRoom() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utilsUI = new UtilsUI();
        System.out.println(
                "We need to know which one is your room.\n" + mStringRequest + "1) Type the name of your Room;\n" + mStringRequestChoseFromList +
                        returnString);
        int option = inputUtils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputRoomName();
                if (!getRoomByName()) {
                    System.out.println("Unable to select a Room. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputRoomByList(mHouse);
                break;

            case 0:
                return;
            default:
                System.out.println(utilsUI.invalidOption);
                break;
        }
    }

    private boolean getInputRoomName() {
        Scanner mScanner = new Scanner(System.in);
        System.out.println("Please type the name of the Room you want to access.");
        this.mRoomName = mScanner.nextLine();
        return (!("exit".equals(mRoomName)));
    }

    private boolean getRoomByName() {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        List<Integer> roomIndexes = mController.getIndexHouseRoomsByString(mRoomName, mHouse);
        while (roomIndexes.isEmpty()) {
            System.out.println("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            roomIndexes = mController.getIndexHouseRoomsByString(mRoomName, mHouse);
        }
        if (roomIndexes.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(mController.printHouseRoomsByIndex(roomIndexes, mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (roomIndexes.contains(aux)) {
                this.mRoom = mHouse.getRoomList().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(mController.printRoom(mRoom));
            } else {
                System.out.println(utilsUI.invalidOption);
            }
        } else {
            System.out.println("You have chosen the following Room:");
            this.mRoom = mHouse.getRoomList().get(0);
            System.out.println(mController.printRoom(mRoom));
        }
        return true;
    }


    private void getInputRoomByList(House house) {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (house.getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        System.out.println("Please select one of the existing rooms on the selected House: ");
        boolean activeInput = false;
        while (!activeInput) {
            System.out.println(mController.printHouseRoomList(house));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux < house.getRoomList().size() && aux >= 0) {
                activeInput = true;
                this.mRoom = house.getRoomList().get(aux);
            } else {
                System.out.println(utilsUI.invalidOption);
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

    private void getInputRoomUS149() {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        System.out.println(
                "We need to know which one is your room.\n" + mStringRequest + "1) Type the name of your Room;\n" + mStringRequestChoseFromList +
                        returnString);
        int option = inputUtils.readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputRoomName();
                printRoomValidation();
                break;
            case 2:
                getInputRoomByListInEG();
                break;
            case 0:
                return;
            default:
                System.out.println(utilsUI.invalidOption);
                break;
        }
    }

    private void getInputRoomByListInEG() {
        UtilsUI utilsUI = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (mEnergyGrid.getListOfRooms().getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            System.out.println(mController.printGridRooms(mEnergyGrid));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mEnergyGrid.getListOfRooms().getRoomList().size()) {
                this.mRoom = mEnergyGrid.getListOfRooms().getRoomList().get(aux);
                activeInput = true;
            } else {
                System.out.println(utilsUI.invalidOption);
            }
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

    void printRoomValidation() {
        if (!getRoomByName()) {
            System.out.println("Unable to select a Room. Returning to main menu.");
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
