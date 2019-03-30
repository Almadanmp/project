package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.Local;

public final class LocalMapper {
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
