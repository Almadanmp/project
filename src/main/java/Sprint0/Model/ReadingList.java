package Sprint0.Model;

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

    public double getAverageOfAllRecordedValuesFromAGivenMonth(Date d1) {
        List<Date> daysArray = getDatesOfMonthWithReadings(d1);
        double sum = 0;
        double counter = 0;
        if (daysArray.isEmpty()) {
            return 0;
        }
        for (Date d : daysArray) {
            for(Reading r : this.mReadings) {
                if(r.getmDate() == d) {
                    sum = sum + r.getmValue();
                    counter++;
                }
            }
        }

        return sum / counter;
    }

    public List<Date> getDatesOfMonthWithReadings(Date monthToTest) {
        ReadingList rl1 = new ReadingList();
        Date beginningMonth = rl1.getFirstDateOfMonthFromGivenDate(monthToTest);
        Date endMonth = rl1.getLastDateOfMonthFromGivenDate(monthToTest);

        Calendar cal = Calendar.getInstance();
        cal.setTime(beginningMonth);
        cal.add(Calendar.SECOND, -1);
        Date dayBeforeBegMonth = cal.getTime();

        cal.setTime(endMonth);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date dayAfterEndMonth = cal.getTime();

        List<Date> daysArray = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if ((currentReadingDate.after(dayBeforeBegMonth)) && (currentReadingDate.before(dayAfterEndMonth))) {
                if (!daysArray.contains(currentReadingDate)) {
                    daysArray.add(currentReadingDate);
                }
            }
        }
        return daysArray;
    }

    public Date getFirstDateOfMonthFromGivenDate (Date d1){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date getLastDateOfMonthFromGivenDate (Date d1){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }



    public double getAverageOfMinimumValuesInTheReadingsOfMonth(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(dateGiven);
        List<Double> minsValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchDayWithinMonth(day);
            double minValueOfDay;
            minValueOfDay = getLowestValueInList(valueReadingsThatMatchDay);
            minsValuesFromDaysWithReadings.add(minValueOfDay);
        }
        return getAverageFromGivenList(minsValuesFromDaysWithReadings);
    }

    public List<Reading> removeReadingsWithDifferentMonthAndYearFromDateGiven(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        for(int i = mReadings.size()-1; i>=0; i--) {
            Reading reading = mReadings.get(i);
            cal.setTime(reading.getmDate());
            if(!(cal.get(Calendar.MONTH) == month) && (cal.get(Calendar.YEAR) == year)) {
                this.mReadings.remove(reading);
            }
        }
        return this.mReadings;
    }


    public List<Integer> getDaysOfMonthWithReadings(Date dateGiven) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dateGiven);
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        Date dayBeforeStartOfMonth = cal.getTime();

        cal.setTime(dateGiven);
        cal.add(Calendar.MONTH, +1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date dayAfterEndOfMonth = cal.getTime();

        List<Integer> daysWithReadings = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dayBeforeStartOfMonth) && currentReadingDate.before(dayAfterEndOfMonth)){
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

    /**
     * @param dayOfMonth is an integer that matches the day that we want to look for readings of.
     * @return gives us an ArrayList with the values of all the readings from a particular day.
     * Does not filter for month - only finds readings of a day inside a month. Year / month must be filtered elsewhere.
     */

    public List<Double> getValueReadingsThatMatchDayWithinMonth(int dayOfMonth) {
        ArrayList<Double> valueReadingsFromGivenDay = new ArrayList<>();
        for (Reading r : mReadings) {
            GregorianCalendar temporaryCalend = new GregorianCalendar();
            temporaryCalend.setTime(r.getmDate());

            if ((temporaryCalend.get(Calendar.DAY_OF_MONTH)) == dayOfMonth) {
                valueReadingsFromGivenDay.add(r.getmValue());
            }
        }
        return valueReadingsFromGivenDay;
    }

    /**
     * @param values is a list of all the values obtained from all the valid readings within a day.
     * @return returns the lowest value of all the readings within a day (0 if list null or empty).
     */
    public double getLowestValueInList(List<Double> values) {
        double minValue;

        if (values == null || values.isEmpty()) {
            return 0;
        }

        minValue = values.get(0);

        for (double value : values) {
            if (value < minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    /**
     * @param listToTest receives a list of doubles.
     * @return returns the average of all values contained within array.
     */

    public double getAverageFromGivenList(List<Double> listToTest) {
        double sum = 0;
        if(listToTest.size() == 0) {
            return -1;
        }

        for (int i = 0; i < listToTest.size(); i++) {
            sum =  sum + listToTest.get(i);
        }
        return (sum / listToTest.size());
    }

    public double getAverageOfMaximumValuesInTheReadingsOfMonth(Date dateGiven) {
        removeReadingsWithDifferentMonthAndYearFromDateGiven(dateGiven);
        List<Integer> daysWithReadings = getDaysOfMonthWithReadings(dateGiven);
        List<Double> maxValuesFromDaysWithReadings = new ArrayList<>();
        for (int day : daysWithReadings) {
            List<Double> valueReadingsThatMatchDay = getValueReadingsThatMatchDayWithinMonth(day);
            double maxValueOfDay;
            maxValueOfDay = getHighestValueInList(valueReadingsThatMatchDay);
            maxValuesFromDaysWithReadings.add(maxValueOfDay);
        }
        return getAverageFromGivenList(maxValuesFromDaysWithReadings);
    }

    /**
     *
     * @param values is a list of all the values obtained from all the valid readings within a day.
     * @return returns the highest value of all the readings within a day (0 if list null or empty).
     */

    public double getHighestValueInList(List<Double> values) {
        double maxValue;

        if (values == null || values.isEmpty()) {
            return 0;
        }

        maxValue = values.get(0);

        for (double value : values) {
            if (value >= maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }


    public double getMeanOfGivenDay(Date dateGiven) {
        Calendar cal = Calendar.getInstance();
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
            return 0;
        }
        return sum / counter;
    }

    public List<Date> getDaysOfWeekWithReadings(Date inputDate){
        ReadingList rl1 = new ReadingList();
        Date firstDayOfWeek = rl1.getFirstDayOfWeekFromGivenDay(inputDate);
        Date lastDayOfWeek = firstDayOfWeek;


        Calendar cal = Calendar.getInstance();
        cal.setTime(firstDayOfWeek);
        cal.add(Calendar.SECOND, -1);
        Date dayBeforeBegMonth = cal.getTime();

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(lastDayOfWeek);
        cal1.add(Calendar.DAY_OF_MONTH, 7);
        cal1.add(Calendar.SECOND, -1);
        Date dayAfterEndMonth = cal1.getTime();

        List<Date> daysArray = new ArrayList<>();
        for (int i = 0; i < mReadings.size(); i++) {
            Date currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dayBeforeBegMonth) && currentReadingDate.before(dayAfterEndMonth)){
                daysArray.add(currentReadingDate);
            }
        }
        return daysArray;
    }

    /**
     * Method to get the 1st day of the week. We assume the weeks starts on a Sunday and ends on Saturday.
     *
     * @param d1 input date from user
     * @return 1stDayOfWeek
     */
    public Date getFirstDayOfWeekFromGivenDay(Date d1) {
        GregorianCalendar firstDayOfWeek = new GregorianCalendar();
        firstDayOfWeek.setTime(d1);
        int day = firstDayOfWeek.get(Calendar.DAY_OF_YEAR);

        while (firstDayOfWeek.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            firstDayOfWeek.set(Calendar.DAY_OF_YEAR, --day);
        }
        return firstDayOfWeek.getTime();
    }


}




