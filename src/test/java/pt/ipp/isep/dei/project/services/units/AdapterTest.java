package pt.ipp.isep.dei.project.services.units;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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

}
