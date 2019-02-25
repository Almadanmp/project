package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Types of Geographical Areas.
 */

public class TypeAreaList {
    private List<TypeArea> typeAreas;

    /**
     * TypeAreaList() empty constructor that initializes an ArrayList of TypeAreas.
     */
    public TypeAreaList() {
        typeAreas = new ArrayList<>();
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     *
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
     *
     * @param type Type of Geographic Area one wishes to add to a list.
     * @return true or false depending on the list containing or not the type input already.
     */
    public boolean addTypeArea(TypeArea type) {
        if (!typeAreas.contains(type)) {
            typeAreas.add(type);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method builds a string of all the individual members of the geoAreaType list.
     *
     * @param typeAreaList is a list of all the types a Geographical Area may have.
     * @return builds a string of all the individual members of the geoAreaType list.
     */
    public String buildGATypeWholeListString(TypeAreaList typeAreaList) {
        StringBuilder result = new StringBuilder("---------------\n");
        for (int i = 0; i < typeAreaList.size(); i++) {
            TypeArea aux = typeAreaList.get(i);
            result.append(i).append(") Name: ").append(aux.getTypeOfGeographicArea()).append(" \n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * This method checks if type area list is empty.*
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.typeAreas.isEmpty();
    }

    /**
     * Checks the type area list size and returns the size as int.\
     *
     * @return TypeArea size as int
     **/
    public int size() {
        return this.typeAreas.size();
    }

    /**
     * This method receives an index as parameter and gets a type area from Type Area list.
     *
     * @return returns Type Area that corresponds to index.
     */
    public TypeArea get(int index) {
        return this.typeAreas.get(index);
    }

    /**
     * Getter (array of Type Areas)

     * @return array of Type Areas
     */
    private TypeArea[] getElementsAsArray() {
        int sizeOfResultArray = typeAreas.size();
        TypeArea[] result = new TypeArea[sizeOfResultArray];
        for (int i = 0; i < typeAreas.size(); i++) {
            result[i] = typeAreas.get(i);
        }
        return result;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof TypeAreaList)) {
            return false;
        }
        TypeAreaList list = (TypeAreaList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
