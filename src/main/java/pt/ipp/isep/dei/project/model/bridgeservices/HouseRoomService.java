package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.dto.RoomDTOMinimal;
import pt.ipp.isep.dei.project.dto.mappers.RoomMinimalMapper;
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
        Room room = RoomMinimalMapper.dtoToObject(roomDTOMinimal);
        room = updateHouseID(room);
        return roomRepository.addRoomToCrudRepository(room);
    }

    /**
     * This method accesses the House Repository, gets the ID of the house, and assigns it to a given room.
     * @param roomToUpdate is the room whose houseID we want to update.
     * @return is the updated room.
     */
    Room updateHouseID(Room roomToUpdate){
        String houseID = houseRepository.getHouseId();
        roomToUpdate.setHouseID(houseID);
        return roomToUpdate;
    }
}
