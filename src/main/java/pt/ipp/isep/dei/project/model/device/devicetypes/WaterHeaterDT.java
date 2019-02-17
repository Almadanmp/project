package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;

public class WaterHeaterDT implements DeviceType {

    public Device createDeviceType() {
        WaterHeaterSpec ds = new WaterHeaterSpec();
        return new WaterHeater(ds);
    }

    public String getDeviceType() {
        return "WaterHeater";
    }
}
