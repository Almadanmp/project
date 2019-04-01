package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class ReadingDTO {
    private double value;
    private Date date;

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
}
