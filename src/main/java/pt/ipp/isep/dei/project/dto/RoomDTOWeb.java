package pt.ipp.isep.dei.project.dto;

import java.util.Objects;

public class RoomDTOWeb {

    private String name;
    private int floor;
    private double width;
    private double length;
    private double height;

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDTO roomDTO = (RoomDTO) o;
        return Objects.equals(name, roomDTO.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
