package pt.ipp.isep.dei.project.model.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.repository.HouseCrudeRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HouseRepositoryTest {

    private House validHouse;

    @Mock
    HouseCrudeRepo houseCrudeRepo;

    @InjectMocks
    private HouseRepository houseRepository;

    @BeforeEach
    void arrangeArtifacts() {
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"), new Local(20, 20, 20), 60,
                180, new ArrayList<>());
    }

    @Test
    void seeIfGetHouseIdWorks() {
        //Arrange

        String expectedResult = "ISEP";
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);
        Mockito.when(houseCrudeRepo.findAll()).thenReturn(houseList);

        //Act

        String actualResult = houseRepository.getHouseId();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}