package pt.ipp.isep.dei.project.model;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This is a utility class for Readings. All methods are Static.
 */
@Service
public class ReadingUtils {


    private static final String EMPTY_LIST = "The reading list is empty.";


    private ReadingUtils() {
    }

    /**
     * This mehtod receives a list of readings and a string of a sensor ID,
     * checks for every reading within the list with the same sensorID, and
     * returns a list of readings with the given sensor ID.
     *
     * @param readings list of readings
     * @param sensorID a string of a sensor ID
     * @return a list of readings that have the same sensor ID as the one given
     * as parameter.
     **/
    public static List<Reading> getReadingsBySensorID(String sensorID, List<Reading> readings) {
        List<Reading> subArray = new ArrayList<>();
        for (Reading r : readings) {
            String readingSensorID = r.getSensorID();
            if (sensorID.equals(readingSensorID)) {
                subArray.add(r);
            }
        }
        return subArray;
    }

    /**
     * This method receives a list of readings, checks for every sensor ID
     * in every Reading contained in the list and returns a list of strings
     * of all sensor IDs.
     *
     * @param readings a list of readings
     * @return a list of strings of all sensor IDs from the list of readings
     **/
    public static List<String> getSensorIDs(List<Reading> readings) {
        List<String> sensorIDs = new ArrayList<>();
        for (Reading r : readings) {
            String sensorID = r.getSensorID();
            if (!sensorIDs.contains(sensorID)) {
                sensorIDs.add(sensorID);
            }
        }
        return sensorIDs;
    }

