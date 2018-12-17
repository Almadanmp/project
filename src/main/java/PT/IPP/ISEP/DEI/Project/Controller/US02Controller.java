package PT.IPP.ISEP.DEI.Project.Controller;
import PT.IPP.ISEP.DEI.Project.Model.TypeAreaList;


/**
 * User Story 2
 * <p>
 * As a System Administrator I want to receive a list of all the previously stated Types of area.
 */

public class US02Controller {

    private TypeAreaList mTypeAreaList = new TypeAreaList();

    public US02Controller(TypeAreaList tipo) {
        this.mTypeAreaList = tipo;
    }

    public String getTypeAreaList() {
        return mTypeAreaList.printTypeAreaList();
    }
}
