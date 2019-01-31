package pt.ipp.isep.dei.project.model.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that groups a number of Power Sources.
 */

public class LogList {

    private List<Log> mLogList;

    public LogList() {
        this.mLogList = new ArrayList<>();
    }

    /**
     * Getter (array of Logs)
     *
     * @return array of Logs
     */
    public Log[] getLogs() {
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

    public boolean addLog(Log logToAdd) {
        if (this.getLogList().contains(logToAdd)) {
            return false;
        }
        this.getLogList().add(logToAdd);
        return true;
    }

    public boolean isEmpty() {
        return this.mLogList.isEmpty();
    }

    public boolean addLogList(LogList listToAdd) {
        int counter = 0;
        for (Log l : listToAdd.getLogList()) {
            if (this.addLog(l)) {
                counter++;
            }
        }
        return counter > 0;
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Log log : this.getLogList()){
            result.append("\n").append(counter).append(") ").append("Start Date: ").append(log.getInitialDate()).append(" | End Date: ").append(log.getFinalDate()).append(" | Value: ").append(log.getValue());
            counter++;
        }
        return result.toString();
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