    /**
     * This method receives an index as parameter and gets a value reading from reading list.
     *
     * @param index the index of the Reading we want to getDB value from
     * @return returns value reading that corresponds to index.
     */
     static double getValueReading(List<Reading> readings, int index) {
        if (readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = readings.get(index);
        return reading.getValue();
    }

    /**
     * This method receives an index as parameter and gets a reading date from reading list.
     *
     * @param index the index of the Reading we want to getDB date from
     * @return returns date reading that corresponds to index.
     */
    public static Date getValueDate(List<Reading> readings, int index) {
        if (readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = readings.get(index);
        return reading.getDate();
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     * @author Carina (US600 e US605)
     **/
    public static Reading getMostRecentReading(List<Reading> readings) {
        Reading error = new Reading(0, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), "C", "ERROR");
        if (readings.isEmpty()) {
            return error;
        }
        Reading recentReading = readings.get(0);
        Date mostRecent = recentReading.getDate();
        for (Reading r : readings) {
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

    public static Date getMostRecentReadingDate(List<Reading> readings) {
        return getMostRecentReading(readings).getDate();
    }


    /**
     * This method returns the most recent reading value a Reading List.
     *
     * @return the most recent reading value or NaN when the Reading List is empty
     * @author Carina (US600 e US605)
     */
    public static double getMostRecentValue(List<Reading> readings) {
        if (readings.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading(readings).getValue();
    }

    /**
     * Method that gives the Total Value of readings on a given day (Date).
     *
     * @param givenDate given date
     * @return sum
     * @author André (US620)
     */
    public static double getValueReadingsInDay(Date givenDate, List<Reading> sensorReadings) {
        List<Double> totalValuesFromDaysWithReadings = new ArrayList<>();
        List<Double> valueReadingsThatMatchDay = getValuesOfSpecificDayReadings(sensorReadings, givenDate);
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
    static double getListSum(List<Double> valueList) {
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
    public static Date getFirstSecondOfDay(Date day) {
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
    public static Date getLastSecondOfDay(Date day) {
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
     * @author Daniela - US623
     */
    public static boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
        return (readingDate.after(startDate) || readingDate.equals(startDate)) &&
                (readingDate.before(endDate) || readingDate.equals(endDate));
    }


    /**
     * This method will receive a day, go through the reading list for value readings that took place on that day,
     * and return a list of doubles with those values.
     *
     * @param day the day to look for readings
     * @return returns a list with every value of readings that was recorded on that particular day.
     * @author Daniela - US623
     */
    public static List<Double> getValuesOfSpecificDayReadings(List<Reading> readings, Date day) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (int i = 0; i < readings.size(); i++) {

            if (compareDayMonthAndYearBetweenDates(getValueDate(readings, i), day)) {
                valueReadingsFromGivenDay.add(getValueReading(readings, i));
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
    private static boolean compareDayMonthAndYearBetweenDates(Date r1, Date r2) {
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
    public static double getAvgFromList(List<Double> valueList) {
        if (valueList.isEmpty()) {
            return 0;
        }
        double sum = getListSum(valueList);
        return (sum / valueList.size());
    }


    /**
     * Auxiliary method for getFirstHottestDayInGivenPeriod
     *
     * @param temperature given for finding first day in period with that temperature
     * @param dates       for selecting the first day with given temperature from date list
     * @return first date where given temperature was registered
     */

    public static Date getFirstDayForGivenTemperature(double temperature, List<Date> dates, List<Reading> sensorReadings) {
        List<Date> daysWithTemperature = new ArrayList<>();
        for (Date date : dates) {
            if (getValuesOfSpecificDayReadings(sensorReadings, date).contains(temperature)) {
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

    public static double getMaxValue(List<Date> list, List<Reading> sensorReadings) {
        ArrayList<Double> values = new ArrayList<>();
        for (Date day : list) {
            values.addAll(getValuesOfSpecificDayReadings(sensorReadings, day));
        }
        return Collections.max(values);
    }

    /**
     * US630
     * This method filters the existing AreaReadingList so that it returns the AreaReadingList with the chosen value.
     *
     * @param value is the value we want to choose.
     * @return a AreaReadingList with a chosen value.
     */
    public static List<Reading> getReadingListOfReadingsWithSpecificValue(List<Reading> readings, Double value) {
        List<Reading> result = new ArrayList<>();
        for (Reading r : readings) {
            if (Double.compare(r.getValue(), value) == 0) {
                result.add(r);
            }
        }
        return result;
    }

    /**
     * US630
     * This method returns a Reading with a specific day from a given AreaReadingList.
     *
     * @param date is the Day of the reading we want to getDB.
     * @return a Reading from the AreaReadingList with a Specific Date.
     */
    static Reading getAReadingWithSpecificDay(List<Reading> readings, Date date) {
        Reading result = null;
        for (Reading r : readings) {
            if (compareDayMonthAndYearBetweenDates(r.getDate(), date)) {
                result = r;
                break;
            }
        }
        return result;
    }


    /**
     * US630
     * This method returns a Reading that represents the maximum value of a Reading in a AreaReadingList
     * from a given day.
     *
     * @param day is the day we want to know the maximum value.
     * @return a double that represents the maximum value of the day.
     */
    static Reading getMaxValueOfTheDay(List<Reading> readings, Date day) {
        double auxValue = getValuesOfSpecificDayReadings(readings, day).get(0);

        Reading result = getAReadingWithSpecificDay(readings, day);

        Date auxDay = getFirstSecondOfDay(day);
        for (Reading r : readings) {
            Date readingDate = getFirstSecondOfDay(r.getDate());
            if (readingDate.equals(auxDay) && r.getValue() > auxValue) {
                result = r;
                auxValue = result.getValue();
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
    public static List<Reading> getListOfMaxValuesForEachDay(List<Reading> readings) {
        List<Reading> result = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        for (Reading r : readings) {
            Date aux = getFirstSecondOfDay(r.getDate());
            if (!dateList.contains(aux)) {
                Reading maxOfTheDay = getMaxValueOfTheDay(readings, r.getDate());
                dateList.add(aux);
                result.add(maxOfTheDay);
            }
        }
        return result;
    }

    /**
     * This method returns a double value that represents the minimum value in a AreaReadingList.
     *
     * @return a double value that represents the minimum value in a AreaReadingList.
     */
    public static double getMinValueInReadingList(List<Reading> readings) {
        double result = readings.get(0).getValue();
        for (Reading r : readings) {
            result = Math.min(r.getValue(), result);
        }
        return result;
    }
}




