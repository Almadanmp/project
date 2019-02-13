package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.Scanner;

class SensorSettingsUI {
    private SensorSettingsController mController;

    SensorSettingsUI() {
        this.mController = new SensorSettingsController();
    }

    void run(GeographicAreaList geographicAreaList, TypeSensorList typeList) {
        UtilsUI utilsUI = new UtilsUI();
        if (utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }

        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Sensor Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printOptionMessage();
            InputUtils inputUtils = new InputUtils();
            UtilsUI utils = new UtilsUI();
            option = inputUtils.getInputAsInt();
            switch (option) {
                case 1:
                    runUS05(typeList);
                    activeInput = false;
                    break;
                case 2:
                    runUS06(geographicAreaList, typeList);
                    activeInput = false;
                    break;
                case 3:
                    displayList(typeList);
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }

    /* LIST DISPLAY */

    private void displayList(TypeSensorList list) {
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.typeSensorListIsValid(list)) {
            System.out.println(utilsUI.invalidTypeSensorList);
            return;
        }
            System.out.println(mController.buildSensorTypesString(list));
        }


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */
    private void runUS05(TypeSensorList typeList){
        TypeSensor typeSensor = getInput05();
        boolean added = updateModel05(typeSensor, typeList);
        displayState05(added);
    }

    private TypeSensor getInput05() {
        InputUtils inputUtils = new InputUtils();
        System.out.print("Enter the sensor type's name: ");
        String name = inputUtils.getInputStringAlphabetCharOnly();
        System.out.print("Type the sensor type's unit of measurement: ");
        String unit = inputUtils.getInputStringAlphabetCharOnly();
        return mController.createType(name, unit);
    }

    private boolean updateModel05(TypeSensor typeSensor, TypeSensorList typeSensorList) {
        return mController.addTypeSensorToList(typeSensor, typeSensorList);
    }

    private void displayState05(boolean added) {
        if(added){
            System.out.print("The sensor type has been successfully created.");
        } else {
            System.out.print("The sensor type you are trying to create already exists. Please try again.");
        }
    }

    /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
     one can get measurements of that type in that area */
    private void runUS06(GeographicAreaList geographicAreaList, TypeSensorList typeSensorList){
        UtilsUI utilsUI = new UtilsUI();
        if (!utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }
        if(!utilsUI.typeSensorListIsValid(typeSensorList)){
            System.out.println(utilsUI.invalidTypeSensorList);
            return;
        }
        Sensor sensor = createSensor();
        if(!getConfirmation(sensor)){
            return;
        }
        addSensor(geographicAreaList);
    }

    private Sensor createSensor() {
        String name = getInputSensorName();
        TypeSensor typeSensor = getInputTypeSensor();
        Local local = getInputSensorLocal();
        Date startDate = getInputStartDate();
        return mController.createSensor(name, typeSensor, local, startDate);
    }

    private String getInputSensorName() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter the sensor's name:\t");
        return input.nextLine();
    }

    private TypeSensor getInputTypeSensor(){
        InputUtils inputUtils = new InputUtils();
        Scanner input = new Scanner(System.in);

        System.out.println("\nEnter the sensor type's name:\t");
        String name = inputUtils.getInputStringAlphabetCharOnly();

        System.out.println("\nEnter the sensor type's unit of measurement:\t");
        String unit = input.nextLine();

        return mController.createType(name, unit);
    }

    private Local getInputSensorLocal(){
        InputUtils inputUtils = new InputUtils();
        System.out.println("\nNow let's set its GPS localization\n");
        System.out.println("\nEnter the latitude:\t");
        double latitude = inputUtils.getInputAsDouble();

        System.out.println("\nEnter Longitude:\t");
        double longitude = inputUtils.getInputAsDouble();

        System.out.println("\nEnter Altitude:\t");
        double altitude = inputUtils.getInputAsDouble();

        return mController.createLocal(latitude, longitude, altitude);
    }

    private Date getInputStartDate(){
        InputUtils inputUtils = new InputUtils();
        System.out.println("\nEnter the sensor's starting date:\t");
        return inputUtils.getInputYearMonthDay();
    }

    private boolean getConfirmation(Sensor sensor) {
        System.out.println("You have created the following sensor:\n" + mController.buildSensorString(sensor));
        Scanner input = new Scanner(System.in);
        System.out.println("\n Do you wish to add this sensor to a geographic area?\n");
        System.out.println("Yes/No:\t");
        return "yes".equals(input.nextLine());
    }

    private void addSensor(GeographicAreaList geographicAreaList) {
        InputUtils inputUtils = new InputUtils();
        GeographicArea geographicArea = inputUtils.getGeographicAreaByList(geographicAreaList);
        if (mController.addSensorToGeographicalArea(geographicArea)) {
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
        System.out.println("3) Display Sensor Types already defined");
        System.out.println("0) (Return to main menu)\n");
    }

}
