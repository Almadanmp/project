package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.GeographicArea;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.RoomList;

public class HouseDTO {
    //House Attributes
    private String id;
    private Local location;
    private RoomList roomList;
    private GeographicArea motherArea;

    //Room Attributes
    private String roomName;
    private int houseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;


    //Setters And Getters House
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Local getLocation() {
        return location;
    }

    public void setLocation(Local location) {
        this.location = location;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public void setRoomList(RoomList roomList) {
        this.roomList = roomList;
    }

    public GeographicArea getMotherArea() {
        return motherArea;
    }

    public void setMotherArea(GeographicArea motherArea) {
        this.motherArea = motherArea;
    }

    //SETTERS AND GETTERS ROOM


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
}