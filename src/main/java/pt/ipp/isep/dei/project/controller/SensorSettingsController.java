package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SensorSettingsController {
    private SensorList mSensorList;
    private Local mLocal;
    private Date mDate;
    private Sensor mSensor;
    private TypeSensor mSensorType;

    public SensorSettingsController(){
        this.mSensorList = new SensorList();
    }


    //SHARED METHODS THROUGH DIFFERENT UIS

    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    public void printTypes(List<TypeSensor> typeList) {
        for(int i = 0; i < typeList.size(); i++) {
            String name = typeList.get(i).getName();
            String units = typeList.get(i).getUnits();
            System.out.println("Sensor Type: " + name + " | " + "Unit of Measurement: " + units + "\n");
        }
    }

    /**
     *
     * @return is the sensorList stored as attribute.
     */

    public SensorList getSensorList() {
        return this.mSensorList;
    }

    public void setSensorList(SensorList sensorList){
        this.mSensorList=sensorList;
    }

    /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
    one can get measurements of that type in that area */

    /**
     * Method to create a Local with given doubles.
     * Calls the original method from model.
     *
     * @param latitude is the new local's latitude.
     * @param longitude is the new local's longitude.
     * @param altitude is the new local's altitude.
     * @return is the newly made local.
     * */

    public Local createLocal(Double latitude, Double longitude, Double altitude) {
        this.mLocal = new Local(latitude, longitude, altitude);
        return this.mLocal;
    }

    /**
     *
     * @param year is the new date's year.
     * @param month is the new date's month.
     * @param day is the new date's day.
     * @return is the newly made Date.
     */

    public Date createDate(int year, int month, int day) {
        this.mDate = new GregorianCalendar(year, month, day).getTime();
        return this.mDate;
    }

    /**
     *
     * @param sensorType is the new type's name.
     * @param sensorUnits is the new type's units.
     * @return is the newly created sensorType.
     */

    public TypeSensor createType(String sensorType, String sensorUnits) {
        this.mSensorType = new TypeSensor(sensorType, sensorUnits);
        return mSensorType;
    }


    /**
     *
     * @param name is the new sensor's name.
     * @param type is the new sensor's type.
     * @param local is the new sensor's local.
     * @param date is the new sensor's date of when it started functioning.
     * @return is the newly made sensor.
     */


    public Sensor createSensor(String name, TypeSensor type, Local local, Date date) {
        this.mSensor = new Sensor(name, type, local, date);
        return mSensor;
    }

    public Sensor createRoomSensor(String name, TypeSensor type, Date date) {
        this.mSensor = new Sensor(name, type, date);
        return mSensor;
    }

    /**
     *
     * @param sensor is the sensor to add to the list.
     * @param sensorList is the list to add the sensor to.
     * @return is true if successfully added, false if not.
     */

    public boolean addSensor(Sensor sensor, SensorList sensorList) {
        if (sensorList.containsSensor(sensor)) {
            sensorList.getSensorList().add(sensor);
            return false;
        }
        return true;
    }

    public void setSensor(Sensor sensor){
        this.mSensor=sensor;
    }
    /**
     *
     * @param geoArea is the area we want to add the sensor to.
     * @return is true if successfully added, false if not.
     */

    public boolean addSensorToGeographicalArea(GeographicArea geoArea) {
        return (geoArea.addSensorToSensorList(this.mSensor));
    }

    /**
     *
     * @return is currently stored local.
     */


    public Local getLocal() {
        return this.mLocal;
    }

    /**
     *
     * @return is currently stored type.
     */

    public TypeSensor getType() {
        return this.mSensorType;
    }

    /**
     *
     * @return is currently stored date of start of functioning.
     */

    public Date getDate() {
        return this.mDate;
    }

    /**
     *
     * @return is currently stored sensor.
     */

    public Sensor getSensor() {
        return this.mSensor;
    }

}