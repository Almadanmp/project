package Sprint0.UI;

import Sprint0.Controller.US04Controller;
import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * US 04:
 * Como Administrador de Sistema pretendo saber
 * quais são as áreas geográficas de um determinado tipo.
 */

public class US04UI {
    private String action;
    private GeographicAreaList mGeoAreaList;
    private GeographicAreaList filteredList; //Geographic Area List filtered with Type of Geographic Area

    /**
     * Usual run() method for UI's. In this one with receive as a parameter the Geographic Area List from the MainUI.
     * @param geoArea
     */
    public void run(GeographicAreaList geoArea) {
        this.mGeoAreaList = geoArea;
        getInput();
        update();
        display();
    }

    /**
     * Usual getInput() method for UI's. This method stores the input to use in the update() method later.
     */
    private void getInput() {
        System.out.println("Please insert Geographic Area type:");
        Scanner input = new Scanner(System.in);
        this.action = input.nextLine();
        System.out.println(this.action + " was successfully entered\n");
    }

    /**
     * Usual update() method for UI's. This method is like a setter because he updates the information using the input given before.
     */
    private void update() {
        US04Controller ctrl04 = new US04Controller(mGeoAreaList);
        ctrl04.matchGeoAreaTypeWithInput(this.action);
        filteredList = ctrl04.getGeographicAreaList();
    }

    /**
     * Usual display() method for UI's. This method displays the answer we want to get from the US given and with the input we received.
     */
    private void display() {
        if (mGeoAreaList.getGeographicAreaList().isEmpty()) {
            System.out.println("The Geographic Area list is currently empty.");
        } else if (filteredList.getGeographicAreaList().isEmpty()) {
            System.out.println("There are no Geographic Areas with this Area Type.");
        } else {
            List<GeographicArea> list = filteredList.getGeographicAreaList();
            String result = ("List Of Geographic Areas with the Area Type Given:\n"+list.stream().map(GeographicArea::getName).collect(Collectors.joining(", ")));
            System.out.print(result);
        }
    }
}
