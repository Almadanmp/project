package Sprint_0;

import java.util.List;
import java.util.*;

/**
 * This is the ReadingList Class, a List of Readings that the Sensor receives.
 */

public class ReadingList {
    List<Reading> mReadings;

    /**
     * Empty Constructor to always allow the creation of an ArrayList of Readings.
     */

    public ReadingList() {
        mReadings = new ArrayList<>();
    }

    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param reading receives a reading.
     * @return returns true if the input reading was added successfully.
     * returns false if the input reading was rejected.
     */

    public boolean addReading(Reading reading) {
        if (!(mReadings.contains(reading))) {
            mReadings.add(reading);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return returns complete list of readings stored in attribute.
     */

    public List<Reading> getListOfReadings() {
        return this.mReadings;
    }

    /**
     * @return returns the reading with the most recent date, that is, the date closest to now.
     */

    public Reading getMostRecentReading() {
        int mostRecentReadingIndex = 0;
        for (int i = 0; i < mReadings.size() - 1; i++) {
            Date firstDate = mReadings.get(i).getmDate();
            Date secondDate = mReadings.get(i + 1).getmDate();
            if (firstDate.before(secondDate)) {
                mostRecentReadingIndex = i + 1;
            }
        }
        return mReadings.get(mostRecentReadingIndex);
    }

    /**
     * @param dayOfMonth is an integer that matches the day that we want to look for readings of.
     * @return gives us an ArrayList with the values of all the readings from a particular day.
     * Does not filter for month - only finds readings of a day inside a month. Year / month must be filtered elsewhere.
     */

    public List<Double> getValueReadingsThatMatchDayWithinMonth(int dayOfMonth) {
        ArrayList<Double> valuesOfDay = new ArrayList<>();
        for (Reading r : mReadings) {
            GregorianCalendar tempCalendar = new GregorianCalendar();
            tempCalendar.setTime(r.getmDate());
            if ((tempCalendar.get(Calendar.DAY_OF_MONTH)) == dayOfMonth) {
                valuesOfDay.add(r.getmValue());
            }
        }
        return valuesOfDay;
    }

    /**
     * @return returns the mean of all values of readings
     */

    public double getMeanOftheDay(int year, int month, int day) {
        GregorianCalendar dayMin = new GregorianCalendar(year, month, day - 1, 23, 59, 59);
        GregorianCalendar dayMax = new GregorianCalendar(year, month, day + 1);
        double sum = 0;
        int counter = 0;
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dayMin.getTime()) && currentReadingDate.before(dayMax.getTime())) {
                sum += mReadings.get(i).getmValue();
                counter++;
            }
        }
        if (counter == 0) {
            return 0;
        }
        return sum / counter;
    }

    /**
     * @param year  filters the date through year.
     * @param month filters the date through month.
     * @return gives the days within the filtered year and month that contain valid readings.
     */

    public List<Integer> getDaysOfMonthWithReadings(int year, int month) {
        GregorianCalendar actualMonth = new GregorianCalendar(year, month, 1);
        GregorianCalendar maxDate = new GregorianCalendar(year, month + 1, 1);
        List<Integer> daysArray = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.compareTo(actualMonth.getTime()) >= 0 && currentReadingDate.before(maxDate.getTime())) {
                GregorianCalendar tempCalendar = new GregorianCalendar();
                tempCalendar.setTime(currentReadingDate);
                int auxDay = tempCalendar.get(Calendar.DAY_OF_MONTH);
                if (!daysArray.contains(auxDay)) {
                    daysArray.add(auxDay);
                }
            }
        }
        return daysArray;
    }

    /**
     * @param year  filters the date through year.
     * @param month filters the date through month.
     * @return returns the average of all mean values of days with valid readings within a month.
     */

    public double getMeanOfRecordedValuesMonth(int year, int month) {
        List<Integer> daysArray = getDaysOfMonthWithReadings(year, month);
        double sum = 0;
        if (daysArray.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < daysArray.size(); i++) {
            sum += getMeanOftheDay(year, month, daysArray.get(i));
        }

        return sum / daysArray.size();
    }

    /**
     * @param list receives a list of doubles.
     * @return returns the average of all values contained within array.
     */

    public double getMeanOfList(List<Double> list) {
        double temporaryValue = 0;
        for (int posInArray = 0; posInArray < list.size(); posInArray++) {
            temporaryValue = list.get(posInArray) + temporaryValue;
        }
        return (temporaryValue / list.size());
    }

    /**
     * @param valuesOfDay is a list of all the values obtained from all the valid readings within a day.
     * @return returns the lowest value of all the readings within a day.
     * Duplicate warnings are disabled because method is similar to getHighestValue, but still needed.
     */

    @SuppressWarnings("Duplicates")

    public double getLowestValueInList(List<Double> valuesOfDay) {
        double minValueOfDay = valuesOfDay.get(0);
        for (double value : valuesOfDay) {
            if (value < minValueOfDay) {
                minValueOfDay = value;
            }
        }
        return minValueOfDay;
    }

    /**
     * @param valuesOfDay is a list of all the values obtained from all the valid readings within a day.
     * @return returns the highest value of all the readings within a day.
     * Duplicate warnings are disabled because method is similar to getHighestValue, but still needed.
     */

    @SuppressWarnings("Duplicates")

    public double getHighestValueInList(List<Double> valuesOfDay) {
        double maxValueOfDay = valuesOfDay.get(0);
        for (double value : valuesOfDay) {
            if (value > maxValueOfDay) {
                maxValueOfDay = value;
            }
        }
        return maxValueOfDay;
    }

    /**
     * @param year  filters date through year.
     * @param month filters date through month.
     * @return gives the average of all the minimum values of each day in which there were valid readings.
     */

    public double getAverageOfMinimumValuesInTheReadingsOfMonth(int year, int month) {
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(year, month);
        List<Double> minsOfDaysInMonth = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchDayWithinMonth(day);
            double minValueOfDay;
            minValueOfDay = getLowestValueInList(valueReadingsThatMatchDay);
            minsOfDaysInMonth.add(minValueOfDay);
        }
        return getMeanOfList(minsOfDaysInMonth);
    }

    /**
     * @param year  filters date through year.
     * @param month filters date through month.
     * @return gives the average of all the maximum values of each day in which there were valid readings.
     */

    public double getAverageOfMaximumValuesInTheReadingsOfMonth(int year, int month) {
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(year, month);
        List<Double> maxsOfDaysInMonth = new ArrayList<>();
        for (int day: daysWithReadings) {
            List<Double> valuesOfDay = getValueReadingsThatMatchDayWithinMonth(day);
            double maxValueOfDay;
            maxValueOfDay = getHighestValueInList(valuesOfDay);
            maxsOfDaysInMonth.add(maxValueOfDay);
        }
        return getMeanOfList(maxsOfDaysInMonth);
    }
}




