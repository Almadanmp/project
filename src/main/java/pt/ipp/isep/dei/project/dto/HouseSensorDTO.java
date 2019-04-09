package pt.ipp.isep.dei.project.dto;

import java.util.ArrayList;
import java.util.List;

public class HouseSensorDTO {

    private String name;
    private String typeSensor;
    private String units;
    private String dateStartedFunctioning;
    private boolean active;
    private List<ReadingDTO> readingList;

    public HouseSensorDTO(){
        readingList = new ArrayList<>();
    }

    /**
     * Method that retrieves the units the Sensor stores readings in, as a String.
     *
     * @return is the unit the Sensor stores readings in.
     */

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

    /**
     * Method that retrieves the DTO's type's name as a string.
     *
     * @return is a string that corresponds to the name of the type of the DTO.
     */

    public String getType() {
        return typeSensor;
    }

    /**
     * Method that stores a String as the DTO's type.
     *
     * @param typeSensor is the string we want to store.
     */
    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
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
        if (!(testDTO instanceof HouseSensorDTO)) {
            return false;
        }
        HouseSensorDTO localVariable = (HouseSensorDTO) testDTO;
        return (localVariable.getName().equals(this.name));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
