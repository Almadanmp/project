package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for converting Geographic Areas and Geographic Area DTOs into one another.
 */

public final class GeographicAreaMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private GeographicAreaMapper(){}

    /**
     * This is the method that converts Geographic Area DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static GeographicArea dtoToObject(GeographicAreaDTO dtoToConvert) {
        // Update generic parameters

        long objectId = -1;

        try {
            objectId = dtoToConvert.getId();
        }
        catch (NullPointerException ok){
            ok.printStackTrace();
        }


        String objectName = dtoToConvert.getName();

        String objectType = dtoToConvert.getTypeArea();

        double objectLength = dtoToConvert.getLength();

        double objectWidth = dtoToConvert.getWidth();

        double objectLatitude = dtoToConvert.getLatitude();

        double objectLongitude = dtoToConvert.getLongitude();

        double objectAltitude = dtoToConvert.getAltitude();

        String objectDescription = dtoToConvert.getDescription();

        // Update the SensorList

        SensorList objectSensorList = new SensorList();
        for (SensorDTO y : dtoToConvert.getSensorDTOList()) {
            Sensor tempSensor = SensorMapper.dtoToObject(y);
            objectSensorList.add(tempSensor);
        }

        // Create, update and return the converted object.

        GeographicArea resultObject = new GeographicArea(objectName, new TypeArea(objectType), objectLength, objectWidth,
                new Local(objectLatitude, objectLongitude, objectAltitude));
        resultObject.setId(objectId);
        resultObject.setDescription(objectDescription);
        resultObject.setSensorList(objectSensorList);

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
            long dtoID = objectToConvert.getId();
            resultDTO.setId(dtoID);

        } catch (NullPointerException ok) {
            ok.printStackTrace();
        }

        String dtoName = objectToConvert.getName();

        String dtoType = objectToConvert.getTypeArea().getName();

        double dtoLength = objectToConvert.getLength();

        double dtoWidth = objectToConvert.getWidth();

        double dtoLatitude = objectToConvert.getLocal().getLatitude();

        double dtoLongitude = objectToConvert.getLocal().getLongitude();

        double dtoAltitude = objectToConvert.getLocal().getAltitude();

        String dtoDescription = objectToConvert.getDescription();

        // Update the SensorList

        List<SensorDTO> sensorDTOList = new ArrayList<>();
        for (Sensor b : objectToConvert.getSensorList().getSensors()) {
            SensorDTO tempDTO = SensorMapper.objectToDTO(b);
            if (!sensorDTOList.contains(tempDTO)) {
                sensorDTOList.add(tempDTO);
            }
        }

        // Update and return the converted DTO.


        resultDTO.setLength(dtoLength);
        resultDTO.setWidth(dtoWidth);
        resultDTO.setTypeArea(dtoType);
        resultDTO.setDescription(dtoDescription);
        resultDTO.setName(dtoName);
        resultDTO.setLongitude(dtoLongitude);
        resultDTO.setAltitude(dtoAltitude);
        resultDTO.setLatitude(dtoLatitude);
        resultDTO.setSensorDTOList(sensorDTOList);

        return resultDTO;
    }
}
