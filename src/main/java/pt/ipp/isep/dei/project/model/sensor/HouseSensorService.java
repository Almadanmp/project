package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;

import java.util.*;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class HouseSensorService {

    @Autowired
    HouseSensorRepository houseSensorRepository;

    private List<HouseSensor> houseSensors;

    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of House Sensors.
     */
    public HouseSensorService() {
        this.houseSensors = new ArrayList<>();
    }

    public HouseSensorService(HouseSensorRepository sensorRepository) {
        this.houseSensorRepository = sensorRepository;
        this.houseSensors = new ArrayList<>();
    }

    public List<HouseSensor> getSensors() {
        return houseSensors;
    }

    public void setSensors(List<HouseSensor> sensors) {
        this.houseSensors = sensors;
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    public HouseSensorService getSensorListByType(String name) {
        HouseSensorService containedTypeSensors = new HouseSensorService();
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

    public void save(HouseSensor sensor) {
        this.houseSensorRepository.save(sensor);
    }

    public boolean addReadingToMatchingSensor(String sensorID, Double readingValue, Date readingDate, String unit) {
        Optional<HouseSensor> value = houseSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            HouseSensor houseSensor = value.get();
            Reading reading = new Reading(readingValue, readingDate, unit, houseSensor.getId());
            ReadingService sensorReadingList = houseSensor.getReadingService();
            if (sensorReadingList.contains(reading)) {
                return false;
            }
            houseSensor.addReading(reading);
            houseSensorRepository.save(houseSensor);
            return true;
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
    public ReadingService getReadings() {
        ReadingService finalList = new ReadingService();
        for (HouseSensor s : this.houseSensors) {
            finalList.appendListNoDuplicates(s.getReadingService());
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
        ReadingService readingService = getReadings();
        return readingService.getValuesOfSpecificDayReadings(day);
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

    public boolean sensorExistsInRepository(String sensorID) {
        Optional<HouseSensor> value = houseSensorRepository.findById(sensorID);
        return value.isPresent();
    }

    public boolean sensorFromRepositoryIsActive(String sensorID, Date date) {
        Optional<HouseSensor> value = houseSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            HouseSensor houseSensor = value.get();
            Date startDate = houseSensor.getDateStartedFunctioning();
            if (date.equals(startDate) || date.after(startDate)) {
                return true;
            }
            return false;
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
        if (!(testObject instanceof HouseSensorService)) {
            return false;
        }
        HouseSensorService list = (HouseSensorService) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
