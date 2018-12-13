package Sprint0.UI;

import Sprint0.Controller.US08Controller;
import Sprint0.Model.GeographicAreaList;

import java.util.Scanner;

public class US08UI {

    private String mNameGeographicAreaContained;
    private String mNameGeographicAreaContainer;
    private boolean mActive;

    public US08UI() {
        mActive = false;
    }

    public void run(GeographicAreaList list) {
        this.mActive = true;
        while (this.mActive) {
            getInputGeographicArea1();
            getInputGeographicArea2();
            verifyAndDisplayState(list);
        }
    }

    private void getInputGeographicArea2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the area you want to check if it CONTAINS another area: ");
        this.mNameGeographicAreaContainer = scanner.next();
    }

    private void getInputGeographicArea1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type the name of the area you want to check if its CONTAINED IN another area: ");
        this.mNameGeographicAreaContained = scanner.next();
    }

    private void verifyAndDisplayState(GeographicAreaList list) {
        US08Controller controller = new US08Controller(list);
        if (!(controller.matchGeographicAreas(mNameGeographicAreaContained, mNameGeographicAreaContainer))) {
            System.out.println("The given areas are invalid");
            return;
        }
        if (!(controller.seeIfAreasHaveVertices())) {
            System.out.print("The given geographic areas don't have vertices!");
            return;
        }
        if (!(controller.seeIfItsContained())) {
            System.out.print(mNameGeographicAreaContained + " is NOT contained in " + mNameGeographicAreaContainer);
            return;
        }
        System.out.print(mNameGeographicAreaContained + " is contained in " + mNameGeographicAreaContainer);
    }
}
