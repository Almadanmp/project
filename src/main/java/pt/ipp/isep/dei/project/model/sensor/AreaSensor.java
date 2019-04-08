package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Local;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents a Sensor.
 * It is defined by a name, type of sensor, localization and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
@Entity
public class AreaSensor {

    @Id
    private String id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_sensor_id")
    private SensorType sensorType;

    @Embedded
    @JoinColumn(name = "local_id")
    private Local local;

    private Date dateStartedFunctioning;

    @Transient
    private AreaReadingList areaReadingList;

    private long geographicAreaId;

    private boolean active;


    /**
     * Empty constructor to import Sensors from a XML file.
     */
    public AreaSensor() {
    }

    /**
     * Sensor() constructor with 5 parameters.
     *
     * @param id                     is the id we want to set to the Sensor.
     * @param name                   is the name we want to set to the Sensor.
     * @param sensorType             is the Type of the Sensor.
     * @param local                  is the Local of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public AreaSensor(String id, String name, SensorType sensorType, Local local, Date dateStartedFunctioning) {
        setId(id);
        setName(name);
        setSensorType(sensorType);
        setLocal(local);
        setDateStartedFunctioning(dateStartedFunctioning);
        areaReadingList = new AreaReadingList();
        this.active = true;
    }

    /**
     * Sensor() constructor with 3 parameters (Used When A Sensor is in a Room already. So the Location is the same as
     * the room/house).
     *
     * @param name                   is the name we want to set to the Sensor.
     * @param sensorType             is the Type of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public AreaSensor(String name, SensorType sensorType, Date dateStartedFunctioning) {
        setName(name);
        setSensorType(sensorType);
        setDateStartedFunctioning(dateStartedFunctioning);
        areaReadingList = new AreaReadingList();
        this.active = true;
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
     * Setter: local
     *
     * @param local is the local we want to set to the sensor.
     */
    public void setLocal(Local local) {
        this.local = local;
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
     * Getter: Id
     *
     * @return a string that represents the name of the sensor.
     */
    public String getId() {
        return (this.id);
    }

    public void setGeographicAreaId(long geographicAreaId) {
        this.geographicAreaId = geographicAreaId;
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
     * Getter: local
     *
     * @return the Local of the Sensor.
     */
    public Local getLocal() {
        return (this.local);
    }

    /**
     * Getter: reading list
     *
     * @return the areaReadingList of the sensor.
     */
    public AreaReadingList getAreaReadingList() {
        return areaReadingList;
    }

    /**
     * Setter: reading list
     *
     * @param areaReadingList is the areaReadingList we want to set to the sensor.
     */
    public void setAreaReadingList(AreaReadingList areaReadingList) {
        if (areaReadingList != null) {
            this.areaReadingList = areaReadingList;
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
     * @param areaReading the reading to be added to the list
     * @return true in case the reading is new and it is added
     * or false in case the reading already exists
     **/
    public boolean addReading(AreaReading areaReading) {
        if (this.active) {
            return areaReadingList.addReading(areaReading);
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
    public boolean addReading(Date date, Double value, String unit) {
        if (this.active) {
            Date startingDate = this.getDateStartedFunctioning();
            if (date.after(startingDate) || date.equals(startingDate)) {
                AreaReading areaReading = new AreaReading(value, date, unit);
                return this.addReading(areaReading);
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
     * Method that checks if the Sensor is contained in a given Geographical Area.
     *
     * @param area is the area we want to check if the sensor is in.
     * @return true if the sensor is in the given area, false otherwise.
     */
    public boolean isContainedInArea(GeographicArea area) {
        double latS = this.getLocal().getLatitude();
        double longS = this.getLocal().getLongitude();
        Local areaLocal = area.getLocal();
        double latTopVert = areaLocal.getLatitude() + (area.getWidth() / 2);
        double longTopVert = areaLocal.getLongitude() - (area.getLength() / 2);
        double latBotVert = areaLocal.getLatitude() - (area.getWidth() / 2);
        double longBotVert = areaLocal.getLongitude() + (area.getLength() / 2);
        return (latS <= latTopVert && latS >= latBotVert && longS <= longBotVert && longS >= longTopVert);
    }

    /**
     * Method that returns the distance between the sensor and the house.
     *
     * @param house is the house we want to calculate the distance to.
     * @return a double that represents the distance between the house and the sensor.
     */
    public double getDistanceToHouse(House house) {
        Local l = house.getLocation();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to print details that are required for a Sensor to be different from another Sensor (equals -
     * name, type area and local).
     *
     * @return returns a string with Sensor Parameters
     */
    public String buildString() {

        if (this.getLocal() == null) {
            return this.name + ", " + this.sensorType.getName() + ". ";
        }
        String result;

        result = this.name + ", " + this.sensorType.getName() + ", " +
                this.local.getLatitude() + "º lat, " + this.local.getLongitude() + "º long \n";
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
     * This method goes through the sensor reading list and return
     * the most recent reading date.
     *
     * @return most recent reading date in sensor
     **/

    Date getMostRecentReadingDate() {
        return this.areaReadingList.getMostRecentReadingDate();
    }

    /**
     * This method returns the sensor type name.
     *
     * @return he sensor type name.
     **/

    String getSensorTypeName() {
        return this.sensorType.getName();
    }

    /**
     * This method checks if the sensor's reading list is valid.
     *
     * @return true if valid, false if invalid.
     **/
    public boolean isReadingListEmpty() {
        return this.areaReadingList.isEmpty();
    }

    /**
     * This method receives an interval, goes through the sensor's reading list and returns the
     * average reading values between the interval given.
     *
     * @param initialDate start of interval
     * @param endDate     end of interval
     * @return average reading value between interval
     * @author Daniela - US623
     ***/
    public double getAverageReadingsBetweenDates(Date initialDate, Date endDate) {
        return this.areaReadingList.getAverageReadingsBetweenDates(initialDate, endDate);
    }

    /**
     * This method receives an interval, goes through the sensor's reading list and returns the date with the
     * highest amplitude reading value between the interval given.
     *
     * @param initialDate start of interval
     * @param endDate     end of interval
     * @return date with the highest amplitude reading value between interval
     * @author Daniela - US633
     ***/
    public Date getDateHighestAmplitudeBetweenDates(Date initialDate, Date endDate) {
        return this.areaReadingList.getDateHighestAmplitudeBetweenDates(initialDate, endDate);
    }

    /**
     * This method receives a date, goes through the sensor's reading list and returns the highest amplitude reading
     * value on that date.
     *
     * @param date start of interval
     * @return highest amplitude reading value on date
     * @author Daniela - US633
     ***/
    public double getHighestAmplitudeInDate(Date date) {
        return this.areaReadingList.getAmplitudeValueFromDate(date);
    }


    /**
     * US630
     * This method joins a lot of other methods used to fulfil the US 630 (As a Regular User,
     * I want to get the last coldest day (lower maximum temperature) in the house area in a given period) and
     * it returns a Reading within an interval from a AreaReadingList that represents the last coldest day in the
     * given period (lower maximum temperature).
     *
     * @param initialDate is the Initial Date of the period.
     * @param endDate     is the Final Date of the period.
     * @return a Reading that represents the Last Coldest Day in a Given Period (Lower Maximum Temperature).
     */
    public Date getLastColdestDayInGivenInterval(Date initialDate, Date endDate) {
        return this.areaReadingList.getLastColdestDayInGivenInterval(initialDate, endDate);
    }

    /**
     * US631
     * This method returns a DATE for the first hottest day (higher maximum temperature) in the house area in a given period
     * (higher maximum temperature).
     *
     * @param startDate is the Initial Date of the period.
     * @param endDate   is the Final Date of the period.
     * @return a Reading that represents the Last Coldest Day in a Given Period (Lower Maximum Temperature).
     */

    public Date getFirstHottestDayInGivenPeriod(Date startDate, Date endDate) {
        return this.areaReadingList.getFirstHottestDayInGivenPeriod(startDate, endDate);
    }

    /**
     * This method receives a date of a given day, goes through the sensor's reading list and
     * returns the total reading values of that day.
     *
     * @param day date of day
     * @return total reading values of that day
     ***/
    public double getTotalValueReadingsOnGivenDay(Date day) {
        return this.areaReadingList.getValueReadingsInDay(day);
    }

    /**
     * This method goes through the sensor's reading list and
     * returns the most recent reading value.
     *
     * @return sensor's most recent reading value.
     ***/
    public double getMostRecentValueReading() {
        return this.areaReadingList.getMostRecentValue();
    }


    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof AreaSensor)) {
            return false;
        }
        AreaSensor areaSensor = (AreaSensor) testObject;
        return this.getName().equals(areaSensor.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}