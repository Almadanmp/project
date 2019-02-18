package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Class that groups a number of Power Sources.
 */

public class PowerSourceList {

    private List<PowerSource> mPowerSourceList;

    PowerSourceList() {
        this.mPowerSourceList = new ArrayList<>();
    }

    /**
     * Checks if a PowerSource is inside the PowerSource List
     *
     * @param powerSource power source received
     * @return true if contains false if not
     */
    boolean containsPowerSource(PowerSource powerSource) {
        return mPowerSourceList.contains(powerSource);
    }

    boolean addPowerSource(PowerSource powerSource) {
        if (!(mPowerSourceList.contains(powerSource))) {
            mPowerSourceList.add(powerSource);
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
        int sizeOfResultArray = mPowerSourceList.size();
        PowerSource[] result = new PowerSource[sizeOfResultArray];
        for (int i = 0; i < mPowerSourceList.size(); i++) {
            result[i] = mPowerSourceList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of powerSources)
     *
     * @return list of powerSources
     */
    List<PowerSource> getPowerSourceList() {
        return this.mPowerSourceList;
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
