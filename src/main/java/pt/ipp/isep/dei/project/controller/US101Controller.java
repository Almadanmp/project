package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.GeographicAreaList;

import java.util.List;

/**
 * US101: As an Administrator, I want to configure the location of the house.
 **/

public class US101Controller {

    private HouseList mHouseList;
    private GeographicAreaList mGeoList;


    public US101Controller(GeographicAreaList geoList) {
        this.mGeoList = geoList;
    }

    public US101Controller(HouseList list) {
        this.mHouseList = list;
    }

    public void printGAList(GeographicAreaList geoAreaList) {
        geoAreaList.printGaWholeList(geoAreaList);
    }


    public void printHouseList() {
        mHouseList.printHouseList();
    }

    public void setHouseAddress(String address, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).setmAddress(address);
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public void setHouseZIPCode(String zipCode, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).setmZipCode(zipCode);
    }

    public void setHouseLocal(double latitude, double longitude, int indexOfHouse) {
        mHouseList.getHouseList().get(indexOfHouse).getmGPS().setLatitude(latitude);
        mHouseList.getHouseList().get(indexOfHouse).getmGPS().setLongitude(longitude);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    public void printHouseList(GeographicArea ga) {
        ga.getHouseList().printHouseList(ga);
    }


}



