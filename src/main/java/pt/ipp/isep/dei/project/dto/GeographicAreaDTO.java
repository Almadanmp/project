package pt.ipp.isep.dei.project.dto;


import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.model.TypeArea;

import java.util.UUID;

public class GeographicAreaDTO {

    private String id;
    private TypeArea typeArea;
    private double length;
    private double width;
    private GeographicArea motherArea;
    private Local location;
    private SensorList areaSensors;
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

    public TypeArea getTypeArea() {
        return typeArea;
    }

    public void setTypeArea(TypeArea typeArea) {
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

    public Local getLocation() {
        return location;
    }

    public void setLocation(Local location) {
        this.location = location;
    }

    public SensorList getAreaSensors() {
        return areaSensors;
    }

    public void setAreaSensors(SensorList areaSensors) {
        this.areaSensors = areaSensors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}