package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class ReadingDTO {
    private double value;
    private Date date;
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
            ReadingDTO reading = (ReadingDTO) o;
            return (this.date.equals(reading.getDate()));
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
