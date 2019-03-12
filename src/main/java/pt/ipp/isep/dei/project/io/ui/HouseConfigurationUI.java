package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.model.GeographicAreaList;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.reader.CSVReader;
import pt.ipp.isep.dei.project.reader.JSONReader;

import java.util.Scanner;

class HouseConfigurationUI {
    private HouseConfigurationController controller;
    private String roomName;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private static final String INVALID_OPTION = "Please enter a valid option";

    HouseConfigurationUI() {
        this.controller = new HouseConfigurationController();
    }

    void run(House house, GeographicAreaList list) {
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
                    runUS15(list);
                    activeInput = false;
                    break;
                case 2:
                    runUS20(list);
                    activeInput = false;
                    break;
                case 3:
                    runUS101(house);
                    activeInput = false;
                    break;
                case 4:
                    runUS105(house);
                    activeInput = false;
                    break;
                case 5:
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

    // USER STORY 15 - As an Administrator, I want to import Geographic Areas and Sensors from a JSON file.

    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON file.
     * @param list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15(GeographicAreaList list){
        InputUtils input = new InputUtils();
        String filePath = input.getInputPath();
        JSONReader reader = new JSONReader();
        GeographicAreaDTO[] fileAreas = reader.readFile(filePath);
        updateModel(fileAreas, list);
    }

    /**
     * Transfer changes from the US to model: Calls the controller to add
     * geographic areas from reading the file to program's list of geographic areas.
     * @param fileAreas is an array of all the Geographic Area DTOs created upon reading the .json file.
     * @param list is the static list of the program's Geographic Areas.
     */

    private void updateModel(GeographicAreaDTO[] fileAreas, GeographicAreaList list){
        controller.addGeoAreasToList(fileAreas, list);
    }

    /* USER STORY 20 - As an Administrator,want to import geographical areas sensors’ readings into the application
     from a CSV file. Data outside the valid sensor operation period shouldn’t be imported but registered in the
     application log. */

    /**
     * As an Administrator, I want to import geographical areas sensors’ readings into the application
     *      from a CSV file.
     * @param list is the static, program list of geographic areas that comes from mainUI.
     */
    private void runUS20(GeographicAreaList list){
        CSVReader reader = new CSVReader();
        reader.readAndSet(list);
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */

      private void runUS101(House house) {
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

        //get latitude
        System.out.print("Please, type the latitude: ");
          double houseLat = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the longitude: ");
          double houseLon = inputUtils.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
          double houseAlt = inputUtils.getInputAsDouble();

        controller.setHouseLocal(houseLat, houseLon, houseAlt, house);
        controller.setHouseAddress(street, zip, town, house);

        String houseId = controller.getHouseName(house);
        System.out.println("You have successfully changed the location of the house " + houseId + ". \n" + "Street: " +
                street + ". \n" + "ZipCode: " + zip + ". \n" + "Town: " + town + ". \n" + "Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house) {
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
        this.roomWidth = inputUtils.getInputAsDoubleZeroOrPositive();

        System.out.println("Please insert your room's length in meters: ");
        this.roomLength = inputUtils.getInputAsDoubleZeroOrPositive();

        System.out.println("Please insert your room's height in meters: ");
        this.roomHeight = inputUtils.getInputAsDoubleZeroOrPositive();
    }

    private Room createNewRoom(House house) {
        return controller.createNewRoom(house, roomName, roomHouseFloor, roomWidth, roomLength, roomHeight);
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

    private boolean addRoomToHouse(House house, Room room) {
        return controller.addRoomToHouse(house, room);
    }

    private void displayFinalState(boolean addedRoom) {
        if (addedRoom) {
            System.out.println("The " + roomName + " was added to the house.\n");
        } else {
            System.out.println("The " + roomName + " wasn't added to the house because it already exists.\n");
        }
    }


    /* USER STORY 108 - As an Administrator, I want to have a list of existing rooms, so that I can choose one to edit it.
     * - MARIA MEIRELES, TERESA VARELA */
    private void runUS108(House house) {
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
        System.out.println("1) Import Geographic Areas and Sensors from a JSON file.");
        System.out.println("2) Import Geographic Area Sensor Readings from a CSV file.");
        System.out.println("3) Configure the location of the house. (US101)");
        System.out.println("4) Add a new room to the house. (US105)");
        System.out.println("5) List the existing rooms. (US108)");
        System.out.println("0) (Return to main menu)\n");
    }
}


