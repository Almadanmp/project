package Sprint0.UI;

import Sprint0.Controller.US08Controller;
import Sprint0.Model.GeographicArea;

import java.util.Scanner;

public class US08UI {

    private GeographicArea mGeographicArea1;
    private GeographicArea mGeographicArea2;
    private US08Controller mUI;

    public US08UI(GeographicArea ga1, GeographicArea ga2){
        this.mGeographicArea1 = ga1;
        this.mGeographicArea2 = ga2;
        US08Controller controller = new US08Controller(mGeographicArea1, mGeographicArea2);
    }

    public void run(){
        Scanner input = new Scanner(System.in);

        System.out.println("See if an Area is contained in another Area:\n");
        System.out.println("\nSelect first Area name: \t");
        String ga1 = input.nextLine();
        System.out.println("\nSelect second Area name: \t");
    }

}
