package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.MicrowaveOvenSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.util.List;
import java.util.Objects;

public class MicrowaveOven extends CommonDeviceAttributes implements Device, Metered, Programmable {
    private final MicrowaveOvenSpec deviceSpecs;
    private ProgramList programList;


    public MicrowaveOven(MicrowaveOvenSpec microwaveOvenSpec) {
        super();
        this.deviceSpecs = microwaveOvenSpec;
        this.programList = new ProgramList();
    }

    public String getType() {
        return "Microwave Oven";
    }

    public void setProgramList(ProgramList plist) {
        this.programList = plist;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.programList;
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


    // WRAPPER METHODS TO DEVICE SPECS

    public List<String> getAttributeNames() {
        return deviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return deviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return deviceSpecs.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(this.getName(), device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
