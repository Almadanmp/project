package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class DateIntervalDTO {
    private Date initialDate;
    private Date endDate;

    public DateIntervalDTO(Date initialDate, Date endDate) {
        setInitialDate(initialDate);
        setEndDate(endDate);
    }


    public DateIntervalDTO() {
    }

    public Date getInitialDate() {
        if (this.initialDate == null) {
            return null;
        } else {
            return new Date(this.initialDate.getTime());
        }
    }

    public void setInitialDate(Date initialDate) {
        if (initialDate == null) {
            this.initialDate = null;
        } else {
            this.initialDate = new Date(initialDate.getTime());
        }
    }

    public Date getEndDate() {
        if (this.endDate == null) {
            return null;
        } else {
            return new Date(this.endDate.getTime());
        }
    }

    public void setEndDate(Date endDate) {
        if (endDate == null) {
            this.endDate = null;
        } else {
            this.endDate = new Date(endDate.getTime());
        }
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
