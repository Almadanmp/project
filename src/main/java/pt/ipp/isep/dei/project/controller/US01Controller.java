package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.TypeAreaList;

/**
 * User Story 01
 * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
 */
public class US01Controller {
 private TypeAreaList mTypeAreaList;

    /**
     * The Builder recieves a List Of Types Of Geographic Areas and sets it.
     * @param tipo
     */
    public US01Controller(TypeAreaList tipo) {
        this.mTypeAreaList = tipo;
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list;
     * false - the name is null.
     */
    public boolean createAndAddTypeAreaToList(String input) {
        return mTypeAreaList.newTAG(input);
    }


}
