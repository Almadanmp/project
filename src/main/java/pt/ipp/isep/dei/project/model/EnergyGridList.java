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
     * @param energyGridToAdd the energy grid we want to addWithoutPersisting to the list.
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
     * This method creates a new EnergyGrid using its constructor.
     *
     * @param designation - designation of the to be created EnergyGrid.
     * @param maxPower    - maximum power of the to be created EnergyGrid.
     * @return a new EnergyGrid or an existing one if the designation is the same.
     */
    EnergyGrid createEnergyGrid(String designation, double maxPower) {
        for (EnergyGrid e : this.energyGrids) {
            String name = e.getName();
            if (name.equals(designation)) {
                return e;
            } }
        return new EnergyGrid(designation, maxPower);
    }

    /**
     * This method checks if the energy grid list is empty.
     *
     * @return returns true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.energyGrids.isEmpty();
    }

    /**
     * This method checks the energy grid list size.
     *
     * @return returns the list size as int.
     */
    public int size() {
        return this.energyGrids.size();
    }

    /**
     * This method receives an index as parameter and gets energy grid from energy grid list.
     *
     * @param index the index of the energy grid.
     * @return returns Energy grid that corresponds to index.
     */
    public EnergyGrid get(int index) {
        if (this.energyGrids.isEmpty()) {
            throw new IndexOutOfBoundsException("The energy grid list is empty.");
        }
        return this.energyGrids.get(index);
    }

    /**
     * Getter (array of energy grids)
     *
     * @return array of energy grids
     */
    public EnergyGrid[] getElementsAsArray() {
        int size = this.size();
        EnergyGrid[] result = new EnergyGrid[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.energyGrids.get(i);
        }
        return result;
    }

    /**
     * Method that builds a string of every grid contained in the grid list, using their name and maximum contracted power,
     * and assigning an index to each one of them.
     * @return a string that is the list of all grids present in the grid list.
     */
    public String buildString() {
        String mStringEnhancer = "---------------\n";
        StringBuilder result = new StringBuilder(mStringEnhancer);
        if (this.energyGrids.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < this.energyGrids.size(); i++) {
            EnergyGrid aux = this.energyGrids.get(i);
            result.append(i).append(") Designation: ").append(aux.getName()).append(" | ");
            result.append("Max Power: ").append(aux.getMaxContractedPower()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
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
