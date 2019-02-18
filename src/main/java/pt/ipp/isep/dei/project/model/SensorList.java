package pt.ipp.isep.dei.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Sensors.
 */

public class SensorList {

    private List<Sensor> mSensorList;
    private String mStringEnhancer = "---------------\n";

    public SensorList() {
        this.mSensorList = new ArrayList<>();
    }


    /**
     * Constructor to always create an Array of Sensors.
     *
     * @param sensorToAdd
     */
    public SensorList(Sensor sensorToAdd) {
        mSensorList = new ArrayList<>();
        mSensorList.add(sensorToAdd);
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd
     * @return
     */

    public boolean addSensor(Sensor sensorToAdd) {
        if (!(mSensorList.contains(sensorToAdd))) {
            mSensorList.add(sensorToAdd);
        }
        return false;
    }

    /**
     * Checks if a Sensor is inside the Sensor List
     *
     * @param sensor
     * @return
     */
    public boolean containsSensor(Sensor sensor) {
        return mSensorList.contains(sensor);
    }

    /**
     * Getter (array of sensors)
     *
     * @return array of sensors
     */
    Sensor[] getSensors() {
        int sizeOfResultArray = mSensorList.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < mSensorList.size(); i++) {
            result[i] = mSensorList.get(i);
        }
        return result;
    }

    /**
     * Gettter (list of sensors)
     *
     * @return list of sensors
     */
    public List<Sensor> getSensorList() {
        return this.mSensorList;
    }

    /**
     * Removes a sensor from the Sensor List
     *
     * @param sensorToRemove
     */
    void removeSensor(Sensor sensorToRemove) {
        mSensorList.remove(sensorToRemove);
    }


    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public Sensor getMostRecentlyUsedSensor() {
        Date d1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("01/00/1900");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        Sensor error = new Sensor("emptySensor", new TypeSensor("type", " "), d1);
        if (this.mSensorList.isEmpty() || !this.hasReadings()) {
            return error;
        }
        Sensor mostRecent = this.mSensorList.get(0);
        Date recent = mostRecent.getReadingList().getMostRecentReading().getmDate();
        for (Sensor s : this.mSensorList) {
            Date test = s.getReadingList().getMostRecentReading().getmDate();
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }

    public boolean hasReadings() {
        for (Sensor s : this.mSensorList) {
            ReadingList readingList = s.getReadingList();
            if (!readingList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param name name of the sensor to find in the list.
     * @return returns true if a Sensor with the same name as @param name exists in the Sensor List
     */

    public boolean doesSensorListContainSensorByName(String name) {
        for (Sensor s : mSensorList) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    List<Sensor> getSensorListByType(String name) {
        List<Sensor> containedTypeSensors = new ArrayList<>();
        for (Sensor sensor : this.mSensorList) {
            if (name.equals(sensor.getTypeSensor().getName())) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }

    /**
     * @param date1 Date when we wish to check which sensors from the list are active at.
     * @param ga    Geographical Area where we wish to get the sensors from.
     * @return builds a list of sensors with the sensors that are active at the precise date the one introduced as parameter.
     */

    List<Sensor> getSensorsInGAAtACertainTimePeriod(Date date1, GeographicArea ga) {
        List<Sensor> finalList = new ArrayList<>();
        for (Sensor s : mSensorList) {
            if (s.isSensorActiveOnGivenDate(date1) && s.isSensorContainedInArea(ga)) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     */
    public String buildSensorWholeListString(SensorList sensorList) {
        StringBuilder result = new StringBuilder(new StringBuilder(mStringEnhancer));

        if (sensorList.getSensorList().isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < sensorList.getSensorList().size(); i++) {
            Sensor aux = sensorList.getSensorList().get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Type: ").append(aux.getTypeSensor().getName()).append("\n");
        }
        result.append(mStringEnhancer);
        return result.toString();
    }

    /**
     * @return true if the list is empty.
     */
    private boolean checkIfListInvalid() {
        return (this.mSensorList.isEmpty());
    }

    /**
     * Method that goes through every sensor in the sensor list and gets
     * every reading within that sensor. In the end we will get a Reading list
     * that contains every reading from every sensor of the sensor list.
     *
     * @return a list with all readings from sensor list
     **/
    public ReadingList getReadings() {
        ReadingList finalList = new ReadingList();
        if (this.mSensorList.isEmpty()) {
            return finalList;
        }
        for (Sensor s : this.mSensorList) {
            for (Reading r : s.getReadingList().getListOfReadings()) {
                finalList.addReading(r);
            }
        }
        return finalList;
    }

    /**
     * Method 'equals' for comparisson between objects of the same class
     *
     * @param testObject
     * @return boolean
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
