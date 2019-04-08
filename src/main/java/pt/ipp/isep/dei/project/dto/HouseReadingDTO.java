package pt.ipp.isep.dei.project.dto;
import pt.ipp.isep.dei.project.model.sensor.HouseReading;

import java.util.Date;

public class HouseReadingDTO {

    private Date date;
    private double value;
    private String unit;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadingDTO)) {
            return false;
        } else {
            HouseReadingDTO reading = (HouseReadingDTO) o;
            return (this.date.equals(reading.getDate()));
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
