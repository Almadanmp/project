package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Lamp;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

public class LampType implements DeviceType {

    public Device createDevice() {
        LampSpec ds = new LampSpec();
        return new Lamp(ds);
    }

    public String getDeviceType() {
        return "Lamp";
    }
}
