package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

public class RoomDTOWeb extends ResourceSupport {
    private String name;
    private int floor;
    private double width;
    private double length;
    private double height;

    /**
     * Name getter
     **/
    public String getName() {
        return name;
    }

    /**
     * Name setter
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Floor getter
     **/
    public int getFloor() {
        return floor;
    }

    /**
     * Floor setter
     **/
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Height getter
     **/
    public double getHeight() {
        return height;
    }

    /**
     * Height setter
     **/
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Length getter
     **/
    public double getLength() {
        return length;
    }

    /**
     * Length setter
     **/
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Width getter
     **/
    public double getWidth() {
        return width;
    }

    /**
     * Width setter
     **/
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Checks if the Dto Name is Valid
     *
     * @return true in case is valid, false otherwise
     **/
    public boolean isNameValid() {
        return this.name != null;
    }

    /**
     * Checks if the Dto dimension is valid
     *
     * @return true in case is valid, false otherwise
     **/
    public boolean areDimensionsValid() {
        if (((Double.compare(this.width, 0.0)) == 0) || (Double.compare(this.length, 0.0) == 0)) {
            return false;
        }
        return Double.compare(this.height, 0.0) != 0;
    }

    /**
     * RoomDTOWeb Equals
     *
     * @return true in case the object is equal, false otherwise
     * *
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDTOWeb roomDTO = (RoomDTOWeb) o;
        return Objects.equals(name, roomDTO.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
