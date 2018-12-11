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
    private GeographicArea mGeoArea;
    private GeographicAreaList mGeoList;
    private TypeArea mTypeGeoArea;
    private Local mGeoAreaLocal;


    public US03Controller() {

    }

    public boolean addNewGeoArea(GeographicArea geoAToAdd) {
        this.mGeoArea = geoAToAdd;
        this.mGeoList = new GeographicAreaList(mGeoArea);
        if (!(mGeoList.containsGA(mGeoArea))) {
            mGeoList.addGeographicAreaToGeographicAreaList(mGeoArea);
            mGeoArea.setGeoAreaList(mGeoList);
        }
        return false;
    }

    public TypeArea createType(String geoAreaType){
        this.mTypeGeoArea = new TypeArea(geoAreaType);
        return this.mTypeGeoArea;
    }

    public Local createLocal(double latitude, double longitude){
        this.mGeoAreaLocal = new Local(latitude, longitude);
        return this.mGeoAreaLocal;
    }
}