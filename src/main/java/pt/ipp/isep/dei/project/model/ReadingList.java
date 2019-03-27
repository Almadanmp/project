package pt.ipp.isep.dei.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the ReadingList Class, a List of Readings that the Sensor receives.
 */
@Entity
public class ReadingList {

    private static final String EMPTY_LIST = "The reading list is empty.";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
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

    public boolean contains(Reading reading){
        if(this.readings.contains(reading)){
            return false;
        }
        this.readings.add(reading);
        return true;
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
     * Getter (array of readings)
     *
     * @return array of readings
     */
    Reading[] getElementsAsArray() {
        int sizeOfResultArray = size();
        Reading[] result = new Reading[sizeOfResultArray];
        for (int i = 0; i < size(); i++) {
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