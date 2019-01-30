package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WashingMachine;

public class WashingMachineDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new WashingMachine();
        Device device = new Device(ds);
        return device;
    }
}
