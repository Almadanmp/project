package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.US135Controller;
import pt.ipp.isep.dei.project.model.HouseList;

import java.util.Scanner;

/** As an Administrator, I want to add a power source to a energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135UI {

    private boolean mActive;

    private US135Controller ctrl135;

    US135UI() {
        mActive = false;
    }

    public void run(HouseList houseList) {
        this.ctrl135 = new US135Controller(houseList);
        this.mActive = true;
        while (this.mActive) {
            getInputAndUpdateHouseName();
            getInputAndSelectEnergyGrid();
            getInputAndCreatePowerSource();
            updateModelAndDisplayState();
        }
    }

    private void getInputAndUpdateHouseName() {
        System.out.println("Please insert the house name you that want to add a power source to one of its energy grids: ");
        Scanner scanner = new Scanner(System.in);
        String houseName = scanner.nextLine();
        if (ctrl135.seeIfHouseExistsInHouseList(houseName)) {
            System.out.println("The house you have inserted is on the list.");
        } else {
            System.out.println("The house you have inserted is not on the list.");
        }
    }

    private void getInputAndSelectEnergyGrid(){
        System.out.println(ctrl135.seeIfEnergyGridListIsEmptyAndShowItsContent());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid you want to add a power source to: ");
        String name = scanner.next();
        if (ctrl135.selectEnergyGrid(name)){
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
        ctrl135.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    private void updateModelAndDisplayState() {
        if (ctrl135.addPowerSourceToEnergyGrid()) {
            System.out.println("The power source was added with success!");
        }else {
            System.out.println("The power source was NOT added to the energy grid!");
        }
    }

}
