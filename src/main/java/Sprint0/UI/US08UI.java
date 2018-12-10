package Sprint0.UI;

import Sprint0.Controller.US08Controller;
import Sprint0.Model.GeographicArea;

import java.util.Scanner;

public class US08UI {

    private String mNameGeographicArea1;
    private String mNameGeographicArea2;
    private boolean active;
    private boolean validGeographicAreas;
    private boolean isContained;

    public US08UI(){active = false;}

    public void run(){
        this.active = true;
        while (this.active) {
            getInputGeographicArea1();
            getInputGeographicArea2();
            updateModel();
            displayState();
        }
    }

    private void getInputGeographicArea2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the area you want to check if it CONTAINS another area: ");
        this.mNameGeographicArea2 = scanner.next();
    }

    private void getInputGeographicArea1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the area you want to check if its CONTAINED IN another area: ");
        this.mNameGeographicArea1 = scanner.next();
    }

    private void updateModel(){
        US08Controller controller = new US08Controller();
        this.validGeographicAreas = controller.setGeographicAreas(mNameGeographicArea1,mNameGeographicArea2);
        this.isContained = controller.seeIfItsContained();
    }

    private void displayState(){
        if (isContained && validGeographicAreas){
            System.out.print(mNameGeographicArea1 + " is contained in " + mNameGeographicArea2);
        } else System.out.print(mNameGeographicArea1 + " is NOT contained in " + mNameGeographicArea2);
        active = false;
    }
}
