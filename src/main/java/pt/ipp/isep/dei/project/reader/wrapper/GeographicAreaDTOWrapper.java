package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaDTOWrapper {
    private Long id;

    @JsonProperty("id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;

    @JsonProperty("type")
    private String typeArea;

    @JsonProperty("length")
    private double length;

    @JsonProperty("width")
    private double width;


    @JsonProperty("latitude")
    private double latitude;
    @JsonProperty("longitude")
    private double longitude;
    @JsonProperty("altitude")
    private double altitude;


    @JsonProperty("area_sensor")
    private List<AreaSensorDTO> areaSensorDTOList = new ArrayList<>();

    @JsonProperty("description")
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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


    public List<AreaSensorDTO> getAreaSensorDTOList() {
        return areaSensorDTOList;
    }


    public void setAreaSensorDTOList(List<AreaSensorDTO> listToStore) {
        this.areaSensorDTOList = listToStore;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setLocalDTO(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude =altitude;
    }


    public LocalDTO getLocalDTO(){
        LocalDTO local = new LocalDTO();
        local.setLongitude(this.longitude);
        local.setLatitude(this.latitude);
        local.setAltitude(this.altitude);
        return local;
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
        LocalDTO testDTOLocal = localVariable.getLocalDTO();
        return (localVariable.getTypeArea().equals(this.typeArea) && localVariable.getName().equals(this.name)
                && testDTOLocal.equals(new LocalDTO(this.latitude,this.longitude,this.altitude)) &&
                localVariable.getAreaSensorDTOList().equals(this.areaSensorDTOList));
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
