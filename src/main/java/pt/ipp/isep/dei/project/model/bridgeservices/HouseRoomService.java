package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.RoomWebMapper;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

@Service
public class HouseRoomService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private RoomRepository roomRepository;

    public boolean addRoomDTOWebToHouse(RoomDTOWeb roomDTOWeb) {
        Room room = RoomWebMapper.dtoToObject(roomDTOWeb);
        String houseID = houseRepository.getHouseId();
        room.setHouseID(houseID);
        if (roomRepository.addRoomToCrudRepository(room)) {
            return true;
        }
        return false;
    }


}
