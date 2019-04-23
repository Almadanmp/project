package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.GASettingsController;
import pt.ipp.isep.dei.project.controller.ReaderController;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.AreaTypeMapper;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeService;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.reader.ReadingsReaderCSV;
import pt.ipp.isep.dei.project.reader.ReadingsReaderJSON;
import pt.ipp.isep.dei.project.reader.ReadingsReaderXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GASettingsUI {
    private GASettingsController gaController;
    private ReaderController readerController;
    private static final String READINGS_IMPORTED = " reading(s) successfully imported.";
    private List<String> menuOptions;

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

    void runGASettings(AreaTypeService areaTypeService, GeographicAreaService geographicAreaService) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\nGeographic Area Settings\n--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("Geographic Area Settings", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS01(areaTypeService);
                    activeInput = false;
                    break;
                case 2:
                    runUS02(areaTypeService);
                    activeInput = false;
                    break;
                case 3:
                    runUS03(areaTypeService, geographicAreaService);
                    activeInput = false;
                    break;
                case 4:
                    runUS04(areaTypeService, geographicAreaService);
                    activeInput = false;
                    break;
                case 5:
                    runUS07(geographicAreaService);
                    activeInput = false;
                    break;
                case 6:
                    runUS08(geographicAreaService);
                    activeInput = false;
                    break;
                case 7:
                    runUS10(geographicAreaService);
                    activeInput = false;
                    break;
                case 8:
                    runUS11(geographicAreaService);
                    activeInput = false;
                    break;
                case 9:
                    runUS15v2(geographicAreaService);
                    activeInput = false;
                    break;
                case 10:
                    runUS20v3(geographicAreaService);
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
    private AreaTypeDTO getInputTypeAreaDTOByList(AreaTypeService areaTypeService) {
        while (true) {
            System.out.println("Please select the Geographic Area Type from the list: ");
            System.out.print(gaController.buildGATypeListString(areaTypeService));
            int aux = InputHelperUI.getInputAsInt();

            AreaType areaType = areaTypeService.getById(aux);
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
    private void runUS01(AreaTypeService areaTypeService) {
        String typeAreaName = getInputUS01();
        boolean created = updateModelUS01(areaTypeService, typeAreaName);
        displayStateUS01(created, typeAreaName);
    }

    private String getInputUS01() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert a new Type of Geographic Area");

       // while (!scanner.hasNext("[a-zA-Z\\sà-ùÀ-Ù]*")) {
       //     System.out.println("Please insert a valid name.");
       //     scanner.next();
       // }
        return scanner.nextLine();
    }

    private boolean updateModelUS01(AreaTypeService areaTypeService, String typeAreaName) {
        return gaController.createAndAddTypeAreaToList(areaTypeService, typeAreaName);
    }

    private void displayStateUS01(boolean created, String typeAreaName) {
        if (created) {
            System.out.println("Success, you have inserted a new Type of Geographic Area: "+typeAreaName);
        } else {
            System.out.println("Failed, you have inserted an invalid or repeated Type of Geographic Area.");
        }

    }

    /* USER STORY 002 - As a System Administrator I want to obtain a list of the types of Geographical Areas previously stated.
     * Class responsible for presenting the list. - NUNO AZEVEDO */
    private void runUS02(AreaTypeService areaTypeService) {
        if (!areaTypeService.isEmpty()) {
            System.out.println(gaController.getTypeAreaList(areaTypeService));
            System.out.println("\nList finished.");
        } else {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
        }
    }

    /* User Story - 03 As a System Administrator I want to create a new Geographic Area */
    private void runUS03(AreaTypeService areaTypeService, GeographicAreaService geographicAreaService) {
        if (areaTypeService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        getAreaInputUS03(areaTypeService, geographicAreaService);
    }

    private void getAreaInputUS03(AreaTypeService areaTypeService, GeographicAreaService geographicAreaService) {
        Scanner scanner = new Scanner(System.in);
        AreaTypeDTO geoTypeAreaDTO = getInputTypeAreaDTOByList(areaTypeService);
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
            gaController.addNewGeoAreaToList(geographicAreaService, geoAreaDTO, localDTO);

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
        return InputHelperUI.getInputAsDoublePositive();
    }

    /* USER STORY 04 -  As an Administrator, I want to getDB a list of existing geographical areas of a given type. */
    private void runUS04(AreaTypeService areaTypeService, GeographicAreaService geographicAreaService) {
        if (areaTypeService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_TYPE_LIST);
            return;
        }
        if (geographicAreaService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        AreaTypeDTO typeAreaDTO = getInputTypeAreaDTOByList(areaTypeService);
        List<GeographicArea> gaFinalList = matchGAByTypeArea(geographicAreaService, typeAreaDTO);
        displayGAListByTypeArea(geographicAreaService, gaFinalList, typeAreaDTO);
    }

    private List<GeographicArea> matchGAByTypeArea(GeographicAreaService geographicAreaService, AreaTypeDTO typeArea) {
        return gaController.matchGAByTypeArea(geographicAreaService, typeArea);
    }

    private void displayGAListByTypeArea(GeographicAreaService geoAreaService, List<GeographicArea> gaFinalList, AreaTypeDTO typeArea) {
        String taName = gaController.getTypeAreaName(typeArea);
        System.out.println("Geographic Areas of the type " + taName + ":\n");
        System.out.println(gaController.buildGAListString(geoAreaService, gaFinalList));
    }

    /* USER STORY 07 -  Add an existing geographical area to another one. */
    private void runUS07(GeographicAreaService geographicAreaService) {
        if (geographicAreaService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getInputMotherGeographicArea(geographicAreaService);
        GeographicArea daughterGA = getInputDaughterGeographicArea(geographicAreaService);
        updateStateUS07(motherGA, daughterGA);
        displayStateUS07(motherGA, daughterGA);
    }

    private GeographicArea getInputMotherGeographicArea(GeographicAreaService geographicAreaService) {
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("First you need to select the geographic area you wish to set as container.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);
    }

    private GeographicArea getInputDaughterGeographicArea(GeographicAreaService geographicAreaService) {
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("Second you need to select the geographic area you wish to set as contained.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);
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
    private void runUS08(GeographicAreaService geographicAreaService) {
        if (geographicAreaService.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        GeographicArea motherGA = getMotherArea(geographicAreaService);
        GeographicArea daughterGA = getDaughterArea(geographicAreaService);
        checkIfContained(motherGA, daughterGA);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private GeographicArea getMotherArea(GeographicAreaService geographicAreaService) {
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("First you need to select the geographic area you wish to test if contains another geographic area.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private GeographicArea getDaughterArea(GeographicAreaService geographicAreaService) {
        List<GeographicArea> geographicAreas = geographicAreaService.getAll();
        System.out.println("Second you need to select the geographic area you wish to test if is contained in the first one.");
        return InputHelperUI.getGeographicAreaByList(geographicAreaService, geographicAreas);
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
    private void runUS10(GeographicAreaService geographicAreaService) {

        GeographicAreaDTO geographicAreaDTO = gaController.inputArea(geographicAreaService);
        AreaSensorDTO areaSensorDTO = gaController.inputSensor(geographicAreaDTO, geographicAreaService);
        if (!gaController.deactivateSensor(areaSensorDTO, geographicAreaService)) {
            System.out.println("Sensor already deactivated.");
        } else {
            System.out.println("Sensor successfully deactivated!");
        }

    }


    /* USER STORY US011 - As an Administrator, I want to remove a sensor from a geographical area, so that it will no
    longer be used.*/

    /**
     * This method removes a sensor selected from a list of sensors of a previously selected geographic area
     */
    private void runUS11(GeographicAreaService geographicAreaService) {
        GeographicAreaDTO geographicAreaDTO = gaController.inputArea(geographicAreaService);
        AreaSensorDTO areaSensorDTO = gaController.inputSensor(geographicAreaDTO, geographicAreaService);
        if (areaSensorDTO != null) {
            updateUS11(areaSensorDTO, geographicAreaService, geographicAreaDTO);
        }
    }

    private void updateUS11(AreaSensorDTO areaSensorDTO, GeographicAreaService geographicAreaService, GeographicAreaDTO geographicAreaDTO) {
        gaController.removeSensor(areaSensorDTO, geographicAreaService);
        System.out.println("The sensor " + areaSensorDTO.getName() + " on the Geographical Area " +
                geographicAreaDTO.getName() + " has been deleted.");
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
    private void runUS20v3(GeographicAreaService geographicAreaService) {
        InputHelperUI inputHelperUI = new InputHelperUI();
        String filePath = inputHelperUI.getInputJsonXmlCsv();
        if (filePath.endsWith(".csv")) {
            importReadingsFromCSV(filePath, geographicAreaService);
        } else if (filePath.endsWith(".json")) {
            importReadingsFromJSON(filePath, geographicAreaService);
        } else if (filePath.endsWith(".xml")) {
            importReadingsFromXML(filePath, geographicAreaService);
        }
    }

    private void importReadingsFromCSV(String filePath, GeographicAreaService geographicAreaService) {
        int result = 0;
        ReadingsReaderCSV readerCSV = new ReadingsReaderCSV();
        try {
            List<ReadingDTO> list = readerCSV.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The CSV file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void importReadingsFromJSON(String filePath, GeographicAreaService geographicAreaService) {
        int result = 0;
        ReadingsReaderJSON readerJSON = new ReadingsReaderJSON();
        try {
            List<ReadingDTO> list = readerJSON.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The JSON file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private void importReadingsFromXML(String filePath, GeographicAreaService geographicAreaService) {
        int result = 0;
        ReadingsReaderXML readerXML = new ReadingsReaderXML();
        try {
            List<ReadingDTO> list = readerXML.readFile(filePath);
            result = addReadingsToAreaSensors(list, geographicAreaService);
        } catch (IllegalArgumentException illegal) {
            System.out.println("The XML file is invalid. Please fix before continuing.");
        }
        System.out.println(result + READINGS_IMPORTED);
    }

    private int addReadingsToAreaSensors(List<ReadingDTO> readings, GeographicAreaService geographicAreaService) {
        return readerController.addReadingsToGeographicAreaSensors(readings, "resources/logs/areaReadingsHtml.html", geographicAreaService);
    }


    /**
     * As an Administrator, I want to import Geographic Areas and Sensors from a JSON or XML file.
     * <p>
     * list is the static, program list of geographic areas that comes from mainUI.
     */

    private void runUS15v2(GeographicAreaService geographicAreaService) {
        InputHelperUI input = new InputHelperUI();
        System.out.println("Please insert the location of the file you want to import:");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        String filePath = input.getInputPathJsonOrXML(result);
        int areas = readerController.acceptPath(filePath, geographicAreaService);
        if (areas > 0) {
            System.out.println(areas + " Geographic Areas have been successfully imported.");
        } else {
            System.out.println("No Geographic Areas were imported. Please refer to the Area Type Log or the Area Sensor " +
                    "Log for more information");
        }
    }
}
