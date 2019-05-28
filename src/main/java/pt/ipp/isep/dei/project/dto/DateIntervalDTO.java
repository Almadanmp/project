package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class DateIntervalDTO {
    private Date initialDate;
    private Date endDate;

    public DateIntervalDTO(Date initialDate, Date endDate) {
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public Date getInitialDate() {
        if(this.initialDate == null) {
            return null;
        }
        return new Date(this.initialDate.getTime());
    }

    public Date getEndDate() {
        if(this.endDate == null) {
            return null;
        }
        return new Date(this.endDate.getTime());
    }

}
