package pt.ipp.isep.dei.project.model;

import java.util.Objects;

/**
 * Class that represents a PowerSource of an Energy Grid.
 */

public class PowerSource {

    private String mPowerSourceName;
    private double mMaxPowerOutput;
    private double mMaxEnergyStorage;

    public PowerSource(String powerSourceName, double maxPowerOutput, double maxEnergyStorage){
        this.mPowerSourceName = powerSourceName;
        this.mMaxPowerOutput = maxPowerOutput;
        this.mMaxEnergyStorage = maxEnergyStorage;
    }

    double getMaxEnergyStorage() {
        return mMaxEnergyStorage;
    }

    double getMaxPowerOutput() {
        return mMaxPowerOutput;
    }

    private String getPowerSourceName(){return this.mPowerSourceName;}

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
        return 1;
    }

}
