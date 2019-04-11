package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.HouseSensorService;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;
import pt.ipp.isep.dei.project.repository.ReadingRepository;
import pt.ipp.isep.dei.project.model.sensor.ReadingService;
import pt.ipp.isep.dei.project.reader.ReadingsReaderCSV;
import pt.ipp.isep.dei.project.reader.ReadingsReaderJSON;
import pt.ipp.isep.dei.project.reader.ReadingsReaderXML;
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
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private AreaSensorService areaSensorService;
    private ReadingService readingService;
    private HouseService houseService;
    private ReaderController readerController;
    private HouseSensorService houseSensorService;


    HouseConfigurationUI(AreaSensorService areaSensorService, ReadingService readingService, HouseService houseService, HouseSensorService houseSensorService) {
        this.controller = new HouseConfigurationController();
        this.areaSensorService = areaSensorService;
        this.readingService = readingService;
        this.houseService = houseService;
        this.readerController = new ReaderController(areaSensorService, readingService, houseService, houseSensorService);
        this.houseSensorService = houseSensorService;
    }

    void run(House house, GeographicAreaService geographicAreaService, HouseSensorService sensorService, RoomService roomService, RoomRepository roomRepository, HouseSensorRepository houseSensorRepository, ReadingRepository readingRepository, EnergyGridService energyGridService) {
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
                    runUS101(house, geographicAreaService);
                    activeInput = false;
                    break;
                case 4:
                    runUS105(house, roomService, energyGridService);
                    activeInput = false;
                    break;
                case 5:
                    runUS108(roomService);
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
                case 8:
                    runUS265v2(houseService);
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
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService, houseSensorService);
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
        ReaderController ctrl = new ReaderController(areaSensorService, readingService, houseService, houseSensorService);
        InputHelperUI inputHelperUI = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String input = inputHelperUI.getInputPathJson(result);
        String filePath = inputHelperUI.getInputPath(input);
        try {
            if (ctrl.readJSONAndDefineHouse(house, filePath)) {
                System.out.println("House Data Successfully imported.");
            } else {
                System.out.println("The JSON file is invalid.");
            }
        }
        catch (IllegalArgumentException ill) {
            ill.getMessage();
        }
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
//TODO Location is just location ot Adress etc, doest make much sense with the new data.
    private void runUS101(House house, GeographicAreaService geographicAreaService) {
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("First select the geographic area where this house is located.");
        GeographicArea motherArea = InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);

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
        controller.setHouseMotherArea(house, motherArea);
        houseService.saveHouse(house);

        System.out.println("\nYou have successfully configured the location of the house with the following Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house, RoomService roomService, EnergyGridService energyGridService) {
        getInputRoomCharacteristics();
        EnergyGrid grid = InputHelperUI.getInputGridByList(energyGridService);
        Room room = createNewRoom(roomService, house, grid);
        displayRoom();
        boolean added = addRoomToHouse(roomService, room);
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

    private Room createNewRoom(RoomService roomService, House house, EnergyGrid grid) {
        return controller.createNewRoom(roomService, roomDescription, roomName, roomHouseFloor, roomWidth, roomLength, roomHeight, house.getId(), grid.getName());
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

    private boolean addRoomToHouse(RoomService roomService, Room room) {
        return controller.addRoomToHouse(roomService, room);
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
    private void runUS108(RoomService roomService) {
        if (roomService.getAllRooms().isEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
        }
        System.out.println(controller.buildRoomsString(roomService));
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
        if(importedSensors==-1){
            System.out.println("There's something wrong with the file provided. Please check it for syntax errors or " +
                    "empty elements.");
        }
        System.out.println(importedSensors + " Sensors successfully imported.");
    }

    /*
        US265 As an Administrator, I want to import a list of sensor readings of the house sensors.
        Data from non-existing sensors or outside the valid sensor operation period shouldn’t be imported but
        registered in the application log.
     */

    private void runUS265(HouseSensorRepository houseSensorRepository, ReadingRepository readingRepository) {
        InputHelperUI inputHUI = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = inputHUI.getInputPathJsonOrXML(result);
        int importedReadings = controller.readReadingListFromFile(readingService, filePath, VALID_LOG_PATH, houseSensorRepository, readingRepository);
        System.out.println(importedReadings + " Readings successfully imported.");
    }

    /*
        US265 As an Administrator, I want to import a list of sensor readings of the house sensors.
        Data from non-existing sensors or outside the valid sensor operation period shouldn’t be imported but
        registered in the application log.
     */

    private void runUS265v2(HouseService houseService) {
        InputHelperUI inputHelperUI = new InputHelperUI();
        String filePath = inputHelperUI.getInputJsonXmlCsv();
        if (filePath.endsWith(".csv")) {
            importReadingsFromCSV(filePath);
        } else if (filePath.endsWith(".json")) {
            importReadingsFromJSON(filePath);
        } else if (filePath.endsWith(".xml")) {
            importReadingsFromXML(filePath);
        }
    }

    private void importReadingsFromCSV(String filePath) {
        int result = 0;
        ReadingsReaderCSV readerCSV = new ReadingsReaderCSV();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            result = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void importReadingsFromJSON(String filePath) {
        int result = 0;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            result = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void importReadingsFromXML(String filePath) {
        int result = 0;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            result = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private int addReadingsToHouseSensors(List<ReadingDTO> readings) {
        return readerController.addReadingsToHouseSensors(readings, VALID_LOG_PATH);
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
        System.out.println("8) Import House Sensor Reading List from a file. (US265v2)");
        System.out.println("0) (Return to main menu)\n");
    }
}


