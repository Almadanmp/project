package pt.ipp.isep.dei.project.io.ui.reader.wrapper;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadingDTOLWrapperListTest {

    @Test
    void seeIfGetReadingDTOWrapperListWorks() {
        //Arrange

        List<ReadingDTOWrapper> expectedResult = new ArrayList<>();
        List<ReadingDTOWrapper> list = new ArrayList<>();

        Date validDate = new Date();
        Date diffDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = simpleDate.parse("2018-12-30T02:00:00+00:00");
            diffDate = simpleDate.parse("2018-12-30T04:55:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        ReadingDTOWrapper wrapper1 = new ReadingDTOWrapper();
        wrapper1.setDate(validDate);
        wrapper1.setSensorId("TT");

        ReadingDTOWrapper wrapper2 = new ReadingDTOWrapper();
        wrapper2.setDate(diffDate);
        wrapper2.setSensorId("TT");

        expectedResult.add(wrapper1);
        expectedResult.add(wrapper2);
        list.add(wrapper1);
        list.add(wrapper2);
        ReadingDTOLWrapperList wrapperList = new ReadingDTOLWrapperList(list);

        //Act

        List<ReadingDTOWrapper> actualResult = wrapperList.getReadingDTOWrapperList();

        //Assert

        assertEquals(expectedResult, actualResult);
    }
    @Test
    void seeIfGetElementsAsArrayWorks() {
        //Arrange

        List<ReadingDTOWrapper> list = new ArrayList<>();

        Date validDate = new Date();
        Date diffDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = simpleDate.parse("2018-12-30T02:00:00+00:00");
            diffDate = simpleDate.parse("2018-12-30T04:55:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        ReadingDTOWrapper wrapper1 = new ReadingDTOWrapper();
        wrapper1.setDate(validDate);
        wrapper1.setSensorId("TT");

        ReadingDTOWrapper wrapper2 = new ReadingDTOWrapper();
        wrapper2.setDate(diffDate);
        wrapper2.setSensorId("TT");

        ReadingDTOWrapper[] expectedResult1 = new ReadingDTOWrapper[2];
        expectedResult1[0] = wrapper1;
        expectedResult1[1] = wrapper2;

        list.add(wrapper1);
        list.add(wrapper2);
        ReadingDTOLWrapperList wrapperList = new ReadingDTOLWrapperList(list);

        //Act

        ReadingDTOWrapper[] actualResult = wrapperList.getElementsAsArray();

        //Assert

        assertArrayEquals(expectedResult1, actualResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Arrange

        List<ReadingDTOWrapper> list = new ArrayList<>();
        List<ReadingDTOWrapper> sameList = new ArrayList<>();
        List<ReadingDTOWrapper> difList = new ArrayList<>();

        Date validDate = new Date();
        Date diffDate = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = simpleDate.parse("2018-12-30T02:00:00+00:00");
            diffDate = simpleDate.parse("2018-12-30T04:55:00+00:00");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        ReadingDTOWrapper wrapper1 = new ReadingDTOWrapper();
        wrapper1.setDate(validDate);
        wrapper1.setSensorId("TT");

        ReadingDTOWrapper wrapper2 = new ReadingDTOWrapper();
        wrapper2.setDate(diffDate);
        wrapper2.setSensorId("TT");

        ReadingDTOWrapper[] expectedResult1 = new ReadingDTOWrapper[2];
        expectedResult1[0] = wrapper1;
        expectedResult1[1] = wrapper2;

        list.add(wrapper1);
        list.add(wrapper2);

        sameList.add(wrapper1);
        sameList.add(wrapper2);

        difList.add(wrapper1);

        ReadingDTOLWrapperList wrapperList = new ReadingDTOLWrapperList(list);
        ReadingDTOLWrapperList sameWrapperList = new ReadingDTOLWrapperList(sameList);
        ReadingDTOLWrapperList difWrapperList = new ReadingDTOLWrapperList(difList);

        //Act

        boolean actualResult1 = wrapperList.equals(sameWrapperList);
        boolean actualResult2 = wrapperList.equals(difWrapperList);
        boolean actualResult3 = wrapperList.equals(wrapperList);
        boolean actualResult4 = wrapperList.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertTrue(actualResult3);
        assertFalse(actualResult4);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        ReadingDTOLWrapperList wrapperList = new ReadingDTOLWrapperList();

        //Assert
        assertEquals(1, wrapperList.hashCode());
    }
}