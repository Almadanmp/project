package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudeRepo;
import pt.ipp.isep.dei.project.repository.RoomCrudeRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * EnergyGridRoomService tests class.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EnergyGridRoomRepositoryTest {

    private Room validRoom;
    private EnergyGridRoomService validEnergyGridRoomService;

    @Mock
    private RoomCrudeRepo roomCrudeRepo;

    @Mock
    private EnergyGridCrudeRepo energyGridCrudeRepo;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        validRoom = new Room("Kitchen", "1st Floor Kitchen", 1, 4, 5, 3, "Room1");
        validEnergyGridRoomService = new EnergyGridRoomService(energyGridCrudeRepo, roomCrudeRepo);
    }

    @Test
    void seeIfEmptyConstructorWoks() {

        //Act
        EnergyGridRoomService energyGridRoomService = new EnergyGridRoomService();

        // Assert
        assertEquals(energyGridRoomService, energyGridRoomService);
    }

}