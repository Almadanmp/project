package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Class that groups a number of Power Sources.
 */

public class PowerSourceList {

    private List<PowerSource> powerSources;

    public PowerSourceList() {
        this.powerSources = new ArrayList<>();
    }

    public List<PowerSource> getPowerSources() {
        return powerSources;
    }

    /**
     * Checks if a PowerSource is inside the PowerSourceList.
     *
     * @param powerSource power source received
     * @return true if contains false if not
     */
    boolean contains(PowerSource powerSource) {
        return powerSources.contains(powerSource);
    }

    /**
     * Adds a power source to the list. Duplicates are rejected.
     * @param powerSource is the power source we want to addWithoutPersisting.
     * @return is true if the power source was added, false if it wasn't.
     */
    public boolean add(PowerSource powerSource) {
        if (!(powerSources.contains(powerSource))) {
            powerSources.add(powerSource);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Getter (array of PowerSources)
     *
     * @return array of powerSources
     */
    PowerSource[] getElementsAsArray() {
        int sizeOfResultArray = powerSources.size();
        PowerSource[] result = new PowerSource[sizeOfResultArray];
        for (int i = 0; i < powerSources.size(); i++) {
            result[i] = powerSources.get(i);
        }
        return result;
    }

    /**
     * Method to create a new power source object from given input. If the power source already exists, return the
     * already existing one.
     * @param name is the name we want to give the power source.
     * @param maxPowerOutput is the maximum power the source can output.
     * @param maxEnergyStorage is the maximum energy the source can store.
     * @return is the newly created power source, or the already existing power source.
     */
    PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){
        for(PowerSource p : this.powerSources){
            String testName = p.getName();
            if(testName.equals(name)){
                return p;
            }
        }
        return new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }

    /**
     * Method to check if an instance of this class is equal to another object.
     * @param testObject is the object we want to check for equality.
     * @return is true if the object is a power source list with the same contents.
     */
    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof PowerSourceList)) {
            return false;
        }
        PowerSourceList list = (PowerSourceList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
