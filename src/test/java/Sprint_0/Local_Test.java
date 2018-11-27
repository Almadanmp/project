package Sprint_0;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Local_Test {
    @Test
    public void seeIfGetSetLatitudeWorks() {
        //Arrange
        double expectedResult = 21;
        double actualResult;
        Local c = new Local(21,3);

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
        Local c = new Local(21,3);

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
        Local c = new Local(21,3, 210);

        //Act
        c.setAltitude(57);
        actualResult = c.getAltitude();

        //Assert
        assertEquals(expectedResult, actualResult, 0.001);
    }

    @Test
    public void seeIfGetLinearDistanceBetweenAreasWorks(){
        //Arrange
        Local l1 = new Local(41.16, -8.63);
        Local c = new Local(38.74, -9.14);
        double actualResult;
        double expectedResult = 2.473;

        //Act
        actualResult = c.getLinearDistanceBetweenLocals(l1,c);

        assertEquals(expectedResult,actualResult, 0.001);
    }

}
