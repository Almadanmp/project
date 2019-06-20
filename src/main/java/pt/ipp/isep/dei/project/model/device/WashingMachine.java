package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WashingMachineSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.List;
import java.util.Objects;

public class WashingMachine extends CommonDeviceAttributes implements Device, Metered, Programmable {
    private final WashingMachineSpec deviceSpecs;
    private ProgramList programList;


    public WashingMachine(WashingMachineSpec washingMachineSpec) {
        super();
        this.deviceSpecs = washingMachineSpec;
        programList = new ProgramList();
    }

    public String getType() {
        return "Washing Machine";
    }


    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        return this.programList;
    }

    public void setProgramList(ProgramList plist) {
        this.programList = plist;
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
