package pt.ipp.isep.dei.project.dto;


import pt.ipp.isep.dei.project.model.GeographicArea;

import java.util.List;
import java.util.UUID;

public class GeographicAreaDTO {

    private String id;
    private String typeArea;
    private double length;
    private double width;
    private GeographicArea motherArea;
    private double latitude;
    private double longitude;
    private double altitude;
    private List<SensorDTO> areaSensors;
    private String description;
    private UUID uniqueId;

    public void setUniqueId(UUID uniqueId){this.uniqueId = uniqueId;}

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getTypeArea() {
        return typeArea;
    }

    public void setTypeArea(String typeArea) {
        this.typeArea = typeArea;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public GeographicArea getMotherArea() {
        return motherArea;
    }

    public void setMotherArea(GeographicArea motherArea) {
        this.motherArea = motherArea;
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

    public List<SensorDTO> getAreaSensors() {
        return areaSensors;
    }

    public void setAreaSensors(List<SensorDTO> areaSensors) {
        this.areaSensors = areaSensors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}