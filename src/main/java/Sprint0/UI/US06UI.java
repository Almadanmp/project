package Sprint0.UI;

import Sprint0.Controller.US06Controller;
import Sprint0.Model.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class US06UI {

    private List<String> mSensors;
    private US06Controller mController;

    public US06UI(Sensor sensor) {
        Scanner input = new Scanner(System.in);
        US06Controller controller = new US06Controller(sensor);
        //Console title
        System.out.println("***************************************************\n"+
                "************** Sensor Addition Menu ***************\n"+
                "****************** sWitCh 2018 ********************\n"+
                "***************************************************\n");

        System.out.println("New Sensor Input:" );
        String sensorNome = input.nextLine();
        System.out.println("You entered sensor " + sensorNome);
        mSensors.add(sensorNome);
        System.out.println(mSensors);
    }
}
