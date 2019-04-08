package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.services.units.Celsius;

import javax.persistence.*;
import java.util.*;

/**
 * This is the AreaReadingList Class, a List of readings that the Sensor receives.
 */
@Entity
public class AreaReadingList {

    private static final String EMPTY_LIST = "The reading list is empty.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Used as primary key in repository tables.

    @OneToMany(mappedBy = "areaReadingList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AreaReading> areaReadings;

    /**
     * /**
     * Empty Constructor to always allow the creation of an ArrayList of readings.
     */
    public AreaReadingList() {
        this.areaReadings = new ArrayList<>();
    }

    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param areaReading receives a reading.
     * @return returns true if the input reading was added successfully.
     * returns false if the input reading was rejected.
     */
    public boolean addReading(AreaReading areaReading) {
        if (contains(areaReading)) {
            return false;
        }
        return this.areaReadings.add(areaReading);
    }

    /**
     * This method receives an index as parameter and gets a reading from reading list.
     *
     * @param index the index of the Reading
     * @return returns reading that corresponds to index.
     */
    public AreaReading get(int index) {
        if (this.areaReadings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        return this.areaReadings.get(index);
    }

    /**
     * This method receives an index as parameter and gets a value reading from reading list.
     *
     * @param index the index of the Reading we want to get value from
     * @return returns value reading that corresponds to index.
     */
    public double getValueReading(int index) {
        if (this.areaReadings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        AreaReading areaReading = this.areaReadings.get(index);
        return areaReading.getValue();
    }

    public List<AreaReading> getAreaReadings() {
        return areaReadings;
    }

    /**
     * This method receives an index as parameter and gets a reading date from reading list.
     *
     * @param index the index of the Reading we want to get date from
     * @return returns date reading that corresponds to index.
     */
   public Date getValueDate(int index) {
        if (this.areaReadings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        AreaReading areaReading = this.areaReadings.get(index);
        return areaReading.getDate();
    }


    /**
     * Simple method to indicate if this reading list is empty i.e. has no registered readings.
     *
     * @return whether the list is empty or not.
     */
    public boolean isEmpty() {
        return areaReadings.isEmpty();
    }

    /**
     * Checks the reading list size and returns the size as int.\
     *
     * @return AreaReadingList size as int
     **/
    public int size() {
        return this.areaReadings.size();
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     * @author Carina (US600 e US605)
     **/
    public AreaReading getMostRecentReading() {
        AreaReading error = new AreaReading(0, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), new Celsius());
        if (isEmpty()) {
            return error;
        }
        AreaReading recentAreaReading = this.areaReadings.get(0);
        Date mostRecent = recentAreaReading.getDate();
        for (AreaReading r : this.areaReadings) {
            Date testDate = r.getDate();
            if (testDate.after(mostRecent)) {
                mostRecent = testDate;
                recentAreaReading = r;
            }
        }
        return recentAreaReading;
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the most recent reading Date.
     *
     * @return most recent reading date
     **/

    public Date getMostRecentReadingDate() {
        return getMostRecentReading().getDate();
    }


    /**
     * This method returns the most recent reading value a Reading List.
     *
     * @return the most recent reading value or NaN when the Reading List is empty
     * @author Carina (US600 e US605)
     */
    public double getMostRecentValue() {
        if (this.areaReadings.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading().getValue();
    }

    /**
     * Method that gives the Total Value of readings on a given day (Date).
     *
     * @param givenDate given date
     * @return sum
     * @author André (US620)
     */
    public double getValueReadingsInDay(Date givenDate) {
        List<Double> totalValuesFromDaysWithReadings = new ArrayList<>();
        List<Double> valueReadingsThatMatchDay = getValuesOfSpecificDayReadings(givenDate);
        if (valueReadingsThatMatchDay.isEmpty()) {
            throw new IllegalStateException("Warning: Total value was not calculated - No readings were available.");
        }
        double givenD;
        givenD = getListSum(valueReadingsThatMatchDay);
        totalValuesFromDaysWithReadings.add(givenD);
        return getListSum(totalValuesFromDaysWithReadings);
    }

    /**
     * This method receives a list of doubles that correspond to value readings and
     * will return the sum of their values.
     *
     * @return returns the sum of all values contained within that List
     */
    public double getListSum(List<Double> valueList) {
        double sum = 0;
        for (Double aValueList : valueList) {
            sum = sum + aValueList;
        }
        return sum;
    }

    /**
     * Gets a date corresponding to the first second of a given day
     *
     * @param day the day to use as reference
     * @return date with 1st second of given day
     * @author Daniela - US623
     */
   public Date getFirstSecondOfDay(Date day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * Gets a date corresponding to the last second of a given day
     *
     * @param day the day to use as reference
     * @return date with last second of given day
     * @author Daniela (US623)
     */
   public Date getLastSecondOfDay(Date day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
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
    public List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {
        List<Date> daysWithReadings = new ArrayList<>();
        List<Date> daysProcessed = new ArrayList<>();

        Date startDate = getFirstSecondOfDay(dayMin);
        Date endDate = getLastSecondOfDay(dayMax);

        for (int i = 0; i < areaReadings.size(); i++) {
            Date currentReadingDate = this.getValueDate(i);
            if (isReadingDateBetweenTwoDates(currentReadingDate, startDate, endDate)) {

                Date readingDay = getFirstSecondOfDay(currentReadingDate);

                if (!daysProcessed.contains(readingDay)) {
                    daysProcessed.add(readingDay);
                    daysWithReadings.add(currentReadingDate);
                }
            }
        }
        return daysWithReadings;
    }

    /**
     * Method to see if a reading date is between two dates
     *
     * @param readingDate reading date we want to verify
     * @param startDate   start date given by user, will be the start of the  date interval;
     * @param endDate     end date given by user, will be the end of the date interval;
     * @return true if reading date is between dates, false if it isn't
     * @author Daniela - US623
     */
    public boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
        return (readingDate.after(startDate) || readingDate.equals(startDate)) &&
                (readingDate.before(endDate) || readingDate.equals(endDate));
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
        AreaReadingList areaReadingListBetweenDates = getReadingListBetweenDates(minDate, maxDate);
        if (areaReadingListBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        List<Double> avgDailyValues = new ArrayList<>();
        for (int i = 0; i < areaReadingListBetweenDates.size(); i++) {
            Date day = areaReadingListBetweenDates.get(i).getDate();
            List<Double> specificDayValues = getValuesOfSpecificDayReadings(day);
            double avgDay = getAvgFromList(specificDayValues);
            avgDailyValues.add(avgDay);
        }
        return getAvgFromList(avgDailyValues);
    }

    /**
     * This method will receive a day, go through the reading list for value readings that took place on that day,
     * and return a list of doubles with those values.
     *
     * @param day the day to look for readings
     * @return returns a list with every value of readings that was recorded on that particular day.
     * @author Daniela - US623
     */
    public List<Double> getValuesOfSpecificDayReadings(Date day) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (compareDayMonthAndYearBetweenDates(this.getValueDate(i), day)) {
                valueReadingsFromGivenDay.add(this.getValueReading(i));
            }
        }
        return valueReadingsFromGivenDay;
    }

    /**
     * Compare if days are equal regarding year - month - date.
     * Ignores hours, minutes and seconds
     *
     * @param r1 date one
     * @param r2 date two
     * @return return true if equal, return false otherwise
     * @author Daniela (US623)
     */
    private boolean compareDayMonthAndYearBetweenDates(Date r1, Date r2) {
        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.setTime(r1);

        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTime(r2);

        return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) &&
                (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) &&
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    /**
     * This method receives a list of doubles that correspond to value readings and
     * will return the average value on that list.
     *
     * @return returns the average of all values contained within that List. If List is empty it will return 0.
     */
    public double getAvgFromList(List<Double> valueList) {
        if (valueList.isEmpty()) {
            return 0;
        }
        double sum = getListSum(valueList);
        return (sum / valueList.size());
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
        List<Double> specificDayValues = getValuesOfSpecificDayReadings(date);
        double maxTemp = Collections.max(specificDayValues);
        double lowestTemp = Collections.min(specificDayValues);
        return maxTemp - lowestTemp;
    }

    /**
     * Adds all readings of a given AreaReadingList to target list, rejecting duplicates.
     *
     * @param areaReadingList The list to be added to the target list
     * @return A parallel deviceList with all the devices that could be added
     **/
    public AreaReadingList appendListNoDuplicates(AreaReadingList areaReadingList) {
        AreaReading[] readingsArray = areaReadingList.getElementsAsArray();
        for (AreaReading r : readingsArray) {
            this.addReading(r);
        }
        return this;
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
        if (isEmpty()) {
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
     * Auxiliary method for getFirstHottestDayInGivenPeriod
     *
     * @param temperature given for finding first day in period with that temperature
     * @param dates       for selecting the first day with given temperature from date list
     * @return first date where given temperature was registered
     */

    private Date getFirstDayForGivenTemperature(double temperature, List<Date> dates) {
        List<Date> daysWithTemperature = new ArrayList<>();
        for (Date date : dates) {
            if (getValuesOfSpecificDayReadings(date).contains(temperature)) {
                daysWithTemperature.add(date);
            }
        }
        return Collections.min(daysWithTemperature);
    }

    /**
     * Auxiliary method for getFirstHottestDayInGivenPeriod
     *
     * @param list of readings for getting highest value from
     * @return highest value from list of readings
     */

    private double getMaxValue(List<Date> list) {
        ArrayList<Double> values = new ArrayList<>();
        for (Date day : list) {
            values.addAll(getValuesOfSpecificDayReadings(day));
        }
        return Collections.max(values);
    }

    /**
     * Getter (array of readings)
     *
     * @return array of readings
     */
    public AreaReading[] getElementsAsArray() {
        int sizeOfResultArray = size();
        AreaReading[] result = new AreaReading[sizeOfResultArray];
        for (int i = 0; i < size(); i++) {
            result[i] = areaReadings.get(i);
        }
        return result;
    }

    /**
     * US630
     * This method filters the existing AreaReadingList so that it returns the AreaReadingList with the chosen value.
     *
     * @param value is the value we want to choose.
     * @return a AreaReadingList with a chosen value.
     */
    public AreaReadingList getReadingListOfReadingsWithSpecificValue(Double value) {
        AreaReadingList result = new AreaReadingList();
        for (AreaReading r : this.areaReadings) {
            if (Double.compare(r.getValue(), value) == 0) {
                result.addReading(r);
            }
        }
        return result;
    }

    /**
     * US630
     * This method returns a Reading with a specific day from a given AreaReadingList.
     *
     * @param date is the Day of the reading we want to get.
     * @return a Reading from the AreaReadingList with a Specific Date.
     */
    public AreaReading getAReadingWithSpecificDay(Date date) {
        AreaReading result = null;
        for (AreaReading r : this.areaReadings) {
            if (compareDayMonthAndYearBetweenDates(r.getDate(), date)) {
                result = r;
                break;
            }
        }
        return result;
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
        if (isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        AreaReadingList areaReadingListBetweenDates = getReadingListBetweenDates(initialDate, finalDate);
        if (areaReadingListBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("No readings available in the chosen interval.");
        }
        AreaReadingList listOfMaxValuesForEachDay = areaReadingListBetweenDates.getListOfMaxValuesForEachDay();
        double minValueInList = listOfMaxValuesForEachDay.getMinValueInReadingList();
        AreaReadingList areaReadingListWithSpecificValue = getReadingListOfReadingsWithSpecificValue(minValueInList);
        return areaReadingListWithSpecificValue.getMostRecentReadingDate();
    }


    /**
     * US630
     * This method returns a Reading that represents the maximum value of a Reading in a AreaReadingList
     * from a given day.
     *
     * @param day is the day we want to know the maximum value.
     * @return a double that represents the maximum value of the day.
     */
   public AreaReading getMaxValueOfTheDay(Date day) {
        double auxValue = getValuesOfSpecificDayReadings(day).get(0);
        AreaReading result = getAReadingWithSpecificDay(day);
        Date auxDay = getFirstSecondOfDay(day);
        for (AreaReading r : this.areaReadings) {
            Date readingDate = getFirstSecondOfDay(r.getDate());
            if (readingDate.equals(auxDay) && r.getValue() > auxValue) {
                result = r;
                auxValue = result.getValue();
            }
        }
        return result;
    }

    /**
     * This method filters a AreaReadingList and returns the AreaReadingList but within an interval of given dates.
     *
     * @param initialDate is the initial date of the interval.
     * @param finalDate   is the final date of the interval.
     * @return a AreaReadingList that represents the initial AreaReadingList but only with readings within the given interval.
     */
    public AreaReadingList getReadingListBetweenDates(Date initialDate, Date finalDate) {
        AreaReadingList result = new AreaReadingList();
        for (AreaReading r : this.areaReadings) {
            if (isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                result.addReading(r);
            }
        }
        return result;
    }

    /**
     * US630
     * This method returns a AreaReadingList that represents a List of maximum values of the AreaReadingList for each
     * day within a given period.
     *
     * @return a AreaReadingList that represents a List of maximum values of the AreaReadingList for each
     * day within a given period.
     */
    public AreaReadingList getListOfMaxValuesForEachDay() {
        AreaReadingList result = new AreaReadingList();
        List<Date> dateList = new ArrayList<>();
        for (AreaReading r : this.areaReadings) {
            Date aux = getFirstSecondOfDay(r.getDate());
            if (!dateList.contains(aux)) {
                AreaReading maxOfTheDay = getMaxValueOfTheDay(r.getDate());
                dateList.add(aux);
                result.addReading(maxOfTheDay);
            }
        }
        return result;
    }

    /**
     * This method returns a double value that represents the minimum value in a AreaReadingList.
     *
     * @return a double value that represents the minimum value in a AreaReadingList.
     */
   public double getMinValueInReadingList() {
        double result = this.areaReadings.get(0).getValue();
        for (AreaReading r : this.areaReadings) {
            result = Math.min(r.getValue(), result);
        }
        return result;
    }

    /**
     * This method checks if a reading exists in a given reading list.
     *
     * @return true if the reading already exists, false otherwise
     */
    public boolean contains(AreaReading areaReading) {
        return (areaReadings.contains(areaReading));
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof AreaReadingList)) {
            return false;
        }
        AreaReadingList list = (AreaReadingList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}




