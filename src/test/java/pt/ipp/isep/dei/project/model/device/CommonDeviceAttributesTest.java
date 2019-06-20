//package pt.ipp.isep.dei.project.model.device;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CommonDeviceAttributesTest {
//
//    @Test
//    void seeIfBuildStringWorks() {
//        // Arrange
//
//        CommonDeviceAttributes commonDeviceAttributes = new CommonDeviceAttributes();
//        commonDeviceAttributes.setName("Fridge");
//        commonDeviceAttributes.setNominalPower(20D);
//
//        String expectedResult = "The device Name is Fridge, and its NominalPower is 20.0 kW.\n";
//
//        // Act
//
//        String actualResult = commonDeviceAttributes.buildString();
//
//        // Assert
//
//        assertEquals(expectedResult, actualResult);
//    }
//
//    @Test
//    void seeIfSetActiveWorks() {
//        CommonDeviceAttributes commonDeviceAttributes = new CommonDeviceAttributes();
//        commonDeviceAttributes.setName("Fridge");
//        commonDeviceAttributes.setNominalPower(20D);
//        commonDeviceAttributes.setActive(commonDeviceAttributes.isActive());
//    }
//
//}