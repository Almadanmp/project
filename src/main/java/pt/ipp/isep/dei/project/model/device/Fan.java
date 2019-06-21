package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.device.devicespecs.FanSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

public class Fan extends CommonProgrammableDevice implements Device, Programmable {

    /**
     * Default constructor for fan devices.
     *
     * @param fanSpec is one deviceSpec specific for fans.
     */

    public Fan(FanSpec fanSpec) {
        super(fanSpec);
    }

    /**
     * Returns a hard coded string that corresponds to the type of device.
     *
     * @return is the device's type.
     */

    public String getType() {
        return "Fan";
    }
}
