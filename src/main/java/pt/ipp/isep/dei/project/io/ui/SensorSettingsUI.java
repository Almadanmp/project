package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.io.ui.utils.DateUtils;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensorList;

import java.util.Date;
import java.util.Scanner;

class SensorSettingsUI {
    private SensorSettingsController controller;

    SensorSettingsUI() {
        this.controller = new SensorSettingsController();
    }

    void run(GeographicAreaList geographicAreaList, TypeSensorList typeSensorList) {
        if (geographicAreaList.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }

        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Sensor Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printOptionMessage();
            option = InputHelperUI.getInputAsInt();
            switch (option) {
                case 1:
                    runUS05(typeSensorList);
                    activeInput = false;
                    break;
                case 2:
                    runUS06(geographicAreaList, typeSensorList);
                    activeInput = false;
                    break;
                case 3:
                    displayList(typeSensorList);
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

    private void displayList(TypeSensorList typeSensorList) {
        System.out.println(controller.buildSensorTypesString(typeSensorList));
    }


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */
    private void runUS05(TypeSensorList typeSensorList) {
        TypeSensor typeSensor = getInput05(typeSensorList);
        boolean added = updateModel05(typeSensor, typeSensorList);
        displayState05(added);
    }

    private TypeSensor getInput05(TypeSensorList typeSensorList) {
        System.out.print("Enter the sensor type's name: ");
        String name = InputHelperUI.getInputStringAlphabetCharOnly();
        System.out.print("Type the sensor type's unit of measurement: ");
        String unit = InputHelperUI.getInputStringAlphabetCharOnly();
        return controller.createType(typeSensorList, name, unit);
    }

    private boolean updateModel05(TypeSensor typeSensor, TypeSensorList typeSensorList) {
        return controller.addTypeSensorToList(typeSensor, typeSensorList);
    }

    private void displayState05(boolean added) {
        if (added) {
            System.out.print("The sensor type has been successfully created.");
        } else {
            System.out.print("The sensor type you are trying to create already exists. Please try again.");
        }
    }

    /* USER STORY 006 - an Administrator, I want to addWithoutPersisting a new sensor and associate it to a geographical area, so that
     one can get measurements of that type in that area */
    private void runUS06(GeographicAreaList geographicAreaList, TypeSensorList typeSensorList) {
        if (geographicAreaList.isEmpty()) {
            System.out.println(UtilsUI.INVALID_GA_LIST);
            return;
        }
        Sensor sensor = createSensor(typeSensorList);
        if (!getConfirmation(sensor)) {
            return;
        }
        addSensor(sensor, geographicAreaList);
    }

    private Sensor createSensor(TypeSensorList typeSensorList) {
        String id = getInputSensorId();
        String name = getInputSensorName();
        TypeSensor typeSensor = getInputTypeSensor(typeSensorList);
        Local local = getInputSensorLocal();
        Date startDate = getInputStartDate();
        return controller.createSensor(id, name, typeSensor, local, startDate);
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

    private TypeSensor getInputTypeSensor(TypeSensorList typeSensorList) {
        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter the sensor type's name:\t");
        String name = InputHelperUI.getInputStringAlphabetCharOnly();

        System.out.println("\nEnter the sensor type's unit of measurement:\t");
        String unit = input.nextLine();

        return controller.createType(typeSensorList, name, unit);
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

    private boolean getConfirmation(Sensor sensor) {
        System.out.println("You have created the following sensor:\n" + controller.buildSensorString(sensor));
        Scanner input = new Scanner(System.in);
        System.out.println("\n Do you wish to addWithoutPersisting this sensor to a geographic area?\n");
        System.out.println("Yes/No:\t");
        return "yes".equals(input.nextLine());
    }

    private void addSensor(Sensor sensor, GeographicAreaList geographicAreaList) {
        GeographicArea geographicArea = InputHelperUI.getGeographicAreaByList(geographicAreaList);
        if (controller.addSensorToGeographicArea(sensor, geographicArea)) {
            System.out.println("\nSensor has been successfully added to the geographic area.");
        } else {
            System.out.println("\nSensor wasn't added to the selected geographic area.");
        }
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printOptionMessage() {
        System.out.println("Sensor Settings Options:\n");
        System.out.println("1) Define a new sensor type. (US05)");
        System.out.println("2) Add a new sensor and associate it to a geographical area. (US006)");
        System.out.println("3) Display already defined sensor types.");
        System.out.println("0) (Return to main menu)\n");
    }

}
