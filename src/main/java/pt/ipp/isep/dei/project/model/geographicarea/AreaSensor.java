package pt.ipp.isep.dei.project.model.geographicarea;

import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingUtils;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensortype.SensorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Represents a Sensor.
 * It is defined by a name, type of sensor, localization and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
@Entity
public class AreaSensor {

    @Id
    private String id;
    private String name;

    //TODO cant point to different aggregate
    @ManyToOne
    @JoinColumn(name = "type_sensor_id")
    private SensorType sensorType;

    @Embedded
    @JoinColumn(name = "local_id")
    private Local local;

    @Temporal(TemporalType.DATE)
    private Date dateStartedFunctioning;

    private Long geographicAreaId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "AreaReading")
    private final List<Reading> areaReadings;

    private boolean active;

    /**
     * Empty constructor to import Sensors from a XML file.
     */
    public AreaSensor() {
        this.active = true;
        areaReadings = new ArrayList<>();
    }

    /**
     * Sensor() constructor with 5 parameters.
     *
     * @param id                     is the id we want to set to the Sensor.
     * @param name                   is the name we want to set to the Sensor.
     * @param sensorType             is the Type of the Sensor.
     * @param local                  is the Local of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public AreaSensor(String id, String name, SensorType sensorType, Local local, Date dateStartedFunctioning,
                      Long geographicAreaId) {
        setId(id);
        setName(name);
        this.sensorType = sensorType;
        this.local = local;
        this.dateStartedFunctioning = dateStartedFunctioning;
        this.active = true;
        this.geographicAreaId = geographicAreaId;
        areaReadings = new ArrayList<>();
    }

    /**
     * Setter: Id
     *
     * @param id is the id we want to set to the sensor.
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * Setter: name
     *
     * @param name is the name we want to set to the sensor.
     */
    void setName(String name) {
        if (isSensorNameValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    public Date getDateStartedFunctioning() {
        return dateStartedFunctioning;
    }

    /**
     * Getter: Id
     *
     * @return a string that represents the name of the sensor.
     */
    public String getId() {
        return (this.id);
    }

    public void setGeographicAreaId(Long geographicAreaId) {
        this.geographicAreaId = geographicAreaId;
    }

    public Long getGeographicAreaId() {
        return this.geographicAreaId;
    }

    public List<Reading> getReadings() {
        return areaReadings;
    }

    /**
     * Getter: name
     *
     * @return a string that represents the name of the sensor.
     */
    public String getName() {
        return (this.name);
    }

    /**
     * Getter: type sensor
     *
     * @return the Type of the Sensor.
     */
    public SensorType getSensorType() {
        return (this.sensorType);
    }

    /**
     * Getter: local
     *
     * @return the Local of the Sensor.
     */
    public Local getLocal() {
        return (this.local);
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Settter: sets the sensor active
     */
    public void setActive(boolean status) {
        this.active = status;
    }

    /**
     * Method to activate an deactivated sensor, and vice versa
     *
     * @return active or not
     */
    public boolean deactivateSensor() {
        if (isActive()) {
            this.active = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to restrain input name so they cant be null or empty.
     *
     * @param name name inserted by user
     * @return will return true if the name is valid or it will throw an exception if Invalid
     */
    private boolean isSensorNameValid(String name) {
        return (name != null && !name.isEmpty());
    }


    /**
     * Method that returns the distance between the sensor and the house.
     *
     * @param house is the house we want to calculate the distance to.
     * @return a double that represents the distance between the house and the sensor.
     */
    double getDistanceToHouse(House house) {
        Local l = house.getLocation();
        return this.local.getLinearDistanceBetweenLocalsInKm(l);
    }

    /**
     * Method to print details that are required for a Sensor to be different from another Sensor (equals -
     * name, type area and local).
     *
     * @return returns a string with Sensor Parameters
     */
    public String buildString() {
        String result;

        result = this.name + ", " + this.sensorType.getName() + ", " +
                this.local.getLatitude() + "º lat, " + this.local.getLongitude() + "º long \n";
        return result;
    }

    /**
     * Method to print info if a sensor is active or not.
     */
    String printActive() {
        if (!this.active) {
            return "Deactivated";
        }
        return "Active";
    }

    /**
     * This method receives a String that corresponds to the reading's sensor ID and a Date that
     * corresponds to the reading's date, and checks that a reading with those characteristics
     * exists in the repository.
     *
     * @param date reading date
     * @return true in case the reading exists in the repository, false otherwise.
     **/
    boolean readingWithGivenDateExists(Date date) {
        for (Reading r : this.areaReadings) {
            Date tempDate = r.getDate();
            if (date.equals(tempDate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will receive a Reading and will try to add that reading
     * to the Area Sensor list of readings.
     * It will check if the reading happened during the Area sensor's
     * date of functioning and if the reading already exists.
     *
     * @return true in case the reading is added, false otherwise.
     **/
    public boolean addReading(Reading reading) {
        Date readingDate = reading.getDate();
        if ((readingDate.equals(dateStartedFunctioning) || readingDate.after(dateStartedFunctioning)) && (!readingWithGivenDateExists(readingDate))) {
            return this.areaReadings.add(reading);
        }
        return false;
    }

    /**
     * US630
     * This method joins a lot of other methods used to fulfil the US 630 (As a Regular User,
     * I want to get the last coldest day (lower maximum temperature) in the house area in a given period) and
     * it returns a Date within an interval from a AreaReadingList that represents the last coldest day in the
     * given period (lower maximum temperature).
     *
     * @param initialDate is the Initial Date of the period.
     * @param finalDate   is the Final Date of the period.
     * @return a Reading that represents the Last Coldest Day in a Given Period (Lower Maximum Temperature).
     */
    public Date getLastColdestDayInGivenInterval(Date initialDate, Date finalDate) {

        List<Reading> readingsBetweenDates = getReadingListBetweenDates(initialDate, finalDate);

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
    private List<Reading> getReadingListBetweenDates(Date initialDate, Date finalDate) {
        List<Reading> finalList = new ArrayList<>();
        List<Reading> result = getReadings();
        for (Reading r : result) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                finalList.add(r);
            }
        }
        return finalList;
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
    public double getAverageReadingsBetweenDates(Date minDate, Date maxDate) {
        List<Reading> sensorReadingsBetweenDates = getReadingListBetweenDates(minDate, maxDate);
        if (sensorReadingsBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        return getSensorReadingAverageValue(sensorReadingsBetweenDates);
    }

    static double getSensorReadingAverageValue(List<Reading> sensorReadingsBetweenDates) {
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

    public Date getFirstHottestDayInGivenPeriod(Date minDate, Date maxDate) {

        if (this.areaReadings.isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: No temperature readings available in given period.");
        }
        double maxTemperature = ReadingUtils.getMaxValue(daysWithReadings, areaReadings);
        return ReadingUtils.getFirstDayForGivenTemperature(maxTemperature, daysWithReadings, areaReadings);
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
//TODO Delete method, daysProcessed was never used and caused untested condition, which was impossible to test
//    List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {
//
//        List<Date> daysWithReadings = new ArrayList<>();
//        List<Date> daysProcessed = new ArrayList<>();
//
//        Date startDate = ReadingUtils.getFirstSecondOfDay(dayMin);
//        Date endDate = ReadingUtils.getLastSecondOfDay(dayMax);
//
//        for (int i = 0; i < areaReadings.size(); i++) {
//            Date currentReadingDate = ReadingUtils.getValueDate(areaReadings, i);
//            if (ReadingUtils.isReadingDateBetweenTwoDates(currentReadingDate, startDate, endDate)) {
//
//                Date readingDay = ReadingUtils.getFirstSecondOfDay(currentReadingDate);
//
//                if (!daysProcessed.contains(readingDay)) {
//                    daysProcessed.add(readingDay);
//                    daysWithReadings.add(currentReadingDate);
//                }
//            }
//        }
//        return daysWithReadings;
//    }

    List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {

        List<Date> daysWithReadings = new ArrayList<>();

        Date startDate = ReadingUtils.getFirstSecondOfDay(dayMin);
        Date endDate = ReadingUtils.getLastSecondOfDay(dayMax);

        for (int i = 0; i < areaReadings.size(); i++) {
            Date currentReadingDate = ReadingUtils.getValueDate(areaReadings, i);
            if (ReadingUtils.isReadingDateBetweenTwoDates(currentReadingDate, startDate, endDate)) {
                daysWithReadings.add(currentReadingDate);
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
    public Date getDateHighestAmplitudeBetweenDates(Date minDate, Date maxDate) {
        List<Reading> daysWithReadings = getReadingListBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings available.");
        }

        Date dateMaxAmplitude = daysWithReadings.get(0).getDate();

        double maxAmplitude = getAmplitudeValueFromDate(dateMaxAmplitude);

        for (int i = 1; i < daysWithReadings.size(); i++) {
            Date day = daysWithReadings.get(i).getDate();
            double amplitudeTemperature = getAmplitudeValueFromDate(day);

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
    public double getAmplitudeValueFromDate(Date date) {
        List<Reading> daySensorReadings = getReadings();
        List<Double> specificDayValues = ReadingUtils.getValuesOfSpecificDayReadings(daySensorReadings, date);

        double maxTemp = Collections.max(specificDayValues);
        double lowestTemp = Collections.min(specificDayValues);
        return maxTemp - lowestTemp;
    }

    public Double getReadingValueOnGivenDay(Date date) {
        List<Reading> readingsBetweenDates = getReadingListBetweenDates(date, date);
        List<Double> reading = ReadingUtils.getValuesOfSpecificDayReadings(readingsBetweenDates, date);
        return Collections.max(reading);
    }

    /**
     * This method receives a Date and checks if the Area Sensor was active
     * at the time of the given date.
     *
     * @param date given date
     * @return true in case the Area Sensor was active at the time of the given date, false otherwise.
     **/
    boolean activeDuringDate(Date date) {
        return this.dateStartedFunctioning.equals(date) || this.dateStartedFunctioning.before(date);
    }

    /**
     * This method returns the sensor type name.
     *
     * @return he sensor type name.
     **/
    String getSensorTypeName() {
        return this.sensorType.getName();
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof AreaSensor)) {
            return false;
        }
        AreaSensor areaSensor = (AreaSensor) testObject;
        return this.getName().equals(areaSensor.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}