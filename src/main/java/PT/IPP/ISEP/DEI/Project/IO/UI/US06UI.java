package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US06Controller;
import PT.IPP.ISEP.DEI.Project.Model.*;
import java.util.*;


public class US06UI {

    private boolean active;
    private String sensorName;
    private String sensorType;
    private double sensorLat;
    private double sensorLong;
    private double sensorAlt;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Sensor mSensor;
    private String mGeographicAreaName;
    private SensorList mSensorList;
    private GeographicAreaList mGeographicAreaList;


    public US06UI(SensorList s, GeographicAreaList a) {
        this.mSensorList = s;
        this.mGeographicAreaList = a;
        active = false;
        // placeholder
    }

    public void run() {
        this.active = true;
        while (this.active) {
            getInput();
            updateUS06();
            displayUS06();
            getInputPart2();
            updateAndDisplayUS06Part2();
            this.active = false;
        }
    }

    private void getInput() {
        Scanner input = new Scanner(System.in);

        //Console title
        System.out.println("***************************************************\n" +
                "************** Sensor Addition Menu ***************\n" +
                "****************** sWitCh 2018 ********************\n" +
                "***************************************************\n");

        System.out.println("**********  New Sensor Input  ***********\n");

        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        this.sensorName = input.next();
        System.out.println("You entered sensor " + sensorName);

        // Type Getter
        System.out.println("\nEnter Sensor type:\t");

        while(!input.hasNext("[a-zA-Z]+")) {
            input.next();
            System.out.println("Not a valid type. Try again");
        }

        this.sensorType = input.next();
        System.out.println("You entered type " + sensorType);
        input.nextLine();
        // Local Getter
        System.out.println("\nEnter Sensor Localization:\t");
        System.out.println("\nEnter Latitude:\t");
        while(!input.hasNextDouble()) {
            input.next();
            System.out.println("Not a valid latitude. Try again");
        }
        this.sensorLat = input.nextDouble();
        input.nextLine();
        System.out.println("\nEnter Longitude:\t");
        while(!input.hasNextDouble()) {
            input.next();
            System.out.println("Not a valid longitude. Try again");
        }
        this.sensorLong = input.nextDouble();
        System.out.println("\nEnter Altitude:\t");
        while(!input.hasNextDouble()) {
            input.next();
            System.out.println("Not a valid altitude. Try again");
        }
        this.sensorAlt = input.nextDouble();
        input.nextLine();
        System.out.println("You entered sensor on coordinates  " + sensorLat + "  ,  " + sensorLong + "  ,  " + sensorAlt);

        // Date Getter
        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        while(!input.hasNextInt()) {
            input.next();
            System.out.println("Not a valid year. Try again");
        }
        this.dataYear = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Month:\t");
        while(!input.hasNextInt()) {
            input.next();
            System.out.println("Not a valid month. Try again");
        }
        this.dataMonth = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter the Day:\t");
        while(!input.hasNextInt()) {
            input.next();
            System.out.println("Not a valid day. Try again");
        }
        this.dataDay = input.nextInt();
        System.out.println("You entered the date successfully!");
        input.nextLine();
    }

    private void updateUS06() {
        US06Controller ctrl = new US06Controller();
        Local mLocal = ctrl.createLocal(this.sensorLat, this.sensorLong, this.sensorAlt);
        TypeSensor mType = ctrl.createType(this.sensorType);
        Date mDate = ctrl.createDate(this.dataYear, this.dataMonth, this.dataDay);
        this.mSensor = ctrl.createSensor(this.sensorName, mType, mLocal, mDate);
    }

    private void displayUS06() {
        this.active = true;
        US06Controller ctrl = new US06Controller();
        if (ctrl.addSensor(mSensor, mSensorList)) {
            System.out.println("\n \n Sensor has been successfully added to the list");
        } else {
            System.out.println("\n \nSensor could not be added to the list.");
        }
    }

    private void getInputPart2() {

        Scanner input = new Scanner(System.in);
        System.out.println("\n Add sensor to Geographic Area?\n");
        System.out.println("Yes/No:\t");
        if ("Yes".equals(input.nextLine()) || "yes".equals(input.nextLine()) || "Y".equals(input.nextLine()) || "y".equals(input.nextLine())) {
            System.out.println("Type the name of the Geographic Area which the sensor will be added to");
            System.out.println("\nEnter Geographic Area Name:\t");
            this.mGeographicAreaName = input.nextLine();
            System.out.println("You entered  " + this.mGeographicAreaName);
        } else {
            System.out.println("Exiting");
        }
    }
        private void updateAndDisplayUS06Part2 () {

            US06Controller ctrl = new US06Controller();
            if (ctrl.addSensorToGeographicArea(mGeographicAreaName, mGeographicAreaList, mSensorList)) {
                System.out.println("\nSensor has been successfully added to the Geographic Area");
            } else {
                System.out.println("\nSensor could not be added to the Area.");
            }
        }

}