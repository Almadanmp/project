package pt.ipp.isep.dei.project.io.ui.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderCSV;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderJSON;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderXML;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.repository.HouseCrudRepo;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
class HouseConfigurationUI {
    @Autowired
    private HouseConfigurationController controller;
    @Autowired
    private ReaderController readerController;
    @Autowired
    private GeographicAreaRepository geographicAreaRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HouseCrudRepo houseCrudRepo;

    private String roomName;
    private String roomDescription;
    private int roomHouseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private static final String INVALID_OPTION = "Please enter a valid option";
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private static final String FILE_LOCATION = "Please insert the location of the file you want to import:";
    private static final String IMPORT_TIME = "Import time: ";
    private static final String MILLISECONDS = " millisecond(s).";

    private final List<String> menuOptions = createMenu();

    private List<String> createMenu() {
        List<String> menuList = new ArrayList<>();
        menuList.add("As an Administrator, I want to configure the house from a file containing basic house" +
                " information, grids and rooms)(US100)");
        menuList.add("Configure the location of the house. (US101)");
        menuList.add("Add a new room to the house. (US105)");
        menuList.add("List the existing rooms. (US108)");
        menuList.add("Import House Sensors from a file. (US260)");
        menuList.add("Import House Sensor Reading List from a file. (US265)");
        menuList.add("(Return to main menu)");
        return menuList;
    }


    void run(House house) {
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
                    runUS100(house);
                    activeInput = false;
                    break;
                case 2:
                    runUS101(house);
                    activeInput = false;
                    break;
                case 3:
                    runUS105(house);
                    activeInput = false;
                    break;
                case 4:
                    runUS108();
                    activeInput = false;
                    break;
                case 5:
                    runUS260();
                    activeInput = false;
                    break;
                case 6:
                    runUS265();
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

    private void runUS100(House house) {
        System.out.println(FILE_LOCATION);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String input = InputHelperUI.getInputPathJson(result);
        String filePath = InputHelperUI.getInputPath(input);
        long startTime = System.currentTimeMillis();
        try {
            readerController.readJSONAndDefineHouse(house, filePath);
            System.out.println("House Data Successfully imported.");
            long stopTime = System.currentTimeMillis();
            System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
        } catch (IllegalArgumentException ill) {
            ill.getMessage();
            System.out.println("The JSON file is invalid.");
        }
    }

    /* USER STORY 101 - As an Administrator, I want to configure the location of the house - MARIA MEIRELES */
    private void runUS101(House house) {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("First select the geographic area where this house is located.");
        GeographicArea motherArea = InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);

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
        houseCrudRepo.save(house);

        System.out.println("\nYou have successfully configured the location of the house with the following\n Latitude: " + houseLat + ". \n" +
                "Longitude: " + houseLon + ". \n" + "Altitude: " + houseAlt + ". \n");
    }


    // USER STORY 105 - As an Administrator, I want to add a new room to the house, in order to configure it (name,
    // house floor and dimensions) - TERESA VARELA.
    private void runUS105(House house) {
        getInputRoomCharacteristics();
        Room room = createNewRoom(house);
        displayRoom();
        boolean added = addRoomToHouse(room);
        displayFinalState(added);
    }

    /**
     * Method gets input from user to saveSensor as the characteristics of a room.
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

    private Room createNewRoom(House house) {
        ArrayList<Double> roomDimensions = new ArrayList<>();
        roomDimensions.add(roomWidth);
        roomDimensions.add(roomLength);
        roomDimensions.add(roomHeight);
        return controller.createNewRoom(roomName, roomDescription, roomHouseFloor, roomDimensions, house.getId());
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

    private boolean addRoomToHouse(Room room) {
        return controller.addRoomToHouse(room);
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
    private void runUS108() {
        if (roomRepository.getAllRooms().isEmpty()) {
            System.out.println(UtilsUI.INVALID_ROOM_LIST);
        }
        List<Room> houseRooms = roomRepository.getAllRooms();
        System.out.println(controller.buildRoomsString(houseRooms));
    }

    // User Story 260 - As an Administrator, I want to import a list of sensors for the house rooms.
    // Sensors without a valid room shouldn’t be imported but registered in the application log.

    private void runUS260() {
        System.out.println(FILE_LOCATION);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = InputHelperUI.getInputPathJsonOrXML(result);
        long startTime = System.currentTimeMillis();
        try {
            int[] importedSensors = controller.readSensors(filePath);
            System.out.println(importedSensors[0] + " Sensors successfully imported and " + importedSensors[1] + " rejected." +
                    " Check the application log for more info.");
            long stopTime = System.currentTimeMillis();
            System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
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

    private void runUS265() {
        String filePath = InputHelperUI.getInputJsonXmlCsv();
        selectImportHouseReadingsMethod(filePath);
    }

    public String selectImportHouseReadingsMethod(String filePath) {
        if (filePath.endsWith(".csv")) {
            return importReadingsFromCSV(filePath);
        } else if (filePath.endsWith(".json")) {
            return importReadingsFromJSON(filePath);
        } else if (filePath.endsWith(".xml")) {
            return importReadingsFromXML(filePath);
        }
        return "ERROR: File type not valid";
    }


    private String importReadingsFromCSV(String filePath) {
        String output;
        int addedReadings;
        ReadingsReaderCSV readerCSV = new ReadingsReaderCSV();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            output = "The CSV file is invalid. Please fix before continuing.";
            System.out.println("The CSV file is invalid. Please fix before continuing.");
            return output;
        }
        long stopTime = System.currentTimeMillis();
        System.out.println(addedReadings + READINGS_IMPORTED);
        output = addedReadings + READINGS_IMPORTED + "\n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
        return output;
    }

    String importReadingsFromJSON(String filePath) {
        String output;
        int addedReadings;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            output = "The JSON file is invalid. Please fix before continuing.";
            System.out.println("The JSON file is invalid. Please fix before continuing.");
            return output;
        }
        System.out.println(addedReadings + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        output = addedReadings + READINGS_IMPORTED + "\n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
        return output;
    }

    private String importReadingsFromXML(String filePath) {
        int addedReadings;
        String output;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            addedReadings = addReadingsToHouseSensors(list);
        } catch (IllegalArgumentException illegal) {
            output = "The XML file is invalid. Please fix before continuing.";
            System.out.println("The XML file is invalid. Please fix before continuing.");
            return output;
        }
        System.out.println(addedReadings + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        output = addedReadings + READINGS_IMPORTED + "\n" + IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
        return output;


    }

    private int addReadingsToHouseSensors(List<ReadingDTO> readings) {
        return readerController.addReadingsToRoomSensors(readings, "resources/logs/houseReadingsHtml.html", roomRepository);
    }
}


