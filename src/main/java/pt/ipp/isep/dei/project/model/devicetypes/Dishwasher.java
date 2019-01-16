package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher implements DeviceSpecs {

    private double mCapacity;

    public Dishwasher() {
    }

    public Dishwasher(double capacity) {
        this.mCapacity = capacity;
    }


    public DeviceType getType() {
        return DeviceType.DISHWASHER;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }


    public double getCapacity() {
        return this.mCapacity;
    }

    public void setCapacity(double capacity) {
        this.mCapacity = capacity;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("capacity");

        return result;
    }


    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "capacity":
                return mCapacity;
            default:
                return 0;
        }
    }


    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "capacity":
                if (attributeValue instanceof Double) {
                    this.mCapacity = (Double) attributeValue;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
