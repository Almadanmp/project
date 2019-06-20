package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.FreezerSpec;

import java.util.List;
import java.util.Objects;

public class Freezer extends CommonDeviceAttributes implements Device, Metered {

    private final FreezerSpec freezerSpecs;
    private double annualConsumption;


    public Freezer(FreezerSpec freezerSpec) {
        super();
        this.freezerSpecs = freezerSpec;
    }

    public String getType() {
        return "Freezer";
    }

    public void setAnnualConsumption(double annualConsumption) {
        this.annualConsumption = annualConsumption;
    }

    public double getAnnualConsumption() {
        return this.annualConsumption;
    }

    public double getDailyEnergyConsumption() {
        return this.annualConsumption / 365;
    }

    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return freezerSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return freezerSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return freezerSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return freezerSpecs.getAttributeUnit(attributeName);
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