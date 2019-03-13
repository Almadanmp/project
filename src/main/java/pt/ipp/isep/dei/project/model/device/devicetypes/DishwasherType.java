package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;

public class DishwasherType implements DeviceType {

    public Device createDevice() {
        DishwasherSpec ds = new DishwasherSpec();
        return new Dishwasher(ds);
    }

    public String getDeviceType() {
        return "Dishwasher";
    }
}
