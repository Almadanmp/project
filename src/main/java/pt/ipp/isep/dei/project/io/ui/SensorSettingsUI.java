package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.SensorSettingsController;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

class SensorSettingsUI {
    private SensorSettingsController mController;
    private String mSensorName;
    private String mSensorTypeName;
    private String mSensorUnits;
    private TypeSensor mTypeAdded;
    private double sensorLat;
    private double sensorLong;
    private double sensorAlt;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Sensor mSensor;
    private GeographicArea mGeographicArea;
    private GeographicAreaList mGeographicAreaList;
    private List<TypeSensor> mTypeSensorList;


    SensorSettingsUI() {
        this.mController = new SensorSettingsController();
    }

    void run(GeographicAreaList newGeoListUi, List<TypeSensor> typeList) {

        this.mGeographicAreaList = newGeoListUi;
        this.mTypeSensorList = typeList;

        if (newGeoListUi == null || newGeoListUi.getGeographicAreaList().isEmpty()) {
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
            InputUtils inputUtils = new InputUtils();
            UtilsUI utils = new UtilsUI();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1:
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
                    activeInput = false;
                    break;
                case 3:
                    displayList(mTypeSensorList);
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

    private void displayList(List<TypeSensor> list) {
        if (mTypeSensorList.isEmpty()) {
            System.out.println("There are no Types of Sensor defined.");
        } else {
            mController.printTypes(list);
        }
    }

    // SHARED METHODS //
    //GET INPUT GEOGRAPHIC AREA BY LIST //

    private void getInputGeographicAreaByList(GeographicAreaList programGAList) {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        if(programGAList == null || programGAList.getGeographicAreaList().isEmpty()) {
            return;
        }
        System.out.println("Please select the Geographic Area in which your House is in from the list: ");
        SensorSettingsController controller = new SensorSettingsController();
        System.out.println(controller.printGAList(programGAList));
        int aux = inputUtils.readInputNumberAsInt();
        if (aux >= 0 && aux < programGAList.getGeographicAreaList().size()) {
            mGeographicArea = programGAList.getGeographicAreaList().get(aux);
            System.out.println((mGeographicArea.printGeographicArea()));
        } else {
            System.out.println(utils.invalidOption);
        }
    }

    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    private void getInput05() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the new Type of Sensor you want to create: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid Type. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mSensorTypeName = scanner.next();
        System.out.print("Type the Unit of Measurement used for this type: ");
        while (!scanner.hasNext("[a-zA-Z_]+")) {
            System.out.println("That's not a valid Unit of Measurement. Please insert only Alphabetic Characters");
            scanner.next();
        }
        this.mSensorUnits = scanner.next();
    }

    private void updateModel05() {
        this.mTypeAdded = mController.createType(mSensorTypeName, mSensorUnits);
        this.mTypeSensorList.add(mTypeAdded);
    }

    private void displayState05() {
        if (this.mTypeAdded != null && !this.mTypeSensorList.isEmpty()) {
            System.out.print("The type has been successfully created.");
        } else System.out.print("The type of sensor wasn't added. Please ry again.");
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

    private void getInputPart206() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Add sensor to Geographic Area?\n");
        System.out.println("Yes/No:\t");
        if ("yes".equals(input.nextLine())) {
            getInputPart306();
        }
    }

    private void getInputPart306() {
        getInputGeographicAreaByList(mGeographicAreaList);
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
