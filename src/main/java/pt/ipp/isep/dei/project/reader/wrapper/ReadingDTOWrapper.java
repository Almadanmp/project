package pt.ipp.isep.dei.project.reader.wrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pt.ipp.isep.dei.project.reader.deserializer.CustomDateDeserializer;

import java.util.Date;

public class ReadingDTOWrapper {
    @JsonProperty("timestamp/date")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date date;
    @JsonProperty("value")
    public double value;
    @JsonProperty("unit")
    public String unit;
    @JsonProperty("id")
    public String sensorId;

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

    public String getSensorId() {
        return this.sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public boolean equals(Object objectTest) {
        if (this == objectTest) {
            return true;
        }
        if (!(objectTest instanceof ReadingDTOWrapper)) {
            return false;
        } else {
            ReadingDTOWrapper reading = (ReadingDTOWrapper) objectTest;
            return (this.date.equals(reading.getDate()));
        }
    }

    public int hashCode() {
        return 1;
    }
}
