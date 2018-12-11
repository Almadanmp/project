package Sprint0.UI;

import Sprint0.Controller.US08Controller;

import java.util.Scanner;

public class US08UI {

    private String mNameGeographicAreaContained;
    private String mNameGeographicAreaContainer;
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
        this.mNameGeographicAreaContainer = scanner.next();
    }

    private void getInputGeographicArea1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the area you want to check if its CONTAINED IN another area: ");
        this.mNameGeographicAreaContained = scanner.next();
    }

    private void updateModel(){
        US08Controller controller = new US08Controller();
        this.validGeographicAreas = controller.setGeographicAreas(mNameGeographicAreaContained, mNameGeographicAreaContainer, MainUI.mGeographicAreaList);
        if (!this.validGeographicAreas){
            System.out.println("The given areas are invalid");
            active = false;
        }else this.isContained = controller.seeIfItsContained();
    }

    private void displayState(){
        if (isContained && validGeographicAreas){
            System.out.print(mNameGeographicAreaContained + " is contained in " + mNameGeographicAreaContainer);
        } else System.out.print(mNameGeographicAreaContained + " is NOT contained in " + mNameGeographicAreaContainer);
        active = false;
    }
}
