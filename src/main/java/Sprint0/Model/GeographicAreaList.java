package Sprint0.Model;

import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean validateIfGeographicAreaToGeographicAreaList(String geographicAreaToAdd) {
        for(GeographicArea ga : mGeographicAreaList) {
            if ((ga.getName().equals(geographicAreaToAdd))) {
                return true;
            }
        }
        return false;
    }

    public String printGeoAreaList() {
        String finalString= "Geographic Area List:";
        for(GeographicArea tipo: mGeographicAreaList)
            finalString = finalString + " \n"+ "-" + tipo.getName() + ";";
        return finalString;
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
    public GeographicAreaList matchGeographicAreaWithInputType(String typeOfGeographicArea) {
        GeographicAreaList finalList = new GeographicAreaList();
        TypeArea typeAreaToTest = new TypeArea(typeOfGeographicArea);
        for (GeographicArea ga : mGeographicAreaList) {
            if(ga.getTypeArea().equals(typeAreaToTest)) {
                finalList.addGeographicAreaToGeographicAreaList(ga);
            }
        }
        return finalList;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof GeographicAreaList)) {
            return false;
        }
        GeographicAreaList list = (GeographicAreaList) testObject;
        return Arrays.equals(this.getGeographicAreaList().toArray(), list.getGeographicAreaList().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
