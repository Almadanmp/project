package pt.ipp.isep.dei.project.dto;


import java.util.Date;

public class ReadingDTO {

    private Date date;
    private double value;
    private String unit;
    private String sensorId;

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
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

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
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
            return (this.date.equals(reading.getDate()) && this.sensorId.equals(reading.getSensorId()));
        }
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
