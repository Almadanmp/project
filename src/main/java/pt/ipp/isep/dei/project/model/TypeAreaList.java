package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypeAreaList {
    private List<TypeArea> mTypeAreaList;

    public TypeAreaList() {
        mTypeAreaList = new ArrayList<>();
    }

    /**
     * This method prints all the Geographic Area Types in a TypeAreaList.
     * @return
     */
    public String printTypeAreaList() {
        String finalString = "\nArea Types List:\n";
        if(mTypeAreaList.isEmpty()) {
            finalString = finalString + "\n|||| List is Empty ||||\nAdd types to list first";
        }
        else { for (TypeArea tipo : mTypeAreaList)
            finalString = finalString + "\n" + "-" + tipo.getTypeOfGeographicArea() + ";";}
        return finalString;
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     * @param name String of the new Area Type that one wishes to create and add to a list.
     * @return
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
     * @return
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
     * Getter of the Type Area List.
     * @return
     */
    public List<TypeArea> getmTypeAreaList() {
        return this.mTypeAreaList;
    }
}
