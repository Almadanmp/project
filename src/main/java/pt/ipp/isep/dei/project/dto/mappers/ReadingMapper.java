package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.model.Reading;

import java.util.Date;

/**
 * This class is responsible for converting Readings and Reading DTOs into one another.
 */
public final class ReadingMapper {
    /**
     * Don't let anyone instantiate this class.
     */
    private ReadingMapper(){}

    /**
     * This is the method that converts Reading DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static Reading dtoToObject(ReadingDTO dtoToConvert) {
        // Update values

        double objectValue = dtoToConvert.getValue();
        Date objectDate = dtoToConvert.getDate();

        // Create, update and return the converted object

        return new Reading(objectValue, objectDate);
    }

    /**
     * This is the method that converts Readings into DTOs with the same data.
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static ReadingDTO objectToDTO(Reading objectToConvert) {
        // Update values

        double dtoValue = objectToConvert.getValue();
        Date dtoDate = objectToConvert.getDate();

        // Create, update and return the converted DTO

        ReadingDTO resultDTO = new ReadingDTO();
        resultDTO.setDate(dtoDate);
        resultDTO.setValue(dtoValue);
        return resultDTO;
    }
}
