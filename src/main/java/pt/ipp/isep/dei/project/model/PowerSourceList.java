package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Class that groups a number of Power Sources.
 */

public class PowerSourceList {

    private List<PowerSource> powerSourceList;

    PowerSourceList() {
        this.powerSourceList = new ArrayList<>();
    }

    /**
     * Checks if a PowerSource is inside the PowerSource List
     *
     * @param powerSource power source received
     * @return true if contains false if not
     */
    boolean containsPowerSource(PowerSource powerSource) {
        return powerSourceList.contains(powerSource);
    }

    boolean addPowerSource(PowerSource powerSource) {
        if (!(powerSourceList.contains(powerSource))) {
            powerSourceList.add(powerSource);
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
    PowerSource[] getPowerSources() {
        int sizeOfResultArray = powerSourceList.size();
        PowerSource[] result = new PowerSource[sizeOfResultArray];
        for (int i = 0; i < powerSourceList.size(); i++) {
            result[i] = powerSourceList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of powerSources)
     *
     * @return list of powerSources
     */
    List<PowerSource> getPowerSourceList() {
        return this.powerSourceList;
    }

    public PowerSource createPowerSource(String name, double maxPowerOutput, double maxEnergyStorage){
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
        return Arrays.equals(this.getPowerSources(), list.getPowerSources());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
