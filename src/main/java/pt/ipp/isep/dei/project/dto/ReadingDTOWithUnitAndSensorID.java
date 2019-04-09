package pt.ipp.isep.dei.project.dto;


import java.util.Date;

public class ReadingDTOWithUnitAndSensorID {

    private Date date;
    private double value;
    private String unit; // This needs to be UnitDto
    private String sensorID;

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

    public String getSensorID() {
        return sensorID;
    }

    public void setSensorID(String sensorID) {
        this.sensorID = sensorID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReadingDTOWithUnitAndSensorID)) {
            return false;
        } else {
            ReadingDTOWithUnitAndSensorID reading = (ReadingDTOWithUnitAndSensorID) o;
            return (this.sensorID.equals(reading.getSensorID()) && this.date.equals(reading.getDate()));
        }
    }

    @Override
    public int hashCode() {
        return date.hashCode() * unit.hashCode() * (int) value;
    }
}
