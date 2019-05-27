package pt.ipp.isep.dei.project.model.geographicarea;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class SensorUtils {

    /**
     * Don't let anyone instantiate this class.
     */

    private SensorUtils() {
    }

    /**
     * This method receives an Area Sensor list and checks every sensor in the
     * list for the most recent reading. The area sensor with the most recent reading
     * is considered the most recently used, and is returned.
     *
     * @param startList starting area sensor list
     * @return most recently used Area Sensor
     **/
    public static AreaSensor getMostRecentlyUsedAreaSensor(List<AreaSensor> startList) {
        if (startList.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }

        // Gets the list of sensors with readings

        List<AreaSensor> areaSensorsWithReadings = getAreaSensorsWithReadings(startList);
        if (areaSensorsWithReadings.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }

        // Gets the most recent reading of the first sensor with readings

        AreaSensor areaSensor = areaSensorsWithReadings.get(0);
        List<Reading> readings = areaSensor.getReadings();

        Reading recentReading = ReadingUtils.getMostRecentReading(readings);
        Date mostRecentDate = recentReading.getDate();

        // Compares the most recent reading of the first sensor with readings with the most recent reading of other sensors.

        for (AreaSensor s : areaSensorsWithReadings) {
            List<Reading> sensorReadings = s.getReadings();

            Date testDate = ReadingUtils.getMostRecentReadingDate(sensorReadings);
            if (mostRecentDate.before(testDate)) {
                mostRecentDate = testDate;
                areaSensor = s;
            }
        }
        return areaSensor;
    }

    /**
     * Method that goes through the sensor list and returns a list of those which
     * have readings. The method throws an exception in case the sensorList is empty.
     *
     * @return AreaSensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    static List<AreaSensor> getAreaSensorsWithReadings(List<AreaSensor> areaSensors) {
        List<AreaSensor> finalList = new ArrayList<>();
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = s.getReadings();

            if (!sensorReadings.isEmpty()) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    /**
     * Method that returns the sensors in a given list that are of a given type.
     * @param areaSensors is the list we want to check for sensors of a given type.
     * @param sensorType is the type of sensor we want to check for.
     * @return is the list of sensors in the list that are of the given type.
     */
    public static List<AreaSensor> getAreaSensorsOfGivenType(List<AreaSensor> areaSensors, String sensorType) {
        List<AreaSensor> sensorsOfGivenType = new ArrayList<>();
        for (AreaSensor aS : areaSensors) {
            if (aS.getSensorType().equals(sensorType)) {
                sensorsOfGivenType.add(aS);
            }
        }
        return sensorsOfGivenType;
    }


}
