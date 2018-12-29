package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.EnergyGrid;
import pt.ipp.isep.dei.project.model.PowerSource;

/** As an Administrator, I want to add a power source to an energy grid,
 * so that the produced energy may be used by all devices on that grid. **/

public class US135Controller {

    private PowerSource mPowerSource;

    public US135Controller(){}

    public void createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){this.mPowerSource = new PowerSource(name, maxPowerOutput, maxEnergyStorage);}

    public void addPowerSourceToEnergyGrid(EnergyGrid energyGrid){energyGrid.addPowerSource(mPowerSource);}
}
