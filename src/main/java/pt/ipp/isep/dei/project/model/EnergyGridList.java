package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of energy Grids of a House.
 */

public class EnergyGridList {

    private List<EnergyGrid> mEnergyGridList;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridList() {
        mEnergyGridList = new ArrayList<>();
    }

    /**
     * Method adds an energy grid to the a energy grid list if the input grid isn't already contained in said list.
     * @param energyGridToAdd the energy grid we want to add to the list.
     * @return returns true if the addition to the list is successful.
     */
    public boolean addGrid(EnergyGrid energyGridToAdd) {
        if (!(mEnergyGridList.contains(energyGridToAdd))) {
            mEnergyGridList.add(energyGridToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method prints the names of all the energy grids contained in the list of energy grids.
     * @return returns true if the addition to the list is successful.
     */
    String buildEnergyGridListString() {
        StringBuilder finalString = new StringBuilder();
        String emptyList = "The list is empty.";
        if (mEnergyGridList.isEmpty()) {
            return emptyList;
        }
        finalString.append("Energy grid list:");
        for (EnergyGrid energyGrid : mEnergyGridList) {
            finalString.append(" \n" + "-").append(energyGrid.getName()).append(";");
        }
        return finalString.toString();
    }

    /**
     * Method returns an array containing all the energy grids contained in the list of energy grids.
     * @return returns an array of energy grids.
     */
    public List<EnergyGrid> getEnergyGridList() {
        return mEnergyGridList;
    }

    /**
     * Method returns an array of integers with the index of energy grids that match the input name.
     * @param name is the name of the grid.
     * @return returns an array of integers.
     */
    public List<Integer> matchGridListElementsByString(String name) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < mEnergyGridList.size(); i++) {
            if (mEnergyGridList.get(i).getName().equals(name)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Method returns a string with the name, nominal power and list of power sources.
     * @param indexes is an array on integers
     * @return returns a string with the name, nominal power and list of power sources.
     */
    public String buildElementsByIndexString(List<Integer> indexes) {
        StringBuilder result = new StringBuilder();
        for (int pos : indexes) {
            result.append(pos).append(") ").append(mEnergyGridList.get(pos).getName()).append(", ").append(mEnergyGridList.get(pos).getNominalPower()).append(", ").append(mEnergyGridList.get(pos).getListPowerSources()).append(".\n");
        }
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
        return Arrays.equals(this.getEnergyGridList().toArray(), list.getEnergyGridList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
