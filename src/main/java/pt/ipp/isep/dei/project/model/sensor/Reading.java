package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.services.units.Unit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * This class will contain a value readSensors by a Sensor, associated with a date of said reading.
 */
@Entity
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double value;
    private Date date;
    private Unit unit;
    private String sensorId;

    /**
     * Builder with 'value' and 'date'
     *
     * @param value value received
     * @param date  date received
     */
    public Reading(double value, Date date, Unit unit, String sensorId) {
        setValue(value);
        setDate(date);
        setUnit(unit);
        this.sensorId = sensorId;
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
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    /**
     * Setter for unit. Receives a string and sets it as a parameter.
     *
     * @param unit string of unit
     **/
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Getter for unit.
     *
     * @return unit as string
     **/
    public Unit getUnit() {
        return this.unit;
    }


    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
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
            return (this.date.equals(reading.getDate()));
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
