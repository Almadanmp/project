package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.ReadingList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Sensors.
 */
@Entity
public class SensorList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "sensorList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sensor> sensors;


    /**
     * SensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public SensorList() {
        this.sensors = new ArrayList<>();
    }


    public List<Sensor> getSensors() {
        return sensors;
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the SensorList, false otherwise.
     */

    public boolean add(Sensor sensorToAdd) {
        if (!(sensors.contains(sensorToAdd))) {
            return sensors.add(sensorToAdd);
        }
        return false;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public Sensor getMostRecentlyUsedSensor() {
        if (this.sensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        SensorList sensorList = getSensorsWithReadings();
        if (sensorList.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }
        Sensor mostRecent = sensorList.get(0);
        Date recent = mostRecent.getMostRecentReadingDate();
        for (Sensor s : this.sensors) {
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
     * @return SensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    public SensorList getSensorsWithReadings() {
        SensorList finalList = new SensorList();
        if (this.sensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (Sensor s : this.sensors) {
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

    public SensorList getSensorListByType(String name) {
        SensorList containedTypeSensors = new SensorList();
        for (Sensor sensor : this.sensors) {
            if (name.equals(sensor.getSensorTypeName())) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }

    /**
     * Goes through the sensor list, calculates sensors distance to house and
     * returns values in ArrayList.
     *
     * @param house to calculate closest distance
     * @return List of sensors distance to house
     */
    public List<Double> getSensorsDistanceToHouse(House house) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (Sensor sensor : this.sensors) {
            arrayList.add(house.calculateDistanceToSensor(sensor));
        }
        return arrayList;
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

        if (sensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < sensors.size(); i++) {
            Sensor aux = sensors.get(i);
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
    public ReadingList getReadings() {
        ReadingList finalList = new ReadingList();
        for (Sensor s : this.sensors) {
            finalList.appendListNoDuplicates(s.getReadingList());
        }
        return finalList;
    }

    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return SensorList with sensors closest to house.
     **/
    public SensorList getSensorsByDistanceToHouse(House house, double minDist) {
        SensorList finalList = new SensorList();
        for (Sensor s : this.sensors) {
            if (Double.compare(minDist, s.getDistanceToHouse(house)) == 0) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    /**
     * Method checks if sensor list is empty.
     *
     * @return true if empty, false otherwise.
     **/
    public boolean isEmpty() {
        return this.sensors.isEmpty();
    }

    /**
     * Checks the sensor list size and returns the size as int.\
     *
     * @return SensorList size as int
     **/
    public int size() {
        return this.sensors.size();
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     *
     * @param index the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public Sensor get(int index) {
        if (this.sensors.isEmpty()) {
            throw new IndexOutOfBoundsException("The sensor list is empty.");
        }
        return this.sensors.get(index);
    }

    /**
     * Method checks if sensor list contains sensor given as parameter.
     *
     * @param sensor sensor to check.
     * @return returns true if list contains sensor, false if it does not contain sensor.
     */

    public boolean contains(Sensor sensor) {
        return sensors.contains(sensor);
    }

    /**
     * This method goes through every sensor reading list and returns the
     * reading values of a given day. This day is given to method as parameter.
     *
     * @param day date of day the method will use to get reading values
     * @return returns value readings from every sensor from given day
     **/
    public List<Double> getValuesOfSpecificDayReadings(Date day) {
        ReadingList readingList = getReadings();
        return readingList.getValuesOfSpecificDayReadings(day);
    }

    /**
     * Getter (array of sensors)
     *
     * @return array of sensors
     */
    public Sensor[] getElementsAsArray() {
        int sizeOfResultArray = sensors.size();
        Sensor[] result = new Sensor[sizeOfResultArray];
        for (int i = 0; i < sensors.size(); i++) {
            result[i] = sensors.get(i);
        }
        return result;
    }

    public boolean remove(Sensor sensor) {
        if (this.contains(sensor)) {
            sensors.remove(sensor);
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
        if (!(testObject instanceof SensorList)) {
            return false;
        }
        SensorList list = (SensorList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}