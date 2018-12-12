package Sprint0.Controller;

import Sprint0.Model.GeographicAreaList;

public class US04Controller {

    private GeographicAreaList mGeographicAreaListOfTypeGiven;

    public US04Controller(GeographicAreaList list) {
        this.mGeographicAreaListOfTypeGiven = list;
    }

    public void setGeographicAreaListWithGeographicAreasFromTypeGivenUS04UI(String input) {
        mGeographicAreaListOfTypeGiven = this.mGeographicAreaListOfTypeGiven.matchGeographicAreaWithInputType(input);
    }

    public GeographicAreaList getGeographicAreaList() {
        return this.mGeographicAreaListOfTypeGiven;
    }
}
