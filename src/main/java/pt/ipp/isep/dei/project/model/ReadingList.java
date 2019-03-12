package pt.ipp.isep.dei.project.model;

import java.util.*;

import static java.lang.Double.NaN;

/**
 * This is the ReadingList Class, a List of Readings that the Sensor receives.
 */

public class ReadingList {

    private static final String EMPTY_LIST = "The reading list is empty.";
    private ArrayList<Reading> readings;

    /**
     * Empty Constructor to always allow the creation of an ArrayList of Readings.
     */
    public ReadingList() {
        this.readings = new ArrayList<>();
    }

    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param reading receives a reading.
     * @return returns true if the input reading was added successfully.
     * returns false if the input reading was rejected.
     */
    public boolean addReading(Reading reading) {
        boolean result = false;
        if (!(readings.contains(reading))) {
            result = readings.add(reading);
        }
        return result;
    }

    /**
     * This method receives an index as parameter and gets a reading from reading list.
     *
     * @param index the index of the Reading
     * @return returns reading that corresponds to index.
     */
    public Reading get(int index) {
        if (this.readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        return this.readings.get(index);
    }

    /**
     * This method receives an index as parameter and gets a value reading from reading list.
     *
     * @param index the index of the Reading we want to get value from
     * @return returns value reading that corresponds to index.
     */
    double getValueReading(int index) {
        if (this.readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = this.readings.get(index);
        return reading.getValue();
    }

    /**
     * This method receives an index as parameter and gets a reading date from reading list.
     *
     * @param index the index of the Reading we want to get date from
     * @return returns date reading that corresponds to index.
     */
    Date getValueDate(int index) {
        if (this.readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = this.readings.get(index);
        return reading.getDate();
    }


    /**
     * Simple method to indicate if this reading list is empty i.e. has no registered readings.
     *
     * @return whether the list is empty or not.
     */
    public boolean isEmpty() {
        return readings.isEmpty();
    }

    /**
     * Checks the reading list size and returns the size as int.\
     *
     * @return ReadingList size as int
     **/
    public int size() {
        return this.readings.size();
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     * @author Carina (US600 e US605)
     **/
    Reading getMostRecentReading() {
        Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime());
        if (isEmpty()) {
            return error;
        }
        Reading recentReading = this.readings.get(0);
        Date mostRecent = recentReading.getDate();
        for (Reading r : this.readings) {
            Date testDate = r.getDate();
            if (testDate.after(mostRecent)) {
                mostRecent = testDate;
                recentReading = r;
            }
        }
        return recentReading;
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the most recent reading Date.
     *
     * @return most recent reading date
     **/

    Date getMostRecentReadingDate() {
        return getMostRecentReading().getDate();
    }


    /**
     * This method returns the most recent reading value a Reading List.
     *
     * @return the most recent reading value or NaN when the Reading List is empty
     * @author Carina (US600 e US605)
     */
    double getMostRecentValue() {
        if (this.readings.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading().getValue();
    }

    /**
     * Method that gives the Total Value of Readings on a given day (Date).
     *
     * @param givenDate given date
     * @return sum
     * @author André (US620)
     */
    double getValueReadingsInDay(Date givenDate) {
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
    double getListSum(List<Double> valueList) {
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
    Date getFirstSecondOfDay(Date day) {
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
    Date getLastSecondOfDay(Date day) {
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
     * @author Daniela - US623
     */
    List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {
        List<Date> daysWithReadings = new ArrayList<>();
        List<Date> daysProcessed = new ArrayList<>();

        Date startDate = getFirstSecondOfDay(dayMin);
        Date endDate = getLastSecondOfDay(dayMax);

        for (int i = 0; i < readings.size(); i++) {
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
    boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
        return (readingDate.after(startDate) || readingDate.equals(startDate)) &&
                (readingDate.before(endDate) || readingDate.equals(endDate));
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
    double getAverageReadingsBetweenDates(Date minDate, Date maxDate) {
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: Average value not calculated - No readings available.");
        }
        List<Double> avgDailyValues = new ArrayList<>();
        for (Date day : daysWithReadings) {
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
    List<Double> getValuesOfSpecificDayReadings(Date day) {
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
    double getAvgFromList(List<Double> valueList) {
        if (valueList.isEmpty()) {
            return 0;
        }
        double sum = getListSum(valueList);
        return (sum / valueList.size());
    }

    /**
     * Method that gives the Date with the Highest Amplitude of Readings between two dates (given days)
     * It will throw an IllegalArgumentException if there are no readings between the selected dates
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the Date with Highest Amplitude of all values in the reading list between the two given dates
     * if there is more than one day with the same temperature amplitude, the return will be the most recent day
     * @author Daniela (US633)
     */

    Date getDateHighestAmplitudeBetweenDates(Date minDate, Date maxDate) {
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: Temperature amplitude value not calculated - No readings available.");
        }

        Date dateAmplitude = daysWithReadings.get(0);

        double amplitudeValue = -1000.0;

        for (Date day : daysWithReadings) {
            double amplitudeTemperature = getAmplitudeValueFromDate(day);
            if (amplitudeValue <= amplitudeTemperature) {
                amplitudeValue = amplitudeTemperature;
                if (day.after(dateAmplitude)) {
                    dateAmplitude = day;
                }
            }
        }
        return dateAmplitude;
    }

    /**
     * Method that gives the highest amplitude value on a given date
     *
     * @param date for each we want the amplitude value
     * @return highest amplitude value
     * @author Daniela (US633)
     */
    double getAmplitudeValueFromDate(Date date) {
        List<Double> specificDayValues = getValuesOfSpecificDayReadings(date);
        double maxTemp = Collections.max(specificDayValues);
        double lowestTemp = Collections.min(specificDayValues);
        return maxTemp - (lowestTemp);
    }

    /**
     * Adds all readings of a given ReadingList to target list, rejecting duplicates.
     *
     * @param readingList The list to be added to the target list
     * @return A parallel deviceList with all the devices that could be added
     **/
    ReadingList appendListNoDuplicates(ReadingList readingList) {
        Reading[] readings = readingList.getElementsAsArray();
        for (Reading r : readings) {
            this.addReading(r);
        }
        return this;
    }


    /**
     * Method that gives the Date with the First Hottest Day Readings in given period
     * It will throw an IllegalArgumentException if there are no available readings between the dates
     * This method runs the array of dates in the given period, storing the first hottest temperature Date,
     * only overwritting if there's a day with a higher temperature, ensuring the final return will be
     * the first hottest day in period.
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the Date with First Hottest Day in given period.
     * @author Nuno (US631)
     */

    Date getFirstHottestDayInGivenPeriod(Date minDate, Date maxDate) {
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: No temperature readings available.");
        }
        Date firstHottestDay = new Date();
        double tempTemp = -1000;
        Date tempDate = daysWithReadings.get(0);
        for (Date day : daysWithReadings) {
            List<Double> listOfMaxReadings = getValuesOfSpecificDayReadings(day);
            double maxTemp = Collections.max(listOfMaxReadings);
            if ((tempTemp < maxTemp) && (day.before(tempDate))) {
                tempTemp = maxTemp;
                firstHottestDay = day;
            }
        }
        return firstHottestDay;
    }

    /**
     * Getter (array of readings)
     *
     * @return array of readings
     */
    Reading[] getElementsAsArray() {
        int sizeOfResultArray = size();
        Reading[] result = new Reading[sizeOfResultArray];
        for (int i = 0; i < size(); i++) {
            result[i] = readings.get(i);
        }
        return result;
    }

    /**
     * US630
     * This method filters the existing ReadingList so that it returns the ReadingList with the chosen value.
     *
     * @param value is the value we want to choose.
     * @return a ReadingList with a chosen value.
     */
    ReadingList getReadingListOfReadingsWithSpecificValue(Double value) {
        ReadingList result = new ReadingList();
        for (Reading r : this.readings) {
            if (Double.compare(r.getValue(), value) == 0) {
                result.addReading(r);
            }
        }
        return result;
    }

    /**
     * US630
     * This method returns a Reading with a specific date from a given ReadingList.
     *
     * @param date is the Date of the reading we want to get.
     * @return a Reading from the ReadingList with a Specific Date.
     */
    Reading getReadingWithSpecificDate(Date date) {
        Reading result = null;
        for (int i = 0; i < this.readings.size(); i++) {
            if (this.readings.get(i).getDate().equals(date)) {
                result = this.readings.get(i);
            }
        }
        return result;
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
    Date getLastColdestDayInGivenInterval(Date initialDate, Date finalDate) {
        if (isEmpty()) {
            throw new IllegalArgumentException("No readings available.");
        }
        ReadingList readingListBetweenDates = getReadingListBetweenDates(initialDate, finalDate);
        if (readingListBetweenDates.isEmpty()) {
            throw new IllegalArgumentException("No readings available in the chosen interval.");
        }
        ReadingList listOfMaxValuesForEachDay = readingListBetweenDates.getListOfMaxValuesForEachDay();
        double minValueInList = listOfMaxValuesForEachDay.getMinValueInReadingList();
        ReadingList readingListWithSpecificValue = getReadingListOfReadingsWithSpecificValue(minValueInList);
        return readingListWithSpecificValue.getMostRecentReadingDate();
    }


    /**
     * US630
     * This method returns a Reading that represents the maximum value of a Reading in a ReadingList
     * from a given day.
     *
     * @param day is the day we want to know the maximum value.
     * @return a double that represents the maximum value of the day.
     */
    Reading getMaxValueOfTheDay(Date day) {
        double auxValue = getValuesOfSpecificDayReadings(day).get(0);
        Reading result = getReadingWithSpecificDate(day);
        Date auxDay = getFirstSecondOfDay(day);
        for (Reading r : this.readings) {
            Date readingDate = getFirstSecondOfDay(r.getDate());
            if (readingDate.equals(auxDay) && r.getValue() > auxValue) {
                result = r;
            }
        }
        return result;
    }

    /**
     * This method filters a ReadingList and returns the ReadingList but within an interval of given dates.
     *
     * @param initialDate is the initial date of the interval.
     * @param finalDate   is the final date of the interval.
     * @return a ReadingList that represents the initial ReadingList but only with Readings within the given interval.
     */
    public ReadingList getReadingListBetweenDates(Date initialDate, Date finalDate) {
        ReadingList result = new ReadingList();
        for (int i = 0; i < size(); i++) {
            if (isReadingDateBetweenTwoDates(this.readings.get(i).getDate(), initialDate, finalDate)) {
                result.addReading(this.readings.get(i));
            }
        }
        return result;
    }

    /**
     * US630
     * This method returns a ReadingList that represents a List of maximum values of the ReadingList for each
     * day within a given period.
     *
     * @return a ReadingList that represents a List of maximum values of the ReadingList for each
     * day within a given period.
     */
    ReadingList getListOfMaxValuesForEachDay() {
        ReadingList result = new ReadingList();
        List<Date> dateList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            Date aux = getFirstSecondOfDay(this.readings.get(i).getDate());
            if (!dateList.contains(aux)) {
                Reading maxOfTheDay = getMaxValueOfTheDay(this.readings.get(i).getDate());
                dateList.add(aux);
                result.addReading(maxOfTheDay);
            }
        }
        return result;
    }

    /**
     * This method returns a double value that represents the minimum value in a ReadingList.
     *
     * @return a double value that represents the minimum value in a ReadingList.
     */
    public double getMinValueInReadingList() {
        double result = this.readings.get(0).getValue();
        for (int i = 0; i < size(); i++) {
            if (this.readings.get(i).getValue() < result) {
                result = this.readings.get(i).getValue();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof ReadingList)) {
            return false;
        }
        ReadingList list = (ReadingList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}





