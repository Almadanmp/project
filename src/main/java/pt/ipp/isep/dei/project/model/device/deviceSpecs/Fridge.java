package pt.ipp.isep.dei.project.model.device.deviceSpecs;

import java.util.ArrayList;
import java.util.List;

public class Fridge implements DeviceSpecs {
    public static final String FREEZER_CAPACITY = "freezerCapacity";
    public static final String REFRIGERATOR_CAPACITY = "refrigeratorCapacity";
    public static final String ANNUAL_CONSUMPTION = "annualEnergyConsumption";
    public static final String NOMINAL_POWER = "nominal power";

    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;
    private double mNominalPower;
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
        result.add(FREEZER_CAPACITY);
        result.add(REFRIGERATOR_CAPACITY);
        result.add(ANNUAL_CONSUMPTION);
        result.add(NOMINAL_POWER);

        return result;
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case FREEZER_CAPACITY:
                return mFreezerCapacity;
            case REFRIGERATOR_CAPACITY:
                return mRefrigeratorCapacity;
            case ANNUAL_CONSUMPTION:
                return mAnnualEnergyConsumption;
            default:
                return 0;
        }
    }
    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case FREEZER_CAPACITY:
                return "Kg";
            case REFRIGERATOR_CAPACITY:
                return "Kg";
            case ANNUAL_CONSUMPTION:
                return "kWh";
            case NOMINAL_POWER:
                return "kW";
            default:
                return 0;
        }
    }

        public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case FREEZER_CAPACITY:
                if (attributeValue instanceof Double) {
                    this.mFreezerCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case REFRIGERATOR_CAPACITY:
                if (attributeValue instanceof Double) {
                    this.mRefrigeratorCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case ANNUAL_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.mAnnualEnergyConsumption = (Double) attributeValue;
                    return true;
                }
                return false;
            case NOMINAL_POWER:
                if (attributeValue instanceof Double) {
                    this.mNominalPower = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}

