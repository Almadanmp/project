package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllercli.GASettingsController;
import pt.ipp.isep.dei.project.controllercli.ReaderController;
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

class GASettingsUI {
    private final GASettingsController gaController;
    private final ReaderController readerController;
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private static final String IMPORT_TIME = "Import time: ";
    private static final String MILLISECONDS = " millisecond(s).";

    private final List<String> menuOptions;

    GASettingsUI() {
        this.gaController = new GASettingsController();
        this.readerController = new ReaderController();
        menuOptions = new ArrayList<>();
        menuOptions.add("Create a new type of Geographical Area. (US001)");
        menuOptions.add("List the existing types of Geographical Areas. (US002)");
        menuOptions.add("Add a new geographical area. (US003)");
        menuOptions.add("List of existing geographical areas of a given type. (US004)");
        menuOptions.add("Add an existing geographical area to another one. (US007)");
        menuOptions.add("See if a geographical area is included, directly or indirectly, in another one. (US008)");
        menuOptions.add("Activate or deactivate a sensor (US010)");
        menuOptions.add("Remove a sensor from the Geographical Area. (US011)");
        menuOptions.add("Import Geographic Areas and Sensors from a JSON or XML file.(US15v3)");
        menuOptions.add("Import Geographic Area Sensor Readings from a file - json, xml, csv. (US20v3)");
        menuOptions.add("(Return to main menu)");
    }

