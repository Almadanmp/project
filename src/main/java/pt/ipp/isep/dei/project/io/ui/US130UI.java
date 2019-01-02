package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US130Controller;
import pt.ipp.isep.dei.project.model.HouseList;

import java.util.Scanner;

/** US130 As an Administrator, I want to create a house grid,
 * so that I can define the rooms that are attached to it and the contracted maximum power for that grid. **/


public class US130UI {

    private US130Controller ctrl130;

    US130UI(){}

    public void run(HouseList houseList){
        this.ctrl130 = new US130Controller(houseList);
            getInputHouseName();
            getInputAndAddEnergyGrid();
            updateEnergyGridList();
    }

    private void getInputHouseName() {
        System.out.println("Please insert the house name you want To create an energy grid on: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (ctrl130.seeIfHouseExistsInHouseList(houseName)) {
            System.out.println("The House you have inserted is on the List.");
        } else {
            System.out.println("The House you have inserted is not on the List.");
        }
    }

    private void getInputAndAddEnergyGrid(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to create: ");
        String name = scanner.next();
        System.out.println("Set the maximum potency of this energy grid: ");
        double maxPower = scanner.nextDouble();
        ctrl130.createEnergyGrid(name, maxPower);
    }

    private void updateEnergyGridList() {
        if (ctrl130.addEnergyGridToHouse()){
            System.out.println("The energy grid was successfully added to the selected house.");
        }else {
            System.out.println("The energy grid was NOT added to the selected house.");
        }
    }
}
