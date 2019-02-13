package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Controller class for Sensor Settings UI
 */

public class SensorSettingsController {
    private SensorList mSensorList;
    private Local mLocal;
    private Date mDate;
    private Sensor mSensor;
    private TypeSensor mSensorType;

    public SensorSettingsController() {
        this.mSensorList = new SensorList();
    }


    //SHARED METHODS THROUGH DIFFERENT UIS

    /**
     * @param geoAreaList is the list of Geographic Areas we want to print.
     * @return builds a string with each individual member of the given list.
     */

    String buildGAListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildGaWholeListString(geoAreaList);
    }


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    public String buildSensorTypesString(TypeSensorList typeSensorList) {
        return typeSensorList.buildString();
    }

    /** This method receives a list and a type sensor and tries to add the type sensor
     * to the list. The type sensor will not be added in case the list already contains it.
     * @return true in case the type sensor is added to the list, false otherwise
     */
    public boolean addTypeSensorToList(TypeSensor typeSensor, TypeSensorList typeSensorList){
        return typeSensorList.add(typeSensor);
    }

    /* USER STORY 006 - an Administrator, I want to add a new sensor and associate it to a geographical area, so that
    one can get measurements of that type in that area */

    /**
     * Method to create a Local with given doubles.
     * Calls the original method from model.
     *
     * @param latitude  is the new local's latitude.
     * @param longitude is the new local's longitude.
     * @param altitude  is the new local's altitude.
     * @return is the newly made local.
     */

    public Local createLocal(Double latitude, Double longitude, Double altitude) {
        this.mLocal = new Local(latitude, longitude, altitude);
        return this.mLocal;
    }

    /**
     * @param year  is the new date's year.
     * @param month is the new date's month.
     * @param day   is the new date's day.
     * @return is the newly made Date.
     */

    public Date createDate(int year, int month, int day) {
        this.mDate = new GregorianCalendar(year, month, day).getTime();
        return this.mDate;
    }

    /**
     * @param sensorType  is the new type's name.
     * @param sensorUnits is the new type's units.
     * @return is the newly created sensorType.
     */

    public TypeSensor createType(String sensorType, String sensorUnits) {
        this.mSensorType = new TypeSensor(sensorType, sensorUnits);
        return mSensorType;
    }


    /**
     * @param name  is the new sensor's name.
     * @param type  is the new sensor's type.
     * @param local is the new sensor's local.
     * @param date  is the new sensor's date of when it started functioning.
     * @return is the newly made sensor.
     */


    public Sensor createSensor(String name, TypeSensor type, Local local, Date date) {
        return new Sensor(name, type, local, date);
    }

    /**This method receives a sensor and returns a string with the sensor's parameters
     * @return String with sensor parameters**/
    public String buildSensorString(Sensor sensor){
        return sensor.buildSensorString();
    }

    public Sensor createRoomSensor(String name, TypeSensor type, Date date) {
        return new Sensor(name, type, date);
    }

    /**
     * @param geoArea is the area we want to add the sensor to.
     * @return is true if successfully added, false if not.
     */

    public boolean addSensorToGeographicalArea(GeographicArea geoArea) {
        return (geoArea.addSensorToSensorList(this.mSensor));
    }


}