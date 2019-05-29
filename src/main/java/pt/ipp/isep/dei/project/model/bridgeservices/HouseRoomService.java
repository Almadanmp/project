package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.RoomWebMapper;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

@Service
public class HouseRoomService implements pt.ipp.isep.dei.project.dddplaceholders.Service {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private RoomRepository roomRepository;

    /**
     * This method receives a Room DTO and tries to add the equivalent
     * room to the room repository.
     *
     * @param roomDTOMinimal dto to be added to repository
     * @return true in case the room is added to the repository, false otherwise.
     **/
    public boolean addMinimalRoomDTOToHouse(RoomDTOMinimal roomDTOMinimal) {
        Room room = RoomWebMapper.dtoToObject(roomDTOMinimal);
        String houseID = houseRepository.getHouseId();
        room.setHouseID(houseID);
        return roomRepository.addRoomToCrudRepository(room);
    }
}
