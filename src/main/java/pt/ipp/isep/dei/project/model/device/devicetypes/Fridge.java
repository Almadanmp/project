package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private static final String FREEZER_CAPACITY_STRING = "freezerCapacity";
    private static final String REFRIGERATOR_CAPACITY_STRING = "refrigeratorCapacity";
    private static final String ANNUAL_CONSUMPTION_STRING = "annualEnergyConsumption";


    public Fridge() {
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
        result.add(FREEZER_CAPACITY_STRING);
        result.add(REFRIGERATOR_CAPACITY_STRING);
        result.add(ANNUAL_CONSUMPTION_STRING);

        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case FREEZER_CAPACITY_STRING:
                return mFreezerCapacity;
            case REFRIGERATOR_CAPACITY_STRING:
                return mRefrigeratorCapacity;
            case ANNUAL_CONSUMPTION_STRING:
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

