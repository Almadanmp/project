package pt.ipp.isep.dei.project.model;

import java.util.Date;

public class DateInterval {
    Date initialDate;
    Date endDate;


    public DateInterval(Date initialDate, Date endDate) {
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
