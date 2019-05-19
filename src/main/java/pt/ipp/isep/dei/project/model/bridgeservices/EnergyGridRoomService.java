package pt.ipp.isep.dei.project.model.bridgeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudRepo;
import pt.ipp.isep.dei.project.repository.RoomCrudRepo;

/**
 * Class that acts as Service to make the bridge connection between RoomService and EnergyGridService
 */

@Service
public class EnergyGridRoomService {

    @Autowired
    EnergyGridCrudRepo energyGridCrudRepository;
    @Autowired
    RoomCrudRepo roomCrudRepo;

}
