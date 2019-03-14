package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fan;
import pt.ipp.isep.dei.project.model.device.devicespecs.FanSpec;

public class FanType implements DeviceType {

    /**
     * Method that creates an instance of a Fan device.
     * @return is a device of type fan.
     */

    public Device createDevice() {
        FanSpec ds = new FanSpec();
        return new Fan(ds);
    }

    /**
     * Method that returns a hard coded string that corresponds to the device's type.
     * @return is the device's type.
     */

    public String getDeviceType() {
        return "Fan";
    }
}
