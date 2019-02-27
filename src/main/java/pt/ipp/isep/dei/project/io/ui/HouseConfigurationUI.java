package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;

import java.util.Scanner;

class HouseConfigurationUI {
    private HouseConfigurationController controller;
    private String roomName;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private double houseLat;
    private double houseLon;
    private double houseAlt;
    private Address address;
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
        System.out.print("Please, type the street where the house is located: ");
        String street = scanner.nextLine();

        // get zip code
        System.out.print("Please, type the address's zip code: ");
        String zip = scanner.nextLine();

        // get town
        System.out.println("Please, type the town where the house is located: ");
        String town = scanner.nextLine();

        this.address = new Address(street, zip, town);

        //get latitude
        System.out.print("Please, type the latitude: ");
        this.houseLat = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the longitude: ");
        this.houseLon = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
        this.houseAlt = inputUtils.getInputAsDouble();

    }

    /**
     * Method updates the house using the input previously stored.
     * @param house receives the house the program is managing, so its parameters get changed.
     */
    private void updateHouseUS101(House house) {
        controller.setHouseLocal(houseLat, houseLon, houseAlt, house);
        controller.setHouseAddress(this.address, house);
    }

    /**
     * Method displays the house after all the changes have happened.
     * @param house receives the house the program is managing, so its new parameters get displayed.
     */
    private void displayHouseUS101(House house) {
        String houseId = controller.getHouseName(house);
        System.out.println("You have successfully changed the location of the house " + houseId + ". \n" + "Street: " +
                this.address.getStreet() + ". \n" + "ZipCode: " + this.address.getZip() + ". \n" + "Town: " + this.address.getTown() + ". \n" + "Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house){
        getInputRoomCharacteristics();
        Room room = createNewRoom(house);
        displayRoom();
        boolean added = addRoomToHouse(house, room);
        displayFinalState(added);
    }


    /**
     * Method gets input from user to save as the characteristics of a room.
     */
    private void getInputRoomCharacteristics() {
        Scanner scanner = new Scanner(System.in);
        InputUtils inputUtils = new InputUtils();

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room's name: ");
        this.roomName = scanner.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        this.roomHouseFloor = inputUtils.getInputAsInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        this.roomWidth = inputUtils.getInputAsDoublePositive();

        System.out.println("Please insert your room's length in meters: ");
        this.roomLength = inputUtils.getInputAsDoublePositive();

        System.out.println("Please insert your room's height in meters: ");
        this.roomHeight = inputUtils.getInputAsDoublePositive();
    }

    private Room createNewRoom(House house){
        return controller.createNewRoom(house,roomName,roomHouseFloor,roomWidth,roomLength,roomHeight);
    }

    /**
     * Method displays the input room and its characteristics.
     */
    private void displayRoom() {
        String yourNewRoom = "The room is called ";
        String located = ", located on the ";
        String width = " meters of width, ";
        String length = " meters of length and ";
        String height = " meters of height.";
        //SHOW ROOM ENTERED BY USER
        if (roomHouseFloor == 1) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "st floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 2) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "nd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 3) {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "rd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else {
            System.out.println(yourNewRoom + roomName + located + roomHouseFloor + "th floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        }
    }

    private boolean addRoomToHouse(House house, Room room){
        return controller.addRoomToHouse(house, room);
    }

    private void displayFinalState(boolean addedRoom){
        if(addedRoom){
            System.out.println("The " + roomName + " was added to the house.\n");
        }
        else {
            System.out.println("The " + roomName + " wasn't added to the house because it already exists.\n");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
    * - MARIA MEIRELES, TERESA VARELA */
    private void runUS108(House house){
        UtilsUI utilsUI = new UtilsUI();
        if (utilsUI.houseRoomListIsValid(house)) {
            printRoomList(house);
        } else {
            System.out.println(utilsUI.invalidRoomList);
        }
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


