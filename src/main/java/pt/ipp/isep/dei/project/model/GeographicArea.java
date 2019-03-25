package pt.ipp.isep.dei.project.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Class that represents a Geographical Area.
 */

@Entity
public class GeographicArea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_area_id")
    private TypeArea typeArea;

    private double length;
    private double width;

    @ManyToOne
    @JoinColumn(name = "mother_area_id")
    private GeographicArea motherArea;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local location;

    @Transient
    private SensorList areaSensors;

//    @OneToMany(mappedBy = "geographicArea")
//    private List<Sensor> sensors;


    private String description;
    //@Id
    private UUID uniqueId;


    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * Empty Constructor to use when importing Geographic Areas from XML files.
     */
    public GeographicArea() {
        this.areaSensors = new SensorList();
    }

    /**
     * Constructor
     *
     * @param name     the name of the Area
     * @param typeArea the type of the area.
     * @param length   the total length of the area.
     * @param width    the total width of the area.
     * @param location the location of the area,
     */

    public GeographicArea(String name, TypeArea typeArea, double length, double width, Local location) {
        this.name = name;
        this.typeArea = typeArea;
        this.length = length;
        this.width = width;
        this.location = location;
        this.areaSensors = new SensorList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Setters and Getters for all the parameters.

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeArea(TypeArea typeArea) {
        this.typeArea = typeArea;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Local getLocation() {
        return location;
    }

    public void setLocation(Local location) {
        this.location = location;
    }

    public SensorList getAreaSensors() {
        return areaSensors;
    }

    public void setAreaSensors(SensorList areaSensors) {
        this.areaSensors = areaSensors;
    }

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

    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for Sensor List.
     *
     * @param listToSet list to set
     */
    public void setSensorList(SensorList listToSet) {
        this.areaSensors = listToSet;
    }

    public boolean setMotherArea(GeographicArea geoArea) {
        if (geoArea != null) {
            this.motherArea = geoArea;
            return true;
        } else {
            return false;
        }
    }

    GeographicArea getMotherArea() {
        return this.motherArea;
    }


    /**
     * This method is used to check if the current GeoArea is contained in the given area.
     *
     * @param motherArea - Geographic Area being compared to daughter area mother area attribute.
     * @return true if contained, false if not.
     */

    public boolean isContainedInArea(GeographicArea motherArea) {
        GeographicArea tempMotherArea = this;
        while (tempMotherArea.getMotherArea() != null) {
            if (tempMotherArea.isMotherAreaEqual(motherArea)) {
                return true;
            } else {
                tempMotherArea = this.getMotherArea();
            }
        }
        return false;
    }

    /**
     * Getter for type of Geographic Area.
     *
     * @return returns the attribute TypeArea from an object of the class Geographic Area
     */
    public TypeArea getTypeArea() {
        return this.typeArea;
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
     * Getter for Geographic Area sensor list.
     *
     * @return returns the attribute sensorList from an object of the class Geographic Area
     */
    public SensorList getSensorList() {
        return this.areaSensors;
    }

    /**
     * Method to print details that are required for a Geographic Area to be different from another GA (equals -
     * name, type area and local).
     *
     * @return returns a string with Geographic Area Parameters
     */

    public String buildString() {
        String result;
        result = this.name + ", " + this.typeArea.getName() + ", " +
                this.location.getLatitude() + "º lat, " + this.location.getLongitude() + "º long\n";
        return result;
    }

    public double getWidth() {
        return this.width;
    }

    public double getLength() {
        return this.length;
    }

    /**
     * Method adds sensor to geographic area sensor list.
     *
     * @param sensor to add
     * @return true in case the sensor is added, false otherwise.
     **/
    public boolean addSensor(Sensor sensor) {
        return this.areaSensors.add(sensor);
    }

    /**
     * Method checks if geographic area's sensor list is empty.
     *
     * @return true if SensorList is empty, false otherwise
     */
    public boolean isSensorListEmpty() {
        return areaSensors.isEmpty();
    }

    /**
     * This method checks if mother area is equal to geographic area given.
     *
     * @param geographicArea the GA to be tested.
     * @return true if is equal to geographic area given, false otherwise.
     **/

    private boolean isMotherAreaEqual(GeographicArea geographicArea) {
        return this.motherArea.equals(geographicArea);
    }

    /**
     * This method checks if name, type area and local match those of geographic area.
     *
     * @param name     the name of the Geographic Area
     * @param local    the localization on the Geographic Area
     * @param typeArea the type of Geographic Area
     * @return true if it matches, false if it does not.
     **/

    boolean equalsParameters(String name, TypeArea typeArea, Local local) {
        return (this.name.equals(name) && (this.typeArea.equals(typeArea) && (this.location.equals(local))));
    }

    /**
     * This method checks if type area given match that of geographic area.
     *
     * @param typeArea the type of Area
     * @return true if it matches, false if it does not.
     **/

    boolean equalsTypeArea(TypeArea typeArea) {
        return (this.typeArea.equals(typeArea));
    }

    /**
     * Method that removes a Sensor from the Geographical Area.
     *
     * @param sensor is the sensor we want to remove.
     * @return true if the sensor was successfully removed from the geographical area, false otherwise.
     */
    public boolean removeSensor(Sensor sensor) {
        return areaSensors.remove(sensor);
    }

    /**
     * This method receives the string type name and returns a list of sensors
     * from geographic area of that type.
     *
     * @param type the type of Sensor to test.
     * @return SensorList of given type
     **/

    SensorList getSensorsOfGivenType(String type) {
        return this.areaSensors.getSensorListByType(type);
    }

    /**
     * Method that adds all the sensorDTOs in an array to a GeographicAreaDTO.
     *
     * @param sensorsToAdd is the array containing all the sensor DTOs that we want to add somewhere.
     */

    public void addSensors(List<Sensor> sensorsToAdd) {
        for (Sensor sensor : sensorsToAdd) {
            this.addSensor(sensor);
        }
    }

    /**
     * Method 'equals' is required so that each 'Geographic Area' can be added to a 'Geographic Area List'. Two
     * Geographic Areas cannot have the same Localization, name and TypeArea
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
        return (this.getLocal().equals(gA.getLocal()) && (this.getName().equals(gA.getName()) && (this.getTypeArea().getName().equals(gA.getTypeArea().getName()))));
    }

    @Override
    public String toString() {
        return String.format(
                "GeographicArea[id=%s, typeArea='%s', length='%s, width='%s', motherArea='%s, location='%s', description='%s', uniqueId='%s']",
                name, typeArea, length, width, motherArea, location, description, uniqueId);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
