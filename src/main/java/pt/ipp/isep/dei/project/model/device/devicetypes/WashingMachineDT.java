package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WashingMachineSpec;

public class WashingMachineDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new WashingMachineSpec();
        Device device = new Device(ds);
        return device;
    }
}
