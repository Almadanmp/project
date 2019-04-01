package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.sensor.Sensor;

import java.util.Date;

/**
 * Controller class for House Monitoring UI
 */


public class HouseMonitoringController {

    private static final String RAINFALL = "rainfall";
    private static final String TEMPERATURE = "temperature";

    /**
     * Returns the current temperature in a given Room.
     *
     * @param roomDTO is the roomDTO we want to get the room from, so that we can get the temperature.
     * @param house   the house of the project.
     * @return is the most recent temperature recorded in a room.
     */

    public double getCurrentRoomTemperature(RoomDTO roomDTO, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getCurrentRoomTemperature();
    }

    /**
     * @param day     is the day we want to check the temperature in.
     * @param roomDTO is the room we want to check the temperature in.
     * @param house   the project's house.
     * @return is the max temperature recorded in a room
     */

    public double getDayMaxTemperature(RoomDTO roomDTO, Date day, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getMaxTemperatureOnGivenDay(day);
    }

    /**
     * This method receives a room and return the room's name
     *
     * @param roomDTO the DTO of the chosen Room.
     * @param house   the House of the project.
     * @return room's name as a string
     **/
    public String getRoomName(RoomDTO roomDTO, House house) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, house);
        return room.getName();
    }



    /* US 623 - Controller Methods
    As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),as it
    is needed to assess the garden’s watering needs. */

    /**
     * @param house       is the house we want to get the average rainfall from.
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate     is the date where we want to stop measuring average rainfall (upper limit).
     * @Author Daniela
     */
    public double getAverageRainfallInterval(House house, Date initialDate, Date endDate) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(RAINFALL);
        if (closestSensor.isReadingListEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        return closestSensor.getAverageReadingsBetweenDates(initialDate, endDate);
    }

    /**
     * @param house is the house we want to get the total rainfall from.
     * @param day   is the date where we want to  measure total rainfall.
     * @return is the total rainfall of the house, as measured by the closest sensor to the house.
     * @Author André
     */
    public double getTotalRainfallOnGivenDay(House house, Date day) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(RAINFALL);
        if (closestSensor.isReadingListEmpty()) {
            throw new IllegalStateException("Warning: Total value could not be calculated - No readings were available.");
        }
        return closestSensor.getTotalValueReadingsOnGivenDay(day);
    }

    /**
     * @param house is the house we want to get the temperature from.
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getHouseAreaTemperature(House house) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(TEMPERATURE);
        return closestSensor.getMostRecentValueReading();
    }

    /**
     * US630 : As a Regular User, I want to get the last coldest day (lower maximum temperature)
     * in the house area in a given period.
     */
    public Date getLastColdestDayInInterval(House house, Date startDate, Date endDate) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(TEMPERATURE);
        return closestSensor.getLastColdestDayInGivenInterval(startDate, endDate);
    }

    /**
     * US 631 - Controller Methods
     * As a Regular User, I want to get the first hottest day (higher maximum temperature)
     * in the house area in a given period.
     **/

    public Date getFirstHottestDayInPeriod(House house, Date startDate, Date endDate) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(TEMPERATURE);
        return closestSensor.getFirstHottestDayInGivenPeriod(startDate, endDate);
    }


    /* US 633 - Controller Methods
       As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given
       period. */

    /**
     * method to get the date with the highest amplitude in the house area between two dates
     *
     * @param house       is the house we want to get the highest temperature amplitude on its area
     * @param initialDate is the date where we want to start measuring temperature (lower limit).
     * @param endDate     is the date where we want to stop measuring temperature(upper limit).
     * @return is the highest temperature amplitude in the house area, in given period, as measured by the closest
     * sensor to the house.
     * @Author Daniela (US633)
     */
    public Date getHighestTempAmplitudeDate(House house, Date initialDate, Date endDate) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(TEMPERATURE);

        if (closestSensor.isReadingListEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings " +
                    "available.");
        }
        return closestSensor.getDateHighestAmplitudeBetweenDates(initialDate, endDate);
    }

    /**
     * method to get the temperature amplitude value in a given day
     *
     * @param house     is the house we want to get the temperature amplitude on its area
     * @param dateInput date for each we want to know the temperature amplitude value
     * @return temperature amplitude value
     * @author Daniela (US633)
     */
    public double getTempAmplitudeValueByDate(House house, Date dateInput) {
        Sensor closestSensor = house.getClosestSensorOfGivenType(TEMPERATURE);
        if (closestSensor.isReadingListEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings " +
                    "available.");
        }
        return Math.floor(closestSensor.getHighestAmplitudeInDate(dateInput) * 10) / 10;
    }

    /**
     * This is a shared methods between many User stories and it checks if the House has its Mother Area defined and
     * if that Mother Area has a valid SensorList
     *
     * @param house - house to get Mother Area from
     * @return true in case both conditions are met
     */
    public boolean isMotherAreaValid(House house) {
        if (house.isMotherAreaNull()) {
            UtilsUI.printMessage(UtilsUI.INVALID_MOTHER_AREA);
            return false;
        }
        if (house.getMotherArea().isSensorListEmpty()) {
            UtilsUI.printMessage(UtilsUI.INVALID_SENSOR_LIST);
            return false;
        }
        return true;
    }

}

