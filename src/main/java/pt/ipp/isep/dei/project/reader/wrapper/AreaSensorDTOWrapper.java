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
    private LocalDTO localDTO = new LocalDTO(latitude,longitude,altitude);

    private boolean active;

    private List<ReadingDTO> readingList;

    public AreaSensorDTOWrapper(){
        readingList = new ArrayList<>();
    }



    public LocalDTO getLocalDTO() {
        return localDTO;
    }

    public void setLocalDTO(LocalDTO localDTO) {
        this.localDTO = localDTO;
    }

    public AreaSensorAttributeWrapper getSensorAttributeWrapper() {
        return sensorAttributeWrapper;
    }

    public void setSensorAttributeWrapper(AreaSensorAttributeWrapper sensorAttributeWrapper1) {
        this.sensorAttributeWrapper = sensorAttributeWrapper1;
    }

    /**
     * Method that retrieves the DTO's altitude as a double.
     *
     * @return is the DTO's altitude.
     */

    public double getAltitude() {
        return altitude;
    }

    /**
     * Method that retrieves the DTO's latitude as a double.
     *
     * @return is the DTO's latitude.
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     * Method that retrieves the DTO's longitude as a double.
     *
     * @return is the DTO's longitude.
     */

    public double getLongitude() {
        return longitude;
    }

    /**
     * Method that stores a double as the DTO's altitude.
     *
     * @param altitude is the value we want to store.
     */

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Method that stores a double as the DTO's latitude.
     *
     * @param latitude is the value we want to store.
     */

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Method that stores a double as the DTO's longitude.
     *
     * @param longitude is the value we want to store.
     */

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public List<ReadingDTO> getReadingList() {
        return readingList;
    }

    public void setReadingList(List<ReadingDTO> readingList) {
        this.readingList = readingList;
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

