package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.WallTowelHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallTowelHeaterSpec;

public class WallTowelHeaterType implements DeviceType {
    public Device createDevice() {
        WallTowelHeaterSpec wTHSpec = new WallTowelHeaterSpec();
        return new WallTowelHeater(wTHSpec);
    }

    public String getDeviceType() {
        return "WallTowelHeater";
    }
}
