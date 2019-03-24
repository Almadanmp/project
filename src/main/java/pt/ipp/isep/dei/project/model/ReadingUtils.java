package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.lang.Double.NaN;

/**
 * This class will contain a value read by a Sensor, associated with a date of said reading.
 */
public class ReadingUtils {

    private static final String EMPTY_LIST = "The reading list is empty.";

    /**
     * This method returns the most recent reading value a Reading List.
     *
     * @return the most recent reading value or NaN when the Reading List is empty
     * @author Carina (US600 e US605)
     */
    static double getMostRecentValue(List<Reading> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading(list).getValue();
    }

    /**
     * Method that goes through every Reading in the list and
     * returns the reading with the most recent Date.
     *
     * @return most recent reading
     * @author Carina (US600 e US605)
     **/
    static Reading getMostRecentReading(List<Reading> list) {
        Reading error = new Reading(NaN, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime());
        if (list.isEmpty()) {
            return error;
        }
        Reading recentReading = list.get(0);
        Date mostRecent = recentReading.getDate();
        for (Reading r : list) {
            Date testDate = r.getDate();
            if (testDate.after(mostRecent)) {
                mostRecent = testDate;
                recentReading = r;
            }
        }
        return recentReading;
    }

    /**
     * This method returns a double value that represents the minimum value in a ReadingList.
     *
     * @return a double value that represents the minimum value in a ReadingList.
     */
    static double getMinValueInReadingList(List<Reading> list) {
        double result = list.get(0).getValue();
        for (Reading r : list) {
            result = Math.min(r.getValue(), result);
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
    static List<Reading> getListOfMaxValuesForEachDay(List<Reading> list) {
        List<Reading> result = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        for (Reading r : list) {
            Date aux = getFirstSecondOfDay(r.getDate());
            if (!dateList.contains(aux)) {
                Reading maxOfTheDay = getMaxValueOfTheDay(r.getDate(), list);
                dateList.add(aux);
                result.add(maxOfTheDay);
            }
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
    static List<Reading> getReadingListOfReadingsWithSpecificValue(Double value, List<Reading> list) {
        List<Reading> result = new ArrayList<>();
        for (Reading r : list) {
            if (Double.compare(r.getValue(), value) == 0) {
                result.add(r);
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
    static List<Reading> getReadingListBetweenDates(Date initialDate, Date finalDate, List<Reading> list) {
        List<Reading> result = new ArrayList<>();
        for (Reading r : list) {
            if (ReadingUtils.isReadingDateBetweenTwoDates(r.getDate(), initialDate, finalDate)) {
                result.add(r);
            }
        }
        return result;
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
    static boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
        return (readingDate.after(startDate) || readingDate.equals(startDate)) &&
                (readingDate.before(endDate) || readingDate.equals(endDate));
    }
    /**
     * Gets a date corresponding to the first second of a given day
     *
     * @param day the day to use as reference
     * @return date with 1st second of given day
     * @author Daniela - US623
     */
    static Date getFirstSecondOfDay(Date day) {
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
    static Date getLastSecondOfDay(Date day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
    }

    // TODO - date methods could be in dateutils
    /**
     * US630
     * This method returns a Reading that represents the maximum value of a Reading in a ReadingList
     * from a given day.
     *
     * @param day is the day we want to know the maximum value.
     * @return a double that represents the maximum value of the day.
     */
    static Reading getMaxValueOfTheDay(Date day, List<Reading> list) {
        double auxValue = getValuesOfSpecificDayReadings(day, list).get(0);
        Reading result = getAReadingWithSpecificDay(day, list);
        Date auxDay = getFirstSecondOfDay(day);
        for (Reading r : list) {
            Date readingDate = getFirstSecondOfDay(r.getDate());
            if (readingDate.equals(auxDay) && r.getValue() > auxValue) {
                result = r;
                auxValue = result.getValue();
            }
        }
        return result;
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
    static boolean compareDayMonthAndYearBetweenDates(Date r1, Date r2) {
        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.setTime(r1);

        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTime(r2);

        return (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) &&
                (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) &&
                (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
    }

    /**
     * This method will receive a day, go through the reading list for value readings that took place on that day,
     * and return a list of doubles with those values.
     *
     * @param day the day to look for readings
     * @return returns a list with every value of readings that was recorded on that particular day.
     * @author Daniela - US623
     */
    static List<Double> getValuesOfSpecificDayReadings(Date day, List<Reading> list) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (ReadingUtils.compareDayMonthAndYearBetweenDates(getValueDate(i, list), day)) {
                valueReadingsFromGivenDay.add(getValueReading(i, list));
            }
        }
        return valueReadingsFromGivenDay;
    }

    /**
     * This method receives an index as parameter and gets a value reading from reading list.
     *
     * @param index the index of the Reading we want to get value from
     * @return returns value reading that corresponds to index.
     */
    static double getValueReading(int index, List<Reading> list) {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = list.get(index);
        return reading.getValue();
    }

    /**
     * This method receives an index as parameter and gets a reading date from reading list.
     *
     * @param index the index of the Reading we want to get date from
     * @return returns date reading that corresponds to index.
     */
    static Date getValueDate(int index, List<Reading> list) {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        Reading reading = list.get(index);
        return reading.getDate();
    }


    /**
     * US630
     * This method returns a Reading with a specific day from a given ReadingList.
     *
     * @param date is the Day of the reading we want to get.
     * @return a Reading from the ReadingList with a Specific Date.
     */
    static Reading getAReadingWithSpecificDay(Date date, List<Reading> list) {
        Reading result = null;
        for (Reading r : list) {
            if (ReadingUtils.compareDayMonthAndYearBetweenDates(r.getDate(), date)) {
                result = r;
                break;
            }
        }
        return result;
    }

}
