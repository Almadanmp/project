package pt.ipp.isep.dei.project.controller;
import pt.ipp.isep.dei.project.model.TypeAreaList;


/**
 * User Story 2
 * <p>
 * As a System Administrator I want to receive a list of all the previously stated Types of area.
 */

public class US02Controller {

    private TypeAreaList mTypeAreaList;

    public US02Controller(TypeAreaList tipo) {
        this.mTypeAreaList = tipo;
    }

    public String getTypeAreaList() {
        return mTypeAreaList.printTypeAreaList();
    }
}
