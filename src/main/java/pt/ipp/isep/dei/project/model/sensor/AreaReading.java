package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.*;
import java.util.Date;

/**
 * This class will contain a value read by a Sensor, associated with a date of said reading.
 */
@Entity
public class AreaReading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double value;
    private Date date;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "area_reading_list_id")
    private AreaReadingList areaReadingList;

    /**
     * Builder with 'value' and 'date'
     *
     * @param value value received
     * @param date  date received
     */
    public AreaReading(double value, Date date, String unit) {
        setValue(value);
        setDate(date);
        setUnit(unit);
    }

    protected AreaReading() {
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
        if (!(o instanceof AreaReading)) {
            return false;
        } else {
            AreaReading areaReading = (AreaReading) o;
            return (this.date.equals(areaReading.getDate()));
        }
    }


    @Override
    public int hashCode() {
        return 1;
    }
}
