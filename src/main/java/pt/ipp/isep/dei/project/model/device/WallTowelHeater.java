package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WallTowelHeaterSpec;

public class WallTowelHeater extends CommonDevice implements Device, Metered {

    public WallTowelHeater(WallTowelHeaterSpec wallTowelHeaterSpec) {
        super(wallTowelHeaterSpec);
    }

    public String getType() {
        return "WallTowelHeater";
    }

}
