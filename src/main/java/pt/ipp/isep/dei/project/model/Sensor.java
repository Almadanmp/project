package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Represents a Sensor.
 * It is defined by a name, type of sensor, localization and the date it started functioning.
 * It contains a list with one or more weather readings.
 */
//@Entity
public class Sensor {
    //  @Id
    private String id;
    private String name;
    //@ManyToOne
    private TypeSensor typeSensor;
    //@ManyToOne
    private Local local;
    private Date dateStartedFunctioning;
    //@OneToOne
    private List<Reading> readingList;
    private boolean active;

    /**
     * Empty constructor to import Sensors from a XML file.
     */
    public Sensor() {
        readingList = new ArrayList<>();
        this.active = true;
    }

    /**
     * Sensor() constructor with 5 parameters.
     *
     * @param id                     is the id we want to set to the Sensor.
     * @param name                   is the name we want to set to the Sensor.
     * @param typeSensor             is the Type of the Sensor.
     * @param local                  is the Local of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public Sensor(String id, String name, TypeSensor typeSensor, Local local, Date dateStartedFunctioning) {
        setId(id);
        setName(name);
        setTypeSensor(typeSensor);
        setLocal(local);
        setDateStartedFunctioning(dateStartedFunctioning);
        this.readingList = new ArrayList<>();
        this.active = true;
    }

    /**
     * Sensor() constructor with 3 parameters (Used When A Sensor is in a Room already. So the Location is the same as
     * the room/house).
     *
     * @param name                   is the name we want to set to the Sensor.
     * @param typeSensor             is the Type of the Sensor.
     * @param dateStartedFunctioning is the Date that the Sensor Started Working.
     */
    public Sensor(String name, TypeSensor typeSensor, Date dateStartedFunctioning) {
        setName(name);
        setTypeSensor(typeSensor);
        setDateStartedFunctioning(dateStartedFunctioning);
        this.readingList = new ArrayList<>();
        this.active = true;
    }

