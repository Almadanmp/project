package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;

import java.util.*;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class AreaSensorService {

    @Autowired
    AreaSensorRepository areaSensorRepository;

    private List<AreaSensor> areaSensors;


    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public AreaSensorService() {
        this.areaSensors = new ArrayList<>();
    }

    public AreaSensorService(AreaSensorRepository areaSensorRepository) {
        this.areaSensors = new ArrayList<>();
        this.areaSensorRepository = areaSensorRepository;
    }

    public List<AreaSensor> getAreaSensors() {
        return areaSensors;
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
        AreaSensorService areaSensorService = getSensorsWithReadings();
        if (areaSensorService.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }
        AreaSensor mostRecent = areaSensorService.get(0);
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
    public AreaSensorService getSensorsWithReadings() {
        AreaSensorService finalList = new AreaSensorService();
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

    public AreaSensorService getSensorListByType(String name) {
        AreaSensorService containedTypeSensors = new AreaSensorService();
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
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param areaSensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the AreaSensorList, false otherwise.
     */

    public boolean addWithPersist(AreaSensor areaSensorToAdd) {
        if (!(areaSensors.contains(areaSensorToAdd))) {
            areaSensorRepository.save(areaSensorToAdd);
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

        for (AreaSensor as : this.areaSensors) {
            result.append(as.getId()).append(") Name: ").append(as.getName()).append(" | ");
            result.append("Type: ").append(as.getSensorTypeName()).append(" | ")
                    .append(as.printActive()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return AreaSensorList with sensors closest to house.
     **/
    public AreaSensorService getSensorsByDistanceToHouse(House house, double minDist) {
        AreaSensorService finalList = new AreaSensorService();
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
     * Method to add a reading to a matching sensor contained in the repository. The sensor is found by its ID.
     * <p>
     * sensorList   is the sensorList we want to add a reading to.
     * <p>
     * to avoid duplicated readings we will check first if the reading list of the sensor already has the reading to import
     * (has the same date) and will only add it if false.
     *
     * @param sensorID     is the ID of the sensor we want to add a reading to.
     * @param readingValue is the value of the reading we want to add.
     * @param readingDate  is the date of the reading we want to add.
     * @return is true if the reading is added (if there is a sensor with an ID that matches the given ID), false
     * if there is no sensor with that ID.
     */

    public boolean addReadingToMatchingSensor(String sensorID, Double readingValue, Date readingDate, String unit) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            Reading reading = new Reading(readingValue, readingDate, unit, sensorID);
            ReadingService sensorReadingService = areaSensor.getReadingService();
            if (sensorReadingService.contains(reading)) {
                return false;
            }
            areaSensor.addReading(reading);
            areaSensorRepository.save(areaSensor);
            return true;
        }
        return false;
    }


    /**
     * This method receives a sensor ID, checks if that sensor exists in the repository.
     *
     * @param sensorID String of sensor ID
     * @return true in case the sensor exists, false otherwise.
     **/
    public boolean sensorExistsInRepository(String sensorID) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        return value.isPresent();
    }

    /**
     * This method receives a sensor ID and a Date checks if that sensor exists in the repository
     * and if it was active at the moment of the given date.
     *
     * @param sensorID String of sensor ID
     * @param date date to test
     * @return true in case the sensor exists and it was active during the given date, false otherwise.
     **/
    public boolean sensorFromRepositoryIsActive(String sensorID, Date date) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if(value.isPresent()){
            AreaSensor areaSensor = value.get();
            Date startDate = areaSensor.getDateStartedFunctioning();
            if(date.equals(startDate) || date.after(startDate)){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean remove(AreaSensor areaSensor) {
        if (this.contains(areaSensor)) {
            areaSensors.remove(areaSensor);
            return true;
        }
        return false;
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
        if (!(testObject instanceof AreaSensorService)) {
            return false;
        }
        AreaSensorService list = (AreaSensorService) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}