package Sprint0.View;

import Sprint0.Controller.UserStory06Controller;
import Sprint0.Model.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserStory06View {

    private List<String> mSensors;
    private UserStory06Controller mController;

    public UserStory06View(Sensor sensor) {
        Scanner input = new Scanner(System.in);
        UserStory06Controller controller = new UserStory06Controller(sensor);
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
