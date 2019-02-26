package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.ElectricOven;
import pt.ipp.isep.dei.project.model.device.MicrowaveOven;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;

public class ElectricOvenDT implements DeviceType {

    public Device createDevice() {
        ElectricOvenSpec ds = new ElectricOvenSpec();
        return new ElectricOven(ds);
    }

    public String getDeviceType() {
        return "Electric Oven";
    }

}
