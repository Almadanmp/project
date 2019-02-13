package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.Scanner;

class SensorSettingsUI {
    private SensorSettingsController mController;
    private String mSensorName;
    private String mSensorTypeName;
    private String mSensorUnits;
    private double sensorLat;
    private double sensorLong;
    private double sensorAlt;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Sensor mSensor;
    private GeographicArea mGeographicArea;


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
            option = inputUtils.readInputNumberAsInt();
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the new Type of Sensor you want to create: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid Type. Please insert only Alphabetic Characters");
            scanner.next();
        }
        String name = scanner.next();
        System.out.print("Type the Unit of Measurement used for this type: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid Unit of Measurement. Please insert only Alphabetic Characters");
            scanner.next();
        }
        String unit = scanner.next();
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
        if (utilsUI.geographicAreaListIsValid(geographicAreaList)) {
            System.out.println(utilsUI.invalidGAList);
            return;
        }
        if(utilsUI.typeSensorListIsValid(typeSensorList)){
            System.out.println(utilsUI.invalidTypeSensorList);
            return;
        }
        getInput06();
        updateUS06();
        displayUS06();
        getInputPart206(geographicAreaList);
    }
    private void getInput06() {
        Scanner input = new Scanner(System.in);

        //Console title
        System.out.println("***************************************************\n" +
                "************** Sensor Addition Menu ***************\n" +
                "****************** sWitCh 2018 ********************\n" +
                "***************************************************\n");

        System.out.println("**********  New Sensor Input  ***********\n");

        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        this.mSensorName = input.nextLine();
        System.out.println("You entered sensor " + mSensorName);

        // Type Getter
        System.out.println("\nEnter Sensor type:\t");

        while (!input.hasNext("[a-zA-Z]+")) {
            input.next();
            System.out.println("Not a valid type. Try again");
        }

        this.mSensorTypeName = input.nextLine();
        System.out.println("You entered type " + mSensorTypeName);

        System.out.println("\nEnter Sensor units:\t");
        this.mSensorUnits = input.nextLine();
        System.out.println("You entered units " + mSensorUnits);


        // Local Getter
        System.out.println("\nEnter Sensor Localization:\t");
        System.out.println("\nEnter Latitude:\t");
        while (!input.hasNextDouble()) {
            input.nextLine();
            System.out.println("Not a valid latitude. Try again");
        }
        this.sensorLat = input.nextDouble();
        input.nextLine();
        System.out.println("\nEnter Longitude:\t");
        while (!input.hasNextDouble()) {
            input.nextLine();
            System.out.println("Not a valid longitude. Try again");
        }
        this.sensorLong = input.nextDouble();
        System.out.println("\nEnter Altitude:\t");
        while (!input.hasNextDouble()) {
            input.nextLine();
            System.out.println("Not a valid altitude. Try again");
        }
        this.sensorAlt = input.nextDouble();
        input.nextLine();
        System.out.println("You entered sensor on coordinates  " + sensorLat + "  ,  " + sensorLong + "  ,  " + sensorAlt);

        // Date Getter

        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid year. Try again");
        }
        this.dataYear = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Month:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid month. Try again");
        }
        this.dataMonth = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Day:\t");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Not a valid day. Try again");
        }
        this.dataDay = input.nextInt();
        System.out.println("You entered the date successfully!");
        input.nextLine();
    }

    private void updateUS06() {
        Local mLocal = mController.createLocal(this.sensorLat, this.sensorLong, this.sensorAlt);
        TypeSensor mType = mController.createType(this.mSensorTypeName, this.mSensorUnits);
        Date mDate = mController.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mSensor = mController.createSensor(this.mSensorName, mType, mLocal, mDate);
    }

    private void displayUS06() {
        if (mSensor != null) {
            System.out.println("\n \n Sensor has been successfully created.");
        } else {
            System.out.println("\n \nSensor could not be created.");
        }
    }

    private void getInputPart206(GeographicAreaList geographicAreaList) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Add sensor to Geographic Area?\n");
        System.out.println("Yes/No:\t");
        if ("yes".equals(input.nextLine())) {
            getInputPart306(geographicAreaList);
        }
    }

    private void getInputPart306(GeographicAreaList geographicAreaList) {
        InputUtils inputUtils = new InputUtils();
        mGeographicArea = inputUtils.getGeographicAreaByList(geographicAreaList);
        updateAndDisplayUS06Part206();
    }

    private void updateAndDisplayUS06Part206() {
        if (mController.addSensorToGeographicalArea(mGeographicArea)) {
            System.out.println("\nSensor has been successfully added to the Geographic Area");
        } else {
            System.out.println("\nSensor could not be added to the Area.");
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
