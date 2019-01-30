package pt.ipp.isep.dei.project.model.device.devicespecs;

import java.util.ArrayList;
import java.util.List;


public class WaterHeaterSpec implements DeviceSpecs {
    public static final String ATTRIBUTE_VOLUME_OF_WATER = "Volume Of Water";
    public static final String ATTRIBUTE_HOT_WATER_TEMP = "Hot Water Temperature";
    public static final String ATTRIBUTE_COLD_WATER_TEMP = "Cold Water Temperature";
    public static final String ATTRIBUTE_PERFORMANCE_RATIO = "Performance Ratio";
    public static final String ATTRIBUTE_VOLUME_OF_WATER_HEAT = "Volume Of Water To Heat";
    public static final String NOMINAL_POWER = "nominal power";

    private Double mVolumeOfWater;
    private Double mHotWaterTemperature;
    private Double mPerformanceRatio;
    private Double mColdWaterTemperature;
    private Double mVolumeOfWaterToHeat;
    private String mType = "WaterHeater";
    private Double mNominalPower;


    public WaterHeaterSpec() {
        mColdWaterTemperature = 0.0;
        mVolumeOfWaterToHeat = 0.0;
    }

    public String getType() {
        return mType;
    }

    /**
     * Estimate energy consumption for a water heater.
     * It is calculated by the following equation:
     * Energy consumption = C*V*dT*PR (kWh)
     * C -> Specific heat of water = 1,163 Wh/kg°C
     * V -> Volume of water to heat (water consumption in litres/min)
     * Dt -> difference in temperature = hot water temperature – cold watertemperature
     * PR -> performance ratio (typically 0.9)
     * <p>
     * When the temperature of ColdWater is above the HotWaterTemperature, there will be no energy consumption, so we
     * return 0.
     *
     * @return returns an estimate energy consumption for a water heater
     */

    public double getConsumption() {
        if (mColdWaterTemperature >= mHotWaterTemperature) {
            return -1;
        }

        double dT = mHotWaterTemperature - mColdWaterTemperature;
        double volForMinute = mVolumeOfWaterToHeat / 1440; //calculate v in liters per minute
        double specificHeatOfWater = 1.163 / 1000;
        return specificHeatOfWater * volForMinute * dT * mPerformanceRatio * 60;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(ATTRIBUTE_VOLUME_OF_WATER);
        result.add(ATTRIBUTE_HOT_WATER_TEMP);
        result.add(ATTRIBUTE_PERFORMANCE_RATIO);
        result.add(NOMINAL_POWER);

        return result;
    }

    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER:
                return mVolumeOfWater;
            case ATTRIBUTE_HOT_WATER_TEMP:
                return mHotWaterTemperature;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return mPerformanceRatio;
            case ATTRIBUTE_COLD_WATER_TEMP:
                return mColdWaterTemperature;
            case ATTRIBUTE_VOLUME_OF_WATER_HEAT:
                return mVolumeOfWaterToHeat;
            case NOMINAL_POWER:
                return mNominalPower;
            default:
                return 0.0;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER:
                return "L";
            case ATTRIBUTE_HOT_WATER_TEMP:
                return "ºC";
            case ATTRIBUTE_PERFORMANCE_RATIO:
                return "";
            case ATTRIBUTE_COLD_WATER_TEMP:
                return "ºC";
            case ATTRIBUTE_VOLUME_OF_WATER_HEAT:
                return "L";
            case NOMINAL_POWER:
                return "kW";
            default:
                return false;
        }
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        if (attributeName == null) {
            return false;
        }
        switch (attributeName) {
            case ATTRIBUTE_VOLUME_OF_WATER:
                if (attributeValue instanceof Double) {
                    this.mVolumeOfWater = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_HOT_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.mHotWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_PERFORMANCE_RATIO:
                if (attributeValue instanceof Double) {
                    this.mPerformanceRatio = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_COLD_WATER_TEMP:
                if (attributeValue instanceof Double) {
                    this.mColdWaterTemperature = (Double) attributeValue;
                    return true;
                }
                return false;
            case ATTRIBUTE_VOLUME_OF_WATER_HEAT:
                if (attributeValue instanceof Double) {
                    this.mVolumeOfWaterToHeat = (Double) attributeValue;
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
