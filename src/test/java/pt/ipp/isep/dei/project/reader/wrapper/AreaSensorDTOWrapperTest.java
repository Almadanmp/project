package pt.ipp.isep.dei.project.reader.wrapper;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.LocalDTO;

import static org.junit.jupiter.api.Assertions.*;

class AreaSensorDTOWrapperTest {


    @Test
    void seeIfEqualsWorks() {
        //Assert
        AreaSensorAttributeWrapper attributeWrapper = new AreaSensorAttributeWrapper();

        attributeWrapper.setId("id");
        attributeWrapper.setDateStartedFunctioning("2010-11-02");
        attributeWrapper.setName("sensor");
        attributeWrapper.setUnits("C");
        attributeWrapper.setTypeSensor("temperature");

        AreaSensorAttributeWrapper attributeWrapper1 = new AreaSensorAttributeWrapper();

        attributeWrapper1.setId("id1");
        attributeWrapper1.setDateStartedFunctioning("2010-11-02");
        attributeWrapper1.setName("sensor1");
        attributeWrapper1.setUnits("C1");
        attributeWrapper1.setTypeSensor("temperature1");

        AreaSensorDTOWrapper wrapper1 = new AreaSensorDTOWrapper();
        wrapper1.setSensorAttributeWrapper(attributeWrapper);

        //Act

        boolean actualResult1 = wrapper1.equals(wrapper1);
        boolean actualResult2 = wrapper1.equals(2D);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
    }

    @Test
    void seeIfHashCodeWorks() {
        //Arrange

        AreaSensorDTOWrapper wrapper1 = new AreaSensorDTOWrapper();

        //Assert

        assertEquals(wrapper1.hashCode(), 1);
    }

    @Test
    void seeIfEqualsAttributeWorks() {
        //Assert
        AreaSensorAttributeWrapper attributeWrapper = new AreaSensorAttributeWrapper();

        attributeWrapper.setId("id");
        attributeWrapper.setDateStartedFunctioning("2010-11-02");
        attributeWrapper.setName("sensor");
        attributeWrapper.setUnits("C");
        attributeWrapper.setTypeSensor("temperature");

        AreaSensorAttributeWrapper attributeWrapper1 = new AreaSensorAttributeWrapper();

        attributeWrapper1.setId("id1");
        attributeWrapper1.setDateStartedFunctioning("2010-11-02");
        attributeWrapper1.setName("sensor1");
        attributeWrapper1.setUnits("C1");
        attributeWrapper1.setTypeSensor("temperature1");


        //Act

        boolean actualResult1 = attributeWrapper.equals(attributeWrapper);
        boolean actualResult2 = attributeWrapper.equals(2D);
        boolean actualResult3 = attributeWrapper.equals(attributeWrapper1);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }

    @Test
    void seeIfHashCodeWorksAttribute() {
        //Arrange

        AreaSensorAttributeWrapper wrapper1 = new AreaSensorAttributeWrapper();

        //Assert

        assertEquals(wrapper1.hashCode(), 1);
    }
}