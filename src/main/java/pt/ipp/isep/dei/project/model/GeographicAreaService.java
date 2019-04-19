package pt.ipp.isep.dei.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.sensor.AreaSensor;
import pt.ipp.isep.dei.project.model.sensor.AreaSensorService;
import pt.ipp.isep.dei.project.model.sensor.Reading;
import pt.ipp.isep.dei.project.repository.AreaSensorRepository;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;

import java.util.*;
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

    public GeographicAreaService(GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository, AreaSensorRepository areaSensorRepository) {
        this.geographicAreaRepository = geographicAreaRepository;
        this.areaTypeRepository = areaTypeRepository;
        this.areaSensorRepository = areaSensorRepository;
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

    /**
     * Method to print a Whole Geographic Area List.
     * It will print the attributes needed to check if a GA is different from another GA
     * (name, type of GA and Localization)
     *
     * @return a string with the names of the geographic areas
     */
    public String buildStringRepository(List<GeographicArea> geographicAreas) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (geographicAreas.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (GeographicArea ga : geographicAreas) {
            result.append(ga.getId()).append(") Name: ").append(ga.getName()).append(" | ");
            result.append("Type: ").append(ga.getAreaType().getName()).append(" | ");
            result.append("Latitude: ").append(ga.getLocal().getLatitude()).append(" | ");
            result.append("Longitude: ").append(ga.getLocal().getLongitude()).append("\n");
        }
        result.append("---------------\n");
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
        AreaType areaType = getAreaTypeByName(areaTypeName);
        areaTypeRepository.save(areaType);
        return new GeographicArea(newName, areaType, length, width, local);
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
    public AreaType getAreaTypeByName(String name) {
        Optional<AreaType> value = areaTypeRepository.findByName(name);
        if (value.isPresent()) {
            return value.get();
        }
        return new AreaType(name);
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
    public boolean sensorFromRepositoryIsActive(String sensorID, Date date) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        if (value.isPresent()) {
            AreaSensor areaSensor = value.get();
            Date startDate = areaSensor.getDateStartedFunctioning();
            if (date.equals(startDate) || date.after(startDate)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This method receives a sensor ID, checks if that sensor exists in the repository.
     *
     * @param sensorID String of sensor ID
     * @return true in case the sensor exists, false otherwise.
     **/
    boolean sensorExistsInRepository(String sensorID) {
        Optional<AreaSensor> value = areaSensorRepository.findById(sensorID);
        return value.isPresent();
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
    public boolean addAreaReadingToRepository(AreaSensor sensor, Double readingValue, Date readingDate, String unit, Logger logger, AreaSensorService areaSensorService) {
        if (sensor != null && sensorExistsInRepository(sensor.getId())) {
            if (sensorFromRepositoryIsActive(sensor.getId(), readingDate)) {
                if (sensor.readingExists(readingDate)) {
                    logger.warning("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                            + sensor.getId() + " wasn't added because it already exists.");
                    return false;
                }
                Reading reading = new Reading(readingValue, readingDate, unit, sensor.getId());
                sensor.getAreaReadings().add(reading);
                areaSensorService.updateSensor(sensor);
                return true;
            }
            logger.warning("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                    + sensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
            return false;
        }
        logger.warning("The reading " + readingValue + " " + unit + " from " + readingDate + " because a sensor with that ID wasn't found.");
        return false;
    }
}
