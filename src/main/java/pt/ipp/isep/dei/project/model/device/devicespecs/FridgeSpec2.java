package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class FridgeSpec2 implements DeviceSpecs2 {

    public static final String FREEZER_CAPACITY = "Freezer Capacity";
    public static final String REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    public static final String ANNUAL_CONSUMPTION = "Annual Energy Consumption";

    private double mFreezerCapacity;
    private double mRefrigeratorCapacity;
    private double mAnnualEnergyConsumption;


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(FREEZER_CAPACITY);
        result.add(REFRIGERATOR_CAPACITY);
        result.add(ANNUAL_CONSUMPTION);
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
                   default:
                return false;
        }
    }

}
