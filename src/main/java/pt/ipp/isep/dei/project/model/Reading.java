package pt.ipp.isep.dei.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * This class will contain a value read by a Sensor, associated with a date of said reading.
 */
@Entity
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long iD;
    private double value;
    private Date date;

    /**
     * Builder with 'value' and 'date'
     *
     * @param value value received
     * @param date date received
     */
    public Reading(double value, Date date) {
        setValue(value);
        setDate(date);
    }

    protected Reading() {
    }

    /**
     * getters e setters
     *
     * @param value of reading made
     */
    void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    /**
     * getters e setters
     * @param date of the reading
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }


    /**
     * Method 'equals' is required so that each 'Reading' can be added to a 'ReadingList'.
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
