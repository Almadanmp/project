package Sprint_0;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class will contain a value read by a Sensor, associated with a date of said reading.
 */
public class Reading {

    private double mValue;
    private Date mDate;

    /**
     * Builder with 'value' and 'date'
     *
     * @param value
     * @param date
     */
    public Reading(double value, Date date) {
        setmValue(value);
        setData(date);
    }

    /**
     * Builder with 'value'.
     *
     * @param value
     */
    public Reading(double value) {
        setmValue(value);
    }

    /**
     * getters e setters
     *
     * @param value
     */
    public void setmValue(double value) {
        this.mValue = value;
    }

    public double getmValue() {
        return this.mValue;
    }

    /**
     * getters e setters
     *
     * @param date
     */
    public void setData(Date date) {
        this.mDate = date;
    }

    public Date getmDate() {
        return this.mDate;
    }

    /**
     * Method 'equals' is required so that each 'Reading' can be added to a 'ReadingList'.
     *
     * @param o
     * @return
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
            if (this.mValue == reading.getmValue() && this.mDate.equals(reading.getmDate())) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
