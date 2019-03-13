package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.MicrowaveOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;

public class MicrowaveOvenType implements DeviceType {

    public Device createDevice() {
        MicrowaveOvenSpec ds = new MicrowaveOvenSpec();
        return new MicrowaveOven(ds);
    }

    public String getDeviceType() {
        return "Microwave Oven";
    }

}
