package pt.ipp.isep.dei.project.model.bridgeservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;


/**
 * EnergyGridRoomService tests class.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EnergyGridRoomServiceTest {

    @Test
    void seeIfEmptyConstructorWoks(){

        //Act
        EnergyGridRoomService energyGridRoomService = new EnergyGridRoomService();

        // Assert
        assertEquals(energyGridRoomService,energyGridRoomService);
    }
}