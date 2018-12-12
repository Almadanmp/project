package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.UI.MainUI;

import java.util.List;

public class US04Controller {

    private GeographicAreaList mGeographicAreaListOfTypeGiven;

    public US04Controller(GeographicAreaList list) {
        this.mGeographicAreaListOfTypeGiven = list;
    }

    public void setGeographicAreaListWithGeographicAreasFromTypeGivenUS04UI(String input) {
        mGeographicAreaListOfTypeGiven.matchGeographicAreaListOfTypeGiven(input);
    }

    public GeographicAreaList getGeographicAreaList() {
        return this.mGeographicAreaListOfTypeGiven;
    }
}
