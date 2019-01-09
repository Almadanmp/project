package pt.ipp.isep.dei.project.model;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents a Sensor.
 * It is defined by a name, type of sensor, localization and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
public class Sensor {
    private String mName;
    private TypeSensor mTypeSensor;
    private Local mLocal;
    private Date mDateStartedFunctioning;
    private ReadingList mReadingList;


    /**
     * Constructor with:name, typesensor, local, date
     *
     * @param name
     * @param typeSensor
     * @param local
     * @param date
     */
    public Sensor(String name, TypeSensor typeSensor, Local local, Date date) {
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(date);
    }

    /**
     * Constructor with name, type sensor, local, date, reading list
     *
     * @param name
     * @param typeSensor
     * @param local
     * @param date
     * @param readingList
     */
    public Sensor(String name, TypeSensor typeSensor, Local local, Date date, ReadingList readingList) {
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(date);
        setReadingList(readingList);
    }

    /**
     * Setter: name
     *
     * @param name
     */
    public void setName(String name) {
        if (isSensorNameValid(name)) {
            this.mName = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    public String printSensor(){
        String result;
        result = this.mName + ", " + this.mTypeSensor.getName() + ", " +
                this.mLocal.getLatitude() + "º lat, " + this.mLocal.getLongitude() + "º long\n";
        return result;
    }
    /**
     * Setter: local
     *
     * @param local
     */
    public void setLocal(Local local) {
        this.mLocal = local;
    }

    /**
     * Setter: type sensor
     *
     * @param sensor
     */
    public void setTypeSensor(TypeSensor sensor) {
        this.mTypeSensor = sensor;
    }

    /**
     * Setter: date started functioning
     *
     * @param dateStartedFunctioning
     */
    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.mDateStartedFunctioning = dateStartedFunctioning;
    }

    /**
     * Getter: name
     *
     * @return
     */
    public String getName() {
        return (this.mName);
    }

    /**
     * Getter: type sensor
     *
     * @return
     */
    public TypeSensor getTypeSensor() {
        return (this.mTypeSensor);
    }

    /**
     * Getter: local
     *
     * @return
     */
    public Local getLocal() {
        return (this.mLocal);
    }

    /**
     * Getter: date started functioning
     *
     * @return
     */
    public Date getDateStartedFunctioning() {
        return (this.mDateStartedFunctioning);
    }

    /**
     * Getter: reading list
     *
     * @return
     */
    public ReadingList getReadingList() {
        return mReadingList;
    }

    /**
     * Setter: reading list
     *
     * @param readingList
     */
    public void setReadingList(ReadingList readingList) {
        this.mReadingList = readingList;
    }

    /**
     * Method to calculate distance to sensor from a sensor to a local
     *
     * @param s1 - sensor
     * @return the distance from a local to sensor in km (doubles)
     */
    public double calculateDistanceToSensor(Sensor s1) {
        Local l = s1.getLocal();
        return this.mLocal.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to determine the month average of a sensor reading (e.g. temperature, wind...)
     *
     * @param s1 - sensor
     * @return average of readings on a month on a sensor
     */
    public double calculateMonthMeanOnSensor(Sensor s1, Date dateGiven) {
        return s1.getReadingList().getAverageOfAllRecordedValueReadingsFromGivenMonth(dateGiven);
    }


    /**
     * Method to restrain input name so they cant be null or empty.
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    public boolean isSensorNameValid(String name) {
        return (name != null && !name.isEmpty());
    }

    public boolean isSensorContainedInArea(GeographicArea area) {
        double latS = this.getLocal().getLatitude();
        double longS = this.getLocal().getLongitude();
        double latTopVert = area.getLocal().getLatitude() + (area.getWidth()/2);
        double longTopVert = area.getLocal().getLongitude() - (area.getLength()/2);
        double latBotVert = area.getLocal().getLatitude() - (area.getWidth()/2);
        double longBotVert = area.getLocal().getLongitude() + (area.getLength()/2);
        return (latS <= latTopVert && latS >= latBotVert && longS <= longBotVert && longS >= longTopVert);
    }

    public boolean isSensorActiveOnGivenDate(GregorianCalendar date1) {
        return this.getDateStartedFunctioning().before(date1.getTime());
    }

    public double getDistanceToHouse(House house){
        Local l = house.getmGPS();
        return this.mLocal.getLinearDistanceBetweenLocalsInKm(l);
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
