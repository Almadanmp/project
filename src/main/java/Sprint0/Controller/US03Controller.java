package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;


/**
 * User Story 3
 * As a System Administrator I want to Create a new Geographic Area
 */

public class US03Controller {


    public US03Controller() {
    }

    public GeographicArea createNewGeographicArea(String newName, TypeArea newType, Local newLocal) {
        GeographicArea newGeoArea = new GeographicArea(newName, newType, newLocal);
        return newGeoArea;
    }

    public boolean addNewGeoArea(GeographicArea geoAToAdd, GeographicAreaList newGeoList) {
        if (!(newGeoList.containsGA(geoAToAdd))) {
            newGeoList.addGeographicAreaToGeographicAreaList(geoAToAdd);
            return true;
        }
        return false;
    }
}