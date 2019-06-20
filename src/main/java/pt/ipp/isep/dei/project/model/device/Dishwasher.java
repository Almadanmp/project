package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.DishwasherSpec;
import pt.ipp.isep.dei.project.model.device.program.ProgramList;
import pt.ipp.isep.dei.project.model.device.program.Programmable;

import java.util.List;
import java.util.Objects;

public class Dishwasher extends CommonDeviceAttributes implements Device, Metered, Programmable {
    private final DishwasherSpec deviceSpecs;
    private ProgramList programList;

    public Dishwasher(DishwasherSpec dishwasherSpec) {
        super();
        this.deviceSpecs = dishwasherSpec;
        this.programList = new ProgramList();
    }

    public String getType() {
        return "Dishwasher";
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
        Device dishWasher = (Device) o;
        return Objects.equals(this.getName(), dishWasher.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
