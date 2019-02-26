package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallElectricHeater;

public class WallElectricHeaterDT implements DeviceType {

    public Device createDevice() {
       return new WallElectricHeater();
    }

    public String getDeviceType() {
        return "WallElectricHeater";
    }
}
