package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaDTOWrapper {

    @JsonProperty("id")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String typeArea;

    @JsonProperty("width")
    private double width;

    @JsonProperty("length")
    private double length;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("altitude")
    private double altitude;

    @JsonProperty("location")
    private LocalDTO localDTO = new LocalDTO(latitude, longitude, altitude);

    @JsonProperty("area_sensor")
    private List<AreaSensorDTOWrapper> areaSensorDTOList = new ArrayList<>();


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalDTO(LocalDTO localDTO) {
        this.localDTO = localDTO;
    }

    public LocalDTO getLocalDTO() {
        return this.localDTO;
    }

    /**
     * This method receives a List of Geographic Area DTO Wrappers and returns a HashMap that contains Geographic Area DTO
     * with its corresponding id.
     *
     * @param geographicAreaDTOWrappers list of areas Dto wrappers
     * @return hashmap containing Reading DTOs with its corresponding sensor ID
     **/
    public static List<GeographicAreaDTO> geographicAreaDTOWrapperConversion(List<GeographicAreaDTOWrapper> geographicAreaDTOWrappers) {
        List<GeographicAreaDTO> finalList = new ArrayList<>();
        for (GeographicAreaDTOWrapper wrapper : geographicAreaDTOWrappers) {
            GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();
            geographicAreaDTO.setTypeArea(wrapper.getTypeArea());
            geographicAreaDTO.setLength(wrapper.getLength());
            geographicAreaDTO.setWidth(wrapper.getWidth());
            geographicAreaDTO.setDescription(wrapper.getDescription());
            geographicAreaDTO.setName(wrapper.getName());
            geographicAreaDTO.setLocalDTO(wrapper.getLocalDTO());
            if (!finalList.contains(geographicAreaDTO)) {
                finalList.add(geographicAreaDTO);
            }
        }
        return finalList;
    }

    /**
     * This method receives a List of Geographic Area DTO Wrappers and returns a HashMap that contains Geographic Area DTO
     * with its corresponding id.
     *
     * @param areaSensorDTOWrappers list of areas Dto wrappers
     * @return hashmap containing Reading DTOs with its corresponding sensor ID
     **/
    public static List<AreaSensorDTO> areaSensorDTOWrapperConversion(List<AreaSensorDTOWrapper> areaSensorDTOWrappers) {
        List<AreaSensorDTO> finalList = new ArrayList<>();
        for (AreaSensorDTOWrapper wrapper : areaSensorDTOWrappers) {
            AreaSensorDTO areaSensorDTO = new AreaSensorDTO();
            areaSensorDTO.setId(wrapper.getSensorAttributeWrapper().getId());
            areaSensorDTO.setName(wrapper.getSensorAttributeWrapper().getName());
            areaSensorDTO.setActive(wrapper.getActive());
            areaSensorDTO.setDateStartedFunctioning(wrapper.getSensorAttributeWrapper().getDateStartedFunctioning());
            areaSensorDTO.setTypeSensor(wrapper.getSensorAttributeWrapper().getType());
            areaSensorDTO.setUnits(wrapper.getSensorAttributeWrapper().getUnits());
            areaSensorDTO.setReadingDTOS(wrapper.getReadingList());
            areaSensorDTO.setLocalDTO(wrapper.getLocalDTO());
            if (!finalList.contains(areaSensorDTO)) {
                finalList.add(areaSensorDTO);
            }
        }
        return finalList;
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof GeographicAreaDTOWrapper)) {
            return false;
        }

        GeographicAreaDTOWrapper localVariable = (GeographicAreaDTOWrapper) testDTO;
        LocalDTO testDTOLocal = localVariable.getLocalDTO();
        return (localVariable.getTypeArea().equals(this.typeArea) && localVariable.getName().equals(this.name)
                && testDTOLocal.equals(new LocalDTO(this.latitude, this.longitude, this.altitude)));
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
