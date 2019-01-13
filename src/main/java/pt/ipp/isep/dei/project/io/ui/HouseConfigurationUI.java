package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.House;

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

    HouseConfigurationUI() {
        this.controller = new HouseConfigurationController();
    }

    void run(House house) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if (house == null) {
            System.out.println("Please create a House before you continue.");
            return;
        }
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printHouseConfigMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
                    getInputHouseCharacteristicsUS101();
                    updateHouseUS101(house);
                    displayHouseUS101(house);
                    activeInput = false;
                    break;

                case 2:
                    getInputRoomCharacteristics();
                    updateInputRoom();
                    displayStateRoom();
                    updateRoomAndDisplayState(house);
                    activeInput = false;
                    break;
                case 3:
                    printRoomList(house);
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

    //  SHARED METHODS


    /**
     * Method gets input from user to save as the characteristics of a room.
     */

    private void getInputRoomCharacteristics() {
        String insertValidNumber = "Please insert a valid number.";
        Scanner input = new Scanner(System.in);

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        this.mRoomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        while (!input.hasNextInt()) {
            System.out.println(insertValidNumber);
        }
        this.mRoomHouseFloor = input.nextInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println(insertValidNumber);
        }
        this.mRoomWidth = input.nextDouble();

        System.out.println("Please insert your room's length in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println(insertValidNumber);
        }
        this.mRoomLength = input.nextDouble();

        System.out.println("Please insert your room's height in meters: ");
        while (!input.hasNextDouble()) {
            System.out.println(insertValidNumber);
        }
        this.mRoomHeight = input.nextDouble();
    }


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
    private void getInputHouseCharacteristicsUS101() {

        Scanner scanner = new Scanner(System.in);

        // get house address
        System.out.print("Please, type the address of the house: ");
        this.mHouseAddress = scanner.nextLine();


        // get zip code
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.nextLine();


        //get latitude
        String onlyNumbers = "Please,try again. Only numbers this time:";
        System.out.print("Please, type the latitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mHouseLat = scanner.nextDouble();


        // get longitude
        System.out.print("Please, type the longitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mHouseLon = scanner.nextDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mHouseAlt = scanner.nextDouble();

    }

    /**
     * Method updates the house using the input previously stored.
     *
     * @param house receives the house the program is managing, so its parameters get changed.
     */

    private void updateHouseUS101(House house) {
        controller.setHouseLocal(mHouseLat, mHouseLon, mHouseAlt, house);
        controller.setHouseZIPCode(mHouseZipCode, house);
        controller.setHouseAddress(mHouseAddress, house);
    }

    /**
     * Method displays the house after all the changes have happened.
     *
     * @param house receives the house the program is managing, so its new parameters get displayed.
     */

    private void displayHouseUS101(House house) {
        System.out.println("You have successfully changed the location of the house " + house.getHouseId() + ". \n" + "Address: " +
                mHouseAddress + ". \n" + "ZipCode: " + mHouseZipCode + ". \n" + "Latitude: " + mHouseLat + ". \n" +
                "Longitude: " + mHouseLon + ". \n" + "Altitude: " + mHouseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.

    /**
     * Method creates a new room with the parameters previously provided by the user.
     */

    private void updateInputRoom() {
        this.controller.createNewRoom(mRoomName, mRoomHouseFloor, mRoomWidth, mRoomLength, mRoomHeight);
    }

    /**
     * Method displays the Room and its characteristics.
     */

    private void displayStateRoom() {
        String yourNewRoom = "Your new room is called ";
        String located = ", it is located on the ";
        String width = " meters of width, ";
        String length = " meters of length and ";
        String height = " meters of height.";
        //SHOW ROOM ENTERED BY USER
        if (mRoomHouseFloor == 1) {
            System.out.println(yourNewRoom + mRoomName + located + mRoomHouseFloor + "st floor and has " + mRoomWidth + width + mRoomLength + length + mRoomHeight + height);
        } else if (mRoomHouseFloor == 2) {
            System.out.println(yourNewRoom + mRoomName + located + mRoomHouseFloor + "nd floor and has " + mRoomWidth + width + mRoomLength + length + mRoomHeight + height);
        } else if (mRoomHouseFloor == 3) {
            System.out.println(yourNewRoom + mRoomName + located + mRoomHouseFloor + "rd floor and has " + mRoomWidth + width + mRoomLength + length + mRoomHeight + height);
        } else {
            System.out.println(yourNewRoom + mRoomName + located + mRoomHouseFloor + "th floor and has " + mRoomWidth + width + mRoomLength + length + mRoomHeight + height);
        }
    }

    /**
     * Method calls on controller to add the created room to the house the program manages.
     *
     * @param house receives the house the program manages so the room can be added to it.
     */

    private void updateRoomAndDisplayState(House house) {
        String mHouseName = controller.getHouseName(house);
        if (controller.addRoomToHouse(house)) {
            System.out.println("The room " + this.mRoomName + " has been added to house " + mHouseName + ".");
        } else {
            System.out.println("The room you entered already exists in house " + mHouseName + ". Please try again.");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
    * - MARIA MEIRELES, TERESA VARELA */

    private void printRoomList(House house) {
        System.out.println(controller.printRooms(house.getRoomList()));
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printHouseConfigMenu() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Configure the location of the house. (US101)");
        System.out.println("2) Add a new room to the house. (US105)");
        System.out.println("3) List the existing rooms. (US108)");
        System.out.println("0) (Return to main menu)\n");
    }

}


