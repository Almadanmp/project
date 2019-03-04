package pt.ipp.isep.dei.project.dto;


import pt.ipp.isep.dei.project.model.SensorList;
import pt.ipp.isep.dei.project.model.device.DeviceList;

import java.util.UUID;

public class RoomDTO {

    private String roomName;
    private int houseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;
    private SensorList roomSensorList;
    private DeviceList deviceList;
    private UUID id;

    public SensorList getRoomSensorList() {
        return roomSensorList;
    }

    public void setRoomSensorList(SensorList roomSensorList) {
        this.roomSensorList = roomSensorList;
    }

    public DeviceList getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(DeviceList deviceList) {
        this.deviceList = deviceList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(int houseFloor) {
        this.houseFloor = houseFloor;
    }

    public double getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(double roomWidth) {
        this.roomWidth = roomWidth;
    }

    public double getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(double roomLength) {
        this.roomLength = roomLength;
    }

    public double getRoomHeight() {
        return roomHeight;
    }

    public void setRoomHeight(double roomHeight) {
        this.roomHeight = roomHeight;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}