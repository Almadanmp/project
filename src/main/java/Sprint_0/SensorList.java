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
     * Constructor to always create an ArrayList of Arrays.
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

    public boolean containsSensor(Sensor sensor) {
        return sensorList.contains(sensor);
    }

    /**
     * Getter (array of sensors)
     * @return
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
     * @return
     */
    public List<Sensor> getSensorList() {
        List<Sensor> result = this.sensorList;
        return result;
    }

    public void removeSensor(Sensor sensorToRemove) {
        sensorList.remove(sensorToRemove);
    }

    /**
     *
     * @return
     */
    public Sensor getMostRecentlyUsedSensor() {
        List<Sensor> listToTest = this.sensorList;
        int indexMostRecentlyUsedSensor = 0;
        for (int i = 0; i < listToTest.size() - 1; i++) {
            Date firstDate = listToTest.get(i).getReadingList().getMostRecentReading().getmDate().getTime();
            Date secondDate = listToTest.get(i + 1).getReadingList().getMostRecentReading().getmDate().getTime();
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
