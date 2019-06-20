package pt.ipp.isep.dei.project.model.device;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.devicespecs.WineCoolerSpec;

import java.util.List;
import java.util.Objects;

public class WineCooler extends CommonDeviceAttributes implements Device, Metered {

    private final WineCoolerSpec deviceSpecs;
    private double annualConsumption;


    public WineCooler(WineCoolerSpec wineCoolerSpec) {
        super();
        this.deviceSpecs = wineCoolerSpec;
    }

    public String getType() {
        return "WineCooler";
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


