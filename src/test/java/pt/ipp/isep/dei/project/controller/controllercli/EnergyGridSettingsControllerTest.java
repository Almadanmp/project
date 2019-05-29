package pt.ipp.isep.dei.project.controller.controllercli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.dto.RoomDTO;
import pt.ipp.isep.dei.project.dto.mappers.RoomMapper;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.bridgeservices.EnergyGridRoomService;
import pt.ipp.isep.dei.project.model.device.Device;
import pt.ipp.isep.dei.project.model.device.Fridge;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.energy.EnergyGrid;
import pt.ipp.isep.dei.project.model.energy.EnergyGridRepository;
import pt.ipp.isep.dei.project.model.energy.PowerSource;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.room.Room;
import pt.ipp.isep.dei.project.model.room.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EnergyGridSettingsController tests class.
 */
@ExtendWith(MockitoExtension.class)
class EnergyGridSettingsControllerTest {

    // Common artifacts for testing in this class.

    @Mock
    private EnergyGridRepository energyGridRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private EnergyGridRoomService energyGridRoomService;

    @InjectMocks
    private EnergyGridSettingsController controller;

    private List<Room> rooms = new ArrayList<>();
    private Device validFridge;
    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private EnergyGrid validGrid;
    private Room validRoom;
    private Room validRoom2;
    private GeographicArea validGeographicArea;

    @BeforeEach
    void arrangeArtifacts() {
        validGeographicArea = new GeographicArea("Porto", "Cidade", 2, 3, new Local(4, 4, 100));

        validGrid = new EnergyGrid("validGrid", 300D, "34576");
        validRoom = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1");
        validRoom2 = new Room("Office", "2nd Floor Office", 2, 30, 30, 10, "Room1");
        validFridge = new Fridge(new FridgeSpec());
        validFridge.setNominalPower(20);
        validFridge.setAttributeValue(FridgeSpec.FREEZER_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.REFRIGERATOR_CAPACITY, 200D);
        validFridge.setAttributeValue(FridgeSpec.ANNUAL_CONSUMPTION, 200D);
        validRoom.addDevice(validFridge);
        energyGridRoomService.addRoom(validGrid, validRoom2);
    }

    //US145

