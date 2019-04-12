package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.HouseSensorRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Class that groups a number of Sensors.
 */
@Service
public class HouseSensorService {

    @Autowired
    HouseSensorRepository houseSensorRepository;

    @Autowired
    SensorTypeRepository sensorTypeRepository;

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

    public boolean addSensorType(SensorType sensorType) {
        sensorTypeRepository.save(sensorType);
        return true;
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

    public HouseSensor createSensor(String id, String name, String sensorName, String sensorUnit, Date dateStartedFunctioning,
                                    String roomId) {

        SensorType sensorType = getTypeSensorByName(sensorName, sensorUnit);
        sensorTypeRepository.save(sensorType);

        return new HouseSensor(id, name, sensorType, dateStartedFunctioning, roomId);
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

    public HouseSensorService getSensorListByType(String name) {
        HouseSensorService containedTypeSensors = new HouseSensorService();
        for (HouseSensor sensor : this.houseSensors) {
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
