package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllerCLI.SensorSettingsController;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.MenuFormatter;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class SensorSettingsUI {
    private final SensorSettingsController controller;
    private final List<String> menuOptions;

    SensorSettingsUI() {
        this.controller = new SensorSettingsController();
        menuOptions = new ArrayList<>();
        menuOptions.add("Define a new sensor type. (US05)");
        menuOptions.add("Add a new sensor and associate it to a geographical area. (US006)");
        menuOptions.add("Display already defined sensor types.");
        menuOptions.add("(Return to main menu)");
    }

    void run(GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeList) {
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Sensor Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            MenuFormatter.showMenu("Sensor Settings", menuOptions);
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS05(sensorTypeList);
                    activeInput = false;
                    break;
                case 2:
                    runUS06(geographicAreaRepository, sensorTypeList);
                    activeInput = false;
                    break;
                case 3:
                    displayList(sensorTypeList);
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

    /* LIST DISPLAY */

    private void displayList(SensorTypeRepository sensorTypeRepository) {
        System.out.println(controller.buildSensorTypesString(sensorTypeRepository));
    }


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */
    private void runUS05(SensorTypeRepository sensorTypeList) {
        SensorType sensorType = getInput05(sensorTypeList);
        boolean added = updateModel05(sensorType, sensorTypeList);
        displayState05(added);
    }

    private SensorType getInput05(SensorTypeRepository sensorTypeList) {
        System.out.print("Enter the sensor type's name: ");
        String name = InputHelperUI.getInputStringAlphabetCharOnly();
        System.out.print("Type the sensor type's unit of measurement: ");
        Scanner scan = new Scanner(System.in);
        String unit = scan.nextLine();
        return controller.createType(sensorTypeList, name, unit);
    }

    private boolean updateModel05(SensorType sensorType, SensorTypeRepository sensorTypeList) {
        return controller.addTypeSensorToList(sensorType, sensorTypeList);
    }

    private void displayState05(boolean added) {
        if (added) {
            System.out.print("The sensor type has been successfully created.");
        } else {
            System.out.print("The sensor type you are trying to create already exists. Please try again.");
        }
    }

    /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
     one can get measurements of that type in that area */
    private void runUS06(GeographicAreaRepository geographicAreaRepository, SensorTypeRepository sensorTypeList) {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        List<GeographicArea> geoAreas = geographicAreaRepository.getAll();
        GeographicArea geographicArea = InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geoAreas);
        AreaSensor areaSensor = createSensor(sensorTypeList, geographicArea);
        if (!getConfirmation(areaSensor)) {
            return;
        }
        addSensor(areaSensor, geographicArea, geographicAreaRepository);
    }

    private AreaSensor createSensor(SensorTypeRepository sensorTypeList, GeographicArea geographicArea) {
        String id = getInputSensorId();
        String name = getInputSensorName();
        SensorType sensorType = getInputTypeSensor(sensorTypeList);
        Local local = getInputSensorLocal();
        Date startDate = getInputStartDate();
        Long geoID = geographicArea.getId();
        return controller.createSensor(id, name, sensorType, local, startDate, geoID);
    }

    private String getInputSensorId() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter the sensor's ID:\t");
        return input.nextLine();
    }

    private String getInputSensorName() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter the sensor's name:\t");
        return input.nextLine();
    }

    private SensorType getInputTypeSensor(SensorTypeRepository sensorTypeList) {
        System.out.println("\nPlease select one of the following sensorTypes:\t");

        return InputHelperUI.getInputSensorTypeByList(sensorTypeList);
    }

    private Local getInputSensorLocal() {
        System.out.println("\nNow let's set its GPS localization\n");
        System.out.println("\nEnter the latitude:\t");
        double latitude = InputHelperUI.getInputAsDouble();

        System.out.println("\nEnter Longitude:\t");
        double longitude = InputHelperUI.getInputAsDouble();

        System.out.println("\nEnter Altitude:\t");
        double altitude = InputHelperUI.getInputAsDouble();

        return controller.createLocal(latitude, longitude, altitude);
    }

    private Date getInputStartDate() {
        System.out.println("\nEnter the sensor's starting date:\t");
        return DateUtils.getInputYearMonthDay();
    }

    private boolean getConfirmation(AreaSensor areaSensor) {
        System.out.println("You have created the following sensor:\n" + controller.buildSensorString(areaSensor));
        Scanner input = new Scanner(System.in);
        System.out.println("\n You are now adding the sensor to the selected geographic area. Do you wish to continue?\n");
        System.out.println("Yes/No:\t");
        return "yes".equals(input.nextLine());
    }

    private void addSensor(AreaSensor areaSensor, GeographicArea geographicArea, GeographicAreaRepository geographicAreaRepository) {

        if (controller.addSensorToGeographicArea(areaSensor, geographicArea)) {
            geographicAreaRepository.updateGeoArea(geographicArea);
            System.out.println("\nSensor has been successfully added to the geographic area.");
        } else {
            System.out.println("\nSensor wasn't added to the selected geographic area.");
        }
    }
}
