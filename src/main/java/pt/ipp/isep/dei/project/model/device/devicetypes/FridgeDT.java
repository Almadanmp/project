package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.FridgeSpec;

public class FridgeDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new FridgeSpec();
        return new Device(ds);
    }
}
