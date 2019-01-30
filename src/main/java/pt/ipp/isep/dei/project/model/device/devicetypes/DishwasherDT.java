package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.Dishwasher;

public class DishwasherDT implements DeviceType {

    public void createDevice() {
        DeviceSpecs ds = new Dishwasher();
        Device device = new Device(ds);
    }
}
