package Sprint_0;

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
     * Constructor with:name, typesensor, local, date
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
     *Constructor with name, type sensor, local, date, reading list
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
     * @param name
     */
    public void setName(String name) {
        if (AuxiliaryMethods.isNameValid(name)) {
            this.name = name;
        }
    }

    /**
     * Setter: local
     * @param local
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Setter: type sensor
     * @param sensor
     */
    public void setTypeSensor(TypeSensor sensor) {
        this.typeSensor = sensor;
    }

    /**
     * Setter: date started functioning
     * @param dateStartedFunctioning
     */
    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    /**
     * Getter: name
     * @return
     */
    public String getName() {
        String result = this.name;
        return result;
    }

    /**
     * Getter: type sensor
     * @return
     */
    public TypeSensor getTypeSensor() {
        TypeSensor result = this.typeSensor;
        return result;
    }

    /**
     * Getter: local
     * @return
     */
    public Local getLocal() {
        Local result = this.local;
        return result;
    }

    /**
     * Getter: date started functioning
     * @return
     */
    public Date getDateStartedFunctioning() {
        Date result = this.dateStartedFunctioning;
        return result;
    }

    /**
     * Getter: reading list
     * @return
     */
    public ReadingList getReadingList() {
        return readingList;
    }

    /**
     * Setter: reading list
     * @param readingList
     */
    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }

    /**
     * Method to calculate distance to sensor from a sensor to a local
     * @param s1 - sensor
     * @return the distance from a local to sensor in km (doubles)
     */
    public double calculateDistanceToSensor(Sensor s1) {
        Local l = s1.getLocal();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Specific Methods.
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) testObject;
        if (this.getName().equals(sensor.getName())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
