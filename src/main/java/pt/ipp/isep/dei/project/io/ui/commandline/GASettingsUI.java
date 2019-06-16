package pt.ipp.isep.dei.project.io.ui.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.GASettingsController;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderCSV;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderJSON;
import pt.ipp.isep.dei.project.io.ui.reader.ReadingsReaderXML;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
class GASettingsUI {
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private static final String IMPORT_TIME = "Import time: ";
    private static final String MILLISECONDS = " millisecond(s).";
    private final List<String> menuOptions = createMenu();
    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;
    @Autowired
    InputHelperUI inputHelperUI;
    @Autowired
    private GASettingsController gaController;
    @Autowired
    private ReaderController readerController;

    private List<String> createMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Create a new type of Geographical Area. (US001)");
        menu.add("List the existing types of Geographical Areas. (US002)");
        menu.add("Add a new geographical area. (US003)");
        menu.add("List of existing geographical areas of a given type. (US004)");
        menu.add("Add an existing geographical area to another one. (US007)");
        menu.add("See if a geographical area is included, directly or indirectly, in another one. (US008)");
        menu.add("Activate or deactivate a sensor (US010)");
        menu.add("Remove a sensor from the Geographical Area. (US011)");
        menu.add("Import Geographic Areas and Sensors from a JSON or XML file.(US15v3)");
        menu.add("Import Geographic Area Sensor Readings from a file - json, xml, csv. (US20v3)");
        menu.add("(Return to main menu)");
        return menu;
    }

    void runGASettings() {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\nGeographic Area Settings\n--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("Geographic Area Settings", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS01();
                    activeInput = false;
                    break;
                case 2:
                    runUS02();
                    activeInput = false;
                    break;
                case 3:
                    runUS03();
                    activeInput = false;
                    break;
                case 4:
                    runUS04();
                    activeInput = false;
                    break;
                case 5:
                    runUS07();
                    activeInput = false;
                    break;
                case 6:
                    runUS08();
                    activeInput = false;
                    break;
                case 7:
                    runUS10();
                    activeInput = false;
                    break;
                case 8:
                    runUS11();
                    activeInput = false;
                    break;
                case 9:
                    runUS15();
                    activeInput = false;
                    break;
                case 10:
                    runUS20v3();
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(UtilsUI.INVALID_OPTION);
                    break;
            }
        }
    }


    // SHARED METHODS //

    /**
     * Method to get a AreaType DTO By List
     *
     * @return Type area DTO
     * used on US 03 and 04
     */
    private AreaTypeDTO getInputTypeAreaDTOByList() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select the Geographic Area Type from the list: ");
            System.out.print(gaController.buildGATypeListString());
            String aux = scanner.nextLine();

            AreaType areaType = areaTypeRepository.getById(aux);
            if (areaType != null) {
                AreaTypeDTO typeAreaDTO = AreaTypeMapper.objectToDTO(areaType);
                System.out.println("You have chosen the following Geographic Area Type:");
                System.out.println("AreaType: " + gaController.getTypeAreaName(typeAreaDTO));
                return typeAreaDTO;
            }

            System.out.println(UtilsUI.INVALID_OPTION);
        }
    }

    /* USER STORY 001 - As an Administrator, I want to add a new type of geographical area, in order to be able to create a
     classification of geographical areas.*/
    private void runUS01() {
        String typeAreaName = getInputUS01();
        boolean created = updateModelUS01(typeAreaName);
        displayStateUS01(created, typeAreaName);
    }

    private String getInputUS01() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert a new Type of Geographic Area");

        while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println("Please insert a valid name.");
            scanner.next();
        }
        return scanner.nextLine();
    }

    private boolean updateModelUS01(String typeAreaName) {
        return gaController.createAndAddTypeAreaToList(typeAreaName);
    }

    private void displayStateUS01(boolean created, String typeAreaName) {
        if (created) {
            System.out.println("Success, you have inserted a new Type of Geographic Area: " + typeAreaName);
        } else {
            System.out.println("Failed, you have inserted an invalid or repeated Type of Geographic Area.");
        }

    }

    /* USER STORY 002 - As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
     * Class responsible for presenting the list. - NUNO AZEVEDO */
    private void runUS02() {
        if (!areaTypeRepository.isEmpty()) {
            System.out.println(gaController.getTypeAreaList());
            System.out.println("\nList finished.");
        } else {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
        }
    }

    /* User Story - 03 As a System Administrator I want to create a new Geographic Area */
    private void runUS03() {
        if (areaTypeRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        getAreaInputUS03();
    }

    private void getAreaInputUS03() {
        Scanner scanner = new Scanner(System.in);
        AreaTypeDTO geoTypeAreaDTO = getInputTypeAreaDTOByList();
        String gaTypeAreaName = gaController.getTypeAreaName(geoTypeAreaDTO);
        String nameOfGeoArea = readInputString("Name");
        double geoAreaLat = readInputNumber("Latitude");
        double geoAreaLong = readInputNumber("Longitude");
        double geoAreaAlt = readInputNumber("Altitude");
        double geoAreaLength = readInputPositiveNumber("Length");
        double geoAreaWidth = readInputPositiveNumber("Width");
        String geoAreDescription = null;
        LocalDTO localDTO = gaController.createLocalDTO(geoAreaLat, geoAreaLong, geoAreaAlt);
        GeographicAreaDTO geoAreaDTO = gaController.createGeoAreaDTO(nameOfGeoArea, geoTypeAreaDTO, localDTO, geoAreaLength, geoAreaWidth);
        if (InputHelperUI.yesOrNo("Would you like to add a description to the new geographic area? (y/n)")) {
            System.out.println("Please insert the geographic area description:");
            geoAreDescription = scanner.nextLine();
            geoAreaDTO.setDescription(geoAreDescription);
        }

        System.out.print("The Geographic Area you want to create is " + nameOfGeoArea + " from the type " + gaTypeAreaName +
                " and its " + "localization is on " + geoAreaLat + " latitude " + geoAreaLong + " longitude. The geographic area size" +
                " is " + geoAreaLength + " by " + geoAreaWidth + " kms\n");
        if (geoAreDescription != null) {
            System.out.println("And has the following description: " + geoAreDescription);
            gaController.addNewGeoAreaToList(geoAreaDTO);

        }
    }

    private String createInputMsg(String inputType) {
        return "Please Insert " + inputType + " for the New Geographic Area: ";
    }

    private String createInvalidStringMsg(String inputType) {
        return "That's not a valid " + inputType + ". Please insert only Alphabetic Characters";
    }

    private String readInputString(String inputType) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(createInputMsg(inputType));

        while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
            System.out.println(createInvalidStringMsg(inputType));
            scanner.next();
        }
        return scanner.next();
    }

    private double readInputNumber(String inputType) {
        System.out.print(createInputMsg(inputType));
        return InputHelperUI.getInputAsDouble();
    }

    private double readInputPositiveNumber(String inputType) {
        System.out.print(createInputMsg(inputType));
        return InputHelperUI.getInputAsDoubleZeroOrPositive();
    }

    /* USER STORY 04 -  As an Administrator, I want to getDB a list of existing geographical areas of a given type. */
    private void runUS04() {
        if (areaTypeRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        AreaTypeDTO typeAreaDTO = getInputTypeAreaDTOByList();
        List<GeographicArea> gaFinalList = matchGAByTypeArea(typeAreaDTO);
        displayGAListByTypeArea(gaFinalList, typeAreaDTO);
    }

    private List<GeographicArea> matchGAByTypeArea(AreaTypeDTO typeArea) {
        return gaController.matchGAByTypeArea(typeArea);
    }

    private void displayGAListByTypeArea(List<GeographicArea> gaFinalList, AreaTypeDTO typeArea) {
        String taName = gaController.getTypeAreaName(typeArea);
        System.out.println("Geographic Areas of the type " + taName + ":\n");
        System.out.println(gaController.buildGAListString(gaFinalList));
    }

    /* USER STORY 07 -  Add an existing geographical area to another one. */
    private void runUS07() {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getInputMotherGeographicArea();
        GeographicArea daughterGA = getInputDaughterGeographicArea();
        updateStateUS07(motherGA, daughterGA);
        displayStateUS07(motherGA, daughterGA);
    }

    private GeographicArea getInputMotherGeographicArea() {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("First you need to select the geographic area you wish to set as container.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    private GeographicArea getInputDaughterGeographicArea() {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("Second you need to select the geographic area you wish to set as contained.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    private void updateStateUS07(GeographicArea motherGA, GeographicArea daughterGA) {
        gaController.addDaughterArea(motherGA, daughterGA);
    }

    private void displayStateUS07(GeographicArea motherGA, GeographicArea daughterGA) {
        String motherGAName = gaController.getGeographicAreaId(motherGA);
        String daughterGAName = gaController.getGeographicAreaId(daughterGA);
        System.out.print("The Geographic Area " + daughterGAName + " is contained in " + motherGAName + ".");
    }


    /* US08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */
    private void runUS08() {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getMotherArea();
        GeographicArea daughterGA = getDaughterArea();
        checkIfContained(motherGA, daughterGA);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private GeographicArea getMotherArea() {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("First you need to select the geographic area you wish to test if contains another geographic area.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private GeographicArea getDaughterArea() {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("Second you need to select the geographic area you wish to test if is contained in the first one.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    /**
     * This method receives two geographic areas and checks if the first geographic area
     * contains the second geographic area, returning a message to the user accordingly.
     */
    private void checkIfContained(GeographicArea motherGA, GeographicArea daughterGA) {
        if (!(gaController.isAreaContained(motherGA, daughterGA))) {
            System.out.println(gaController.getGeographicAreaId(daughterGA) + " is NOT contained in " + gaController.getGeographicAreaId(motherGA));
        } else {
            System.out.println(gaController.getGeographicAreaId(daughterGA) + " is contained in " + gaController.getGeographicAreaId(motherGA));
        }
    }

    /**
     * This method deactivates a sensor selected from a list of sensor of an selected geographic area
     */
    private void runUS10() {

        GeographicAreaDTO geographicAreaDTO = gaController.getInputArea();
        AreaSensorDTO areaSensorDTO = gaController.getInputSensor(geographicAreaDTO);
        if (!gaController.deactivateSensor(areaSensorDTO)) {
            System.out.println("Sensor already deactivated.");
        } else {
            System.out.println("Sensor successfully deactivated!");
            geographicAreaRepository.updateGeoArea(GeographicAreaMapper.dtoToObject(geographicAreaDTO));
        }

    }


    /* USER STORY US011 - As an Administrator, I want to removeSensor a sensor from a geographical area, so that it will no
    longer be used.*/

    /**
     * This method removes a sensor selected from a list of sensors of a previously selected geographic area
     */
    private void runUS11() {
        GeographicAreaDTO geographicAreaDTO = gaController.getInputArea();
        AreaSensorDTO areaSensorDTO = gaController.getInputSensor(geographicAreaDTO);
        if (areaSensorDTO != null) {
            updateUS11(areaSensorDTO, geographicAreaDTO);
        }
    }

    private void updateUS11(AreaSensorDTO areaSensorDTO, GeographicAreaDTO geographicAreaDTO) {
        gaController.removeSensor(areaSensorDTO, geographicAreaDTO);
        System.out.println("The sensor " + areaSensorDTO.getName() + " on the Geographical Area " +
                geographicAreaDTO.getName() + " has been deleted.");
    }

    /* USER STORY 20v3 - As an Administrator I want to import geographic area sensor readings into the application
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
    private void runUS20v3() {
        String filePath = InputHelperUI.getInputJsonXmlCsv();
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
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            result = addReadingsToAreaSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    void importReadingsFromJSON(String filePath) {
        int result = 0;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            result = addReadingsToAreaSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    private void importReadingsFromXML(String filePath) {
        int result = 0;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            result = addReadingsToAreaSensors(list);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    private int addReadingsToAreaSensors(List<ReadingDTO> readings) {
        return readerController.addReadingsToGeographicAreaSensors(readings, geographicAreaRepository);
    }


    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON or XML file.
     * <p>
     * list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15() {
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = InputHelperUI.getInputPathJsonOrXML(result);
        long startTime = System.currentTimeMillis();
        int areas = inputHelperUI.acceptPathJSONorXMLAndReadFile(filePath, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
        if (areas > 0) {
            System.out.println(areas + " Geographic Areas have been successfully imported.");
            long stopTime = System.currentTimeMillis();
            System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
        } else {
            System.out.println("No Geographic Areas were imported. Please refer to the Area Type Log or the Area Sensor " +
                    "Log for more information");
        }
    }

}
