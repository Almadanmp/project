package Sprint_0;

import java.util.*;

/**
 * Represents a List of Sensors.
 */
public class SensorList {
    private List<Sensor> mSensorList;

    /**
     * Constructor of an ArrayList of Arrays.
     * @param sensorsToAdd
     */

    public SensorList(Sensor[] sensorsToAdd) {
        mSensorList = new ArrayList<>();
        if (sensorsToAdd.length > 0) {
            for (int i = 0; i < sensorsToAdd.length; i++) {
                mSensorList.add(sensorsToAdd[i]);
            }
        }
    }

    /**
     * Constructor to always create an Array of Sensors.
     * @param sensorToAdd
     */
    public SensorList(Sensor sensorToAdd) {
        mSensorList = new ArrayList<>();
        mSensorList.add(sensorToAdd);
    }

    /**
     *Method to Add a sensor only if it's not contained in the list already.
     * @param sensorToAdd
     * @return
     */

    public boolean addSensor(Sensor sensorToAdd) {
        if (!(mSensorList.contains(sensorToAdd))) {
            mSensorList.add(sensorToAdd);
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
        return mSensorList.contains(sensor);
    }

    /**
     * Getter (array of sensors)
     * @return array of sensors
     */
    public Sensor[] getSensors() {
        int sizeOfResultArray = mSensorList.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < mSensorList.size(); i++) {
            result[i] = mSensorList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of sensors)
     * @return list of sensors
     */
    public List<Sensor> getSensorList() {
        return this.mSensorList;
    }

    /**
     * Removes a sensor from the Sensor List
     * @param sensorToRemove
     */
    public void removeSensor(Sensor sensorToRemove) {
        mSensorList.remove(sensorToRemove);
    }

    /**
     * Method to find the most recently used sensor in the sensor list
     * @return the most recently used sensor
     */
    public Sensor getMostRecentlyUsedSensor() {
        List<Sensor> listToTest = this.mSensorList;
        int indexMostRecentlyUsedSensor = 0;
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Date firstDate = listToTest.get(i).getReadingList().getMostRecentReading().getmDate();
            Date secondDate = listToTest.get(i + 1).getReadingList().getMostRecentReading().getmDate();
            if (firstDate.before(secondDate)) {
                indexMostRecentlyUsedSensor = i + 1;
            }
        }return listToTest.get(indexMostRecentlyUsedSensor);
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
        return Arrays.equals(this.getSensors(), list.getSensors());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
