package Sprint_0;

/**
 * This is the central class.
 */

public class Geographic_Area {
    private TypeArea typeArea;
    private Local local;
    private SensorList sensorList;


    // GeoArea constructors. The minimum amount of data for a GeoArea is a place and a type of area.
    // They can be made with or without a sensor list.

    /** Constructor of the class Geographic_Area that receives the type of Geographic Area and its localization
     * as parameters.
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     * @param local    Localization is defined by longitude, latitude and altitude.
     */
    public Geographic_Area(TypeArea typeArea, Local local) {
        this.setTypeArea(typeArea);
        this.setLocal(local);
    }

    /** Constructor of the class Geographic_Area that receives the type of Geographic Area, its localization
     * and a list of sensors as parameters.
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     * @param local    Localization is defined by three doubles (longitude, latitude and altitude).
     * @param sensorList Defined by a List<Sensor>.
     */
    public Geographic_Area(TypeArea typeArea, Local local, SensorList sensorList) {
        this.setTypeArea(typeArea);
        this.setLocal(local);
        this.setSensorList(sensorList);
    }

    // Setters and Getters for all the parameters.
    /** Setter for Geographic Area type.
     * @param typeArea Type area is determined by a string - e.g. "Street", "City", etc.
     */
    public void setTypeArea(TypeArea typeArea) {
        this.typeArea = typeArea;
    }

    /** Setter for Geographic Area localization.
     * @param local Localization is defined by three doubles (longitude, latitude and altitude).
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /** Setter for Geographic Area List of Sensors.  The method determines that the parameter listToSet will
     * not be given to Geographic Area as a parameter if there are duplicated sensors inside it.
     * @param listToSet Object of SensorList class that is defined by a List<Sensor>.
     * @return Returns true if the list was added. Returns false otherwise.
     */
    public boolean setSensorList(SensorList listToSet) {
        for (int i = 0; i < listToSet.getSensorlist().size(); i++) {
            for (int j = i + 1; j < listToSet.getSensorlist().size(); j++) {
                if (listToSet.getSensorlist().get(i).equals(listToSet.getSensorlist().get(j))) {
                    return false;
                }
            }
        }
        this.sensorList = listToSet;
        return true;
    }
    /** Getter for type of Geographic Area.
     * @return  returns the attribute TypeArea from an object of the class Geographic Area
     */
    public TypeArea getTypeArea() {
        return this.typeArea;
    }

    /** Getter for Geographic Area localization.
     * @return  returns the attribute local from an object of the class Geographic Area
     */
    public Local getLocal() {
        return this.local;
    }

    /** Getter for Geographic Area sensor list.
     * @return  returns the attribute sensorList from an object of the class Geographic Area
     */
    public SensorList getSensorList() {
        return this.sensorList;
    }

    /** Method will go through Geographic Area's sensor list, create a second list with the type
     * of sensors defined by the parameter and finally return the most recent value recorded in that list.
     * @param typeOfSensor Type sensor is determined by a string - e.g. "Temperature", "Rain", etc.
     * @return  returns a double of the most recent value recorded in every type sensor given
     */
    public double getMostRecentReadingValue(String typeOfSensor){
        SensorList listToTest = this.sensorList;
        for(int i = 0; i < listToTest.getSensorlist().size(); i++){
            if(!(listToTest.getSensorlist().get(i).getTypeSensor().getName().equals(typeOfSensor))){
                listToTest.removeSensor(listToTest.getSensorlist().get(i));
            }
        }
        return listToTest.getMostRecentlyUsedSensor().getReadingList().getMostRecentReading().getmValue();
    }

    /** Method will calculate the distance between two different Geographic Areas.
     * @param ga  object of the class Geographic_Area
     * @return  returns a double of the distance between Geographic Areas.
     */
    public double calculateDistanceToGA(Geographic_Area ga){
        Local l = ga.getLocal();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }
}
