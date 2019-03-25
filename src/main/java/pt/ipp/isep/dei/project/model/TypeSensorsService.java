package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.TypeSensorRepository;

import java.util.List;


@Service
public class TypeSensorsService {

    @Autowired
    TypeSensorRepository typeSensorRepository;


    public TypeSensor createTypeSensor(String name, String unit) {
        return new TypeSensor(name, unit);
    }


    /**
     * Method receives a type Sensor, checks if it already exists in list
     * and adds it in case it does not exist in list.
     *
     * @param typeSensor The type of the Sensor
     * @return true in case the type sensor is added, false otherwise
     **/
    public boolean add(TypeSensor typeSensor) {
        TypeSensor typesensor = typeSensorRepository.findByName(typeSensor.getName());
        if (typesensor != null) {
            typeSensorRepository.delete(typeSensor);
        }
        typeSensorRepository.save(typeSensor);
        return true;
    }

    public List<TypeSensor> getAll() {
        return typeSensorRepository.findAll();
    }

    /**
     * Checks the type sensor list size and returns the size as int.\
     *
     * @return TypeSensor size as int
     **/
    public int size() {
        return typeSensorRepository.findAll().size();
    }

    /**
     * This methods checks if type sensor list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * This method prints all sensor types in a type sensor list.
     *
     * @return a string of sensor types in a list
     */
    public String getAllAsString() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (isEmpty()) {
            return "There are no Sensor Types Configured\n";
        }
        Iterable<TypeSensor> typeSensors = typeSensorRepository.findAll();
        for (TypeSensor tS : typeSensors) {
            result.append(tS.getId()).append(") Description: ").append(tS.getName()).append(" | ");
            result.append("Unit: ").append(tS.getUnits()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }
}
