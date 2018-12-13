package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

public class US07Controller {

    private GeographicAreaList mGeographicAreaList;


    public US07Controller(GeographicAreaList list) {
        this.mGeographicAreaList = list;
    }

    public GeographicArea matchGeoArea(String nameArea){
        return mGeographicAreaList.matchGeoArea(nameArea);
    }

    public void setMotherArea(GeographicArea daughterArea, GeographicArea motherArea){
        daughterArea.setMotherArea(motherArea);
    }

    public String getGeographicAreaList() {
        return mGeographicAreaList.printGeoAreaList();
    }

    public boolean validateGeoArea(String ga) {

        return mGeographicAreaList.validateIfGeographicAreaToGeographicAreaList(ga);
    }




}
