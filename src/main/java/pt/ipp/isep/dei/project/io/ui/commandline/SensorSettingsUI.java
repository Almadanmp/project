package pt.ipp.isep.dei.project.io.ui.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.SensorSettingsController;
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

@Service
class SensorSettingsUI {
    @Autowired
    private SensorSettingsController controller;
    private final List<String> menuOptions = createMenu();
    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    private List<String> createMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("Define a new sensor type. (US05)");
        menu.add("Add a new sensor and associate it to a geographical area. (US006)");
        menu.add("Display already defined sensor types.");
        menu.add("(Return to main menu)");
        return menu;
    }

    void run() {
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
                    runUS05();
                    activeInput = false;
                    break;
                case 2:
                    runUS06();
                    activeInput = false;
                    break;
                case 3:
                    displayList();
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

    private void displayList() {
        System.out.println(controller.buildSensorTypesString());
    }


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */
    private void runUS05() {
        SensorType sensorType = getInput05();
        boolean added = updateModel05(sensorType);
        displayState05(added);
    }

    private SensorType getInput05() {
        System.out.print("Enter the sensor type's name: ");
        String name = InputHelperUI.getInputStringAlphabetCharOnly();
        System.out.print("Type the sensor type's unit of measurement: ");
        Scanner scan = new Scanner(System.in);
        String unit = scan.nextLine();
        return controller.createType(name, unit);
    }

    private boolean updateModel05(SensorType sensorType) {
        return controller.addTypeSensorToList(sensorType);
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
    private void runUS06() {
        if (geographicAreaRepository.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        List<GeographicArea> geoAreas = geographicAreaRepository.getAll();
        GeographicArea geographicArea = InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geoAreas);
        AreaSensor areaSensor = createSensor();
        if (!getConfirmation(areaSensor)) {
            return;
        }
        addSensor(areaSensor, geographicArea);
    }

    private AreaSensor createSensor() {
        String id = getInputSensorId();
        String name = getInputSensorName();
        String sensorType = getInputTypeSensor().getName();
        Local local = getInputSensorLocal();
        Date startDate = getInputStartDate();
        return controller.createSensor(id, name, sensorType, local, startDate);
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

    private SensorType getInputTypeSensor() {
        System.out.println("\nPlease select one of the following sensorTypes:\t");

        return InputHelperUI.getInputSensorTypeByList(sensorTypeRepository);
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

    private void addSensor(AreaSensor areaSensor, GeographicArea geographicArea) {

        if (controller.addSensorToGeographicArea(areaSensor, geographicArea)) {
            geographicAreaRepository.updateGeoArea(geographicArea);
            System.out.println("\nSensor has been successfully added to the geographic area.");
        } else {
            System.out.println("\nSensor wasn't added to the selected geographic area.");
        }
    }
}
