package pt.ipp.isep.dei.project.model.device.log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Power Sources.
 */

public class LogList {

    private List<Log> logs;

    /**
     * LogList empty constructor with an initialization of an ArrayList of Logs.
     */
    public LogList() {
        this.logs = new ArrayList<>();
    }

    /**
     * Getter (array of Logs)
     *
     * @return array of Logs
     */
    public Log[] getElementsAsArray() {
        int sizeOfResultArray = logs.size();
        Log[] result = new Log[sizeOfResultArray];
        for (int i = 0; i < logs.size(); i++) {
            result[i] = logs.get(i);
        }
        return result;
    }

    /**
     * This method is used to get a List of objects of the class Log.
     *
     * @return List with Logs
     */
    public List<Log> getLogListAttribute() {
        return this.logs;
    }

    /**
     * Method that adds a Log to the LogList.
     *
     * @param logToAdd is the Log we want to addWithoutPersisting to the LogList.
     * @return false if it's already contained in the list, true if it added successfully.
     */
    public boolean addLog(Log logToAdd) {
        if (this.logs.contains(logToAdd)) {
            return false;
        }
        this.logs.add(logToAdd);
        return true;
    }

    /**
     * Method that checks if a Log is contained in the LogList.
     *
     * @param log is the log that we want to see if it's contained in the LogList.
     * @return true if log is contained in the LogList, false otherwise.
     */
    public boolean contains(Log log) {
        return (this.logs.contains(log));
    }

    /** This method checks if a LogList is empty.
     *
     * @return true if empty, false otherwise.
     * **/
    public boolean isEmpty() {
        return this.logs.isEmpty();
    }

    /**
     * Method that allows adding a LogList to another one.
     *
     * @param listToAdd is the List we want to addWithoutPersisting to the LogList.
     * @return true if at least one object was added do the list, false if nothing was added to the list.
     */
    public boolean addLogList(LogList listToAdd) {
        int counter = 0;
        for (Log l : listToAdd.getLogListAttribute()) {
            if (this.addLog(l)) {
                counter++;
            }
        }
        return counter > 0;
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        int counter = 0;
        for (Log l : this.logs) {
            if (l.isLogInInterval(initialTime, finalTime)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method that returns LogList in given interval of time.
     *
     * @param startDate is the Starting Date of the Log.
     * @param endDate   is the End Date of the Log.
     * @return a LogList in a given Interval.
     */
    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Log l : this.logs) {
            if (l.isLogInInterval(startDate, endDate)) {
                result.addLog(l);
            }
        }
        return result;
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        double result = 0;
        for (Log l : this.logs) {
            if (l.isLogInInterval(initialTime, finalTime)) {
                result += l.getValue();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Log log : this.logs) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String startDateString = format.format(log.getInitialDate());
            String endDateString = format.format(log.getFinalDate());
            result.append("\n").append(counter).append(") ").append("Start Date: ").append(startDateString).append(" | End Date: ").append(endDateString).append(" | Value: ").append(log.getValue());
            counter++;
        }
        if (counter == 0) {
            return "There's no valid logs within that interval.";
        }
        String resultString = result.toString();
        resultString = resultString.replaceAll("\\bGMT\\b", "");
        resultString = resultString.replaceAll("\\bWET\\b", "");
        return resultString;
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
        return Arrays.equals(this.getElementsAsArray(), list.getElementsAsArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
