package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceTemporary;
import pt.ipp.isep.dei.project.model.device.devicespecs.DeviceSpecs;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

public class WaterHeaterDT implements DeviceType {

    public DeviceTemporary createDeviceType() {
        DeviceSpecs ds = new WaterHeaterSpec();
        return new DeviceTemporary(ds);
    }

    public String getDeviceType() {
        return "Water Heater";
    }
}
