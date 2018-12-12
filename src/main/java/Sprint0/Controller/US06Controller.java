package Sprint0.Controller;

import Sprint0.Model.*;
import Sprint0.UI.MainUI;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User Story 06
 * As a system administrator, I want to be able to manually input a new sensor and add it to a pre-input Geographic Area
 */

public class US06Controller {

    private Local mLocal;
    private Date mDate;
    private TypeSensor mType;
    private Sensor mSensor;


    public US06Controller() {
    }

    public Local createLocal(Double latitude, Double longitude, Double altitude) {
        Local local = new Local(latitude, longitude, altitude);
        this.mLocal = local;
        return this.mLocal;
    }
    public Date createDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        this.mDate = date;
        return this.mDate;
    }
    public TypeSensor createType(String sensorType){
        this.mType = new TypeSensor(sensorType);
        return this.mType;
    }
    public Sensor createSensor (String name, TypeSensor type, Local local, Date date){
        this.mSensor = new Sensor(name,type,local,date);
        return mSensor;
    }
    public boolean addSensor(Sensor sensor, SensorList sensorList){
        checkIfListValid(sensorList.getSensorList());
        if(!(sensorList.getSensorList().contains(sensor))) {
            sensorList.getSensorList().add(sensor);
            return true;
        }
        return false;
    }
    private boolean checkIfListValid(List<Sensor> values) {
        if (values == null || values.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean addSensorToGeographicArea(String name, GeographicAreaList gaList, SensorList sensorList) {
        for(GeographicArea ga : gaList.getGeographicAreaList()) {
            if ((ga.getName().equals(name))) {
                ga.setSensorList(sensorList);
                return true;
            }
        }
    return false;
    }

    public Local getLocal() {
        return this.mLocal;
    }
    public TypeSensor getType() {
        return this.mType;
    }
    public Date getDate() {
        return this.mDate;
    }
    public Sensor getSensor() {
        return this.mSensor;
    }
}
