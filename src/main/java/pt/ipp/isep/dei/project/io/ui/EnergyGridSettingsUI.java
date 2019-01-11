package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.EnergyGridSettingsController;
import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static pt.ipp.isep.dei.project.io.ui.UtilsUI.INVALID_OPTION;
import static pt.ipp.isep.dei.project.io.ui.UtilsUI.readInputNumberAsInt;

class EnergyGridSettingsUI {
    private EnergyGridSettingsController mController;
    private EnergyGrid mEnergyGrid;
    private String mGridName;
    private Room mRoom;
    private String mRoomName;
    private House mHouse;
    private String mStringRequest = "Would you like to:\n";
    private String mStringRequestChoseFromList = "2) Choose it from a list:\n";
    private String s1 = "return";

    EnergyGridSettingsUI() {
        this.mController = new EnergyGridSettingsController();
    }

    void run(House house) {
        this.mHouse = house;
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Energy Grid Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printEnergyGridMenu();
            option = UtilsUI.readInputNumberAsInt();
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
                    getInputEnergyGrid(mHouse);
                    displayRoomList(mEnergyGrid);
                    activeInput = false;
                    break;
                case 4:
                    getInputRoom();
                    getInputEnergyGrid(mHouse);
                    updateGridUS147(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 5:
                    getInputEnergyGrid(mHouse);
                    getInputRoomUS149();
                    updateGridUS149(mEnergyGrid, mRoom);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }

    //SHARED METHODS THROUGH UIS

    private void getInputEnergyGrid(House house) {
        System.out.println(
                "We need to know which one is your energy grid.\n" + mStringRequest + "1) Type the name of your grid;\n" + mStringRequestChoseFromList +
                        s1);
        int option = readInputNumberAsInt();
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
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getGridByName(House house) {
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
            int aux = readInputNumberAsInt();
            if (listOfIndexesGrids.contains(aux)) {
                mEnergyGrid = house.getEGList().getEnergyGridList().get(aux);
                System.out.println("You have chosen the following grid:");
                System.out.println(mController.printEnergyGrid(mEnergyGrid));
            } else {
                System.out.println(INVALID_OPTION);
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
        if (house.getEGList().getEnergyGridList().isEmpty()) {
            System.out.print("Invalid Grid List - List Is Empty\n");
            System.out.println("If you want to create an energy grid and associate it to this house, type yes:");
            Scanner scanner = new Scanner(System.in);
            if (Objects.equals(scanner.nextLine(), "yes")) {
                getInputUS130();
                updateHouse(house);
                return;
            } else {
                return;
            }
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing grids on the selected house: ");
        while (!activeInput) {
            System.out.println(mController.printGridList(house));
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < house.getEGList().getEnergyGridList().size()) {
                mEnergyGrid = house.getEGList().getEnergyGridList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    // Get Input Room
    // Methods to get Rooms By Name / by List

    private void getInputRoom() {
        System.out.println(
                "We need to know which one is your room.\n" + mStringRequest + "1) Type the name of your Room;\n" + mStringRequestChoseFromList +
                        s1);
        int option = readInputNumberAsInt();
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
                System.out.println(INVALID_OPTION);
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
            int aux = readInputNumberAsInt();
            if (roomIndexes.contains(aux)) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(mController.printRoom(mRoom));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Room:");
            this.mRoom = mHouse.getRoomList().getRoomList().get(0);
            System.out.println(mController.printRoom(mRoom));
        }
        return true;
    }


    private void getInputRoomByList(House house) {
        if (house.getRoomList().getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        System.out.println("Please select one of the existing rooms on the selected House: ");
        boolean activeInput = false;
        while (!activeInput) {
            System.out.println(mController.printHouseRoomList(house));
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < house.getRoomList().getRoomList().size()) {
                this.mRoom = house.getRoomList().getRoomList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }


    // USER STORY 130 UI -  As an Administrator, I want to create a house grid, so that I can define the rooms that are
    // attached to it and the contracted maximum power for that grid.

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
    energy may be used by all devices on that grid.
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
    // can attach/detach rooms from it.

    private void displayRoomList(EnergyGrid energyGrid) {
        if (mEnergyGrid != null) {
            System.out.println(mController.printRooms(energyGrid.getListOfRooms()));
        } else {
            System.out.println("The energy grid you've selected has no rooms attached to it.");
        }
    }

    // USER STORY 147 -  As an Administrator, I want to attach a room to a house grid, so that the room’s power and
    // energy consumption is included in that grid.

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
        System.out.println(
                "We need to know which one is your room.\n" + mStringRequest + "1) Type the name of your Room;\n" + mStringRequestChoseFromList +
                        s1);
        int option = readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputRoomName();
                if (!getRoomByName()) {
                    System.out.println("Unable to select a Room. Returning to main menu.");
                    return;}
                break;
            case 2:
                getInputRoomByListInEG();
                break;
            case 0:
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private void getInputRoomByListInEG() {
        if (mEnergyGrid.getListOfRooms().getRoomList().isEmpty()) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            System.out.println(mController.printGridRooms(mEnergyGrid));
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mEnergyGrid.getListOfRooms().getRoomList().size()) {
                this.mRoom = mEnergyGrid.getListOfRooms().getRoomList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
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
        System.out.println("0) (Return to main menu)\n");
    }
}
