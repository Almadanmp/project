package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.HouseReadingDTO;

import pt.ipp.isep.dei.project.model.sensor.HouseReading;

import java.util.Date;

/**
 * This class is responsible for converting readings and Reading DTOs into one another.
 */
public class HouseReadingMapper {
    /**
     * Don't let anyone instantiate this class.
     */
    private HouseReadingMapper(){}

    /**
     * This is the method that converts Reading DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static HouseReading dtoToObject(HouseReadingDTO dtoToConvert) {
        // Update values

        double objectValue = dtoToConvert.getValue();
        Date objectDate = dtoToConvert.getDate();
        String objectUnit = dtoToConvert.getUnit();

        // Create, update and return the converted object

        return new HouseReading(objectValue, objectDate, objectUnit);
    }

    /**
     * This is the method that converts readings into DTOs with the same data.
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static HouseReadingDTO objectToDTO(HouseReading objectToConvert) {
        // Update values

        double dtoValue = objectToConvert.getValue();
        Date dtoDate = objectToConvert.getDate();

        // Create, update and return the converted DTO

        HouseReadingDTO resultDTO = new HouseReadingDTO();
        resultDTO.setDate(dtoDate);
        resultDTO.setValue(dtoValue);
        return resultDTO;
    }
}
