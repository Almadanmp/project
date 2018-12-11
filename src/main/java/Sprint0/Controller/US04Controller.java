package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.UI.MainUI;

import java.util.List;

public class US04Controller {

    private List<GeographicArea> mGeographicAreaListOfTypeGiven;

    public US04Controller() {

    }

    public void receiveTypeOfGeographicArea(String input) {
        mGeographicAreaListOfTypeGiven = MainUI.mGeographicAreaList.getGeographicAreaListOfTypeGiven(input);
    }

    public List<GeographicArea> getGeographicAreaListOfTypeGiven() {
        return this.mGeographicAreaListOfTypeGiven;
    }
}
