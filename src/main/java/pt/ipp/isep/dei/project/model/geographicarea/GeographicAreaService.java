package pt.ipp.isep.dei.project.model.geographicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.areatype.AreaType;
import pt.ipp.isep.dei.project.model.house.House;
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
        AreaType areaType = getAreaTypeByName(areaTypeName);
        if (areaType != null) {
            areaTypeRepository.save(areaType);
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
    private AreaType getAreaTypeByName(String name) {
        Logger logger = getLogger("resources/logs/areaTypeLogs.log");
        Optional<AreaType> value = areaTypeRepository.findByName(name);

        if (!(value.isPresent())) {
            logger.warning("The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.");
            return null;
        } else {
            return value.orElseGet(() -> new AreaType(name));
        }
    }

    /**
     * This method creates a Logger.
     *
     * @param logPath log file path.
     * @return object of class Logger.
     **/
    private Logger getLogger(String logPath) {
        Logger logger = Logger.getLogger(GeographicAreaService.class.getName());
        try {
            CustomFormatter myFormat = new CustomFormatter();
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(myFormat);
            logger.setLevel(Level.WARNING);
        } catch (IOException io) {
            io.getMessage();
        }
        return logger;
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
     * This method returns the sensor closest to the house. If more than one sensor is close to it,
     * the one with the most recent reading should be used.
     *
     * @param sensorType the type of sensor to check
     * @return the closest sensor.
     */
    public AreaSensor getClosestAreaSensorOfGivenType(List<AreaSensor> areaSensors, String sensorType, House house) {

        AreaSensor areaSensor;

        List<AreaSensor> minDistSensor = new ArrayList<>();


        AreaSensor areaSensorError = new AreaSensor("RF12345", "EmptyList", new SensorType("temperature", " " +
                ""), new Local(0, 0, 0), new GregorianCalendar(1900, Calendar.FEBRUARY,
                1).getTime(), 2356L);

        List<AreaSensor> sensorsOfGivenType = getAreaSensorsOfGivenType(areaSensors, sensorType);

        if (!sensorsOfGivenType.isEmpty()) {
            double minDist = getMinDistanceToSensorOfGivenType(sensorsOfGivenType, house);

            minDistSensor = getAreaSensorsByDistanceToHouse(sensorsOfGivenType, house, minDist);
        }
        if (minDistSensor.isEmpty()) {
            return areaSensorError;
        }
        if (minDistSensor.size() > 1) {

            areaSensor = getMostRecentlyUsedAreaSensor(minDistSensor);
        } else {
            areaSensor = minDistSensor.get(0);
        }
        return areaSensor;
    }

    private AreaSensor getMostRecentlyUsedAreaSensor(List<AreaSensor> areaSensors) {
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty.");
        }
        List<AreaSensor> areaSensors2 = getAreaSensorsWithReadings(areaSensors);
        if (areaSensors2.isEmpty()) {
            throw new IllegalArgumentException("The sensor list has no readings available.");
        }

        AreaSensor mostRecent = areaSensors2.get(0);
        List<Reading> mostRecentSensorReadings = mostRecent.getAreaReadings();

        Reading recentReading = ReadingUtils.getMostRecentReading(mostRecentSensorReadings);
        Date recent = recentReading.getDate();


        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = new ArrayList<>();

            Date test = ReadingUtils.getMostRecentReadingDate(sensorReadings);
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }

    private List<AreaSensor> getAreaSensorsOfGivenType(List<AreaSensor> areaSensors, String sensorType) {
        List<AreaSensor> sensorsOfGivenType = new ArrayList<>();
        for (AreaSensor aS : areaSensors) {
            if (aS.getSensorType().getName().equals(sensorType)) {
                sensorsOfGivenType.add(aS);
            }

        }
        return sensorsOfGivenType;
    }

    /**
     * Method that goes through the sensor list and returns a list of those which
     * have readings. The method throws an exception in case the sensorList is empty.
     *
     * @return AreaSensorList of every sensor that has readings. It will return an empty list in
     * case the original list was empty from readings.
     */
    private List<AreaSensor> getAreaSensorsWithReadings(List<AreaSensor> areaSensors) {
        List<AreaSensor> finalList = new ArrayList<>();
        if (areaSensors.isEmpty()) {
            throw new IllegalArgumentException("The sensor list is empty");
        }
        for (AreaSensor s : areaSensors) {
            List<Reading> sensorReadings = s.getAreaReadings();

            if (!sensorReadings.isEmpty()) {
                finalList.add(s);
            }
        }
        return finalList;
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


    /**
     * This method receives a house and the distance of the sensor closest to it,
     * goes through the sensor list and returns the sensors closest to house.
     *
     * @param house   the House of the project
     * @param minDist the distance to the sensor
     * @return AreaSensorList with sensors closest to house.
     **/
    private List<AreaSensor> getAreaSensorsByDistanceToHouse(List<AreaSensor> areaSensors, House house, double minDist) {
        List<AreaSensor> finalList = new ArrayList<>();
        for (AreaSensor s : areaSensors) {
            if (Double.compare(minDist, s.getDistanceToHouse(house)) == 0) {
                finalList.add(s);
            }
        }
        return finalList;
    }

    public AreaSensor createAreaSensor(String id, String name, String sensorName, String sensorUnit, Local local, Date dateStartedFunctioning,
                                       Long geographicAreaId) {

        SensorType sensorType = getTypeSensorByName(sensorName, sensorUnit);
        sensorTypeRepository.save(sensorType);

        return new AreaSensor(id, name, sensorType, local, dateStartedFunctioning, geographicAreaId);
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    private SensorType getTypeSensorByName(String name, String unit) {
        Optional<SensorType> value = sensorTypeRepository.findByName(name);
        return value.orElseGet(() -> new SensorType(name, unit));
    }

    private double getMinDistanceToSensorOfGivenType(List<AreaSensor> areaSensors, House house) {
        List<Double> arrayList = getSensorsDistanceToHouse(house, areaSensors);
        return Collections.min(arrayList);
    }

    /**
     * Goes through the sensor list, calculates sensors distance to house and
     * returns values in ArrayList.
     *
     * @param house to calculate closest distance
     * @return List of sensors distance to house
     */
    private List<Double> getSensorsDistanceToHouse(House house, List<AreaSensor> areaSensors) {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (AreaSensor areaSensor : areaSensors) {
            arrayList.add(house.calculateDistanceToSensor(areaSensor));
        }
        return arrayList;
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
                    return false;
                }
                Reading reading = new Reading(readingValue, readingDate, unit, sensor.getId());
                sensor.getAreaReadings().add(reading);
                updateSensor(sensor);
                return true;
            }
            logger.fine("The reading " + readingValue + " " + unit + " from " + readingDate + " with a sensor ID "
                    + sensor.getId() + " wasn't added because the reading is from before the sensor's starting date.");
            return false;
        }
        logger.fine("The reading " + readingValue + " " + unit + " from " + readingDate + " because a sensor with that ID wasn't found.");
        return false;
    }

    /**
     * US630
     * This method joins a lot of other methods used to fulfil the US 630 (As a Regular User,
     * I want to getDB the last coldest day (lower maximum temperature) in the house area in a given period) and
     * it returns a Date within an interval from a AreaReadingList that represents the last coldest day in the
     * given period (lower maximum temperature).
     *
     * @param initialDate is the Initial Date of the period.
     * @param finalDate   is the Final Date of the period.
     * @return a Reading that represents the Last Coldest Day in a Given Period (Lower Maximum Temperature).
     */
    public Date getLastColdestDayInGivenInterval(AreaSensor areaSensor, Date initialDate, Date finalDate) {

        List<Reading> readingsBetweenDates = getReadingListBetweenDates(areaSensor, initialDate, finalDate);

        if (readingsBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("No readings available in the chosen interval.");
        }
        List<Reading> listOfMaxValuesForEachDay = ReadingUtils.getListOfMaxValuesForEachDay(readingsBetweenDates);

        double minValueInList = ReadingUtils.getMinValueInReadingList(listOfMaxValuesForEachDay);

        List<Reading> readingsWithSpecificValue = ReadingUtils.getReadingListOfReadingsWithSpecificValue(listOfMaxValuesForEachDay, minValueInList);

        return ReadingUtils.getMostRecentReading(readingsWithSpecificValue).getDate();
    }

    /**
     * This method filters a AreaReadingList and returns the AreaReadingList but within an interval of given dates.
     *
     * @param initialDate is the initial date of the interval.
     * @param finalDate   is the final date of the interval.
     * @return a AreaReadingList that represents the initial AreaReadingList but only with readings within the given interval.
     */
    private List<Reading> getReadingListBetweenDates(AreaSensor areaSensor, Date initialDate, Date finalDate) {
        List<Reading> finalList = new ArrayList<>();
        List<Reading> result = areaSensor.getAreaReadings();
        for (Reading r : result) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(r);
            }
        }
        return result;
    }


    /**
     * Method that gives the Average of readings between two dates (given days)
     * It calculates the average of all days, considering the average of each day.
     * It will throw an IllegalArgumentException if there are no readings between the selected dates
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the average of all values in the reading list between the two given dates
     * @author Daniela (US623)
     */
    public double getAverageReadingsBetweenDates(AreaSensor areaSensor, Date minDate, Date maxDate) {
        List<Reading> sensorReadingsBetweenDates = getReadingListBetweenDates(areaSensor, minDate, maxDate);
        if (sensorReadingsBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        List<Double> avgDailyValues = new ArrayList<>();
        for (int i = 0; i < sensorReadingsBetweenDates.size(); i++) {
            Date day = sensorReadingsBetweenDates.get(i).getDate();
            List<Double> specificDayValues = ReadingUtils.getValuesOfSpecificDayReadings(sensorReadingsBetweenDates, day);
            double avgDay = ReadingUtils.getAvgFromList(specificDayValues);
            avgDailyValues.add(avgDay);
        }
        return ReadingUtils.getAvgFromList(avgDailyValues);
    }

    /**
     * Method that gives the Date with the First Hottest Day Reading in given period
     * It will throw an IllegalArgumentException if there are no available readings between the dates
     * This method runs the array of dates in the given period, storing the first hottest temperature Date,
     * only overwriting if there's a day with a higher temperature, ensuring the final return will be
     * the first hottest day in period.
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the Date with First Hottest Day in given period.
     * @author Nuno (US631)
     */

    public Date getFirstHottestDayInGivenPeriod(AreaSensor areaSensor, Date minDate, Date maxDate) {
        List<Reading> sensorReadings = areaSensor.getAreaReadings();
        if (sensorReadings.isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(areaSensor, minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: No temperature readings available in given period.");
        }
        double maxTemperature = ReadingUtils.getMaxValue(daysWithReadings, sensorReadings);
        return ReadingUtils.getFirstDayForGivenTemperature(maxTemperature, daysWithReadings, sensorReadings);
    }

    /**
     * Returns return a list with every day with readings between two given dates.
     * Returns the date of the first reading for each day (no duplicated days - same day, month, year).
     *
     * @param dayMin start date given by user, will be the start of the  date interval;
     * @param dayMax end date given by user, will be the end of the date interval;
     * @return list of dates of readings between the given dates
     * @author Daniela - US623 & US633
     */
    List<Date> getDaysWithReadingsBetweenDates(AreaSensor areaSensor, Date dayMin, Date dayMax) {
        List<Reading> sensorReadings = areaSensor.getAreaReadings();

        List<Date> daysWithReadings = new ArrayList<>();
        List<Date> daysProcessed = new ArrayList<>();

        Date startDate = ReadingUtils.getFirstSecondOfDay(dayMin);
        Date endDate = ReadingUtils.getLastSecondOfDay(dayMax);

        for (int i = 0; i < sensorReadings.size(); i++) {
            Date currentReadingDate = ReadingUtils.getValueDate(sensorReadings, i);
            if (ReadingUtils.isReadingDateBetweenTwoDates(currentReadingDate, startDate, endDate)) {

                Date readingDay = ReadingUtils.getFirstSecondOfDay(currentReadingDate);

                if (!daysProcessed.contains(readingDay)) {
                    daysProcessed.add(readingDay);
                    daysWithReadings.add(currentReadingDate);
                }
            }
        }
        return daysWithReadings;
    }

    /**
     * Method that gives the Date with the Highest Amplitude of readings between two dates (given days).
     * If there is more than one day with the same temperature amplitude, the return will be the most recent day.
     * It will throw an IllegalArgumentException if there are no readings between the selected dates.
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the Date with Highest Amplitude of all values in the reading list between the two given dates
     * @author Daniela (US633)
     */
    public Date getDateHighestAmplitudeBetweenDates(AreaSensor areaSensor, Date minDate, Date maxDate) {

        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(areaSensor, minDate, maxDate);

        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings available.");
        }

        Date dateMaxAmplitude = daysWithReadings.get(0);

        double maxAmplitude = getAmplitudeValueFromDate(areaSensor, dateMaxAmplitude);

        for (int i = 1; i < daysWithReadings.size(); i++) {
            Date day = daysWithReadings.get(i);
            double amplitudeTemperature = getAmplitudeValueFromDate(areaSensor, day);

            if (maxAmplitude < amplitudeTemperature) {
                maxAmplitude = amplitudeTemperature;
                dateMaxAmplitude = day;
            } else if ((Double.compare(maxAmplitude, amplitudeTemperature) == 0) && (day.after(dateMaxAmplitude))) {
                dateMaxAmplitude = day;
            }
        }
        return dateMaxAmplitude;
    }

    /**
     * Method that gives the highest amplitude value on a given date
     *
     * @param date for each we want the amplitude value
     * @return highest amplitude value
     * @author Daniela (US633)
     */
    public double getAmplitudeValueFromDate(AreaSensor areaSensor, Date date) {
        List<Reading> daySensorReadings = areaSensor.getAreaReadings();
        List<Double> specificDayValues = ReadingUtils.getValuesOfSpecificDayReadings(daySensorReadings, date);

        double maxTemp = Collections.max(specificDayValues);
        double lowestTemp = Collections.min(specificDayValues);
        return maxTemp - lowestTemp;
    }


}
