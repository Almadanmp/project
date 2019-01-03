package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * HouseMonitoringUI:
 * <p>
 * 1) As a Regular User, I want to get the total rainfall in the house area for a given day.
 * 2) As a Regular User, I want to get the average daily rainfall in the house area for a
 * given period (days), as it is needed to assess the garden’s watering needs.
 **/

public class HouseMonitoringController {


    public double getCurrentRoomTemperature(Date day, RoomList room) {
        double currentTemperature = 0;
        for (Room r : room.getListOfRooms()) {
            currentTemperature = r.getCurrentRoomTemperature(day);
        }
        return currentTemperature;
    }

    public double getMaxTemperatureInARoomOnAGivenDay(Date day, RoomList room){
        double max=-275;
        for(Room r: room.getListOfRooms()){
            max=r.getMaxTemperatureInARoomOnAGivenDay(day);
        }
        return max;
    }

    public boolean doesListContainRoomByName(String name, RoomList room){
        return room.doesListOfRoomsContainRoomByName(name);
    }
    public boolean doesSensorListInARoomContainASensorByName(String name, RoomList room){
        boolean result = true;
        for (Room r: room.getRooms()){
            result= r.getRoomSensorList().doesSensorListContainSensorByName(name);}
        return result;
    }

    public List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    public String printGeoGraphicAreaElementsByIndex(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.printGeoGraphicAreaElementsByIndex(listOfIndexesGeographicAreas);
    }

    public String printGA(GeographicArea geoArea) {
        return geoArea.printGeographicArea();
    }

    public String printGAList(GeographicAreaList geoAreaList) {
        return geoAreaList.printGaWholeList(geoAreaList);
    }

    public String printHouseList(GeographicArea geoArea) {
        return geoArea.getHouseList().printHouseList(geoArea);
    }

    public double getAVGDailyRainfallOnGivenPeriod(House house, Date minDay, Date maxDay) {
        GeographicArea geoArea = house.getmMotherArea();
        return geoArea.getAvgReadingsFromSensorTypeInGA("Rain", minDay, maxDay);
    }

    public Date createDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    public double getTotalRainfallOnGivenDayHouseArea(GeographicArea geoArea, Date day) {
        double sum = 0;
        int counter = 0;
        List<Sensor> listRain = geoArea.getSensorList().getSensorListByType("Rain");
        for (Sensor sensor : listRain) {
            sum = sum + sensor.getReadingList().getTotalSumOfGivenDayValueReadings(day);
            counter++;
        }
        if (counter != 0) return sum / counter;
        else return 0;
    }



    public Sensor getSensorWithTheMinimumDistanceToHouse(House house, GeographicArea ga) {
        return house.getSensorWithTheMinimumDistanceToHouse(ga, house);
    }

    public double getCurrentTemperatureInTheHouseArea(House house, GeographicArea ga) {
        return getSensorWithTheMinimumDistanceToHouse(house,ga).getReadingList().getMostRecentValueOfReading();
    }

}

