package Sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaList {
    private List<GeographicArea> mGeographicAreaList;

    public GeographicAreaList(GeographicArea geographicAreaToAdd) {
        mGeographicAreaList = new ArrayList<>();
        mGeographicAreaList.add(geographicAreaToAdd);
    }

    public GeographicAreaList() {
        mGeographicAreaList = new ArrayList<>();
    }

    public boolean addGeographicAreaToGeographicAreaList(GeographicArea geographicAreaToAdd) {
        if(!(mGeographicAreaList.contains(geographicAreaToAdd))) {
            mGeographicAreaList.add(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    /**
     * Checks if a Geographic Area is inside the Geographic Area List
     * @param geoArea
     * @return
     */
    public boolean containsGA(GeographicArea geoArea) {
        return mGeographicAreaList.contains(geoArea);
    }

    public List<GeographicArea> getGeographicAreaList() {
        return mGeographicAreaList;
    }

    //PARA USAR NA USERSTORY 04??
    public List<GeographicArea> getGeographicAreaListOfTypeGiven(String typeOfGeographicArea) {
        List<GeographicArea> finalList = new ArrayList<>();
        TypeArea typeAreaToTest = new TypeArea(typeOfGeographicArea);
        for (GeographicArea ga : mGeographicAreaList) {
            if(ga.getTypeArea().equals(typeAreaToTest)) {
                finalList.add(ga);
            }
        }
        return finalList;
    }


}
