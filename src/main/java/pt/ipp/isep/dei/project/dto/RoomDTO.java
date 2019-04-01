package pt.ipp.isep.dei.project.dto;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.List;
import java.util.UUID;

public class RoomDTO {

    private String name;
    private int floor;
    private double width;
    private double length;
    private double height;
    private List<SensorDTO> sensorList;
    private DeviceList deviceList;
    private UUID id;

    /**
     * Method that retrieves the DTO's sensor list.
     * @return is the DTO's sensorList.
     */

    public List<SensorDTO> getSensorList() {
        return sensorList;
    }

    /**
     * Method that stores a particular sensorList in a DTO.
     * @param sensorList is the list we want to store in the DTO.
     */

    public void setSensorList(List<SensorDTO> sensorList) {
        this.sensorList = sensorList;
    }

    /**
     * Method that retrieves the DTO's Device List.
     * @return is the DTO's device list.
     */

    public DeviceList getDeviceList() {
        return deviceList;
    }

    /**
     * Method that stores a particular deviceList in the DTO.
     * @param deviceList is the device list we want to store in the DTO.
     */

    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * Method that retrieves the DTO's name.
     * @return is the name of the DTO.
     */

    public String getName() {
        return name;
    }

    /**
     * Method that stores a particular string as the DTO's name.
     * @param name is the name we want to store.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that retrieves the floor of the DTO.
     * @return is the floor.
     */

    public int getFloor() {
        return floor;
    }

    /**
     * Method that stores an int as the DTO's floor.
     * @param floor is the int we want to store.
     */

    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Method that retrieves the width of the DTO.
     * @return is the DTO's width.
     */

    public double getWidth() {
        return width;
    }

    /**
     * Method that stores a double as the DTO's width.
     * @param width is the double we want to store.
     */

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Method that retrieves the length of the DTO.
     * @return is the length of the DTO.
     */

    public double getLength() {
        return length;
    }

    /**
     * Method that stores a double as the DTO's length.
     * @param length is the double we want to store.
     */

    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Method that retrieves the height of the DTO.
     * @return is the DTO's height.
     */

    public double getHeight() {
        return height;
    }

    /**
     * Method that stores a double as the DTO's height.
     * @param height is the height we want to store.
     */

    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Method that retrieves the DTO's unique ID. This is useful to match it with a model object.
     * @return is a UUID. UUID is a java class.
     */

    public UUID getId() {
        return id;
    }

    /**
     * Method that stores a UUID as the object's UUID. This is useful to match the DTO with a model object.
     * @param id is the UUID we want to store.
     */

    public void setId(UUID id) {
        this.id = id;
    }
}