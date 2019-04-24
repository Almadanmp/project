package pt.ipp.isep.dei.project.model.geographicarea;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import javax.persistence.*;
import java.util.*;

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
    private AreaType areaType;

    private double length;
    private double width;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mother_area_id")
    private GeographicArea motherArea;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "geographicAreaId")
    private List<AreaSensor> areaSensors;

    @Embedded
    private Local location;


    private String description;


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
     * @param name     the name of the Area
     * @param areaType the type of the area.
     * @param length   the total length of the area.
     * @param width    the total width of the area.
     * @param location the location of the area,
     */

    public GeographicArea(String name, AreaType areaType, double length, double width, Local location) {
        this.name = name;
        this.areaType = areaType;
        this.length = length;
        this.width = width;
        this.location = location;
        this.areaSensors = new ArrayList<>();
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
     * @param geoArea is the Geographical Area that contains this Geographical Area.
     */
    public boolean setMotherArea(GeographicArea geoArea) {
        if (geoArea != null) {
            this.motherArea = geoArea;
            return true;
        } else {
            return false;
        }
    }

    public List<AreaSensor> getAreaSensors() {
        return areaSensors;
    }

    public void setAreaSensors(List<AreaSensor> areaSensors) {
        this.areaSensors = areaSensors;
    }


    public AreaSensor getSensor(int index) {
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
     * Standard getter method, to return the Geographical Area that contains the current Geographic Area.
     *
     * @return is the Geographical Area that contains this Geographical Area.
     */
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
     * @return returns the attribute AreaType from an object of the class Geographic Area
     */
    public AreaType getAreaType() {
        return this.areaType;
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
        result = this.name + ", " + this.areaType.getName() + ", " +
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
     * This method checks if mother area is equal to geographic area given.
     *
     * @param geographicArea the GA to be tested.
     * @return true if is equal to geographic area given, false otherwise.
     **/

    private boolean isMotherAreaEqual(GeographicArea geographicArea) {
        return this.motherArea.equals(geographicArea);
    }

    //SENSOR RELATED METHODS

    public boolean removeSensor(AreaSensor areaSensor) {
        if (areaSensors.contains(areaSensor)) {
            areaSensors.remove(areaSensor);
            return true;
        }
        return false;
    }


    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return AreaSensorList with sensors closest to house.
     **/
    private List<AreaSensor> getAreaSensorsByDistanceToHouse(List<AreaSensor> areaSensors, House house, double minDist) {
        List<AreaSensor> finalList = new ArrayList<>();
        for (AreaSensor s : areaSensors) {
            if (Double.compare(minDist, s.getDistanceToHouse(house)) == 0) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    private double getMinDistanceToSensorOfGivenType(List<AreaSensor> areaSensors, House house) {
        List<Double> arrayList = getSensorsDistanceToHouse(areaSensors, house);
        return Collections.min(arrayList);
    }

    /**
     * Goes through the sensor list, calculates sensors distance to house and
     * returns values in ArrayList.
     *
     * @param house to calculate closest distance
     * @return List of sensors distance to house
     */
    private List<Double> getSensorsDistanceToHouse(List<AreaSensor> areaSensors, House house) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (AreaSensor areaSensor : areaSensors) {
            arrayList.add(house.calculateDistanceToSensor(areaSensor));
        }
        return arrayList;
    }

    /**
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType the type of sensor to check
     * @return the closest sensor.
     */
    public AreaSensor getClosestAreaSensorOfGivenType(String sensorType, House house) {

        AreaSensor areaSensor;

        List<AreaSensor> minDistSensor = new ArrayList<>();


        AreaSensor areaSensorError = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", " " +
                ""), new Local(0, 0, 0), new GregorianCalendar(1900, Calendar.FEBRUARY,
                1).getTime(), 2356L);

        List<AreaSensor> sensorsOfGivenType = getAreaSensorsOfGivenType(areaSensors, sensorType);

        if (!sensorsOfGivenType.isEmpty()) {
            double minDist = getMinDistanceToSensorOfGivenType(sensorsOfGivenType, house);

            minDistSensor = getAreaSensorsByDistanceToHouse(sensorsOfGivenType, house, minDist);
        }
        if (minDistSensor.isEmpty()) {
            return areaSensorError;
        }
        if (minDistSensor.size() > 1) {

            areaSensor = getMostRecentlyUsedAreaSensor(minDistSensor);
        } else {
            areaSensor = minDistSensor.get(0);
        }
        return areaSensor;
    }

    private AreaSensor getMostRecentlyUsedAreaSensor(List<AreaSensor> areaSensors) {
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        List<AreaSensor> areaSensors2 = getAreaSensorsWithReadings(areaSensors);
        if (areaSensors2.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }

        AreaSensor mostRecent = areaSensors2.get(0);
        List<Reading> mostRecentSensorReadings = mostRecent.getAreaReadings();

        Reading recentReading = ReadingUtils.getMostRecentReading(mostRecentSensorReadings);
        Date recent = recentReading.getDate();


        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = new ArrayList<>();

            Date test = ReadingUtils.getMostRecentReadingDate(sensorReadings);
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }

    private List<AreaSensor> getAreaSensorsOfGivenType(List<AreaSensor> areaSensors, String sensorType) {
        List<AreaSensor> sensorsOfGivenType = new ArrayList<>();
        for (AreaSensor aS : areaSensors) {
            if (aS.getSensorType().getName().equals(sensorType)) {
                sensorsOfGivenType.add(aS);
            }

        }
        return sensorsOfGivenType;
    }

    /**
     * Method that goes through the sensor list and returns a list of those which
     * have readings. The method throws an exception in case the sensorList is empty.
     *
     * @return AreaSensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    private List<AreaSensor> getAreaSensorsWithReadings(List<AreaSensor> areaSensors) {
        List<AreaSensor> finalList = new ArrayList<>();
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = s.getAreaReadings();

            if (!sensorReadings.isEmpty()) {
                finalList.add(s);
            }
        }
        return finalList;
    }


    boolean equalsParameters(String name, AreaType areaType, Local local) {
        return (this.name.equals(name) && (this.areaType.equals(areaType) && (this.location.equals(local))));
    }

    /**
     * This method checks if type area given match that of geographic area.
     *
     * @param areaType the type of Area
     * @return true if it matches, false if it does not.
     **/

    boolean equalsTypeArea(AreaType areaType) {
        return (this.areaType.equals(areaType));
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
        return (this.getLocal().equals(gA.getLocal()) && (this.getName().equals(gA.getName()) && (this.getAreaType().equals(gA.getAreaType()))));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
