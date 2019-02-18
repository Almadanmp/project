package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import pt.ipp.isep.dei.project.model.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * EnergyGridSettingsController tests class.
 */

class EnergyGridSettingsControllerTest {

    // Common artifacts for testing in this class.

    public static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeDT";
    private House validHouse;
    private EnergyGrid validGrid;
    private EnergyGridSettingsController controller = new EnergyGridSettingsController();

    @BeforeEach
    void arrangeArtifacts() {
        validHouse = new House("ISEP", "Rua Dr. António Bernardino de Almeida", "4200-072",
                "Porto", new Local(20, 20, 20), new GeographicArea("Porto",
                new TypeArea("Cidade"), 2, 3, new Local(4, 4, 100)),
                60, 180, new ArrayList<>());
        validGrid = new EnergyGrid("validGrid", 300);
    }


    //US145


    @Test
    void seeIfRoomsPrint() {

        // Arrange

        RoomList roomList = new RoomList();
        Room room = new Room("Quarto", 1, 20, 2, 2);
        roomList.addRoom(room);
        validHouse.setRoomList(roomList);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildRoomsString(roomList);

        // Assert

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomsPrintNull() {
        //Arrange

        String expectedResult = "The Room List wasn't properly initialized. Please try again.";

        //Act

        String actualResult = controller.buildRoomsString(null);

        //Assert

        Assert.assertEquals(expectedResult, actualResult);
    }

    //USER STORY 149

    @Test
    void seeIfRoomIsRemovedFromGrid() {

        //Arrange

        Room room = new Room("Quarto", 1, 20, 2, 2);
        validGrid.addRoomToAnEnergyGrid(room);

        //Act

        boolean actualresult = controller.removeRoomFromGrid(validGrid, room);

        //Assert

        Assertions.assertTrue(actualresult);
    }

    @Test
    void seeIfEnergyGridPrints() {

        // Arrange

        String expectedResult = "Energy Grid: validGrid, Max Power: 300.0";

        // Act

        String actualResult = controller.buildEnergyGridString(validGrid);

        // Assert

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGridListPrints() {

        // Arrange

        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addGrid(validGrid);
        validHouse.setEGList(energyGridList);
        String expectedResult = "---------------\n" +
                "0) Designation: validGrid | Max Power: 300.0\n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildGridListString(validHouse);

        // Assert

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfRoomIsRemovedFromGridFalse() {

        //Arrange

        Room room = new Room("Quarto", 1, 20, 2, 2);

        //Act

        boolean actualresult = controller.removeRoomFromGrid(validGrid, room);

        //Assert

        assertFalse(actualresult);
    }

    @Test
    void ensureThatWeAddRoomToTheGrid() {

        // Arrange

        Room room = new Room("Quarto", 1, 20, 2, 2);
        EnergyGridList gridList = new EnergyGridList();
        gridList.addGrid(validGrid);
        RoomList rl = new RoomList();
        validGrid.setRoomList(rl);

        // Act

        boolean actualResult = controller.addRoomToGrid(validGrid, room);

        // Assert

        Assertions.assertTrue(actualResult);
    }

    @Test
    void ensureThatWeDoNotAddRoomToTheGrid() {

        // Arrange

        EnergyGridList gridList = new EnergyGridList();
        gridList.addGrid(validGrid);
        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        Room room = new Room("Quarto", 1, 20, 2, 2);
        roomList.addRoom(room);

        // Act

        boolean actualResult = controller.addRoomToGrid(validGrid, room);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfRoomListIsPrintedByHouse() {
        // Arrange

        Room room = new Room("Quarto", 1, 20, 2, 2);
        validHouse.addRoomToRoomList(room);
        String expectedResult = "---------------\n" +
                "0) Designation: Quarto | House Floor: 1 | Width: 20.0 | Length: 2.0 | Height: 2.0\n" +
                "---------------\n";

        // Act

        String actualResult = controller.buildHouseRoomListString(validHouse);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddPowerSourceToEnergyGridWorks() {
        // Arrange

        PowerSource powerSource = new PowerSource("PowerSourceOne", 10, 10);

        // Act

        boolean result = controller.addPowerSourceToGrid(validGrid, powerSource);

        // Assert

        Assertions.assertTrue(result);
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
    void seeIfAddEnergyGridToHouseWorks() {

        // Arrange

        EnergyGrid energyGrid = new EnergyGrid("grid", 400);


        // Act

        boolean actualResult = controller.addEnergyGridToHouse(validHouse, energyGrid);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfCreateGridTrue() {
        // Arrange

        EnergyGrid expectedResult = new EnergyGrid("EG1", 400);

        // Act

        EnergyGrid actualResult = controller.createEnergyGrid("EG1", 400);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintRoomWorks() {
        // Arrange

        Room room = new Room("quarto1", 1, 2, 2, 2);
        String expectedResult = "quarto1, 1, 2.0, 2.0, 2.0.\n";

        // Act

        String actualResult = controller.buildRoomString(room);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsInvalidList() {
        // Arrange

        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String actualResult = controller.buildGridRoomsString(validGrid);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfPrintsRoomList() {
        // Arrange

        Room roomOne = new Room("B117", 1, 7, 11, 3.5);
        Room roomTwo = new Room("B109", 1, 7, 11, 3.5);
        Room roomThree = new Room("B106", 1, 7, 13, 3.5);
        EnergyGridList gridList = new EnergyGridList();
        gridList.addGrid(validGrid);
        RoomList roomList = new RoomList();
        validGrid.setRoomList(roomList);
        roomList.addRoom(roomOne);
        roomList.addRoom(roomTwo);
        roomList.addRoom(roomThree);
        String expectedResult = "---------------\n" +
                "0) Designation: B117 | House Floor: 1 | \n" +
                "1) Designation: B109 | House Floor: 1 | \n" +
                "2) Designation: B106 | House Floor: 1 | \n" +
                "---------------\n";

        // Act

        String result = controller.buildGridRoomsString(validGrid);

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeCreatePowerSource() {
        // Arrange

        PowerSource powerSource1 = new PowerSource("powersource1", 10, 10);
        PowerSource powerSource2 = new PowerSource("powersource2", 123, 76);

        //Act

        PowerSource actualResult1 = controller.createPowerSource("powersource1", 10, 10);
        PowerSource actualResult2 = controller.createPowerSource("powersource2", 123, 76);

        //Assert

        assertEquals(actualResult1, powerSource1);
        assertEquals(actualResult2, powerSource2);
    }
}
