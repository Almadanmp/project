package pt.ipp.isep.dei.project.model.room;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a House Sensor.
 * It is defined by a name, type of sensor and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
@Entity
public class RoomSensor {

    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_sensor_id")
    private SensorType sensorType;

    @Temporal(TemporalType.DATE)
    private Date dateStartedFunctioning;

    private boolean active;

    private String roomId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "RoomReading")
    private List<Reading> readings;

//    @ManyToOne
//    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
//    @JoinColumn(name = "room")
//    protected Room parent;

    /**
     * Empty constructor to import Sensors from a XML file.
     */
    public RoomSensor() {
    }

    /**
     * Sensor() constructor with 3 parameters (Used When A Sensor is in a Room already. So the Location is the same as
     * the room/house).
     *
     * @param name                   is the name we want to set to the Sensor.
     * @param sensorType             is the Type of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public RoomSensor(String id, String name, SensorType sensorType, Date dateStartedFunctioning, String roomId) {
        setId(id);
        setName(name);
        setSensorType(sensorType);
        setDateStartedFunctioning(dateStartedFunctioning);
        this.active = true;
        this.roomId = roomId;
        this.readings = new ArrayList<>();
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

    public String getRoomId() {
        return this.roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<Reading> getReadings() {
        return new ArrayList<>(readings);
    }

    public void setReadings(List<Reading> houseReadings) {
        this.readings = new ArrayList<>(houseReadings);
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
        this.dateStartedFunctioning = new Date(dateStartedFunctioning.getTime());
    }

    public Date getDateStartedFunctioning() {
        return new Date(this.dateStartedFunctioning.getTime());
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

    public boolean isActive() {
        return this.active;
    }

    /**
     * Setter: sets the sensor active
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
        return this.name + ", " + this.sensorType.getName() + "\n";
    }

    /**
     * Method to print info if a sensor is active or not.
     */
    String printActive() {
        if (!this.active) {
            return "Deactivated";
        }
        return "Active";
    }


    /**
     * This method receives a String that corresponds to the reading's sensor ID and a Date that
     * corresponds to the reading's date, and checks that a reading with those characteristics
     * exists in the repository.
     *
     * @param date reading date
     * @return true in case the reading exists in the repository, false otherwise.
     **/
    boolean readingWithGivenDateExists(Date date) {
        for (Reading r : this.readings) {
            if (date.equals(r.getDate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method receives a Date and checks if the Room Sensor was active
     * at the time of the given date.
     *
     * @param date given date
     * @return true in case the Room Sensor was active at the time of the given date, false otherwise.
     **/
    boolean activeDuringDate(Date date) {
        return this.dateStartedFunctioning.equals(date) || this.dateStartedFunctioning.before(date);
    }

    /**
     * This method will receive a Reading and will try to add that reading
     * to the Room Sensor list of readings.
     * It will check if the reading happened during the Room sensor's
     * date of functioning and if the reading already exists.
     *
     * @return true in case the reading is added, false otherwise.
     **/
    boolean addReading(Reading reading) {
        Date readingDate = reading.getDate();
        if ((readingDate.equals(dateStartedFunctioning) || readingDate.after(dateStartedFunctioning)) && (!readingWithGivenDateExists(readingDate))) {
            return this.readings.add(reading);
        }
        return false;
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
        if (!(testObject instanceof RoomSensor)) {
            return false;
        }
        RoomSensor sensor = (RoomSensor) testObject;
        return this.getId().equals(sensor.getId());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
