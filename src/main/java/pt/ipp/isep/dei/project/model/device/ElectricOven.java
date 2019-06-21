package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

public class ElectricOven extends CommonProgrammableDevice implements Device, Metered, Programmable {

    public ElectricOven(ElectricOvenSpec electricOvenSpec) {
        super(electricOvenSpec);
    }

    public String getType() {
        return "Electric Oven";
    }

    /**
     * Energy consumption = energy consumption in a program for a defined period of time (kWh)
     *
     * @param time    the desired time
     * @param program the desired program
     * @return the energy consumed in the given time
     */
    double getProgramEnergyConsumption(float time, VariableTimeProgram program) {
        return program.getNominalPower() * time;
    }
}
