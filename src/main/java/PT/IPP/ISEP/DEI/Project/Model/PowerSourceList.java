package PT.IPP.ISEP.DEI.Project.Model;

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


}
