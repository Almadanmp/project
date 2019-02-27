package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Tv;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;

public class TvDT implements DeviceType {

    public Device createDevice() {
        TvSpec ds = new TvSpec();
        return new Tv(ds);
    }

    public String getDeviceType() {
        return "TV";
    }
}
