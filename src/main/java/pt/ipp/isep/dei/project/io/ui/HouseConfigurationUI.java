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

    public void runUS130(HouseList houseList){
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

    /**
     * US135 UI
     */

    public void runUS135(HouseList houseList) {
        this.controller = new HouseConfigurationController(houseList);
            getInputAndUpdateHouseName();
            getInputAndSelectEnergyGrid();
            getInputAndCreatePowerSource();
            updateModelAndDisplayState();
    }

    private void getInputAndUpdateHouseName() {
        System.out.println("Please insert the house name that you want to add a power source to one of its energy grids: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (controller.seeIfHouseExistsInHouseList(houseName)) {
            System.out.println("The house you have inserted is on the list.");
        } else {
            System.out.println("The house you have inserted is not on the list.");
        }
    }

    private void getInputAndSelectEnergyGrid(){
        System.out.println(controller.seeIfEnergyGridListIsEmptyAndShowItsContent());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to add a power source to: ");
        String name = scanner.next();
        if (controller.selectEnergyGrid(name)){
            System.out.println("The energy grid was selected with success.");
        }
    }

    private void getInputAndCreatePowerSource() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Type the maximum power output of the power source you want to add: ");
        double maxPowerOutput = scanner.nextDouble();
        System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
        double maxEnergyStorage = scanner.nextDouble();
        controller.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    private void updateModelAndDisplayState() {
        if (controller.addPowerSourceToEnergyGrid()) {
            System.out.println("The power source was added with success!");
        }else {
            System.out.println("The power source was NOT added to the energy grid!");
        }
    }
}