    /**
     * Setter: Id
     *
     * @param id is the id we want to set to the sensor.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Setter: name
     *
     * @param name is the name we want to set to the sensor.
     */
    public void setName(String name) {
        if (isSensorNameValid(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Please Insert Valid Name");
        }
    }

    /**
     * Setter: local
     *
     * @param local is the local we want to set to the sensor.
     */
    public void setLocal(Local local) {
        this.local = local;
    }

    /**
     * Setter: type sensor
     *
     * @param sensor is the Type we want to set to the sensor.
     */
    public void setTypeSensor(TypeSensor sensor) {
        this.typeSensor = sensor;
    }

    /**
     * Setter: date started functioning
     *
     * @param dateStartedFunctioning is the date that the sensor started functioning.
     */
    public void setDateStartedFunctioning(Date dateStartedFunctioning) {
        this.dateStartedFunctioning = dateStartedFunctioning;
    }

    public Date getDateStartedFunctioning() {
        return this.dateStartedFunctioning;
    }

    /**
     * Getter: Id
     *
     * @return a string that represents the name of the sensor.
     */
    public String getId() {
        return (this.id);
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
    public TypeSensor getTypeSensor() {
        return (this.typeSensor);
    }

    /**
     * Getter: local
     *
     * @return the Local of the Sensor.
     */
    public Local getLocal() {
        return (this.local);
    }

    /**
     * Getter: reading list
     *
     * @return the readingList of the sensor.
     */
    public List<Reading> getReadingList() {
        return this.readingList;
    }

    /**
     * Setter: reading list
     *
     * @param readingList is the readingList we want to set to the sensor.
     */
    public void setReadingList(List<Reading> readingList) {
        if (readingList != null) {
            this.readingList = readingList;
        }
    }

    public boolean isActive() {
        return this.active;
    }


    /**
     * Settter: sets the sensor active
     */
    public void setActive() {
        this.active = true;
    }

    public void setterActive(boolean active) {
        this.active = active;
    }

    /**
     * Method to activate an deactivated sensor, and vice versa
     *
     * @return active or not
     */
    public boolean activateOrDeactivate() {
        if (!isActive()) {
            this.active = true;
            return true;
        }
        this.active = false;
        return false;
    }

    /**
     * Checks if reading already exists in reading list and in case the
     * reading is new, adds it to the reading list. Only adds readings if the sensor is active.
     *
     * @param reading the reading to be added to the list
     * @return true in case the reading is new and it is added
     * or false in case the reading already exists
     **/
    public boolean addReading(Reading reading) {
        if (this.active && !readingList.contains(reading)) {
            return this.readingList.add(reading);
        }
        return false;
    }

    /**
     * Adds a new Reading to a sensor with the date and value received as parameter, but only if that date is posterior
     * to the date when the sensor was activated.
     *
     * @param value is the value read on the reading.
     * @param date  is the read date of the reading.
     * @return returns true if the reading was successfully added.
     * @author André
     */
    public boolean addReading(Date date, Double value) {
        if (this.active) {
            Date startingDate = this.getDateStartedFunctioning();
            if (date.after(startingDate) || date.equals(startingDate)) {
                Reading reading = new Reading(value, date);
                return !readingList.contains(reading) && this.readingList.add(reading);
            }
        }
        return false;
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
     * Method that checks if the Sensor is contained in a given Geographical Area.
     *
     * @param area is the area we want to check if the sensor is in.
     * @return true if the sensor is in the given area, false otherwise.
     */
    boolean isContainedInArea(GeographicArea area) {
        double latS = this.getLocal().getLatitude();
        double longS = this.getLocal().getLongitude();
        Local areaLocal = area.getLocal();
        double latTopVert = areaLocal.getLatitude() + (area.getWidth() / 2);
        double longTopVert = areaLocal.getLongitude() - (area.getLength() / 2);
        double latBotVert = areaLocal.getLatitude() - (area.getWidth() / 2);
        double longBotVert = areaLocal.getLongitude() + (area.getLength() / 2);
        return (latS <= latTopVert && latS >= latBotVert && longS <= longBotVert && longS >= longTopVert);
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

        if (this.getLocal() == null) {
            return this.name + ", " + this.typeSensor.getName() + ". ";
        }
        String result;

        result = this.name + ", " + this.typeSensor.getName() + ", " +
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
     * This method goes through the sensor reading list and return
     * the most recent reading date.
     *
     * @return most recent reading date in sensor
     **/

    Date getMostRecentReadingDate() {
        return ReadingUtils.getMostRecentReading(this.readingList).getDate();

    }

    /**
     * This method returns the sensor type name.
     *
     * @return he sensor type name.
     **/

    String getSensorTypeName() {
        return this.typeSensor.getName();
    }

    /**
     * This method checks if the sensor's reading list is valid.
     *
     * @return true if valid, false if invalid.
     **/
    public boolean isReadingListEmpty() {
        return this.readingList.isEmpty();
    }


    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof Sensor)) {
            return false;
        }
        Sensor sensor = (Sensor) testObject;
        return this.getName().equals(sensor.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }


    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param reading receives a reading.
     * @return returns true if the input reading was added successfully.
     * returns false if the input reading was rejected.
     */
    public boolean addReading(Reading reading, List<Reading> list) {
        if (list.contains(reading)) {
            return false;
        }
        return list.add(reading);
    }

    /**
     * This method goes through the sensor's reading list and
     * returns the most recent reading value.
     *
     * @return sensor's most recent reading value.
     ***/
    public double getMostRecentValueReading() {
        return ReadingUtils.getMostRecentValue(this.readingList);
    }


    /**
     * This method receives a date of a given day, goes through the sensor's reading list and
     * returns the total reading values of that day.
     *
     * @param day date of day
     * @return total reading values of that day
     * @author André (US620)
     */
    public double getTotalValueReadingsOnGivenDay(Date day) {
        List<Double> totalValuesFromDaysWithReadings = new ArrayList<>();
        List<Double> valueReadingsThatMatchDay = ReadingUtils.getValuesOfSpecificDayReadings(day, this.readingList);
        if (valueReadingsThatMatchDay.isEmpty()) {
            throw new IllegalStateException("Warning: Total value was not calculated - No readings were available.");
        }
        double givenD;
        givenD = getListSum(valueReadingsThatMatchDay);
        totalValuesFromDaysWithReadings.add(givenD);
        return getListSum(totalValuesFromDaysWithReadings);
    }


    /**
     * Method that gives the Average of Readings between two dates (given days)
     * It calculates the average of all days, considering the average of each day.
     * It will throw an IllegalArgumentException if there are no readings between the selected dates
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the average of all values in the reading list between the two given dates
     * @author Daniela (US623)
     */
    public double getAverageReadingsBetweenDates(Date minDate, Date maxDate) {
        List<Reading> readingListBetweenDates = ReadingUtils.getReadingListBetweenDates(minDate, maxDate, this.readingList);
        if (readingListBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        List<Double> avgDailyValues = new ArrayList<>();
        for (int i = 0; i < readingListBetweenDates.size(); i++) {
            Date day = readingListBetweenDates.get(i).getDate();
            List<Double> specificDayValues = ReadingUtils.getValuesOfSpecificDayReadings(day, this.readingList);
            double avgDay = getAvgFromList(specificDayValues);
            avgDailyValues.add(avgDay);
        }
        return getAvgFromList(avgDailyValues);
    }


    /**
     * Method that gives the Date with the Highest Amplitude of Readings between two dates (given days).
     * If there is more than one day with the same temperature amplitude, the return will be the most recent day.
     * It will throw an IllegalArgumentException if there are no readings between the selected dates.
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the Date with Highest Amplitude of all values in the reading list between the two given dates
     * @author Daniela (US633)
     */
    public Date getDateHighestAmplitudeBetweenDates(Date minDate, Date maxDate) {

        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings available.");
        }

        Date dateMaxAmplitude = daysWithReadings.get(0);

        double maxAmplitude = getAmplitudeValueFromDate(dateMaxAmplitude);

        for (int i = 1; i < daysWithReadings.size(); i++) {
            Date day = daysWithReadings.get(i);
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
        List<Double> specificDayValues = ReadingUtils.getValuesOfSpecificDayReadings(date, this.readingList);
        double maxTemp = Collections.max(specificDayValues);
        double lowestTemp = Collections.min(specificDayValues);
        return maxTemp - lowestTemp;
    }

    /**
     * Adds all readings of a given ReadingList to target list, rejecting duplicates.
     *
     * @param finalList The list to be added to the target list
     * @return A parallel deviceList with all the devices that could be added
     **/
    public List<Reading> appendListNoDuplicates(List<Reading> finalList) {
        Reading[] readingsArray = getElementsAsArray();
        for (Reading r : readingsArray) {
            finalList.add(r);
        }
        return finalList;
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
        if (this.readingList.isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: No temperature readings available in given period.");
        }
        double maxTemperature = getMaxValue(daysWithReadings);
        return getFirstDayForGivenTemperature(maxTemperature, daysWithReadings);
    }

    /**
     * US630
     * This method joins a lot of other methods used to fulfil the US 630 (As a Regular User,
     * I want to get the last coldest day (lower maximum temperature) in the house area in a given period) and
     * it returns a Date within an interval from a ReadingList that represents the last coldest day in the
     * given period (lower maximum temperature).
     *
     * @param initialDate is the Initial Date of the period.
     * @param finalDate   is the Final Date of the period.
     * @return a Reading that represents the Last Coldest Day in a Given Period (Lower Maximum Temperature).
     */
    public Date getLastColdestDayInGivenInterval(Date initialDate, Date finalDate) {
        if (this.readingList.isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        List<Reading> readingListBetweenDates = ReadingUtils.getReadingListBetweenDates(initialDate, finalDate, this.readingList);
        if (readingListBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("No readings available in the chosen interval.");
        }
        List<Reading> listOfMaxValuesForEachDay = ReadingUtils.getListOfMaxValuesForEachDay(readingListBetweenDates);
        double minValueInList = ReadingUtils.getMinValueInReadingList(listOfMaxValuesForEachDay);
        List<Reading> readingListWithSpecificValue = ReadingUtils.getReadingListOfReadingsWithSpecificValue(minValueInList, this.readingList);

        return ReadingUtils.getMostRecentReading(readingListWithSpecificValue).getDate();
    }


    /* Sensor class - internal auxiliary methods */

    /**
     * Auxiliary method for getFirstHottestDayInGivenPeriod
     *
     * @param listDates of readings for getting highest value from
     * @return highest value from list of readings
     */

    private double getMaxValue(List<Date> listDates) {
        ArrayList<Double> values = new ArrayList<>();
        for (Date day : listDates) {
            values.addAll(ReadingUtils.getValuesOfSpecificDayReadings(day, this.readingList));
        }
        return Collections.max(values);
    }

    /**
     * Auxiliary method for getFirstHottestDayInGivenPeriod
     *
     * @param temperature given for finding first day in period with that temperature
     * @param dates       for selecting the first day with given temperature from date list
     * @return first date where given temperature was registered
     */

    private Date getFirstDayForGivenTemperature(double temperature, List<Date> dates) {
        List<Date> daysWithTemperature = new ArrayList<>();
        for (Date date : dates) {
            if (ReadingUtils.getValuesOfSpecificDayReadings(date, this.readingList).contains(temperature)) {
                daysWithTemperature.add(date);
            }
        }
        return Collections.min(daysWithTemperature);
    }

    /**
     * Getter (array of readings)
     *
     * @return array of readings
     */
    private Reading[] getElementsAsArray() {
        int sizeOfResultArray = this.readingList.size();
        Reading[] result = new Reading[sizeOfResultArray];
        for (int i = 0; i < this.readingList.size(); i++) {
            result[i] = this.readingList.get(i);
        }
        return result;
    }

    /**
     * This method receives a list of doubles that correspond to value readings and
     * will return the average value on that list.
     *
     * @return returns the average of all values contained within that List. If List is empty it will return 0.
     */
    private double getAvgFromList(List<Double> valueList) {
        if (valueList.isEmpty()) {
            return 0;
        }
        double sum = getListSum(valueList);
        return (sum / valueList.size());
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
    private List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {
        List<Date> daysWithReadings = new ArrayList<>();
        List<Date> daysProcessed = new ArrayList<>();

        Date startDate = ReadingUtils.getFirstSecondOfDay(dayMin);
        Date endDate = ReadingUtils.getLastSecondOfDay(dayMax);

        for (int i = 0; i < this.readingList.size(); i++) {
            Date currentReadingDate = ReadingUtils.getValueDate(i, this.readingList);
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
     * This method receives a list of doubles that correspond to value readings and
     * will return the sum of their values.
     *
     * @return returns the sum of all values contained within that List
     */
    private double getListSum(List<Double> valueList) {
        double sum = 0;
        for (Double aValueList : valueList) {
            sum = sum + aValueList;
        }
        return sum;
    }


}
