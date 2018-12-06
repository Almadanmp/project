package Sprint0.UI;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class MainUI {

    public static void main(String[] args)
    {
    Scanner input = new Scanner(System.in);

    //Console title

    System.out.println("***************************************************\n"+
                       "***************** Main Menu Test ******************\n"+
                       "****************** sWitCh 2018 ********************\n"+
                       "***************************************************\n");

    // Submenus Input selection

    String[] menu = {" 1. Option1\n","2. Option2\n","3. Option3\n","4. Option4\n","5. Option5\n"};
    int[] menuLinks = {1,2,3,4,5};

    System.out.println("Select the talk you want to do: \n" );

    String formattedString = Arrays.toString(menu)
            .replace(",", "")  //remove the commas
            .replace("[", "")  //remove the right bracket
            .replace("]", "");  //remove the left bracket

    System.out.print(formattedString);
    }
}
