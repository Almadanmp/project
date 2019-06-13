package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.Objects;

/**
 * Class that is responsible to return a value and a date.
 * Used on House Monitoring US's
 */
public class DateValueDTO extends ResourceSupport {
    private Date date;
    private double value;


    public DateValueDTO(Date date, double value) {
        setDate(date);
        this.value = value;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DateValueDTO dateValueDTO = (DateValueDTO) o;
        return Objects.equals(dateValueDTO.getDate(), this.date);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
