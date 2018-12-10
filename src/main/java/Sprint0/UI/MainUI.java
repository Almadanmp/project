package Sprint0.UI;

import Sprint0.Model.SensorList;
import Sprint0.Model.TypeAreaList;

import java.util.Arrays;
import java.util.Scanner;

public class MainUI {

    public static SensorList mSensorList = new SensorList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        //Console title

        System.out.println("***************************************************\n" +
                "***************** Main Menu Test ******************\n" +
                "****************** sWitCh 2018 ********************\n" +
                "***************************************************\n");

        // Submenus Input selection

        String[] menu = {" 1. Create a new Type of Geographic Area\n", "2. Option2\n", "3. Option3\n", "4. Option4\n", "5. Determine the type of a sensor.\n"};


        System.out.println("Select the task you want to do:");

        String formattedString = Arrays.toString(menu)
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "");  //remove the left bracket

        System.out.print(formattedString);
        System.out.print("\nEnter option number:\t");
        option = scanner.nextInt();
        switch (option) {
            case 1:
                TypeAreaList list = new TypeAreaList();
                US01UI us = new US01UI(list);
                us.run();
                break;
            case 2:
                System.out.println("test PERAS");
                break;
            case 3:
                System.out.println("test MAÇAS");
                break;
            case 4:
                System.out.println("test LIMOES");
                break;
            case 5:
                US05UI view5 = new US05UI();
                view5.run();
        }
    }
}
