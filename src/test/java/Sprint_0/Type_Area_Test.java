package Sprint_0;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Type_Area_Test {

    @Test
    public void checkTypeOfGeographicAreaConstructor(){
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        //Act
        TypeArea typeArea1 = new TypeArea("Rua");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void checkSetGeographicAreaTypeWithSameDesignationFromConstructor(){
        //Arrange
        String expectedResult = "Rua";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Rua");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void checkSetGeographicAreaTypeWithDifferentDesignationFromConstructor(){
        //Arrange
        String expectedResult = "Freguesia";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Freguesia");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }
}
