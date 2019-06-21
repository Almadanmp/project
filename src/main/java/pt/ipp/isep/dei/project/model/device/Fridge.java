package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;

public class Fridge extends CommonDevice implements Device, Metered {

    public Fridge(FridgeSpec fridgeSpec) {
        super(fridgeSpec);
    }

    public String getType() {
        return "Fridge";
    }

}
