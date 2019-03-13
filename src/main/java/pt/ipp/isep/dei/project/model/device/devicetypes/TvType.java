package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.TV;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;

public class TvType implements DeviceType {

    public Device createDevice() {
        TvSpec ds = new TvSpec();
        return new TV(ds);
    }

    public String getDeviceType() {
        return "TV";
    }
}
