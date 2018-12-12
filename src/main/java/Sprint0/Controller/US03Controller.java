package Sprint0.Controller;

        import Sprint0.Model.GeographicArea;
        import Sprint0.Model.GeographicAreaList;

        import java.util.List;


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
        checkIfListValid(geoList.getGeographicAreaList());
        if (!(mGeoList.containsGA(mGeoArea))) {
            mGeoList.addGeographicAreaToGeographicAreaList(mGeoArea);
            return true;
        }
        return false;
    }

    private boolean checkIfListValid(List<GeographicArea> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }
}