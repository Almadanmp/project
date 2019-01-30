package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DishwasherSpec;

public class DishwasherDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new DishwasherSpec();
        return new Device(ds);
    }
}
