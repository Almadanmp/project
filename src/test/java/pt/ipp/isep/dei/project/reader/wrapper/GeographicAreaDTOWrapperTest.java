package pt.ipp.isep.dei.project.reader.wrapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.AreaSensorDTO;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeographicAreaDTOWrapperTest {

    @Test
    void seeIfGeographicAreaDTOWrapperConversion() {

        List<GeographicAreaDTOWrapper> wrapperList = new ArrayList<>();

        LocalDTO local = new LocalDTO(14,15,1);

        GeographicAreaDTOWrapper wrapper = new GeographicAreaDTOWrapper();
        wrapper.setDescription("City of Gaia");
        wrapper.setLength(100);
        wrapper.setName("Gaia");
        wrapper.setTypeArea("city");
        wrapper.setWidth(100);
        wrapper.setLocalDTO(local);

        wrapperList.add(wrapper);

        List<GeographicAreaDTO> expectedResult = new ArrayList<>();

        GeographicAreaDTO dto = new GeographicAreaDTO();

        dto.setDescription("City of Gaia");
        dto.setLength(100);
        dto.setName("Gaia");
        dto.setTypeArea("city");
        dto.setWidth(100);
        dto.setLocalDTO(local);

        expectedResult.add(dto);

        List<GeographicAreaDTO> result = GeographicAreaDTOWrapper.geographicAreaDTOWrapperConversion(wrapperList);

        assertEquals(result, expectedResult);
    }

    @Test
    void areaSensorDTOWrapperConversion(){

        List<AreaSensorDTOWrapper> wrapperList = new ArrayList<>();

        LocalDTO local = new LocalDTO(14,15,1);

        AreaSensorAttributeWrapper attributeWrapper = new AreaSensorAttributeWrapper();

        attributeWrapper.setId("id");
        attributeWrapper.setDateStartedFunctioning("2010-11-02");
        attributeWrapper.setName("sensor");
        attributeWrapper.setUnits("C");
        attributeWrapper.setTypeSensor("temperature");

        AreaSensorDTOWrapper wrapper = new AreaSensorDTOWrapper();
        wrapper.setActive(true);
        wrapper.setSensorAttributeWrapper(attributeWrapper);
        wrapper.setLocalDTO(local);

        wrapperList.add(wrapper);

        List<AreaSensorDTO> expectedResult = new ArrayList<>();

        AreaSensorDTO dto = new AreaSensorDTO();

        dto.setId("id");
        dto.setDateStartedFunctioning("2010-11-02");
        dto.setName("sensor");
        dto.setUnits("C");
        dto.setTypeSensor("temperature");

        dto.setActive(true);
        dto.setLocalDTO(local);

        expectedResult.add(dto);

        List<AreaSensorDTO> result = GeographicAreaDTOWrapper.areaSensorDTOWrapperConversion(wrapperList);

        assertEquals(result, expectedResult);
    }

    @Test
    void seeIfEqualsWorks() {
        //Assert

        LocalDTO local = new LocalDTO(14,15,1);
        LocalDTO local2 = new LocalDTO(14,15,1);

        GeographicAreaDTOWrapper wrapper1 = new GeographicAreaDTOWrapper();
        wrapper1.setDescription("City of Gaia");
        wrapper1.setLength(100);
        wrapper1.setName("Gaia");
        wrapper1.setTypeArea("city");
        wrapper1.setWidth(100);
        wrapper1.setLocalDTO(local);

        GeographicAreaDTOWrapper wrapper2 = new GeographicAreaDTOWrapper(); //diff local
        wrapper2.setDescription("City of Gaia");
        wrapper2.setLength(100);
        wrapper2.setName("Gaia");
        wrapper2.setTypeArea("city");
        wrapper2.setWidth(100);
        wrapper2.setLocalDTO(local2);

        GeographicAreaDTOWrapper wrapper3 = new GeographicAreaDTOWrapper(); // diff type
        wrapper3.setDescription("City of Gaia");
        wrapper3.setLength(100);
        wrapper3.setName("Gaia");
        wrapper3.setTypeArea("urban");
        wrapper3.setWidth(100);
        wrapper3.setLocalDTO(local);

        GeographicAreaDTOWrapper wrapper4 = new GeographicAreaDTOWrapper(); // diff name
        wrapper4.setDescription("City of Gaia");
        wrapper4.setLength(100);
        wrapper4.setName("Porto");
        wrapper4.setTypeArea("city");
        wrapper4.setWidth(100);
        wrapper4.setLocalDTO(local);

        GeographicAreaDTOWrapper wrapper5 = new GeographicAreaDTOWrapper(); //diff type, name and local
        wrapper5.setDescription("City of Gaia");
        wrapper5.setLength(100);
        wrapper5.setName("Porto");
        wrapper5.setTypeArea("urban");
        wrapper5.setWidth(100);
        wrapper5.setLocalDTO(local2);

        GeographicAreaDTOWrapper wrapper6 = new GeographicAreaDTOWrapper(); //same as 1
        wrapper6.setDescription("City of Gaia");
        wrapper6.setLength(100);
        wrapper6.setName("Gaia");
        wrapper6.setTypeArea("city");
        wrapper6.setWidth(100);
        wrapper6.setLocalDTO(local);

        //Act

        boolean actualResult1 = wrapper1.equals(wrapper1);
        boolean actualResult2 = wrapper1.equals(wrapper2);
        boolean actualResult3 = wrapper1.equals(wrapper3);
        boolean actualResult4 = wrapper1.equals(wrapper4);
        boolean actualResult5 = wrapper1.equals(wrapper5);
        boolean actualResult6 = wrapper1.equals(wrapper6);
        boolean actualResult7 = wrapper1.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
      //  assertTrue(actualResult6);
        assertFalse(actualResult7);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        GeographicAreaDTOWrapper wrapper1 = new GeographicAreaDTOWrapper();

        //Assert

        assertEquals(wrapper1.hashCode(), 1);
    }
}