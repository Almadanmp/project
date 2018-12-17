package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;

public class US04Controller {

    private GeographicAreaList mGeographicAreaListOfTypeGiven;

    /**
     * Constructor of the Class With GeographicAreaList input (Geographic Area List from the MainUI Class)
     * @param list
     */
    public US04Controller(GeographicAreaList list) {
        this.mGeographicAreaListOfTypeGiven = list;
    }

    /**
     * Void Method to Set/Match a Geographic Area List with a Certain Type Area Given(String input).
     * @param input
     */
    public void matchGeoAreaTypeWithInput(String input) {
        mGeographicAreaListOfTypeGiven = this.mGeographicAreaListOfTypeGiven.matchGeographicAreaWithInputType(input);
    }

    /**
     * Geographic Area List getter.
     * @return
     */
    public GeographicAreaList getGeographicAreaList() {
        return this.mGeographicAreaListOfTypeGiven;
    }
}
