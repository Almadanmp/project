package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

public class MicrowaveOven extends CommonProgrammableDevice implements Device, Metered, Programmable {

    public MicrowaveOven(MicrowaveOvenSpec microwaveOvenSpec) {
        super(microwaveOvenSpec);
    }

    public String getType() {
        return "Microwave Oven";
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
