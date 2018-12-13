package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;


/**
 * User Story 3
 * As a System Administrator I want to Create a new Geographic Area
 */
public class US03Controller {

       public boolean addNewGeoArea(GeographicArea geoAToAdd, GeographicAreaList newGeoList) {
        if (!(newGeoList.containsGA(geoAToAdd))) {
            newGeoList.addGeographicAreaToGeographicAreaList(geoAToAdd);
            return true;
        }
        return false;
    }
}