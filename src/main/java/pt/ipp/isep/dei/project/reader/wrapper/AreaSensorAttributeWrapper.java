package pt.ipp.isep.dei.project.reader.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaSensorAttributeWrapper {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String typeSensor;

    @JsonProperty("units")
    private String units;

    @JsonProperty("start_date")
 //   @JsonDeserialize(using = CustomDateDeserializer.class)
    private String dateStartedFunctioning;

    /**
     * Method that retrieves the DTO's id.
     *
     * @return is the DTO's id.
     */

    public String getId() {
        return id;
    }

    /**
     * Method that stores a String as the DTO's id.
     *
     * @param id is the string we want to store.
     */

    public void setId(String id) {
        this.id = id;
    }



    public void setTypeSensor(String typeSensor) {
        this.typeSensor = typeSensor;
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
}
