package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.dto.ReadingDTOWithUnit;
import pt.ipp.isep.dei.project.reader.wrapper.ReadingDTOWrapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdapterTest {

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

        Unit celsius = new Celsius();
        ReadingDTOWithUnit readingDTOWithUnit = new ReadingDTOWithUnit();
        readingDTOWithUnit.setValue(20D);
        readingDTOWithUnit.setDate(validDate);
        readingDTOWithUnit.setUnit(celsius);

        Map<ReadingDTOWithUnit, String> expectedResult = new HashMap<>();
        expectedResult.put(readingDTOWithUnit, "TT12");

        // Act

        Map<ReadingDTOWithUnit, String> actualResult = Adapter.readingDTOWrapperConversion(readingDTOWrapperList);

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
        ReadingDTOWithUnit dtoCelsius1 = new ReadingDTOWithUnit();
        dtoCelsius1.setValue(20D);
        dtoCelsius1.setDate(validDate1);
        dtoCelsius1.setUnit(celsius);

        ReadingDTOWithUnit dtoCelsius2 = new ReadingDTOWithUnit();
        dtoCelsius2.setValue(24.44D);
        dtoCelsius2.setDate(validDate2);
        dtoCelsius2.setUnit(celsius);

        Map<ReadingDTOWithUnit, String> expectedResult = new HashMap<>();
        expectedResult.put(dtoCelsius1, "TT12");
        expectedResult.put(dtoCelsius2, "TT55");

        // Act

        Map<ReadingDTOWithUnit, String> actualResult = Adapter.readingDTOWrapperConversion(readingDTOWrapperList);

        // Assert

        assertEquals(expectedResult, actualResult);
    }


}
