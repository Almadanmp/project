package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.HouseSensorDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for converting Sensors and Sensor DTOs into one another.
 */

public class HouseSensorMapper {

    /**
     * Don't let anyone instantiate this class.
     */

    private HouseSensorMapper(){}

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static HouseSensor dtoToObject(HouseSensorDTO dtoToConvert){

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
        knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy", new Locale("en", "US")));
        knownPatterns.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", new Locale("en", "US")));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US")));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                objectDate = pattern.parse(objectDateStartedFunctioningString);
            } catch (ParseException c) {
                c.getErrorOffset();
            }
        }

        // Update the reading list

        ReadingService objectAreaReadingService = new ReadingService();
        for (ReadingDTO r: dtoToConvert.getReadingList()){
            Reading tempAreaReading = ReadingMapper.dtoToObject(r);
            objectAreaReadingService.addReading(tempAreaReading);
        }

        // Update status

        boolean objectStatus = dtoToConvert.getActive();

        // Create, update and return converted object

        HouseSensor resultObject = new HouseSensor(sensorID, objectName, new SensorType(objectType, objectUnit), objectDate, objectRoomID);
        resultObject.setActive(objectStatus);
        resultObject.setReadingService(objectAreaReadingService);

        return resultObject;
    }

    /**
     * This is the method that converts Sensors into DTOs with the same data.
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static HouseSensorDTO objectToDTO(HouseSensor objectToConvert){

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

        // Update the reading list

        List<ReadingDTO> dtoReadingList = new ArrayList<>();
        for (Reading r: objectToConvert.getReadingService().getReadings()){
            ReadingDTO tempReadingDTO = ReadingMapper.objectToDTO(r);
            if(!dtoReadingList.contains(tempReadingDTO)){
                dtoReadingList.add(tempReadingDTO);
            }
        }

        // Create, update and return the converted DTO.

        HouseSensorDTO resultDTO = new HouseSensorDTO();
        resultDTO.setUnits(dtoUnits);
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);
        resultDTO.setReadingList(dtoReadingList);
        resultDTO.setRoomID(dtoRoomID);

        return resultDTO;
    }
}
