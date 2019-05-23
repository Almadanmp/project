package pt.ipp.isep.dei.project.model.geographicarea;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pt.ipp.isep.dei.project.dddplaceholders.Root;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.model.Local;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class that represents a Geographical Area.
 */

@Entity
public class GeographicArea implements Root {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String areaTypeID;

    private double length;
    private double width;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "daughterAreaId")
    private List<GeographicArea> daughterAreas;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "geographicAreaId")
    private List<AreaSensor> areaSensors;

    @Embedded
    private Local location;

    private String description;

    private static final String BUILDER = "---------------\n";
    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * Empty Constructor to use when importing Geographic Areas from XML files.
     */
    public GeographicArea() {
    }

    /**
     * Constructor
     *
     * @param name       the name of the Area
     * @param areaTypeID the type of the area.
     * @param length     the total length of the area.
     * @param width      the total width of the area.
     * @param location   the location of the area,
     */

    public GeographicArea(String name, String areaTypeID, double length, double width, Local location) {
        this.name = name;
        this.areaTypeID = areaTypeID;
        this.length = length;
        this.width = width;
        this.location = location;
        this.areaSensors = new ArrayList<>();
        this.daughterAreas = new ArrayList<>();
    }

    /**
     * Standard getter method, to return the Id of the Geographical Area.
     *
     * @return the long with the Id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Standard setter method, to define the Id.
     *
     * @param id is the Id of the Geographical Area.
     */
    public void setId(Long id) {
        this.id = id;
    }

    // Setters and Getters for all the parameters.

    /**
     * Standard setter method, to define the name of the Geographical Area.
     *
     * @param name is the name of the Geographical Area.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Standard setter method, to define the length of the Geographic Area.
     *
     * @param length is the length value of the Geographical Area.
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Standard setter method, to define the width of the Geographic Area.
     *
     * @param width is the width value of the Geographical Area.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Standard getter method, to return the Location of the Geographical Area.
     *
     * @return the Location of the Geographical Area.
     */
    public Local getLocation() {
        return location;
    }

    /**
     * Standard setter method, to define the location of the Geographic Area.
     *
     * @param location is the Local of the Geographical Area.
     */
    public void setLocation(Local location) {
        this.location = location;
    }

    /**
     * Standard getter method, to return the name of the Geographical Area.
     *
     * @return the string with the Geographical Area name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the description attribute
     *
     * @param description String is a short description of the Geographical Area.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAreaTypeID(String areaTypeID) {
        this.areaTypeID = areaTypeID;
    }


    /**
     * Standard getter method, to return the description of the Geographical Area.
     *
     * @return the string with the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Standard setter method, to define the Geographical Area that contains the current Geographic Area.
     *
     * @param geoAreas is the Geographical Area that contains this Geographical Area.
     */
    void setDaughterAreas(List<GeographicArea> geoAreas) {
        this.daughterAreas = new ArrayList<>(geoAreas);
    }

    public List<GeographicArea> getDaughterAreas() {
        return new ArrayList<>(this.daughterAreas);
    }

    /**
     * This method receives a geographic area and tries to add it to
     * the parameter list of geographic areas.
     *
     * @param geoArea geographic area to add
     * @return true in case it is added, false otherwise
     * **/
    public boolean addDaughterArea(GeographicArea geoArea) {
        if (!this.daughterAreas.contains(geoArea)) {
            this.daughterAreas.add(geoArea);
            return true;
        }
        return false;
    }

    /**
     * This method will get the list of Area Sensors.
     **/
    public List<AreaSensor> getSensors() {
        return new ArrayList<>(this.areaSensors);
    }

    public void setAreaSensors(List<AreaSensor> areaSensors) {
        this.areaSensors = new ArrayList<>(areaSensors);
    }


    AreaSensor getSensor(int index) {
        return this.areaSensors.get(index);
    }

    public boolean addSensor(AreaSensor areaSensor) {
        if (!this.areaSensors.contains(areaSensor)) {
            this.areaSensors.add(areaSensor);
            return true;
        }
        return false;
    }


    /**
     * Getter for type of Geographic Area.
     *
     * @return returns the attribute AreaType from an object of the class Geographic Area
     */
    public String getAreaTypeID() {
        return this.areaTypeID;
    }


    /**
     * Getter for Geographic Area localization.
     *
     * @return returns the attribute local from an object of the class Geographic Area
     */
    public Local getLocal() {
        return this.location;
    }

    /**
     * Method to print details that are required for a Geographic Area to be different from another GA (equals -
     * name, type area and local).
     *
     * @return returns a string with Geographic Area Parameters
     */

    public String buildString() {
        String result;
        result = this.name + ", " + this.areaTypeID + ", " +
                this.location.getLatitude() + "º lat, " + this.location.getLongitude() + "º long\n";
        return result;
    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }


    public GeographicArea getDaughterAreaByID(Long daughterID) {
        for (GeographicArea geoArea : this.daughterAreas) {
            Long daughterAreaID = geoArea.getId();
            if (daughterID.equals(daughterAreaID)) {
                return geoArea;
            }
        }
        throw new IllegalArgumentException();
    }

    //SENSOR RELATED METHODS

    public boolean removeSensor(AreaSensor areaSensor) {
        if (areaSensors.contains(areaSensor)) {
            areaSensors.remove(areaSensor);
            return true;
        }
        return false;
    }


    boolean equalsParameters(String name, String areaType, Local local) {
        return (this.name.equals(name) && (this.areaTypeID.equals(areaType) && (this.location.equals(local))));
    }

    /**
     * This method will receive a string of a sensor ID and
     * will look for a sensor with the given ID from the sensors list.
     *
     * @param sensorID string of sensor ID
     * @return the sensor of the given sensor ID
     **/
    AreaSensor getAreaSensorByID(String sensorID) {
        for (AreaSensor sensor : this.areaSensors) {
            String tempSensorName = sensor.getId();
            if (sensorID.equals(tempSensorName)) {
                return sensor;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    public String buildString(List<AreaSensor> areaSensors) {
        StringBuilder result = new StringBuilder(new StringBuilder(BUILDER));
        if (areaSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (AreaSensor as : areaSensors) {
            result.append(as.getId()).append(") Name: ").append(as.getName()).append(" | ");
            result.append("Type: ").append(as.getSensorTypeName()).append(" | ")
                    .append(as.printActive()).append("\n");
        }
        result.append(BUILDER);
        return result.toString();
    }

    public AreaSensor createAreaSensor(String id, String name, String sensorName, Local local, Date dateStartedFunctioning) {
        if (sensorName != null) {
            return new AreaSensor(id, name, sensorName, local, dateStartedFunctioning);
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * US011: Method for iterating through area sensor dto list, finding a sensor by ID and removing it.
     *
     * @param areaSensorID id of the sensor to be removed.X
     * @return true if the sensor is found and removed, or false if not found.
     */

    public boolean removeSensor(String areaSensorID) {
        for (AreaSensor s : areaSensors) {
            if (s.getId().equals(areaSensorID)) {
                this.areaSensors.remove(s);
                return true;
            }
        }
        return false;
    }
    /**X
     * deactivateSensor on sensor DTO list
     * @param areaSensor area sensor dto to be deactivated
     * @return true if deactivated
     */
    public boolean deactivateSensorDTO(AreaSensor areaSensor) {
        if (this.removeSensor(areaSensor.getId())) {
            areaSensor.setActive(false);
            this.addSensor(areaSensor);
            return true;
        }
        return false;
    }

    /**
     * Method 'equals' is required so that each 'Geographic Area' can be added to a 'Geographic Area List'. Two
     * Geographic Areas cannot have the same Localization, name and AreaType
     */
    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof GeographicArea)) {
            return false;
        }
        GeographicArea gA = (GeographicArea) testObject;
        return (this.getLocal().equals(gA.getLocal()) && (this.getName().equals(gA.getName()) && (this.getAreaTypeID().equals(gA.getAreaTypeID()))));
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
