package pt.ipp.isep.dei.project.dto.mappers;

import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class is responsible for converting Sensors and Sensor DTOs into one another.
 */

public final class AreaSensorMapper {
    /**
     * Don't let anyone instantiate this class.
     */

    private AreaSensorMapper() {
    }

    /**
     * This is the method that converts Sensor DTOs into model objects with the same data.
     *
     * @param dtoToConvert is the DTO we want to convert.
     * @return is the converted model object.
     */
    public static AreaSensor dtoToObject(AreaSensorDTO dtoToConvert) {
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

        // Update status

        boolean objectStatus = dtoToConvert.getActive();

        // Create, update and return converted object

        AreaSensor resultObject = new AreaSensor(objectID, objectName, objectType, new Local(
                objectLatitude, objectLongitude, objectAltitude), objectDate);
        resultObject.setActive(objectStatus);

        return resultObject;
    }

    /**
     * This is the method that converts Sensors into DTOs with the same data.
     *
     * @param objectToConvert is the model object we want to convert.
     * @return is the converted model object.
     */

    public static AreaSensorDTO objectToDTO(AreaSensor objectToConvert) {
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

        String dtoType = objectToConvert.getSensorType();

        // Update the status

        boolean dtoStatus = objectToConvert.isActive();

        // Update the units

        String dtoUnits = objectToConvert.getSensorType();

        // Create, update and return the converted DTO.

        AreaSensorDTO resultDTO = new AreaSensorDTO();
        resultDTO.setAltitude(dtoAltitude);
        resultDTO.setLongitude(dtoLongitude);
        resultDTO.setLatitude(dtoLatitude);
        resultDTO.setUnits(dtoUnits);
        resultDTO.setTypeSensor(dtoType);
        resultDTO.setActive(dtoStatus);
        resultDTO.setName(dtoName);
        resultDTO.setId(dtoID);
        resultDTO.setDateStartedFunctioning(dtoActivationDate);

        return resultDTO;
    }

    public static AreaSensor dtoToObjectMinimalist(AreaSensorDTO dtoToConvert) {

        String objectID = dtoToConvert.getId();

        String objectName = dtoToConvert.getName();

        String objectType = dtoToConvert.getType();

        String objectUnit = dtoToConvert.getUnits();

        double objectLatitude = dtoToConvert.getLatitude();

        double objectLongitude = dtoToConvert.getLongitude();

        double objectAltitude = dtoToConvert.getAltitude();

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

        // Create, update and return converted object

        AreaSensor resultObject = new AreaSensor(objectID, objectName, objectType, new Local(
                objectLatitude, objectLongitude, objectAltitude), objectDate);
        resultObject.setActive(true);

        return resultObject;
    }
}
