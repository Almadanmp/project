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

    boolean containsLog(Log log) {
        return mLogList.contains(log);
    }

    boolean addLog(Log log) {
        if (!(mLogList.contains(log))) {
            mLogList.add(log);
            return true;
        } else {
            return false;
        }
    }

    boolean removeLog(Log log) {
        if (mLogList.contains(log)) {
            mLogList.remove(log);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter (array of PowerSources)
     *
     * @return array of powerSources
     */
    Log[] getLogs() {
        int sizeOfResultArray = mLogList.size();
        Log[] result = new Log[sizeOfResultArray];
        for (int i = 0; i < mLogList.size(); i++) {
            result[i] = mLogList.get(i);
        }
        return result;
    }

    List<Log> getLogList() {
        return this.mLogList;
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
