package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GeographicAreaDTOTest {
    // Common testing artifacts for testing in this class.

    private GeographicAreaDTO validDTO;
    private List<AreaSensorDTO> validAreaSensorDTOList;

    @BeforeEach
    void arrangeArtifacts() {
        validDTO = new GeographicAreaDTO();
        validAreaSensorDTOList = new ArrayList<>();
        validDTO.setSensorDTOList(validAreaSensorDTOList);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange
        AreaSensorDTO areaSensorDTO1 = new AreaSensorDTO();

        List<AreaSensorDTO> dtoList = new ArrayList<>();
        dtoList.add(areaSensorDTO1);
        List<AreaSensorDTO> dtoListEmpty = new ArrayList<>();

        GeographicAreaDTO geographicAreaDTO1 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO2 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO3 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO4 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO5 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO6 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO7 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO8 = new GeographicAreaDTO();
        GeographicAreaDTO geographicAreaDTO9 = new GeographicAreaDTO();

        geographicAreaDTO1.setDescription("geoAreaDTO1");
        geographicAreaDTO2.setDescription("geoAreaDTO2");
        geographicAreaDTO3.setDescription("geoAreaDTO3");
        geographicAreaDTO4.setDescription("geoAreaDTO4");
        geographicAreaDTO5.setDescription("geoAreaDTO5");
        geographicAreaDTO6.setDescription("geoAreaDTO1");
        geographicAreaDTO7.setDescription("geoAreaDTO1");
        geographicAreaDTO8.setDescription("geoAreaDTO1");
        geographicAreaDTO9.setDescription("geoAreaDTO1");

        geographicAreaDTO1.setName("01");
        geographicAreaDTO2.setName("02");
        geographicAreaDTO3.setName("03");
        geographicAreaDTO4.setName("01");
        geographicAreaDTO5.setName("01");
        geographicAreaDTO6.setName("01");
        geographicAreaDTO7.setName("01");
        geographicAreaDTO8.setName("01");
        geographicAreaDTO9.setName("01");

        LocalDTO localDTO1 = new LocalDTO();
        LocalDTO localDTO2 = new LocalDTO();
        LocalDTO localDTO3 = new LocalDTO();
        LocalDTO localDTO4 = new LocalDTO();
        LocalDTO localDTO5 = new LocalDTO();
        LocalDTO localDTO6 = new LocalDTO();
        LocalDTO localDTO7 = new LocalDTO();
        LocalDTO localDTO8 = new LocalDTO();
        LocalDTO localDTO9 = new LocalDTO();


        localDTO1.setAltitude(1);
        localDTO2.setAltitude(2);
        localDTO3.setAltitude(1);
        localDTO4.setAltitude(1);
        localDTO5.setAltitude(4);
        localDTO6.setAltitude(1);
        localDTO7.setAltitude(1);
        localDTO8.setAltitude(1);
        localDTO9.setAltitude(1);

        localDTO1.setLongitude(1);
        localDTO2.setLongitude(2);
        localDTO3.setLongitude(1);
        localDTO4.setLongitude(1);
        localDTO5.setLongitude(1);
        localDTO6.setLongitude(1);
        localDTO7.setLongitude(5);
        localDTO8.setLongitude(1);
        localDTO9.setLongitude(1);

        localDTO1.setLatitude(1);
        localDTO2.setLatitude(2);
        localDTO3.setLatitude(1);
        localDTO4.setLatitude(1);
        localDTO5.setLatitude(1);
        localDTO6.setLatitude(1);
        localDTO6.setLatitude(1);
        localDTO7.setLatitude(5);
        localDTO8.setLatitude(7);
        localDTO9.setLatitude(1);

        geographicAreaDTO1.setTypeArea("City");
        geographicAreaDTO2.setTypeArea("Town");
        geographicAreaDTO3.setTypeArea("City");
        geographicAreaDTO4.setTypeArea("Country");
        geographicAreaDTO5.setTypeArea("City");
        geographicAreaDTO6.setTypeArea("City");
        geographicAreaDTO7.setTypeArea("City");
        geographicAreaDTO8.setTypeArea("City");
        geographicAreaDTO9.setTypeArea("City");

        geographicAreaDTO1.setSensorDTOList(dtoList);
        geographicAreaDTO2.setSensorDTOList(dtoListEmpty);
        geographicAreaDTO3.setSensorDTOList(dtoList);
        geographicAreaDTO4.setSensorDTOList(dtoList);
        geographicAreaDTO5.setSensorDTOList(dtoList);
        geographicAreaDTO6.setSensorDTOList(dtoList);
        geographicAreaDTO7.setSensorDTOList(dtoList);
        geographicAreaDTO8.setSensorDTOList(dtoList);
        geographicAreaDTO9.setSensorDTOList(dtoListEmpty);

        geographicAreaDTO1.setLocalDTO(localDTO1);
        geographicAreaDTO2.setLocalDTO(localDTO2);
        geographicAreaDTO3.setLocalDTO(localDTO3);
        geographicAreaDTO4.setLocalDTO(localDTO4);
        geographicAreaDTO5.setLocalDTO(localDTO5);
        geographicAreaDTO6.setLocalDTO(localDTO6);
        geographicAreaDTO7.setLocalDTO(localDTO7);
        geographicAreaDTO8.setLocalDTO(localDTO8);
        geographicAreaDTO9.setLocalDTO(localDTO9);

        Double actualResult = 3D;

        //Assert

        assertEquals(geographicAreaDTO1, geographicAreaDTO1); // same object
        assertEquals(geographicAreaDTO1, geographicAreaDTO6); // different object
        assertNotEquals(geographicAreaDTO1, geographicAreaDTO2); //everything different
        assertNotEquals(geographicAreaDTO1, geographicAreaDTO3); // id different
        assertNotEquals(geographicAreaDTO1, geographicAreaDTO4); // type area
        assertEquals(geographicAreaDTO1, geographicAreaDTO5); //altitude
        assertNotEquals(geographicAreaDTO1, geographicAreaDTO7); //longitude
        assertNotEquals(geographicAreaDTO1, geographicAreaDTO8); //latitude
        assertNotEquals(geographicAreaDTO1, actualResult);
    }

    @Test
    void seeIfSetGetIDWorks() {
        // Arrange

        Long expectedresult = 6008L;
        GeographicAreaDTO dto = new GeographicAreaDTO();
        dto.setId(6008L);

        // Act

        Long actualResult = dto.getId();

        // Assert

        assertEquals(expectedresult, actualResult);
    }

    @Test
    void seeIfSetGetNameWorks() {
        // Arrange

        validDTO.setName("Test");
        String expectedResult = "Test";

        // Act

        String actualResult = validDTO.getName();

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfSetGetTypeAreaWorks() {
        // Arrange

        validDTO.setTypeArea("Mock");
        String expectedResult = "Mock";

        // Act

        String actualResult = validDTO.getTypeArea();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSetGetLengthWorks() {
        // Arrange

        validDTO.setLength(21D);

        // Act

        double actualResult = validDTO.getLength();

        // Assert

        assertEquals(21D, actualResult);
    }

    @Test
    void seeIfSetGetWidthWorks() {
        // Arrange

        validDTO.setWidth(13D);

        // Act

        double actualResult = validDTO.getWidth();

        // Assert

        assertEquals(13D, actualResult);
    }

    @Test
    void seeIfSetGetLocalWorks() {
        // Arrange
        LocalDTO expectedResult = new LocalDTO();
        LocalDTO localDTO = new LocalDTO();

        expectedResult.setAltitude(2);
        expectedResult.setLatitude(2);
        expectedResult.setLongitude(4);

        localDTO.setAltitude(2);
        localDTO.setLatitude(2);
        localDTO.setLongitude(4);

        validDTO.setLocalDTO(localDTO);

        // Act

        LocalDTO actualResult = validDTO.getLocalDTO();

        // Assert

        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        GeographicAreaDTO geographicAreaDTO = new GeographicAreaDTO();

        //Act

        int actualResult = geographicAreaDTO.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }

    @Test
    void seeIfSetGetSensorDTOListWorks() {
        // Arrange

        List<AreaSensorDTO> areaSensorDTOList = new ArrayList<>();
        validDTO.setSensorDTOList(areaSensorDTOList);

        // Act

        List<AreaSensorDTO> actualResult = validDTO.getSensorDTOs();

        // Assert

        assertEquals(areaSensorDTOList, actualResult);
    }

    @Test
    void seeIfSetGetDescriptionWorks() {
        // Arrange

        validDTO.setDescription("A geographic area.");
        String expectedResult = "A geographic area.";

        // Act

        String actualResult = validDTO.getDescription();

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfAddSensorWorks() {
        // Arrange

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();
        areaSensorDTO.setName("sensor1");
        areaSensorDTO.setActive(true);
        areaSensorDTO.setId("TT666");
        areaSensorDTO.setAltitude(6);
        areaSensorDTO.setLongitude(6);
        areaSensorDTO.setLatitude(6);
        areaSensorDTO.setDateStartedFunctioning("2018-10-12");
        areaSensorDTO.setTypeSensor("temperature");
        areaSensorDTO.setUnits("mm");

        // Act

        boolean actualResult = validDTO.addSensor(areaSensorDTO);
        boolean actualResult2 = validDTO.addSensor(areaSensorDTO);

        // Assert

        assertTrue(actualResult);
        assertFalse(actualResult2);
    }
}