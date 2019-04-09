package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaService;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.ReadingService;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.List;
import java.util.Scanner;

class HouseConfigurationUI {
    private HouseConfigurationController controller;
    private String roomName;
    private String roomDescription;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private static final String VALID_LOG_PATH = "resources/logs/logOut.log";
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private AreaSensorService areaSensorService;
    private ReadingService readingService;

    HouseConfigurationUI(AreaSensorService areaSensorService, ReadingService readingService) {
        this.controller = new HouseConfigurationController();
        this.areaSensorService = areaSensorService;
        this.readingService = readingService;

    }

    void run(HouseService houseService, GeographicAreaService geographicAreaService, int gridMetPeriod, int devMetPeriod, List<String> deviceTypes) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printHouseConfigMenu();
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS15v2(geographicAreaService, houseService);
                    activeInput = false;
                    break;
                case 2:
                    runUS20v2(geographicAreaService, houseService);
                    activeInput = false;
                    break;
                case 3:
                    runUS100(gridMetPeriod, devMetPeriod, deviceTypes, houseService);
                    activeInput = false;
                    break;
                case 4:
                    runUS101(houseService, geographicAreaService);
                    activeInput = false;
                    break;
                case 5:
                    runUS105(houseService);
                    activeInput = false;
                    break;
                case 6:
                    runUS108(houseService);
                    activeInput = false;
                    break;
                case 7:
                    runUS260(houseService);
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
    // USER STORY 15v.2 - As an Administrator, I want to import Geographic Areas and Sensors from a JSON file and a XML file.


    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON or XML file.
     * <p>
     * list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15v2(GeographicAreaService geographicAreaService, HouseService houseService) {
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        InputHelperUI input = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPathJsonOrXML(result);
        int areas = ctrl.acceptPath(filePath, geographicAreaService);
        System.out.println(areas + " Geographic Areas have been successfully imported.");
    }


    /* USER STORY 20v2 - As an Administrator I want to import geographic area sensor readings into the application
     from a either a CSV, JSON or XML file.
     Data outside the valid sensor operation period should not be imported but registered in the
     application log. */

    /**
     * As an Administrator, I want to import geographic area sensor readings into the application
     * from a a CSV, JSON and XML file and display a message to the user of how many readings were
     * successfully imported.
     * <p>
     * geographicAreaList is the static, program list of geographic areas that comes from mainUI.
     */
    private void runUS20v2(GeographicAreaService geographicAreaService, HouseService houseService) {
        InputHelperUI inputHelperUI = new InputHelperUI();
        String path = inputHelperUI.getInputJsonXmlCsv();
        if (path.endsWith(".csv")) {
            readReadingsFromCSV(path, VALID_LOG_PATH, geographicAreaService, houseService);
        } else if (path.endsWith(".json")) {
            readReadingsFromJSON(path, VALID_LOG_PATH, geographicAreaService, houseService);
        } else if (path.endsWith(".xml")) {
            readReadingsFromXML(path, VALID_LOG_PATH, geographicAreaService, houseService);
        }
    }

