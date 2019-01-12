package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;

public class WashingMachine implements DeviceSpecs {


    public DeviceType getType() {
        return DeviceType.WASHING_MACHINE;
    }


    public double getConsumption() {
        return 0; //To be implemented later, not yet specified
    }
}
