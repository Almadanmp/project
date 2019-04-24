package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.utils.LogUtils;
import pt.ipp.isep.dei.project.model.Local;
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
     * This method receives a sensor ID and a Date checks if that sensor exists in the repository
     * and if it was active at the moment of the given date.
     *
     * @param sensorID String of sensor ID
     * @param date     date to test
     * @return true in case the sensor exists and it was active during the given date, false otherwise.
     **/
    boolean sensorFromRepositoryIsActive(String sensorID, Date date) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            Date startDate = areaSensor.getDateStartedFunctioning();
            return date.equals(startDate) || date.after(startDate);
        }
        return false;
    }

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


    //METHODS FROM READING SERVICE

    /**
     * This method receives the parameters to create a reading and tries to add that
     * reading to the repository. It also receives a Logger so that it can register every
     * reading that was not added to its corresponding sensor.
     * This method will look for the area sensor in the repository by its ID.
     *
     * @param readingValue is the value of the reading we want to add.
     * @param readingDate  is the date of the reading we want to add.
     * @param unit         is the unit of the reading we want to add.
     * @return true in case the reading was added false otherwise.
     */
    //TODO reading should be created in a method on AreaSensor and added there
    public boolean addAreaReadingToRepository(AreaSensor sensor, Double readingValue, Date readingDate, String unit, Logger logger) {
        if (sensor != null && areaSensorExistsInRepository(sensor.getId())) {
            if (sensorFromRepositoryIsActive(sensor.getId(), readingDate)) {
                if (sensor.readingExists(readingDate)) {
                    logger.fine("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                            + sensor.getId() + " wasn't added because it already exists.");
                    LogUtils.closeHandlers(logger);
                    return false;
                }
                Reading reading = new Reading(readingValue, readingDate, unit, sensor.getId());
                sensor.getAreaReadings().add(reading);
                updateSensor(sensor);
                return true;
            }
            logger.fine("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                    + sensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
            LogUtils.closeHandlers(logger);
            return false;
        }
        logger.fine("The reading " + readingValue + " " + unit + " from " + readingDate + " because a sensor with that ID wasn't found.");
        LogUtils.closeHandlers(logger);
        return false;
    }


}
