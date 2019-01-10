package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static pt.ipp.isep.dei.project.io.ui.UtilsUI.INVALID_OPTION;


class SensorSettingsUI {
    private SensorSettingsController mController;
    private String sensorName;
    private String sensorType;
    private String sensorUnits; //TODO this is used but never assigned
    private boolean mTypeAdded;
    private double sensorLat;
    private double sensorLong;
    private double sensorAlt;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Sensor mSensor;
    private GeographicArea mGeographicArea;
    private SensorList mSensorList;
    private GeographicAreaList mGeographicAreaList;  //TODO this is used but never assigned


    SensorSettingsUI() {
        this.mController = new SensorSettingsController();
    }

    void run(GeographicArea geo,GeographicAreaList newGeoListUi) {
        this.mGeographicArea = geo;
        this.mGeographicAreaList = newGeoListUi;
        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().size() == 0) {
            System.out.println("Invalid Geographic Area List - List Is Empty");
            return;
        }
        boolean activeInput = true;
        int option;
        System.out.println("--------------\n");
        System.out.println("Sensor Settings\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printOptionMessage();
            option = UtilsUI.readInputNumberAsInt();
            switch (option) {
                case 1:
                    if(!getInputGeographicArea05(newGeoListUi)){
                        return;
                    }
                    getInput05Sensor05();
                    getInput05();
                    updateModel05();
                    displayState05();
                    activeInput = false;
                    break;

                case 2:
                    getInput06();
                    updateUS06();
                    displayUS06();
                    getInputPart206();
                    updateAndDisplayUS06Part206();
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



    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    private boolean getInputGeographicArea05(GeographicAreaList newGeoListUi){
        UtilsUI utils = new UtilsUI();
        this.mGeographicArea = utils.getInputGeographicArea(newGeoListUi);
        this.mSensorList = this.mGeographicArea.getSensorList();
        if (this.mSensorList == null || this.mSensorList.getSensorList().isEmpty()) {
            System.out.println("This Geographic Area doesn't have any sensors. Please add a sensor to this Geographic Area to continue.");
            return false;
        }
        return true;
    }

    private void getInput05Sensor05() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the sensor to add the type to: ");
        this.sensorName = scanner.next();
    }

    private void getInput05() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the type of sensor you want to assign to the sensor: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid name of Type Area. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.sensorType = scanner.next();
    }

    private void updateModel05() {
        this.mTypeAdded = mController.setTypeSensor(this.mSensorList, this.sensorName, this.sensorType);
    }

    private void displayState05() {
        if (this.mTypeAdded) {
            System.out.print("The type has been successfully assigned.");
        } else System.out.print("The type of sensor wasn't added. There is no sensor with that name.");
    }

    /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
     one can get measurements of that type in that area */

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
        this.sensorName = input.nextLine();
        System.out.println("You entered sensor " + sensorName);

        // Type Getter
        System.out.println("\nEnter Sensor type:\t");

        while (!input.hasNext("[a-zA-Z]+")) {
            input.next();
            System.out.println("Not a valid type. Try again");
        }

        this.sensorType = input.nextLine();
        System.out.println("You entered type " + sensorType);

        System.out.println("\nEnter Sensor units:\t");
        this.sensorUnits = input.nextLine();
        System.out.println("You entered units "+ sensorUnits);


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
        TypeSensor mType = mController.createType(this.sensorType, this.sensorUnits);
        Date mDate = mController.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mSensor = mController.createSensor(this.sensorName, mType, mLocal, mDate);

    }

    private void displayUS06() {
        if (mSensor != null) {
            System.out.println("\n \n Sensor has been successfully created.");
        } else {
            System.out.println("\n \nSensor could not be created.");
        }
    }

    private void getInputPart206() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Add sensor to Geographic Area?\n");
        System.out.println("Yes/No:\t");
        List<String> valid = Arrays.asList("Yes", "YES", "yes", "Y", "y");
        if (input.nextLine().equals(valid) ) {
            System.out.println("Type the name of the Geographic Area which the sensor will be added to");
            System.out.println("\nEnter Geographic Area Name:\t");
            String mGeographicAreaName = input.nextLine();
            System.out.println("You entered  " + mGeographicAreaName);
        } else {
            System.out.println("Exiting");
        }
    }

    private void updateAndDisplayUS06Part206() {

        if (mController.addSensorToGeographicArea(mGeographicArea,mGeographicAreaList,mSensor)) {
            System.out.println("\nSensor has been successfully added to the Geographic Area");
        } else {
            System.out.println("\nSensor could not be added to the Area.");
        }
    }


    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */
    private void printOptionMessage() {
        System.out.println("Sensor Settings Options:\n");
        System.out.println("1) Define the sensor type. (US05)");
        System.out.println("2) Add a new sensor and associate it to a geographical area. (US006)");
        System.out.println("0) (Return to main menu)\n");
    }
}
