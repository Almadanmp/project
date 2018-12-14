package Sprint0_Test.ModelTest;


import Sprint0.Model.AuxiliaryMethods;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.Assert.assertTrue;


public class AuxiliaryMethodsTest {

    @Test
    public void seeIfListNullThrowsException() {
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            AuxiliaryMethods.checkIfListValid(null);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    public void seeIfListEmptyThrowsException() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            AuxiliaryMethods.checkIfListValid(valuesOfDay);
        });
        //Assert
        assertEquals("List is not valid", exception.getMessage());
    }

    @Test
    public void seeIfTrueIfListIsValid() {
        //Arrange
        List<Double> valuesOfDay = new ArrayList<>();
        valuesOfDay.add(31.0);

        //Act
        boolean actualResult = AuxiliaryMethods.checkIfListValid(valuesOfDay);

        //Assert
        assertTrue(actualResult);
    }
}

