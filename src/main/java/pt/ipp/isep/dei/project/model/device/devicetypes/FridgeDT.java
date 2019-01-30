package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

public class FridgeDT implements DeviceType {

    public Device createDeviceType() {
        DeviceSpecs ds = new FridgeSpec();
        return new Device(ds);
    }
}
