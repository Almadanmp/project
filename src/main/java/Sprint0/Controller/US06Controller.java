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
    private SensorList mSensorList;
    private GeographicArea mGeoArea;

    public US06Controller(Sensor sensor, GeographicArea gArea) {
        this.mSensor = sensor;
        this.mGeoArea = gArea;
    }

    public boolean addSensor(Sensor sensorToAdd){
        this.mSensor = sensorToAdd;
        mSensorList = new SensorList(mSensor);
        if (!(mSensorList.containsSensor(mSensor))) {
            mSensorList.addSensor(mSensor);
            mGeoArea.setSensorList(mSensorList);
        }
        return false;
    }
    public void setType(Sensor sensor,String name){
        TypeSensor type = new TypeSensor();
        type.setName(name);
    }

}

