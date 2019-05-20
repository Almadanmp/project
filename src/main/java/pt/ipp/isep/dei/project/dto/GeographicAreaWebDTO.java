package pt.ipp.isep.dei.project.dto;

import java.util.ArrayList;
import java.util.List;

public class GeographicAreaWebDTO {

    private Long id;
    private String name;
    private String description;
    private String typeArea;
    private List<AreaSensorWebDTO> areaSensorDTOList = new ArrayList<>();


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

    public List<AreaSensorWebDTO> getSensorDTOs() {
        return new ArrayList<>(areaSensorDTOList);
    }

    public void setSensorDTOList(List<AreaSensorWebDTO> listToStore) {
        this.areaSensorDTOList = new ArrayList<>(listToStore);
    }

    public String getTypeArea() {
        return typeArea;
    }

    public void setTypeArea(String typeArea) {
        this.typeArea = typeArea;
    }

    public String getDescription() {
        return description;
    }

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
        LocalDTO testDTOLocal = localVariable.getLocal();
        return (localVariable.getTypeArea().equals(this.typeArea) && localVariable.getName().equals(this.name)
                );
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public boolean addSensor(AreaSensorWebDTO areaSensorDTO) {
        if (!this.areaSensorDTOList.contains(areaSensorDTO)) {
            this.areaSensorDTOList.add(areaSensorDTO);
            return true;
        }
        return false;
    }

    public boolean removeSensor(String areaSensorID) {
        for (AreaSensorWebDTO s : areaSensorDTOList) {
            if (s.getId().equals(areaSensorID)) {
                this.areaSensorDTOList.remove(s);
                return true;
            }
        }
        return false;
    }
}
