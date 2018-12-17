package PT.IPP.ISEP.DEI.Project.Model;

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

    public double getMostRecentValueOfReading() {
        int mostRecentReadingIndex = 0;
        for (int i = 0; i < mReadings.size() - 1; i++) {
            Date firstDate = mReadings.get(i).getmDate();
            Date secondDate = mReadings.get(i + 1).getmDate();
            if (firstDate.before(secondDate)) {
                mostRecentReadingIndex = i + 1;
            }
        }
        return mReadings.get(mostRecentReadingIndex).getmValue();
    }
    /**Method receives a date and will return the average of all recorded value reading from the given date's month.
     * @return average value of all reading from given date's month
     */
    public double getAverageOfAllRecordedValueReadingsFromGivenMonth(Date dateGiven) {
        List<Date> datesOfMonth = getListOfDatesOfMonthWithReadings(dateGiven);
        double sum = 0;
        int counter = 0;
        for (Date d : datesOfMonth) {
            for(Reading r : this.mReadings) {
                if(r.getmDate() == d) {
                    sum = sum + r.getmValue();
                    counter++;
                }
            }
        }
        if(counter == 0) {
            return 0;
        }
        return sum / counter;
    }

    /**
     * Get before start and after end month
     * @param dateGiven
     * @return
     */
    public Date getDateBeforeStartMonth (Date dateGiven){
        ReadingList rl1 = new ReadingList();
        Date startOfMonth = rl1.getFirstDateOfMonthFromGivenDate(dateGiven);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(startOfMonth);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime();
    }

    public Date getDateAfterEndMonth (Date dateGiven){
        ReadingList rl1 = new ReadingList();
        Date endOfMonth = rl1.getLastDateOfMonthFromGivenDate(dateGiven);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(endOfMonth);
        cal.add(Calendar.SECOND, +1);
        return cal.getTime();
    }

    /**Method receives a date and will return a list with every date in the given date's month.
     * @param dateGiven date that will correspond to the month where to look for readings
     * @return list of dates of readings from the given date's month
     */
    public List<Date> getListOfDatesOfMonthWithReadings(Date dateGiven) {
        Date dateBeforeStartMonth = getDateBeforeStartMonth(dateGiven);
        Date dateAfterEndMonth = getDateAfterEndMonth(dateGiven);

        List<Date> datesList = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if ((currentReadingDate.after(dateBeforeStartMonth)) && (currentReadingDate.before(dateAfterEndMonth))) {
                    datesList.add(currentReadingDate);

            }
        }
        return datesList;
    }

    /**This method will receive a date that corresponds to a date on a given month, and will return
     * the first date within that month.
     * @return first date from the given date's month
     */
    public Date getFirstDateOfMonthFromGivenDate (Date dateGiven){
        GregorianCalendar firstDate = new GregorianCalendar();
        firstDate.setTime(dateGiven);
        firstDate.set(Calendar.DAY_OF_MONTH, firstDate.getActualMinimum(Calendar.DAY_OF_MONTH));
        firstDate.set(Calendar.HOUR_OF_DAY,0);
        firstDate.set(Calendar.MINUTE,0);
        firstDate.set(Calendar.SECOND,0);
        return firstDate.getTime();
    }

    /**This method will receive a date that corresponds to a date on a given month, and will return
     * the last date within that month.
     * @return last date from the given date's month
     */
    public Date getLastDateOfMonthFromGivenDate (Date dateGiven){
        GregorianCalendar lastDate = new GregorianCalendar();
        lastDate.setTime(dateGiven);
        lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        lastDate.set(Calendar.HOUR_OF_DAY,23);
        lastDate.set(Calendar.MINUTE,59);
        lastDate.set(Calendar.SECOND,59);
        return lastDate.getTime();
    }

    /**This method will receive a date that corresponds to a date on a given month, and will return
     * the average of that month's minimum value readings.
     * @return average of month's minimum value readings
     */
    public double getAverageOfMinimumValuesInTheReadingsOfMonth(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(dateGiven);
        List<Double> minValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(day);
            double minValueOfDay;
            minValueOfDay = getLowestValueFromGivenList(valueReadingsThatMatchDay);
            minValuesFromDaysWithReadings.add(minValueOfDay);
        }
        return getAverageFromGivenList(minValuesFromDaysWithReadings);
    }

    /**This method will receive a date that corresponds to a date on a given month and year, and will
     * remove every reading from List<Reading> that is not from that month and year.
     * @return List<Reading> with readings from given date's month and year
     */
    public List<Reading> removeReadingsWithDifferentMonthAndYearFromDateGiven(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        for(int i = mReadings.size()-1; i>=0; i--) {
            Reading reading = mReadings.get(i);
            cal.setTime(reading.getmDate());
            if((cal.get(Calendar.MONTH) != month) || (cal.get(Calendar.YEAR) != year)) {
                this.mReadings.remove(reading);
            }
        }
        return this.mReadings;
    }

    /**This method will receive a date that corresponds to a date on a given month and year,
     * go through every reading in the object list of readings and return the days
     * of that month where readings took place
     * @return list of days of given date's month and year with readings
     */
    public List<Integer> getDaysOfMonthWithReadings(Date dateGiven) {
        Date dateBeforeStartMonth = getDateBeforeStartMonth(dateGiven);
        Date dateAfterEndMonth = getDateAfterEndMonth(dateGiven);

        List<Integer> daysWithReadings = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dateBeforeStartMonth) && currentReadingDate.before(dateAfterEndMonth)){
                GregorianCalendar temporaryCalend = new GregorianCalendar();
                temporaryCalend.setTime(currentReadingDate);
                int dayToAdd = temporaryCalend.get(Calendar.DAY_OF_MONTH);
                if (!daysWithReadings.contains(dayToAdd)) {
                    daysWithReadings.add(dayToAdd);
                }
            }
        }
        return daysWithReadings;
    }

    /**This method will receive a day within a month, go through the reading list
     * for value readings that took place on that day, and return a list of doubles
     * with those values.
     * @param dayOfMonth is an integer that matches the day that we want to look for readings
     * @return returns an ArrayList with every value readings that was recorded on that particular day.
     * Does not filter for month - only finds readings of a day inside a month. Year / month must be filtered elsewhere.
     */
    public List<Double> getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(int dayOfMonth) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (Reading r : mReadings) {
            GregorianCalendar dateOfReading = new GregorianCalendar();
            dateOfReading.setTime(r.getmDate());

            if ((dateOfReading.get(Calendar.DAY_OF_MONTH)) == dayOfMonth) {
                valueReadingsFromGivenDay.add(r.getmValue());
            }
        }
        return valueReadingsFromGivenDay;
    }

    /**Method that receives a list of doubles that correspond to value readings and will return
     * the lowest value on that list.
     * @param valueList list with value readings
     * @return returns the lowest of all value readings
     */
    public double getLowestValueFromGivenList(List<Double> valueList) {
        checkIfListValid(valueList);
            double minValue = valueList.get(0);

            for (double value : valueList) {
                if (value < minValue) {
                    minValue = value;
                }
            }
            return minValue;
        }


    /**This method receives a list of doubles that correspond to value readings and
     * will return the average value on that list.
     * @return returns the average of all values contained within that ArrayList
     */
    public double getAverageFromGivenList(List<Double> valueList) {
        double sum = 0;
        if(valueList.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < valueList.size(); i++) {
            sum =  sum + valueList.get(i);
        }
        return (sum / valueList.size());
    }

    /**This method will receive a date that corresponds to a date on a given month, and will return
     * the average of that month's maximum value readings.
     * @return average of month's maximum value readings
     */
    public double getAverageOfMaximumValuesInTheReadingsOfMonth(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(dateGiven);
        List<Double> maxValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(day);
            double maxValueOfDay;
            maxValueOfDay = getHighestValueInList(valueReadingsThatMatchDay);
            maxValuesFromDaysWithReadings.add(maxValueOfDay);
        }
        return getAverageFromGivenList(maxValuesFromDaysWithReadings);
    }

    /**Method that receives a list of doubles that correspond to value readings and will return
     * the highest value on that list.
     * @return returns the highest of all value readings within list
     */
    public double getHighestValueInList(List<Double> valueList) {
        checkIfListValid(valueList);

       double maxValue = valueList.get(0);
        for (double value : valueList) {
            if (value >= maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**Method that receives a date and will return the average of all recorded value readings
     * from the given date's day.
     * @return average value of all reading from given date's day
     */
    public double getAverageOfGivenDayValueReadings(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date beginDay = cal.getTime();

        cal.setTime(dateGiven);
        cal.add(Calendar.DAY_OF_MONTH, +1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date endDay = cal.getTime();

        double sum = 0;
        int counter = 0;
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(beginDay) && currentReadingDate.before(endDay)) {
                sum += mReadings.get(i).getmValue();
                counter++;
            }
        }
        if (counter == 0) {
            return -1;
        }
        return sum / counter;
    }

    /**Method receives a date and will return a list with every date in the given date's week
     * where readings took place.
     * @param dateGiven date that will correspond to the week where to look for readings
     * @return list of dates of readings from the given date's week
     */
    public List<Integer> getListOfDatesWithReadingsFromWeekOfDateGiven(Date dateGiven){
        ReadingList rl1 = new ReadingList();
        Date firstDateOfWeek = rl1.getFirstDateOfWeekFromDateGiven(dateGiven);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(firstDateOfWeek);
        cal.add(Calendar.SECOND, -1);
        Date dateBeforeStartOfWeek = cal.getTime();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(firstDateOfWeek);
        cal1.add(Calendar.DAY_OF_MONTH, 7);
        Date dateAfterEndOfWeek = cal1.getTime();

        List<Integer> daysWithReadings = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dateBeforeStartOfWeek) && currentReadingDate.before(dateAfterEndOfWeek)){
                GregorianCalendar temporaryCalend = new GregorianCalendar();
                temporaryCalend.setTime(currentReadingDate);
                int dayToAdd = temporaryCalend.get(Calendar.DAY_OF_MONTH);
                if (!daysWithReadings.contains(dayToAdd)) {
                    daysWithReadings.add(dayToAdd);
                }
            }
        }
        return daysWithReadings;
    }


    /**Method receives a date and will return the first date on the given date's week
     * (the first date on a given week corresponds to a Sunday at 0 hours, 0 minutes and 0 seconds).
     * @param dateGiven date that will correspond to the week where to look for the first day
     * @return first date from that given date's week
     */
    public Date getFirstDateOfWeekFromDateGiven(Date dateGiven) {
        GregorianCalendar firstDateOfWeek = new GregorianCalendar();
        firstDateOfWeek.setTime(dateGiven);
        int dayOfYear = firstDateOfWeek.get(Calendar.DAY_OF_YEAR);

        while (firstDateOfWeek.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            firstDateOfWeek.set(Calendar.DAY_OF_YEAR, --dayOfYear);
        }
        firstDateOfWeek.set(Calendar.HOUR_OF_DAY,0);
        firstDateOfWeek.set(Calendar.MINUTE,0);
        firstDateOfWeek.set(Calendar.SECOND, 0);
        firstDateOfWeek.set(Calendar.MILLISECOND,0);
        return firstDateOfWeek.getTime();
    }

    public double getAverageOfMinimumValuesInTheReadingsOfWeek(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getListOfDatesWithReadingsFromWeekOfDateGiven(dateGiven);
        List<Double> minValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(day);
            double minValueOfDay;
            minValueOfDay = getLowestValueFromGivenList(valueReadingsThatMatchDay);
            minValuesFromDaysWithReadings.add(minValueOfDay);
        }
        return getAverageFromGivenList(minValuesFromDaysWithReadings);
    }

    public double getAverageOfMaximumValuesInTheReadingsOfWeek(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getListOfDatesWithReadingsFromWeekOfDateGiven(dateGiven);
        List<Double> maxValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchGivenDayFromListOfOneMonthReadings(day);
            double maxValueOfDay;
            maxValueOfDay = getHighestValueInList(valueReadingsThatMatchDay);
            maxValuesFromDaysWithReadings.add(maxValueOfDay);
        }
        return getAverageFromGivenList(maxValuesFromDaysWithReadings);
    }

    public boolean checkIfListValid(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List is not valid");
        }
        return true;
    }


}




