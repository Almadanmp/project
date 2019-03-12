package pt.ipp.isep.dei.project.model.device.log;

import java.util.Date;

public class Log {
    private double value;
    private Date initialDate;
    private Date finalDate;

    /**
     * Log() Constructor with 3 parameters. A value for the log with a initialDate and finalDate.
     *
     * @param value       of the Log
     * @param initialDate of the Log
     * @param finalDate   of the Log
     */
    public Log(double value, Date initialDate, Date finalDate) {
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * Getter for the Log value.
     *
     * @return value of the Log.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Getter of the initialDate of the Log.
     *
     * @return initialDate of the Log.
     */
    public Date getInitialDate() {
        return this.initialDate;
    }

    /**
     * Getter of the finalDate of the Log.
     *
     * @return finalDate of the Log.
     */
    public Date getFinalDate() {
        return this.finalDate;
    }

    /**
     * This method checks if log is contained in interval given as initial and final date.
     *
     * @return true if log is contained in interval, false otherwise.
     **/
    boolean isLogInInterval(Date initialDate, Date finalDate) {
        return (this.initialDate.after(initialDate) || this.initialDate.equals(initialDate)) &&
                ((this.finalDate.before(finalDate)) || this.finalDate.equals(finalDate));
    }

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof Log)) {
            return false;
        }
        Log log = (Log) testObject;
        return (log.getInitialDate().equals(this.initialDate) && log.getFinalDate().equals(this.finalDate));
    }

    @Override
    public int hashCode() {
        return 1;
    }


}
