package Sprint0.Controller;

import Sprint0.Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class US06Controller {

    private Sensor mSensor;
    private String mSensorName;
    private TypeSensor mSensorTypeSensor;
    private Local mSensorLocal;
    private Date mSensorDateStartedFunctioning;
    private ReadingList mSensorReadingList;
    private List<Sensor> mSensorList;
    private GeographicArea GA;

    public US06Controller(Sensor sensor) {
        this.mSensor = sensor;
    }

    public boolean addSensor(Sensor sensorToAdd){
        this.mSensor = sensorToAdd;
        mSensorList = new ArrayList<>();
        if (!(mSensorList.contains(sensorToAdd))) {
            mSensorList.add(sensorToAdd);
        }
        return false;
    }
    public void addSensorToGeographicArea(Sensor sensorToAdd) {

    }
    public void setType(Sensor sensor,String name){
        TypeSensor type = new TypeSensor();
        type.setName(name);
    }

}

