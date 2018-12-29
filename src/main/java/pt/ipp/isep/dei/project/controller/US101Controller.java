package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

/**
 * US101: As an Administrator, I want to configure the location of the house.
 **/

public class US101Controller {

    private HouseList mHouseList;
    private GeographicAreaList mGeographicAreaList;
    private GeographicArea mGeographicArea;



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
        this.mGeographicArea = newGeoArea;

    }

    public GeographicAreaList getGeographicAreaList() {
        return mGeographicAreaList;
    }



}
