package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of energy Grids of a House.
 */

public class EnergyGridList {

    private List<EnergyGrid> energyGrids;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridList() {
        energyGrids = new ArrayList<>();
    }

    /**
     * Method adds an energy grid to the a energy grid list if the input grid isn't already contained in said list.
     *
     * @param energyGridToAdd the energy grid we want to add to the list.
     * @return returns true if the addition to the list is successful.
     */
    public boolean addGrid(EnergyGrid energyGridToAdd) {
        if (!(energyGrids.contains(energyGridToAdd))) {
            energyGrids.add(energyGridToAdd);
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
        return energyGrids;
    }

    public EnergyGrid createEnergyGrid(String designation, double maxPower) {
        return new EnergyGrid(designation, maxPower);
    }

    /** This method checks if the energy grid list is empty.
     * @return returns true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.energyGrids.isEmpty();
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
