package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.devices.Lamp;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

public class LampDT implements DeviceType {

    public Device createDeviceType() {
        LampSpec ds = new LampSpec();
        return new Lamp(ds);
    }

    public String getDeviceType() {
        return "Lamp";
    }
}
