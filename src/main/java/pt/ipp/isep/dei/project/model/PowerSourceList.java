package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Class that groups a number of Power Sources.
 */

public class PowerSourceList {

    private List<PowerSource> powerSources;

    PowerSourceList() {
        this.powerSources = new ArrayList<>();
    }

    /**
     * Checks if a PowerSource is inside the PowerSource List
     *
     * @param powerSource power source received
     * @return true if contains false if not
     */
    boolean contains(PowerSource powerSource) {
        return powerSources.contains(powerSource);
    }

    boolean add(PowerSource powerSource) {
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


    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){
        for(PowerSource p : this.powerSources){
            String testName = p.getPowerSourceName();
            if(testName.equals(name)){
                return p;
            }
        }
        return new PowerSource(name, maxPowerOutput, maxEnergyStorage);
    }


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
