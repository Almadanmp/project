package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.TypeAreaList;

import java.util.Scanner;

class HouseConfigurationUI {
    private HouseConfigurationController controller;
    private String mRoomName;
    private int mRoomHouseFloor;
    private double mRoomWidth;
    private double mRoomLength;
    private double mRoomHeight;
    private double mHouseLat;
    private double mHouseLon;
    private double mHouseAlt;
    private String mHouseAddress;
    private String mHouseZipCode;
    private static final String INVALID_OPTION = "Please enter a valid option";

    public HouseConfigurationUI() {
        this.controller = new HouseConfigurationController();
    }

    public void run(GeographicAreaList newGeoListUi, TypeAreaList typeAreaList, House house) {
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = false;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (!activeInput) {
            printOptionMessage();
            option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputHouseCharacteristicsUS101();
                    updateModelUS101(house);
                    displayStateUS101(house);
                    activeInput = true;
                    break;

                case 2:
                    getInputRoomCharacteristics();
                    updateInputRoom();
                    displayStateRoom();
                    updateRoomAndDisplayState(house);
                    activeInput = true;
                    break;
                case 3:
                    showListRooms(house);
                    activeInput = true;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(INVALID_OPTION);
                    break;
            }
        }
    }

    //  SHARED METHODS


    /**
     * ------ GET ROOM CHARACTERISTICS --------
     **/

    private void getInputRoomCharacteristics() {
        Scanner input = new Scanner(System.in);

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        this.mRoomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while (!input.hasNextInt()) {
            System.out.println("Please insert a valid number.");
        }
        this.mRoomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println("Please insert a valid number.");
        }
        this.mRoomWidth = input.nextDouble();

        System.out.println("Please insert your room's length in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println("Please insert a valid number.");
        }
        this.mRoomLength = input.nextDouble();

        System.out.println("Please insert your room's height in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println("Please insert a valid number.");
        }
        this.mRoomHeight = input.nextDouble();
    }


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house */
    private void getInputHouseCharacteristicsUS101() {

        Scanner scanner = new Scanner(System.in);

        // get house address
        System.out.print("Please, type the address of the house: ");
        this.mHouseAddress = scanner.nextLine();


        // get zip code
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.nextLine();


        //get latitude
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLat = scanner.nextDouble();


        // get longitude
        System.out.print("Please, type the longitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Please,try again. Only numbers this time:");
            scanner.next();
        }
        this.mHouseLon = scanner.nextDouble();

    }

    private void updateModelUS101(House house) {
        controller.setHouseLocal(mHouseLat, mHouseLon, mHouseAlt, house);
        controller.setHouseZIPCode(mHouseZipCode, house);
        controller.setHouseAddress(mHouseAddress, house);
    }

    private void displayStateUS101(House house) {
        System.out.println("You have successfully changed the location of the house " + house.getHouseId() + ". \n" + "Address: " +
                mHouseAddress + ". \n" + "ZipCode: " + mHouseZipCode + ". \n" + "Latitude: " + mHouseLat + ". \n" +
                "Longitude: " + mHouseLon + ". \n");
    }


    /* USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    house floor and dimensions) */

    private void updateInputRoom() {
        this.controller.createNewRoom(mRoomName, mRoomHouseFloor, mRoomWidth, mRoomLength, mRoomHeight);
    }

    private void displayStateRoom() {
        //SHOW ROOM ENTERED BY USER
        if (mRoomHouseFloor == 1) {
            System.out.println("Your new room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "st floor and has " + mRoomWidth + " meters in width, " + mRoomLength + " meters in length and " + mRoomHeight + " meters in height.");
        } else if (mRoomHouseFloor == 2) {
            System.out.println("Your new room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "nd floor and has " + mRoomWidth + " meters in width, " + mRoomLength + " meters in length and " + mRoomHeight + " meters in height.");
        } else if (mRoomHouseFloor == 3) {
            System.out.println("Your new a room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "rd floor and has " + mRoomWidth + " meters in width, " + mRoomLength + " meters in length and " + mRoomHeight + " meters in height.");
        } else {
            System.out.println("Your new a room is called " + mRoomName + ", it is located on the " + mRoomHouseFloor + "th floor and has " + mRoomWidth + " meters in width, " + mRoomLength + " meters in length and " + mRoomHeight + " meters in height.");
        }
    }

    private void updateRoomAndDisplayState(House house) {
        String mHouseName = controller.getHouseName(house);
        if (controller.addRoomToHouse(house)) {
            System.out.println("The room " + this.mRoomName + " has been added to house " + mHouseName + ".");
        } else {
            System.out.println("The room you entered already exists in house " + mHouseName + ".");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it. */

    private void showListRooms(House house) {
        System.out.println(controller.printRooms(house.getRoomList()));
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printOptionMessage() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Configure the location of the house. (US101)");
        System.out.println("2) Add a new room to the house. (US105)");
        System.out.println("3) List the existing rooms. (US108)");
        System.out.println("0) (Return to main menu)\n");
    }

}


