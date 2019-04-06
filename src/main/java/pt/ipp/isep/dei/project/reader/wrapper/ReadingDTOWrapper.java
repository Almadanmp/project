package pt.ipp.isep.dei.project.reader.wrapper;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pt.ipp.isep.dei.project.reader.deserializer.CustomDateDeserializer;

import java.util.Date;

public class ReadingDTOWrapper {
    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String sensorId;
    @JsonProperty("timestamp/date")
    @JsonAlias({"timestamp/date", "timestamp_date"})
    @JsonDeserialize(using = CustomDateDeserializer.class)
    public Date date;
    @JsonProperty("value")
    public double value;
    @JsonProperty("unit")
    public String unit;

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
