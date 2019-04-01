package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.TypeAreaDTO;
import pt.ipp.isep.dei.project.model.TypeArea;


/**
 * This class is responsible for converting TypeAreas and TypeAreaDTOs into one another.
 */

public final class TypeAreaMapper {
    /**
     * Don't let anyone instantiate this class.
     */
    private TypeAreaMapper(){}

    /**
     * This is the method that converts TypeArea DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static TypeArea dtoToObject(TypeAreaDTO dtoToConvert){
        // Update parameters

        String objectName = dtoToConvert.getName();

        long objectID = dtoToConvert.getID();

        // Create, update and return the converted object.

        TypeArea resultObject = new TypeArea(objectName);

        resultObject.setId(objectID);

        return resultObject;
    }

    /**
     * This is the method that converts Type Area model objects into DTOs with the same data.
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */
    public static TypeAreaDTO objectToDTO(TypeArea objectToConvert){
        // Update parameters

        String dtoName = objectToConvert.getName();

        long dtoID = objectToConvert.getId();

        // Create, update and return the converted object

        TypeAreaDTO resultDTO = new TypeAreaDTO();
        resultDTO.setName(dtoName);
        resultDTO.setID(dtoID);

        return resultDTO;
    }
}
