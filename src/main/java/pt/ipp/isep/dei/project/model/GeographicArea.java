package pt.ipp.isep.dei.project.model;

import java.util.Date;

/**
 * This is the central class.
 */

public class GeographicArea {

    private String mName;
    private TypeArea mTypeArea;
    private GeographicArea mMotherArea;
    private Local mLocal;
    private Local mTopLeftVertex;
    private Local mBottomRightVertex;
    private HouseList mHouseList;
    private SensorList mSensorList;


    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * empty constructor to use on UIs
     */
    public GeographicArea() {
        mSensorList = new SensorList();
        mHouseList = new HouseList();
    }

    public GeographicArea(String mName, TypeArea mTypeArea, Local mLocal) {
        setName(mName);
        setTypeArea(mTypeArea);
        setLocal(mLocal);
        mSensorList = new SensorList();
        mHouseList = new HouseList();
    }

    public GeographicArea(String mName) {
        setName(mName);
        mSensorList = new SensorList();
        mHouseList = new HouseList();
    }

    /**
     * Constructor of the class GeographicAreaController that receives the type of Geographic Area and its localization
     * as parameters.
     *
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     * @param local    Localization is defined by longitude, latitude and altitude.
     */
    public GeographicArea(TypeArea typeArea, Local local) {
        setTypeArea(typeArea);
        setLocal(local);
        mSensorList = new SensorList();
        mHouseList = new HouseList();
    }

    /**
     * Constructor of the class GeographicAreaController that receives the type of Geographic Area, its location
     * and a list of sensors as parameters.
     *
     * @param typeArea   Type area is determined by a string - e.g. "Street", "City", etc.
     * @param local      Localization is defined by three doubles (longitude, latitude and altitude).
     * @param sensorList Defined by a List<Sensor>.
     */
    public GeographicArea(TypeArea typeArea, Local local, SensorList sensorList) {
        setTypeArea(typeArea);
        setLocal(local);
        setSensorList(sensorList);
        mHouseList = new HouseList();
    }

    public GeographicArea(String name, TypeArea typeArea, Local local, SensorList sensorList, HouseList houselist) {
        setHouseList(houselist);
        setName(name);
        setTypeArea(typeArea);
        setLocal(local);
        setSensorList(sensorList);
    }

    /**
     * Constructor of the class GeographicAreaController that receives the type of Geographic Area, its location, a
     * list of sensors contained in it, and two vertices. The two vertices define a rectangular physical
     * area that matches an approximation of the physical area covered by the Geographic Area.
     */

    public GeographicArea(TypeArea typeArea, Local local, SensorList sensorList, Local topLeftVertex, Local bottomRightVertex) {
        setTypeArea(typeArea);
        setLocal(local);
        setTopLeftVertex(topLeftVertex);
        setBottomRightVertex(bottomRightVertex);
        setSensorList(sensorList);
        mHouseList = new HouseList();
    }

    // Setters and Getters for all the parameters.

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        if (isGeographicNameValid(name)) {
            this.mName = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Setters for the Vertexes of the geographic area. For the purposes of the area they define, the area
     * is assumed to be a rectangle.
     */


    void setTopLeftVertex(Local localv1) {
        if (localv1.getLatitude() <= mLocal.getLatitude() && localv1.getLongitude() >= mLocal.getLongitude()) {
            this.mTopLeftVertex = localv1;
        }
    }

    void setBottomRightVertex(Local localv2) {
        if (localv2.getLatitude() >= mLocal.getLatitude() && localv2.getLongitude() <= mLocal.getLongitude()) {
            this.mBottomRightVertex = localv2;
        }
    }

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
    public void setLocal(Local local) {
        this.mLocal = local;
    }


    /**
     * Setter for Sensor List.
     *
     * @param listToSet
     */

    public void setSensorList(SensorList listToSet) {
        this.mSensorList = listToSet;
    }

    public void setMotherArea(GeographicArea geoArea) {
        this.mMotherArea = geoArea;
    }

    public HouseList getHouseList() {
        return mHouseList;
    }

    public void setHouseList(HouseList mHouseList) {
        this.mHouseList = mHouseList;
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
     * @return returns a Local object containing the top left Vertex.
     */
    Local getTopLeftVertex() {
        return mTopLeftVertex;
    }

    /**
     * @return returns a Local object containing the bottom right Vertex.
     */

    Local getBottomRightVertex() {
        return mBottomRightVertex;
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
        return this.mLocal;
    }

    /**
     * Getter for Geographic Area sensor list.
     *
     * @return returns the attribute sensorList from an object of the class Geographic Area
     */
    public SensorList getSensorList() {
        return this.mSensorList;
    }

    /**
     * Method to print details that are required for a Geographic Area to be different from another GA (equals -
     * name, type area and local).
     *
     * @return
     */

    public String printGeographicArea() {
        String result;
        result = this.mName + ", " + this.mTypeArea.getTypeOfGeographicArea() + ", " +
                this.mLocal.getLatitude() + "º lat, " + this.mLocal.getLongitude() + "º long\n";
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
        SensorList listToTest = this.mSensorList;
        for (int i = 0; i < listToTest.getSensorList().size(); i++) {
            if (!(listToTest.getSensorList().get(i).getTypeSensor().getName().equals(typeOfSensor))) {
                listToTest.removeSensor(listToTest.getSensorList().get(i));
            }
        }
        return listToTest.getMostRecentlyUsedSensor().getReadingList().getMostRecentReading().getmValue();
    }

    boolean isAreaContainedInAnotherArea(GeographicArea area1, GeographicArea area2) {
        double latTopVert1 = area1.getTopLeftVertex().getLatitude();
        double longTopVert1 = area1.getTopLeftVertex().getLongitude();
        double latBotVert1 = area1.getBottomRightVertex().getLatitude();
        double longBotVert1 = area1.getBottomRightVertex().getLongitude();
        double latTopVert2 = area2.getTopLeftVertex().getLatitude();
        double longTopVert2 = area2.getTopLeftVertex().getLongitude();
        double latBotVert2 = area2.getBottomRightVertex().getLatitude();
        double longBotVert2 = area2.getBottomRightVertex().getLongitude();
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
        return this.mLocal.getLinearDistanceBetweenLocalsInKm(l);
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
        if (mSensorList.getSensorList().isEmpty()) {
            return -1;
        }
        for (int i = 0; i < mSensorList.getSensorList().size(); i++) {
            Sensor sensorToGetAVG = mSensorList.getSensorList().get(i);
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
        for (Sensor s : mSensorList.getSensorList()) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Method 'equals' is required so that each 'Geographic Area' can be added to a 'Geographic Area List'. Two
     * Geographic Areas cannot have the same Localization, name and TypeArea
     *
     * @param testObject
     * @return boolean
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
        return (this.getLocal().equals(gA.getLocal()) && (this.getName().equals(gA.getName()) && (this.getTypeArea().getTypeOfGeographicArea().equals(gA.getTypeArea().getTypeOfGeographicArea()))));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
