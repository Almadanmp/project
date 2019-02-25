package pt.ipp.isep.dei.project.model;

/**
 * Represents all the metered objects.
 * Provides methods to get the nominal power for each one of the metered objects.
 * (e.g. of metered implementation: rooms, devices, houses.
 */

public interface Metered {

    /** Method to be implemented in every class that implements the Metered interface
     *
     * @return the nominal power of that object
     */

    double getNominalPower();

    double getEnergyConsumption(float time);

}
