package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Freezer;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;

public class FreezerDT implements DeviceType {

    public Device createDevice() {
        FreezerSpec ds = new FreezerSpec();
        return new Freezer(ds);
    }

    public String getDeviceType() {
        return "Freezer";
    }
}