package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.*;
import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;

/**
 * US101: As an Administrator, I want to configure the location of the house.
 **/

public class US101Controller {

    private HouseList mHouseList;
    private GeographicAreaList mGeographicAreaList;



    public US101Controller (){}

    public US101Controller (GeographicAreaList geographicAreaList){this.mGeographicAreaList =geographicAreaList;}


    public boolean addHouseToHouseList(HouseList newHouseList, String newHouseDesignation, String newHouseAddress, String newHouseZipCode, double latitude, double longitude) {
        if (newHouseList == null) {
            return false;
        }
        House houseToAdd = new House(newHouseDesignation, newHouseAddress, new Local(latitude, longitude), newHouseZipCode, new RoomList());
        return newHouseList.addHouseToHouseList(houseToAdd);
    }


    public void setHouseListToGeoArea(HouseList newHouseList, GeographicArea newGeoArea) {
        newGeoArea.setHouseList(newHouseList);
        this.mHouseList = newHouseList;

    }

    public GeographicAreaList getGeographicAreaList() {
        return mGeographicAreaList;
    }

    public String printGeographicAreaListNames() {
        return mGeographicAreaList.printGeoAreaList();
    }

    public boolean validateGeoArea(String ga) {

        return mGeographicAreaList.validateIfGeographicAreaToGeographicAreaList(ga);
    }
    public GeographicArea matchGeoArea(String nameArea){
        return mGeographicAreaList.matchGeoArea(nameArea);
    }

}
