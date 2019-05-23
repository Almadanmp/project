package pt.ipp.isep.dei.project.controller.controllercli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.dto.mappers.AreaSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.LocalMapper;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.List;

/**
 * Controller class for Geographical Area Settings UI
 */
@Service
public class GASettingsController {
    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;

    /**
     * @return builds a string with each individual member of the given list.
     */

    public String buildGAListString(List<GeographicArea> geoAreas) {
        return geographicAreaRepository.buildStringRepository(geoAreas);
    }

    /**
     * @return builds a string with each individual member of the given list.
     */

    public String buildGATypeListString() {
        return areaTypeRepository.getAllAsString();
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
    public boolean createAndAddTypeAreaToList(String input) {
        AreaType areaType = areaTypeRepository.create(input);
        return areaTypeRepository.add(areaType);
    }

    /* User Story 02
     As a System Administrator I want to receive a list of all the previously stated Types of area.
     */
    public String getTypeAreaList() {
        return areaTypeRepository.getAllAsString();
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method that creates a new Geographic Area and adds it to our database.
     *
     * @return success if a new GA is added, false otherwise.
     */
    public boolean addNewGeoAreaToList(GeographicAreaDTO geoAreaDTO) {
        GeographicArea geoToAdd = GeographicAreaMapper.dtoToObject(geoAreaDTO);
        return geographicAreaRepository.addAndPersistGA(geoToAdd);
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
    public GeographicAreaDTO createGeoAreaDTO(String newName, AreaTypeDTO typeAreaDTO, LocalDTO localDTO, double length, double width) {
        GeographicArea geoArea = new GeographicArea(newName, typeAreaDTO.getName(), length, width,
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
     * @param typeArea is the type that we want to look for.
     * @return is a list of all the objects in the original list with a type that matches the given type.
     */
    public List<GeographicArea> matchGAByTypeArea(AreaTypeDTO typeArea) {
        return geographicAreaRepository.getGeoAreasByType(typeArea.getName());
    }

    /**
     * @param typeAreaDTO is the Type of Area we want to getDB the name of.
     * @return is the name of the given type of area.
     */
    public String getTypeAreaName(AreaTypeDTO typeAreaDTO) {
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
    public boolean addDaughterArea(GeographicArea daughterArea, GeographicArea motherArea) {
        return motherArea.addDaughterArea(daughterArea);
    }

    /*USER STORY 08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */

    /**
     * @param motherGA   the geographic area that contains the daughter geographic area.
     * @param daughterGA the geographic area that is contained in the mother geographic area.
     * @return returns true if one area is contained in the other, false if it isn't.
     */

    public boolean isAreaContained(GeographicArea motherGA, GeographicArea daughterGA) {
        return daughterGA.getDaughterAreaByID(motherGA.getId()) != null;
    }

    /**
     * Deactivates a sensor from a sensor list
     *
     * @param areaSensorDTO selected sensor from the geographic area, list of sensors
     * @return returns true if the selected sensor is deactivated, if it's already deactivated returns false
     */
    public boolean deactivateSensor(AreaSensorDTO areaSensorDTO) {
        AreaSensor areaSensor = AreaSensorMapper.dtoToObject(areaSensorDTO);
        if (areaSensor.isActive()) {
            areaSensor.deactivateSensor();
            return true;
        }
        return false;
    }

    /* USER STORY 11 */

    public GeographicAreaDTO getInputArea() {
        List<GeographicArea> geographicAreas = geographicAreaRepository.getAll();
        GeographicArea geographicArea = InputHelperUI.getGeographicAreaByList(geographicAreaRepository, geographicAreas);
        return GeographicAreaMapper.objectToDTO(geographicArea);
    }

    public AreaSensorDTO getInputSensor(GeographicAreaDTO geographicAreaDTO) {
        GeographicArea geographicArea = GeographicAreaMapper.dtoToObject(geographicAreaDTO);
        AreaSensor areaSensor = InputHelperUI.getInputSensorByList(geographicArea);
        return AreaSensorMapper.objectToDTO(areaSensor);
    }

    public void removeSensor(AreaSensorDTO areaSensorDTO, GeographicAreaDTO geographicAreaDTO) {
        GeographicArea geographicArea = GeographicAreaMapper.dtoToObject(geographicAreaDTO);
        AreaSensor areaSensor = AreaSensorMapper.dtoToObject(areaSensorDTO);
        geographicArea.removeSensor(areaSensor);
        geographicAreaRepository.updateGeoArea(geographicArea);
    }
}