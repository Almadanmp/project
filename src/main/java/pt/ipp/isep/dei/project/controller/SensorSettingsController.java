package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;

public class SensorSettingsController {
    private SensorList mSensorList;
    private Local mLocal;
    private Date mDate;
    private Sensor mSensor;
    private TypeSensor mSensorType;
    private GeographicArea mGeoArea;


    public SensorSettingsController(){
        this.mSensorList = new SensorList();
    }
    //SHARED METHODS THROUGH DIFFERENT UIS


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    public boolean setTypeSensor(SensorList sensorList, String name, String typeToSet) {
        return sensorList.setTypeSensorByString(name, typeToSet);
    }

    public SensorList getSensorList() {
        return this.mSensorList;
    }


       /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
     one can get measurements of that type in that area */

    /**
     * Method to create a Local with given doubles
     * Calls the original method from model
     *
     * @param latitude Latitude
     * @param latitude Longitude
     * @param latitude Altitude
     * @return Local created
     */
    public Local createLocal(Double latitude, Double longitude, Double altitude) {
        this.mLocal = new Local(latitude, longitude, altitude);
        return this.mLocal;
    }

    public Date createDate(int year, int month, int day) {
        this.mDate = new GregorianCalendar(year, month, day).getTime();
        return this.mDate;
    }

    public TypeSensor createType(String sensorType, String sensorUnits) {
        this.mSensorType = new TypeSensor(sensorType, sensorUnits);
        return mSensorType;
    }


    public Sensor createSensor(String name, TypeSensor type, Local local, Date date) {
        this.mSensor = new Sensor(name, type, local, date);
        return mSensor;
    }

    /**
     * Method to add a Sensor to a SensorList
     * Calls the original method from model
     */
    public boolean addSensor(Sensor sensor, SensorList sensorList) {
        if (sensorList.containsSensor(sensor)) {
            sensorList.getSensorList().add(sensor);
            return false;
        }
        return true;
    }

    public boolean addSensorToGeographicalArea(GeographicArea geoArea) {
        return (geoArea.addSensorToSensorList(this.mSensor));
    }


    public GeographicArea getGeoAreaFromName(String geoName, GeographicAreaList galist) {
        for (GeographicArea ga : galist.getGeographicAreaList()) {
            if (ga.getId().equals(geoName))
                mGeoArea = ga;
        }
        return mGeoArea;
    }

    public Local getLocal() {
        return this.mLocal;
    }

    public TypeSensor getType() {
        return this.mSensorType;
    }

    public Date getDate() {
        return this.mDate;
    }

    public Sensor getSensor() {
        return this.mSensor;
    }

}