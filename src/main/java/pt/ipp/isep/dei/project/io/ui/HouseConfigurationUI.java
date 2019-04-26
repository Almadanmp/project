package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridService;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.reader.ReadingsReaderCSV;
import pt.ipp.isep.dei.project.reader.ReadingsReaderJSON;
import pt.ipp.isep.dei.project.reader.ReadingsReaderXML;
import pt.ipp.isep.dei.project.repository.HouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HouseConfigurationUI {
    private final HouseConfigurationController controller;
    private String roomName;
    private String roomDescription;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private static final String FILE_LOCATION = "Please insert the location of the file you want to import:";
    private final ReaderController readerController;
    private final List<String> menuOptions;


    HouseConfigurationUI() {
        this.controller = new HouseConfigurationController();
        this.readerController = new ReaderController();
        menuOptions = new ArrayList<>();
        menuOptions.add("As an Administrator, I want to configure the house from a file containing basic house" +
                "information, grids and rooms)(US100)");
        menuOptions.add("Configure the location of the house. (US101)");
        menuOptions.add("Add a new room to the house. (US105)");
        menuOptions.add("List the existing rooms. (US108)");
        menuOptions.add("Import House Sensors from a file. (US260)");
        menuOptions.add("Import House Sensor Reading List from a file. (US265)");
        menuOptions.add("(Return to main menu)");
    }

    void run(House house, GeographicAreaService geographicAreaService, RoomService roomService, EnergyGridService energyGridService, HouseRepository houseRepository) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("House Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("House Configuration", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS100(house, energyGridService, houseRepository, roomService);
                    activeInput = false;
                    break;
                case 2:
                    runUS101(house, geographicAreaService, houseRepository);
                    activeInput = false;
                    break;
                case 3:
                    runUS105(house, roomService, energyGridService);
                    activeInput = false;
                    break;
                case 4:
                    runUS108(roomService);
                    activeInput = false;
                    break;
                case 5:
                    runUS260(roomService);
                    activeInput = false;
                    break;
                case 6:
                    runUS265(roomService);
                    activeInput = false;
                    break;
                case 7:
                    runUS265(roomService);
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


    /*As an Administrator, I want to configure the house from a file containing basic house information, grids and rooms.*/

    private void runUS100(House house, EnergyGridService energyGridService, HouseRepository houseRepository, RoomService roomService) {
        ReaderController ctrl = new ReaderController();
        InputHelperUI inputHelperUI = new InputHelperUI();
        System.out.println(FILE_LOCATION);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String input = inputHelperUI.getInputPathJson(result);
        String filePath = inputHelperUI.getInputPath(input);
        try {
            if (ctrl.readJSONAndDefineHouse(house, filePath, energyGridService, houseRepository, roomService)) {
                System.out.println("House Data Successfully imported.");
            } else {
                System.out.println("The JSON file is invalid.");
            }
        } catch (IllegalArgumentException ill) {
            ill.getMessage();
        }
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
    private void runUS101(House house, GeographicAreaService geographicAreaService, HouseRepository houseRepository) {
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
        houseRepository.save(house);

        System.out.println("\nYou have successfully configured the location of the house with the following\n Latitude: " + houseLat + ". \n" +
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
     * Method gets input from user to addRoom as the characteristics of a room.
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
        this.roomWidth = InputHelperUI.getInputAsDoubleZeroOrPositive();

        System.out.println("Please insert your room's length in meters: ");
        this.roomLength = InputHelperUI.getInputAsDoubleZeroOrPositive();

        System.out.println("Please insert your room's height in meters: ");
        this.roomHeight = InputHelperUI.getInputAsDoubleZeroOrPositive();
    }

    private Room createNewRoom(RoomService roomService, House house, EnergyGrid grid) {
        return controller.createNewRoom(roomService, roomName, roomDescription, roomHouseFloor, roomWidth, roomLength, roomHeight, house.getId(), grid.getName());
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
        List<Room> houseRooms = roomService.getAllRooms();
        System.out.println(controller.buildRoomsString(roomService, houseRooms));
    }

    // User Story 260 - As an Administrator, I want to import a list of sensors for the house rooms.
    // Sensors without a valid room shouldn’t be imported but registered in the application log.

    private void runUS260(RoomService roomService) {
        InputHelperUI inputs = new InputHelperUI();
        System.out.println(FILE_LOCATION);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = inputs.getInputPathJsonOrXML(result);
        try {
            int[] importedSensors = controller.readSensors(filePath, roomService);
            System.out.println(importedSensors[0] + " Sensors successfully imported and " + importedSensors[1] + " rejected." +
                    " Check the application log for more info.");
        } catch (IllegalArgumentException ok) {
            System.out.println("There's something wrong with the file provided. Please check it for syntax errors or " +
                    "empty elements.");
        }
    }

    /*
        US265 As an Administrator, I want to import a list of sensor readings of the house sensors.
        Data from non-existing sensors or outside the valid sensor operation period shouldn’t be imported but
        registered in the application log.
     */

    private void runUS265(RoomService roomService) {
        InputHelperUI inputHelperUI = new InputHelperUI();
        String filePath = inputHelperUI.getInputJsonXmlCsv();
        if (filePath.endsWith(".csv")) {
            importReadingsFromCSV(filePath, roomService);
        } else if (filePath.endsWith(".json")) {
            importReadingsFromJSON(filePath, roomService);
        } else if (filePath.endsWith(".xml")) {
            importReadingsFromXML(filePath, roomService);
        }
    }

    private void importReadingsFromCSV(String filePath, RoomService roomService) {
        int addedReadings = 0;
        ReadingsReaderCSV readerCSV = new ReadingsReaderCSV();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list, roomService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid. Please fix before continuing.");
        }
        System.out.println(addedReadings + READINGS_IMPORTED);
    }

    private void importReadingsFromJSON(String filePath, RoomService roomService) {
        int addedReadings = 0;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list, roomService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid. Please fix before continuing.");
        }
        System.out.println(addedReadings + READINGS_IMPORTED);
    }

    private void importReadingsFromXML(String filePath, RoomService roomService) {
        int addedReadings = 0;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list, roomService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid. Please fix before continuing.");
        }
        System.out.println(addedReadings + READINGS_IMPORTED);
    }

    private int addReadingsToHouseSensors(List<ReadingDTO> readings, RoomService roomService) {
        return readerController.addReadingsToRoomSensors(readings, "resources/logs/houseReadingsHtml.html", roomService);
    }
}


