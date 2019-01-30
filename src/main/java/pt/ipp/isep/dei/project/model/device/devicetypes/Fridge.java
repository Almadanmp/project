package pt.ipp.isep.dei.project.model.device.devicetypes;

import pt.ipp.isep.dei.project.model.device.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    private static final String FREEZER_CAPACITY_STRING = "freezerCapacity";
    private static final String REFRIGERATOR_CAPACITY_STRING = "refrigeratorCapacity";
    private static final String ANNUAL_CONSUMPTION_STRING = "annualEnergyConsumption";

    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private String mType = "Fridge";

    /**
     * Empty Constructor.
     * This class uses an empty constructor so we can create a device of this type and after it choose the values to the
     * attributes.
     */
    public Fridge() {
    }

    public String getType() {
        return mType;
    }

    public double getConsumption() {
        return 0.0; //To be implemented later, not yet specified
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
    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case FREEZER_CAPACITY_STRING:
                return "Kg";
            case REFRIGERATOR_CAPACITY_STRING:
                return "Kg";
            case ANNUAL_CONSUMPTION_STRING:
                return "kWh";
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

