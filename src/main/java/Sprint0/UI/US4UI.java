package Sprint0.UI;

import Sprint0.Controller.US04Controller;
import Sprint0.Model.GeographicArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * US 04:
 * Como Administrador de Sistema pretendo saber
 * quais são as áreas geográficas de um determinado tipo.
 * */

public class US4UI {
    private String action;
    private boolean active;
    private US04Controller controller;


    public US4UI() {
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
    }

    private void update() {
        controller.receiveTypeOfGeographicArea(this.action);
    }

    private void display() {
        List<GeographicArea> ga =controller.getGeographicAreaListOfTypeGiven();
        String result = ga.stream().map(GeographicArea::getName).collect(Collectors.joining(", "));
        System.out.print(result);
    }
}
