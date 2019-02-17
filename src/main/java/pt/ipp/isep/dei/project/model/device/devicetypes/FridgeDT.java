package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.devices.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

public class FridgeDT implements DeviceType {

    public Device createDeviceType() {
        FridgeSpec ds = new FridgeSpec();
        return new Fridge(ds);
    }

    public String getDeviceType() {
        return "Fridge";
    }
}
