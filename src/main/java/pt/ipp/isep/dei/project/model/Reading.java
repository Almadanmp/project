package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.dddplaceholders.ValueObject;

import javax.persistence.*;
import java.util.Date;

/**
 * This class will contain a value readSensors by a Sensor, associated with a date of said reading.
 */
@Embeddable
public class Reading implements ValueObject {
    private String sensorID;
    private double value;
    private Date date;
    private String unit;


    /**
     * Builder with 'value' and 'date'
     *
     * @param value value received
     * @param date  date received
     */
    public Reading(double value, Date date, String unit, String sensorID) {
        setValue(value);
        setDate(date);
        setUnit(unit);
        this.sensorID = sensorID;
    }

    protected Reading() {
    }

    /**
     * getters e setters
     *
     * @param value of reading made
     */
    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    /**
     * getters e setters
     *
     * @param date of the reading
     */
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    /**
     * Setter for unit. Receives a string and sets it as a parameter.
     *
     * @param unit string of unit
     **/
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Getter for unit.
     *
     * @return unit as string
     **/
    public String getUnit() {
        return this.unit;
    }

    /**
     * Method 'equals' is required so that each 'Reading' can be added to a 'AreaReadingList'.
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reading)) {
            return false;
        } else {
            Reading reading = (Reading) o;
            return (this.date.equals(reading.getDate()) && this.sensorID.equals(reading.getSensorID()));
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
