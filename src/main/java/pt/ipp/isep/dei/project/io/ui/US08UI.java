package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US08Controller;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.Scanner;

public class US08UI {

    private String mNameGeographicAreaContained;
    private String mNameGeographicAreaContainer;

    private boolean mActive;

    US08UI() {
        mActive = false;
    }

    public void run(GeographicAreaList list) {
        this.mActive = true;
        while (this.mActive) {
            getInputGeographicContainer();
            getInputGeographicContained();
            verifyAndDisplayState(list);
        }
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINER
     */
    private void getInputGeographicContainer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the name of the area you want to check if it CONTAINS another area: ");
        this.mNameGeographicAreaContainer = scanner.next();
    }

    /**
     * getInputGeographicContainer()
     * this method makes the user define the NAME of the GeographicArea CONTAINED
     */
    private void getInputGeographicContained() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the name of the area you want to check if its CONTAINED IN another area: ");
        this.mNameGeographicAreaContained = scanner.next();
    }

    /**
     * @param list is the MainUI List
     * First we check if the Geographic Areas that we are testing exist in the MainUI list.
     * Then we check the GeographicAreaContained for its flag
     * And finally it tests the flag (Geographic Area) is equal to the testing GeographicArea Container
     */

    private void verifyAndDisplayState(GeographicAreaList list) {
        US08Controller controller = new US08Controller(list);
        if (!(controller.matchGeographicAreas(mNameGeographicAreaContained, mNameGeographicAreaContainer))) {
            System.out.println("The given areas are invalid!");
            return;
        }
        if (!(controller.seeIfItsContained())) {
            System.out.println(mNameGeographicAreaContained + " is NOT contained in " + mNameGeographicAreaContainer);
            this.mActive = false;
            return;
        }
        System.out.println(mNameGeographicAreaContained + " is contained in " + mNameGeographicAreaContainer);
        this.mActive = false;
        return;
    }
}
