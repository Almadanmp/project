package pt.ipp.isep.dei.project.model;

import java.util.Date;

/**
 * This class will contain a value read by a Sensor, associated with a date of said reading.
 */
public class Reading {

    private double mValue;
    private Date mDate;

    /**
     * Builder with 'value' and 'date'
     *
     * @param value value received
     * @param date date received
     */
    public Reading(double value, Date date) {
        setmValue(value);
        setData(date);
    }

    /**
     * Builder with 'value'.
     *
     * @param value of reading made
     */
    public Reading(double value) {
        setmValue(value);
    }

    /**
     * getters e setters
     *
     * @param value of reading made
     */
    void setmValue(double value) {
        this.mValue = value;
    }

    double getmValue() {
        return this.mValue;
    }

    /**
     * getters e setters
     * @param date of the reading
     */
    public void setData(Date date) {
        this.mDate = date;
    }

    Date getmDate() {
        return this.mDate;
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
            return (java.lang.Double.compare(this.mValue, reading.getmValue()) == 0 && this.mDate.equals(reading.getmDate()));
        }
    }


    @Override
    public int hashCode() {
        return 1;
    }
}
