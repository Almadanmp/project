package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudeRepo;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudeRepo;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudeRepo;

import java.util.*;

@Service
public class GeographicAreaHouseService {

    @Autowired
    private static GeographicAreaCrudeRepo geographicAreaCrudeRepo;
    @Autowired
    private static AreaTypeCrudeRepo areaTypeCrudeRepo;
    @Autowired
    SensorTypeCrudeRepo sensorTypeCrudeRepo;
    @Autowired
    HouseCrudeRepo houseCrudeRepo;

    public GeographicAreaHouseService(GeographicAreaCrudeRepo geographicAreaCrudeRepo, AreaTypeCrudeRepo areaTypeCrudeRepo, SensorTypeCrudeRepo sensorTypeCrudeRepo) {
        GeographicAreaHouseService.geographicAreaCrudeRepo = geographicAreaCrudeRepo;
        GeographicAreaHouseService.areaTypeCrudeRepo = areaTypeCrudeRepo;
        this.sensorTypeCrudeRepo = sensorTypeCrudeRepo;
    }

    /**
     * This method calculates the average temperature in the house area in a given date.
     *
     * @param date is used to determine the day in which we want to calculate the average.
     * @return the average temperature value for the 24 hours of the given date.
     */
    private double getGeographicAreaAverageTemperature(Date date, House house) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date d1 = calendar.getTime(); // gets date at 00:00:00

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date d2 = calendar.getTime(); // gets date at 23:59:59

        // gets and returns average readings on the closest AreaSensor to the house
        AreaSensor houseClosestSensor = house.getMotherArea().getClosestAreaSensorOfGivenType("temperature", house);
        return getAverageReadingsBetweenFormattedDates(d1, d2, houseClosestSensor);
    }

    Double getAverageReadingsBetweenFormattedDates(Date minDate, Date maxDate, AreaSensor areaSensor) {
        List<Reading> sensorReadingsBetweenDates = getReadingListBetweenFormattedDates(minDate, maxDate, areaSensor);
        if (sensorReadingsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        return AreaSensor.getSensorReadingAverageValue(sensorReadingsBetweenDates);
    }

    List<Reading> getReadingListBetweenFormattedDates(Date initialDate, Date finalDate, AreaSensor areaSensor) {
        List<Reading> finalList = new ArrayList<>();
        List<Reading> result = areaSensor.getReadings();
        for (Reading r : result) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(r);
            }
        }
        return finalList;
    }

    public List<Reading> getReadingsBelowCategoryILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsBelowCategoryIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsBelowCategoryIIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIIICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category I.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 2;
        return reading.getValue() < minT;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category II.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 3;
        return reading.getValue() < minT;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category III.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIIICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 4;
        return reading.getValue() < minT;
    }

    public List<Reading> getReadingsAboveCategoryILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryICalculusAboveAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsAboveCategoryIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIICalculusAboveAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsAboveCategoryIIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIIICalculusAboveAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category I.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryICalculusAboveAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 2;
        return reading.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category II.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIICalculusAboveAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 3;
        return reading.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category III.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIIICalculusAboveAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 4;
        return reading.getValue() > minT;
    }
}
