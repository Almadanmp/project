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
     * Method receives a date and will return the average of all recorded value reading from the given date's month.
     *
     * @return average value of all reading from given date's month
     */
    double getAverageReadingsFromGivenMonth(Date dateGiven) {
        List<Date> datesOfMonth = getDatesOfMonthWithReadings(dateGiven);
        double sum = 0;
        int counter = 0;
        for (Date d : datesOfMonth) {
            for (Reading r : this.readings) {
                if (r.getDate() == d) {
                    sum = sum + r.getValue();
                    counter++;
                }
            }
        }
        if (counter == 0) {
            return 0;
        }
        return sum / counter;
    }

    /**
     * Get before start and after end month
     *
     * @param dateGiven date given
     * @return date one second before date given
     */
    private Date getDateBeforeStartMonth(Date dateGiven) {
        ReadingList rl1 = new ReadingList();
        Date startOfMonth = rl1.getFirstDateOfMonthFromGivenDate(dateGiven);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(startOfMonth);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime();
    }

    private Date getDateAfterEndMonth(Date dateGiven) {
        ReadingList rl1 = new ReadingList();
        Date endOfMonth = rl1.getLastDateOfMonthFromGivenDate(dateGiven);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(endOfMonth);
        cal.add(Calendar.SECOND, 1);
        return cal.getTime();
    }

    /**
     * Method receives a date and will return a list with every date with readings in the given date's month.
     *
     * @param dateGiven date that will correspond to the month where to look for readings
     * @return list of dates of readings from the given date's month
     */
    List<Date> getDatesOfMonthWithReadings(Date dateGiven) {
        Date dateBeforeStartMonth = getDateBeforeStartMonth(dateGiven);
        Date dateAfterEndMonth = getDateAfterEndMonth(dateGiven);

        List<Date> datesList = new ArrayList<>();
        for (Reading mReading : readings) {
            Date currentReadingDate = mReading.getDate();
            if ((currentReadingDate.after(dateBeforeStartMonth)) && (currentReadingDate.before(dateAfterEndMonth))) {
                datesList.add(currentReadingDate);

            }
        }
        return datesList;
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
     * This method will receive a date that corresponds to a date on a given month, and will return
     * the first date within that month.
     *
     * @return first date from the given date's month
     */
    Date getFirstDateOfMonthFromGivenDate(Date dateGiven) {
        GregorianCalendar firstDate = new GregorianCalendar();
        firstDate.setTime(dateGiven);
        firstDate.set(Calendar.DAY_OF_MONTH, firstDate.getActualMinimum(Calendar.DAY_OF_MONTH));
        firstDate.set(Calendar.MINUTE, 0);
        firstDate.set(Calendar.HOUR_OF_DAY, 0);
        firstDate.set(Calendar.SECOND, 0);
        return firstDate.getTime();
    }

    /**
     * This method will receive a date that corresponds to a date on a given month, and will return
     * the last date within that month.
     *
     * @return last date from the given date's month
     */
    Date getLastDateOfMonthFromGivenDate(Date dateGiven) {
        GregorianCalendar lastDate = new GregorianCalendar();
        lastDate.setTime(dateGiven);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        lastDate.set(Calendar.HOUR_OF_DAY, 23);
        lastDate.set(Calendar.MINUTE, 59);
        lastDate.set(Calendar.SECOND, 59);
        return lastDate.getTime();
    }

    /**
     * This method will receive a date that corresponds to a date on a given month and year, and will
     * remove every reading from List<Reading> that is not from that month and year.
     *
     * @return List<Reading> with readings from given date's month and year
     */
    List<Reading> removeReadingsWithDifferentMonthAndYearFromDateGiven(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        for (int i = readings.size() - 1; i >= 0; i--) {
            Reading reading = readings.get(i);
            cal.setTime(reading.getDate());
            if ((cal.get(Calendar.MONTH) != month) || (cal.get(Calendar.YEAR) != year)) {
                this.readings.remove(reading);
            }
        }
        return this.readings;
    }

    /**
     * This method will receive a date that corresponds to a date on a given month and year,
     * go through every reading in the object list of readings and return the days
     * of that month where readings took place
     *
     * @return list of days of given date's month and year with readings
     */
    List<Integer> getDaysOfMonthWithReadings(Date dateGiven) {
        Date dateBeforeStartMonth = getDateBeforeStartMonth(dateGiven);
        Date dateAfterEndMonth = getDateAfterEndMonth(dateGiven);
        List<Integer> daysWithReadings = new ArrayList<>();
        for (Reading reading : readings) {
            Date currentReadingDate = reading.getDate();
            if (currentReadingDate.after(dateBeforeStartMonth) && currentReadingDate.before(dateAfterEndMonth)) {
                GregorianCalendar temporaryCalend = new GregorianCalendar();
                temporaryCalend.setTime(currentReadingDate);
                Integer dayToAdd = temporaryCalend.get(Calendar.DAY_OF_MONTH);
                if (!daysWithReadings.contains(dayToAdd)) {
                    daysWithReadings.add(dayToAdd);
                }
            }
        }
        return daysWithReadings;
    }

    /**
     * This method will receive a day within a month, go through the reading list
     * for value readings that took place on that day, and return a list of doubles
     * with those values.
     *
     * @param dayOfMonth is an integer that matches the day that we want to look for readings
     * @return returns an ArrayList with every value readings that was recorded on that particular day.
     * Does not filter for month - only finds readings of a day inside a month. Year / month must be filtered elsewhere.
     */
    List<Double> getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(int dayOfMonth) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (Reading r : readings) {
            GregorianCalendar dateOfReading = new GregorianCalendar();
            dateOfReading.setTime(r.getDate());

            if ((dateOfReading.get(Calendar.DAY_OF_MONTH)) == dayOfMonth) {
                valueReadingsFromGivenDay.add(r.getValue());
            }
        }
        return valueReadingsFromGivenDay;
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
     * Method that receives a list of doubles that correspond to value readings and will return
     * the lowest value on that list.
     *
     * @param valueList list with value readings
     * @return returns the lowest of all value readings
     */
    double getLowestValueFromGivenList(List<Double> valueList) {
        checkIfListValid(valueList);
        ArrayList<Double> arrayList = new ArrayList<>(valueList);
        return Collections.min(arrayList);
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
     * This method will receive a date that corresponds to a date on a given month, and will return
     * the average of that month's maximum value readings.
     *
     * @return average of month's maximum value readings
     */
    double getAverageOfMaximumValuesInTheReadingsOfMonth(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(dateGiven);
        List<Double> maxValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            double maxValueOfDay;
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(day);
            maxValueOfDay = getHighestValueInList(valueReadingsThatMatchDay);
            maxValuesFromDaysWithReadings.add(maxValueOfDay);
        }
        return getAvgFromList(maxValuesFromDaysWithReadings);
    }


    /**
     * Method that receives a list of doubles that correspond to value readings and will return
     * the highest value on that list.
     *
     * @return returns the highest of all value readings within list
     */
    double getHighestValueInList(List<Double> valueList) {
        checkIfListValid(valueList);
        ArrayList<Double> arrayList = new ArrayList<>(valueList);
        return Collections.max(arrayList);
    }

    /**
     * Method that receives a date and will return the average of all recorded value readings
     * from the given date's day.
     *
     * @return average value of all reading from given date's day
     */
    double getAverageOfGivenDayValueReadings(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        Date beginDay = cal.getTime();
        cal.setTime(dateGiven);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        Date endDay = cal.getTime();

        double sum = 0;
        int counter = 0;
        for (Reading mReading : readings) {
            Date currentReadingDate = mReading.getDate();
            if (currentReadingDate.after(beginDay) && currentReadingDate.before(endDay)) {
                sum += mReading.getValue();
                counter++;
            }
        }
        if (counter == 0) {
            return -1;
        }
        return sum / counter;
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
        double maxValue = dayReadings.getListOfReadings().get(0).getValue();
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

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof ReadingList)) {
            return false;
        }
        ReadingList list = (ReadingList) testObject;
        return Arrays.equals(this.getListOfReadings().toArray(), list.getListOfReadings().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}





