package pt.ipp.isep.dei.project.model.geographicarea;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaSensorUtils {

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
        List<AreaSensor> areaSensorsWithReadings = getAreaSensorsWithReadings(startList);
        if (areaSensorsWithReadings.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }

        AreaSensor areaSensor = areaSensorsWithReadings.get(0);
        List<Reading> readings = areaSensor.getReadings();

        Reading recentReading = ReadingUtils.getMostRecentReading(readings);
        Date mostRecentDate = recentReading.getDate();


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
