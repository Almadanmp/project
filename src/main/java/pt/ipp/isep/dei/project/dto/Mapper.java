package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;


public class Mapper {
    private RoomDTO roomDTO = new RoomDTO();

    public RoomDTO roomToDTO(Room room) {
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setHouseFloor(room.getHouseFloor());
        roomDTO.setRoomHeight(room.getRoomHeight());
        roomDTO.setRoomLength(room.getRoomLength());
        roomDTO.setRoomWidth(room.getRoomWidth());
        roomDTO.setId(room.getUniqueID());
        roomDTO.setRoomSensorList(room.getSensorList());
        roomDTO.setDeviceList(room.getDeviceList());
        return roomDTO;
    }

    public Room DTOtoRoom(RoomDTO roomDTO, House house) {
        Room room = null;
        RoomList roomlist = house.getRoomList();
        for (Room r : roomlist.getRooms()) {
            if (roomDTO.getId().compareTo(r.getUniqueID()) == 0) {
                r.setRoomName(roomDTO.getRoomName());
                r.setHouseFloor(roomDTO.getHouseFloor());
                r.setRoomWidth(roomDTO.getRoomWidth());
                r.setRoomLength(roomDTO.getRoomLength());
                r.setRoomHeight(roomDTO.getRoomHeight());
                r.setSensorList(roomDTO.getRoomSensorList());
                r.setDeviceList(roomDTO.getDeviceList());
                room = r;
            }
        }
        return room;
    }


}


