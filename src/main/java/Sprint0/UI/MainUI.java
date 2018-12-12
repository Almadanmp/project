package Sprint0.UI;

import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.SensorList;
import Sprint0.Model.TypeAreaList;

import java.util.Arrays;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        //Program Variables

        boolean activeProgram = true;
        SensorList mSensorList = new SensorList();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        TypeAreaList mTypeAreaList = new TypeAreaList();

        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (activeProgram) {

            System.out.println("\n***************************************************\n" +
                    "***************** Main Menu Test ******************\n" +
                    "****************** sWitCh 2018 ********************\n" +
                    "***************************************************\n");

            // Submenus Input selection

            String[] menu = {" 0. Exit\n", "1. Create a new Type of Geographic Area\n", "2. List all area Types\n", "3. Create a new Geographic Area\n", "4. Option4\n", "5. Determine the type of a sensor.\n", "6. Create a new Sensor and Add it to a GA 6\n", "7. Option 7\n", "8. See if an area is contained in another area.\n"};


            System.out.println("Select the task you want to do:");

            String formattedString = Arrays.toString(menu)
                    .replace(",", "")  //remove the commas
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "");  //remove the left bracket

            System.out.print(formattedString);
            System.out.print("\nEnter option number:\t");
            option = scanner.nextInt();
            switch (option) {
                case 0:
                    activeProgram = false;
                    break;
                case 1:
                    US01UI view1 = new US01UI();
                    view1.run(mTypeAreaList);
                    break;
                case 2:
                    US02UI view2 = new US02UI();
                    break;
                case 3:
                    US03UI view3 = new US03UI();
                    view3.run(mGeographicAreaList);
                    break;
                case 4:
                    US04UI view4 = new US04UI();
                    view4.run(mGeographicAreaList);
                    break;
                case 5:
                    US05UI view5 = new US05UI();
                    view5.run(mSensorList);
                    break;
                case 6:
                    US06UI view6 = new US06UI(mSensorList,mGeographicAreaList);
                    view6.run();
                    break;
                case 7:
                    System.out.println("test pegaLaMaças");
                    break;
                case 8:
                    US08UI view8 = new US08UI();
                    view8.run(mGeographicAreaList);
                    break;
            }
        }
    }
}
