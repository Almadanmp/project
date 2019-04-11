package pt.ipp.isep.dei.project.dto;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseSensorDTOTest {

    @Test
    void seeIfSetUnitWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setUnits("Unit");

        //Act

        String actualResult1 = houseSensorDTO1.getUnits();

        //Assert
        assertEquals("Unit", actualResult1);
    }

    @Test
    void seeIfSetIdWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setId("Id");

        //Act

        String actualResult1 = houseSensorDTO1.getId();

        //Assert
        assertEquals("Id", actualResult1);
    }

    @Test
    void seeIfSetReadingList() {
        //Arrange

        SimpleDateFormat validSdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            date = validSdf.parse("10/01/2018 09:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<ReadingDTO> readingDTOList = new ArrayList<>();
        ReadingDTO readingDTO = new ReadingDTO();
        readingDTO.setDate(date);
        readingDTO.setSensorId("Id");
        readingDTOList.add(readingDTO);

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setReadingList(readingDTOList);

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(readingDTO);

        //Act

        List<ReadingDTO> actualResult1 = houseSensorDTO1.getReadingList();

        //Assert
        assertEquals(expectedResult, actualResult1);
    }

    @Test
    void seeIfSetRoomIdWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setRoomID("Id");

        //Act

        String actualResult1 = houseSensorDTO1.getRoomID();

        //Assert
        assertEquals("Id", actualResult1);
    }

    @Test
    void seeIfSetTypeSensorWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setTypeSensor("Type");

        //Act

        String actualResult1 = houseSensorDTO1.getType();

        //Assert
        assertEquals("Type", actualResult1);
    }

    @Test
    void seeIfSetActiveWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setActive(true);

        //Act

        boolean actualResult1 = houseSensorDTO1.getActive();

        //Assert

        assertTrue(actualResult1);
    }

    @Test
    void seeIfSetInactiveWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setActive(false);

        //Act

        boolean actualResult1 = houseSensorDTO1.getActive();

        //Assert

        assertFalse(actualResult1);
    }


    @Test
    void seeIfEqualsWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();
        houseSensorDTO1.setName("Name1");

        HouseSensorDTO houseSensorDTO2 = new HouseSensorDTO();
        houseSensorDTO2.setName("Name1");

        HouseSensorDTO houseSensorDTO3 = new HouseSensorDTO();
        houseSensorDTO3.setName("Name2");

        //Act

        boolean actualResult1 = houseSensorDTO1.equals(houseSensorDTO1);
        boolean actualResult2 = houseSensorDTO1.equals(houseSensorDTO2);
        boolean actualResult3 = houseSensorDTO1.equals(houseSensorDTO3);
        boolean actualResult4 = houseSensorDTO1.equals(4D);

        //Assert
        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        HouseSensorDTO houseSensorDTO1 = new HouseSensorDTO();

        //Assert
        assertEquals(1, houseSensorDTO1.hashCode());
    }
}