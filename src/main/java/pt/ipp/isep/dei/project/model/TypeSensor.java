package pt.ipp.isep.dei.project.model;

/**
 * Defines the Type of the Sensor.
 * A Type of Sensor is characterized by a name and a measure unit
 * Different Sensor Types cannot share the same Type Name
 */

public class TypeSensor {
    private String name;
    private String units;

// CONSTRUCTOR

    /**
     * @param name  The name of the type
     * @param units The type of units used in that type of sensor
     *              Constructor to always create an object that names the Type of the Sensor and the Units of the Sensor.
     */

    public TypeSensor(String name, String units) {
        this.name = name;
        this.units = units;
    }

    //GETTER METHODS
    /**
     * Getter Units of the Sensor.
     *
     * @return the unit measure of the type
     */
    public String getUnits() {
        return this.units;
    }

    /**
     * Getter Name
     *
     * @return the name of the type
     */
    public String getName() {
        return this.name;
    }

    //SPECIFIC METHODS

    /**
     * This method will return a string with the type sensor information (name and unit of measure)
     *
     * @return string of name and unit of measure
     **/
    public String buildString() {
        String result;
        result = "The type of the sensor is " + this.name + ", and the unit of measurement is " + this.units + ".";
        return result;
    }

    /**
     * Equals Method
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
