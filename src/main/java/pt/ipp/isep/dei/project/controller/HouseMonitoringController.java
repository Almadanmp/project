package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;

/**
 * Controller class for House Monitoring UI
 */


public class HouseMonitoringController {

    private String rainfall = "rainfall";

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
     * @param room is the room we want to check the temperature in.
     * @return is the max temperature recorded in a room
     */

    public double getMaxTemperatureInARoomOnAGivenDay(Room room, Date day) {
        return room.getMaxTemperatureOnGivenDay(day);
    }

    /**
     * This method receives a room and return the room's name
     * @return room's name as a string
     * **/
    public String getRoomName(Room room) {
        return room.getRoomName();
    }



    /* US 623 - Controller Methods
    As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),as it
    is needed to assess the garden’s watering needs. */
    /**
     *
     * @param house is the house we want to get the average rainfall from.
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate is the date where we want to stop measuring average rainfall (upper limit).
     * @return is the average rainfall of the house, as measured by the closest sensor to the house.
     */

    public double getAVGDailyRainfallOnGivenPeriod(House house, Date initialDate, Date endDate) {
        GeographicArea geoArea = house.getMotherArea();
        Sensor closestSensor = house.getClosestSensorOfGivenType(geoArea, rainfall);
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
        int counter = 0;
        GeographicArea geoArea = house.getMotherArea();
        for (Sensor s : geoArea.getSensorList().getSensorList()) {
            if (rainfall.equals(s.getTypeSensor().getName())) {
                counter++;
            }
        }
        if (counter >= 1) {
            Sensor closestSensor = house.getClosestSensorOfGivenType(geoArea,rainfall);
            if (closestSensor.getReadingList() == null) {
                return Double.NaN;
            }
            return closestSensor.getReadingList().getTotalValueOfReadingOnGivenDay(day);
        }
        return Double.NaN;
    }

    /**
     *
     * @param house is the house we want to get the temperature from.
     * @param ga is the area the house is contained in.
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getCurrentTemperatureInTheHouseArea(House house, GeographicArea ga) {
        Sensor closest = house.getClosestSensorOfGivenType(ga,"temperature");
        ReadingList readingList = closest.getReadingList();
        return readingList.getMostRecentValueOfReading();
    }
}

