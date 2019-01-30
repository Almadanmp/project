package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

public class LampDT implements DeviceType {

    public Device createDeviceType() {
        DeviceSpecs ds = new LampSpec();
        return new Device(ds);
    }
}
