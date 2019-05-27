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
        return initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
