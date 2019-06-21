package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.StoveSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

public class Stove extends CommonProgrammableDevice implements Device, Metered, Programmable {

    public Stove(StoveSpec stoveSpec) {
        super(stoveSpec);
    }

    public String getType() {
        return "Stove";
    }

    /**
     * Energy consumption = energy consumption in a program for a defined period of time (kWh)
     *
     * @param time    the desired time
     * @param program the desired program
     * @return the energy consumed in the given time
     */

    double getProgramConsumption(float time, VariableTimeProgram program) {
        return program.getNominalPower() * time;
    }

}

