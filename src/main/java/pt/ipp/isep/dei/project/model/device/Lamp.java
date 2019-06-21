package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.LampSpec;

public class Lamp extends CommonDevice implements Device, Metered {

    public Lamp(LampSpec lampSpec) {
        super(lampSpec);
    }

    public String getType() {
        return "Lamp";
    }

}
