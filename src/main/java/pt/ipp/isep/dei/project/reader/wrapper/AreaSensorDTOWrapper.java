package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;

import java.util.ArrayList;
import java.util.List;

public class AreaSensorDTOWrapper {

    @JsonProperty("sensor")
    private AreaSensorAttributeWrapper sensorAttributeWrapper;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("altitude")
    private double altitude;

    @JsonProperty("location")
    private LocalDTO localDTO = new LocalDTO(latitude, longitude, altitude);

    private boolean active;

    private List<ReadingDTO> readingList;

    AreaSensorDTOWrapper() {
        readingList = new ArrayList<>();
    }

    void setSensorAttributeWrapper(AreaSensorAttributeWrapper sensorAttributeWrapper) {
        this.sensorAttributeWrapper = sensorAttributeWrapper;
    }

    public LocalDTO getLocalDTO() {
        return localDTO;
    }

    public void setLocalDTO(LocalDTO localDTO) {
        this.localDTO = localDTO;
    }

    AreaSensorAttributeWrapper getSensorAttributeWrapper() {
        return sensorAttributeWrapper;
    }


    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    List<ReadingDTO> getReadingList() {
        return readingList;
    }


    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof AreaSensorDTOWrapper)) {
            return false;
        }
        AreaSensorDTOWrapper localVariable = (AreaSensorDTOWrapper) testDTO;
        return (localVariable.getSensorAttributeWrapper().equals(this.sensorAttributeWrapper));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

