package pt.ipp.isep.dei.project.controller;

import org.springframework.stereotype.Controller;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.util.Date;

/**
 * Controller class for Sensor Settings UI
 */
@Controller
public class SensorSettingsController {


    /* USER STORY 005 - As an Administrator, I want to define the sensor types. */

    public String buildSensorTypesString(SensorTypeRepository sensorTypeList) {
        return sensorTypeList.buildString();
    }

    /**
     * This method receives a list and a type sensor and tries to addWithoutPersisting the type sensor
     * to the list. The type sensor will not be added in case the list already contains it.
     *
     * @param sensorType the type of sensor to be added
     *                   // * @param typeSensorList the list of types of sensors
     * @return true if the type of sensor was added to the list of type sensors.
     */
    public boolean addTypeSensorToList(SensorType sensorType, SensorTypeRepository sensorTypeList) {
        return sensorTypeList.add(sensorType);
    }

    /* USER STORY 006 - an Administrator, I want to addWithoutPersisting a new sensor and associate it to a geographical area, so that
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
        return new Local(latitude, longitude, altitude);
    }


    /**
     * @param sensorType  is the new type's name.
     * @param sensorUnits is the new type's units.
     * @return is the newly created sensortype.
     */

    public SensorType createType(SensorTypeRepository sensorTypeList, String sensorType, String sensorUnits) {
        return sensorTypeList.createTypeSensor(sensorType, sensorUnits);
    }


    /**
     * @param id    is the new sensor's ID.
     * @param name  is the new sensor's name.
     * @param type  is the new sensor's type.
     * @param local is the new sensor's local.
     * @param date  is the new sensor's date of when it started functioning.
     * @return is the newly made sensor.
     */


    public AreaSensor createSensor(String id, String name, SensorType type, Local local, Date date, Long geographicAreaID) {
        return new AreaSensor(id, name, type, local, date, geographicAreaID);
    }

    /**
     * This method receives a sensor and returns a string with the sensor's parameters
     *
     * @param areaSensor the chosen sensor.
     * @return String with sensor parameters
     **/
    public String buildSensorString(AreaSensor areaSensor) {
        return areaSensor.buildString();
    }

    /**
     * @param areaSensor the sensor we want to add to the geographic area.
     * @return is true if successfully added, false if not.
     */

    public boolean addSensorToGeographicArea(AreaSensor areaSensor, GeographicArea geographicArea) {
        return (geographicArea.addSensor(areaSensor));
    }


}