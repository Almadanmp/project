package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.deviceSpecs.WaterHeaterSpec;

public class WaterHeaterDT implements DeviceType {

    public Device createDevice() {
        DeviceSpecs ds = new WaterHeaterSpec();
        return new Device(ds);
    }
}
