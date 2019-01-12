package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;

public class WaterHeater implements DeviceSpecs {


    public DeviceType getType() {
        return DeviceType.WATER_HEATER;
    }

    public double getConsumption() {
        return 0; //To be implemented by US752
    }
}
