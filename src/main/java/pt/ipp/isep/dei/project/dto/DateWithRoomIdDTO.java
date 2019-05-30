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

    private void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
