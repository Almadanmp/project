package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.*;
import java.util.*;

/**
 * This is the AreaReadingList Class, a List of readings that the Sensor receives.
 */
@Entity
public class HouseReadingList {

    private static final String EMPTY_LIST = "The reading list is empty.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Used as primary key in repository tables.

    @OneToMany(mappedBy = "houseReadingList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HouseReading> readings;

    /**
     * /**
     * Empty Constructor to always allow the creation of an ArrayList of readings.
     */
    public HouseReadingList() {
        this.readings = new ArrayList<>();
    }

    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param reading receives a reading.
     * @return returns true if the input reading was added successfully.
     * returns false if the input reading was rejected.
     */
    public boolean addReading(HouseReading reading) {
        if (contains(reading)) {
            return false;
        }
        return this.readings.add(reading);
    }

    /**
     * This method receives an index as parameter and gets a reading from reading list.
     *
     * @param index the index of the Reading
     * @return returns reading that corresponds to index.
     */
    public HouseReading get(int index) {
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
    public double getValueReading(int index) {
        if (this.readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        HouseReading reading = this.readings.get(index);
        return reading.getValue();
    }

    public List<HouseReading> getReadings() {
        return readings;
    }

    /**
     * This method receives an index as parameter and gets a reading date from reading list.
     *
     * @param index the index of the Reading we want to get date from
     * @return returns date reading that corresponds to index.
     */
    public Date getValueDate(int index) {
        if (this.readings.isEmpty()) {
            throw new IndexOutOfBoundsException(EMPTY_LIST);
        }
        HouseReading reading = this.readings.get(index);
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
     * @return AreaReadingList size as int
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
    public HouseReading getMostRecentReading() {
        HouseReading error = new HouseReading(0, new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime(), "C");
        if (isEmpty()) {
            return error;
        }
        HouseReading recentReading = this.readings.get(0);
        Date mostRecent = recentReading.getDate();
        for (HouseReading r : this.readings) {
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
        if (this.readings.isEmpty()) {
            throw new IllegalArgumentException("There aren't any readings available.");
        }
        return getMostRecentReading().getValue();
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
    public boolean isReadingDateBetweenTwoDates(Date readingDate, Date startDate, Date endDate) {
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
     * Adds all readings of a given AreaReadingList to target list, rejecting duplicates.
     *
     * @param readingList The list to be added to the target list
     * @return A parallel deviceList with all the devices that could be added
     **/
    public HouseReadingList appendListNoDuplicates(HouseReadingList readingList) {
        HouseReading[] readingsArray = readingList.getElementsAsArray();
        for (HouseReading r : readingsArray) {
            this.addReading(r);
        }
        return this;
    }

    /**
     * Getter (array of readings)
     *
     * @return array of readings
     */
    public HouseReading[] getElementsAsArray() {
        int sizeOfResultArray = size();
        HouseReading[] result = new HouseReading[sizeOfResultArray];
        for (int i = 0; i < size(); i++) {
            result[i] = readings.get(i);
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
    public HouseReadingList getReadingListOfReadingsWithSpecificValue(Double value) {
        HouseReadingList result = new HouseReadingList();
        for (HouseReading r : this.readings) {
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
    public HouseReading getAReadingWithSpecificDay(Date date) {
        HouseReading result = null;
        for (HouseReading r : this.readings) {
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
    public HouseReading getMaxValueOfTheDay(Date day) {
        double auxValue = getValuesOfSpecificDayReadings(day).get(0);
        HouseReading result = getAReadingWithSpecificDay(day);
        Date auxDay = getFirstSecondOfDay(day);
        for (HouseReading r : this.readings) {
            Date readingDate = getFirstSecondOfDay(r.getDate());
            if (readingDate.equals(auxDay) && r.getValue() > auxValue) {
                result = r;
                auxValue = result.getValue();
            }
        }
        return result;
    }

    /**
     * This method checks if a reading exists in a given reading list.
     *
     * @return true if the reading already exists, false otherwise
     */
    public boolean contains(HouseReading reading) {
        return (readings.contains(reading));
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof HouseSensorService)) {
            return false;
        }
        HouseReadingList list = (HouseReadingList) testObject;
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}