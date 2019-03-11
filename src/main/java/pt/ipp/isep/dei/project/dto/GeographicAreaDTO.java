package pt.ipp.isep.dei.project.dto;


import java.util.List;
import java.util.UUID;

public class GeographicAreaDTO {

    private String id;
    private String typeArea;
    private double length;
    private double width;
    private double latitudeGeoAreaDTO;
    private double longitudeGeoAreaDTO;
    private double altitudeGeoAreaDTO;
    private List<SensorDTO> areaSensors;
    private String description;
    private UUID uniqueId;

    void setUniqueId(UUID uniqueId){this.uniqueId = uniqueId;}

    UUID getUniqueId() {
        return uniqueId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    String getTypeArea() {
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

    public double getLatitudeGeoAreaDTO() {
        return latitudeGeoAreaDTO;
    }

    public void setLatitudeGeoAreaDTO(double latitudeGeoAreaDTO) {
        this.latitudeGeoAreaDTO = latitudeGeoAreaDTO;
    }

    public double getLongitudeGeoAreaDTO() {
        return longitudeGeoAreaDTO;
    }

    public void setLongitudeGeoAreaDTO(double longitudeGeoAreaDTO) {
        this.longitudeGeoAreaDTO = longitudeGeoAreaDTO;
    }

    public double getAltitudeGeoAreaDTO() {
        return altitudeGeoAreaDTO;
    }

    public void setAltitudeGeoAreaDTO(double altitudeGeoAreaDTO) {
        this.altitudeGeoAreaDTO = altitudeGeoAreaDTO;
    }

    public void addSensorDTO(SensorDTO sensorToAdd){
        this.areaSensors.add(sensorToAdd);
    }

    List<SensorDTO> getAreaSensors() {
        return areaSensors;
    }

    void setAreaSensors(List<SensorDTO> areaSensors) {
        this.areaSensors = areaSensors;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}