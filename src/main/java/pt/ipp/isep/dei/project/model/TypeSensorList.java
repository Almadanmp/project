package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeSensorList {
    private List<TypeSensor> typeSensors;

    public TypeSensorList() {
        typeSensors = new ArrayList<>();
    }

    /**
     * Method receives a type Sensor, checks if it already exists in list
     * and adds it in case it does not exist in list.
     * @param typeSensor The type of the Sensor
     * @return true in case the type sensor is added, false otherwise
     **/
    public boolean add(TypeSensor typeSensor) {
        if (!typeSensors.contains(typeSensor)) {
            typeSensors.add(typeSensor);
            return true;
        }
        return false;
    }

    /**
     * Method returns a list with all sensor types.
     * @return List that contains sensor types
     **/
    public List<TypeSensor> getTypeSensorList() {
        return this.typeSensors;
    }

    /**
     * This method prints all sensor types in a type sensor list.
     *
     * @return a string of sensor types in a list
     */
    public String buildString() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (typeSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < typeSensors.size(); i++) {
            TypeSensor aux = typeSensors.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Unit: ").append(aux.getUnits()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Checks the type sensor list size and returns the size as int.\
     *
     * @return TypeSensor size as int
     **/
    public int size() {
        return this.typeSensors.size();
    }

    /**
     * This method receives an index as parameter and gets a type sensor from Type Sensor list.
     * @param index the type sensor index
     * @return returns Type Sensor that corresponds to index.
     */
    public TypeSensor get(int index) {
        return this.typeSensors.get(index);
    }

    /**
     * Getter (array of Type Sensors)

     * @return array of Type Sensors
     */
    TypeSensor[] getElementsAsArray() {
        int sizeOfResultArray = typeSensors.size();
        TypeSensor[] result = new TypeSensor[sizeOfResultArray];
        for (int i = 0; i < typeSensors.size(); i++) {
            result[i] = typeSensors.get(i);
        }
        return result;
    }

    /**
     * This methods checks if type sensor list is empty.
     *
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.typeSensors.isEmpty();
    }

    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof TypeSensorList)) {
            return false;
        }
        TypeSensorList list = (TypeSensorList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
