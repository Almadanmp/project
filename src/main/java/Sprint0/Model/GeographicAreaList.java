package Sprint0.Model;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaList {
    private List<GeographicArea> mGeographicAreaList;
    private GeographicArea mGeographicAreaContained;
    private GeographicArea mGeographicAreaContainer;


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
    public GeographicAreaList getGeographicAreaListOfTypeGiven(String typeOfGeographicArea) {
        GeographicAreaList finalList = new GeographicAreaList();
        TypeArea typeAreaToTest = new TypeArea(typeOfGeographicArea);
        for (GeographicArea ga : mGeographicAreaList) {
            if(ga.getTypeArea().equals(typeAreaToTest)) {
                finalList.addGeographicAreaToGeographicAreaList(ga);
            }
        }
        return finalList;
    }

    public boolean setContainerAreaAndContainedArea(String area1Name, String area2Name){
        if (checkIfListIsValid(mGeographicAreaList)) {
            for (GeographicArea ga1 : mGeographicAreaList) {
                if (ga1.getName().equals(area1Name)) {
                    mGeographicAreaContained = ga1;
                    break;
                }
            }
            for (GeographicArea ga2 : mGeographicAreaList) {
                if (ga2.getName().equals(area2Name)) {
                    mGeographicAreaContainer = ga2;
                    return true;
                }
            }
        }return false;
    }

    public boolean seeIfItsContained(){
        if (mGeographicAreaContained.isAreaContainedInAnotherArea(mGeographicAreaContained,mGeographicAreaContainer)){
            return true;
        }return false;
    }

    private boolean checkIfListIsValid(List<GeographicArea> values){
        if (values == null || values.isEmpty()){
            return false;
        }return true;
    }
}
