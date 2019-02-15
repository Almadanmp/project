package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;

public class DishwasherDT implements DeviceType {

    public DeviceTemporary createDeviceType() {
        DeviceSpecs ds = new DishwasherSpec();
        return new DeviceTemporary(ds);
    }

    public String getDeviceType() {
        return "Dishwasher";
    }
}
