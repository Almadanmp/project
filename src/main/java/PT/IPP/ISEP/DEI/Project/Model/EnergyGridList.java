package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnergyGridList {

    private List<EnergyGrid> mEnergyGridList;

    public EnergyGridList() {
        mEnergyGridList = new ArrayList<>();
    }
    public EnergyGridList(EnergyGrid energyGridToAdd) {
        mEnergyGridList = new ArrayList<>();
        mEnergyGridList.add(energyGridToAdd);
    }
    public boolean addEnergyGridToEnergyGridList(EnergyGrid energyGridToAdd) {
        if(!(mEnergyGridList.contains(energyGridToAdd))) {
            mEnergyGridList.add(energyGridToAdd);
            return true;
        }
        return false;
    }
    public EnergyGrid matchEnergyGrid(String gridToMatch){
        for (EnergyGrid g: mEnergyGridList){
            if (g.getmName().equals(gridToMatch)){
                return g;
            }
        }
        return null;
    }
    public boolean checkIfListIsValid() {
        return !mEnergyGridList.isEmpty();
    }

    public String printEnergyGridList() {
        StringBuilder finalString = new StringBuilder();
        String emptyList= "The list is empty.";
        if(mEnergyGridList.isEmpty()) {
            return emptyList;
        }
        finalString.append("Room List:");
        for(EnergyGrid energyGrid: mEnergyGridList) {
            finalString.append(" \n" + "-").append(energyGrid.getmName()).append(";");
        }
        return finalString.toString();
    }

    public List<EnergyGrid> getEnergyGridList() {
        return mEnergyGridList;
    }
    public boolean seeIfContainsEnergyGrid (String energyGridName){
        for (EnergyGrid eg: mEnergyGridList){
            if (eg.getmName().equals(energyGridName)){
                return true;
            }
        }
        return false;
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
