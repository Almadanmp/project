package pt.ipp.isep.dei.project.model;

import java.util.Objects;

/**
 * Class that represents a PowerSource of an Energy Grid.
 */

public class PowerSource {

    private String powerSourceName;
    private double maxPowerOutput;
    private double maxEnergyStorage;

    public PowerSource(String powerSourceName, double maxPowerOutput, double maxEnergyStorage){
        this.powerSourceName = powerSourceName;
        this.maxPowerOutput = maxPowerOutput;
        this.maxEnergyStorage = maxEnergyStorage;
    }

    double getMaxEnergyStorage() {
        return maxEnergyStorage;
    }

    double getMaxPowerOutput() {
        return maxPowerOutput;
    }

    private String getPowerSourceName(){return this.powerSourceName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PowerSource ps = (PowerSource) o;
        return Objects.equals(powerSourceName, ps.getPowerSourceName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
