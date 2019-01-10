package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * HouseMonitoringUI:
 **/

public class HouseMonitoringController {


    public double getCurrentRoomTemperature(Room room) {
        return room.getCurrentRoomTemperature();
    }

    public double getMaxTemperatureInARoomOnAGivenDay(Date day, House house, Room room) {
     return room.getMaxTemperatureInARoomOnAGivenDay(house,day);
    }
    public String printSensor(Sensor sensor){
        return sensor.printSensor();
    }

    public boolean doesListContainRoomByName(String name, RoomList room) {
        return room.doesListOfRoomsContainRoomByName(name);
    }

    public boolean doesSensorListInARoomContainASensorByName(String name, RoomList room) {
        boolean result = true;
        for (Room r : room.getRooms()) {
            result = r.getRoomSensorList().doesSensorListContainSensorByName(name);
        }
        return result;
    }

     /**
      * Common Methods to House Monitoring UI
      */

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

    public String printHouse (House house){
        return house.printHouse();
    }

    public List<Integer> matchRoomIndexByString(String input, House house){
        return house.getRoomList().matchRoomIndexByString(input);
    }

    public List<Integer> matchSensorIndexByString(String input, Room room){
        return room.getmRoomSensorList().matchSensorIndexByString(input);
    }

    public String printRoomElementsByIndex(List<Integer> listOfIndexesOfRoom, House house) {
        return house.getRoomList().printElementsByIndex(listOfIndexesOfRoom);
    }

    public String printSensorElementsByIndex(List<Integer> listOfIndexesOfSensor, Room room){
        return room.getmRoomSensorList().printElementsByIndex(listOfIndexesOfSensor);
    }

    public String printSensorList(Room room){return room.getmRoomSensorList().printSensorList(room);}
    public String printRoomList(House house) {
        return house.getRoomList().printRoomList(house);
    }

    public String printRoom (Room room){
        return room.printRoom();
    }
    public Date createDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    /**
     * US623 Controller Methods
     * As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),
     * as it is needed to assess the garden’s watering needs.
     */
    public double getAVGDailyRainfallOnGivenPeriod(House house, Date initialDate, Date endDate) {
        GeographicArea geoArea = house.getMotherArea();
        return geoArea.getAvgReadingsFromSensorTypeInGA("rainfall", initialDate, endDate);
    }

    public double getTotalRainfallOnGivenDayHouseArea(GeographicArea geoArea, Date day) {
        double sum = 0;
        int counter = 0;
        List<Sensor> listRain = geoArea.getSensorList().getSensorListByType("Rain");
        for (Sensor sensor : listRain) {
            sum = sum + sensor.getReadingList().getTotalSumOfGivenDayValueReadings(day);
            counter++;
        }
        if (counter != 0)
            return sum / counter;
        else return 0;
    }


    public Sensor getSensorWithTheMinimumDistanceToHouse(House house, GeographicArea ga) {
        return house.getSensorWithMinDistanceToHouse(ga, house);
    }

    public double getCurrentTemperatureInTheHouseArea(House house, GeographicArea ga) {
        return getSensorWithTheMinimumDistanceToHouse(house, ga).getReadingList().getMostRecentValueOfReading();
    }

}

