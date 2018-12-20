package PT.IPP.ISEP.DEI.Project.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeographicAreaList {
    private List<GeographicArea> mGeographicAreaList;

    /**
     * GeographicAreaList constructor that receives a Geographic Area as a parameter and
     * adds the GA to the attribute mGeographicAreaList
     * @param geographicAreaToAdd geographic area to add to the attribute
     */
    public GeographicAreaList(GeographicArea geographicAreaToAdd) {
        mGeographicAreaList = new ArrayList<>();
        mGeographicAreaList.add(geographicAreaToAdd);
    }

    /**
     * GeographicAreaList constructor that receives without parameters
     */
    public GeographicAreaList() {
        mGeographicAreaList = new ArrayList<>();
    }

    /**
     * Method that receives a geographic area as a parameter and adds that
     * GA to the list in case it is not contained in that list already.
     * @param geographicAreaToAdd geographic area to be added
     * @return returns true in case the geographic area is added and false if not
     * **/
    public boolean addGeographicAreaToGeographicAreaList(GeographicArea geographicAreaToAdd) {
        if(!(mGeographicAreaList.contains(geographicAreaToAdd))) {
            mGeographicAreaList.add(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    /**
     * Method that receives a string as a parameter, compares that string with every
     * Geographic Area name of every GA in the list.
     * @param areaToMatch string that corresponds to a geographic area name
     */
    public GeographicArea matchGeoArea (String areaToMatch){
        for (GeographicArea g: mGeographicAreaList){
            if (g.getName().equals(areaToMatch)){
                return g;
            }
        }
        return null;
    }

    public GeographicArea[] getGeographicAreas() {
        int sizeOfResultArray = mGeographicAreaList.size();
        GeographicArea[] result = new GeographicArea[sizeOfResultArray];
        for (int i = 0; i < mGeographicAreaList.size(); i++) {
            result[i] = mGeographicAreaList.get(i);
        }
        return result;
    }

    /**
     * Method that goes through every geographic area from the attribute mGeographicAreaList
     * and returns a string with every GA name
     */
    public String printGeoAreaList() {
        StringBuilder finalString = new StringBuilder();
        String emptyList= "The list is empty.";
        if(mGeographicAreaList.isEmpty()) {
            return emptyList;
        }
        finalString.append("Geographic Area List:");
        for(GeographicArea tipo: mGeographicAreaList) {
            finalString.append(" \n" + "-").append(tipo.getName()).append(";");
        }
        return finalString.toString();
    }

    /**
     * Method that receives a string as a parameter, compares that string with every
     * Geographic Area name of every GA in the list and returns true in case of match.
     * @param geographicAreaToAdd string that corresponds to a geographic area name
     * @return returns true in case of match and false otherwise
     */
    public boolean validateIfGeographicAreaToGeographicAreaList(String geographicAreaToAdd) {
        for(GeographicArea ga : mGeographicAreaList) {
            if ((ga.getName().equals(geographicAreaToAdd))) {
                return true;
            }
        }
        return false;
    }

    public String printListOfHousesInAGeographicAreaContainedInAList(){
        String finalString= "";
        for (GeographicArea ga: mGeographicAreaList){
            finalString=ga.getmHouseList().printHouseList();
        }
        return finalString;
    }

    /**
     * Checks if a the Geographic Area given as a parameter is inside the Geographic Area List
     * @param geoArea geographic area to test
     * @return returns true in case the GA is contained in the list and false otherwise
     */
    public boolean containsGA(GeographicArea geoArea) {
        return mGeographicAreaList.contains(geoArea);
    }

    /**
     * Getter of the attribute mGeographicAreaList from this class
     * @return returns the geographic area list
     */
    public List<GeographicArea> getGeographicAreaList() {
        return mGeographicAreaList;
    }

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

    /**
     * @param name name of the GeographicArea to find in the list.
     * @return return the GeographicArea whose name matches the name introduced.
     */
    public GeographicArea getGeographicAreaByName(String name) {
        for (GeographicArea gA : mGeographicAreaList) {
            if (gA.getName().equals(name)) {
                return gA;
            }
        }
        return null;
    }

    /**
     * This method checks if the list exists
     * @return
     */
    public boolean checkIfListIsValid() {
        return !mGeographicAreaList.isEmpty();
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
