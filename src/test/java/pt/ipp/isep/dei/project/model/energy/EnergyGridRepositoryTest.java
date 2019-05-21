package pt.ipp.isep.dei.project.model.energy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.RoomDTOWeb;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.dto.mappers.RoomWebMapper;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.repository.EnergyGridCrudRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class EnergyGridRepositoryTest {
    // Common testing artifacts for testing class.

    private EnergyGrid firstValidGrid;

    @Mock
    private EnergyGridCrudRepo energyGridCrudRepository;
    @InjectMocks
    private EnergyGridRepository validGridRepo;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        firstValidGrid = new EnergyGrid("Primary Grid", 500D, "CasaUm");
    }

    @Test
    void seeIfGetAllGridsWorks() {
        // Arrange
        Mockito.when(energyGridCrudRepository.findAll()).thenReturn(null);

        List<EnergyGrid> expectedResult = new ArrayList<>();

        // Act
        List<EnergyGrid> actualResult = validGridRepo.getAllGrids();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddGridWorks() {
        // Arrange

        Mockito.when(energyGridCrudRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        EnergyGrid expectedResult = firstValidGrid;

        // Act

        EnergyGrid actualResult = validGridRepo.addGrid(firstValidGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddGridWorksNull() {
        // Arrange

        Mockito.when(energyGridCrudRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        EnergyGrid expectedResult = firstValidGrid;

        // Act

        EnergyGrid actualResult = validGridRepo.addGrid(firstValidGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    /**
     * This test tries to have a Grid with powersources and one equal but without them.
     * Then it saves the grid with powersources in the mockito repository.
     * Then it saves the grid without PS, to see if by substituting the grid it still keeps the powersources.
     * The test is positive, meaning it keeps the powersources. We are still not sure if this is
     * the wanted behaviour, but if it is, it works.
     */

    @Test
    void seeIfAddGridWorksAndKeepsPowerSourceList() {

        // Arrange
        PowerSource firstPowerSource = new PowerSource("Top Floor", 25,
                15);

        List<PowerSource> expectedResult = new ArrayList<>();
        expectedResult.add(firstPowerSource);

        EnergyGrid firstValidGridWithNoPSList = firstValidGrid;

        firstValidGrid.addPowerSource(firstPowerSource);

        String mockId = "Primary Grid";

        Mockito.when(energyGridCrudRepository.findByName(mockId)).thenReturn(firstValidGrid);

        Mockito.when(energyGridCrudRepository.save(firstValidGrid)).thenReturn(firstValidGrid);

        // Act
        validGridRepo.addGrid(firstValidGrid);
        validGridRepo.addGrid(firstValidGridWithNoPSList);
        EnergyGrid copiedGridWithoutPS = energyGridCrudRepository.findByName("Primary Grid");

        List<PowerSource> actualResult = copiedGridWithoutPS.getPowerSourceList();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateEnergyGrid() {
        EnergyGrid expectedResult = new EnergyGrid("Primary Grid", 500D, "CasaUm");

        EnergyGrid result = validGridRepo.createEnergyGrid("Primary Grid", 500, "CasaUm");

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetEnergyGridByIdRepository() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56D, "CasaUm");
        validGridRepo.addGrid(energyGrid);

        Mockito.when(energyGridCrudRepository.findById(energyGrid.getName())).thenReturn(Optional.of(energyGrid));

        EnergyGrid result = validGridRepo.getById(energyGrid.getName());

        assertEquals(result.getName(), energyGrid.getName());
    }

    @Test
    void seeIfGetEnergyGridByIdRepositoryNull() {
        String mockId = "1234";

        Mockito.when(energyGridCrudRepository.findById(mockId)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> validGridRepo.getById(mockId));

        assertEquals("ERROR: There is no Energy Grid with the selected ID.", exception.getMessage());
    }

    @Test
    void seeIfSizeRepository() {

        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56D, "CasaUm");

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(energyGrid);
        int expectedResult = 1;

        Mockito.when(energyGridCrudRepository.findAll()).thenReturn(energyGrids);

        int result = validGridRepo.size();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfSizeRepositoryDoNotChange() {

        List<EnergyGrid> energyGrids = new ArrayList<>();
        energyGrids.add(null);
        int expectedResult = 1;

        Mockito.when(energyGridCrudRepository.findAll()).thenReturn(energyGrids);

        int result = validGridRepo.size();

        assertEquals(expectedResult, result);
    }

//    @Test
//    void seeIfIsEmptyFalse() {
//
//        EnergyGrid energyGrid = new EnergyGrid("Third Grid", 56, "CasaUm");
//
//        validGridRepo.addGrid(energyGrid);
//
//
//        assertFalse(validGridRepo.isEmpty());
//
//    }

    @Test
    void seeIfIsEmptyTrue() {

        assertTrue(validGridRepo.isEmpty());
    }

    @Test
    void getAllAsString() {

        String expectedResult = "Invalid List - List is Empty\n";

        String result = validGridRepo.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void getAllAsStringEmpty() {

        String expectedResult = "Invalid List - List is Empty\n";

        String result = validGridRepo.buildString();

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfEqualsWorks() {
        // Arrange
        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500D, "CasaUm");
        List<EnergyGrid> testList = new ArrayList<>();
        List<EnergyGrid> validList = new ArrayList<>();
        validList.add(energyGrid);
        testList.add(energyGrid);

        // Act

        boolean actualResult = testList.equals(validList);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksDifferentContents() {
        // Arrange

        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500D, "CasaUm");
        EnergyGrid energyGrid2 = new EnergyGrid("Primary Grid", 500D, "CasaUm");
        List<EnergyGrid> testList = new ArrayList<>();
        List<EnergyGrid> validList = new ArrayList<>();
        validList.add(energyGrid);
        testList.add(energyGrid2);

        // Act

        boolean actualResult = validGridRepo.equals(testList);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualsItselfWorks() {
        // Act

        boolean actualResult = validGridRepo.equals(validGridRepo); // For sonarqube coverage purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfIsEmpty() {
        //Arrange

        // Act

        boolean actualResult1 = validGridRepo.isEmpty();

        // Assert

        assertTrue(actualResult1);
    }

//    @Test
//    void getElementsAsArray() {
//        //Arrange
//
//        EnergyGrid energyGrid = new EnergyGrid("Primary Grid", 500);
//
//
//        EnergyGrid[] expectedResult1 = new EnergyGrid[0];
//        EnergyGrid[] expectedResult2 = new EnergyGrid[1];
//        EnergyGrid[] expectedResult3 = new EnergyGrid[2];
//
//        EnergyGridService emptyList = new EnergyGridService();
//        validGridRepo.addGrid(energyGrid);
//        EnergyGridService validEnergyGridService2 = new EnergyGridService();
//        validEnergyGridService2.addGrid(energyGrid);
//        validEnergyGridService2.addGrid(secondValidGrid);
//
//        expectedResult2[0] = energyGrid;
//        expectedResult3[0] = energyGrid;
//        expectedResult3[1] = secondValidGrid;
//
//        //Act
//
//        EnergyGrid[] actualResult1 = emptyList.getElementsAsArray();
//        EnergyGrid[] actualResult2 = validGridRepo.getElementsAsArray();
//        EnergyGrid[] actualResult3 = validEnergyGridService2.getElementsAsArray();
//
//        //Assert
//
//        assertArrayEquals(expectedResult1, actualResult1);
//        assertArrayEquals(expectedResult2, actualResult2);
//        assertArrayEquals(expectedResult3, actualResult3);
//    }


    @Test
    void seeIfEqualsDifferentObjectWorks() {
        // Act

        boolean actualResult = validGridRepo.equals(firstValidGrid); // For sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }


//    @Test
//    void gridListSize() {
//        //Act
//
//        int actualResult1 = validGridRepo.size();
//
//        //Assert Empty List
//
//        assertEquals(0, actualResult1);
//
//        //Arrange
//
//        validGridRepo.addGrid(new EnergyGrid("grid", 200));
//
//        //Act
//
//        int actualResult2 = validGridRepo.size();
//
//        //Assert One Grid
//
//        assertEquals(1, actualResult2);
//    }

    @Test
    void seeIfAttachRoomToGridWorks() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setHouseId("ISEP");
        roomDTO.setHeight(3);
        roomDTO.setLength(3);
        roomDTO.setWidth(3);
        roomDTO.setFloor(1);
        roomDTO.setDescription("Classroom");
        EnergyGrid energyGrid = new EnergyGrid("Main Grid", 200D, "ISEP");
        Mockito.when(energyGridCrudRepository.findById("Main Grid")).thenReturn(Optional.of(energyGrid));
        //Act
        boolean actualResult = validGridRepo.attachRoomToGrid(roomDTO, "Main Grid");
        //Assert
        assertTrue(actualResult);

    }

    @Test
    void seeIfAttachRoomToGridDoesNotWorks() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setHouseId("ISEP");
        roomDTO.setHeight(3);
        roomDTO.setLength(3);
        roomDTO.setWidth(3);
        roomDTO.setFloor(1);
        roomDTO.setDescription("Classroom");
        EnergyGrid energyGrid = new EnergyGrid("Main Grid", 200D, "ISEP");
        energyGrid.addRoom(RoomMapper.dtoToObject(roomDTO));
        Mockito.when(energyGridCrudRepository.findById("Main Grid")).thenReturn(Optional.of(energyGrid));
        //Act
        boolean actualResult = validGridRepo.attachRoomToGrid(roomDTO, "Main Grid");
        //Assert
        assertFalse(actualResult);

    }

    @Test
    void seeIfRemoveRoomFromGridWorksValidGridInvalidRoom() {
        // Arrange

        EnergyGrid mockGrid = new EnergyGrid("firstGrid", 21D, "01");
        Optional<EnergyGrid> repoReturn = Optional.of(mockGrid);
        Mockito.when(energyGridCrudRepository.findById("firstGrid")).thenReturn(repoReturn);

        // Act

        boolean actualResult = validGridRepo.removeRoomFromGrid("Invalid", "firstGrid");

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRemoveRoomFromGridWorksInvalidGrid() {
        // Assert

        assertThrows(NoSuchElementException.class,
                () -> validGridRepo.removeRoomFromGrid("B107", "Invalid"));
    }

    @Test
    void seeIfRemoveRoomFromGridWorks() {
        // Arrange

        EnergyGrid mockGrid = new EnergyGrid("firstGrid", 21D, "01");

        // Create the room

        List<Double> roomDimensions = new ArrayList<>();
        roomDimensions.add(31D);
        roomDimensions.add(15D);
        roomDimensions.add(13D);
        Room mockRoom = new Room("B107", "The lesser room", 1, roomDimensions, "01");

        // Set up the artifacts

        mockGrid.addRoom(mockRoom);
        Optional<EnergyGrid> repoReturn = Optional.of(mockGrid);
        Mockito.when(energyGridCrudRepository.findById("firstGrid")).thenReturn(repoReturn);

        // Act

        boolean actualResult = validGridRepo.removeRoomFromGrid("B107", "firstGrid");

        // Assert

        assertTrue(actualResult);
    }
    @Test
    void seeIfGetRoomsWebDtoWorks() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("B109");
        roomDTO.setHouseId("ISEP");
        roomDTO.setHeight(3);
        roomDTO.setLength(3);
        roomDTO.setWidth(3);
        roomDTO.setFloor(1);
        roomDTO.setDescription("Classroom");
        EnergyGrid energyGrid = new EnergyGrid("Main Grid", 200D, "ISEP");
        energyGrid.addRoom(RoomMapper.dtoToObject(roomDTO));
        Mockito.when(energyGridCrudRepository.findByName("Main Grid")).thenReturn(energyGrid);
        //Act
        List<RoomDTOWeb> expectedResult = new ArrayList<>();
        expectedResult.add(RoomWebMapper.objectToDtoWeb(RoomMapper.dtoToObject(roomDTO)));
        List<RoomDTOWeb> actualResult = validGridRepo.getRoomsDtoWebInGrid("Main Grid");
        //Assert
        assertEquals(expectedResult,actualResult);
    }

}

