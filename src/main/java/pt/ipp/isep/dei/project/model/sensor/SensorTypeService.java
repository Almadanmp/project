package pt.ipp.isep.dei.project.model.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.*;

/**
 * This class represents a collection of Sensor Types
 * Methods are organized by functionality
 */

@Service
public class SensorTypeService {

    private static final String STRING_BUILDER = "---------------\n";

    @Autowired
    SensorTypeRepository sensorTypeRepository;

    //CONSTRUCTOR
    public SensorTypeService(SensorTypeRepository sensorTypeRepository) {
        this.sensorTypeRepository = sensorTypeRepository;
    }


    /**
     * Method receives a type Sensor, checks if it already exists in list
     * and adds it in case it does not exist in list.
     *
     * @param type The type of the Sensor
     * @return true in case the type sensor is added, false otherwise
     **/
    public boolean add(SensorType type) {
        SensorType sensorType = sensorTypeRepository.findByName(type.getName());
        if (sensorType != null) {
            sensorTypeRepository.delete(sensorType);
        }
        sensorTypeRepository.save(type);
        return true;
    }

    public SensorType createTypeSensor(String name, String unit) {
        return new SensorType(name, unit);
    }

    /**
     * Checks the type sensor list size and returns the size as int.\
     *
     * @return SensorType size as int
     **/
    public int size() {
        return sensorTypeRepository.findAll().size();
    }

    /**
     * This methods checks if type sensor list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return sensorTypeRepository.findAll().isEmpty();
    }

    /**
     * This method prints all sensor types in a type sensor list.
     *
     * @return a string of sensor types in a list
     */
    public String buildString() {
        StringBuilder result = new StringBuilder(STRING_BUILDER);
        List<SensorType> typeSensor = sensorTypeRepository.findAll();
        if (isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (SensorType s : typeSensor) {
            result.append(s.getId()).append(") Name: ").append(s.getName()).append(" | ").append("Unit: ").
                    append(s.getUnits()).append(" \n");
        }
        result.append(STRING_BUILDER);
        return result.toString();
    }

    /**
     * Method to get a TypeSensor from the Repository through a given id
     *
     * @param id selected id
     * @return Type Sensor corresponding to the given id
     */
    public SensorType getById(long id) {
        Optional<SensorType> value = sensorTypeRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Sensor Type with the selected ID.");
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
