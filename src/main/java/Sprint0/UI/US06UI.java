package Sprint0.UI;

import Sprint0.Controller.US06Controller;
import Sprint0.Model.*;

import java.sql.SQLOutput;
import java.util.*;

public class US06UI {

    private Sensor mSensor;
    private GeographicArea mAG;
    private US06Controller mController;


    public US06UI(Sensor sensor,GeographicArea ga) {
        this.mSensor = sensor;
        this.mAG = ga;
        US06Controller controller = new US06Controller(mSensor, mAG);

    }
    public void run() {
        Scanner input = new Scanner(System.in);

        //Console title
        System.out.println("***************************************************\n" +
                           "************** Sensor Addition Menu ***************\n" +
                           "****************** sWitCh 2018 ********************\n" +
                           "***************************************************\n");

        System.out.println("New Sensor Input\n");
        System.out.println("\nEnter Sensor Name:\t");
        String sensorName = input.nextLine();
        System.out.println("You entered sensor " + sensorName);

        System.out.println("\nEnter Sensor type:\t");
        String sensorType = input.nextLine();
        TypeSensor sensorT = new TypeSensor(sensorType);
        System.out.println("You entered type " + sensorType);

        System.out.println("\nEnter Sensor Localization:\t");
        double sensorLat = input.nextDouble();
        double sensorLong = input.nextDouble();
        double sensorAlt = input.nextDouble();
        System.out.println("You entered sensor on coordinates" + sensorLat + "," + sensorLong + "," + sensorAlt);

        System.out.println("\nEnter Sensor starting date:\t");
        System.out.println("\nEnter the year:\t");
        int dataYear = input.nextInt();
        System.out.println("\nEnter the Month:\t");
        int dataMonth = input.nextInt();
        System.out.println("\nEnter the Day:\t");
        int dataDay = input.nextInt();
        Date date = new GregorianCalendar(dataYear, dataMonth,dataDay).getTime();
        System.out.println("You entered type " + sensorType);

        Local local = new Local(sensorLat, sensorLong, sensorAlt);
        Sensor sensorCreated = new Sensor(sensorName,sensorT,local,date);

        System.out.println("Sensor sucessefully created!");

        mController.addSensor(sensorCreated);
    }
}
