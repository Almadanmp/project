package Sprint_0;

import java.util.Date;


public class Reading {
    private double mValue;
    private Date mDate;

    public Reading(double value, Date date) {
        setmValue(value);
        setData(date);
    }

    public Reading(double value) {
        setmValue(value);
    }

    public void setmValue(double value) {
        this.mValue = value;
    }

    public double getmValue() {
        return this.mValue;
    }

    public void setData(Date date) {
        this.mDate = date;
    }

    public Date getmDate() {
        return this.mDate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reading)) {
            return false;
        } else {
            Reading reading = (Reading) o;
            if (this.mValue == reading.getmValue() && this.mDate.equals(reading.getmDate())) {
                return true;
            } else {
                return false;
            }
        }
    }

}
