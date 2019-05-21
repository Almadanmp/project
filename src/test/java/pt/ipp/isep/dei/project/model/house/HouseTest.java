package pt.ipp.isep.dei.project.model.house;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.ipp.isep.dei.project.model.Local;
import pt.ipp.isep.dei.project.model.device.devicetypes.DeviceType;
import pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType;
import pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType;
import pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType;
import pt.ipp.isep.dei.project.model.geographicarea.AreaSensor;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicArea;
import pt.ipp.isep.dei.project.model.room.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * House tests class.
 */
@ExtendWith(MockitoExtension.class)
class HouseTest {

    // Common artifacts for testing in this class.

    private static final String PATH_TO_FRIDGE = "pt.ipp.isep.dei.project.model.device.devicetypes.FridgeType";
    private House validHouse;
    private GeographicArea validArea;
    private AreaSensor firstValidAreaSensor;
    private List<String> deviceTypeString;

    @BeforeEach
    void arrangeArtifacts() {
        deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        validArea = new GeographicArea("Europe", "Continent", 3500, 3000,
                new Local(20, 12, 33));
        validArea.setId(11L);
        validHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        firstValidAreaSensor = new AreaSensor("RF12345", "tempOne", "Temperature", new Local(
                30, 20, 10), new Date());
        AreaSensor secondValidAreaSensor = new AreaSensor("RF17745", "rainOne", "Rainfall", new Local(21,
                40, 15), new Date());
        validHouse.setMotherAreaID(validArea.getId());
    }

    @Test
    void seeIfConstructorWorks() {
        //Arrange

        House house = new House("12", new Local(2, 2, 2), 2, 2, deviceTypeString);
        Local expectedLocal = new Local(2, 2, 2);
        List<DeviceType> expectedList = new ArrayList<>();
        expectedList.add(new FridgeType());

        // Act

        String actualResult1 = house.getId();
        Local actualResult2 = house.getLocation();
        int actualResult3 = house.getGridMeteringPeriod();
        int actualResult4 = house.getDeviceMeteringPeriod();
        List<DeviceType> actualResult5 = house.getDeviceTypeList();


        // Assert
        assertEquals("12", actualResult1);
        assertEquals(expectedLocal, actualResult2);
        assertEquals(2, actualResult3);
        assertEquals(2, actualResult4);
        assertEquals(expectedList.get(0).getDeviceType(), actualResult5.get(0).getDeviceType());
    }


    @Test
    void seeIfGetsId() {
        //Arrange

        House house = new House(); //Want to test constructor as well
        house.setId("ISEP");

        // Act

        String actualResult = house.getId();

        // Assert

        assertEquals("ISEP", actualResult);
    }


    @Test
    void seeIfEqualWorksWithNull() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherAreaID(validArea.getId());

        // Act

        boolean actualResult = validHouse.equals(null);

        // Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfEqualWorksEqualObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherAreaID(validArea.getId());

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeEqualToSameObject() {
        // Act

        boolean actualResult = validHouse.equals(validHouse); // Needed for Sonarqube testing purposes.

        // Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsWorksDifferentObject() {
        // Arrange

        List<String> deviceTypeString = new ArrayList<>();
        deviceTypeString.add(PATH_TO_FRIDGE);
        House testHouse = new House("ISEP1", new Address("Rua Dr. António Bernardino de Almeida", "431",
                "4455-125", "Porto", "Portugal"),
                new Local(20, 20, 20), 60,
                180, deviceTypeString);
        testHouse.setMotherAreaID(validArea.getId());

        // Act

        boolean actualResult = validHouse.equals(testHouse);

        // Assert

        assertTrue(actualResult);
    }


    @Test
    void seeIfEqualsWorksNotInstanceOf() {
        // Arrange

        Room testRoom = new Room("Bedroom", "Single Bedroom", 2, 30, 30, 10, "Room1");

        // Act

        boolean actualResult = validHouse.equals(testRoom); // Needed for Sonarqube testing purposes.

        // Assert

        assertFalse(actualResult);
    }


