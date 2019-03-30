package pt.ipp.isep.dei.project.model;

import java.util.Objects;

/**
 * Class that represents a PowerSource of an Energy Grid.
 */

public class PowerSource {

    private String powerSourceName;
    private double maxPowerOutput;
    private double maxEnergyStorage;

    /**
     * Standard constructor for power sources.
     * @param powerSourceName is the name we want to give the power source.
     * @param maxPowerOutput is the maximum power the power source can output.
     * @param maxEnergyStorage is the maximum energy the power source is capable of storing.
     */

    public PowerSource(String powerSourceName, double maxPowerOutput, double maxEnergyStorage){
        this.powerSourceName = powerSourceName;
        this.maxPowerOutput = maxPowerOutput;
        this.maxEnergyStorage = maxEnergyStorage;
    }

    public double getMaxEnergyStorage() {
        return maxEnergyStorage;
    }

    public double getMaxPowerOutput() {
        return maxPowerOutput;
    }

    public String getName(){return this.powerSourceName;}

    /**
     * Method to check if an instance of this class is equal to another object.
     * @param o is the object we want to check for equality.
     * @return is true if the object is a power source with the same name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PowerSource ps = (PowerSource) o;
        return Objects.equals(powerSourceName, ps.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
