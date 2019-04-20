package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.AreaTypeDTO;
import pt.ipp.isep.dei.project.model.areatype.AreaType;


/**
 * This class is responsible for converting TypeAreas and TypeAreaDTOs into one another.
 */

public final class AreaTypeMapper {
    /**
     * Don't let anyone instantiate this class.
     */
    private AreaTypeMapper(){}

    /**
     * This is the method that converts AreaType DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static AreaType dtoToObject(AreaTypeDTO dtoToConvert){
        // Update parameters

        String objectName = dtoToConvert.getName();

        long objectID = dtoToConvert.getID();

        // Create, update and return the converted object.


        AreaType resultObject = new AreaType(objectName);

        resultObject.setId(objectID);

        return resultObject;
    }

    /**
     * This is the method that converts Type Area model objects into DTOs with the same data.
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */
    public static AreaTypeDTO objectToDTO(AreaType objectToConvert){
        // Update parameters

        String dtoName = objectToConvert.getName();

        long dtoID = objectToConvert.getId();

        // Create, update and return the converted object

        AreaTypeDTO resultDTO = new AreaTypeDTO();
        resultDTO.setName(dtoName);
        resultDTO.setID(dtoID);

        return resultDTO;
    }
}
