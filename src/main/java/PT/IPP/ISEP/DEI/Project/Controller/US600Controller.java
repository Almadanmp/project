package PT.IPP.ISEP.DEI.Project.Controller;

import PT.IPP.ISEP.DEI.Project.Model.GeographicArea;
import PT.IPP.ISEP.DEI.Project.Model.GeographicAreaList;
import PT.IPP.ISEP.DEI.Project.Model.House;
import PT.IPP.ISEP.DEI.Project.Model.Sensor;

/**
 * As a Regular User, I want to get the current temperature in the house area. If, in the
 * first element with temperature sensors of the hierarchy of geographical areas that
 * includes the house, there is more than one temperature sensor, the nearest one
 * should be used.
 */
public class US600Controller {
    private House mHouse;
    private String mGeographicAreaName;
    private GeographicAreaList mGeographicAreaList;

    public US600Controller(House house){
        this.mHouse=house;
    }

    public US600Controller(String nameOfGeographicArea){
        mGeographicAreaName=nameOfGeographicArea;
    }

    public US600Controller(GeographicAreaList list){
        this.mGeographicAreaList=list;
    }

    public String printGeoAreaList(){
        return mGeographicAreaList.printGeoAreaList();
    }

    public boolean validateIfGeographicAreaToGeographicAreaList(String geographicArea){
        return mGeographicAreaList.validateIfGeographicAreaToGeographicAreaList(geographicArea);
    }

    public double getTheClosestDistanceFromTheHouseToTheSensor(){
        return mHouse.getTheMinorDistanceFromTheHouseToTheSensor();
    }

    public GeographicArea getGeographicAreaByName(String geographicAreaName) {
        mGeographicAreaName=geographicAreaName;
        return mGeographicAreaList.getGeographicAreaByName(geographicAreaName);
    }

    public House gethousenbyname(String houseName){
        return getGeographicAreaByName(mGeographicAreaName).getmHouseList().getHouseByDesignation(houseName);
    }

    public Sensor getSensorWithTheMinimumDistanceToHouse(){
        return mHouse.getSensorWithTheMinimumDistanceToHouse(mHouse);
    }

    public double getCurrentTemperatureInTheHouseArea(){
        return getSensorWithTheMinimumDistanceToHouse().getReadingList().getMostRecentValueOfReading();
        }
}
