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

    UUID getUniqueID() {
        return uniqueID;
    }

    void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String getUnits() {
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

    String getTypeSensor() {
        return typeSensor;
    }

    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    String getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    public void setDateStartedFunctioning(String dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof SensorDTO)) {
            return false;
        }
        SensorDTO localVariable = (SensorDTO) testDTO;
        return (localVariable.getId().equals(this.id) && localVariable.getName().equals(this.name));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
