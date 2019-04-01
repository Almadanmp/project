package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.SensorDTO;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.TypeSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for converting Sensors and Sensor DTOs into one another.
 */

public final class SensorMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private SensorMapper(){}

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static Sensor dtoToObject(SensorDTO dtoToConvert){
        // Update id

        String objectID = dtoToConvert.getId();

        // Update name

        String objectName = dtoToConvert.getName();

        // Update type

        String objectType = dtoToConvert.getType();

        // Update units

        String objectUnit = dtoToConvert.getUnits();

        // Update latitude

        double objectLatitude = dtoToConvert.getLatitude();

        // Update longitude

        double objectLongitude = dtoToConvert.getLongitude();

        // Update altitude

        double objectAltitude = dtoToConvert.getAltitude();

        // Update date of activation

        Date objectDate = null;
        String objectDateStartedFunctioningString = dtoToConvert.getDateStartedFunctioning();
        List<SimpleDateFormat> knownPatterns = new ArrayList<>();
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy"));
        knownPatterns.add(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                objectDate = pattern.parse(objectDateStartedFunctioningString);
            } catch (ParseException c) {
                c.getErrorOffset();
            }
        }

        // Update the reading list

        ReadingList objectReadingList = new ReadingList();
        for (ReadingDTO r: dtoToConvert.getReadingList()){
            Reading tempReading = ReadingMapper.dtoToObject(r);
            objectReadingList.addReading(tempReading);
        }

        // Update status

        boolean objectStatus = dtoToConvert.getActive();

        // Create, update and return converted object

        Sensor resultObject = new Sensor(objectID, objectName, new TypeSensor(objectType, objectUnit), new Local(
                objectLatitude, objectLongitude, objectAltitude), objectDate);
        resultObject.setActive(objectStatus);
        resultObject.setReadingList(objectReadingList);

        return resultObject;
    }

    /**
     * This is the method that converts Sensors into DTOs with the same data.
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static SensorDTO objectToDTO(Sensor objectToConvert){
        // Update the ID

        String dtoID = objectToConvert.getId();

        // Update the name

        String dtoName = objectToConvert.getName();

        // Update the date of activation

        String dtoActivationDate = objectToConvert.getDateStartedFunctioning().toString();

        // Update the local

        double dtoAltitude = objectToConvert.getLocal().getAltitude();
        double dtoLongitude = objectToConvert.getLocal().getLongitude();
        double dtoLatitude = objectToConvert.getLocal().getLatitude();

        // Update the type of the sensor

        String dtoType = objectToConvert.getTypeSensor().getName();

        // Update the status

        boolean dtoStatus = objectToConvert.isActive();

        // Update the units

        String dtoUnits = objectToConvert.getTypeSensor().getUnits();

        // Update the reading list

        List<ReadingDTO> dtoReadingList = new ArrayList<>();
        for (Reading r: objectToConvert.getReadingList().getReadings()){
            ReadingDTO tempReadingDTO = ReadingMapper.objectToDTO(r);
            if(!dtoReadingList.contains(tempReadingDTO)){
                dtoReadingList.add(tempReadingDTO);
            }
        }

        // Create, update and return the converted DTO.

        SensorDTO resultDTO = new SensorDTO();
        resultDTO.setAltitude(dtoAltitude);
        resultDTO.setLongitude(dtoLongitude);
        resultDTO.setLatitude(dtoLatitude);
        resultDTO.setUnits(dtoUnits);
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setId(dtoID);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);
        resultDTO.setReadingList(dtoReadingList);

        return resultDTO;
    }
}
