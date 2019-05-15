package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudeRepo;
import pt.ipp.isep.dei.project.repository.RoomCrudeRepo;

import java.util.List;

/**
 * Class that acts as Service to make the bridge connection between RoomService and EnergyGridService
 */

@Service
public class EnergyGridRoomService {

    @Autowired
    EnergyGridCrudeRepo energyGridCrudeRepository;
    @Autowired
    RoomCrudeRepo roomCrudeRepo;

    /**
     * Empty constructor to use on UIs.
     */
    public EnergyGridRoomService(EnergyGridCrudeRepo energyGridCrudeRepository, RoomCrudeRepo roomCrudeRepo) {
        this.energyGridCrudeRepository = energyGridCrudeRepository;
        this.roomCrudeRepo = roomCrudeRepo;
    }

    public EnergyGridRoomService() {
    }

}
