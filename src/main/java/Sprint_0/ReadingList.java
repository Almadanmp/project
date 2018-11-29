package Sprint_0;

import java.util.*;

import static java.lang.Double.parseDouble;

/**
 * This is the ReadingList Class, A List of Readings that the Sensor receives.
 */
public class ReadingList {
    List<Reading> mReadings;

    /**
     * Empty Constructor to always create an ArrayList of Readings.
     */
    public ReadingList() {
        mReadings = new ArrayList<>();
    }

    /**
     * Method to Add a reading only if it's not contained in the list already.
     *
     * @param reading
     * @return
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
     * @param reading
     * @return
     */
    public boolean containsReading(Reading reading) {
        return mReadings.contains(reading);
    }


    /**
     * Getter
     *
     * @return
     */
    public List<Reading> getListOfReadings() {
        return this.mReadings;
    }

    /**
     *
     */

    public Reading getMostRecentReading() {
        int mostRecentReadingIndex=0;
        for (int i = 0; i < mReadings.size()-1; i++){
            Date firstDate = mReadings.get(i).getmDate().getTime();
            Date secondDate = mReadings.get(i+1).getmDate().getTime();
            if (firstDate.before(secondDate)){
                mostRecentReadingIndex = i+1;
            }
        }
        return mReadings.get(mostRecentReadingIndex);
    }


    /**
     * Get Mean Value of The Day
     */
    public double meanOftheDay(int year, int month, int day) {
        GregorianCalendar dayMin = new GregorianCalendar(year, month, day - 1,23,59,59);
        GregorianCalendar dayMax = new GregorianCalendar(year, month, day +1);

        double sum = 0;
        int counter = 0;


        for (int i = 0; i < mReadings.size(); i++) {
            GregorianCalendar currentReadingDate = mReadings.get(i).getmDate();
            if (currentReadingDate.after(dayMin) && currentReadingDate.before(dayMax)) {
                sum += mReadings.get(i).getmValue();
                counter++;
            }
        }
        if (counter == 0) {
            return 0;
        }
        return sum / counter;
    }

    public ArrayList<Integer> getDaysOfMonthWithReadings(int year, int month) {
        GregorianCalendar actualMonth = new GregorianCalendar(year, month, 1);
        GregorianCalendar maxDate = new GregorianCalendar(year, month + 1, 1);

        ArrayList<Integer> daysArray = new ArrayList<>();

        for (int i = 0; i < mReadings.size(); i++) {
            GregorianCalendar currentReadingDate = mReadings.get(i).getmDate();

            if (currentReadingDate.compareTo(actualMonth) >= 0 && currentReadingDate.before(maxDate)) {

                int auxDay = currentReadingDate.get(Calendar.DAY_OF_MONTH);
                if (!daysArray.contains(auxDay)) {
                    daysArray.add(auxDay);
                }
            }
        }
        return daysArray;
    }

    public double meanOfMonth(int year, int month) {

        ArrayList<Integer> daysArray = getDaysOfMonthWithReadings(year, month);
        double sum = 0;

        if (daysArray.size() == 0) {
            return 0;
        }

        for (int i = 0; i < daysArray.size(); i++) {
            sum += meanOftheDay(year, month, daysArray.get(i));
        }

        return sum / daysArray.size();
    }

}




