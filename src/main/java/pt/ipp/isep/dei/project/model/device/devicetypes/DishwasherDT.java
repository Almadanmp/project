package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;

public class DishwasherDT implements DeviceType {

    public Device createDeviceType() {
        DeviceSpecs ds = new DishwasherSpec();
        return new Device(ds);
    }
}
