package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Kettler;
import pt.ipp.isep.dei.project.model.device.devicespecs.KettlerSpec;

public class KettlerType implements DeviceType {

    /**
     * This method creates a new Kettler device.
     **/
    @Override
    public Device createDevice() {
        return new Kettler(new KettlerSpec());
    }

    /**
     * This method returns a String "Kettler" which constitutes
     * this class device type.
     **/
    @Override
    public String getDeviceType() {
        return "Kettler";
    }
}
