package Sprint_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Represents a List of Sensors.
 */
public class SensorList {
    private List<Sensor> sensorList;

    /**
     * Constructor of an ArrayList of Arrays.
     * @param sensorsToAdd
     */

    public SensorList(Sensor[] sensorsToAdd) {
        sensorList = new ArrayList<>();
        if (sensorsToAdd.length > 0) {
            for (int i = 0; i < sensorsToAdd.length; i++) {
                sensorList.add(sensorsToAdd[i]);
            }
        }
    }

    /**
     * Constructor to always create an Array of Sensors.
     * @param sensorToAdd
     */
    public SensorList(Sensor sensorToAdd) {
        sensorList = new ArrayList<>();
        sensorList.add(sensorToAdd);
    }

    /**
     *Method to Add a sensor only if it's not contained in the list already.
     * @param sensorToAdd
     * @return
     */

    public boolean addSensor(Sensor sensorToAdd) {
        if (!(sensorList.contains(sensorToAdd))) {
            sensorList.add(sensorToAdd);
            return true;
        }
        return false;
    }

    /**
     * Checks if a Sensor is inside the Sensor List
     * @param sensor
     * @return
     */
    public boolean containsSensor(Sensor sensor) {
        return sensorList.contains(sensor);
    }

    /**
     * Getter (array of sensors)
     * @return array of sensors
     */
    public Sensor[] getSensors() {
        int sizeOfResultArray = sensorList.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < sensorList.size(); i++) {
            result[i] = sensorList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of sensors)
     * @return list of sensors
     */
    public List<Sensor> getSensorList() {
        List<Sensor> result = this.sensorList;
        return result;
    }

    /**
     * Removes a sensor from the Sensor List
     * @param sensorToRemove
     */
    public void removeSensor(Sensor sensorToRemove) {
        sensorList.remove(sensorToRemove);
    }

    /**
     * Method to find the most recently used sensor in the sensor list
     * @return the most recently used sensor
     */
    public Sensor getMostRecentlyUsedSensor() {
        List<Sensor> listToTest = this.sensorList;
        int indexMostRecentlyUsedSensor = 0;
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Date firstDate = listToTest.get(i).getReadingList().getMostRecentReading().getmDate();
            Date secondDate = listToTest.get(i + 1).getReadingList().getMostRecentReading().getmDate();
            if (firstDate.before(secondDate)) {
                indexMostRecentlyUsedSensor = i + 1;
            }
        }
        Sensor result = listToTest.get(indexMostRecentlyUsedSensor);
        return result;
    }

    /**
     * Specific Methods.
     * @param testObject
     * @return
     */
    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof SensorList)) {
            return false;
        }
        SensorList list = (SensorList) testObject;
        if (Arrays.equals(this.getSensors(), list.getSensors())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
