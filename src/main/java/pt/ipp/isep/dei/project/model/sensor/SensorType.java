package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines the Type of the Sensor.
 * A Type of Sensor is characterized by a name and a measure unit
 * Different Sensor Types cannot share the same Type Name
 */
@Entity
public class SensorType {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String units;

    public SensorType() {
    }

    /**
     * @param name  The name of the type
     * @param units The type of units used in that type of sensor
     *              Constructor to always create an object that names the Type of the Sensor and the Units of the Sensor.
     */
    public SensorType(String name, String units) {
        this.name = name;
        this.units = units;
    }

    public long getId() {
        return id;
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
     * Method to print a SensorType.
     * It will print the attributes needed to check the information of the SensorType
     * (id, name and Units)
     *
     * @return a string of the SensorType.
     */
    public String toString() {
        return String.format(
                "SensorType[id=%d, name='%s', units='%s']",
                id, name, units);
    }

    /**
     * Equals Method
     *
     * @param testSensor Receives an object to verify if it matches current instance of SensorType.
     * @return boolean returns true if equal, false if they are not equal.
     */
    @Override
    public boolean equals(Object testSensor) {
        if (this == testSensor) {
            return true;
        }
        if (!(testSensor instanceof SensorType)) {
            return false;
        }
        SensorType sensorType = (SensorType) testSensor;
        return this.getName().equals(sensorType.getName()) && this.getUnits().equals(sensorType.getUnits());
    }

    /**
     * Specific Method
     */
    @Override
    public int hashCode() {
        return 1;
    }
}
