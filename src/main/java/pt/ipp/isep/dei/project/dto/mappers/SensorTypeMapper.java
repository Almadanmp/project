package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.SensorTypeDTO;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

public class SensorTypeMapper {

    private SensorTypeMapper() {
    }

    /**
     * This is the method that converts SensorType DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static SensorType dtoToObject(SensorTypeDTO dtoToConvert) {
        // Update parameters

        String objectName = dtoToConvert.getName();
        String objectUnits = dtoToConvert.getUnits();

        // Create, update and return the converted object.


        return new SensorType(objectName, objectUnits);
    }

    /**
     * This is the method that converts Type Sensor model objects into DTOs with the same data.
     *
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */
    public static SensorTypeDTO objectToDTO(SensorType objectToConvert) {
        // Update parameters

        String dtoName = objectToConvert.getName();
        String dtoUnits = objectToConvert.getUnits();


        // Create, update and return the converted object

        SensorTypeDTO resultDTO = new SensorTypeDTO();
        resultDTO.setName(dtoName);
        resultDTO.setUnits(dtoUnits);

        return resultDTO;
    }
}
