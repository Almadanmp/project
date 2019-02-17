package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.devices.Device;
import pt.ipp.isep.dei.project.model.device.devices.WashingMachine;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;

public class WashingMachineDT implements DeviceType {

    public Device createDeviceType() {
        WashingMachineSpec ds = new WashingMachineSpec();
        return new WashingMachine(ds);
    }

    public String getDeviceType() {
        return "Washing Machine";
    }
}
