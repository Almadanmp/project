package Sprint0.UI;

import Sprint0.Controller.US06Controller;
import Sprint0.Model.*;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.util.*;

public class US06UI {

    private boolean active;
    private US06Controller mController;
    private String sensorName;
    private String sensorType;
    private double sensorLat;
    private double sensorLong;
    private double sensorAlt;
    private int dataYear;
    private int dataMonth;
    private int dataDay;
    private Local mLocal;
    private Date mDate;
    private TypeSensor mType;
    private Sensor mSensor;
    private SensorList mSensors;


    public US06UI() {
        active = false;
        // placeholder
    }
    public void run() {
        this.active = true;
        while (this.active) {
            getInput();
            updateUS06();
            displayUS06();
        }
    }
    private void getInput() {
        Scanner input = new Scanner(System.in);

        //Console title
        System.out.println("***************************************************\n" +
                           "************** Sensor Addition Menu ***************\n" +
                           "****************** sWitCh 2018 ********************\n" +
                           "***************************************************\n");

        System.out.println("New Sensor Input\n");

        // Name Getter
        System.out.println("\nEnter Sensor Name:\t");
        this.sensorName = input.nextLine();
        System.out.println("You entered sensor " + sensorName);

        // Type Getter
        System.out.println("\nEnter Sensor type:\t");
        this.sensorType = input.nextLine();
        System.out.println("You entered type " + sensorType);

        // Local Getter
        System.out.println("\nEnter Sensor Localization:\t");
        this.sensorLat = input.nextDouble();
        this.sensorLong = input.nextDouble();
        this.sensorAlt = input.nextDouble();
        System.out.println("You entered sensor on coordinates" + sensorLat + "," + sensorLong + "," + sensorAlt);

        // Date Getter
        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        this.dataYear = input.nextInt();
        System.out.println("\nEnter the Month:\t");
        this.dataMonth = input.nextInt();
        System.out.println("\nEnter the Day:\t");
        this.dataDay = input.nextInt();
        Date date = new GregorianCalendar(dataYear, dataMonth, dataDay).getTime();
        System.out.println("You entered type " + sensorType);
    }

    private void updateUS06() {
        US06Controller ctrl = new US06Controller();
        this.mLocal = ctrl.createLocal(this.sensorLat,this.sensorLong,this.sensorAlt);
        this.mType = ctrl.createType(this.sensorType);
        this.mDate = ctrl.createData(this.dataYear,this.dataMonth,this.dataDay);
        this.mSensor = ctrl.createSensor(this.sensorName,this.mType,this.mLocal,mDate);
    }

    private void displayUS06() {
        this.active = true;
        US06Controller ctrl = new US06Controller();
        if (ctrl.addSensor()) {
            System.out.println("Sensor has been sucessefully added to the list");
        }
        else {
            System.out.println("Sensor could not be added to the list.");
        }
    }
}