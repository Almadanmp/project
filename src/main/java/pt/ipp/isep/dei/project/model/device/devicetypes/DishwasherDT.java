package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.devices.Dishwasher;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;

public class DishwasherDT implements DeviceType {

    public Device createDeviceType() {
        DishwasherSpec ds = new DishwasherSpec();
        return new Dishwasher(ds);
    }

    public String getDeviceType() {
        return "Dishwasher";
    }
}
