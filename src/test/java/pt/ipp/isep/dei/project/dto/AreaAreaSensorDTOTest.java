package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AreaAreaSensorDTOTest {

    /**
     * Sensor tests class.
     */

    // Common artifacts for testing in this class.

    private AreaSensorDTO validAreaSensorDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validAreaSensorDTO = new AreaSensorDTO();
        validAreaSensorDTO.setActive(true);
        validAreaSensorDTO.setId("12");
        validAreaSensorDTO.setName("SensorDTO1");
        validAreaSensorDTO.setTypeSensor("Temperature");
        validAreaSensorDTO.setUnits("Celsius");
        validAreaSensorDTO.setLatitude(2);
        validAreaSensorDTO.setLongitude(4);
        validAreaSensorDTO.setAltitude(5);
        validAreaSensorDTO.setDateStartedFunctioning("21/03/2018 10:02:00");
    }

    @Test
    void seeIfGetUnitsWorks() {
        //Act

        String actualResult = validAreaSensorDTO.getUnits();

        //Assert

        assertEquals("Celsius", actualResult);
    }

    @Test
    void seeIfGetAltitudeWorks() {
        //Act

        double actualResult = validAreaSensorDTO.getAltitude();

        //Assert

        assertEquals(5D, actualResult, 0.01);
    }

    @Test
    void seeIfGetLatitudeWorks() {
        //Act

        double actualResult = validAreaSensorDTO.getLatitude();

        //Assert

        assertEquals(2D, actualResult, 0.01);
    }

    @Test
    void seeIfGetLongitudeWorks() {
        //Act

        double actualResult = validAreaSensorDTO.getLongitude();

        //Assert

        assertEquals(4D, actualResult, 0.01);
    }

    @Test
    void seeIfGetTypeWorks() {
        //Act

        String actualResult = validAreaSensorDTO.getType();

        //Assert

        assertEquals("Temperature", actualResult);
    }

    @Test
    void seeIfGetActiveWorks() {
        //Act

        boolean actualResult = validAreaSensorDTO.getActive();

        //Assert

        assertTrue(actualResult);
    }

    @Test
    void seeIfGetActiveWorksWhenInactive() {
        //Arrange

        validAreaSensorDTO.setActive(false);

        //Act

        boolean actualResult = validAreaSensorDTO.getActive();

        //Assert

        assertFalse(actualResult);
    }

    @Test
    void seeIfGetDateStartedFunctioningWorks() {
        //Act

        String actualResult = validAreaSensorDTO.getDateStartedFunctioning();

        //Assert

        assertEquals("21/03/2018 10:02:00", actualResult);
    }

    @Test
    void seeIfGetReading() {
        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("11/01/2018 10:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<ReadingDTO> readingDTOS = new ArrayList<>();
        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setSensorId("ID1");
        readingDTO.setDate(date);

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(readingDTO);
        readingDTOS.add(readingDTO);
        validAreaSensorDTO.setReadingDTOS(readingDTOS);


        //Act

        List<ReadingDTO> actualResult = validAreaSensorDTO.getReadingDTOS();

        //Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        AreaSensorDTO areaSensorDTO1 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO2 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO3 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO4 = new AreaSensorDTO();
        AreaSensorDTO areaSensorDTO5 = new AreaSensorDTO();

        areaSensorDTO1.setName("areaSensorDTO1");
        areaSensorDTO2.setName("areaSensorDTO1");
        areaSensorDTO3.setName("areaSensorDTO2");
        areaSensorDTO4.setName("areaSensorDTO1");
        areaSensorDTO5.setName("areaSensorDTO2");

        areaSensorDTO1.setId("01");
        areaSensorDTO2.setId("01");
        areaSensorDTO3.setId("02");
        areaSensorDTO4.setId("02");
        areaSensorDTO5.setId("01");

        Double actualResult = 3D;
        //Assert

        assertEquals(areaSensorDTO1, areaSensorDTO1);
        assertEquals(areaSensorDTO1, areaSensorDTO2);
        assertNotEquals(areaSensorDTO1, areaSensorDTO3);
        assertNotEquals(areaSensorDTO1, areaSensorDTO4);
        assertNotEquals(areaSensorDTO1, areaSensorDTO5);
        assertNotEquals(areaSensorDTO1, actualResult);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        AreaSensorDTO areaSensorDTO = new AreaSensorDTO();

        //Act

        int actualResult = areaSensorDTO.hashCode();

        //Assert

        assertEquals(1, actualResult);
    }
}