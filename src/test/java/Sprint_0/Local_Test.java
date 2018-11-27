package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Local_Test {
    @Test
    public void seeIfGetSetLatitudeWorks() {
        //Arrange
        double expectedResult = 21;
        double actualResult;
        Local c = new Local(21, 3);

        //Act
        actualResult = c.getLatitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetSetLongitudeWorks() {
        //Arrange
        double expectedResult = 3;
        double actualResult;
        Local c = new Local(21, 3);

        //Act
        actualResult = c.getLongitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetSetAltitudeWorks() {
        //Arrange
        double expectedResult = 57;
        double actualResult;
        Local c = new Local(21, 3, 210);

        //Act
        c.setAltitude(57);
        actualResult = c.getAltitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLinearDistanceBetweenAreasWorks() {
        //Arrange
        Local l1 = new Local(41.16, -8.63);
        Local c = new Local(38.74, -9.14);
        double actualResult;
        double expectedResult = 2.473;

        //Act
        actualResult = c.getLinearDistanceBetweenLocals(l1, c);

        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfEqualsWorksSameObjects() {
        //Arrange
        Local l1 = new Local(23, 42, 2);
        Local l2 = l1;
        boolean expectedResult = true;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksNotInstance() {
        //Arrange
        Local l1 = new Local(23, 42, 2);
        TypeSensor t1 = new TypeSensor("Rua");
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = l1.equals(t1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void seeIfEqualsWorksDifferentObject(){
        //Arrange
        Local l1 = new Local(23, 42, 2);
        Local l2 = new Local(21,21,5);
        boolean expectedResult = false;
        boolean actualResult;

        //Act
        actualResult = l1.equals(l2);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void hashCodeDummyTest(){
        int expectedResult = 22671767;
        int actualResult = hashCode();
        assertEquals(expectedResult,actualResult);
    }
}