    private void readReadingsFromCSV(String filePath, String logFilePath, GeographicAreaService geographicAreaService, HouseService houseService) {
        int result = 0;
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        try {
            result = ctrl.readReadingsFromCSV(geographicAreaService, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void readReadingsFromJSON(String filePath, String logFilePath, GeographicAreaService geographicAreaService, HouseService houseService) {
        int result = 0;
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        try {
            result = ctrl.readReadingsFromJSON(geographicAreaService, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void readReadingsFromXML(String filePath, String logFilePath, GeographicAreaService geographicAreaService, HouseService houseService) {
        int result = 0;
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        try {
            result = ctrl.readReadingsFromXML(geographicAreaService, filePath, logFilePath);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    /*As an Administrator, I want to configure the house from a file containing basic house information, grids and rooms.*/

    private void runUS100(int gridMetPeriod, int devMetPeriod, List<String> deviceTypes, HouseService houseService) {
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        InputHelperUI input = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPathJson(result);
        if (ctrl.readJSONAndDefineHouse(filePath, gridMetPeriod, devMetPeriod, deviceTypes)) {
            System.out.println("House Data Successfully imported.");
        }
        System.out.println("The JSON file is invalid.");
    }


    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
//TODO Location is just location ot Adress etc, doest make much sense with the new data.
    private void runUS101(HouseService houseService, GeographicAreaService geographicAreaService) {
        House house = houseService.getHouse();
        Scanner scanner = new Scanner(System.in);

        System.out.println("First select the geographic area where this house is located.");
        GeographicArea motherArea = InputHelperUI.getGeographicAreaByList(geographicAreaService);

//        // get house address
//        System.out.print("Please, type the street where the house is located: ");
//        String street = scanner.nextLine();
//
//        // get number
//        System.out.print("Please, type the number where the house is located: ");
//        String number = scanner.nextLine();
//
//        // get zip code
//        System.out.print("Please, type the address's zip code: ");
//        String zip = scanner.nextLine();
//
//        // get town
//        System.out.println("Please, type the town where the house is located: ");
//        String town = scanner.nextLine();
//
//        // get country
//        System.out.println("Please, type the country where the house is located: ");
//        String country = scanner.nextLine();

        //get latitude
        System.out.print("Please, type the latitude: ");
        double houseLat = InputHelperUI.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the longitude: ");
        double houseLon = InputHelperUI.getInputAsDouble();

        // get longitude
        System.out.print("Please, type the altitude: ");
        double houseAlt = InputHelperUI.getInputAsDouble();

        controller.setHouseLocal(houseLat, houseLon, houseAlt, house);
//        controller.setHouseAddress(street, number, zip, town, country, house);
        controller.setHouseMotherArea(house, motherArea);
        houseService.saveHouse(house);

        //       String houseId = controller.getHouseName(house);
//        System.out.println("\nYou have successfully configured the location of the house " + houseId + ". \n" + "Street: " +
//                street + ". \n" + "Number: " + number + ". \n" + "ZipCode: " + zip + ". \n" + "Town: " + town + ". \n" + "Country: " + country + ". \n" + "Latitude: " + houseLat + ". \n" +
//                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");

        System.out.println("\nYou have successfully configured the location of the house with the following Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(HouseService houseService) {
        House house = houseService.getHouse();
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

        //GET ROOM DESIGNATION
        System.out.println("Please insert the room's name: ");
        this.roomName = scanner.nextLine();

        //GET ROOM DESCRIPTION
        System.out.println("Please insert the room's description: ");
        this.roomDescription = scanner.nextLine();

        //GET ROOM HOUSE FLOOR
        System.out.println("Please insert your room's house floor: ");
        this.roomHouseFloor = InputHelperUI.getInputAsInt();

        //GET ROOM DIMENSIONS
        System.out.println("Please insert your room's width in meters: ");
        this.roomWidth = InputHelperUI.getInputAsDoublePositive();

        System.out.println("Please insert your room's length in meters: ");
        this.roomLength = InputHelperUI.getInputAsDoublePositive();

        System.out.println("Please insert your room's height in meters: ");
        this.roomHeight = InputHelperUI.getInputAsDoublePositive();
    }

    private Room createNewRoom(House house) {
        return controller.createNewRoom(house, roomDescription, roomName, roomHouseFloor, roomWidth, roomLength, roomHeight);
    }

    /**
     * Method displays the input room and its characteristics.
     */

    private void displayRoom() {
        String yourNewRoom = "The room is called ";
        String description = " it is ";
        String located = ", located on the ";
        String width = " meters of width, ";
        String length = " meters of length and ";
        String height = " meters of height.";
        //SHOW ROOM ENTERED BY USER
        if (roomHouseFloor == 1) {
            System.out.println(yourNewRoom + roomName + description + roomDescription + located + roomHouseFloor + "st floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 2) {
            System.out.println(yourNewRoom + roomName + description + roomDescription + located + roomHouseFloor + "nd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else if (roomHouseFloor == 3) {
            System.out.println(yourNewRoom + roomName + description + roomDescription + located + roomHouseFloor + "rd floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
        } else {
            System.out.println(yourNewRoom + roomName + description + roomDescription + located + roomHouseFloor + "th floor and has " + roomWidth + width + roomLength + length + roomHeight + height);
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
    private void runUS108(HouseService houseService) {
        House house = houseService.getHouse();
        if (!house.isRoomListEmpty()) {
            printRoomList(house);
        } else {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
        }
    }

    private void printRoomList(House house) {
        System.out.println(controller.buildRoomsString(house));
    }

    // User Story 260 - As an Administrator, I want to import a list of sensors for the house rooms.
    // Sensors without a valid room shouldn’t be imported but registered in the application log.

    private void runUS260(HouseService houseService) {
        House house = houseService.getHouse();
        InputHelperUI inputs = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = inputs.getInputPathJsonOrXML(result);
        int importedSensors = controller.readSensors(filePath, house);
        System.out.println(importedSensors + " Sensors successfully imported.");
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printHouseConfigMenu() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Import Geographic Areas and Sensors from a JSON or XML file.");
        System.out.println("2) Import Geographic Area Sensor readings from a file - json, xml, csv. (US20v2)");
        System.out.println("3) As an Administrator, I want to configure the house from a file containing basic house " +
                "information, grids and rooms)(US100)");
        System.out.println("4) Configure the location of the house. (US101)");
        System.out.println("5) Add a new room to the house. (US105)");
        System.out.println("6) List the existing rooms. (US108)");
        System.out.println("7) Import House Sensors from a file. (US260)");
        System.out.println("0) (Return to main menu)\n");
    }
}


