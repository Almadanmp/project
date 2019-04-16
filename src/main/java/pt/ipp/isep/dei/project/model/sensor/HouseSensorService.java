package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.*;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class HouseSensorService {

    @Autowired
    HouseSensorRepository houseSensorRepository;

    @Autowired
    SensorTypeRepository sensorTypeRepository;


    private static final String TEMPERATURE = "temperature";
    private static final String noTempReadings = "There aren't any temperature readings available.";

    private List<HouseSensor> houseSensors;

    /**
     * AreaSensorList() empty constructor that initializes an ArrayList of House Sensors.
     */
    public HouseSensorService() {
        this.houseSensors = new ArrayList<>();
    }

    public HouseSensorService(HouseSensorRepository sensorRepository, SensorTypeRepository sensorTypeRepository) {
        this.houseSensorRepository = sensorRepository;
        this.sensorTypeRepository = sensorTypeRepository;
        this.houseSensors = new ArrayList<>();
    }

    public void save(HouseSensor sensor) {
        Optional<SensorType> sensorType = sensorTypeRepository.findByName(sensor.getSensorType().getName());

        if (sensorType.isPresent()) {
            sensor.setSensorType(sensorType.get());
        } else {
            SensorType newSensorType = sensor.getSensorType();
            sensorTypeRepository.save(newSensorType);
            sensor.setSensorType(newSensorType);
        }
        this.houseSensorRepository.save(sensor);
    }

    public List<HouseSensor> getAllSensor() {
        return houseSensorRepository.findAll();
    }

    public List<HouseSensor> getAllByRoomId(String roomName) {
        return houseSensorRepository.findAllByRoomId(roomName);
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    public String buildString(List<HouseSensor> houseSensors) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));

        if (houseSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (HouseSensor hS : houseSensors) {
            result.append(hS.getId()).append(hS.getName()).append(" | ");
            result.append("Type: ").append(hS.getSensorTypeName()).append(" | ")
                    .append(hS.printActive()).append("\n");
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
    public List<Reading> getReadings(List<HouseSensor> roomSensors, ReadingService readingService) {
        List<Reading> finalList = new ArrayList<>();
        for (HouseSensor s : roomSensors) {
            finalList.addAll(readingService.findReadingsBySensorID(s.getId()));
        }
        return finalList;
    }

    /**
     * Method checks if sensor list is empty.
     *
     * @return true if empty, false otherwise.
     **/
    public boolean isEmpty() {
        return getAllSensor().isEmpty();
    }


    /**
     * This method goes through every sensor reading list and returns the
     * reading values of a given day. This day is given to method as parameter.
     *
     * @param day date of day the method will use to get reading values
     * @return returns value readings from every sensor from given day
     **/
    public List<Double> getValuesOfSpecificDayReadings(List<HouseSensor> houseSensor, Date day, ReadingService readingService) {
        List<Reading> sensorReadings = new ArrayList<>();
        for (HouseSensor hS : houseSensor) {
            sensorReadings.addAll(readingService.findReadingsBySensorID(hS.getId()));
        }
        return readingService.getValuesOfSpecificDayReadingsDb(sensorReadings, day);
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

    public boolean addWithPersistence(HouseSensor sensorToAdd) {
        Optional<HouseSensor> aux = houseSensorRepository.findById(sensorToAdd.getId());
        if (aux.isPresent()) {
            HouseSensor sensor = aux.get();
            sensor = sensorToAdd;
            houseSensorRepository.save(sensor);
        }
        houseSensorRepository.save(sensorToAdd);
        return true;
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
     * Method receives a date of a given day and looks for the max temperature
     * recorded in every sensor that measure temperature, in the room.
     *
     * @param day where we want to look for max temperature
     * @return the max temperature recorded in a sensor that measures temperature or
     * NaN in case there are no readings in the given day or
     * in case the room has no readings whatsoever
     **/
    public double getMaxTemperatureOnGivenDayDb(Room room, Date day, ReadingService readingService) throws NoSuchElementException {
        List<HouseSensor> houseSensors = houseSensorRepository.findAllByRoomId(room.getId());
        List<HouseSensor> tempSensors = getSensorsOfGivenType(TEMPERATURE, houseSensors);
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException(noTempReadings);
        } else {
            List<Double> values = getValuesOfSpecificDayReadings(tempSensors, day, readingService);
            if (!values.isEmpty()) {
                return Collections.max(values);
            }
            throw new NoSuchElementException(noTempReadings);
        }
    }

    /**
     * Method that goes through every Sensor in the room of the type "temperature" returning
     * the value of the most recent reading.
     *
     * @return the most recent temperature reading or NaN in case the room has no temperature
     * sensors and/or when temperature sensors have no readings
     */

    public double getCurrentRoomTemperature(Room room, ReadingService readingService) {
        List<HouseSensor> houseSensors = houseSensorRepository.findAllByRoomId(room.getName());
        List<HouseSensor> tempSensors = getSensorsOfGivenType(TEMPERATURE, houseSensors);
        if (tempSensors.isEmpty()) {
            throw new IllegalArgumentException(noTempReadings);
        }
        List<Reading> sensorReadings = getReadings(tempSensors, readingService);
        return readingService.getMostRecentValue(sensorReadings);
    }


    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public SensorType getTypeSensorByName(String name, String unit) {
        Optional<SensorType> value = sensorTypeRepository.findByName(name);
        if (value.isPresent()) {
            return value.get();
        }
        return new SensorType(name, unit);
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    public List<HouseSensor> getSensorsOfGivenType(String name, List<HouseSensor> houseSensors) {
        List<HouseSensor> containedTypeSensors = new ArrayList<>();
        for (HouseSensor sensor : houseSensors) {
            if (name.equals(sensor.getSensorTypeName())) {
                containedTypeSensors.add(sensor);
            }
        }
        return containedTypeSensors;
    }

    public List<HouseSensor> getSensors() {
        return houseSensors;
    }


}
