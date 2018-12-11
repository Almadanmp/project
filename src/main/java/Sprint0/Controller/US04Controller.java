package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

import java.util.List;

public class US04Controller {

    private GeographicAreaList mGeographicAreaList;

    public US04Controller() {

    }

    public void receiveTypeOfGeographicArea(String input) {
        this.mGeographicAreaList.getGeographicAreaListOfTypeGiven(input);
    }

    public List<GeographicArea> getGeographicAreaListOfTypeGiven() {
        return this.mGeographicAreaList.getGeographicAreaList();
    }
}
