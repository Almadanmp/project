package pt.ipp.isep.dei.project.model;

import java.util.*;

import static java.lang.Double.NaN;

/**
 * This is the ReadingList Class, a List of Readings that the Sensor receives.
 */

public class ReadingList {

    private static final String LIST_IS_NOT_VALID = "List is not valid";
    private List<Reading> readings;

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
     * @return returns complete list of readings stored in attribute.
     */

    List<Reading> getListOfReadings() {
        return this.readings;
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     **/
    Reading getMostRecentReading() {
        Reading error = new Reading(NaN, new GregorianCalendar(1900, 0, 1).getTime());
        if (isEmpty()) {
            return error;
        }
        Reading recentReading = this.readings.get(0);
        Date mostRecent = recentReading.getDate();
        for (Reading r : this.readings) {
            Date testDate = r.getDate();
            if (mostRecent.before(testDate)) {
                mostRecent = testDate;
                recentReading = r;
            }
        }
        return recentReading;
    }

    /**
     * This method returns the most recent reading value a Reading List.
     *
     * @return the most recent reading value or NaN when the Reading List is empty
     */
    public double getMostRecentValue() {
        if (this.readings.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading().getValue();
    }

    /**
     * Returns return a list with every day with readings between two given dates.
     * Returns the date of the first reading for each day (no duplicated days)
     *
     * @param dayMin start date given by user, will be the start of the  date interval;
     * @param dayMax end date given by user, will be the end of the date interval;
     * @return list of dates of readings between the given dates
     * @author Daniela
     */
    List<Date> getDaysWithReadingsBetweenDates(Date dayMin, Date dayMax) {
        List<Date> daysWithReadings = new ArrayList<>();
        List<Integer> daysProcessed = new ArrayList<>();

        Date startDate = getFirstSecondOfDay(dayMin);
        Date endDate = getLastSecondOfDay(dayMax);

        for (int i = 0; i < readings.size(); i++) {
            Date currentReadingDate = readings.get(i).getDate();
            if (isReadingDateBetweenTwoDates(currentReadingDate, startDate, endDate)) {
                GregorianCalendar aux = new GregorianCalendar();
                aux.setTime(currentReadingDate);
                Integer readingDay = aux.get(Calendar.DAY_OF_MONTH);

                if (!daysProcessed.contains(readingDay)) {
                    daysProcessed.add(readingDay);
                    daysWithReadings.add(currentReadingDate);
                }
            }
        }
        return daysWithReadings;
    }

    /**
     * Gets a date corresponding to the 1st second of a given day
     *
     * @param day the day to use as reference
     * @return date with 1st second of given day
     * @author Daniela
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
     * @author Daniela
     */
    Date getLastSecondOfDay(Date day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
    }


    /**
     * Method to see if a reading date is between two dates
     *
     * @param readingDate reading date we want to verify
     * @param startDate   start date given by user, will be the start of the  date interval;
     * @param endDate     end date given by user, will be the end of the date interval;
     * @return true if reading date is between dates, false if it isn't
     * @author Daniela
     */
    boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
        return (readingDate.after(startDate) || readingDate.equals(startDate)) &&
                (readingDate.before(endDate) || readingDate.equals(endDate));
    }

    /**
     * This method will receive a day, go through the reading list for value readings that took place on that day,
     * and return a list of doubles with those values.
     *
     * @param day the day to look for readings
     * @return returns a list with every value of readings that was recorded on that particular day.
     */
    List<Double> getValuesOfSpecificDayReadings(Date day) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (int i = 0; i < readings.size(); i++) {
            if (compareDayMonthAndYearBetweenDates(readings.get(i).getDate(), day)) {
                valueReadingsFromGivenDay.add(readings.get(i).getValue());
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
     */
    boolean compareDayMonthAndYearBetweenDates(Date r1, Date r2) {
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
     * This method returns the Maximum Value of the Reading of a Given Day.
     *
     * @param dateGiven date given
     * @return get maximum value reading in a given day
     */
    double getMaximumOfGivenDayValueReadings(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date beginDay = cal.getTime();

        cal.setTime(dateGiven);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date endDay = cal.getTime();
        ReadingList dayReadings = matchByDate(beginDay, endDay);

        if (dayReadings.isEmpty()) {
            throw new IllegalArgumentException("The day given has no readings.");
        }
        double maxValue = dayReadings.get(0).getValue();
        for (Reading r : dayReadings.getListOfReadings()) {
            double currentValue = r.getValue();
            maxValue = Math.max(maxValue, currentValue);
        }
        return maxValue;
    }

    /**
     * This method receives two dates and checks the readings to match those that
     * have a date contained in the interval given as parameter
     *
     * @return list of readings contained in interval given as parameter
     */
    ReadingList matchByDate(Date beginDate, Date endDate) {
        ReadingList finalList = new ReadingList();
        for (Reading r : this.readings) {
            Date readingDate = r.getDate();
            if (readingDate.after(beginDate) && readingDate.before(endDate)) {
                finalList.addReading(r);
            }
        }
        return finalList;
    }


    boolean checkIfListValid(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException(LIST_IS_NOT_VALID);
        }
        return true;
    }


    /**
     * Method that gives the Average of Readings between two dates (given days)
     * It calculates the average of all days, considering the average of each day.
     * <p>
     * It will throw an IllegalArgumentException if there are no readings between the selected dates
     *
     * @param minDate the lower (min) date for interval comparison
     * @param maxDate the upper (max) date for interval comparison
     * @return the average of all values in the reading list between the two given dates
     * @author Daniela
     * @linkplain US623
     */
    public double getAverageReadingsBetweenDates(Date minDate, Date maxDate) {
        List<Date> daysWithReadings = getDaysWithReadingsBetweenDates(minDate, maxDate);
        if (daysWithReadings.isEmpty()) {
            throw new IllegalArgumentException("Warning: average value not calculated - no readings available.");
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
     * Simple method to indicate if this reading list is empty i.e. has no registered readings.
     *
     * @return whether the list is empty or not.
     */
    public boolean isEmpty() {
        return readings.isEmpty();
    }

    /**
     * Method that gives the Total Value of Readings on a given day (Date).
     *
     * @param givenDate given date
     * @return sum
     */
    public double getTotalValueOfReadingOnGivenDay(Date givenDate) {
        List<Double> totalValuesFromDaysWithReadings = new ArrayList<>();
        List<Double> valueReadingsThatMatchDay = getValuesOfSpecificDayReadings(givenDate);
        if (valueReadingsThatMatchDay.isEmpty()) {
            throw new IllegalStateException("Warning: Total value was not calculated - no readings were available.");
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
        if (valueList.isEmpty()) {
            return 0;
        }
        for (Double aValueList : valueList) {
            sum = sum + aValueList;
        }
        return sum;
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
     * Method checks if reading list contains reading given as parameter.
     *
     * @param reading reading to check.
     * @return returns true if list contains reading, false if it does not contain reading.
     */

    public boolean contains(Reading reading) {
        return readings.contains(reading);
    }

    /**
     * This method receives an index as parameter and gets a reading from reading list.
     *
     * @return returns reading that corresponds to index.
     */
    public Reading get(int index) {
        return this.readings.get(index);
    }

    /**
     * Getter (array of readings)

     * @return array of readings
     */
    Reading[] getElementsAsArray() {
        int sizeOfResultArray = readings.size();
        Reading[] result = new Reading[sizeOfResultArray];
        for (int i = 0; i < readings.size(); i++) {
            result[i] = readings.get(i);
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





