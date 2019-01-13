package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

public class WaterHeater implements DeviceSpecs, Metered {

    private double mNominalPower;

    void setNominalPower(double nominalPower) { this.mNominalPower = nominalPower;}

    public DeviceType getType() {
        return DeviceType.WATER_HEATER;
    }

    public double getConsumption() {
        return 0; //To be implemented by US752
    }

    public double getNominalPower() { return this.mNominalPower;}

}
