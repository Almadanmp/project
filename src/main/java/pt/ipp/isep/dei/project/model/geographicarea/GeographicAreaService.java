package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.controller.utils.LogUtils;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.reader.CustomFormatter;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.io.IOException;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaService {
    @Autowired
    private GeographicAreaRepository geographicAreaRepository;
    @Autowired
    private AreaTypeRepository areaTypeRepository;
    @Autowired
    AreaSensorRepository areaSensorRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    private static final String BUILDER = "---------------\n";

    public GeographicAreaService(GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository, AreaSensorRepository areaSensorRepository, SensorTypeRepository sensorTypeRepository) {
        this.geographicAreaRepository = geographicAreaRepository;
        this.areaTypeRepository = areaTypeRepository;
        this.areaSensorRepository = areaSensorRepository;
        this.sensorTypeRepository = sensorTypeRepository;
    }

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public List<GeographicArea> getAll() {
        return this.geographicAreaRepository.findAll();
    }

    /**
     * Method that receives a geographic area as a parameter and adds that
     * GA to the list in case it is not contained in that list already.
     *
     * @param geographicAreaToAdd geographic area to be added
     * @return returns true in case the geographic area is added and false if not
     **/
    public boolean addAndPersistGA(GeographicArea geographicAreaToAdd) {
        List<GeographicArea> geographicAreas = getAll();
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            geographicAreaRepository.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    public void updateGeoArea(GeographicArea area) {
        geographicAreaRepository.save(area);
    }

    /**
     * This method receives a String of a given sensor ID, a list of Readings and a Logger,
     * and tries to add the readings to the sensor with the given sensor ID. The sensor will be
     * fetched from the geographic area from the geographic area repository.
     *
     * @param sensorID a string of the sensor ID
     * @param readings a list of readings to be added to the given sensor
     * @param logger   logger
     * @return the number of readings added
     **/
    public int addAreaReadings(String sensorID, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        try {
            GeographicArea geographicArea = getGeographicAreaContainingSensorWithGivenId(sensorID);
            AreaSensor areaSensor = geographicArea.getAreaSensorByID(sensorID);
            addedReadings = addReadingsToAreaSensor(areaSensor, readings, logger);
            geographicAreaRepository.save(geographicArea);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                logger.fine("The reading " + r.getValue() + " " + r.getUnit() + " from " + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.");
            }
        }
        return addedReadings;
    }

    /**
     * This method receives a string of a sensor ID and will look in the repository
     * for the geographic area that contains the sensor with the given sensor ID.
     *
     * @param sensorID string of the sensor ID
     * @return the geographic area that contains the sensor with the given ID
     **/
    public GeographicArea getGeographicAreaContainingSensorWithGivenId(String sensorID) {
        List<GeographicArea> geographicAreas = geographicAreaRepository.findAll();
        for (GeographicArea ga : geographicAreas) {
            List<AreaSensor> areaSensors = ga.getAreaSensors();
            for (AreaSensor sensor : areaSensors) {
                String tempSensorID = sensor.getId();
                if (tempSensorID.equals(sensorID)) {
                    return ga;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method receives an Area Sensor, a list of readings and a logger, tries to add the
     * readings to the given Area Sensor, returning the number of readings that were added.
     * The method will log every reading that wasn't added to the Area Sensor.
     *
     * @param areaSensor given Area Sensor
     * @param readings   list of readings to be added to the given Area Sensor
     * @param logger     logger
     * @return number of readings added to the Area Sensor
     **/
    private int addReadingsToAreaSensor(AreaSensor areaSensor, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (areaSensor.readingExists(readingDate)) {
                logger.fine("The reading " + r.getValue() + " " + r.getUnit() + " from " + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because it already exists.");
            } else if (!areaSensor.activeDuringDate(readingDate)) {
                logger.fine("The reading " + r.getValue() + " " + r.getUnit() + " from " + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
            } else if (areaSensor.addReading(r)) {
                addedReadings++;
            }
        }
        return addedReadings;
    }

    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildStringRepository(List<GeographicArea> geographicAreas) {
        StringBuilder result = new StringBuilder(new StringBuilder(BUILDER));
        if (geographicAreas.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (GeographicArea ga : geographicAreas) {
            result.append(ga.getId()).append(") Name: ").append(ga.getName()).append(" | ");
            result.append("Type: ").append(ga.getAreaType().getName()).append(" | ");
            result.append("Latitude: ").append(ga.getLocal().getLatitude()).append(" | ");
            result.append("Longitude: ").append(ga.getLocal().getLongitude()).append("\n");
        }
        result.append(BUILDER);
        return result.toString();
    }

    /**
     * Method to create a new geographic area before adding it to a GA List.
     *
     * @param newName      input string for geographic area name for the new geographic area
     * @param areaTypeName input string for type area for the new geographic area
     * @param length       input number for length for the new geographic area
     * @param width        input number for width for the new geographic area
     * @param local        input number for latitude, longitude and altitude of the new geographic area
     * @return a new geographic area.
     */
    public GeographicArea createGA(String newName, String areaTypeName, double length, double width, Local local) {
        Logger logger = LogUtils.getLogger("areaTypeLogger", "resources/logs/areaTypeLogHtml.html", Level.FINE);
        AreaType areaType = getAreaTypeByName(areaTypeName, logger);
        LogUtils.closeHandlers(logger);
        if (areaType != null) {
            // areaTypeRepository.save(areaType);
            return new GeographicArea(newName, areaType, length, width, local);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method that returns a GeographicAreaList with a given type.
     *
     * @param typeAreaName is the type of the area we want to get all the geographicAreas.
     * @return a GeographicAreaList with a given type.
     */
    public List<GeographicArea> getGeoAreasByType(List<GeographicArea> geographicAreas, String typeAreaName) {
        List<GeographicArea> finalList = new ArrayList<>();
        AreaType areaTypeToTest = new AreaType(typeAreaName);
        for (GeographicArea ga : geographicAreas) {
            if (ga.equalsTypeArea(areaTypeToTest)) {
                finalList.add(ga);
            }
        }
        return finalList;
    }

    /**
     * Checks the geographic area list size and returns the size as int.\
     *
     * @return GeographicAreaList size as int
     **/
    public int size() {
        return getAll().size();
    }

    /**
     * This method receives an index as parameter and gets a geographic area from geographic
     * area list.
     *
     * @param id the index of the GA.
     * @return returns geographic area that corresponds to index.
     */
    public GeographicArea get(long id) {
        Optional<GeographicArea> value = geographicAreaRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        throw new NoSuchElementException("ERROR: There is no Geographic Area with the selected ID.");
    }

    /**
     * This method checks if a geographic area list is empty
     *
     * @return true if empty, false otherwise
     **/
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public AreaType getAreaTypeByName(String name, Logger logger) {
        Optional<AreaType> value = areaTypeRepository.findByName(name);
        if (!(value.isPresent())) {
            logger.fine("The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.");
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            return value.orElseGet(() -> new AreaType(name));
        }
    }


    //METHODS FROM AREA SENSOR REPOSITORY


    /**
     * This method receives a sensor ID, checks if that sensor exists in the repository.
     *
     * @param sensorID String of sensor ID
     * @return true in case the sensor exists, false otherwise.
     **/
    boolean areaSensorExistsInRepository(String sensorID) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        return value.isPresent();
    }

    public List<AreaSensor> findByGeoAreaSensorsByID(Long geoAreaId) {
        return areaSensorRepository.findByGeographicAreaId(geoAreaId);
    }

    public boolean remove(AreaSensor areaSensor) {
        Optional<AreaSensor> areaSensor2 = areaSensorRepository.findById(areaSensor.getId());
        if (areaSensor2.isPresent()) {
            areaSensorRepository.delete(areaSensor);
            return true;
        }
        return false;
    }


    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     *
     * @return a string of the sensors contained in the list.
     */

    public String buildString(List<AreaSensor> areaSensors) {
        StringBuilder result = new StringBuilder(new StringBuilder(BUILDER));
        if (areaSensors.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (AreaSensor as : areaSensors) {
            result.append(as.getId()).append(") Name: ").append(as.getName()).append(" | ");
            result.append("Type: ").append(as.getSensorTypeName()).append(" | ")
                    .append(as.printActive()).append("\n");
        }
        result.append(BUILDER);
        return result.toString();
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     *
     * @param id the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public AreaSensor getById(String id) {
        Optional<AreaSensor> value = areaSensorRepository.findById(id);
        return value.orElse(null);
    }

    public AreaSensor updateSensor(AreaSensor areaSensor) {
        return areaSensorRepository.save(areaSensor);
    }


    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param areaSensorToAdd is the sensor we want to addWithoutPersisting to the sensorList.
     * @return true if sensor was successfully added to the AreaSensorList, false otherwise.
     */

    public boolean addAreaSensorToDb(AreaSensor areaSensorToAdd) {
        AreaSensor areaSensor = areaSensorRepository.findByName(areaSensorToAdd.getName());
        if (areaSensor == null) {
            areaSensorRepository.save(areaSensorToAdd);
            return true;
        }
        return false;
    }


    public AreaSensor createAreaSensor(String id, String name, String sensorName, String sensorUnit, Local local, Date dateStartedFunctioning,
                                       Long geographicAreaId) {

        SensorType sensorType = getTypeSensorByName(sensorName, sensorUnit);
        if (sensorType != null) {
            return new AreaSensor(id, name, sensorType, local, dateStartedFunctioning, geographicAreaId);
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    private SensorType getTypeSensorByName(String name, String unit) {
        Logger logger = LogUtils.getLogger("SensorTypeLogger", "resources/logs/sensorTypeLogHtml.html", Level.FINE);
        Optional<SensorType> value = sensorTypeRepository.findByName(name);
        if (!(value.isPresent())) {
            logger.fine("The Sensor Type " + name + " does not yet exist in the Data Base. Please create the Sensor" +
                    "Type first.");
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            return value.orElseGet(() -> new SensorType(name, unit));
        }

    }
}