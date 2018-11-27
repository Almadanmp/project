package Sprint_0;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

    public double getMostRecentReading() {
        int mostRecentReadingIndex=0;
        for (int i = 0; i < mReadings.size()-1; i++){
            Date firstDate = mReadings.get(i).getmDate().getTime();
            Date secondDate = mReadings.get(i+1).getmDate().getTime();
            if (firstDate.before(secondDate)){
                mostRecentReadingIndex = i+1;
            }
        }
        return mReadings.get(mostRecentReadingIndex).getmValue();
    }

    /**
     *
     */
}


