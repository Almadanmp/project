package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.PowerSource;

/** As an Administrator, I want to add a power source to a house grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135Controller {

    private PowerSource mPowerSource;

    public US135Controller(){}

    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){
        return this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    public void addPowerSource(PowerSource powerSourceToAdd){
        this.mPowerSource = powerSourceToAdd;
    }
}
