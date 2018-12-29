package pt.ipp.isep.dei.project.model;

import java.util.*;

/**
 * Represents a List of PowerSources.
 */

public class PowerSourceList {

    private List<PowerSource> mPowerSourceList;

    public PowerSourceList() {
        this.mPowerSourceList = new ArrayList<>();
    }

    /**
     * Constructor of an ArrayList of Arrays.
     *
     * @param powerSourceToAdd
     */


    public PowerSourceList(PowerSource[] powerSourceToAdd) {
        mPowerSourceList = new ArrayList<>();
        for (int i = 0; i < powerSourceToAdd.length; i++) {
            mPowerSourceList.add(powerSourceToAdd[i]);
        }
    }

    /**
     * Constructor to always create an Array of PowerSources.
     *
     * @param powerSourceToAdd
     */

    public PowerSourceList(PowerSource powerSourceToAdd) {
        mPowerSourceList = new ArrayList<>();
        mPowerSourceList.add(powerSourceToAdd);
    }

    /**
     * Method to Add a PowerSource only if it's not contained in the list already.
     */

    public boolean addPowerSource(PowerSource powerSourceToAdd) {
        if(!(mPowerSourceList.contains(powerSourceToAdd))){
            mPowerSourceList.add(powerSourceToAdd);
        }
        return false;
    }

    /**
     * Checks if a PowerSource is inside the PowerSource List
     *
     * @param powerSource
     * @return
     */
    public boolean containsPowerSource(PowerSource powerSource) {
        return mPowerSourceList.contains(powerSource);
    }

    /**
     * Getter (array of PowerSources)
     *
     * @return array of powerSources
     */
    public PowerSource[] getPowerSources() {
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
    public List<PowerSource> getPowerSourceList() {
        return this.mPowerSourceList;
    }

    /**
     * Removes a powerSources from the powerSource List
     *
     * @param powerSourceToRemove
     */
    public void removePowerSource(PowerSource powerSourceToRemove) {
        mPowerSourceList.remove(powerSourceToRemove);
    }

    private boolean checkIfListInvalid() {
        return (this.mPowerSourceList.isEmpty());
    }

    public boolean checkIfListIsValid() {
        return !mPowerSourceList.isEmpty();
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
