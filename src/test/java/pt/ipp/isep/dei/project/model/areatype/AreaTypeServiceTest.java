package pt.ipp.isep.dei.project.model.areatype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.room.RoomService;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TypeAreaList tests class.
 */
@ExtendWith(MockitoExtension.class)
class AreaTypeServiceTest {
    // Common testing artifacts for this class.

    private AreaType firstValidType;
    private AreaType secondValidType;

    @Mock
    private AreaTypeRepository areaTypeRepository;

    private AreaTypeService areaTypeService;

    @BeforeEach
    void arrangeArtifacts() {
        areaTypeService = new AreaTypeService(areaTypeRepository);
        firstValidType = new AreaType("Country");
        secondValidType = new AreaType("City");
    }

    @Test
    void seeIfGetAreaTypes() {
        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(firstValidType);
        areaTypes.add(secondValidType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        List<AreaType> result = areaTypeService.getAreaTypes();

        assertEquals(result, areaTypes);
    }

    @Test
    void seeIfCreateAreaType() {
        AreaType expectedResult = new AreaType("Rua");

        AreaType result = areaTypeService.create("Rua");

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;

        AreaType areaType = new AreaType("Street");
        areaType.setId(mockId);

        Mockito.when(areaTypeRepository.findById(mockId)).thenReturn(Optional.of(areaType));

        AreaType result = areaTypeService.getById(mockId);

        assertEquals(result.getId(), areaType.getId());
        assertEquals(result.getName(), areaType.getName());
    }

    @Test
    void seeIfSizeRepository() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        int result = areaTypeService.size();

        assertEquals(result, 1);
    }


    @Test
    void seeIfIsEmptyFalse() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        assertFalse(areaTypeService.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        assertTrue(areaTypeService.isEmpty());
    }

    @Test
    void getAllAsString() {
        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(secondValidType);
        areaTypes.add(firstValidType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        String expectedResult = "---------------\n" +
                "0) Name: City \n" +
                "0) Name: Country \n" +
                "---------------\n";

        String result = areaTypeService.getAllAsString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {
        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        String expectedResult = "Invalid List - List is Empty\n";

        String result = areaTypeService.getAllAsString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfAddWorks() {
        //Arrange

        AreaType areatype = new AreaType("Name");
        Mockito.when(areaTypeRepository.findByName("Name")).thenReturn(Optional.empty());

        //Act
        boolean actualResult = areaTypeService.add(areatype);

        assertTrue(actualResult);
    }

    @Test
    void seeIfAddWorksWhenAreaTypeAlreadyExistsInRepository() {
        //Arrange

        AreaType areatype = new AreaType("Name");
        Mockito.when(areaTypeRepository.findByName("Name")).thenReturn(Optional.of(areatype));

        //Act
        boolean actualResult = areaTypeService.add(areatype);

        assertFalse(actualResult);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<AreaType> testList = new ArrayList<>();
        testList.add(secondValidType);

        // Act

        boolean actualResult = areaTypeService.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = areaTypeService.equals(new RoomService()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = areaTypeService.equals(areaTypeService); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }
}