package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

public class WashingMachineDT implements DeviceType {

    public Device createDeviceType() {
        DeviceSpecs ds = new WashingMachineSpec();
        return new Device(ds);
    }

    public String getDeviceType() {
        return "Washing Machine";
    }
}
