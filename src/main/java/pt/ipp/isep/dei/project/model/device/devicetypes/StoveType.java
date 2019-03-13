package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Stove;
import pt.ipp.isep.dei.project.model.device.devicespecs.StoveSpec;

public class StoveType implements DeviceType {

    public Device createDevice() {
        StoveSpec ds = new StoveSpec();
        return new Stove(ds);
    }

    public String getDeviceType() {
        return "Stove";
    }
}
