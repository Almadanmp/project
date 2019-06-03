package pt.ipp.isep.dei.project.dto;

import java.util.Date;

public class DateWithRoomIdDTO {


    private Date date;
    private String roomId;

    public DateWithRoomIdDTO(Date date, String roomId) {
        setDate(date);
        setRoomId(roomId);
    }

    public  DateWithRoomIdDTO(){}

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

    public String getRoomId() {
        if (this.roomId == null) {
            return null;
        } else {
            return this.roomId;
        }
    }

    void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * Method to validate if a interval of dates is valid
     * Date is valid if - Both input are valid inputs
     * If end date is after initial date
     *
     * @return true if date valid
     */
    public boolean isDateValid() {
        return date != null;
    }

}
