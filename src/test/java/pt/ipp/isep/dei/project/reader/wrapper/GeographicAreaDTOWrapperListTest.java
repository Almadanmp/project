package pt.ipp.isep.dei.project.reader.wrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeographicAreaDTOWrapperListTest {

    @Test
    void seeIFGetGeoAreaDTOWrapperListWorks() {
        GeographicAreaDTOWrapperList wrapperList = new GeographicAreaDTOWrapperList();

        List<GeographicAreaDTOWrapper> finalList = new ArrayList<>();

        List<GeographicAreaDTOWrapper> result = wrapperList.getGeoAreaDTOWrapperList();

        assertEquals(finalList,result);
    }

    @Test
    void seeIfGetElementsAsArrayWorks() {
        GeographicAreaDTOWrapperList wrapperList = new GeographicAreaDTOWrapperList();
        List<GeographicAreaDTOWrapper> finalList = new ArrayList<>();
        GeographicAreaDTOWrapper wrapper = new GeographicAreaDTOWrapper();
        finalList.add(wrapper);
        wrapperList.setAreaDTOWrappers(finalList);
        GeographicAreaDTOWrapper[] array = new GeographicAreaDTOWrapper[]{wrapper};

        GeographicAreaDTOWrapper[] result = wrapperList.getElementsAsArray();

        assertArrayEquals(array,result);
    }

    @Test
    void seeIfEqualsWorks() {
        //Assert

        List<GeographicAreaDTOWrapper> finalList = new ArrayList<>();
        GeographicAreaDTOWrapperList wrapperList = new GeographicAreaDTOWrapperList(finalList);
        GeographicAreaDTOWrapper wrapper = new GeographicAreaDTOWrapper();
        finalList.add(wrapper);

        GeographicAreaDTOWrapperList wrapperList2 = new GeographicAreaDTOWrapperList();
        List<GeographicAreaDTOWrapper> finalList2 = new ArrayList<>();
        GeographicAreaDTOWrapper wrapper2 = new GeographicAreaDTOWrapper();
        finalList.add(wrapper2);
        wrapperList2.setAreaDTOWrappers(finalList2);

        //Act

        boolean actualResult1 = wrapperList.equals(wrapperList);
        boolean actualResult2 = wrapperList.equals(wrapperList2);
        boolean actualResult3 = wrapperList.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        GeographicAreaDTOWrapperList wrapper1 = new GeographicAreaDTOWrapperList();

        //Assert

        Assertions.assertEquals(wrapper1.hashCode(), 1);
    }
}