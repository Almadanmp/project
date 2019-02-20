package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;

public class FridgeSpec implements DeviceSpecs {

    public static final String FREEZER_CAPACITY = "Freezer Capacity";
    public static final String REFRIGERATOR_CAPACITY = "Refrigerator Capacity";
    public static final String ANNUAL_CONSUMPTION = "Annual Energy Consumption";

    private double freezerCapacity;
    private double refrigeratorCapacity;
    private double annualEnergyConsumption;


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
                return freezerCapacity;
            case REFRIGERATOR_CAPACITY:
                return refrigeratorCapacity;
            case ANNUAL_CONSUMPTION:
                return annualEnergyConsumption;
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
                    this.freezerCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case REFRIGERATOR_CAPACITY:
                if (attributeValue instanceof Double) {
                    this.refrigeratorCapacity = (Double) attributeValue;
                    return true;
                }
                return false;
            case ANNUAL_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.annualEnergyConsumption = (Double) attributeValue;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

}
