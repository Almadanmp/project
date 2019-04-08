package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class AreaSensorList {

    @Autowired
    AreaSensorRepository areaSensorRepository;

    private List<AreaSensor> areaSensors;


    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public AreaSensorList() {
        this.areaSensors = new ArrayList<>();
    }

    public AreaSensorList(AreaSensorRepository areaSensorRepository) {
        this.areaSensors = new ArrayList<>();
        this.areaSensorRepository = areaSensorRepository;
    }

    public List<AreaSensor> getAreaSensors() {
        return areaSensors;
    }


    public void setAreaSensors(List<AreaSensor> areaSensors) {
        this.areaSensors = areaSensors;
    }

    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public AreaSensor getMostRecentlyUsedSensor() {
        if (this.areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        AreaSensorList areaSensorList = getSensorsWithReadings();
        if (areaSensorList.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }
        AreaSensor mostRecent = areaSensorList.get(0);
        Date recent = mostRecent.getMostRecentReadingDate();
        for (AreaSensor s : this.areaSensors) {
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
    public AreaSensorList getSensorsWithReadings() {
        AreaSensorList finalList = new AreaSensorList();
        if (this.areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (AreaSensor s : this.areaSensors) {
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

    public AreaSensorList getSensorListByType(String name) {
        AreaSensorList containedTypeSensors = new AreaSensorList();
        for (AreaSensor areaSensor : this.areaSensors) {
            if (name.equals(areaSensor.getSensorTypeName())) {
                containedTypeSensors.add(areaSensor);
            }
        }
        return containedTypeSensors;
    }


    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param areaSensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the AreaSensorList, false otherwise.
     */

    public boolean add(AreaSensor areaSensorToAdd) {
        if (!(areaSensors.contains(areaSensorToAdd))) {
            return areaSensors.add(areaSensorToAdd);
        }
        return false;
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
        for (AreaSensor areaSensor : this.areaSensors) {
            arrayList.add(house.calculateDistanceToSensor(areaSensor));
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

        if (areaSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < areaSensors.size(); i++) {
            AreaSensor aux = areaSensors.get(i);
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
    public AreaReadingList getReadings() {
        AreaReadingList finalList = new AreaReadingList();
        for (AreaSensor s : this.areaSensors) {
            finalList.appendListNoDuplicates(s.getAreaReadingList());
        }
        return finalList;
    }

    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return AreaSensorList with sensors closest to house.
     **/
    public AreaSensorList getSensorsByDistanceToHouse(House house, double minDist) {
        AreaSensorList finalList = new AreaSensorList();
        for (AreaSensor s : this.areaSensors) {
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
        return this.areaSensors.isEmpty();
    }

    /**
     * Checks the sensor list size and returns the size as int.\
     *
     * @return AreaSensorList size as int
     **/
    public int size() {
        return this.areaSensors.size();
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     *
     * @param index the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public AreaSensor get(int index) {
        if (this.areaSensors.isEmpty()) {
            throw new IndexOutOfBoundsException("The sensor list is empty.");
        }
        return this.areaSensors.get(index);
    }

    /**
     * Method checks if sensor list contains sensor given as parameter.
     *
     * @param areaSensor sensor to check.
     * @return returns true if list contains sensor, false if it does not contain sensor.
     */

    public boolean contains(AreaSensor areaSensor) {
        return areaSensors.contains(areaSensor);
    }

    /**
     * This method goes through every sensor reading list and returns the
     * reading values of a given day. This day is given to method as parameter.
     *
     * @param day date of day the method will use to get reading values
     * @return returns value readings from every sensor from given day
     **/
    public List<Double> getValuesOfSpecificDayReadings(Date day) {
        AreaReadingList areaReadingList = getReadings();
        return areaReadingList.getValuesOfSpecificDayReadings(day);
    }

    /**
     * Getter (array of sensors)
     *
     * @return array of sensors
     */
    public AreaSensor[] getElementsAsArray() {
        int sizeOfResultArray = areaSensors.size();
        AreaSensor[] result = new AreaSensor[sizeOfResultArray];
        for (int i = 0; i < areaSensors.size(); i++) {
            result[i] = areaSensors.get(i);
        }
        return result;
    }

    public boolean remove(AreaSensor areaSensor) {
        if (this.contains(areaSensor)) {
            areaSensors.remove(areaSensor);
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
        if (!(testObject instanceof AreaSensorList)) {
            return false;
        }
        AreaSensorList list = (AreaSensorList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}