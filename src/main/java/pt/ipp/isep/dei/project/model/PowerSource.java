package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class PowerSource {

    private String mPowerSourceName;
    private double mMaxPowerOutput;
    private double mMaxEnergyStorage;
    private boolean mActivePowerSource;

    public PowerSource(String powerSourceName, double maxPowerOutput, double maxEnergyStorage){
        this.mPowerSourceName = powerSourceName;
        this.mMaxPowerOutput = maxPowerOutput;
        this.mMaxEnergyStorage = maxEnergyStorage;
    }

    public void setPowerSourceType(String powerSourceName){this.mPowerSourceName = powerSourceName;}

    public String getPowerSourceName(){return this.mPowerSourceName;}

    public void setMaxPowerOutput(double maxPowerOutput){this.mMaxPowerOutput = maxPowerOutput;}

    public double getMaxPowerOutput(){return this.mMaxPowerOutput;}

    public void setMaxEnergyStorage(double maxEnergyStorage){this.mMaxEnergyStorage = maxEnergyStorage;}

    public double getMaxEnergyStorage (){return this.mMaxEnergyStorage;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PowerSource ps = (PowerSource) o;
        return Objects.equals(mPowerSourceName, ps.getPowerSourceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mPowerSourceName);
    }

}
