package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;

public class WineCooler extends CommonDevice implements Device, Metered {

    private double annualConsumption;


    public WineCooler(WineCoolerSpec wineCoolerSpec) {
        super(wineCoolerSpec);
    }

    public String getType() {
        return "WineCooler";
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


