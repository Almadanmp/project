package Sprint_0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void checkGetGeographicAreaTypeAfterSet(){
        //Arrange
        String expectedResult = "Porto";
        String actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");

        //Act
        typeArea1.setTypeOfGeographicArea("Porto");
        actualResult = typeArea1.getTypeOfGeographicArea();

        //Assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void checkEqualsBetweenTwoGeographicAreaTypesWithSameDesignation(){
        //Arrange
        Boolean expectedResult = true;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void checkEqualsBetweenTwoGeographicAreaTypesWithDifferentDesignation(){
        //Arrange
        Boolean expectedResult = false;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        TypeArea typeArea2 = new TypeArea("Freguesia");
        //Act
        actualResult = typeArea1.equals(typeArea2);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void checkEqualsBetweenSameObject(){
        //Arrange
        Boolean expectedResult = true;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        //Act
        actualResult = typeArea1.equals(typeArea1);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
    @Test
    public void checkEqualsBetweenObjectsFromDifferentClasses(){
        //Arrange
        Boolean expectedResult = false;
        Boolean actualResult;
        TypeArea typeArea1 = new TypeArea("Rua");
        int number = 1;
        //Act
        actualResult = typeArea1.equals(number);
        //Assert
        assertEquals(expectedResult,actualResult);
    }
}
