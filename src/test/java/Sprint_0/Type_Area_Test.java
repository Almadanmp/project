package Sprint_0;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Type_Area_Test {
    @Test
    public void seeIfSetGetTypeWorks(){
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        TypeArea c = new TypeArea("Rua");

        //Act
        actualResult = c.getType();

        //Assert
        assertEquals(expectedResult,actualResult);
    }
}
