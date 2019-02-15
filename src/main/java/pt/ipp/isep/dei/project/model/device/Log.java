package pt.ipp.isep.dei.project.model.device;

import java.util.Date;

public class Log {
    private double mValue;
    private Date mInitialDate;
    private Date mFinalDate;

    public Log(double value, Date initialDate, Date finalDate) {
        this.mValue = value;
        this.mInitialDate = initialDate;
        this.mFinalDate = finalDate;
    }

    public double getValue() {
        return this.mValue;
    }

    public Date getInitialDate() {
        return this.mInitialDate;
    }

    Date getFinalDate() {
        return this.mFinalDate;
    }


}
