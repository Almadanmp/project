package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class AreaSensorDTO extends ResourceSupport {

    private String sensorId;
    private String name;
    private String typeSensor;
    private String units;
    private double latitude;
    private double longitude;
    private double altitude;
    private String dateStartedFunctioning;
    private boolean active = true;
    private List<ReadingDTO> readingDTOS;

    public AreaSensorDTO() {
        readingDTOS = new ArrayList<>();
    }

    public void setId(String id) {
        this.sensorId = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getAltitude() {
        return altitude;
    }

    /**
     * Method that stores a double as the DTO's altitude.
     *
     * @param altitude is the value we want to store.
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * Method that stores a double as the DTO's latitude.
     *
     * @param latitude is the value we want to store.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUnits() {
        return units;
    }

    /**
     * Method that stores a String as the DTO's unit.
     *
     * @param units is string we want to store.
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Method that retrieves the DTO's name as a string.
     *
     * @return is the DTO's name.
     */

    public String getName() {
        return name;
    }

    /**
     * Method that stores a String as the DTO's name.
     *
     * @param name is the string we want to store.
     */

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    /**
     * Method that stores a double as the DTO's longitude.
     *
     * @param longitude is the value we want to store.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Method that stores a String as the DTO's type.
     *
     * @param typeSensor is the string we want to store.
     */
    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
    }

    /**
     * Method that retrieves the DTO's type's name as a string.
     *
     * @return is a string that corresponds to the name of the type of the DTO.
     */

    public String getType() {
        return typeSensor;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method that retrieves the date at which the sensorDTO started functioning, as a string.
     *
     * @return the date at which the sensor started functioning, as a string.
     */

    public String getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    /**
     * Method that stores a string as the date at which the DTO started functioning.
     *
     * @param dateStartedFunctioning is the date that we want to store.
     */

    public void setDateStartedFunctioning(String dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    List<ReadingDTO> getReadingDTOS() {
        return new ArrayList<>(readingDTOS);
    }

    public void setReadingDTOS(List<ReadingDTO> readingDTOS) {
        this.readingDTOS = new ArrayList<>(readingDTOS);
    }

    @Override
    public boolean equals(Object testDTO) {
        if (this == testDTO) {
            return true;
        }
        if (!(testDTO instanceof AreaSensorDTO)) {
            return false;
        }
        AreaSensorDTO localVariable = (AreaSensorDTO) testDTO;
        return (localVariable.getSensorId().equals(this.sensorId) && localVariable.getName().equals(this.name));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
