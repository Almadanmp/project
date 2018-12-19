package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US135Controller;
import PT.IPP.ISEP.DEI.Project.Model.EnergyGridList;
import PT.IPP.ISEP.DEI.Project.Model.PowerSource;

import java.util.Scanner;

/** As an Administrator, I want to add a power source to a energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135UI {

    private PowerSource mPowerSource;
    private boolean mActive;

    public US135UI() {
        mActive = false;
    }

    public void run(EnergyGridList energyGridList) {
        this.mActive = true;
        while (this.mActive) {
            addPowerSourceToEnergyGrid();
            updateModelAndDisplayState(energyGridList);
        }
    }

    public void addPowerSourceToEnergyGrid() {
        US135Controller ctrl = new US135Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Type the maximum power output of the power source you want to add: ");
        double maxPowerOutput = scanner.nextDouble();
        System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
        double maxEnergyStorage = scanner.nextDouble();
        this.mPowerSource = ctrl.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    public void updateModelAndDisplayState(EnergyGridList energyGridList) {
        US135Controller ctrl = new US135Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the energy grid that the power source should be added on: ");
        String energyGridName = scanner.next();
        ctrl.addPowerSourceToEnergyGrid(energyGridList.matchEnergyGrid(energyGridName), mPowerSource);
        System.out.println("The power source was added with success!");
    }

}
