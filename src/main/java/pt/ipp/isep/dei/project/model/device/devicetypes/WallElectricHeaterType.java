package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallElectricHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallElectricHeaterSpec;

public class WallElectricHeaterType implements DeviceType {

    public Device createDevice() {
       return new WallElectricHeater(new WallElectricHeaterSpec());
    }

    public String getDeviceType() {
        return "WallElectricHeater";
    }
}
