package Sprint0.Controller;

import Sprint0.Model.GeographicAreaList;
import Sprint0.UI.MainUI;

public class US04Controller {

    private GeographicAreaList mGeographicAreaListOfTypeGiven;

    public US04Controller() {

    }

    public void receiveTypeOfGeographicArea(String input) {
        mGeographicAreaListOfTypeGiven = MainUI.mGeographicAreaList.getGeographicAreaListOfTypeGiven(input);
    }

    public GeographicAreaList matchGeographicAreaTypeGiven() {
        return this.mGeographicAreaListOfTypeGiven;
    }
}
