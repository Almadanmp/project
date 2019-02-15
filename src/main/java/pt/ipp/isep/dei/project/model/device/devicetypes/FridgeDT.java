package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

public class FridgeDT implements DeviceType {

    public DeviceTemporary createDeviceType() {
        DeviceSpecs ds = new FridgeSpec();
        return new DeviceTemporary(ds);
    }

    public String getDeviceType() {
        return "Fridge";
    }
}
