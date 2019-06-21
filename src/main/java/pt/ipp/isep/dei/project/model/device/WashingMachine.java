package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

public class WashingMachine extends CommonProgrammableDevice implements Device, Metered, Programmable {

    public WashingMachine(WashingMachineSpec washingMachineSpec) {
        super(washingMachineSpec);
    }

    public String getType() {
        return "Washing Machine";
    }

}
