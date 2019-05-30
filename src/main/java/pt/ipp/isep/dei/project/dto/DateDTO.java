package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class DateDTO {


    private Date date;

    public DateDTO(Date date) {
        setDate(date);
    }


    public DateDTO() {
    }

    public Date getDate() {
        if (this.date == null) {
            return null;
        } else {
            return new Date(this.date.getTime());
        }
    }

    public void setDate(Date date) {
        if (date == null) {
            this.date = null;
        } else {
            this.date = new Date(date.getTime());
        }
    }
}
