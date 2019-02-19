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

    private List<Log> logList;

    public LogList() {
        this.logList = new ArrayList<>();
    }

    /**
     * Getter (array of Logs)
     *
     * @return array of Logs
     */
    public Log[] getLogs() {
        int sizeOfResultArray = logList.size();
        Log[] result = new Log[sizeOfResultArray];
        for (int i = 0; i < logList.size(); i++) {
            result[i] = logList.get(i);
        }
        return result;
    }

    /**
     * This method is used to get a List of objects of the class Log.
     *
     * @return List with Logs
     */
   public  List<Log> getLogList() {
        return this.logList;
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
        return this.logList.isEmpty();
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

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        int counter = 0;
        for (Log l : this.getLogList()) {
            if ((l.getInitialDate().after(initialTime) || l.getInitialDate().equals(initialTime)) &&
                    ((l.getFinalDate().before(finalTime)) || l.getFinalDate().equals(finalTime))) {
                counter++;
            }
        }
        return counter;
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Log l : this.getLogList()) {
            if ((l.getInitialDate().after(startDate) || l.getInitialDate().equals(startDate)) &&
                    ((l.getFinalDate().before(endDate)) || l.getFinalDate().equals(endDate))) {
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
        for (Log l : this.getLogList()) {
            if ((l.getInitialDate().after(initialTime) || l.getInitialDate().equals(initialTime)) &&
                    ((l.getFinalDate().before(finalTime)) || l.getFinalDate().equals(finalTime))) {
                result += l.getValue();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (Log log : this.getLogList()) {
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
        return Arrays.equals(this.getLogs(), list.getLogs());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
