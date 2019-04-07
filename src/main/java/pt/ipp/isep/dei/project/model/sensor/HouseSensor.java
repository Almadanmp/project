package pt.ipp.isep.dei.project.model.sensor;

import java.util.Date;

/**
 * Represents a House Sensor.
 * It is defined by a name, type of sensor and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
//@Entity
public class HouseSensor {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "type_sensor_id")
    private SensorType sensorType;

    private Date dateStartedFunctioning;

    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private HouseReadingList houseReadingList;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "sensor_list_id")
    private HouseSensorList houseSensorList;

    private boolean active;

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
        houseReadingList = new HouseReadingList();
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
    public HouseReadingList getReadingList() {
        return houseReadingList;
    }

    /**
     * Setter: reading list
     *
     * @param houseReadingList is the readingList we want to set to the sensor.
     */
    public void setReadingList(HouseReadingList houseReadingList) {
        if (houseReadingList != null) {
            this.houseReadingList = houseReadingList;
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

    public void setSensorList(HouseSensorList houseSensorList) {
        this.houseSensorList = houseSensorList;
    }

    public HouseSensorList getSensorList() {
        return houseSensorList;
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
    public boolean addReading(HouseReading reading) {
        if (this.active) {
            return houseReadingList.addReading(reading);
        }
        return false;
    }

    /**
     * Adds a new Reading to a sensor with the date and value received as parameter, but only if that date is posterior
     * to the date when the sensor was activated.
     *
     * @param value is the value read on the reading.
     * @param date  is the read date of the reading.
     * @return returns true if the reading was successfully added.
     * @author André
     */
    public boolean addReading(Date date, Double value, String unit) {
        if (this.active) {
            Date startingDate = this.getDateStartedFunctioning();
            if (date.after(startingDate) || date.equals(startingDate)) {
                HouseReading reading = new HouseReading(value, date, unit);
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
     * This method goes through the sensor reading list and return
     * the most recent reading date.
     *
     * @return most recent reading date in sensor
     **/

    Date getMostRecentReadingDate() {
        return this.houseReadingList.getMostRecentReadingDate();
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
        return this.houseReadingList.isEmpty();
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
        return this.houseReadingList.getAverageReadingsBetweenDates(initialDate, endDate);
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
        return this.houseReadingList.getDateHighestAmplitudeBetweenDates(initialDate, endDate);
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
        return this.houseReadingList.getAmplitudeValueFromDate(date);
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
        return this.houseReadingList.getLastColdestDayInGivenInterval(initialDate, endDate);
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
        return this.houseReadingList.getFirstHottestDayInGivenPeriod(startDate, endDate);
    }

    /**
     * This method receives a date of a given day, goes through the sensor's reading list and
     * returns the total reading values of that day.
     *
     * @param day date of day
     * @return total reading values of that day
     ***/
    public double getTotalValueReadingsOnGivenDay(Date day) {
        return this.houseReadingList.getValueReadingsInDay(day);
    }

    /**
     * This method goes through the sensor's reading list and
     * returns the most recent reading value.
     *
     * @return sensor's most recent reading value.
     ***/
    public double getMostRecentValueReading() {
        return this.houseReadingList.getMostRecentValue();
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
