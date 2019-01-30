package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.Lamp;

public class LampDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new Lamp();
        Device device = new Device(ds);
        return device;
    }
}
