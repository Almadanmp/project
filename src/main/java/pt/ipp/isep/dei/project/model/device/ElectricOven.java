package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.ElectricOvenSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;
import pt.ipp.isep.dei.project.model.device.program.VariableTimeProgram;

import java.util.List;
import java.util.Objects;

public class ElectricOven extends CommonDeviceAttributes implements Device, Metered, Programmable {
    private final ElectricOvenSpec deviceSpecsElectricOven;
    private ProgramList programListElectricOven;

    public ElectricOven(ElectricOvenSpec electricOvenSpec) {
        super();
        this.deviceSpecsElectricOven = electricOvenSpec;
        programListElectricOven = new ProgramList();
    }

    public String getType() {
        return "Electric Oven";
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.programListElectricOven;
    }

    public void setProgramList(ProgramList plist) {
        this.programListElectricOven = plist;
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

    // WRAPPER METHODS TO DEVICE SPECS

    public List<String> getAttributeNames() {
        return deviceSpecsElectricOven.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return deviceSpecsElectricOven.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return deviceSpecsElectricOven.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return deviceSpecsElectricOven.getAttributeUnit(attributeName);
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
