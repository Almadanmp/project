package pt.ipp.isep.dei.project.model;

import java.util.Date;

/**
 * Represents a Sensor.
 * It is defined by a name, type of sensor, localization and the date it started functioning.
 * It contains a list with one or more weather readings.
 */

public class Sensor {
    private String name;
    private TypeSensor typeSensor;
    private Local local;
    private Date dateStartedFunctioning;
    private ReadingList readingList;

    /**
     * Constructor with:name, typesensor, local, dateStartedFunctioning
     *
     * @param name
     * @param typeSensor
     * @param local
     * @param dateStartedFunctioning
     */
    public Sensor(String name, TypeSensor typeSensor, Local local, Date dateStartedFunctioning) {
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(dateStartedFunctioning);
        readingList = new ReadingList();
    }

    /**
     * Constructor with:name, typesensor, DateStartedFunctioning (Used When A Sensor is in a Room already. So the Location is the same as the room/house).
     *
     * @param name
     * @param typeSensor
     * @param dateStartedFunctioning
     */
    public Sensor(String name, TypeSensor typeSensor, Date dateStartedFunctioning) {
        setName(name);
        setTypeSensor(typeSensor);
        setDateStartedFunctioning(dateStartedFunctioning);
        readingList = new ReadingList();
    }

    /**
     * Setter: name
     *
     * @param name
     */
    public void setName(String name) {
        if (isSensorNameValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Setter: local
     *
     * @param local
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Setter: type sensor
     *
     * @param sensor
     */
    void setTypeSensor(TypeSensor sensor) {
        this.typeSensor = sensor;
    }

    /**
     * Setter: date started functioning
     *
     * @param dateStartedFunctioning
     */
    void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    /**
     * Getter: name
     *
     * @return
     */
    public String getName() {
        return (this.name);
    }

    /**
     * Getter: type sensor
     *
     * @return
     */
    public TypeSensor getTypeSensor() {
        return (this.typeSensor);
    }

    /**
     * Getter: local
     *
     * @return
     */
    public Local getLocal() {
        return (this.local);
    }

    /**
     * Getter: date started functioning
     *
     * @return
     */
    Date getDateStartedFunctioning() {
        return (this.dateStartedFunctioning);
    }

    /**
     * Getter: reading list
     *
     * @return
     */
    public ReadingList getReadingList() {
        return readingList;
    }

    /**
     * Setter: reading list
     *
     * @param readingList
     */
    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }


    /**
     * Checks if reading already exists in reading list and in case the
     * reading is new, adds it to the reading list.
     * @return true in case the reading is new and it is added
     * or false in case the reading already exists**/
    public boolean addReading(Reading reading){
        for(Reading r : this.readingList.getListOfReadings()){
            if(r.equals(reading)){
                return false;
            }
        }
        this.readingList.addReading(reading);
        return true;
    }

    /**
     * Method to calculate distance to sensor from a sensor to a local
     *
     * @param s1 - sensor
     * @return the distance from a local to sensor in km (doubles)
     */
    double calculateDistanceToSensor(Sensor s1) {
        Local l = s1.getLocal();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to determine the month average of a sensor reading (e.g. temperature, wind...)
     *
     * @param s1 - sensor
     * @return average of readings on a month on a sensor
     */
    double calculateMonthMeanOnSensor(Sensor s1, Date dateGiven) {
        return s1.getReadingList().getAverageReadingsFromGivenMonth(dateGiven);
    }

    /**
     * Method to restrain input name so they cant be null or empty.
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    private boolean isSensorNameValid(String name) {
        return (name != null && !name.isEmpty());
    }



    boolean isSensorContainedInArea(GeographicArea area) {
        double latS = this.getLocal().getLatitude();
        double longS = this.getLocal().getLongitude();
        double latTopVert = area.getLocal().getLatitude() + (area.getWidth() / 2);
        double longTopVert = area.getLocal().getLongitude() - (area.getLength() / 2);
        double latBotVert = area.getLocal().getLatitude() - (area.getWidth() / 2);
        double longBotVert = area.getLocal().getLongitude() + (area.getLength() / 2);
        return (latS <= latTopVert && latS >= latBotVert && longS <= longBotVert && longS >= longTopVert);
    }

    boolean isSensorActiveOnGivenDate(Date date1) {
        return this.getDateStartedFunctioning().before(date1);
    }


    double getDistanceToHouse(House house) {
        Local l = house.getLocation();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to print details that are required for a Sensor to be different from another Sensor (equals -
     * name, type area and local).
     *
     * @return returns a string with Sensor Parameters
     */
    public String buildSensorString() {
        String result;
        result = this.name + ", " + this.typeSensor.getName() + ", " +
                this.local.getLatitude() + "º lat, " + this.local.getLongitude() + "º long\n";
        return result;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) testObject;
        return this.getName().equals(sensor.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
