package pt.ipp.isep.dei.project.model.house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.HouseDTO;
import pt.ipp.isep.dei.project.dto.HouseWithoutGridsDTO;
import pt.ipp.isep.dei.project.dto.mappers.HouseMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.repository.HouseCrudRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HouseRepositoryTest {

    @Mock
    HouseCrudRepo houseCrudRepo;
    private House validHouse;
    @InjectMocks
    private HouseRepository houseRepository;

    @BeforeEach
    void arrangeArtifacts() {
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"), new Local(20, 20, 20), 60,
                180, new ArrayList<>());
    }

    @Test
    void seeIfUpdateHouseDTOWithoutGridsWorks() {
        //Arrange

        HouseWithoutGridsDTO withoutGridsDTO = HouseMapper.objectToWithoutGridsDTO(validHouse);

        Mockito.when(houseCrudRepo.save(validHouse)).thenReturn(validHouse);

        //Act

        boolean actualResult = houseRepository.updateHouseDTOWithoutGrids(withoutGridsDTO);

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfUpdateHouseDTOWithoutGridsWorksWhenSaveDoesNotWork() {
        //Arrange

        HouseWithoutGridsDTO withoutGridsDTO = HouseMapper.objectToWithoutGridsDTO(validHouse);

        Mockito.when(houseCrudRepo.save(validHouse)).thenReturn(null);

        //Act

        boolean actualResult = houseRepository.updateHouseDTOWithoutGrids(withoutGridsDTO);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetHouseWithoutGridsDTOWorks() {
        //Arrange

        HouseWithoutGridsDTO expectedResult = HouseMapper.objectToWithoutGridsDTO(validHouse);

        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);
        Mockito.when(houseCrudRepo.findAll()).thenReturn(houseList);

        //Act

        HouseWithoutGridsDTO actualResult = houseRepository.getHouseWithoutGridsDTO();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHouseApplicationWorks() {
        //Arrange

        HouseDTO expectedResult = HouseMapper.objectToDTO(validHouse);

        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);
        Mockito.when(houseCrudRepo.findAll()).thenReturn(houseList);

        //Act

        HouseDTO actualResult = houseRepository.getApplicationHouse();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetHousesWorks() {
        //Arrange

        String expectedResult = "ISEP";
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);
        Mockito.when(houseCrudRepo.findAll()).thenReturn(houseList);

        //Act

        List<House> actualResult = houseRepository.getHouses();

        //Assert
        assertEquals(houseList, actualResult);
    }

    @Test
    void seeIfGetHousesWorksWhenListIsEmpty() {
        //Arrange

        String expectedResult = "ISEP";
        List<House> houseList = new ArrayList<>();
        Mockito.when(houseCrudRepo.findAll()).thenReturn(houseList);

        //Act

        List<House> actualResult = houseRepository.getHouses();

        //Assert
        assertEquals(houseList, actualResult);
    }

    @Test
    void seeIfGetHouseIdWorks() {
        //Arrange

        String expectedResult = "ISEP";
        List<House> houseList = new ArrayList<>();
        houseList.add(validHouse);
        Mockito.when(houseCrudRepo.findAll()).thenReturn(houseList);

        //Act

        String actualResult = houseRepository.getHouseId();

        //Assert
        assertEquals(expectedResult, actualResult);
    }
}