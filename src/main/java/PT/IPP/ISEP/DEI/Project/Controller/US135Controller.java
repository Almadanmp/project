package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.EnergyGrid;
import PT.IPP.ISEP.DEI.Project.Model.EnergyGridList;
import PT.IPP.ISEP.DEI.Project.Model.PowerSource;

/** As an Administrator, I want to add a power source to a house grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135Controller {

    public US135Controller(){}

    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){
        return new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    public void addPowerSourceToEnergyGrid(EnergyGrid energyGrid, PowerSource powerSource){
        energyGrid.addPowerSource(powerSource);
    }
}
