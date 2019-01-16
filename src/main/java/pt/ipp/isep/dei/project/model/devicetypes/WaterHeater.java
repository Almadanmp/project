package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;

import java.util.ArrayList;
import java.util.List;


public class WaterHeater implements DeviceSpecs {
    //private static final String ATTRIBUTE_VOLUME_OF_WATER = "volumeOfWater";
    //private static final String ATTRIBUTE_HOT_WATER_TEMP = "hotWaterTemperature";
    //private static final String ATTRIBUTE_COLD_WATER_TEMP = "coldWaterTemperature";
    //private static final String ATTRIBUTE_PERFORMANCE_RATIO = "performanceRatio";

    double mVolumeOfWater;
    double mHotWaterTemperature;
    double mPerformanceRatio;
    double mColdWaterTemperature;
    double mVolumeOfWaterToHeat;

    public WaterHeater() {
    }


    public WaterHeater(double volumeOfWater, double hotWaterTemperature, double mPerformanceRatio) {
        this.mVolumeOfWater = volumeOfWater;
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mPerformanceRatio = mPerformanceRatio;
    }


    public DeviceType getType() {
        return DeviceType.WATER_HEATER;
    }

    public double getConsumption() {
        double specificHeatOfWater = 1.163;
        double dT = mHotWaterTemperature - mColdWaterTemperature;
        return specificHeatOfWater * mVolumeOfWater * dT * mPerformanceRatio; //To be implemented by US752
    }

    public double getVolumeWater() {
        return this.mVolumeOfWater;
    }

    public void setVolumeOfWater(double volumeOfWater) {
        this.mVolumeOfWater = volumeOfWater;
    }


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("volumeOfWater");
        result.add("hotWaterTemperature");
        result.add("performanceRatio");
        result.add("coldWaterTemperature");
          result.add("volumeOfWaterToHeat");

        return result;
    }


    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "volumeOfWater":
                return mVolumeOfWater;
            case "hotWaterTemperature":
                return mHotWaterTemperature;
            case "performanceRatio":
                return mPerformanceRatio;
            case "coldWaterTemperature":
                return mColdWaterTemperature;
            case "volumeOfWaterToHeat":
                return mVolumeOfWaterToHeat;
            default:
                return 0;
        }
    }

    public boolean setAttributeValue(String attributeName, double attributeValue) {
        switch (attributeName) {
            case "volumeOfWater":
                this.mVolumeOfWater = attributeValue;
                return true;
            case "hotWaterTemperature":
                this.mHotWaterTemperature = attributeValue;
                return true;
            case "performanceRatio":
                this.mPerformanceRatio = attributeValue;
                return true;
            case "coldWaterTemperature":
                this.mColdWaterTemperature = attributeValue;
                return true;
            case "volumeOfWaterToHeat":
                this.mVolumeOfWaterToHeat = attributeValue;
                return true;
            default:
                return false;
        }
    }
}
