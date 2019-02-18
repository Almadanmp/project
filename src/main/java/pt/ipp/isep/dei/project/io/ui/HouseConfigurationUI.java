package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

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
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printHouseConfigMenu();
            option = inputUtils.getInputAsInt();
            switch (option) {
                case 1:
                    runUS101(house);
                    activeInput = false;
                    break;

                case 2:
                    runUS105(house);
                    activeInput = false;
                    break;
                case 3:
                    runUS108(house);
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

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
    private void runUS101(House house){
        getInputHouseCharacteristicsUS101();
        updateHouseUS101(house);
        displayHouseUS101(house);
    }

    private void getInputHouseCharacteristicsUS101() {
        InputUtils inputUtils = new InputUtils();
        Scanner scanner = new Scanner(System.in);

        // get house address
        System.out.print("Please, type the address of the house: ");
        this.mHouseAddress = scanner.nextLine();

        // get zip code
        System.out.print("Please, type the Zip Code of the house: ");
        this.mHouseZipCode = scanner.nextLine();

        //get latitude
        System.out.print("Please, type the latitude: ");
        this.mHouseLat = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the longitude: ");
        this.mHouseLon = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
        this.mHouseAlt = inputUtils.getInputAsDouble();

    }

    /**
     * Method updates the house using the input previously stored.
     * @param house receives the house the program is managing, so its parameters get changed.
     */
    private void updateHouseUS101(House house) {
        controller.setHouseLocal(mHouseLat, mHouseLon, mHouseAlt, house);
        controller.setHouseZIPCode(mHouseZipCode, house);
        controller.setHouseAddress(mHouseAddress, house);
    }

    /**
     * Method displays the house after all the changes have happened.
     * @param house receives the house the program is managing, so its new parameters get displayed.
     */
    private void displayHouseUS101(House house) {
        String houseId = controller.getHouseName(house);
        System.out.println("You have successfully changed the location of the house " + houseId + ". \n" + "Address: " +
                mHouseAddress + ". \n" + "ZipCode: " + mHouseZipCode + ". \n" + "Latitude: " + mHouseLat + ". \n" +
                "Longitude: " + mHouseLon + ". \n" + "Altitude: " + mHouseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house){
        getInputRoomCharacteristics();
        displayStateRoom();
        updateRoomAndDisplayState(house);
    }

    /**
     * Method gets input from user to save as the characteristics of a room.
     */
    private void getInputRoomCharacteristics() {
        Scanner input = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils();

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room name: ");
        this.mRoomName = input.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        this.mRoomHouseFloor = inputUtils.getInputAsInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        this.mRoomWidth = inputUtils.getInputAsDouble();

        System.out.println("Please insert your room's length in meters: ");
        this.mRoomLength = inputUtils.getInputAsDouble();

        System.out.println("Please insert your room's height in meters: ");
        this.mRoomHeight = inputUtils.getInputAsDouble();
    }

    /**
     * Method displays the input room and its characteristics.
     */
    private void displayStateRoom() {
        String yourNewRoom = "The room you are trying to create is called ";
        String located = ", it would be located on the ";
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
     * Method tries to create a room based on the input from user and returns message to user
     * @param house receives program house so the room can be added to it.
     */
    private void updateRoomAndDisplayState(House house) {
        if (controller.createNewRoom(house, mRoomName, mRoomHouseFloor, mRoomWidth, mRoomLength, mRoomHeight)) {
            System.out.println("The room has been added to house.");
        } else {
            System.out.println("The room you entered already exists in house. Please try again.");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
    * - MARIA MEIRELES, TERESA VARELA */
    private void runUS108(House house){
        UtilsUI utilsUI = new UtilsUI();
        if(!utilsUI.houseRoomListIsValid(house)){
            System.out.println(utilsUI.invalidRoomList);
            return;
        }
        printRoomList(house);
    }

    private void printRoomList(House house) {
        System.out.println(controller.buildRoomsString(house));
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


