package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;


/**
 * User Story 3
 * As a System Administrator I want to Create a new Geographic Area
 */

public class US03Controller {
    private GeographicArea mGeoArea;
    private GeographicAreaList mGeoList;


    public US03Controller(GeographicArea newGeo, GeographicAreaList newGeoList) {
        this.mGeoArea = newGeo;
        this.mGeoList = newGeoList;
    }

    public boolean addNewGeoArea(GeographicArea geoAToAdd, GeographicAreaList geoList) {
        this.mGeoArea = geoAToAdd;
        this.mGeoList = geoList;
        if (!(mGeoList.containsGA(mGeoArea))) {
            mGeoList.addGeographicAreaToGeographicAreaList(mGeoArea);
            return true;
        }
        return false;
    }

}