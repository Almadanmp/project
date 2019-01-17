package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private final String mFreezerCapacityString = "freezerCapacity";
    private final String mRefrigeratorCapacityString = "refrigeratorCapacity";
    private final String mAnnualConsumptionString = "annualEnergyConsumption";

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

    double getFreezerCapacity() {
        return this.mFreezerCapacity;
    }

    void setFreezerCapacity(double freezerCapacity) {
        this.mFreezerCapacity = freezerCapacity;
    }

    double getRefrigeratorCapacity() {
        return this.mRefrigeratorCapacity;
    }

    void setRefrigeratorCapacity(double refrigeratorCapacity) {
        this.mRefrigeratorCapacity = refrigeratorCapacity;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(mFreezerCapacityString);
        result.add(mRefrigeratorCapacityString);
        result.add(mAnnualConsumptionString);

        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case mFreezerCapacityString:
                return mFreezerCapacity;
            case mRefrigeratorCapacityString:
                return mRefrigeratorCapacity;
            case mAnnualConsumptionString:
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
                }
                    return false;
            case "refrigeratorCapacity":
                if (attributeValue instanceof Double) {
                    this.mRefrigeratorCapacity = (Double) attributeValue;
                    return true;
                }
                    return false;
            case "annualEnergyConsumption":
                if (attributeValue instanceof Double) {
                    this.mAnnualEnergyConsumption = (Double) attributeValue;
                    return true;
                }
                    return false;
            default:
                return false;
        }
    }
}

