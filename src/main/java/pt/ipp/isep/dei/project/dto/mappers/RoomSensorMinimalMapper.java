package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.RoomSensorDTOMinimal;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RoomSensorMinimalMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private RoomSensorMinimalMapper() {
    }

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static RoomSensor dtoToObject(RoomSensorDTOMinimal dtoToConvert) {

        String objectID = dtoToConvert.getSensorID();

        // Update name

        String objectName = dtoToConvert.getName();

        // Update type

        String objectType = dtoToConvert.getType();

        // Update date of activation

        Date objectDate = null;
        String objectDateStartedFunctioningString = dtoToConvert.getDateStartedFunctioning();
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat4 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en", "US"));
        knownPatterns.add(dateFormat1);
        knownPatterns.add(dateFormat2);
        knownPatterns.add(dateFormat3);
        knownPatterns.add(dateFormat4);
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                objectDate = pattern.parse(objectDateStartedFunctioningString);
            } catch (ParseException c) {
                c.getErrorOffset();
            }
        }

        // Update status

        boolean objectStatus = dtoToConvert.getActive();

        // Create, update and return converted object

        RoomSensor resultObject = new RoomSensor(objectID, objectName, objectType, objectDate);
        resultObject.setActive(objectStatus);
        return resultObject;
    }

    public static RoomSensorDTOMinimal objectToDTO(RoomSensor objectToConvert) {
        // Update the id

        String dtoID = objectToConvert.getId();

        // Update the name

        String dtoName = objectToConvert.getName();

        // Update the date of activation

        String dtoActivationDate = objectToConvert.getDateStartedFunctioning().toString();

        // Update the type of the sensor

        String dtoType = objectToConvert.getSensorType();

        // Update the status

        boolean dtoStatus = objectToConvert.isActive();


        // Create, update and return the converted DTO.

        RoomSensorDTOMinimal resultDTO = new RoomSensorDTOMinimal();
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);
        resultDTO.setSensorId(dtoID);

        return resultDTO;
    }

}
