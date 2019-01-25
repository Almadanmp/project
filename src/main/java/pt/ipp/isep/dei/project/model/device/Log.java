package pt.ipp.isep.dei.project.model.device;

import java.util.Date;

public class Log {
    private double mValue;
    private Date mInitialDate;
    private Date mFinalDate;

    public void setValue(double value){
        this.mValue = value;
    }

    public void setInitialDate(Date date){
        this.mInitialDate = date;
    }

    public void setFinalDate(Date date){
        this.mFinalDate = date;
    }

    public double getValue(){return this.mValue;}

    public Date getInitialDate(){return this.mInitialDate;}

    public Date getFinalDate(){return this.mFinalDate;}
}
