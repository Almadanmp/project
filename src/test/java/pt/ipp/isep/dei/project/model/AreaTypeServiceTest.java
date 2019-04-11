package pt.ipp.isep.dei.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.repository.AreaTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    private AreaTypeService validList;

    @BeforeEach
    void arrangeArtifacts() {
        validList = new AreaTypeService(areaTypeRepository);
        firstValidType = new AreaType("Country");
        secondValidType = new AreaType("City");
    }

    @Test
    void seeIfGetAreaTypes() {
        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(firstValidType);
        areaTypes.add(secondValidType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        List<AreaType> result = validList.getAreaTypes();

        assertEquals(result, areaTypes);
    }

    @Test
    void seeIfCreateAreaType() {
        AreaType expectedResult = new AreaType("Rua");

        AreaType result = validList.create("Rua");

        assertEquals(expectedResult, result);
    }


//    @Test
//    void seeIfAddAreaType() {
//        AreaType areaType = new AreaType("Street");
//
//        Mockito.when(areaTypeRepository.findByName(areaType.getName())).thenReturn(areaType);
//
//        assertTrue(validList.add(areaType));
//    }

    @Test
    void seeIfGetTypeAreaByIdRepository() {
        long mockId = 1234;

        AreaType areaType = new AreaType("Street");
        areaType.setId(mockId);

        Mockito.when(areaTypeRepository.findById(mockId)).thenReturn(Optional.of(areaType));

        AreaType result = validList.getById(mockId);

        assertEquals(result.getId(), areaType.getId());
        assertEquals(result.getName(), areaType.getName());
    }

    @Test
    void seeIfGetTypeAreaByIdRepositoryNull() {
        long mockId = 1234;

        Mockito.when(areaTypeRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> validList.getById(mockId));

        assertEquals("ERROR: There is no Area Type with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        int result = validList.size();

        assertEquals(result, 1);
    }


    @Test
    void seeIfIsEmptyFalse() {

        AreaType areaType = new AreaType("Street");

        List<AreaType> areaTypes = new ArrayList<>();
        areaTypes.add(areaType);

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        assertFalse(validList.isEmpty());

    }

    @Test
    void seeIfIsEmptyTrue() {

        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        assertTrue(validList.isEmpty());
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

        String result = validList.getAllAsString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {
        List<AreaType> areaTypes = new ArrayList<>();

        Mockito.when(areaTypeRepository.findAll()).thenReturn(areaTypes);

        String expectedResult = "Invalid List - List is Empty\n";

        String result = validList.getAllAsString();

        assertEquals(expectedResult, result);
    }


    @Test
    void seeIfEqualsWorksFalse() {
        // Arrange

        List<AreaType> testList = new ArrayList<>();
        testList.add(secondValidType);

        // Act

        boolean actualResult = validList.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksNotAnInstance() {
        // Act

        boolean actualResult = validList.equals(new RoomService()); // Needed for sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsWorksForItself() {
        // Act

        boolean actualResult = validList.equals(validList); // Needed for sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }
}