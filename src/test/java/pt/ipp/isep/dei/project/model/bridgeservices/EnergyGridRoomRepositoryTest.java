package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudRepo;
import pt.ipp.isep.dei.project.repository.RoomCrudRepo;


/**
 * EnergyGridRoomService tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyGridRoomRepositoryTest {

    private Room validRoom;

    @Mock
    private RoomCrudRepo roomCrudRepo;

    @Mock
    private EnergyGridCrudRepo energyGridCrudRepo;

    @InjectMocks
    private EnergyGridRoomService validEnergyGridRoomService;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");

    }
}