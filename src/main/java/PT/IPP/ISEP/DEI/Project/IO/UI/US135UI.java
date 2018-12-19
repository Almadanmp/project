package PT.IPP.ISEP.DEI.Project.IO.UI;

import PT.IPP.ISEP.DEI.Project.Controller.US135Controller;
import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.PowerSource;

import java.util.Scanner;

/** As an Administrator, I want to add a power source to a energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135UI {

    private boolean mActive;

    public US135UI(){mActive = false;}

    public void run(EnergyGrid energyGrid){
        this.mActive = true;
        while(this.mActive){
            addPowerSourceToEnergyGrid();
            //displayState();
        }
    }

    public void addPowerSourceToEnergyGrid(){
        US135Controller ctrl = new US135Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the designation of the power source you want to add: ");
        String name = scanner.next();
        System.out.println("Type the maximum power output of the power source you want to add: ");
        double maxPowerOutput = scanner.nextDouble();
        System.out.println("Type the maximum energy storage of the power source you want to add (type 0 if the power source can't storage energy.): ");
        double maxEnergyStorage = scanner.nextDouble();
        PowerSource templateToAdd = ctrl.createPowerSource(name, maxPowerOutput, maxEnergyStorage);
        ctrl.addPowerSource(templateToAdd);
    }
}
