package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.HouseConfigurationController;
import pt.ipp.isep.dei.project.model.HouseList;

import java.util.Scanner;

public class HouseConfigurationUI {
    HouseConfigurationController controller;
    
    
    /** 
     *  US 130 UI
     *  
     */

    public void run(HouseList houseList){
        this.controller = new HouseConfigurationController(houseList);
        getInputHouseName();
        getInputAndAddEnergyGrid();
        updateEnergyGridList();
    }

    private void getInputHouseName() {
        System.out.println("Please insert the house name you want To create an energy grid on: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (controller.seeIfHouseExistsInHouseList(houseName)) {
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
        controller.createEnergyGrid(name, maxPower);
    }

    private void updateEnergyGridList() {
        if (controller.addEnergyGridToHouse()){
            System.out.println("The energy grid was successfully added to the selected house.");
        }else {
            System.out.println("The energy grid was NOT added to the selected house.");
        }
    }
}
