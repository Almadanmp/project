package pt.ipp.isep.dei.project.dto;


import pt.ipp.isep.dei.project.model.Local;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaDTO {

    private Long id;
    private String name;
    private String typeArea;
    private double length;
    private double width;
    private double latitude;
    private double longitude;
    private double altitude;
    private List<SensorDTO> sensorDTOList = new ArrayList<>();
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to determine the ID of the object.
     *
     * @param name is a String that corresponds to the ID we want to store.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to retrieve the ID of the object.
     *
     * @return is a string that corresponds to the ID we want.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Method to retrieve the String that corresponds to the the name of the Type of the area.
     *
     * @return is a string that corresponds to the name of the type of the geographic area.
     */

    public String getTypeArea() {
        return typeArea;
    }

    /**
     * Method to store a String that corresponds to the name of the Type of the area.
     *
     * @param typeArea is a string that corresponds to the name of the type of the geographic area.
     */

    public void setTypeArea(String typeArea) {
        this.typeArea = typeArea;
    }

    /**
     * Method that retrieves the length of a geographic area DTO.
     *
     * @return is the length of the object.
     */

    public double getLength() {
        return length;
    }

    /**
     * Method that stores the length of a geographic area DTO.
     *
     * @param length is the length of the object.
     */

    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Method that retrieves the width of a geographic area DTO.
     *
     * @return is the width of the object.
     */

    public double getWidth() {
        return width;
    }

    /**
     * Method that stores the width of a geographic area DTO.
     *
     * @param width is the width of the object.
     */

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Method that retrieves the latitude of a geographic area DTO.
     *
     * @return is the latitude of the object.
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     * Method that stores the latitude of a geographic area DTO.
     *
     * @param latitude is the latitude of the object.
     */

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Method that retrieves the longitude of a geographic area DTO.
     *
     * @return is the longitude of the object.
     */

    public double getLongitude() {
        return longitude;
    }

    /**
     * Method that stores the longitude of a geographic area DTO.
     *
     * @param longitude is the longitude of the object.
     */

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Method that retrieves the altitude of a geographic area DTO.
     *
     * @return is the altitude of the object.
     */

    public double getAltitude() {
        return altitude;
    }

    /**
     * Method that stores the altitude of a geographic area DTO.
     *
     * @param altitude is the altitude of the object.
     */

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Method that retrieves the object's list of sensor DTOs.
     *
     * @return is a list of sensorDTOs.
     */

    public List<SensorDTO> getSensorDTOList() {
        return sensorDTOList;
    }

    /**
     * Method that stores a specific list as the object's list of Sensor DTOs.
     *
     * @param listToStore is the list we want to store.
     */

    public void setSensorDTOList(List<SensorDTO> listToStore) {
        this.sensorDTOList = listToStore;
    }

    /**
     * Method that retrieves the object's description.
     *
     * @return is the object's description.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Method that stores a particular string as an object's description.
     *
     * @param description is the description we want to store.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof GeographicAreaDTO)) {
            return false;
        }

        GeographicAreaDTO localVariable = (GeographicAreaDTO) testDTO;
        Local testDTOLocal = new Local(localVariable.getLatitude(), localVariable.getLongitude(), localVariable.
                getAltitude());
        Local geographicAreaDTOLocal = new Local(this.latitude, this.longitude, this.altitude);
        return (localVariable.getTypeArea().equals(this.typeArea) && localVariable.getName().equals(this.name)
                && testDTOLocal.equals(geographicAreaDTOLocal) && localVariable.getSensorDTOList().equals(this.sensorDTOList));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}