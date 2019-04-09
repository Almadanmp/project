package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a House Sensor.
 * It is defined by a name, type of sensor and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
@Entity
public class HouseSensor {

    @Id
    private String id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_sensor_id")
    private SensorType sensorType;

    private Date dateStartedFunctioning;

    @Transient
    private ReadingList readingList;

    private boolean active;

    private String roomId;

    /**
     * Empty constructor to import Sensors from a XML file.
     */
    public HouseSensor() {
    }

    /**
     * Sensor() constructor with 3 parameters (Used When A Sensor is in a Room already. So the Location is the same as
     * the room/house).
     *
     * @param name                   is the name we want to set to the Sensor.
     * @param sensorType             is the Type of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public HouseSensor(String name, SensorType sensorType, Date dateStartedFunctioning) {
        setName(name);
        setSensorType(sensorType);
        setDateStartedFunctioning(dateStartedFunctioning);
        this.readingList = new ReadingList();
        this.active = true;
    }

    /**
     * Setter: name
     *
     * @param name is the name we want to set to the sensor.
     */
    public void setName(String name) {
        if (isSensorNameValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Setter: Id
     *
     * @param id is the id we want to set to the sensor.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter: Id
     *
     * @return a string that represents the name of the sensor.
     */
    public String getId() {
        return (this.id);
    }

    /**
     * Setter: type sensor
     *
     * @param sensor is the Type we want to set to the sensor.
     */
    public void setSensorType(SensorType sensor) {
        this.sensorType = sensor;
    }

    /**
     * Setter: date started functioning
     *
     * @param dateStartedFunctioning is the date that the sensor started functioning.
     */
    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    public Date getDateStartedFunctioning() {
        return this.dateStartedFunctioning;
    }

    /**
     * Getter: name
     *
     * @return a string that represents the name of the sensor.
     */
    public String getName() {
        return (this.name);
    }

    /**
     * Getter: type sensor
     *
     * @return the Type of the Sensor.
     */
    public SensorType getSensorType() {
        return (this.sensorType);
    }

    /**
     * Getter: reading list
     *
     * @return the readingList of the sensor.
     */
    public ReadingList getReadingList() {
        return readingList;
    }

    /**
     * Setter: reading list
     *
     * @param readingList is the readingList we want to set to the sensor.
     */
    public void setReadingList(ReadingList readingList) {
        if (readingList != null) {
            this.readingList = readingList;
        }
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Settter: sets the sensor active
     */
    public void setActive(boolean status) {
        this.active = status;
    }


    /**
     * Method to activate an deactivated sensor, and vice versa
     *
     * @return active or not
     */
    public boolean deactivateSensor() {
        if (isActive()) {
            this.active = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if reading already exists in reading list and in case the
     * reading is new, adds it to the reading list. Only adds readings if the sensor is active.
     *
     * @param reading the reading to be added to the list
     * @return true in case the reading is new and it is added
     * or false in case the reading already exists
     **/
    public boolean addReading(Reading reading) {
        if (this.active) {
            return readingList.addReading(reading);
        }
        return false;
    }

    /**
     * Adds a new Reading to a sensor with the date and value received as parameter, but only if that date is posterior
     * to the date when the sensor was activated.
     *
     * @param value is the value readSensors on the reading.
     * @param date  is the readSensors date of the reading.
     * @return returns true if the reading was successfully added.
     * @author André
     */
    public boolean addReading(Date date, Double value, String unit, String sensorId) {
        if (this.active) {
            Date startingDate = this.getDateStartedFunctioning();
            if (date.after(startingDate) || date.equals(startingDate)) {
                Reading reading = new Reading(value, date, unit, sensorId);
                return this.addReading(reading);
            }
        }
        return false;
    }

    /**
     * Method to restrain input name so they cant be null or empty.
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    private boolean isSensorNameValid(String name) {
        return (name != null && !name.isEmpty());
    }

    /**
     * Method to print details that are required for a Sensor to be different from another Sensor (equals -
     * name, type area and local).
     *
     * @return returns a string with Sensor Parameters
     */
    public String buildString() {
        String result = this.name + ", " + this.sensorType.getName() + "\n";
        return result;
    }

    /**
     * Method to print info if a sensor is active or not.
     */
    public String printActive() {
        if (!this.active) {
            return "Deactivated";
        }
        return "Active";
    }

    /**
     * This method returns the sensor type name.
     *
     * @return he sensor type name.
     **/

    String getSensorTypeName() {
        return this.sensorType.getName();
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseSensor)) {
            return false;
        }
        HouseSensor sensor = (HouseSensor) testObject;
        return this.getName().equals(sensor.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
