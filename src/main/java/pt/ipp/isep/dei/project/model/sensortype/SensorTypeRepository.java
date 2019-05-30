package pt.ipp.isep.dei.project.model.sensortype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.utils.LogUtils;
import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.dto.mappers.SensorTypeMapper;
import pt.ipp.isep.dei.project.repository.SensorTypeCrudRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents a collection of Sensor Types
 * Methods are organized by functionality
 */

@Service
public class SensorTypeRepository {

    private static final String STRING_BUILDER = "---------------\n";

    @Autowired
    SensorTypeCrudRepo repository;

    /**
     * Method receives a type Sensor, checks if it already exists in list
     * and adds it in case it does not exist in list.
     *
     * @param type The type of the Sensor
     * @return true in case the type sensor is added, false otherwise
     **/
    public boolean add(SensorType type) {
        Optional<SensorType> sensorType = repository.findByName(type.getName());
        if (sensorType.isPresent()) {
            return false;
        }
        repository.save(type);
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
        return repository.findAll().size();
    }

    /**
     * This methods checks if type sensor list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return repository.findAll().isEmpty();
    }

    /**
     * This method prints all sensor types in a type sensor list.
     *
     * @return a string of sensor types in a list
     */
    public String buildString() {
        StringBuilder result = new StringBuilder(STRING_BUILDER);
        List<SensorType> typeSensor = repository.findAll();
        if (isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (SensorType s : typeSensor) {
            result.append("Name: ").append(s.getName()).append(" | ").append("Unit: ").
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
    public SensorType getById(String id) {
        Optional<SensorType> value = repository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Sensor Type with the selected ID.");
    }


    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public SensorType getTypeSensorByName(String name, String unit) {
        Logger logger = LogUtils.getLogger("SensorTypeLogger", "resources/logs/sensorTypeLogHtml.html", Level.FINE);
        Optional<SensorType> value = repository.findByName(name);
        if (!(value.isPresent())) {
            String message = "The Sensor Type " + name + "with the unit " + unit + " does not yet exist in the Data Base. Please create the Sensor" +
                    "Type first.";
            logger.fine(message);
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.get();
        }
    }

    public List<SensorTypeDTO> getAllSensorTypeDTO() {
        List<SensorType> typeAux = repository.findAll();
        List<SensorTypeDTO> finalList = new ArrayList<>();
        if (typeAux != null) {
            for (SensorType st : typeAux) {
                finalList.add(SensorTypeMapper.objectToDTO(st));
            }
            return finalList;
        }
        return new ArrayList<>();
    }

}
