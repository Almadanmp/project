package pt.ipp.isep.dei.project.model;

/**
 * Defines the Type of the Sensor.
 */

public class TypeSensor {
    private String mName;
    private String mUnits;


    /**
     * Constructor to always create an object that names the Type of the Sensor.
     */

    public TypeSensor() {
    }

    /**
     * Constructor to always create an object that names the Type of the Sensor and the Units of the Sensor.
     */

    public TypeSensor(String name, String units) {
        setName(name);
        setUnits(units);
    }

    /**
     * Setter Name
     *
     * @param name of type of sensor
     */
    public void setName(String name) {
        if (isNameValid(name)) {
            this.mName = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Setter Units of the Sensor.
     *
     * @param units Unit measure of the type
     */
    void setUnits(String units) {
        if (isNameValid(units)) {
            this.mUnits = units;
        } else {
            throw new IllegalArgumentException("Please Insert Valid String for Units of The Sensor");
        }
    }

    /**
     * Getter Units of the Sensor.
     *
     * @return the unit measure of the type
     */
    public String getUnits() {
        return this.mUnits;
    }

    /**
     * Getter Name
     *
     * @return the name of the type
     */
    public String getName() {
        return this.mName;
    }

    /**
     * Method to restrain input name/unit so they can't be null or empty
     *
     * @param name name or units inserted by user
     * @return will return true if the String is valid or it will throw an exception if Invalid
     */

    private boolean isNameValid(String name) {
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
        return this.getName().equals(typeSensor.getName()) && this.getUnits().equals(typeSensor.getUnits());
    }

    /**
     * Specific Method
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
