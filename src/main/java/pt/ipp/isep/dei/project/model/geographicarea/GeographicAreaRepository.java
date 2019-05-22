package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controllercli.utils.LogUtils;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaWebDTO;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.dto.mappers.GeographicAreaMapper;
import pt.ipp.isep.dei.project.dto.mappers.ReadingMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.repository.GeographicAreaCrudRepo;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaRepository {
    @Autowired
    private GeographicAreaCrudRepo geographicAreaCrudRepo;


    private static final String BUILDER = "---------------\n";
    private static final String THE_READING = "The reading ";
    private static final String FROM = " from ";

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public List<GeographicArea> getAll() {
        return geographicAreaCrudRepo.findAll();
    }

    //WEB CONTROLLER//
    //TODO: Replace previous getAll()
    public List<GeographicAreaDTO> getAllDTO() {
        List<GeographicArea> list = geographicAreaCrudRepo.findAll();
        List<GeographicAreaDTO> finalList = new ArrayList<>();
        for (GeographicArea ga : list) {
            GeographicAreaDTO gaDTO = GeographicAreaMapper.objectToDTOWithMother(ga);
            finalList.add(gaDTO);
        }
        return finalList;
    }

    public List<GeographicAreaWebDTO> getAllDTOWebInformation() {
        List<GeographicArea> list = geographicAreaCrudRepo.findAll();
        List<GeographicAreaWebDTO> finalList = new ArrayList<>();
        for (GeographicArea ga : list) {
            GeographicAreaWebDTO gaDTO = GeographicAreaMapper.objectToWebDTO(ga);
            finalList.add(gaDTO);
        }
        return finalList;
    }

    public GeographicAreaDTO getDTOById(long Id) {
        Optional<GeographicArea> aux = geographicAreaCrudRepo.findById(Id);
        if (!aux.isPresent()) {
            throw new IllegalArgumentException("Geographic Area not found - 404");
        }
        return GeographicAreaMapper.objectToDTO(aux.get());
    }

    public GeographicAreaDTO getDTOByIdWithMother(long Id) {
        Optional<GeographicArea> aux = geographicAreaCrudRepo.findById(Id);
        if (!aux.isPresent()) {
            throw new IllegalArgumentException("Geographic Area not found - 404");
        }
        return GeographicAreaMapper.objectToDTOWithMother(aux.get());
    }

    public boolean addAndPersistDTO(GeographicAreaDTO geographicAreaToAddDTO) {
        List<GeographicArea> geographicAreas = getAll();
        GeographicArea geographicAreaToAdd = GeographicAreaMapper.dtoToObject(geographicAreaToAddDTO);
        if (!(geographicAreas.contains(geographicAreaToAdd))) {
            geographicAreas.add(geographicAreaToAdd);
            geographicAreaCrudRepo.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    void deleteFromDatabase(GeographicAreaDTO geographicAreaDTO) {
        geographicAreaCrudRepo.deleteById(geographicAreaDTO.getId());
    }

    public void updateAreaDTO(GeographicAreaDTO areaDTO) {
        GeographicArea area = GeographicAreaMapper.dtoToObject(areaDTO);
        geographicAreaCrudRepo.save(area);
    }

    public boolean updateAreaDTOWithMother(GeographicAreaDTO areaDTO) {
        GeographicArea area = GeographicAreaMapper.dtoToObjectWithMother(areaDTO);
        if (geographicAreaCrudRepo.findAll().contains(area)) {
            geographicAreaCrudRepo.save(area);
            return true;
        }
        return false;
    }

    public boolean addSensorDTO(GeographicAreaDTO geographicAreaDTO, AreaSensorDTO areaSensorDTO) {
        return geographicAreaDTO.addSensor(areaSensorDTO);
    }

    /**
     * Acessory method for US011: Removes area sensor DTO.
     *
     * @param geographicAreaDTO geo area DTO for finding correct area sensor
     * @param areaSensorID      area sensor ID for removing correct sensor.
     * @return method for removing area sensor by id from GeographicAreaDTO class.
     */
    public boolean removeSensorDTO(GeographicAreaDTO geographicAreaDTO, String areaSensorID) {
        return geographicAreaDTO.removeSensor(areaSensorID);
    }

    public boolean deactivateSensorDTO(GeographicAreaDTO geographicAreaDTO, AreaSensorDTO areaSensorDTO) {
        if (geographicAreaDTO.removeSensor(areaSensorDTO.getSensorId())) {
            areaSensorDTO.setActive(false);
            geographicAreaDTO.addSensor(areaSensorDTO);
            return true;
        }
        return false;
    }


    public boolean addDaughterDTO(GeographicAreaDTO motherDTO, GeographicAreaDTO daughterDTO) {
        return motherDTO.addDaughter(daughterDTO);
    }

    //WEB CONTROLLER END //

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
            geographicAreaCrudRepo.save(geographicAreaToAdd);
            return true;
        }
        return false;
    }

    public void updateGeoArea(GeographicArea area) {
        geographicAreaCrudRepo.save(area);
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
            result.append("Type: ").append(ga.getAreaTypeID()).append(" | ");
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
        return new GeographicArea(newName, areaTypeName, length, width, local);
    }

    /**
     * Method that returns a GeographicAreaList with a given type.
     *
     * @param typeAreaName is the type of the area we want to get all the geographicAreas.
     * @return a GeographicAreaList with a given type.
     */
    public List<GeographicArea> getGeoAreasByType(String typeAreaName) {
        return geographicAreaCrudRepo.findAllByAreaTypeID(typeAreaName);
    }

    /**
     * method that returns a area sensor DTO found by id
     *
     * @param idSensor sensor id
     * @param idArea   area id
     * @return area sensor dto with the selected id
     */
    public AreaSensorDTO getAreaSensorByID(String idSensor, long idArea) {
        GeographicAreaDTO geographicArea = getDTOById(idArea);
        for (AreaSensorDTO as : geographicArea.getSensors()) {
            String asString = as.getSensorId();
            if (asString.equals(idSensor)) {
                return as;
            }
        }
        throw new IllegalArgumentException(("Area Sensor not found"));
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
    public GeographicArea getByID(long id) {
        Optional<GeographicArea> value = geographicAreaCrudRepo.findById(id);
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


    //METHODS FROM AREA SENSOR REPOSITORY


    /**
     * This method will receive a list of readings, a string of a path to a log file,
     * and a geographic area service and will try to add readings to the given sensors
     * in the given geographic area from the repository.
     *
     * @param readingDTOS a list of readings
     * @param logPath     string of a log file path
     * @return the number of readings added
     **/
    public int addReadingsToGeographicAreaSensors(List<ReadingDTO> readingDTOS, String logPath) {
        List<Reading> readings = ReadingMapper.readingDTOsToReadings(readingDTOS);
        Logger logger = LogUtils.getLogger("areaReadingsLogger", logPath, Level.FINE);
        int addedReadings = 0;
        List<String> sensorIds = ReadingUtils.getSensorIDs(readings);
        for (String sensorID : sensorIds) {
            List<Reading> subArray = ReadingUtils.getReadingsBySensorID(sensorID, readings);
            addedReadings += addAreaReadings(sensorID, subArray, logger);
        }
        return addedReadings;
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
    int addAreaReadings(String sensorID, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        try {
            GeographicArea geographicArea = getGeographicAreaContainingSensorWithGivenId(sensorID);
            AreaSensor areaSensor = geographicArea.getAreaSensorByID(sensorID);
            addedReadings = addReadingsToAreaSensor(areaSensor, readings, logger);
            geographicAreaCrudRepo.save(geographicArea);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
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
    GeographicArea getGeographicAreaContainingSensorWithGivenId(String sensorID) {
        List<GeographicArea> geographicAreas = geographicAreaCrudRepo.findAll();
        for (GeographicArea ga : geographicAreas) {
            List<AreaSensor> areaSensors = ga.getSensors();
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
    int addReadingsToAreaSensor(AreaSensor areaSensor, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (areaSensor.readingWithGivenDateExists(readingDate)) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because it already exists.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
            } else if (!areaSensor.activeDuringDate(readingDate)) {
                String message = THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.";
                logger.fine(message);
                LogUtils.closeHandlers(logger);
            } else {
                areaSensor.addReading(r);
                addedReadings++;
            }
        }
        return addedReadings;
    }
}
