package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class HouseMonitoringController {

    /**
     *
     * @param room is the room we want to get the temperature from.
     * @return is the most recent temperature recorded in a room.
     */

    public double getCurrentRoomTemperature(Room room) {
        return room.getCurrentRoomTemperature();
    }

    /**
     *
     * @param day is the day we want to check the temperature in.
     * @param house is the house that contains the room we want to check the temperature in.
     * @param room is the room we want to check the temperature in.
     * @return is the most recent temperature recorded in a room.
     */

    public double getMaxTemperatureInARoomOnAGivenDay(Date day, House house, Room room) {
        return room.getMaxTemperatureInARoomOnAGivenDay(house, day);
    }

    /**
     *
     * @param sensor is the sensor we want to print.
     * @return builds a string with given sensor.
     */

    String buildSensorString(Sensor sensor) {
        return sensor.buildSensorString();
    }

    /**
     *
     * @param name is the name of the room we want to look for in the roomList.
     * @param roomList is the list we're going to look for the room in.
     * @return is true if the list contains a room with given name, false if it doesn't.
     */

    boolean doesListContainRoomByName(String name, RoomList roomList) {
        return roomList.doesListOfRoomsContainRoomByName(name);
    }

    /**
     *
     * @param name is the name of the sensor we want to look for in the rooms in a room list.
     * @param roomList is the list we're going to look for the sensor in.
     * @return is true if the rooms in the room list contain a sensor with the given name, false if they don't.
     */

    boolean doesSensorListInARoomContainASensorByName(String name, RoomList roomList) {
        boolean result = true;
        for (Room r : roomList.getList()) {
            result = r.getmRoomSensorList().doesSensorListContainSensorByName(name);
        }
        return result;
    }

    /* Common Methods to House Monitoring UI
     */

    /**
     *
     * @param input is the name of the GeoArea we want to look for in a list.
     * @param geoAreaList is the list where we're going to look for an Area.
     * @return is a list of Integers containing all the indexes where the list contains a geoArea whose name matches the
     * given string.
     */

    List<Integer> matchGeographicAreaIndexByString(String input, GeographicAreaList geoAreaList) {
        return geoAreaList.matchGeographicAreaIndexByString(input);
    }

    /**
     *
     * @param listOfIndexesGeographicAreas is a list of integers that represent positions in a list.
     * @param geoAreaList is the list where we're going to access specific positions.
     * @return builds a string of the elements of the list contained in the indexes given as a parameter.
     */

    String buildGeographicAreaElementsByIndexString(List<Integer> listOfIndexesGeographicAreas, GeographicAreaList geoAreaList) {
        return geoAreaList.buildGeographicAreaElementsByIndexString(listOfIndexesGeographicAreas);
    }

    /**
     *
     * @param geoArea is the geoArea to print.
     * @return builds a string with the given Area.
     */

    String buildGeoAreaString(GeographicArea geoArea) {
        return geoArea.buildGeographicAreaString();
    }

    /**
     *
     * @param geoAreaList is the list we want to print.
     * @return builds a string with the individual elements of the given list.
     */

    String buildGeoAreaListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildGaWholeListString(geoAreaList);
    }

    /**
     *
     * @param input is the string we want to look for.
     * @param house is the house where we want to look for rooms with names that match given string.
     * @return is a list of integers that are indexes of the house's roomList where a room with a name matching
     * the input string is contained.
     */

    List<Integer> matchRoomIndexByString(String input, House house) {
        return house.matchRoomIndexByString(input);
    }

    /**
     *
     * @param input is the name of the sensor we want to look for in a room.
     * @param room is the room where we want to look for the sensor.
     * @return is a list of integers that are indexes of the room's SensorList where a Sensor with a name matching
     * the input string is contained.
     */

    List<Integer> matchSensorIndexByString(String input, Room room) {
        return room.getmRoomSensorList().matchSensorIndexByString(input);
    }

    /**
     *
     * @param listOfIndexesOfRoom is a list of indexes representing positions in a list.
     * @param house is the house whose lists we want to access.
     * @return builds a string of all the elements of the house's roomList in the positions given by the list of indexes.
     */

    String buildRoomElementsByIndexString(List<Integer> listOfIndexesOfRoom, House house) {
        return house.buildRoomsByIndexString(listOfIndexesOfRoom);
    }

    /**
     *
     * @param listOfIndexesOfSensor is a list of indexes representing positions in a list.
     * @param room is the room whose lists we want to access.
     * @return builds a string of all the elements of the room's sensorList in the positions given by the list of indexes.
     */

    String buildSensorElementsByIndexString(List<Integer> listOfIndexesOfSensor, Room room) {
        return room.getmRoomSensorList().buildElementsByIndexString(listOfIndexesOfSensor);
    }

    /**
     *
     * @param room is the room that we want to print the list from.
     * @return builds a string from the individual elements of the room's sensor list.
     */

    public String buildRoomSensorListString(Room room) {
        return room.getmRoomSensorList().buildSensorListString(room);
    }

    /**
     *
     * @param house is the house we want to print the list from.
     * @return builds a string from the individual elements of the house's room list.
     */

    public String buildHouseRoomListString(House house) {
        return house.buildRoomListString();
    }

    /**
     *
     * @param room is the room we want to print.
     * @return builds a string with given room.
     */

    public String buildRoomString(Room room) {
        return room.buildRoomString();
    }

    /**
     *
     * @param year is the year of the created date.
     * @param month is the month of the created date.
     * @param day is the day of the created date.
     * @return is the new created date.
     */

    public Date createDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    /*
     * US623 Controller Methods
     * As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),
     * as it is needed to assess the garden’s watering needs.
     */

    /**
     *
     * @param house is the house we want to get the average rainfall from.
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate is the date where we want to stop measuring average rainfall (upper limit).
     * @return is the average rainfall of the house, as measured by the closest sensor to the house.
     */

    public double getAVGDailyRainfallOnGivenPeriod(House house, Date initialDate, Date endDate) {
        GeographicArea geoArea = house.getMotherArea();
        Sensor closestSensor = house.getSensorWithMinDistanceToHouse(geoArea, house, "rainfall");
        if (closestSensor.getReadingList() == null || closestSensor.getReadingList().isEmpty()) {
            return Double.NaN;
        }
        return closestSensor.getReadingList().getAverageReadingsBetweenTwoDates(initialDate, endDate);
    }

    /**
     *
     * @param house is the house we want to get the total rainfall from.
     * @param day is the date where we want to  measure total rainfall.
     * @return is the total rainfall of the house, as measured by the closest sensor to the house.
     */

    public double getTotalRainfallOnGivenDay(House house, Date day) {
        GeographicArea geoArea = house.getMotherArea();
        Sensor closestSensor = house.getSensorWithMinDistanceToHouse(geoArea, house, "rainfall");
        if (closestSensor.getReadingList() == null || closestSensor.getReadingList().isEmpty()) {
            return Double.NaN;
        }
        return closestSensor.getReadingList().getTotalValueOfReadingOnGivenDay(day);
    }


    /**
     *
     * @param house is the house we want to get the closest sensor to.
     * @param ga is the geographic area the house is contained in.
     * @param sensorType is the type of sensor we want to look for.
     * @return is the closest sensor of the given type to the house.
     */

    Sensor getSensorWithTheMinimumDistanceToHouse(House house, GeographicArea ga, String sensorType) {
        return house.getSensorWithMinDistanceToHouse(ga, house, sensorType);
    }

    /**
     *
     * @param house is the house we want to get the temperature from.
     * @param ga is the area the house is contained in.
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getCurrentTemperatureInTheHouseArea(House house, GeographicArea ga) {
        return getSensorWithTheMinimumDistanceToHouse(house, ga, "temperature").getReadingList().getMostRecentValueOfReading();
    }

    /**
     *
     * @param house is the house we want to get info from.
     * @return builds a string with the house's information.
     */

    public String getHouseInfoForOutputMessage(House house) {
        return "The Average Rainfall on the house area of " + house.getHouseId();
    }

}

