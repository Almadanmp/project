package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.ReadingList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.Sensor;

import java.util.Date;

/**
 * Controller class for House Monitoring UI
 */


public class HouseMonitoringController {

    private String rainfall = "rainfall";

    /**
     * @param room is the room we want to get the temperature from.
     * @return is the most recent temperature recorded in a room.
     */

    public double getCurrentRoomTemperature(Room room) {
        return room.getCurrentRoomTemperature();
    }

    /**
     * @param day  is the day we want to check the temperature in.
     * @param room is the room we want to check the temperature in.
     * @return is the max temperature recorded in a room
     */

    public double getDayMaxTemperature(Room room, Date day) {
        return room.getMaxTemperatureOnGivenDay(day);
    }

    /**
     * This method receives a room and return the room's name
     *
     * @return room's name as a string
     **/
    public String getRoomName(Room room) {
        return room.getRoomName();
    }



    /* US 623 - Controller Methods
    As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),as it
    is needed to assess the garden’s watering needs. */

    /**
     * @param house       is the house we want to get the average rainfall from.
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate     is the date where we want to stop measuring average rainfall (upper limit).
     * @return is the average rainfall of the house, as measured by the closest sensor to the house.
     */
    public double getAverageRainfallInterval(House house, Date initialDate, Date endDate) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(rainfall);
        if (closestSensor.getReadingList() == null || closestSensor.getReadingList().isEmpty()) {
            throw new IllegalArgumentException("Warning: average value not calculated - no readings available.");
        }
        return closestSensor.getReadingList().getAverageReadingsBetweenDates(initialDate, endDate);
    }

    /**
     * @param house is the house we want to get the total rainfall from.
     * @param day   is the date where we want to  measure total rainfall.
     * @return is the total rainfall of the house, as measured by the closest sensor to the house.
     */
    public double getTotalRainfallOnGivenDay(House house, Date day) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(rainfall);
        if (closestSensor.getReadingList() == null || closestSensor.getReadingList().isEmpty()) {
            throw new IllegalStateException("Warning: Total value could not be calculated - No readings were available.");
        }
        return closestSensor.getReadingList().getTotalValueOfReadingOnGivenDay(day);
    }

    /**
     * @param house is the house we want to get the temperature from.
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getHouseAreaTemperature(House house) {
        Sensor closest = house.getClosestSensorOfGivenType("Temperature");
        ReadingList readingList = closest.getReadingList();
        return readingList.getMostRecentValue();
    }

}

