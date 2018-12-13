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
    private GeographicAreaList filteredList;

    public US04UI() {
    }

    public void run(GeographicAreaList geoArea) {
        this.mGeoAreaList = geoArea;
        getInput();
        update();
        display();

    }

    private void getInput() {
        System.out.println("Please insert Geographic Area type:");
        Scanner input = new Scanner(System.in);
        this.action = input.nextLine();
        System.out.println("You entered Geographic Area Type: " + this.action);
    }

    private void update() {
        US04Controller ctrl04 = new US04Controller(mGeoAreaList);
        ctrl04.matchGeoAreaTypeWithInput(this.action);
        filteredList = ctrl04.getGeographicAreaList();
    }

    private void display() {
        if (mGeoAreaList.getGeographicAreaList().isEmpty()) {
            System.out.println("The Geographic Area list is currently empty.");
        } else if (filteredList.getGeographicAreaList().isEmpty()) {
            System.out.println("There are no Geographic Areas with this Area Type.");
        } else {
            List<GeographicArea> list = filteredList.getGeographicAreaList();
            String result = list.stream().map(GeographicArea::getName).collect(Collectors.joining(", "));
            System.out.print(result);
        }
    }
}
