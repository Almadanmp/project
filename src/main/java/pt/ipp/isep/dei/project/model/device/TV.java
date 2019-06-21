package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.TvSpec;

public class TV extends CommonDevice implements Device, Metered {

    public TV(TvSpec tVSpec) {
        super(tVSpec);
    }

    public String getType() {
        return "TV";
    }

}
