package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.utils.LogUtils;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;
import pt.ipp.isep.dei.project.repository.GeographicAreaRepository;
import pt.ipp.isep.dei.project.repository.SensorTypeRepository;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Geographical Areas.
 */
@Service
public class GeographicAreaService {
    @Autowired
    private static GeographicAreaRepository geographicAreaRepository;
    @Autowired
    private static AreaTypeRepository areaTypeRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    private static final String BUILDER = "---------------\n";
    private static final String THE_READING = "The reading ";
    private static final String FROM = " from ";


    public GeographicAreaService(GeographicAreaRepository geographicAreaRepository, AreaTypeRepository areaTypeRepository, SensorTypeRepository sensorTypeRepository) {
        GeographicAreaService.geographicAreaRepository = geographicAreaRepository;
        GeographicAreaService.areaTypeRepository = areaTypeRepository;
        this.sensorTypeRepository = sensorTypeRepository;
    }

    /**
     * Method to return a list with all the Geographical Areas contained on the geographicAreaRepository
     *
     * @return a GeographicAreaList with all the Geographical Areas saved in the repository.
     */
    public List<GeographicArea> getAll() {
        return geographicAreaRepository.findAll();
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
            if (ga.isOfType(areaTypeToTest)) {
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
    AreaType getAreaTypeByName(String name, Logger logger) {
        Optional<AreaType> value = areaTypeRepository.findByName(name);
        if (!(value.isPresent())) {
            logger.fine("The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.");
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.orElseGet(() -> new AreaType(name));
        }
    }


    //METHODS FROM AREA SENSOR REPOSITORY

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
            logger.fine("The Sensor Type " + name + "with the unit " + unit + " does not yet exist in the Data Base. Please create the Sensor" +
                    "Type first.");
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.orElseGet(() -> new SensorType(name, unit));
        }
    }

    /**
     * This method will receive a list of readings, a string of a path to a log file,
     * and a geographic area service and will try to add readings to the given sensors
     * in the given geographic area from the repository.
     *
     * @param readings a list of readings
     * @param logPath  string of a log file path
     * @return the number of readings added
     **/
    public int addReadingsToGeographicAreaSensors(List<Reading> readings, String logPath) {
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
            geographicAreaRepository.save(geographicArea);
        } catch (IllegalArgumentException ill) {
            for (Reading r : readings) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " wasn't added because a sensor with the ID " + r.getSensorID() + " wasn't found.");
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
    int addReadingsToAreaSensor(AreaSensor areaSensor, List<Reading> readings, Logger logger) {
        int addedReadings = 0;
        for (Reading r : readings) {
            Date readingDate = r.getDate();
            if (areaSensor.readingWithGivenDateExists(readingDate)) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because it already exists.");
                LogUtils.closeHandlers(logger);
            } else if (!areaSensor.activeDuringDate(readingDate)) {
                logger.fine(THE_READING + r.getValue() + " " + r.getUnit() + FROM + r.getDate() + " with a sensor ID "
                        + areaSensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
                LogUtils.closeHandlers(logger);
            } else {
                areaSensor.addReading(r);
                addedReadings++;
            }
        }
        return addedReadings;
    }

    // Methods to be moved to GeographicArea-House-bridge-Service

    /**
     * This method calculates the average temperature in the house area in a given date.
     *
     * @param date is used to determine the day in which we want to calculate the average.
     * @return the average temperature value for the 24 hours of the given date.
     */
    private double getGeographicAreaAverageTemperature(Date date, House house) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date d1 = calendar.getTime(); // gets date at 00:00:00

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date d2 = calendar.getTime(); // gets date at 23:59:59

        // gets and returns average readings on the closest AreaSensor to the house
        AreaSensor houseClosestSensor = house.getMotherArea().getClosestAreaSensorOfGivenType("temperature", house);
        return getAverageReadingsBetweenFormattedDates(d1, d2, houseClosestSensor);
    }

    Double getAverageReadingsBetweenFormattedDates(Date minDate, Date maxDate, AreaSensor areaSensor) {
        List<Reading> sensorReadingsBetweenDates = getReadingListBetweenFormattedDates(minDate, maxDate, areaSensor);
        if (sensorReadingsBetweenDates.isEmpty()) {
            return Double.NaN;
        }
        return AreaSensor.getSensorReadingAverageValue(sensorReadingsBetweenDates);
    }

    List<Reading> getReadingListBetweenFormattedDates(Date initialDate, Date finalDate, AreaSensor areaSensor) {

        List<Reading> finalList = new ArrayList<>();
        List<Reading> result = areaSensor.getReadings();
        for (Reading r : result) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(r);
            }
        }
        return finalList;
    }

    public List<Reading> getReadingsBelowCategoryILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsBelowCategoryIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsBelowCategoryIIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIIICalculusTemperaturesLowerThanAverage(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category I.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 2;
        return reading.getValue() < minT;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category II.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 3;
        return reading.getValue() < minT;
    }

    /**
     * Method to check id a given reading is below the comfort temperature for category III.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIIICalculusTemperaturesLowerThanAverage(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 - 4;
        return reading.getValue() < minT;
    }

    public List<Reading> getReadingsAboveCategoryILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryICalculusUS445(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsAboveCategoryIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIICalculusUS445(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    public List<Reading> getReadingsAboveCategoryIIILimit(List<Reading> readingValues, House house) {
        List<Reading> allReadings = new ArrayList<>();
        for (Reading r : readingValues) {
            Double temperature = getGeographicAreaAverageTemperature(r.getDate(), house);
            if (!temperature.isNaN() && categoryIIICalculusUS445(r, temperature)) {
                allReadings.add(r);
            }
        }
        return allReadings;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category I.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryICalculusUS445(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 2;
        return reading.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category II.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIICalculusUS445(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 3;
        return reading.getValue() > minT;
    }

    /**
     * Method to check id a given reading is above the comfort temperature for category III.
     *
     * @param reading         - Reading to get value.
     * @param areaTemperature - outside average temperature for the given date
     * @return true if the reading is above the comfort level.
     */
    boolean categoryIIICalculusUS445(Reading reading, double areaTemperature) {
        double minT = 0.33 * areaTemperature + 18.8 + 4;
        return reading.getValue() > minT;
    }
}
