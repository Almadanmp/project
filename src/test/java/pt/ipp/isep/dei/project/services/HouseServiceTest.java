package pt.ipp.isep.dei.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.repository.EnergyGridRepository;
import pt.ipp.isep.dei.project.repository.HouseRepository;
import pt.ipp.isep.dei.project.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class HouseServiceTest {
    // Common testing artifacts for this class.

    private House firstValidHouse;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private EnergyGridRepository energyGridRepository;

    private HouseService houseService;

    @BeforeEach
    void arrangeArtifacts() {
        houseService = new HouseService(houseRepository, roomRepository, energyGridRepository);
        List<String> deviceTypeConfig = new ArrayList<>();
        firstValidHouse = new House("TestHouse", new Address("Rua das Flores", "12", "445",
                "Porto", "Portugal"), new Local(10, 10, 10), 10,
                10, deviceTypeConfig);
    }

    @Test
    void seeIfSaveHouseWorksTrue() {
        assertTrue(houseService.saveHouse(firstValidHouse));
    }

}
