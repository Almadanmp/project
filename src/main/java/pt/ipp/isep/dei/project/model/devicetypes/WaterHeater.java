package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;

import java.util.ArrayList;
import java.util.List;


public class WaterHeater implements DeviceSpecs, Metered {
    //private static final String ATTRIBUTE_NOMINAL_POWER = "nominalPower";
    //private static final String ATTRIBUTE_VOLUME_OF_WATER = "volumeOfWater";
    //private static final String ATTRIBUTE_HOT_WATER_TEMP = "hotWaterTemperature";
    //private static final String ATTRIBUTE_COLD_WATER_TEMP = "coldWaterTemperature";
    //private static final String ATTRIBUTE_PERFORMANCE_RATIO = "performanceRatio";

    double mNominalPower;
    double mVolumeOfWater;
    double mHotWaterTemperature;
    double mColdWaterTemperature;
    double mPerformanceRatio;

    public WaterHeater() {
    }


    public WaterHeater(double volumeOfWater, double hotWaterTemperature, double mPerformanceRatio) {
        this.mVolumeOfWater = volumeOfWater;
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mPerformanceRatio = mPerformanceRatio;
    }


    public WaterHeater(double volumeOfWater, double hotWaterTemperature, double coldWaterTemperature,
                       double performanceRatio) {
        this.mVolumeOfWater = volumeOfWater;
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mColdWaterTemperature = coldWaterTemperature;
        this.mPerformanceRatio = performanceRatio;
    }

    public DeviceType getType() {
        return DeviceType.WATER_HEATER;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public double getConsumption() {
        double specificHeatOfWater = 1.163;
        double dT = mHotWaterTemperature - mColdWaterTemperature;
        double result = specificHeatOfWater * mVolumeOfWater * dT * mPerformanceRatio;
        return result; //To be implemented by US752
    }

    public double getVolumeWater() {
        return this.mVolumeOfWater;
    }

    public void setVolumeOfWater(double volumeOfWater) {
        this.mVolumeOfWater = volumeOfWater;
    }


    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add("nominalPower");
        result.add("volumeOfWater");
        result.add("hotWaterTemperature");
        result.add("coldWaterTemperature");
        result.add("performanceRatio");

        return result;
    }


    public double getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "nominalPower":
                return mNominalPower;
            case "volumeOfWater":
                return mVolumeOfWater;
            case "hotWaterTemperature":
                return mHotWaterTemperature;
            case "coldWaterTemperature":
                return mColdWaterTemperature;
            case "performanceRatio":
                return mPerformanceRatio;
            default:
                return 0;
        }
    }


    public boolean setAttributeValue(String attributeName, double attributeValue) {
        switch (attributeName) {
            case "nominalPower":
                this.mNominalPower = attributeValue;
                return true;
            case "volumeOfWater":
                this.mVolumeOfWater = attributeValue;
                return true;
            case "hotWaterTemperature":
                this.mHotWaterTemperature = attributeValue;
                return true;
            case "coldWaterTemperature":
                this.mColdWaterTemperature = attributeValue;
                return true;
            case "performanceRatio":
                this.mPerformanceRatio = attributeValue;
                return true;
            default:
                return false;
        }
    }
}
