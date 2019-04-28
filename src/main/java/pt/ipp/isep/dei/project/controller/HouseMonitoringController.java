package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.io.ui.utils.UtilsUI;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.house.House;
import java.util.Date;
import java.util.List;

/**
 * Controller class for House Monitoring UI
 */


public class HouseMonitoringController {


    /* US 623 - Controller Methods
    As a Regular User, I want to get the average daily rainfall in the house area for a given period (days),as it
    is needed to assess the garden’s watering needs. */

    /**
     * @param initialDate is the date where we want to start measuring average rainfall (lower limit).
     * @param endDate     is the date where we want to stop measuring average rainfall (upper limit).
     * @author Daniela
     */
    public double getAverageRainfallInterval(AreaSensor closestAreaSensor, Date initialDate, Date endDate) {
        return closestAreaSensor.getAverageReadingsBetweenDates(initialDate, endDate);
    }

    /**
     * @param day is the date where we want to  measure total rainfall.
     * @return is the total rainfall of the house, as measured by the closest sensor to the house.
     * @author André
     */
    public double getTotalRainfallOnGivenDay(Date day, AreaSensor closestAreaSensor) {
        List<Reading> sensorReadings = closestAreaSensor.getReadings();
        return ReadingUtils.getValueReadingsInDay(day, sensorReadings);
    }

    /**
     * @return is the most recent temperature reading as measured by the closest sensor to the house.
     */

    public double getHouseAreaTemperature(AreaSensor closestAreaSensor) {
        List<Reading> sensorReadings = closestAreaSensor.getReadings();
        return ReadingUtils.getMostRecentReading(sensorReadings).getValue();
    }

    /**
     * US630 : As a Regular User, I want to get the last coldest day (lower maximum temperature)
     * in the house area in a given period.
     */
    public Date getLastColdestDayInInterval(AreaSensor closestAreaSensor, Date startDate, Date endDate) {
        return closestAreaSensor.getLastColdestDayInGivenInterval(startDate, endDate);
    }

    /**
     * US 631 - Controller Methods
     * As a Regular User, I want to get the first hottest day (higher maximum temperature)
     * in the house area in a given period.
     **/

    public Date getFirstHottestDayInPeriod(AreaSensor closestAreaSensor, Date startDate, Date endDate) {
        return closestAreaSensor.getFirstHottestDayInGivenPeriod(startDate, endDate);
    }

    public Double getReadingValueInGivenDay(AreaSensor closestAreaSensor, Date givenDate) {
        return closestAreaSensor.getReadingValueOfGivenDay(givenDate);
    }


    /* US 633 - Controller Methods
       As Regular User, I want to get the day with the highest temperature amplitude in the house area in a given
       period. */

    public AreaSensor getClosesSensorByTypeToHouse(House house, String sensorType) {
        GeographicArea houseGa = house.getMotherArea();
        return houseGa.getClosestAreaSensorOfGivenType(sensorType, house);
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
    public Date getHighestTempAmplitudeDate(AreaSensor closestAreaSensor, Date initialDate, Date endDate) {
        return closestAreaSensor.getDateHighestAmplitudeBetweenDates(initialDate, endDate);
    }

    /**
     * method to get the temperature amplitude value in a given day
     *
     * @param dateInput date for each we want to know the temperature amplitude value
     * @return temperature amplitude value
     * @author Daniela (US633)
     */
    public double getTempAmplitudeValueByDate(AreaSensor closestAreaSensor, Date dateInput) {
        return Math.floor(closestAreaSensor.getAmplitudeValueFromDate(dateInput) * 10) / 10;
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