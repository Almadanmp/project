package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.List;
import java.util.Objects;

public class RoomDTO {

    private String name;
    private String description;
    private int floor;
    private double width;
    private double length;
    private double height;
    private List<HouseSensorDTO> sensorList;
    private DeviceList deviceList;

    /**
     * Method that retrieves the DTO's sensor list.
     *
     * @return is the DTO's sensorList.
     */

    public List<HouseSensorDTO> getSensorList() {
        return sensorList;
    }

    /**
     * Method that stores a particular sensorList in a DTO.
     *
     * @param sensorList is the list we want to store in the DTO.
     */

    public void setSensorList(List<HouseSensorDTO> sensorList) {
        this.sensorList = sensorList;
    }

    /**
     * Method that retrieves the DTO's Device List.
     *
     * @return is the DTO's device list.
     */

    public DeviceList getDeviceList() {
        return deviceList;
    }

    /**
     * Method that stores a particular deviceList in the DTO.
     *
     * @param deviceList is the device list we want to store in the DTO.
     */

    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Method that retrieves the DTO's name.
     *
     * @return is the name of the DTO.
     */

    public String getName() {
        return name;
    }

    /**
     * Method that stores a particular string as the DTO's name.
     *
     * @param name is the name we want to store.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that retrieves the DTO's description.
     *
     * @return is the description of the DTO.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that stores a particular string as the DTO's description.
     *
     * @param description is the description we want to store.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method that retrieves the floor of the DTO.
     *
     * @return is the floor.
     */


    public int getFloor() {
        return floor;
    }

    /**
     * Method that stores an int as the DTO's floor.
     *
     * @param floor is the int we want to store.
     */

    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Method that retrieves the width of the DTO.
     *
     * @return is the DTO's width.
     */

    public double getWidth() {
        return width;
    }

    /**
     * Method that stores a double as the DTO's width.
     *
     * @param width is the double we want to store.
     */

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Method that retrieves the length of the DTO.
     *
     * @return is the length of the DTO.
     */

    public double getLength() {
        return length;
    }

    /**
     * Method that stores a double as the DTO's length.
     *
     * @param length is the double we want to store.
     */

    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Method that retrieves the height of the DTO.
     *
     * @return is the DTO's height.
     */

    public double getHeight() {
        return height;
    }

    /**
     * Method that stores a double as the DTO's height.
     *
     * @param height is the height we want to store.
     */

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomDTO room = (RoomDTO) o;
        return Objects.equals(name, room.name);
    }


    @Override
    public int hashCode() {
        return 1;
    }
}