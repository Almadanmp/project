package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Sensors.
 */
@Entity
public class HouseSensorList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "houseSensorList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HouseSensor> houseSensors;

    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of House Sensors.
     */
    public HouseSensorList() {
        this.houseSensors = new ArrayList<>();
    }

    public List<HouseSensor> getSensors() {
        return houseSensors;
    }

    public void setSensors(List<HouseSensor> sensors) {
        this.houseSensors = sensors;
    }

    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public HouseSensor getMostRecentlyUsedSensor() {
        if (this.houseSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        HouseSensorList sensorList = getSensorsWithReadings();
        if (sensorList.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }
        HouseSensor mostRecent = sensorList.get(0);
        Date recent = mostRecent.getMostRecentReadingDate();
        for (HouseSensor s : this.houseSensors) {
            Date test = s.getMostRecentReadingDate();
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }

    /**
     * Method that goes through the sensor list and returns a list of those which
     * have readings. The method throws an exception in case the sensorList is empty.
     *
     * @return AreaSensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    public HouseSensorList getSensorsWithReadings() {
        HouseSensorList finalList = new HouseSensorList();
        if (this.houseSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (HouseSensor s : this.houseSensors) {
            if (!s.isReadingListEmpty()) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    public HouseSensorList getSensorListByType(String name) {
        HouseSensorList containedTypeSensors = new HouseSensorList();
        for (HouseSensor sensor : this.houseSensors) {
            if (name.equals(sensor.getSensorTypeName())) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the AreaSensorList, false otherwise.
     */

    public boolean add(HouseSensor sensorToAdd) {
        if (!(houseSensors.contains(sensorToAdd))) {
            return houseSensors.add(sensorToAdd);
        }
        return false;
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));

        if (houseSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < houseSensors.size(); i++) {
            HouseSensor aux = houseSensors.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Type: ").append(aux.getSensorTypeName()).append(" | ")
                    .append(aux.printActive()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method that goes through every sensor in the sensor list and gets
     * every reading within that sensor. In the end we will get a Reading list
     * that contains every reading from every sensor of the sensor list.
     *
     * @return a list with all readings from sensor list
     **/
    public HouseReadingList getReadings() {
        HouseReadingList finalList = new HouseReadingList();
        for (HouseSensor s : this.houseSensors) {
            finalList.appendListNoDuplicates(s.getReadingList());
        }
        return finalList;
    }

    /**
     * Method checks if sensor list is empty.
     *
     * @return true if empty, false otherwise.
     **/
    public boolean isEmpty() {
        return this.houseSensors.isEmpty();
    }

    /**
     * Checks the sensor list size and returns the size as int.\
     *
     * @return AreaSensorList size as int
     **/
    public int size() {
        return this.houseSensors.size();
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     *
     * @param index the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public HouseSensor get(int index) {
        if (this.houseSensors.isEmpty()) {
            throw new IndexOutOfBoundsException("The sensor list is empty.");
        }
        return this.houseSensors.get(index);
    }

    /**
     * Method checks if sensor list contains sensor given as parameter.
     *
     * @param sensor sensor to check.
     * @return returns true if list contains sensor, false if it does not contain sensor.
     */

    public boolean contains(HouseSensor sensor) {
        return houseSensors.contains(sensor);
    }

    /**
     * This method goes through every sensor reading list and returns the
     * reading values of a given day. This day is given to method as parameter.
     *
     * @param day date of day the method will use to get reading values
     * @return returns value readings from every sensor from given day
     **/
    public List<Double> getValuesOfSpecificDayReadings(Date day) {
        HouseReadingList readingList = getReadings();
        return readingList.getValuesOfSpecificDayReadings(day);
    }

    /**
     * Getter (array of sensors)
     *
     * @return array of sensors
     */
    public HouseSensor[] getElementsAsArray() {
        int sizeOfResultArray = houseSensors.size();
        HouseSensor[] result = new HouseSensor[sizeOfResultArray];
        for (int i = 0; i < houseSensors.size(); i++) {
            result[i] = houseSensors.get(i);
        }
        return result;
    }

    public boolean remove(HouseSensor sensor) {
        if (this.contains(sensor)) {
            houseSensors.remove(sensor);
            return true;
        }
        return false;
    }


    /**
     * Method 'equals' for comparison between objects of the same class
     *
     * @param testObject is the object we want to test.
     * @return true if it's equal, false otherwise.
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseSensorList)) {
            return false;
        }
        HouseSensorList list = (HouseSensorList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
