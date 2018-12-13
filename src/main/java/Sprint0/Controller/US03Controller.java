package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;

import java.util.List;


/**
 * User Story 3
 * As a System Administrator I want to Create a new Geographic Area
 */
public class US03Controller {

    /**
     * Method to add a new geographic area to a list of geographic areas
     *
     * @param geoAToAdd  Geographic area to add to the list
     * @param newGeoList Geographic List
     * @return will return true if the area was added or false if it wasnt added
     */
    public boolean addNewGeoArea(GeographicArea geoAToAdd, GeographicAreaList newGeoList) {
        if (!(newGeoList.containsGA(geoAToAdd))) {
            newGeoList.addGeographicAreaToGeographicAreaList(geoAToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method to check if the list of Geographic Areas is Valid
     *
     * @param values
     * @return returns false if its not valid (null or empty) or true if valid.
     */
    private boolean checkIfListValid(List<GeographicArea> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }
}