package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.PowerSourceDTO;
import pt.ipp.isep.dei.project.model.PowerSource;

/**
 * This class is responsible for converting PowerSources and PowerSource DTOs into one another.
 */

public final class PowerSourceMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private PowerSourceMapper(){}

    /**
     * This is the method that converts PowerSource DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */

    public static PowerSource dtoToObject(PowerSourceDTO dtoToConvert){
        // Update name

        String objectName = dtoToConvert.getName();

        // Update maximum power output

        double objectMaxPowerOutput = dtoToConvert.getMaxPowerOutput();

        // Update maximum energy storage

        double objectMaxEnergyStorage = dtoToConvert.getMaxEnergyStorage();

        // Create, update, and return converted object

        return new PowerSource(objectName, objectMaxPowerOutput, objectMaxEnergyStorage);
    }

    /**
     * This is the method that converts PowerSource model objects into DTOs with the same data.
     * @param objectToConvert is the object we want to convert.
     * @return is the converted DTO.
     */

    public static PowerSourceDTO objectToDTO(PowerSource objectToConvert){
        // Update name

        String dtoName = objectToConvert.getName();

        // Update maximum energy storage

        double dtoMaxEnergyStorage = objectToConvert.getMaxEnergyStorage();

        // Update maximum power output

        double dtoMaxPowerOutput = objectToConvert.getMaxPowerOutput();

        // Create, update and return converted object

        PowerSourceDTO resultDTO = new PowerSourceDTO();
        resultDTO.setMaxEnergyStorage(dtoMaxEnergyStorage);
        resultDTO.setMaxPowerOutput(dtoMaxPowerOutput);
        resultDTO.setName(dtoName);

        return resultDTO;
    }
}
