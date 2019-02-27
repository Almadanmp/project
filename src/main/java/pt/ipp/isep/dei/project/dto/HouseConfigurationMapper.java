package pt.ipp.isep.dei.project.dto;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class HouseConfigurationMapper {
    private HouseDTO houseDTO = new HouseDTO();
    private RoomDTO roomDTO = new RoomDTO();

    HouseDTO getHousetoDTO(House house) {
        this.houseDTO.setId(house.getHouseId());
        this.houseDTO.setLocation(house.getLocation());
        this.houseDTO.setRoomList(house.getRoomList());
        this.houseDTO.setMotherArea(house.getMotherArea());
        return houseDTO;
    }

//    House DTOHouseToHouse(HouseDTO houseDTO) {
//        House house = new House();
//        house.setId(houseDTO.getId());
//        house.setLocation(houseDTO.getLocation().getLatitude(), houseDTO.getLocation().getLongitude(), houseDTO.getLocation().getAltitude());
//        house.setRoomList(houseDTO.getRoomList());
//        house.setMotherArea(houseDTO.getMotherArea());
//    }


    public RoomDTO roomToDTO(Room room){
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setHouseFloor(room.getHouseFloor());
        roomDTO.setRoomHeight(room.getRoomHeight());
        roomDTO.setRoomLength(room.getRoomLength());
        roomDTO.setRoomWidth(room.getRoomWidth());
        roomDTO.setId(room.hashCode());
        return roomDTO;
    }

    public Room DTOtoRoom(RoomDTO roomDTO, House house){
        Room room = null;
        RoomList roomlist = house.getRoomList();
        for (Room r: roomlist.getRooms()){
            if (roomDTO.getId()==r.hashCode())
                r.setRoomName(roomDTO.getRoomName());
            r.setHouseFloor(roomDTO.getHouseFloor());
            r.setRoomWidth(roomDTO.getRoomWidth());
            r.setRoomLength(roomDTO.getRoomLength());
            r.setRoomHeight(roomDTO.getRoomHeight());
            room=r;
        }
        return room;
    }
}


