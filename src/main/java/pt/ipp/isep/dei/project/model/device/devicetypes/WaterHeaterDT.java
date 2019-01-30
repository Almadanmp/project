package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

public class WaterHeaterDT implements DeviceType {

    public Device createDeviceType() {
        DeviceSpecs ds = new WaterHeaterSpec();
        return new Device(ds);
    }
}
