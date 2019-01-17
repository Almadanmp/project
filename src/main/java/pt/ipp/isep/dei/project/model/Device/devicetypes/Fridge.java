package pt.ipp.isep.dei.project.model.Device.devicetypes;

import pt.ipp.isep.dei.project.model.Device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private String mFreezerCapacityString = "freezerCapacity";
    private String mRefrigeratorCapacityString = "refrigeratorCapacity";
    private String mAnualConsumptionString = "annualEnergyConsumption";

    public Fridge() {
    }

    public Fridge(double mFreezerCapacity, double mRefrigeratorCapacity, double annualEnergyConsumption) {
        this.mFreezerCapacity = mFreezerCapacity;
        this.mRefrigeratorCapacity = mRefrigeratorCapacity;
        this.mAnnualEnergyConsumption = annualEnergyConsumption;
    }

    public DeviceType getType() {
        return DeviceType.FRIDGE;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
    }

    public double getFreezerCapacity() {
        return this.mFreezerCapacity;
    }

    public void setFreezerCapacity(double freezerCapacity) {
        this.mFreezerCapacity = freezerCapacity;
    }

    public double getRefrigeratorCapacity() {
        return this.mRefrigeratorCapacity;
    }

    public void setRefrigeratorCapacity(double refrigeratorCapacity) {
        this.mRefrigeratorCapacity = refrigeratorCapacity;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(mFreezerCapacityString);
        result.add(mRefrigeratorCapacityString);
        result.add(mAnualConsumptionString);

        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "freezerCapacity":
                return mFreezerCapacity;
            case "refrigeratorCapacity":
                return mRefrigeratorCapacity;
            case "annualEnergyConsumption":
                return mAnnualEnergyConsumption;
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case "freezerCapacity":
                if (attributeValue instanceof Double) {
                    this.mFreezerCapacity = (Double) attributeValue;
                    return true;
                } else {
                    return false;
                }
            case "refrigeratorCapacity":
                if (attributeValue instanceof Double) {
                    this.mRefrigeratorCapacity = (Double) attributeValue;
                    return true;
                } else {
                    return false;
                }
            case "annualEnergyConsumption":
                if (attributeValue instanceof Double) {
                    this.mAnnualEnergyConsumption = (Double) attributeValue;
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}

