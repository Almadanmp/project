package pt.ipp.isep.dei.project.dto.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for converting Geographic Areas and Geographic Area DTOs into one another.
 */

public final class GeographicAreaMapper {

    @Autowired
    private GeographicAreaRepository geographicAreaRepository;

    /**
     * Don't let anyone instantiate this class.
     */

    private GeographicAreaMapper() {
    }

    /**
     * This is the method that converts Geographic Area DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static GeographicArea dtoToObject(GeographicAreaDTO dtoToConvert) {
        // Update generic parameters

        Long objectId = null;

        try {
            objectId = dtoToConvert.getId();
            if (objectId == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException ok) {
            ok.getMessage();
        }

        String objectName = dtoToConvert.getName();

        String objectType = dtoToConvert.getTypeArea();

        double objectLength = dtoToConvert.getLength();

        double objectWidth = dtoToConvert.getWidth();

        Local objectLocal = LocalMapper.dtoToObject(dtoToConvert.getLocal());

        String objectDescription = dtoToConvert.getDescription();


        // Update sensors

        List<AreaSensor> objectSensorList = new ArrayList<>();
        for (AreaSensorDTO s : dtoToConvert.getSensors()) {
            AreaSensor tempObject = AreaSensorMapper.dtoToObject(s);
            objectSensorList.add(tempObject);
        }


        // Create, update and return the converted object.

        GeographicArea resultObject = new GeographicArea(objectName, objectType, objectLength, objectWidth,
                objectLocal);
        for (AreaSensorDTO sensorDTO : dtoToConvert.getSensors()) {
            AreaSensor sensor = AreaSensorMapper.dtoToObject(sensorDTO);
            resultObject.addSensor(sensor);
        }
        resultObject.setId(objectId);
        resultObject.setDescription(objectDescription);
        resultObject.setAreaSensors(objectSensorList);


        return resultObject;
    }

    /**
     * This is the method that converts Geographic Area DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static GeographicArea dtoToObjectWithMother(GeographicAreaDTO dtoToConvert, GeographicAreaDTO daughter) {
        // Update generic parameters

        Long objectId = null;

        try {
            objectId = dtoToConvert.getId();
            if (objectId == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException ok) {
            ok.getMessage();
        }

        String objectName = dtoToConvert.getName();

        String objectType = dtoToConvert.getTypeArea();

        double objectLength = dtoToConvert.getLength();

        double objectWidth = dtoToConvert.getWidth();

        Local objectLocal = LocalMapper.dtoToObject(dtoToConvert.getLocal());

        String objectDescription = dtoToConvert.getDescription();


        // Update sensors

        List<AreaSensor> objectSensorList = new ArrayList<>();
        for (AreaSensorDTO s : dtoToConvert.getSensors()) {
            AreaSensor tempObject = AreaSensorMapper.dtoToObject(s);
            objectSensorList.add(tempObject);
        }

        List<GeographicArea> objectGeographicList = new ArrayList<>();
        for (GeographicAreaDTO s : dtoToConvert.getDaughterAreas()) {
            GeographicArea tempAreaObject = GeographicAreaMapper.dtoToObject(s);
            objectGeographicList.add(tempAreaObject);
        }


        // Create, update and return the converted object.

        GeographicArea resultObject = new GeographicArea(objectName, objectType, objectLength, objectWidth,
                objectLocal);
        for (AreaSensorDTO sensorDTO : dtoToConvert.getSensors()) {
            AreaSensor sensor = AreaSensorMapper.dtoToObject(sensorDTO);
            resultObject.addSensor(sensor);
        }
        for (GeographicAreaDTO daughterAreaDTO : dtoToConvert.getDaughterAreas()) {
            GeographicArea geoArea = GeographicAreaMapper.dtoToObject(daughterAreaDTO);
            resultObject.addDaughterArea(geoArea);
        }
        resultObject.setId(objectId);
        resultObject.setDescription(objectDescription);
        resultObject.setAreaSensors(objectSensorList);
        resultObject.setDaughterAreas(objectGeographicList);


        return resultObject;
    }

    public static GeographicAreaDTO objectToDTOWithMother(GeographicArea objectToConvert) {
        // Create the result DTO

        GeographicAreaDTO resultDTO = new GeographicAreaDTO();

        // Update generic parameters

        try {
            Long dtoID = objectToConvert.getId();
            resultDTO.setId(dtoID);
            if (dtoID == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException ok) {
            ok.getMessage();
        }

        String dtoName = objectToConvert.getName();

        String dtoType = objectToConvert.getAreaTypeID();

        double dtoLength = objectToConvert.getLength();

        double dtoWidth = objectToConvert.getWidth();

        LocalDTO localDTO = LocalMapper.objectToDTO(objectToConvert.getLocal());

        String dtoDescription = objectToConvert.getDescription();



        // Update sensors

        List<AreaSensorDTO> dtoSensorList = new ArrayList<>();
        for (AreaSensor s : objectToConvert.getAreaSensors()) {
            AreaSensorDTO tempDTO = AreaSensorMapper.objectToDTO(s);
            dtoSensorList.add(tempDTO);
        }

        List<GeographicAreaDTO> dtoDaughterList = new ArrayList<>();
        for (AreaSensor s : objectToConvert.getAreaSensors()) {
            AreaSensorDTO tempDTO = AreaSensorMapper.objectToDTO(s);
            dtoSensorList.add(tempDTO);
        }

        // Update and return the converted DTO.

        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setTypeArea(dtoType);
        resultDTO.setDescription(dtoDescription);
        resultDTO.setName(dtoName);
        resultDTO.setLocal(localDTO);
        resultDTO.setSensorList(dtoSensorList);
        resultDTO.setDaughterAreaList(dtoDaughterList);

        return resultDTO;
    }

    /**
     * This is the method that converts Geographic Area model objects into DTOs with the same data.
     *
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */
    public static GeographicAreaDTO objectToDTO(GeographicArea objectToConvert) {
        // Create the result DTO

        GeographicAreaDTO resultDTO = new GeographicAreaDTO();

        // Update generic parameters

        try {
            Long dtoID = objectToConvert.getId();
            resultDTO.setId(dtoID);
            if (dtoID == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException ok) {
            ok.getMessage();
        }

        String dtoName = objectToConvert.getName();

        String dtoType = objectToConvert.getAreaTypeID();

        double dtoLength = objectToConvert.getLength();

        double dtoWidth = objectToConvert.getWidth();

        LocalDTO localDTO = LocalMapper.objectToDTO(objectToConvert.getLocal());

        String dtoDescription = objectToConvert.getDescription();

        // Update sensors

        List<AreaSensorDTO> dtoSensorList = new ArrayList<>();
        for (AreaSensor s : objectToConvert.getAreaSensors()) {
            AreaSensorDTO tempDTO = AreaSensorMapper.objectToDTO(s);
            dtoSensorList.add(tempDTO);
        }

        // Update and return the converted DTO.

        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setTypeArea(dtoType);
        resultDTO.setDescription(dtoDescription);
        resultDTO.setName(dtoName);
        resultDTO.setLocal(localDTO);
        resultDTO.setSensorList(dtoSensorList);

        return resultDTO;
    }

//    public static List<LinkedHashMap<String, Object>> controllerGADTOToList(List<GeographicAreaDTO> geographicAreaDTOList) {
//        List<LinkedHashMap<String, Object>> entities = new ArrayList<>();
//        for (GeographicAreaDTO dto : geographicAreaDTOList) {
//            LinkedHashMap<String, Object> entity = new LinkedHashMap<>();
//            entity.put("Id", dto.getId());
//            entity.put("Name", dto.getName());
//            entity.put("Description", dto.getDescription());
//            entity.put("Sensors", AreaSensorMapper.controllerAreaSensorDTOToList(dto.getSensors()));
//            entities.add(entity);
//        }
//        return entities;
//    }

    public static GeographicAreaWebDTO objectToWebDTO(GeographicArea objectToConvert) {
        // Create the result DTO

        GeographicAreaWebDTO resultDTO = new GeographicAreaWebDTO();

        // Update generic parameters

        try {
            Long id = objectToConvert.getId();
            resultDTO.setId(id);
            if (id == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException np) {
            np.getMessage();
        }

        String type = objectToConvert.getAreaTypeID();

        String dtoName = objectToConvert.getName();

        String dtoDescription = objectToConvert.getDescription();

        // Update sensors

        List<AreaSensorWebDTO> dtoSensorList = new ArrayList<>();
        for (AreaSensor s : objectToConvert.getAreaSensors()) {
            AreaSensorWebDTO tempDTO = AreaSensorMapper.objectToWebDTO(s);
            dtoSensorList.add(tempDTO);
        }

        // Update and return the converted DTO.

        resultDTO.setDescription(dtoDescription);
        resultDTO.setName(dtoName);
        resultDTO.setTypeArea(type);
        resultDTO.setSensorDTOList(dtoSensorList);

        return resultDTO;
    }

}