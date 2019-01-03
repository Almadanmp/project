package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controller.US108Controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;
import java.util.Scanner;


/**
 * As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
 */

public class US108UI {

    private Scanner mScanner;
    private GeographicAreaList mGAList;
    private US108Controller ctrl108;
    private GeographicArea mGeoArea;
    private House mHouse;
    private String mGeoName;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private List<Integer> listOfIndexesGeographicAreas;
    private List<Integer> listOfIndexesHouses;
    private String mHouseName;
    private Room mRoom;
    private String mRoomName;
    private List<Integer> listOfIndexesRoom;


    public US108UI() {
        this.mScanner = new Scanner(System.in);
        this.ctrl108 = new US108Controller();
    }

    public void runUS108UI(GeographicAreaList newGeoListUi) {
        this.mGAList = newGeoListUi;
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        getInputGeographicArea(newGeoListUi);
        getInputHouse(mGeoArea);
        if (mHouse == null) {
            System.out.println("Unable to select a house. Returning to main menu.");
            return;
        }
        getInputRoom();

    }

    private void getInputGeographicArea(GeographicAreaList newGeoListUi) {
        System.out.println(
                "We need to know where your house is located\n" + "Would you like to:\n" + "1) Type the Geographic Area name;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputGeographicAreaName();
                if (!getGeographicAreaByName(newGeoListUi)) {
                    System.out.println("Unable to select a Geographic Area. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputGeographicAreaByList(newGeoListUi);
                break;
            case 0:
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputGeographicAreaName() {
        System.out.println("Please type the name of the Geographic Area Where Your House Is Located.");
        this.mGeoName = mScanner.nextLine();
        return (!(mGeoName.equals("exit")));
    }

    private boolean getGeographicAreaByName(GeographicAreaList newGeoListUi) {
        listOfIndexesGeographicAreas = this.ctrl108.matchGeographicAreaIndexByString(mGeoName, newGeoListUi);

        while (listOfIndexesGeographicAreas.isEmpty()) {
            System.out.println("There is no Geographic Area with that name. Please insert the name of a Geographic Area" +
                    " that exists or  Type 'exit' to cancel and create a new Geographic Area on the Main Menu.");
            if (!getInputGeographicAreaName()) {
                return false;
            }
            listOfIndexesGeographicAreas = this.ctrl108.matchGeographicAreaIndexByString(mGeoName, newGeoListUi);
        }
        if (listOfIndexesGeographicAreas.size() > 1) {
            System.out.println("There are multiple Geographic Areas with that name. Please choose the right one.");
            System.out.println(this.ctrl108.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas, newGeoListUi));
            int aux = readInputNumberAsInt();
            if (listOfIndexesGeographicAreas.contains(aux)) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println(this.ctrl108.printGA(mGeoArea));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Geographic Area:");
            mGeoArea = newGeoListUi.getGeographicAreaList().get(listOfIndexesGeographicAreas.get(0));
            System.out.println(this.ctrl108.printGA(mGeoArea));
        }
        return true;
    }

    private void getInputGeographicAreaByList(GeographicAreaList newGeoListUi) {
        boolean activeInput = false;
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");

        while (!activeInput) {
            this.ctrl108.printGAList(newGeoListUi);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < newGeoListUi.getGeographicAreaList().size()) {
                mGeoArea = newGeoListUi.getGeographicAreaList().get(aux);
                activeInput = true;
                //TODO fazer um print bonito
                System.out.println("You have chosen the following Geographic Area:");
                System.out.println((mGeoArea.printGeographicArea()));
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }

    private void getInputHouse(GeographicArea mGeoArea) {
        System.out.println(
                "We need to know which one is your house.\n" + "Would you like to:\n" + "1) Type the name of your House;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
        int option = readInputNumberAsInt();
        switch (option) {
            case 1:
                getInputHouseName();
                if (!getHouseByName(mGeoArea)) {
                    System.out.println("Unable to select a House. Returning to main menu.");
                    return;
                }
                break;
            case 2:
                getInputHouseByList(mGeoArea);
                break;
            case 0:
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputHouseName() {
        System.out.println("Please type the name of the House you want to access.");
        this.mHouseName = mScanner.nextLine();
        return (!(mHouseName.equals("exit")));
    }

    private boolean getHouseByName(GeographicArea mGeoArea) {
        listOfIndexesHouses = this.ctrl108.matchHouseIndexByString(mHouseName, mGeoArea);

        while (listOfIndexesHouses.isEmpty()) {
            System.out.println("There is no House Area with that name. Please insert the name of a House" +
                    " that exists or  Type 'exit' to cancel and create a new House on the Main Menu.");
            if (!getInputHouseName()) {
                return false;
            }
            listOfIndexesHouses = this.ctrl108.matchHouseIndexByString(mHouseName, mGeoArea);
        }
        if (listOfIndexesHouses.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(this.ctrl108.printHouseElementsByIndex(listOfIndexesHouses, mGeoArea));
            int aux = readInputNumberAsInt();
            if (listOfIndexesHouses.contains(aux)) {
                mGeoArea.getHouseList().getHouseList().get(aux);
                System.out.println("You have chosen the following House:");
                System.out.println(this.ctrl108.printHouse(mHouse));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following House:");
            mGeoArea.getHouseList().getHouseList().get(0);
            System.out.println(this.ctrl108.printHouse(mHouse));
        }
        return true;
    }


    private void getInputHouseByList(GeographicArea mGeoArea) {
        if (mGeoArea.getHouseList().getHouseList().size() == 0) {
            System.out.print("Invalid House List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing houses on the selected geographic area: ");

        while (!activeInput) {
            this.ctrl108.printHouseList(mGeoArea);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mGeoArea.getHouseList().getHouseList().size()) {
                mHouse = mGeoArea.getHouseList().getHouseList().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }


    private int readInputNumberAsInt() {
        while (!mScanner.hasNextDouble()) {
            System.out.println(INVALID_OPTION);
            mScanner.next();
        }
        Double option = mScanner.nextDouble();
        return option.intValue();
    }

    private void getInputRoom() {
        System.out.println(
                "We need to know which one is your room.\n" + "Would you like to:\n" + "1) Type the name of your Room;\n" + "2) Choose it from a list;\n" +
                        "0) Return;");
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
                getInputRoomByList();
                break;
            case 0:
                return;
            default:
                System.out.println(INVALID_OPTION);
                break;
        }
    }

    private boolean getInputRoomName() {
        System.out.println("Please type the name of the Room you want to access.");
        this.mRoomName = mScanner.nextLine();
        return (!(this.mRoomName.equals("exit")));
    }

    private boolean getRoomByName() {
        this.listOfIndexesRoom = this.ctrl108.matchRoomIndexByString(mRoomName, mHouse);

        while (listOfIndexesRoom.isEmpty()) {
            System.out.println("There is no Room with that name. Please insert the name of a Room" +
                    " that exists or  Type 'exit' to cancel and create a new Room on the Main Menu.");
            if (!getInputRoomName()) {
                return false;
            }
            listOfIndexesRoom = this.ctrl108.matchRoomIndexByString(mRoomName, mHouse);
        }
        if (listOfIndexesRoom.size() > 1) {
            System.out.println("There are multiple Houses with that name. Please choose the right one.");
            System.out.println(this.ctrl108.printRoomElementsByIndex(listOfIndexesRoom, mHouse));
            int aux = readInputNumberAsInt();
            if (listOfIndexesRoom.contains(aux)) {
                mHouse.getmRoomList().getListOfRooms().get(aux);
                System.out.println("You have chosen the following Room:");
                System.out.println(this.ctrl108.printRoom(mRoom));
            } else {
                System.out.println(INVALID_OPTION);
            }
        } else {
            System.out.println("You have chosen the following Room:");
            mHouse.getmRoomList().getListOfRooms().get(0);
            System.out.println(this.ctrl108.printRoom(mRoom));
        }
        return true;
    }


    private void getInputRoomByList() {
        if (mHouse.getmRoomList().getListOfRooms().size() == 0) {
            System.out.print("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House: ");

        while (!activeInput) {
            this.ctrl108.printRoomList(mHouse);
            int aux = readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getmRoomList().getListOfRooms().size()) {
                this.mRoom = mHouse.getmRoomList().getListOfRooms().get(aux);
                activeInput = true;
            } else {
                System.out.println(INVALID_OPTION);
            }
        }
    }
}
    /**
    private US108Controller ctrl108;
    private boolean active;

    public void US108UI() {
        this.active = false;
    }

    public void run(HouseList houseList) {
        this.active = true;
        this.ctrl108 = new US108Controller(houseList);

        while (this.active) {
            if (!displayHouseList()) {
                return;
            } else {
                getHouse();
                displayRoomList();
                getRoom();
                setInputRoom();
                return;
            }
        }

    }

    public boolean displayHouseList() {
        if (this.ctrl108.getHouseList().getHouseList().isEmpty()) {
            System.out.println(this.ctrl108.printHouseListNames());
            return false;
        } else {
            System.out.println(this.ctrl108.printHouseListNames());
            return true;
        }
    }

    private boolean getHouse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the house where the room is in:");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        String houseName = scanner.next();
        if (this.ctrl108.matchHouseByName(houseName)) {
            System.out.println("You chose the House " + houseName);
        } else {
            System.out.println("This house does not exist in the list of houses.");
            return false;
        }
        return true;
    }


    private boolean displayRoomList() {
        if (this.ctrl108.getRoomList().getListOfRooms().isEmpty()) {
            System.out.println(this.ctrl108.printRoomListNames());
            return false;
        } else {
            System.out.println(this.ctrl108.printRoomListNames());
            return true;
        }
    }

    private boolean getRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the name of the room you want to reconfigure:");
        while (!scanner.hasNext("[\\p{L}\\s]+")) {
            System.out.println("Please,try again:");
            scanner.next();
        }
        String roomName = scanner.next();
        if (this.ctrl108.matchRoom(roomName)) {
            System.out.println("You chose the Room " + roomName);
        } else {
            System.out.println("This room does not exist in the list of rooms.");
            return false;
        }
        return true;
    }


    private void setInputRoom() {
        Scanner input = new Scanner(System.in);

        ////GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        String roomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while (!input.hasNextInt()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        int roomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's dimensions in square meters: ");
        while (!input.hasNextDouble()) {
            input.next();
            System.out.println("Please insert a valid number.");
        }
        double roomDimensions = input.nextDouble();
        this.ctrl108.setRoom(roomName,roomHouseFloor,roomDimensions);
        if (roomHouseFloor == 1) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "st floor and has " + roomDimensions + " square meters.");
        } else if (roomHouseFloor == 2) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "nd floor and has " + roomDimensions + " square meters.");
        } else if (roomHouseFloor == 3) {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "rd floor and has " + roomDimensions + " square meters.");
        } else {
            System.out.println("Your room is now called " + roomName + ", it is located on the " + roomHouseFloor + "th floor and has " + roomDimensions + " square meters.");
        }
    }
}


**/
