package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.model.sensor.HouseSensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for converting Sensors and Sensor DTOs into one another.
 */

public final class HouseSensorMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private HouseSensorMapper() {
    }

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static HouseSensor dtoToObject(HouseSensorDTO dtoToConvert) {

        String sensorID = dtoToConvert.getId();

        // Update name

        String objectName = dtoToConvert.getName();

        // Update type

        String objectType = dtoToConvert.getType();

        // Update units

        String objectUnit = dtoToConvert.getUnits();

        // Update roomID

        String objectRoomID = dtoToConvert.getRoomID();

        // Update date of activation

        Date objectDate = null;
        String objectDateStartedFunctioningString = dtoToConvert.getDateStartedFunctioning();
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        knownPatterns.add(dateFormat1);
        knownPatterns.add(dateFormat2);
        knownPatterns.add(dateFormat3);
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

        HouseSensor resultObject = new HouseSensor(sensorID, objectName, new SensorType(objectType, objectUnit), objectDate, objectRoomID);
        resultObject.setActive(objectStatus);
        return resultObject;
    }

    /**
     * This is the method that converts Sensors into DTOs with the same data.
     *
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static HouseSensorDTO objectToDTO(HouseSensor objectToConvert) {

        // Update the name

        String dtoName = objectToConvert.getName();

        // Update the roomID

        String dtoRoomID = objectToConvert.getRoomId();

        // Update the date of activation

        String dtoActivationDate = objectToConvert.getDateStartedFunctioning().toString();

        // Update the type of the sensor

        String dtoType = objectToConvert.getSensorType().getName();

        // Update the status

        boolean dtoStatus = objectToConvert.isActive();

        // Update the units

        String dtoUnits = objectToConvert.getSensorType().getUnits();

        // Create, update and return the converted DTO.

        HouseSensorDTO resultDTO = new HouseSensorDTO();
        resultDTO.setUnits(dtoUnits);
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);
        resultDTO.setRoomID(dtoRoomID);

        return resultDTO;
    }
}
