package pt.ipp.isep.dei.project.model.device.log;

import java.util.Date;

public class Log {
    private double value;
    private Date initialDate;
    private Date finalDate;

    public Log(double value, Date initialDate, Date finalDate) {
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public double getValue() {
        return this.value;
    }

    public Date getInitialDate() {
        return this.initialDate;
    }

   public Date getFinalDate() {
        return this.finalDate;
    }


}
