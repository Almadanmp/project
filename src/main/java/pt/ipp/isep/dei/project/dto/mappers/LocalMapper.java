package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.Local;

/**
 * This class is responsible for converting Locals and Local DTOs into one another.
 */

public final class LocalMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private LocalMapper(){}

    /**
     * This is the method that converts Local DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */

    public static Local dtoToObject(LocalDTO dtoToConvert){
        // Update parameters

        double objectLatitude = dtoToConvert.getLatitude();

        double objectLongitude = dtoToConvert.getLongitude();

        double objectAltitude = dtoToConvert.getAltitude();

        long objectID = dtoToConvert.getId();

        // Create, update and return new object

        Local resultObject = new Local(objectLatitude, objectLongitude, objectAltitude);

        resultObject.setId(objectID);

        return resultObject;
    }

    /**
     * This is the method that converts Local model objects into DTOs with the same data.
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */

    public static LocalDTO objectToDTO(Local objectToConvert){
        // Update parameters

        double dtoLatitude = objectToConvert.getLatitude();

        double dtoLongitude = objectToConvert.getLongitude();

        double dtoAltitude = objectToConvert.getAltitude();

        long dtoID = objectToConvert.getId();

        // Create, update and return new object

        LocalDTO resultDTO = new LocalDTO();
        resultDTO.setAltitude(dtoAltitude);
        resultDTO.setLatitude(dtoLatitude);
        resultDTO.setLongitude(dtoLongitude);
        resultDTO.setId(dtoID);

        return resultDTO;
    }
}
