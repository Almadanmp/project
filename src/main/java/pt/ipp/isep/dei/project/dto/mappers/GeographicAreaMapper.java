package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.AreaType;
import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for converting Geographic Areas and Geographic Area DTOs into one another.
 */

public final class GeographicAreaMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    public GeographicAreaMapper() {
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
        } catch (NullPointerException ignored) {
            ignored.getMessage();
        }

        String objectName = dtoToConvert.getName();

        String objectType = dtoToConvert.getTypeArea();

        double objectLength = dtoToConvert.getLength();

        double objectWidth = dtoToConvert.getWidth();

        Local objectLocal = LocalMapper.dtoToObject(dtoToConvert.getLocalDTO());

        String objectDescription = dtoToConvert.getDescription();

        // Update the AreaSensorList

        AreaSensorService objectAreaSensorService = new AreaSensorService();
        for (AreaSensorDTO y : dtoToConvert.getAreaSensorDTOList()) {
            AreaSensor tempAreaSensor = AreaSensorMapper.dtoToObject(y);
            objectAreaSensorService.add(tempAreaSensor);
        }

        // Create, update and return the converted object.

        GeographicArea resultObject = new GeographicArea(objectName, new AreaType(objectType), objectLength, objectWidth,
                objectLocal);
        resultObject.setId(objectId);
        resultObject.setDescription(objectDescription);
        resultObject.setSensorList(objectAreaSensorService);


        return resultObject;
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

        } catch (NullPointerException ignored) {
            ignored.getMessage();
        }

        String dtoName = objectToConvert.getName();

        String dtoType = objectToConvert.getAreaType().getName();

        double dtoLength = objectToConvert.getLength();

        double dtoWidth = objectToConvert.getWidth();

        LocalDTO localDTO = LocalMapper.objectToDTO(objectToConvert.getLocal());

        String dtoDescription = objectToConvert.getDescription();

        // Update the AreaSensorList

        List<AreaSensorDTO> areaSensorDTOList = new ArrayList<>();
        for (AreaSensor b : objectToConvert.getSensorList().getAreaSensors()) {
            AreaSensorDTO tempDTO = AreaSensorMapper.objectToDTO(b);
            if (!areaSensorDTOList.contains(tempDTO)) {
                areaSensorDTOList.add(tempDTO);
            }
        }

        // Update and return the converted DTO.


        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setTypeArea(dtoType);
        resultDTO.setDescription(dtoDescription);
        resultDTO.setName(dtoName);
        resultDTO.setLocalDTO(localDTO);
        resultDTO.setAreaSensorDTOList(areaSensorDTOList);

        return resultDTO;
    }
}
