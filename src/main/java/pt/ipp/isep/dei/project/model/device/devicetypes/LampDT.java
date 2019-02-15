package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

public class LampDT implements DeviceType {

    public DeviceTemporary createDeviceType() {
        DeviceSpecs ds = new LampSpec();
        return new DeviceTemporary(ds);
    }

    public String getDeviceType() {
        return "Lamp";
    }
}
