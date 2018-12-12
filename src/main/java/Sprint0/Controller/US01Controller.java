package Sprint0.Controller;

import Sprint0.Model.TypeAreaList;

/**
 * User Story 01
 * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
 */
public class US01Controller {
 private TypeAreaList mTypeAreaList;

    public US01Controller(TypeAreaList tipo) {
        this.mTypeAreaList = tipo;
    }

    /**
     * This method creates a new Type of Geographic Area.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created;
     * false - the name is null.
     */
    public boolean CreateAndAddTypeAreaToList(String input) {
        return mTypeAreaList.newTAG(input);
    }

    public String getTypeAreaList(){
        return mTypeAreaList.getTypeAreaList();
    }
}
