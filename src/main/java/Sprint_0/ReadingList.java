package Sprint_0;

import java.util.ArrayList;
import java.util.List;

public class ReadingList {
    List<Reading> mReadings;

    public ReadingList() {
        mReadings = new ArrayList<>();
    }

    public boolean addReading (Reading reading) {
        if (!(mReadings.contains(reading))) {
            mReadings.add(reading);
            return true;
        } else {
            return false;
        }
    }

    public boolean containsReading (Reading reading) {
        return mReadings.contains(reading);
    }

    public void setReadings(List<Reading> readings) {
        this.mReadings = readings;
    }

    public List<Reading> getListOfReadings () {
        return this.mReadings;
    }
}

}
