package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.RoomSensorDTO;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.room.RoomSensor;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for converting Sensors and Sensor DTOs into one another.
 */

public final class RoomSensorMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private RoomSensorMapper() {
    }

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static RoomSensor dtoToObject(RoomSensorDTO dtoToConvert) {

        String objectID = dtoToConvert.getId();

        // Update name

        String objectName = dtoToConvert.getName();

        // Update type

        String objectType = dtoToConvert.getType();

        // Update units

        String objectUnit = dtoToConvert.getUnits();

        // Update roomID

        String objectRoomID = dtoToConvert.getRoomID();

        // Update readings

        List<Reading> objectReadingList = new ArrayList<>();
        for (ReadingDTO r : dtoToConvert.getReadingList()) {
            Reading tempReading = ReadingMapper.dtoToObject(r);
            objectReadingList.add(tempReading);
        }

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

        RoomSensor resultObject = new RoomSensor(objectID, objectName, new SensorType(objectType, objectUnit), objectDate, objectRoomID);
        resultObject.setActive(objectStatus);
        resultObject.setReadings(objectReadingList);
        return resultObject;
    }

    /**
     * This is the method that converts Sensors into DTOs with the same data.
     *
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static RoomSensorDTO objectToDTO(RoomSensor objectToConvert) {
        // Update the id

        String dtoID = objectToConvert.getId();

        // Update the name

        String dtoName = objectToConvert.getName();

        // Update the date of activation

        String dtoActivationDate = objectToConvert.getDateStartedFunctioning().toString();

        // Update the type of the sensor

        String dtoType = objectToConvert.getSensorType().getName();

        // Update the status

        boolean dtoStatus = objectToConvert.isActive();

        // Update the units

        String dtoUnits = objectToConvert.getSensorType().getUnits();

        // Update the readings

        List<ReadingDTO> dtoReadingList = new ArrayList<>();
        for (Reading r : objectToConvert.getReadings()) {
            ReadingDTO tempDTO = ReadingMapper.objectToDTO(r);
            dtoReadingList.add(tempDTO);
        }

        // Create, update and return the converted DTO.

        RoomSensorDTO resultDTO = new RoomSensorDTO();
        resultDTO.setUnits(dtoUnits);
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);
        resultDTO.setReadingList(dtoReadingList);
        resultDTO.setId(dtoID);

        return resultDTO;
    }
}
