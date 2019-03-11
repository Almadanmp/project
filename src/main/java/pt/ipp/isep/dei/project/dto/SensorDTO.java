package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.ReadingList;

import java.util.Date;
import java.util.UUID;

public class SensorDTO {

    private String name;
    private String typeSensorName;
    private String typeSensorUnits;
    private double latitude;
    private double longitude;
    private double altitude;
    private Date dateStartedFunctioning;
    private ReadingList readingList;
    private UUID uniqueID;

    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeSensorName() {
        return typeSensorName;
    }

    public void setTypeSensorName(String typeSensorName) {
        this.typeSensorName = typeSensorName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Date getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    public String getTypeSensorUnits() {
        return typeSensorUnits;
    }

    public void setTypeSensorUnits(String typeSensorUnits) {
        this.typeSensorUnits = typeSensorUnits;
    }

    public ReadingList getReadingList() {
        return readingList;
    }

    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }
}
