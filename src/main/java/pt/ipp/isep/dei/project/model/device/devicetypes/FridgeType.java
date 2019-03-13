package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

public class FridgeType implements DeviceType {

    public Device createDevice() {
        FridgeSpec ds = new FridgeSpec();
        return new Fridge(ds);
    }

    public String getDeviceType() {
        return "Fridge";
    }
}
