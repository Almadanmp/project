package pt.ipp.isep.dei.project.dto;

import java.util.UUID;

public class SensorDTO {

    private String id;
    private String name;
    private String typeSensor;
    private String units;
    private double latitude;
    private double longitude;
    private double altitude;
    private String dateStartedFunctioning;
    private UUID uniqueID;

    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnits() {
        return units;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    public String getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    public void setDateStartedFunctioning(String dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }
}