    @Test
    void seeIfGetSetHouseLocationWorks() {
        // Arrange

        Local expectedResult = new Local(7, 78, 50);

        // Act

        validHouse.setLocation(7, 78, 50);
        Local actualResult = validHouse.getLocation();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetHouseLocationWorksAltitude() {
        // Arrange

        double expectedResult = 70;

        // Act

        validHouse.setLocation(7, 78, 70);
        double actualResult = validHouse.getAltitude();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfIsMotherAreaNullBothConditions() {
        // Act

        boolean actualResult1 = validHouse.isMotherAreaNull();
        validHouse.setMotherAreaID(new GeographicArea().getId());
        boolean actualResult2 = validHouse.isMotherAreaNull();

        // Assert

        assertFalse(actualResult1);
        assertTrue(actualResult2);
    }


    @Test
    void seeIfBuildDeviceTypeStringWorks() {
        // Arrange

        String expectedResult = "0) DeviceType: Fridge\n";

        // Act

        String actualResult = validHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfBuildDeviceTypeStringWorksEmptyList() {
        // Arrange

        House testHouse = new House("Mock", new Address("Mock", "Mock", "Mock", "Mock", "Mock"),
                new Local(4, 5, 50), 20,
                5, new ArrayList<>());
        testHouse.setMotherAreaID(validArea.getId());
        String expectedResult = "Invalid List - List is Empty\n";

        // Act

        String result = testHouse.buildDeviceTypeString();

        // Assert

        assertEquals(expectedResult, result);
    }

    @Test
    void seeIfGetDeviceTypeListWorks() {
        // Arrange

        List<String> deviceTypePaths = new ArrayList<>();
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.DishwasherType");
        deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.WaterHeaterType");

        // Act

        validHouse.buildAndSetDeviceTypeList(deviceTypePaths);
        List<DeviceType> deviceTypeList = validHouse.getDeviceTypeList();
        int expectedResult = 2;

        // Assert

        assertEquals(expectedResult, deviceTypeList.size());
        assertTrue(deviceTypeList.get(0) instanceof DishwasherType);
        assertTrue(deviceTypeList.get(1) instanceof WaterHeaterType);
    }

    @Test
    void seeIfGetDeviceTypeListWorksIllegalArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    List<String> deviceTypePaths = new ArrayList<>();
                    deviceTypePaths.add("pt.ipp.isep.dei.project.model.device.devicetypes.Dish");

                    validHouse.buildAndSetDeviceTypeList(deviceTypePaths);
                });
    }

    @Test
    void setGridMeteringPeriod() {
        // Act

        validHouse.setGridMeteringPeriod(8);
        validHouse.setDeviceMeteringPeriod(10);
        double actualResultGrid = validHouse.getGridMeteringPeriod();
        double actualResultDevice = validHouse.getDeviceMeteringPeriod();

        // Assert

        assertEquals(8, actualResultGrid);
        assertEquals(10, actualResultDevice);
    }

    @Test
    void seeIfDeviceTypeListSizeWorks() {
        //Act

        int actualResult1 = validHouse.deviceTypeListSize();

        //Assert

        assertEquals(1, actualResult1);
    }

    @Test
    void seeIfSetMotherAreaWorks() {
        //Act
        GeographicArea geoArea = new GeographicArea("Porto", "City", 50, 13, new Local(5, 5, 5));
        validHouse.setMotherAreaID(geoArea.getId());

        //Assert
        assertEquals(validHouse.getMotherAreaID(), geoArea.getId());
    }

    @Test
    void seeIfSetGetAddressWorks() {
        //Act
        Address address = new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal");
        validHouse.setAddress(address);

        //Assert
        assertEquals(validHouse.getAddress(), new Address("Rua do ISEP", "431", "4400", "Campus", "Portugal"));
    }

    @Test
    void seeIfIsMotherAreaNullWorks() {
        //Act

        boolean actualResult1 = validHouse.isMotherAreaNull();

        //Assert

        assertFalse(actualResult1);

        //Arrange As Null

        validHouse.setMotherAreaID(null);

        //Act

        boolean actualResult2 = validHouse.isMotherAreaNull();

        //Assert

        assertTrue(actualResult2);
    }

    @Test
    void hashCodeDummyTest() {
        // Arrange

        int expectedResult = 1;

        // Act

        int actualResult = validHouse.hashCode();

        // Assert

        assertEquals(expectedResult, actualResult);
    }
}