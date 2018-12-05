package Sprint_0;

/**
 * This is the central class.
 */

public class GeographicArea {
    private TypeArea mTypeArea;
    private Local mLocal;
    private SensorList mSensorList;
    private Local mTopLeftVertex;
    private Local mBottomRightVertex;


    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /**
     * Constructor of the class GeographicArea that receives the type of Geographic Area and its localization
     * as parameters.
     *
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     * @param local    Localization is defined by longitude, latitude and altitude.
     */
    public GeographicArea(TypeArea typeArea, Local local) {
        setTypeArea(typeArea);
        setLocal(local);
    }

    /**
     * Constructor of the class GeographicArea that receives the type of Geographic Area, its location
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
    }

    /**
     * Constructor of the class GeographicArea that receives the type of Geographic Area, its location, a
     * list of sensors contained in it, and two vertices. The two vertices define a rectangular physical
     * area that matches an approximation of the physical area covered by the Geographic Area.
     */

    public GeographicArea(TypeArea typeArea, Local local, SensorList sensorList, Local topLeftVertex, Local bottomRightVertex) {
        setTypeArea(typeArea);
        setLocal(local);
        setTopLeftVertex(topLeftVertex);
        setBottomRightVertex(bottomRightVertex);
        setSensorList(sensorList);
    }

    // Setters and Getters for all the parameters.

    /**
     * Setters for the Vertexes of the geographic area. For the purposes of the area they define, the area
     * is assumed to be a rectangle.
     */

    public void setTopLeftVertex(Local local) {
        this.mTopLeftVertex = local;
    }

    public void setBottomRightVertex(Local local) {
        this.mBottomRightVertex = local;
    }

    /**
     * Setter for Geographic Area type.
     *
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     */
    public void setTypeArea(TypeArea typeArea) {
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

    /**
     * @return returns a Local object containing the top left Vertex.
     */
    public Local getTopLeftVertex() {
        return mTopLeftVertex;
    }

    /**
     * @return returns a Local object containing the bottom right Vertex.
     */

    public Local getBottomRightVertex() {
        return mBottomRightVertex;
    }

    /**
     * Getter for type of Geographic Area.
     *
     * @return returns the attribute TypeArea from an object of the class Geographic Area
     */
    public TypeArea getTypeArea() {
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
     * Method will go through Geographic Area's sensor list, create a second list with the type
     * of sensors defined by the parameter and finally return the most recent value recorded in that list.
     *
     * @param typeOfSensor Type sensor is determined by a string - e.g. "Temperature", "Rain", etc.
     * @return returns a double of the most recent value recorded in every type sensor given
     */
    public double getMostRecentReadingValue(String typeOfSensor) {
        SensorList listToTest = this.mSensorList;
        for (int i = 0; i < listToTest.getSensorList().size(); i++) {
            if (!(listToTest.getSensorList().get(i).getTypeSensor().getName().equals(typeOfSensor))) {
                listToTest.removeSensor(listToTest.getSensorList().get(i));
            }
        }
        return listToTest.getMostRecentlyUsedSensor().getReadingList().getMostRecentReading().getmValue();
    }

    /**
     * Method will calculate the distance between two different Geographic Areas.
     *
     * @param ga object of the class GeographicArea
     * @return returns a double of the distance between Geographic Areas.
     */
    public double calculateDistanceToGA(GeographicArea ga) {
        Local l = ga.getLocal();
        return this.mLocal.getLinearDistanceBetweenLocalsInKm(l);
    }

}
