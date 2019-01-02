package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

/**
 * As a Regular User, I want to get the current temperature in the house area. If, in the
 * first element with temperature sensors of the hierarchy of geographical areas that
 * includes the house, there is more than one temperature sensor, the nearest one
 * should be used.
 */
public class US600Controller {
    private House mHouse;
    private GeographicAreaList mGeographicAreaList;

    public US600Controller(House house) {
        this.mHouse = house;
    }

    public US600Controller(GeographicAreaList list) {
        this.mGeographicAreaList = list;
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA (GeographicArea geoArea){
        return geoArea.printGeographicArea();
    }

    public void printGAList(GeographicAreaList geoAreaList){
        geoAreaList.printGaWholeList(geoAreaList);
    }

    public void printHouseList(GeographicArea ga) {
        ga.getHouseList().printHouseList(ga);
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList){
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(House house, GeographicArea ga) {
        return house.getSensorWithTheMinimumDistanceToHouse(ga, house);
    }

    public double getCurrentTemperatureInTheHouseArea(House house, GeographicArea ga) {
        return getSensorWithTheMinimumDistanceToHouse(house,ga).getReadingList().getMostRecentValueOfReading();
    }

    public boolean matchHouseFromList(GeographicArea ga, String houseName) {
        return ga.getHouseList().checkHouseExists(houseName);
    }

}
