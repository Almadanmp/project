package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that groups a number of Types of Geographical Areas.
 */

public class TypeAreaList {
    private List<TypeArea> mTypeAreaList;
    private String mResultLookEnhancer = "---------------\n";

    public TypeAreaList() {
        mTypeAreaList = new ArrayList<>();
    }

    /**
     * This method prints all the Geographic Area Types in a TypeAreaList.
     * @return the types of geographic areas in a list
     */
    String buildTypeAreaListString() {
        StringBuilder finalString = new StringBuilder("\nArea Types List:\n");
        if(mTypeAreaList.isEmpty()) {
            finalString.append("\n|||| List is Empty ||||\nAdd types to list first");
        }
        else { for (TypeArea tipo : mTypeAreaList)
            finalString.append("\n").append("-").append(tipo.getTypeOfGeographicArea()).append(";");}
        return finalString.toString();
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     * @param name String of the new Area Type that one wishes to create and add to a list.
     * @return true or false depending on if it adds the type to the list or not.
     */
    public boolean newTAG(String name) {
        if (name == null || name.isEmpty() || name.matches(".*\\d+.*")) {
            return false;
        }
        TypeArea tipo = new TypeArea(name);
        return addTypeArea(tipo);
    }

    /**
     * This method adds a previously stated Area Type to a List of Area Types.
     * @param type Type of Geographic Area one wishes to add to a list.
     * @return true or false depending on the list containing or not the type input already.
     */
    public boolean addTypeArea(TypeArea type) {
        if (!mTypeAreaList.contains(type)){
            mTypeAreaList.add(type);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * The method generates a list of integers with the indexes of all the Geographic Areas e a given list matching a string
     * @param input is the string we're going to look for in the list of Geographic Areas.
     * @return is a list of integers that contains the indexes of all the Geographic Areas in the given list whose name
     * matches the given string.
     */

    public List<Integer>  matchGeographicAreaTypeIndexByString(String input){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < mTypeAreaList.size(); i++){
            if (mTypeAreaList.get(i).getTypeOfGeographicArea().equals(input)){
                result.add(i);
            }
        }
        return result;
    }
    /** This method prints a string of the indexes of all the individual members of the geoArea list.
     *
     * @param indexes is a list of all the indexes in a list where relevant objects are.
     * @return builds a string of all the individual members of the geoArea list.
     */

    public String buildGATypeElementsByIndexString(List<Integer> indexes){
        StringBuilder result = new StringBuilder(mResultLookEnhancer);
        for (Integer index : indexes) {
            int pos = index;
            result.append(index).append(") ").append(mTypeAreaList.get(pos).buildTypeGeographicAreaString());
        }
        result.append(mResultLookEnhancer);
        return result.toString();
    }
    /** This method builds a string of all the individual members of the geoAreaType list.
     *
     * @param typeAreaList is a list of all the types a Geographical Area may have.
     * @return builds a string of all the individual members of the geoAreaType list.
     */


    public String buildGATypeWholeListString(TypeAreaList typeAreaList) {
        StringBuilder result = new StringBuilder(mResultLookEnhancer);
        for (int i = 0; i < typeAreaList.getTypeAreaList().size(); i++) {
            TypeArea aux = typeAreaList.getTypeAreaList().get(i);
            result.append(i).append(") Name: ").append(aux.getTypeOfGeographicArea()).append(" \n");
        }
        result.append(mResultLookEnhancer);
        return result.toString();
    }

    /**
     * Getter of the Type Area List.
     * @return a list of the types of Geographic Areas.
     */
    public List<TypeArea> getTypeAreaList() {
        return this.mTypeAreaList;
    }
}
