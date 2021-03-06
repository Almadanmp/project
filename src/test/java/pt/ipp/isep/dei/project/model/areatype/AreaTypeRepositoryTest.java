package pt.ipp.isep.dei.project.model.areatype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.device.WaterHeater;
import pt.ipp.isep.dei.project.model.device.devicespecs.WaterHeaterSpec;
import pt.ipp.isep.dei.project.model.repository.AreaTypeCrudRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeAreaList tests class.
 */
@ExtendWith(MockitoExtension.class)
class AreaTypeRepositoryTest {
    // Common testing artifacts for this class.

    private AreaType firstValidType;
    private AreaType secondValidType;

    @Mock
    private AreaTypeCrudRepo areaTypeCrudRepo;
    @InjectMocks
    private AreaTypeRepository areaTypeRepository;

    @BeforeEach
    void arrangeArtifacts() {
        firstValidType = new AreaType("Country");
        secondValidType = new AreaType("City");
    }

    @Test
    void seeIfGetByIDWorks() {
        //Arrange

        Mockito.when(areaTypeCrudRepo.findById("Country")).thenReturn(Optional.of(firstValidType));

        //Act

        AreaType actualResult = areaTypeRepository.getById("Country");

        //Assert

        assertEquals(firstValidType, actualResult);
    }

    @Test
    void seeIfGetByIDWorksIfItDoesNotExist() {
        //Arrange

        Mockito.when(areaTypeCrudRepo.findById("Country")).thenReturn(Optional.empty());

        //Act

        AreaType actualResult = areaTypeRepository.getById("Country");

        //Assert

        assertNull(actualResult);
    }

    @Test
    void seeIfGetByNameWorks() {
        //Arrange

        Mockito.when(areaTypeCrudRepo.findByName("Country")).thenReturn(Optional.of(firstValidType));

        //Act

        AreaType actualResult = areaTypeRepository.getAreaTypeByName("Country");

        //Assert

        assertEquals(firstValidType, actualResult);
    }



    @Test
    void seeIfGetAreaTypeByNameWorks() {
        //Arrange

        Mockito.when(areaTypeCrudRepo.findByName("Country")).thenReturn(Optional.empty());

        //Act

        AreaType actualResult = areaTypeRepository.getAreaTypeByName("Country");

        //Assert

        assertNull(actualResult);
    }

    @Test
    void seeIfGetAreaTypes() {
        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(firstValidType);
        areaTypes.add(secondValidType);

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        List<AreaType> result = areaTypeRepository.getAreaTypes();

        assertEquals(result, areaTypes);
    }

    @Test
    void seeIfCreateAreaType() {
        AreaType expectedResult = new AreaType("Rua");

        AreaType result = areaTypeRepository.create("Rua");

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSizeRepository() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        int actualResult = areaTypeRepository.size();

        assertEquals(1, actualResult);
    }


    @Test
    void seeIfIsEmptyFalse() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        assertFalse(areaTypeRepository.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        assertTrue(areaTypeRepository.isEmpty());
    }

    @Test
    void getAllAsString() {
        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(secondValidType);
        areaTypes.add(firstValidType);

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        String expectedResult = "---------------\n" +
                "Name: City \n" +
                "Name: Country \n" +
                "---------------\n";

        String result = areaTypeRepository.getAllAsString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {
        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeCrudRepo.findAll()).thenReturn(areaTypes);

        String expectedResult = "Invalid List - List is Empty\n";

        String result = areaTypeRepository.getAllAsString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddWorks() {
        //Arrange

        AreaType areatype = new AreaType("Name");
        Mockito.when(areaTypeCrudRepo.findByName("Name")).thenReturn(Optional.empty());

        //Act
        boolean actualResult = areaTypeRepository.add(areatype);

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorksWhenAreaTypeAlreadyExistsInRepository() {
        //Arrange

        AreaType areatype = new AreaType("Name");
        Mockito.when(areaTypeCrudRepo.findByName("Name")).thenReturn(Optional.of(areatype));

        //Act
        boolean actualResult = areaTypeRepository.add(areatype);

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<AreaType> testList = new ArrayList<>();
        testList.add(secondValidType);

        // Act

        boolean actualResult = areaTypeCrudRepo.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = areaTypeCrudRepo.equals(new WaterHeater(new WaterHeaterSpec())); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = areaTypeCrudRepo.equals(areaTypeCrudRepo); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }
}