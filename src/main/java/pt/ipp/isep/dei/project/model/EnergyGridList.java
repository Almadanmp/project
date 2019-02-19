package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of energy Grids of a House.
 */

public class EnergyGridList {

    private List<EnergyGrid> energyGridList;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridList() {
        energyGridList = new ArrayList<>();
    }

    /**
     * Method adds an energy grid to the a energy grid list if the input grid isn't already contained in said list.
     *
     * @param energyGridToAdd the energy grid we want to add to the list.
     * @return returns true if the addition to the list is successful.
     */
    public boolean addGrid(EnergyGrid energyGridToAdd) {
        if (!(energyGridList.contains(energyGridToAdd))) {
            energyGridList.add(energyGridToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method returns an array containing all the energy grids contained in the list of energy grids.
     *
     * @return returns an array of energy grids.
     */
    public List<EnergyGrid> getEnergyGridList() {
        return energyGridList;
    }

    public EnergyGrid createEnergyGrid(String designation, double maxPower) {
        return new EnergyGrid(designation, maxPower);
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof EnergyGridList)) {
            return false;
        }
        EnergyGridList list = (EnergyGridList) testObject;
        return Arrays.equals(this.getEnergyGridList().toArray(), list.getEnergyGridList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