    @Test
    void seeIfRoomsPrint() {

        // Arrange
        List<Room> rooms = new ArrayList<>();
        rooms.add(validRoom);

        String expectedResult = "---------------\n" +
                "Room) Description: Double Bedroom | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";

        Mockito.when(roomRepository.buildRoomsAsString(rooms)).thenReturn(expectedResult);
        String actualResult = controller.buildRoomsString(rooms);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGridNoRoom() {

        //Arrange

        Room room = new Room("Room", "Double Bedroom", 1, 20, 2, 2,
                "Room1");

        //Act

        List<Room> roomList = new ArrayList<>();
        roomList.add(room);

        validGrid.addRoomId(validRoom.getId());
        roomList.add(validRoom);
//        Mockito.when(roomRepository.getAllRooms()).thenReturn(roomList);

        boolean actualResult = controller.removeRoomFromGrid(validGrid, room);

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRoomIsRemovedFromGridFalse() {

        //Arrange

        Room room = new Room("Room", "Double Bedroom", 1, 20, 2, 2, "Room1");

        //Act

        boolean actualresult = controller.removeRoomFromGrid(validGrid, room);

        //Assert

        assertFalse(actualresult);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorks() {
        // Arrange

        PowerSource powerSource = new PowerSource("PowerSourceOne", 10, 10);

        // Act

        boolean result = controller.addPowerSourceToGrid(validGrid, powerSource);

        // Assert

        assertTrue(result);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorksFalse() {

        // Arrange

        PowerSource powerSource = new PowerSource("PowerSource", 20, 20);
        validGrid.addPowerSource(powerSource);

        // Act

        boolean result = controller.addPowerSourceToGrid(validGrid, powerSource);

        //Assert

        assertFalse(result);
    }

    @Test
    void seeIfCreateGridTrue() {
        // Arrange

        EnergyGrid expectedResult1 = new EnergyGrid("EG1", 400D, "34576");
        EnergyGrid expectedResult2 = new EnergyGrid("EG2", 400D, "34576");
        // Act
        Mockito.when(energyGridRepository.createEnergyGrid("EG1", 400, "34576")).thenReturn(expectedResult1);
        EnergyGrid actualResult1 = controller.createEnergyGrid("EG1", 400, "34576");
        Mockito.when(energyGridRepository.createEnergyGrid("EG2", 400, "34576")).thenReturn(expectedResult2);
        EnergyGrid actualResult2 = controller.createEnergyGrid("EG2", 400, "34576");
        // Assert

        assertEquals(expectedResult1, actualResult1);
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    void seeCreatePowerSource() {
        // Arrange

        PowerSource powerSource1 = new PowerSource("powersource1", 10, 10);
        PowerSource powerSource2 = new PowerSource("powersource2", 123, 76);

        // Act
        Mockito.when(energyGridRepository.createPowerSource("powersource1", 10, 10)).thenReturn(powerSource1);
        PowerSource actualResult1 = controller.createPowerSource("powersource1", 10, 10);
        Mockito.when(energyGridRepository.createPowerSource("powersource2", 123, 76)).thenReturn(powerSource2);
        PowerSource actualResult2 = controller.createPowerSource("powersource2", 123, 76);

        // Assert

        assertEquals(actualResult1, powerSource1);
        assertEquals(actualResult2, powerSource2);
    }

//    @Test
//    void testBuildListOfDevicesOrderedByTypeStringEmptyString() {
//        //Arrange
//
////        rooms.add(validRoom);
////        validGrid.addRoomId(validRoom.getId());
//    //    Mockito.when(roomRepository.getAllRooms()).thenReturn(new ArrayList<>());
//
//        EnergyGrid testGrid = new EnergyGrid("EmptyGrid", 100D, "34576");
//
//        //Act
//        String expectedResult = "---------------\n" +
//                "---------------\n";
//        String actualResult = controller.buildListOfDevicesOrderedByTypeString(testGrid);
//        //Arrange
//        assertEquals(expectedResult, actualResult);
//    }


//    @Test
//    void testBuildListOfDevicesOrderedByTypeStringWithDevices() {
//        //Arrange
//        List<String> deviceTypeString = new ArrayList<>();
//        deviceTypeString.add(PATH_TO_FRIDGE);
//        Address address = new Address("Rua das Flores", "431", "4512", "Porto", "Portugal");
//        House house = new House("casa de praia", address, new Local(4, 5, 4), 60, 180, deviceTypeString);
//        house.setMotherAreaID(validGeographicArea.getId());
//        Room room1EdC = new Room("B107", "Classroom", 1, 7, 11, 3.5, "Room1");
//        EnergyGrid eg = new EnergyGrid("Main Energy Grid Edificio C", 333D, "34576");
//        List<String> rl = new ArrayList<>();
//        Device fridge = new Fridge(new FridgeSpec());
//        room1EdC.addDevice(fridge);
//        rl.add(room1EdC.getId());
//        eg.setRooms(rl);
//        //Act
//        String expectedResult = "---------------\n" +
//                "Device type: Fridge | Device name: null | Nominal power: 0.0 | Room: B107 | \n" +
//                "---------------\n";
//        String actualResult = controller.buildListOfDevicesOrderedByTypeString(eg);
//        //Arrange
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void seeIfBuildGridListStringWorks() {
        //Arrange

        String expectedResult = "---------------\n" +
                "Designation: validGrid | Max Power: 300.0\n" +
                "---------------\n";

        //Act
        Mockito.when(EnergyGridSettingsController.buildGridListString(energyGridRepository)).thenReturn(expectedResult);
        String actualResult = EnergyGridSettingsController.buildGridListString(energyGridRepository);

        //Arrange

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void seeIfAddRoomDTOToGridWorks() {
//        // Arrange
//        RoomDTO testDTO = RoomMapper.objectToDTO(validRoom);
//
//        // Act
//        Mockito.when(roomRepository.updateHouseRoom(testDTO)).thenReturn(RoomMapper.dtoToObject(testDTO));
//        boolean actualResult = controller.addRoomDTOToGrid(validGrid, testDTO);
//
//        // Assert
//
//        assertTrue(actualResult);
//    }

    @Test
    void seeIfAddGridToHouseWorks() {
        // Act

        Mockito.when(energyGridRepository.addGrid(validGrid)).thenReturn(validGrid);
        EnergyGrid actualResult = controller.addEnergyGridToHouse(validGrid);

        // Assert

        assertEquals(validGrid, actualResult);
    }

    @Test
    void seeIfUpdateEnergyGridWorksFalse() {
        // Act


        assertThrows(RuntimeException.class,
                () -> controller.addEnergyGridToHouse(validGrid));

    }

    @Test
    void seeIfAddRoomDTOToGridWorksNoRooms() {
        // Arrange

        RoomDTO testDTO = RoomMapper.objectToDTO(validRoom);

        // Act

        Mockito.when(roomRepository.updateHouseRoom(testDTO)).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class,
                () -> controller.addRoomDTOToGrid(validGrid, testDTO));

    }
}