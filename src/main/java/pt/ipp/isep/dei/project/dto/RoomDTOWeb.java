package pt.ipp.isep.dei.project.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.Objects;

public class RoomDTOWeb extends ResourceSupport {
    //TODO javadocs
    private String name;
    private int floor;
    private double width;
    private double length;
    private double height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isNameValid() {
        return this.name != null;
    }

    public boolean areDimensionsValid() {
        if (((Double.compare(this.width, 0.0)) == 0) || (Double.compare(this.length, 0.0) == 0)) {
            return false;
        }
        if (Double.compare(this.width, 0.0) == 0) {
            return false;
        }
        return true;
    }

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
