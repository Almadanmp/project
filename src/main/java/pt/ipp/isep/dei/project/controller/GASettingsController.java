package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.LocalMapper;
import pt.ipp.isep.dei.project.dto.mappers.SensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.TypeAreaMapper;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;

/**
 * Controller class for Geographical Area Settings UI
 */
public class GASettingsController {


    //GEOGRAPHIC AREA SETTINGS CONTROLLER  - SHARED METHODS//

    public GASettingsController() {
        //empty constructor
    }

    /**
     * @param geoAreaList is the list of Geographic Areas we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGAListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildString();
    }

    /**
     * @param typeAreaList is the list of Geographic Area Types we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGATypeListString(TypeAreaList typeAreaList) {
        return typeAreaList.buildString();
    }


    /*
     * User Story 01
     * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
     */

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list or false if the name is
     * null.
     */
    public boolean createAndAddTypeAreaToList(TypeAreaList typeAreaList, String input) {
        TypeArea typeArea = typeAreaList.createTypeArea(input);
        return typeAreaList.addTypeArea(typeArea);
    }

    /* User Story 02
     As a System Administrator I want to receive a list of all the previously stated Types of area.
     */
    public String getTypeAreaList(TypeAreaList typeAreaList) {
        return typeAreaList.buildString();
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method to addWithoutPersisting a new geographic area to a list of geographic areas
     *
     * @param newGeoList geographic area list to addWithoutPersisting the new geographic area
     * @param localDTO   the latitude, longitude and altitude of the GA
     * @return success if a new GA is added, false otherwise
     */
    public boolean addNewGeoAreaToList(GeographicAreaList newGeoList, GeographicAreaDTO geoAreaDTO, LocalDTO localDTO) {
        GeographicArea geoToAdd = newGeoList.createGA(geoAreaDTO.getName(), new TypeArea(geoAreaDTO.getTypeArea()),
                geoAreaDTO.getLength(), geoAreaDTO.getLength(), LocalMapper.dtoToObject(localDTO));
        if ((newGeoList.containsObjectMatchesParameters(geoAreaDTO.getName(), new TypeArea(geoAreaDTO.getTypeArea()),
                LocalMapper.dtoToObject(localDTO)))) {
            newGeoList.removeGeographicArea(geoToAdd);
            return newGeoList.addGeographicArea(geoToAdd);
        } else {
           return newGeoList.addGeographicArea(geoToAdd);
        }
    }

    /**
     * Method to create a DTO of Geographic Area
     *
     * @param newName     name of the Geographic Area
     * @param typeAreaDTO Type area of the Geographic Area
     * @param localDTO    Localization of the Geographic Area
     * @param length      Length of the Geographic Area
     * @param width       width of the Geographic Area
     * @return Geographic Area DTO
     */
    public GeographicAreaDTO createGeoAreaDTO(String newName, TypeAreaDTO typeAreaDTO, LocalDTO localDTO, double length, double width) {
        GeographicArea geoArea = new GeographicArea(newName, TypeAreaMapper.dtoToObject(typeAreaDTO), length, width,
                LocalMapper.dtoToObject(localDTO));
        return GeographicAreaMapper.objectToDTO(geoArea);
    }

    /**
     * Method to create a DTO Localization
     *
     * @param latitude  value for latitude
     * @param longitude value for longitude
     * @param altitude  value for altitude
     * @return returns a Local DTO
     */
    public LocalDTO createLocalDTO(double latitude, double longitude, double altitude) {
        Local local = new Local(latitude, longitude, altitude);
        return LocalMapper.objectToDTO(local);
    }

    /* USER STORY 04 -  As an Administrator, I want to get a list of existing geographical areas of a given type. */

    /**
     * @param geographicAreaList is the Geographic Area List where we want to search for objects with a given type.
     * @param typeArea           is the type that we want to look for.
     * @return is a list of all the objects in the original list with a type that matches the given type.
     */
    public GeographicAreaList matchGAByTypeArea(GeographicAreaList geographicAreaList, TypeAreaDTO typeArea) {
        String typeAreaName = typeArea.getName();
        return geographicAreaList.getGeoAreasByType(typeAreaName);
    }

    /**
     * @param typeAreaDTO is the Type of Area we want to get the name of.
     * @return is the name of the given type of area.
     */
    public String getTypeAreaName(TypeAreaDTO typeAreaDTO) {
        return typeAreaDTO.getName();
    }

    /*USER STORY 07 - As an Administrator, I want to addWithoutPersisting an existing geographical area to another one (e.g. addWithoutPersisting city of
    Porto to the district of Porto). */


    /**
     * Method that gets a Geographic Area and returns its Id.
     *
     * @param geographicArea that method will use
     * @return geographic area id as a string
     */
    public String getGeographicAreaId(GeographicArea geographicArea) {
        return geographicArea.getName();
    }

    /**
     * @param daughterArea is the area that is contained in another.
     * @param motherArea   is the area that contains another.
     * @return true if the area was successfully added.
     */
    public boolean setMotherArea(GeographicArea daughterArea, GeographicArea motherArea) {
        return daughterArea.setMotherArea(motherArea);
    }

    /*USER STORY 08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */

    /**
     * @param motherGA   the geographic area that contains the daughter geographic area.
     * @param daughterGA the geographic area that is contained in the mother geographic area.
     * @return returns true if one area is contained in the other, false if it isn't.
     */

    public boolean isAreaContained(GeographicArea motherGA, GeographicArea daughterGA) {
        return daughterGA.isContainedInArea(motherGA);
    }

    /**
     * Deactivates a sensor from a sensor list
     *
     * @param geographicAreaList the geographic area list that contains the geographic area with sensors
     * @param sensorDTO          selected sensor from the geographic area, list of sensors
     * @param geographicAreaDTO  selected geographicAreaDTO from the geographic area list
     * @return returns true if the selected sensor is deactivated, if it's already deactivated returns false
     */
    public boolean deactivateSensor(GeographicAreaList geographicAreaList, SensorDTO sensorDTO, GeographicAreaDTO geographicAreaDTO) {
        Sensor sensor = SensorMapper.dtoToObject(sensorDTO);
        if (sensor.isActive()) {
            sensor.deactivateSensor();
            for (GeographicArea g : geographicAreaList.getElementsAsArray()) {
                if (g.getName().equals(geographicAreaDTO.getName())) {
                    g.removeSensor(sensor);
                    g.addSensor(sensor);
                }
            }
            return true;
        }
        return false;
    }

    /* USER STORY 11 */

    public GeographicAreaDTO inputArea(GeographicAreaList geographicAreaList) {
        GeographicArea geographicArea = InputHelperUI.getGeographicAreaByList(geographicAreaList);
        return GeographicAreaMapper.objectToDTO(geographicArea);
    }

    public SensorDTO inputSensor(GeographicAreaDTO geographicAreaDTO) {
        GeographicArea geographicArea = GeographicAreaMapper.dtoToObject(geographicAreaDTO);
        Sensor sensor = InputHelperUI.getInputSensorByList(geographicArea.getSensorList());
        return SensorMapper.objectToDTO(sensor);
    }

    public void removeSensor(GeographicAreaList geographicAreaList, SensorDTO sensorDTO, GeographicAreaDTO geographicAreaDTO) {
        Sensor sensor = SensorMapper.dtoToObject(sensorDTO);
        for (GeographicArea g : geographicAreaList.getElementsAsArray()) {
            if (g.getName().equals(geographicAreaDTO.getName())) {
                g.removeSensor(sensor);
            }
        }
    }

}
