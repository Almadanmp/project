package pt.ipp.isep.dei.project.model.bridgeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;
import pt.ipp.isep.dei.project.repository.RoomSensorRepository;

/**
 * Class that acts as Service to make the bridge connection between RoomService and EnergyGridService
 */

@Service
public class EnergyGridRoomService {

    @Autowired
    EnergyGridRepository energyGridRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomSensorRepository roomSensorRepository;

}
