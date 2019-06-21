package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

public class Dishwasher extends CommonProgrammableDevice implements Device, Metered, Programmable {

    public Dishwasher(DishwasherSpec dishwasherSpec) {
        super(dishwasherSpec);
    }

    public String getType() {
        return "Dishwasher";
    }

}
