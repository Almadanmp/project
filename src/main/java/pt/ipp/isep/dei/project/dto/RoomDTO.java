package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.dto.mappers.RoomSensorMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomSensorMinimalMapper;
import pt.ipp.isep.dei.project.model.device.DeviceList;
import pt.ipp.isep.dei.project.model.room.RoomSensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDTO {

    private String name;
    private String description;
    private int floor;
    private double width;
    private double length;
    private double height;
    private List<RoomSensorDTO> sensorList = new ArrayList<>();
    private DeviceList deviceList;
    private String houseID;


    /**
     * Method that retrieves the DTO's sensor list.
     *
     * @return is the DTO's sensorList.
     */

    public List<RoomSensorDTO> getSensorList() {
        return new ArrayList<>(sensorList);
    }

    public List<RoomSensorDTOMinimal> getSensorDTOMinimalistList() {
        List<RoomSensor> objectSensorList = new ArrayList<>();
        List<RoomSensorDTOMinimal> objectSensorListDto = new ArrayList<>();
        for (RoomSensorDTO d : sensorList) {
            RoomSensor tempObject = RoomSensorMapper.dtoToObject(d);
            objectSensorList.add(tempObject);
        }
        for (RoomSensor rs : objectSensorList) {
            RoomSensorDTOMinimal tempObject2 = RoomSensorMinimalMapper.objectToDTO(rs);
            objectSensorListDto.add(tempObject2);
        }
        return objectSensorListDto;
    }

    /**
     * Method that stores a particular sensorList in a DTO.
     *
     * @param sensorList is the list we want to store in the DTO.
     */

    public void setSensorList(List<RoomSensorDTO> sensorList) {
        this.sensorList = new ArrayList<>(sensorList);
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

    public boolean addSensor(RoomSensorDTO roomSensorDTO) {
        if (!this.sensorList.contains(roomSensorDTO)) {
            this.sensorList.add(roomSensorDTO);
            return true;
        }
        return false;
    }

    public boolean removeSensor(String roomSensorID) {
        for (RoomSensorDTO s : sensorList) {
            if (s.getId().equals(roomSensorID)) {
                sensorList.remove(s);
                return true;
            }
        }
        return false;
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

    /**
     * Method that retrieves the DTO's house ID.
     *
     * @return is the house annotation of the DTO.
     */

    public String getHouseId() {
        return houseID;
    }

    /**
     * Method that stores a particular long as the DTO's house ID.
     *
     * @param houseId is the house annotation of the DTO.
     */

    public void setHouseId(String houseId) {
        this.houseID = houseId;
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