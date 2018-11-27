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

    public Sensor(String name, TypeSensor typeSensor, Local local, Date date) {
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(date);
    }

    /**
     * Getters and Setters
     */
    public void setName(String name) {
        if (AuxiliaryMethods.isNameValid(name)) {
            this.name = name;
        }
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setTypeSensor(TypeSensor sensor) {
        this.typeSensor = sensor;
    }

    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    public String getName() {
        String result = this.name;
        return result;
    }

    public TypeSensor getTypeSensor() {
        TypeSensor result = this.typeSensor;
        return result;
    }

    public Local getLocal() {
        Local result = this.local;
        return result;
    }

    public Date getDateStartedFunctioning() {
        Date result = this.dateStartedFunctioning;
        return result;
    }

    public ReadingList getReadingList() {
        return readingList;
    }

    public void setReadingList(ReadingList readingList) {
        this.readingList = readingList;
    }

    /**
     * Specific Methods
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
    public int hashCode(){
        return 1;
    }
}
