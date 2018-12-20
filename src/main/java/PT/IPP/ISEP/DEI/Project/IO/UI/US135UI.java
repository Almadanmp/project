package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US135Controller;
import PT.IPP.ISEP.DEI.Project.Model.EnergyGridList;

import java.util.Scanner;

/** As an Administrator, I want to add a power source to a energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135UI {

    private boolean mActive;

    US135Controller ctrl = new US135Controller();

    public US135UI() {
        mActive = false;
    }

    public void run(EnergyGridList energyGridList) {
        this.mActive = true;
        while (this.mActive) {
            getInputAndUpdate();
            if(!displayEnergyGridList(energyGridList)){
                return;
            }
            displayEnergyGridList(energyGridList);
            updateModelAndDisplayState(energyGridList);
        }
    }

    private void getInputAndUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Type the maximum power output of the power source you want to add: ");
        double maxPowerOutput = scanner.nextDouble();
        System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
        double maxEnergyStorage = scanner.nextDouble();
        ctrl.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    private boolean displayEnergyGridList(EnergyGridList energyGridList) {
        if (energyGridList.getEnergyGridList().isEmpty()) {
            System.out.println("The list of rooms is empty!");
            return false;
        } else {
            System.out.println(energyGridList.printEnergyGridList());
            return true;
        }
    }

    private void updateModelAndDisplayState(EnergyGridList energyGridList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid that the power source should be added to: ");
        String energyGridName = scanner.next();
        ctrl.addPowerSourceToEnergyGrid(energyGridList.matchEnergyGrid(energyGridName));
        System.out.println("The power source was added with success!");
    }

}
