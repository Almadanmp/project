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
 * */

public class US04UI {
    private String action;
    private boolean active;

    public US04UI() {
        active = false;
    }

    public void run() {
        this.active = true;
        while(this.active) {
            getInput();
            update();
            display();
        }
    }

    private void getInput() {
        System.out.println("Please insert Geographic Area type:");
        Scanner input = new Scanner(System.in);
        this.action = input.nextLine();
        System.out.println("You entered Geographic Area Type: " + action);
    }

    private void update() {
        US04Controller ctrl04 = new US04Controller();
        ctrl04.receiveTypeOfGeographicArea(this.action);
    }

    private void display() {
        US04Controller ctrl04 = new US04Controller();
        GeographicAreaList ga = ctrl04.matchGeographicAreaTypeGiven();
        List<GeographicArea> lga;
        lga = ga.getGeographicAreaList();
        String result = lga.stream().map(GeographicArea::getName).collect(Collectors.joining(", "));
        System.out.print(result);
    }
}
