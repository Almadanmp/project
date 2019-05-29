package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class DateIntervalDTO {
    private Date initialDate;
    private Date endDate;

    public DateIntervalDTO(Date initialDate, Date endDate) {
        this.initialDate = initialDate;
        this.endDate = endDate;
    }


    public DateIntervalDTO() {
    }

    public Date getInitialDate() {
        if (this.initialDate == null) {
            return null;
        }
        return new Date(this.initialDate.getTime());
    }

    public Date getEndDate() {
        if (this.endDate == null) {
            return null;
        }
        return new Date(this.endDate.getTime());
    }

    /**
     * Method to validate if a interval of dates is valid
     * Date is valid if - Both input are valid inputs
     * If end date is after initial date
     *
     * @return true if date valid
     */
    public boolean isValid() {
        return initialDate != null && endDate != null
                && endDate.after(initialDate);
    }

}
