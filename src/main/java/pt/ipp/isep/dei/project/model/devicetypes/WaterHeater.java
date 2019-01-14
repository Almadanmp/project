package pt.ipp.isep.dei.project.model.devicetypes;

import pt.ipp.isep.dei.project.model.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Metered;


public class WaterHeater implements DeviceSpecs, Metered {
    double mNominalPower;
    double mVolumeOfWater;
    double mHotWaterTemperature;
    double mColdWaterTemperature;
    double mPerformanceRatio;

    public WaterHeater(){}

    public WaterHeater(double volumeOfWater, double hotWaterTemperature, double coldWaterTemperature) {
        this.mVolumeOfWater = volumeOfWater;
        this.mHotWaterTemperature = hotWaterTemperature;
        this.mColdWaterTemperature = coldWaterTemperature;
        this.mPerformanceRatio = 0.9;
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

    public double getConsumption() {
        double specificHeatOfWater = 1.163;
        double dT = mHotWaterTemperature - mColdWaterTemperature;
        double result = specificHeatOfWater * mVolumeOfWater * dT * mPerformanceRatio;
        return result; //To be implemented by US752
    }

    public void setVolumeOfWater(double volumeOfWater) {
        this.mVolumeOfWater = volumeOfWater;
    }

    public void setHotWaterTemperature(double hotWaterTemperature) {
        this.mHotWaterTemperature = hotWaterTemperature;
    }

    public void setColdWaterTemperature(double coldWaterTemperature) {
        this.mColdWaterTemperature = coldWaterTemperature;
    }
    public void setPerformanceRatio(double performanceRatio) {
        this.mPerformanceRatio = performanceRatio;
    }
}   
