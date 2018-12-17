package PT.IPP.ISEP.DEI.Project.Model;

/**
 * Defines the Type of the Sensor.
 */

public class TypeSensor {
    private String mName;


    /**
     * Constructor to always create an object that names the Type of the Sensor.
     */

    public TypeSensor() {
    }

    /**
     * Constructor to always create an object that names the Type of the Sensor.
     */

    public TypeSensor(String name) {
        setName(name);
    }


    /**
     * Setter Name
     *
     * @param name of type of sensor
     */
    public void setName(String name) {
        if (isTypeSensorNameValid(name)) {
            this.mName = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Getter Name
     */
    public String getName() {
        return this.mName;
    }

    /**
     * Method to restrain input name so they cant be null or empty
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */

    private boolean isTypeSensorNameValid(String name) {
        return (name != null && !name.isEmpty());
    }

    /**
     * Specific Method
     *
     * @param testSensor Receives an object to verify if it matches current instance of TypeSensor.
     * @return boolean returns true if equal, false if they are not equal.
     */

    @Override
    public boolean equals(Object testSensor) {
        if (this == testSensor) {
            return true;
        }
        if (!(testSensor instanceof TypeSensor)) {
            return false;
        }
        TypeSensor typeSensor = (TypeSensor) testSensor;
        return this.getName().equals(typeSensor.getName());
    }

    /**
     * Specific Method
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
