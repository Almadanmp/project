package Sprint_0;

import java.util.Date;

public class Sensor {
    private String name;
    private TypeSensor typeSensor;
    private Local local;
    private Date dateStartedFunctioning;

    public Sensor (String name, TypeSensor typeSensor, Local local, Date date){
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(date);
    }

    public void setName(String name) {
        this.name = name;
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
}
