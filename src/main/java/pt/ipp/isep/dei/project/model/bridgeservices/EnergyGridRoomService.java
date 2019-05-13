package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridRepo;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import java.util.List;

/**
 * Class that acts as Service to make the bridge connection between RoomService and EnergyGridService
 */

@Service
public class EnergyGridRoomService {

    @Autowired
    EnergyGridRepo energyGridRepository;
    @Autowired
    RoomRepository roomRepository;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridRoomService(EnergyGridRepo energyGridRepository, RoomRepository roomRepository) {
        this.energyGridRepository = energyGridRepository;
        this.roomRepository = roomRepository;
    }

    public EnergyGridRoomService() {
    }

    public List<Room> getAllByEnergyGridName(String energyGridName) {
        return roomRepository.findAllByEnergyGridId(energyGridName);
    }



}
