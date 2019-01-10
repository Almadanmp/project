package pt.ipp.isep.dei.project.model;

import java.util.Date;

/**
 * This is the central class.
 */

public class GeographicArea {

    private String mId;
    private TypeArea mTypeArea;
    private double mLength;
    private double mWidth;
    private GeographicArea mMotherArea;
    private Local mLocation;
    private SensorList mAreaSensors;
    private String mDescription;



    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * empty constructor to use on UIs
     */
    public GeographicArea() {
        mAreaSensors = new SensorList();
    }

    public GeographicArea(String id, TypeArea typeArea, double length, double width, Local location) {
        setId(id);
        setTypeArea(typeArea);
        setLength(length);
        setWidth(width);
        setLocal(location);
    }

    // Setters and Getters for all the parameters.

    public String getId() {
        return this.mId;
    }

     public void setId(String name) {
        if (isGeographicNameValid(name)) {
            this.mId = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Sets the width attribute
     * @param width related to longitude
     */

    public void setWidth(double width){this.mWidth = width;}

    /**
     * Sets the length attribute
     * @param length related to latitude
     */

    public void setLength(double length){this.mLength = length;}

    /**
     * Sets the description attribute
     * @param description String is a short description of the Geographical Area.
     */

    public void setDescription(String description){
        this.mDescription = description;
    }

    public String getDescription() {return this.mDescription;}
    /**
     * Setter for Geographic Area type.
     *
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     */
    void setTypeArea(TypeArea typeArea) {
        this.mTypeArea = typeArea;
    }

    /**
     * Setter for Geographic Area localization.
     *
     * @param local Localization is defined by three doubles (longitude, latitude and altitude).
     */
     void setLocal(Local local) {
        this.mLocation = local;
    }


    /**
     * Setter for Sensor List.
     *
     * @param listToSet
     */

    public void setSensorList(SensorList listToSet) {
        this.mAreaSensors = listToSet;
    }

    public void setMotherArea(GeographicArea geoArea) {
        this.mMotherArea = geoArea;
    }

    GeographicArea getMotherArea() {
        return this.mMotherArea;
    }

    public boolean checkIfAreaIsContained(GeographicArea daughterArea, GeographicArea motherArea) {
        GeographicArea onTest = daughterArea;
        while (onTest.getMotherArea() != null) {
            if (onTest.getMotherArea().equals(motherArea)) {
                return true;
            } else {
                onTest = onTest.getMotherArea();
            }
        }
        return false;
    }

    /**
     * Getter for type of Geographic Area.
     *
     * @return returns the attribute TypeArea from an object of the class Geographic Area
     */
    TypeArea getTypeArea() {
        return this.mTypeArea;
    }

    /**
     * Getter for Geographic Area localization.
     *
     * @return returns the attribute local from an object of the class Geographic Area
     */
    public Local getLocal() {
        return this.mLocation;
    }

    /**
     * Getter for Geographic Area sensor list.
     *
     * @return returns the attribute sensorList from an object of the class Geographic Area
     */
    public SensorList getSensorList() {
        return this.mAreaSensors;
    }

    /**
     * Method to print details that are required for a Geographic Area to be different from another GA (equals -
     * name, type area and local).
     *
     * @return
     */

    public String printGeographicArea() {
        String result;
        result = this.mId + ", " + this.mTypeArea.getTypeOfGeographicArea() + ", " +
                this.mLocation.getLatitude() + "º lat, " + this.mLocation.getLongitude() + "º long\n";
        return result;
    }

    /**
     * Method to restrain input name so they cant be null or empty.
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    boolean isGeographicNameValid(String name) {
        return (name != null && !name.isEmpty());
    }


    /**
     * Method will go through Geographic Area's sensor list, create a second list with the type
     * of sensors defined by the parameter and finally return the most recent value recorded in that list.
     *
     * @param typeOfSensor Type sensor is determined by a string - e.g. "Temperature", "Rain", etc.
     * @return returns a double of the most recent value recorded in every type sensor given
     */
    double getMostRecentReadingValue(String typeOfSensor) {
        SensorList listToTest = this.mAreaSensors;
        for (int i = 0; i < listToTest.getSensorList().size(); i++) {
            if (!(listToTest.getSensorList().get(i).getTypeSensor().getName().equals(typeOfSensor))) {
                listToTest.removeSensor(listToTest.getSensorList().get(i));
            }
        }
        return listToTest.getMostRecentlyUsedSensor().getReadingList().getMostRecentReading().getmValue();
    }

    double getWidth(){return this.mWidth;}

    public double getLength(){return this.mLength;}

    boolean isAreaContainedInAnotherArea(GeographicArea area1, GeographicArea area2) {
        double latTopVert1 = area1.getLocal().getLatitude() + (area1.getWidth()/2);
        double longTopVert1 = area1.getLocal().getLongitude() - (area1.getLength()/2);
        double latBotVert1 = area1.getLocal().getLatitude() - (area1.getWidth()/2);
        double longBotVert1 = area1.getLocal().getLongitude() + (area1.getLength()/2);
        double latTopVert2 = area2.getLocal().getLatitude() + (area2.getWidth()/2);
        double longTopVert2 = area2.getLocal().getLongitude() - (area2.getLength()/2);
        double latBotVert2 = area2.getLocal().getLatitude() - (area2.getWidth()/2);
        double longBotVert2 = area2.getLocal().getLongitude() + (area2.getLength()/2);
        return (latTopVert2 <= latTopVert1 && longTopVert2 >= longTopVert1 && latBotVert2 >= latBotVert1 && longBotVert2 <= longBotVert1);
    }

    /**
     * Method will calculate the distance between two different Geographic Areas.
     *
     * @param ga object of the class GeographicAreaController
     * @return returns a double of the distance between Geographic Areas.
     */
    double calculateDistanceToGA(GeographicArea ga) {
        Local l = ga.getLocal();
        return this.mLocation.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to get the Average of Readings on a certain typeofSensor on a GeographicArea.
     *
     * @param typeSensor String input, the type of the sensor we want to get from the list e.g, "Rainfall"
     * @param dateMin    the start date of readings (start of interval)
     * @param dateMax    the end date of readings (end of interval)
     * @return average of the readings off all sensors of the GA SensorList with the input typeSensor
     */
    public double getAvgReadingsFromSensorTypeInGA(String typeSensor, Date dateMin, Date dateMax) {
        double average = 0;
        int counter = 0;
        if (mAreaSensors.getSensorList().isEmpty()) {
            return -1;
        }
        for (int i = 0; i < mAreaSensors.getSensorList().size(); i++) {
            Sensor sensorToGetAVG = mAreaSensors.getSensorList().get(i);
            if (sensorToGetAVG.getTypeSensor().getName().equals(typeSensor)) {
                average += sensorToGetAVG.getReadingList().getAverageReadingsBetweenTwoDays(dateMin, dateMax);
                counter++;
            }
        }
        if (counter == 0) {
            return -1;
        }
        return average / counter;
    }

    public boolean doesSensorListInAGeoAreaContainASensorByName(String name) {
        for (Sensor s : mAreaSensors.getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
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
        return (this.getLocal().equals(gA.getLocal()) && (this.getId().equals(gA.getId()) && (this.getTypeArea().getTypeOfGeographicArea().equals(gA.getTypeArea().getTypeOfGeographicArea()))));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
