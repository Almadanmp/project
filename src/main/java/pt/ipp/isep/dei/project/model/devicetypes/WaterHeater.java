package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;


public class WaterHeater implements DeviceSpecs, Metered {
    double mNominalPower;
    double volumeOfWater = 1.163;
    double hotWaterTemperature;
    double coldWaterTemperature;
    double performanceRatio = 0.9;

    public DeviceType getType() {
        return DeviceType.WATER_HEATER;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public double getConsumption() {
        return 1; //To be implemented by US752
    }
}
