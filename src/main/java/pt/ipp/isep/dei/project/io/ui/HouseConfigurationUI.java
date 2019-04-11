package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorService;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;
import pt.ipp.isep.dei.project.repository.ReadingRepository;
import pt.ipp.isep.dei.project.model.sensor.ReadingService;
import pt.ipp.isep.dei.project.repository.RoomRepository;

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
    private AreaSensorService areaSensorService;
    private ReadingService readingService;

    HouseConfigurationUI(AreaSensorService areaSensorService, ReadingService readingService) {
        this.controller = new HouseConfigurationController();
        this.areaSensorService = areaSensorService;
        this.readingService = readingService;

    }

    void run(House house, HouseService houseService, GeographicAreaService geographicAreaService, HouseSensorService sensorService, RoomService roomService, RoomRepository roomRepository, HouseSensorRepository houseSensorRepository, ReadingRepository readingRepository) {
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
                    runUS100(house, houseService);
                    activeInput = false;
                    break;
                case 3:
                    runUS101(houseService, geographicAreaService);
                    activeInput = false;
                    break;
                case 4:
                    runUS105(houseService);
                    activeInput = false;
                    break;
                case 5:
                    runUS108(roomService, roomRepository);
                    activeInput = false;
                    break;
                case 6:
                    runUS260(sensorService, roomService);
                    activeInput = false;
                    break;
                case 7:
                    runUS265(houseSensorRepository, readingRepository);
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

    /*As an Administrator, I want to configure the house from a file containing basic house information, grids and rooms.*/

    private void runUS100(House house, HouseService houseService) {
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService);
        InputHelperUI input = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPathJson(result);
        if (ctrl.readJSONAndDefineHouse(house, filePath)) {
            System.out.println("House Data Successfully imported.");
        } else {
            System.out.println("The JSON file is invalid.");
        }
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
//TODO Location is just location ot Adress etc, doest make much sense with the new data.
    private void runUS101(HouseService houseService, GeographicAreaService geographicAreaService) {
        House house = houseService.getHouse();
        Scanner scanner = new Scanner(System.in);
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("First select the geographic area where this house is located.");
        GeographicArea motherArea = InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);

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
        EnergyGrid grid = InputHelperUI.getInputGridByList(house);
        Room room = createNewRoom(house,grid);
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

    private Room createNewRoom(House house,EnergyGrid grid) {
        return controller.createNewRoom(house, roomDescription, roomName, roomHouseFloor, roomWidth, roomLength, roomHeight,house.getId(),grid.getName());
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
    private void runUS108(RoomService roomService, RoomRepository roomRepository) {
        //        if (){
//            System.out.println(UtilsUI.INVALID_ROOM_LIST);
//        }
        System.out.println(controller.buildRoomsString(roomService, roomRepository));
    }

    // User Story 260 - As an Administrator, I want to import a list of sensors for the house rooms.
    // Sensors without a valid room shouldn’t be imported but registered in the application log.

    private void runUS260(HouseSensorService sensorService, RoomService roomService) {
        InputHelperUI inputs = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = inputs.getInputPathJsonOrXML(result);
        int importedSensors = controller.readSensors(filePath, roomService, sensorService);
        System.out.println(importedSensors + " Sensors successfully imported.");
    }

    /*
        US265 As an Administrator, I want to import a list of sensor readings of the house sensors.
        Data from non-existing sensors or outside the valid sensor operation period shouldn’t be imported but
        registered in the application log.
     */

    private void runUS265(HouseSensorRepository houseSensorRepository, ReadingRepository readingRepository) {
        String logPath = VALID_LOG_PATH;
        InputHelperUI inputHUI = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = inputHUI.getInputPathJsonOrXML(result);
        int importedReadings = controller.readReadingListFromFile(readingService, filePath, logPath, houseSensorRepository, readingRepository);
        System.out.println(importedReadings + " Readings successfully imported.");
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printHouseConfigMenu() {
        System.out.println("House Controller Options:\n");
        System.out.println("1) Import Geographic Areas and Sensors from a JSON or XML file.");
        System.out.println("2) As an Administrator, I want to configure the house from a file containing basic house " +
                "information, grids and rooms)(US100)");
        System.out.println("3) Configure the location of the house. (US101)");
        System.out.println("4) Add a new room to the house. (US105)");
        System.out.println("5) List the existing rooms. (US108)");
        System.out.println("6) Import House Sensors from a file. (US260)");
        System.out.println("7) Import House Sensor Reading List from a file. (US265)");
        System.out.println("0) (Return to main menu)\n");
    }
}


