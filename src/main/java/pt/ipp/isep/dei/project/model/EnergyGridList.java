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

    public EnergyGrid createEnergyGrid(String designation, double maxPower) {
        return new EnergyGrid(designation, maxPower);
    }

    /** This method checks if the energy grid list is empty.
     * @return returns true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.energyGrids.isEmpty();
    }

    /** This method checks the energy grid list size.
     * @return returns the list size as int.
     */
    public int size() {
        return this.energyGrids.size();
    }

    /** This method receives an index as parameter and gets energy grid from energy grid list.
     * @param index the index of the energy grid.
     * @return returns Energy grid that corresponds to index.
     */
    public EnergyGrid get(int index) {
        return this.energyGrids.get(index);
    }

    public EnergyGrid[] getElementsAsArray(){
        int size = this.size();
        EnergyGrid [] result = new EnergyGrid[size];
        for (int i = 0; i < size; i++){
            result[i] = this.energyGrids.get(i);
        }
        return result;
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
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
