package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaService;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomService;

import java.util.Date;
import java.util.List;

/**
 * Controller class for House Monitoring UI
 */


public class HouseMonitoringController {

    /**
     * Returns the current temperature in a given Room.
     *
     * @param roomDTO is the roomDTO we want to get the room from, so that we can get the temperature.
     * @return is the most recent temperature recorded in a room.
     */

    public double getCurrentRoomTemperature(RoomDTO roomDTO, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return roomService.getCurrentRoomTemperature(room);
    }

    /**
     * @param day     is the day we want to check the temperature in.
     * @param roomDTO is the room we want to check the temperature in.
     * @return is the max temperature recorded in a room
     */

    public double getDayMaxTemperature(RoomDTO roomDTO, Date day, ReadingUtils readingUtils, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return roomService.getMaxTemperatureOnGivenDayDb(room, day, readingUtils);
    }

    /**
     * This method receives a room and return the room's name
     *
     * @param roomDTO the DTO of the chosen Room.
     * @return room's name as a string
     **/
    public String getRoomName(RoomDTO roomDTO, RoomService roomService) {
        Room room = RoomMapper.updateHouseRoom(roomDTO, roomService);
        return room.getId();
    }


    /* US 623 - Controller Methods
    As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),as it
    is needed to assess the garden’s watering needs. */

    /**
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate     is the date where we want to stop measuring average rainfall (upper limit).
     * @author Daniela
     */
    public double getAverageRainfallInterval(AreaSensor closestAreaSensor, Date initialDate, Date endDate, ReadingUtils readingUtils) {
        return readingUtils.getAverageReadingsBetweenDates(closestAreaSensor, initialDate, endDate);
    }

    /**
     * @param day is the date where we want to  measure total rainfall.
     * @return is the total rainfall of the house, as measured by the closest sensor to the house.
     * @author André
     */
    public double getTotalRainfallOnGivenDay(Date day, ReadingUtils readingUtils, AreaSensor closestAreaSensor) {

        List<Reading> sensorReadings = closestAreaSensor.getAreaReadings();

        return readingUtils.getValueReadingsInDay(day, sensorReadings);
    }

    /**
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getHouseAreaTemperature(AreaSensor closestAreaSensor) {

        List<Reading> sensorReadings = closestAreaSensor.getAreaReadings();

        return ReadingUtils.getMostRecentReading(sensorReadings).getValue();
    }

    /**
     * US630 : As a Regular User, I want to get the last coldest day (lower maximum temperature)
     * in the house area in a given period.
     */
    public Date getLastColdestDayInInterval(AreaSensor closestAreaSensor, Date startDate, Date endDate, ReadingUtils readingUtils) {


        return readingUtils.getLastColdestDayInGivenInterval(closestAreaSensor, startDate, endDate, readingUtils);
    }

    /**
     * US 631 - Controller Methods
     * As a Regular User, I want to get the first hottest day (higher maximum temperature)
     * in the house area in a given period.
     **/

    public Date getFirstHottestDayInPeriod(AreaSensor closestAreaSensor, Date startDate, Date endDate, ReadingUtils readingUtils) {

        return readingUtils.getFirstHottestDayInGivenPeriod(closestAreaSensor, startDate, endDate);
    }




    /* US 633 - Controller Methods
       As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given
       period. */

    public AreaSensor getClosesSensorByTypeToHouse(House house, GeographicAreaService geographicAreaService, ReadingUtils readingUtils, String sensorType) {
        List<AreaSensor> areaSensors = geographicAreaService.findByGeoAreaSensorsByID(house.getMotherArea().getId());

        return geographicAreaService.getClosestSensorOfGivenType(areaSensors, sensorType, house, readingUtils);
    }

    /**
     * method to get the date with the highest amplitude in the house area between two dates
     *
     * @param initialDate is the date where we want to start measuring temperature (lower limit).
     * @param endDate     is the date where we want to stop measuring temperature(upper limit).
     * @return is the highest temperature amplitude in the house area, in given period, as measured by the closest
     * sensor to the house.
     * @author Daniela (US633)
     */
    public Date getHighestTempAmplitudeDate(AreaSensor closestAreaSensor, Date initialDate, Date endDate, ReadingUtils readingUtils) {

        return readingUtils.getDateHighestAmplitudeBetweenDates(closestAreaSensor, initialDate, endDate);
    }

    /**
     * method to get the temperature amplitude value in a given day
     *
     * @param dateInput date for each we want to know the temperature amplitude value
     * @return temperature amplitude value
     * @author Daniela (US633)
     */
    public double getTempAmplitudeValueByDate(AreaSensor closestAreaSensor, Date dateInput, ReadingUtils readingUtils) {

        return Math.floor(readingUtils.getAmplitudeValueFromDate(closestAreaSensor, dateInput) * 10) / 10;
    }

    /**
     * This is a shared methods between many User stories and it checks if the House has its Mother Area defined and
     * if that Mother Area has a valid AreaSensorList
     *
     * @param house - house to get Mother Area from
     * @return true in case both conditions are met
     */
    public boolean isMotherAreaValid(House house) {
        if (house.isMotherAreaNull()) {
            UtilsUI.printMessage(UtilsUI.INVALID_MOTHER_AREA);
            return false;
        }
        return true;
    }
}