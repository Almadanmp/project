package Sprint0.UI;

import Sprint0.Model.*;

import java.util.Arrays;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args) {
        //Program Variables

        boolean activeProgram = true;
        SensorList mSensorList = new SensorList();
        GeographicAreaList mGeographicAreaList = new GeographicAreaList();
        TypeAreaList mTypeAreaList = new TypeAreaList();
        Scanner enterToReturnToConsole=new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        int option;

        while (activeProgram) {

            System.out.println("\n***************************************************\n" +
                    "***************** Main Menu Test ******************\n" +
                    "****************** sWitCh 2018 ********************\n" +
                    "***************************************************\n");

            // Submenus Input selection

            String[] menu = {" 0. Exit\n", "1. Create a new Type of Geographic Area\n", "2. List all area Types\n", "3. Create a new Geographic Area\n", "4. List All Geographic Areas With Area Type Given\n", "5. Determine the type of a sensor\n", "6. Create a new Sensor and Add it to a GA\n", "7. Say that an area is contained in another area \n", "8. See if an area is contained in another area.\n", "9. Display Sensor List\n", "10. Display Geographic Area\n"};


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
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 2:
                    US02UI view2 = new US02UI();
                    view2.run(mTypeAreaList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 3:
                    US03UI view3 = new US03UI();
                    view3.run(mGeographicAreaList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 4:
                    US04UI view4 = new US04UI();
                    view4.run(mGeographicAreaList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 5:
                    US05UI view5 = new US05UI();
                    view5.run(mSensorList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 6:
                    US06UI view6 = new US06UI(mSensorList,mGeographicAreaList);
                    view6.run();
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 7:
                    US07UI view7 = new US07UI();
                    view7.run(mGeographicAreaList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 8:
                    US08UI view8 = new US08UI();
                    view8.run(mGeographicAreaList);
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 9:
                    for(Sensor sensor : mSensorList.getSensorList()) {
                        System.out.println(sensor.getName());
                    }
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
                case 10:
                    for(GeographicArea ga : mGeographicAreaList.getGeographicAreaList()) {
                        System.out.println(ga.getName());
                    }
                    System.out.println("\nPress ENTER to return.");
                    enterToReturnToConsole.nextLine();
                    break;
            }
        }
    }
}
