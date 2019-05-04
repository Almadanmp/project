package pt.ipp.isep.dei.project.services.units;

import javassist.Modifier;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTO;
import pt.ipp.isep.dei.project.io.ui.reader.wrapper.ReadingDTOWrapper;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AdapterTest {

    @Test
    public void seeIfPrivateConstructorExceptionWorks() throws IllegalStateException {
        try {
            Constructor<Adapter> constructor = Adapter.class.getDeclaredConstructor();
            assertTrue(Modifier.isPrivate(constructor.getModifiers()));
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (InstantiationException e) {
        } catch (IllegalAccessException i) {
        } catch (InvocationTargetException a) {
        } catch (NoSuchMethodException ns) {
        }
    }

    @Test
    void seeIfConvertToSystemDefaultWorks() throws IOException {
        // Arrange

        double expectedResult = 20;
        double valueToConvert = 20;
        TemperatureUnit unit = new Celsius();

        // Act

        double actualResult = Adapter.convertToSystemDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConvertToSystemDefaultWorksWhenUnitIsDifferent() throws IOException {
        // Arrange

        double expectedResult = 4.44;
        double valueToConvert = 40;
        TemperatureUnit unit = new Fahrenheit();

        // Act

        double actualResult = Adapter.convertToSystemDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult, 0.01);
    }

    @Test
    void seeIfConvertToUserDefaultWorks() throws IOException {
        // Arrange

        double expectedResult = 10;
        double valueToConvert = 10;
        RainfallUnit unit = new Millimeter();

        // Act

        double actualResult = Adapter.convertToUserDefault(valueToConvert, unit);

        // Assert

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void seeIfReadingDTOWrapperConversionWorks() {
        // Arrange

        //Date

        Date validDate = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate = simpleDate.parse("2018-12-30T02:00:00+00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        //Wrapper

        List<ReadingDTOWrapper> readingDTOWrapperList = new ArrayList<>();

        ReadingDTOWrapper readingDTOWrapper = new ReadingDTOWrapper();
        readingDTOWrapper.setValue(20D);
        readingDTOWrapper.setDate(validDate);
        readingDTOWrapper.setUnit("C");
        readingDTOWrapper.setSensorId("TT12");
        readingDTOWrapperList.add(readingDTOWrapper);

        //Dto

        ReadingDTO readingDTOWithUnitAndSensorID = new ReadingDTO();
        readingDTOWithUnitAndSensorID.setValue(20D);
        readingDTOWithUnitAndSensorID.setDate(validDate);
        readingDTOWithUnitAndSensorID.setUnit("C");
        readingDTOWithUnitAndSensorID.setSensorId("TT12");

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(readingDTOWithUnitAndSensorID);

        // Act

        List<ReadingDTO> actualResult = Adapter.readingDTOWrapperConversion(readingDTOWrapperList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfReadingDTOWrapperConversionWorksWithUnitDifferentFromSystemDefault() {
        // Arrange

        //Dates
        Date validDate1 = new Date();
        Date validDate2 = new Date();

        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        try {
            validDate1 = simpleDate.parse("2018-12-30T02:00:00+00:00");
            validDate2 = simpleDate.parse("2018-10-12T11:35:00+00:00");

        } catch (ParseException c) {
            c.printStackTrace();
        }

        //Wrappers

        List<ReadingDTOWrapper> readingDTOWrapperList = new ArrayList<>();

        ReadingDTOWrapper wrapperCelsius = new ReadingDTOWrapper();
        wrapperCelsius.setValue(20D);
        wrapperCelsius.setDate(validDate1);
        wrapperCelsius.setUnit("C");
        wrapperCelsius.setSensorId("TT12");
        readingDTOWrapperList.add(wrapperCelsius);

        ReadingDTOWrapper wrapperFahrenh = new ReadingDTOWrapper();
        wrapperFahrenh.setValue(76D);
        wrapperFahrenh.setDate(validDate2);
        wrapperFahrenh.setUnit("F");
        wrapperFahrenh.setSensorId("TT55");
        readingDTOWrapperList.add(wrapperFahrenh);

        //Dtos

        Unit celsius = new Fahrenheit();
        ReadingDTO dtoCelsius1 = new ReadingDTO();
        dtoCelsius1.setValue(20D);
        dtoCelsius1.setDate(validDate1);
        dtoCelsius1.setUnit("C");
        dtoCelsius1.setSensorId("TT12");

        ReadingDTO dtoCelsius2 = new ReadingDTO();
        dtoCelsius2.setValue(24.44D);
        dtoCelsius2.setDate(validDate2);
        dtoCelsius2.setUnit("C");
        dtoCelsius2.setSensorId("TT55");

        List<ReadingDTO> expectedResult = new ArrayList<>();
        expectedResult.add(dtoCelsius1);
        expectedResult.add(dtoCelsius2);


        // Act

        List<ReadingDTO> actualResult = Adapter.readingDTOWrapperConversion(readingDTOWrapperList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}
