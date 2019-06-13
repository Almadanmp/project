package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaDTO extends ResourceSupport {

    private Long id;
    private String name;
    private String typeArea;
    private double length;
    private double width;
    private LocalDTO local;
    private List<AreaSensorDTO> areaSensorDTOList = new ArrayList<>();
    private List<GeographicAreaDTO> daughterAreaDTOList = new ArrayList<>();
    private String description;

    public Long getGeographicAreaId() {
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
     * Method that retrieves the object's list of sensor DTOs.
     *
     * @return is a list of sensorDTOs.
     */

    public List<AreaSensorDTO> getSensors() {
        return new ArrayList<>(areaSensorDTOList);
    }

    /**
     * Method that stores a specific list as the object's list of Sensor DTOs.
     *
     * @param listToStore is the list we want to store.
     */

    public void setSensorList(List<AreaSensorDTO> listToStore) {
        this.areaSensorDTOList = new ArrayList<>(listToStore);
    }

    /**
     * Method that retrieves the object's list of sensor DTOs.
     *
     * @return is a list of sensorDTOs.
     */

    public List<GeographicAreaDTO> getDaughterAreas() {
        return new ArrayList<>(daughterAreaDTOList);
    }

    /**
     * Method that stores a specific list as the object's list of Sensor DTOs.
     *
     * @param listToStore is the list we want to store.
     */

    public void setDaughterAreaList(List<GeographicAreaDTO> listToStore) {
        this.daughterAreaDTOList = new ArrayList<>(listToStore);
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

    /**
     * Method that receives a Local Dto and sets it as an attribute.
     */
    public void setLocal(LocalDTO localDTO) {
        this.local = localDTO;
    }

    /**
     * Method that retrieves the object's Local Dto.
     *
     * @return is the object's Local Dto.
     */
    public LocalDTO getLocal() {
        return this.local;
    }


    /**
     * method to add Daughter Area to a list of geographic areas
     * @param areaSensorDTO areasensor to add
     * @return true if added
     */
    public boolean addSensor(AreaSensorDTO areaSensorDTO) {
        if (!this.areaSensorDTOList.contains(areaSensorDTO)) {
            this.areaSensorDTOList.add(areaSensorDTO);
            return true;
        }
        return false;
    }

    /**
     * US011: Method for iterating through area sensor dto list, finding a sensor by ID and removing it.
     *
     * @param areaSensorID id of the sensor to be removed.
     * @return true if the sensor is found and removed, or false if not found.
     */

    public boolean removeSensor(String areaSensorID) {
        for (AreaSensorDTO s : areaSensorDTOList) {
            if (s.getSensorId().equals(areaSensorID)) {
                this.areaSensorDTOList.remove(s);
                return true;
            }
        }
        return false;
    }


    /**
     * method that returns a area sensor DTO found by id
     *
     * @return area sensor dto with the selected id
     */
    public AreaSensorDTO getAreaSensorByID(String idSensor) {
        for (AreaSensorDTO as : this.getSensors()) {
            String asString = as.getSensorId();
            if (asString.equals(idSensor)) {
                return as;
            }
        }
        throw new IllegalArgumentException(("Area Sensor not found"));
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
        LocalDTO testDTOLocal = localVariable.getLocal();
        return (localVariable.getTypeArea().equals(this.typeArea) && localVariable.getName().equals(this.name)
                && testDTOLocal.equals(this.local));
    }

    @Override
    public int hashCode() {
        return 1;
    }

}