package pt.ipp.isep.dei.project.model.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Power Sources.
 */

public class LogList {

    private List<Log> mLogList;

    LogList() {
        this.mLogList = new ArrayList<>();
    }

    /**
     * Getter (array of Logs)
     *
     * @return array of Logs
     */
    private Log[] getLogs() {
        int sizeOfResultArray = mLogList.size();
        Log[] result = new Log[sizeOfResultArray];
        for (int i = 0; i < mLogList.size(); i++) {
            result[i] = mLogList.get(i);
        }
        return result;
    }

    /**
     * This method is used to get a List of objects of the class Log.
     *
     * @return List with Logs
     */
    List<Log> getLogList() {
        return this.mLogList;
    }

    /**
     * This method checks if the LogList is empty.
     *
     * @return boolean
     */

    public boolean isEmpty() {
        return this.mLogList.isEmpty();
    }


    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof LogList)) {
            return false;
        }
        LogList list = (LogList) testObject;
        return Arrays.equals(this.getLogs(), list.getLogs());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
