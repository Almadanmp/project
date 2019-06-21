package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;

public class Freezer extends CommonDevice implements Device, Metered {

    private double annualConsumption;

    public Freezer(FreezerSpec freezerSpec) {
        super(freezerSpec);
    }

    public String getType() {
        return "Freezer";
    }

    public void setAnnualConsumption(double annualConsumption) {
        this.annualConsumption = annualConsumption;
    }

    public double getAnnualConsumption() {
        return this.annualConsumption;
    }

    public double getDailyEnergyConsumption() {
        return this.annualConsumption / 365;
    }

}