    void runGASettings(AreaTypeRepository areaTypeRepository, GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeRepository) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\nGeographic Area Settings\n--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("Geographic Area Settings", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS01(areaTypeRepository);
                    activeInput = false;
                    break;
                case 2:
                    runUS02(areaTypeRepository);
                    activeInput = false;
                    break;
                case 3:
                    runUS03(areaTypeRepository, geographicAreaRepository);
                    activeInput = false;
                    break;
                case 4:
                    runUS04(areaTypeRepository, geographicAreaRepository);
                    activeInput = false;
                    break;
                case 5:
                    runUS07(geographicAreaRepository);
                    activeInput = false;
                    break;
                case 6:
                    runUS08(geographicAreaRepository);
                    activeInput = false;
                    break;
                case 7:
                    runUS10(geographicAreaRepository);
                    activeInput = false;
                    break;
                case 8:
                    runUS11(geographicAreaRepository);
                    activeInput = false;
                    break;
                case 9:
                    runUS15(geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
                    activeInput = false;
                    break;
                case 10:
                    runUS20v3(geographicAreaRepository);
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
    private AreaTypeDTO getInputTypeAreaDTOByList(AreaTypeRepository areaTypeRepository) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select the Geographic Area Type from the list: ");
            System.out.print(gaController.buildGATypeListString(areaTypeRepository));
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
    private void runUS01(AreaTypeRepository areaTypeRepository) {
        String typeAreaName = getInputUS01();
        boolean created = updateModelUS01(areaTypeRepository, typeAreaName);
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

    private boolean updateModelUS01(AreaTypeRepository areaTypeRepository, String typeAreaName) {
        return gaController.createAndAddTypeAreaToList(areaTypeRepository, typeAreaName);
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
    private void runUS02(AreaTypeRepository areaTypeRepository) {
        if (!areaTypeRepository.isEmpty()) {
            System.out.println(gaController.getTypeAreaList(areaTypeRepository));
            System.out.println("\nList finished.");
        } else {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
        }
    }

    /* User Story - 03 As a System Administrator I want to create a new Geographic Area */
    private void runUS03(AreaTypeRepository areaTypeRepository, GeographicAreaRepository geographicAreaRepository) {
        if (areaTypeRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        getAreaInputUS03(areaTypeRepository, geographicAreaRepository);
    }

    private void getAreaInputUS03(AreaTypeRepository areaTypeRepository, GeographicAreaRepository geographicAreaRepository) {
        Scanner scanner = new Scanner(System.in);
        AreaTypeDTO geoTypeAreaDTO = getInputTypeAreaDTOByList(areaTypeRepository);
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
            gaController.addNewGeoAreaToList(geographicAreaRepository, geoAreaDTO);

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
    private void runUS04(AreaTypeRepository areaTypeRepository, GeographicAreaRepository geographicAreaRepository) {
        if (areaTypeRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        AreaTypeDTO typeAreaDTO = getInputTypeAreaDTOByList(areaTypeRepository);
        List<GeographicArea> gaFinalList = matchGAByTypeArea(geographicAreaRepository, typeAreaDTO);
        displayGAListByTypeArea(geographicAreaRepository, gaFinalList, typeAreaDTO);
    }

    private List<GeographicArea> matchGAByTypeArea(GeographicAreaRepository geographicAreaRepository, AreaTypeDTO typeArea) {
        return gaController.matchGAByTypeArea(geographicAreaRepository, typeArea);
    }

    private void displayGAListByTypeArea(GeographicAreaRepository geoAreaService, List<GeographicArea> gaFinalList, AreaTypeDTO typeArea) {
        String taName = gaController.getTypeAreaName(typeArea);
        System.out.println("Geographic Areas of the type " + taName + ":\n");
        System.out.println(gaController.buildGAListString(geoAreaService, gaFinalList));
    }

    /* USER STORY 07 -  Add an existing geographical area to another one. */
    private void runUS07(GeographicAreaRepository geographicAreaRepository) {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getInputMotherGeographicArea(geographicAreaRepository);
        GeographicArea daughterGA = getInputDaughterGeographicArea(geographicAreaRepository);
        updateStateUS07(motherGA, daughterGA);
        displayStateUS07(motherGA, daughterGA);
    }

    private GeographicArea getInputMotherGeographicArea(GeographicAreaRepository geographicAreaRepository) {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("First you need to select the geographic area you wish to set as container.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    private GeographicArea getInputDaughterGeographicArea(GeographicAreaRepository geographicAreaRepository) {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("Second you need to select the geographic area you wish to set as contained.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    private void updateStateUS07(GeographicArea motherGA, GeographicArea daughterGA) {
        gaController.setMotherArea(daughterGA, motherGA);
    }

    private void displayStateUS07(GeographicArea motherGA, GeographicArea daughterGA) {
        String motherGAName = gaController.getGeographicAreaId(motherGA);
        String daughterGAName = gaController.getGeographicAreaId(daughterGA);
        System.out.print("The Geographic Area " + daughterGAName + " is contained in " + motherGAName + ".");
    }


    /* US08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */
    private void runUS08(GeographicAreaRepository geographicAreaRepository) {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getMotherArea(geographicAreaRepository);
        GeographicArea daughterGA = getDaughterArea(geographicAreaRepository);
        checkIfContained(motherGA, daughterGA);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private GeographicArea getMotherArea(GeographicAreaRepository geographicAreaRepository) {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        System.out.println("First you need to select the geographic area you wish to test if contains another geographic area.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private GeographicArea getDaughterArea(GeographicAreaRepository geographicAreaRepository) {
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
    private void runUS10(GeographicAreaRepository geographicAreaRepository) {

        GeographicAreaDTO geographicAreaDTO = gaController.getInputArea(geographicAreaRepository);
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
    private void runUS11(GeographicAreaRepository geographicAreaRepository) {
        GeographicAreaDTO geographicAreaDTO = gaController.getInputArea(geographicAreaRepository);
        AreaSensorDTO areaSensorDTO = gaController.getInputSensor(geographicAreaDTO);
        if (areaSensorDTO != null) {
            updateUS11(areaSensorDTO, geographicAreaRepository, geographicAreaDTO);
        }
    }

    private void updateUS11(AreaSensorDTO areaSensorDTO, GeographicAreaRepository geographicAreaRepository, GeographicAreaDTO geographicAreaDTO) {
        gaController.removeSensor(areaSensorDTO, geographicAreaDTO, geographicAreaRepository);
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
    private void runUS20v3(GeographicAreaRepository geographicAreaRepository) {
        InputHelperUI inputHelperUI = new InputHelperUI();
        String filePath = inputHelperUI.getInputJsonXmlCsv();
        if (filePath.endsWith(".csv")) {
            importReadingsFromCSV(filePath, geographicAreaRepository);
        } else if (filePath.endsWith(".json")) {
            importReadingsFromJSON(filePath, geographicAreaRepository);
        } else if (filePath.endsWith(".xml")) {
            importReadingsFromXML(filePath, geographicAreaRepository);
        }
    }

    private void importReadingsFromCSV(String filePath, GeographicAreaRepository geographicAreaRepository) {
        int result = 0;
        ReadingsReaderCSV readerCSV = new ReadingsReaderCSV();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaRepository);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    private void importReadingsFromJSON(String filePath, GeographicAreaRepository geographicAreaRepository) {
        int result = 0;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaRepository);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    private void importReadingsFromXML(String filePath, GeographicAreaRepository geographicAreaRepository) {
        int result = 0;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        long startTime = System.currentTimeMillis();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaRepository);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
        long stopTime = System.currentTimeMillis();
        System.out.println(IMPORT_TIME + (stopTime - startTime) + MILLISECONDS);
    }

    private int addReadingsToAreaSensors(List<ReadingDTO> readings, GeographicAreaRepository geographicAreaRepository) {
        return readerController.addReadingsToGeographicAreaSensors(readings, "resources/logs/areaReadingsHtml.html", geographicAreaRepository);
    }


    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON or XML file.
     * <p>
     * list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15(GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeRepository, AreaTypeRepository areaTypeRepository) {
        InputHelperUI input = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPathJsonOrXML(result);
        long startTime = System.currentTimeMillis();
        int areas = input.acceptPathJSONorXMLAndReadFile(filePath, geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
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
